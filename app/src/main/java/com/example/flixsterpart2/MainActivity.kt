package com.example.flixsterpart2

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.flixsterpart2.R.id
//
///**
// * The MainActivity for the BestSellerList app.
// * Launches a [PersonFragment].
// */
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val supportFragmentManager = supportFragmentManager
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(id.content, PersonFragment(), null).commit()
//    }
//}

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixsterpart2.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val ARTICLE_SEARCH_URL =
    "https://api.themoviedb.org/3/person/popular?&api_key=${SEARCH_API_KEY}"

class MainActivity : AppCompatActivity() {
    private var models = mutableListOf<Person>()
    private lateinit var personRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        personRecyclerView = findViewById(R.id.people)
        //Set up PersonAdapter with articles
        val personAdapter = PersonAdapter(this, models)
        personRecyclerView.adapter = personAdapter

        personRecyclerView.layoutManager = GridLayoutManager(this, 2)
//        personRecyclerView.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            personRecyclerView.addItemDecoration(dividerItemDecoration)
//        }

        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    //Create the parsedJSON
                    //Do something with the returned json (contains article information)
//                    val parsedJson = createJson().decodeFromString(
//                        SearchPersonResponse.serializer(),
//                        json.jsonObject.toString()
//                    )

                    val peopleRawJSON: String = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayPersonType = object : TypeToken<List<Person>>() {}.type
                    val modelList: List<Person> = gson.fromJson(peopleRawJSON, arrayPersonType)
                    modelList.let{list ->
                        models.addAll(list)
                        personAdapter.notifyDataSetChanged()
                    }
                    Log.d("Models", models.toString())

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}