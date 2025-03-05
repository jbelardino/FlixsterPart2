package com.example.flixsterpart2

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var headshotImageView: ImageView
    private lateinit var posterImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var knownForTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_detail)

        //Find the views for the screen
        headshotImageView = findViewById(R.id.headshot)
        posterImageView = findViewById(R.id.moviePoster)
        nameTextView = findViewById(R.id.name)
        knownForTextView = findViewById(R.id.knownFor)
        descriptionTextView = findViewById(R.id.description)

        //Get the extra from the Intent
        val person = intent.getSerializableExtra(ARTICLE_EXTRA) as Person

        //Set the title, byline, and abstract information from the article
        val knownForText = "Known For: " + person.known_for?.get(0)?.title
        nameTextView.text = person.name
        knownForTextView.text = knownForText
        descriptionTextView.text = person.known_for?.get(0)?.overview
        Log.d(TAG, "knownFor: " + person.known_for)

        //Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + person.profile_path)
            .centerInside()
            .into(headshotImageView)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + person.known_for?.get(0)?.poster_path)
            .centerInside()
            .into(posterImageView)
    }
}