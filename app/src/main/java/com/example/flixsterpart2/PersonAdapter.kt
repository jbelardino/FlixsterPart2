package com.example.flixsterpart2

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"
private const val TAG = "PersonAdapter"

class PersonAdapter(private val context: Context, private val people: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the individual article and bind to holder
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount() = people.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val personImageView = itemView.findViewById<ImageView>(R.id.personImage)
        private val nameTextView = itemView.findViewById<TextView>(R.id.personName)


        init {
            itemView.setOnClickListener(this)
        }

        //Write a helper method to help set up the onBindViewHolder method
        fun bind(person: Person) {
            Log.d("BINDING", "person.name =" + person.name)
            nameTextView.text = person.name

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + person.profile_path)
                .centerInside()
                .into(personImageView)
        }

        override fun onClick(v: View?) {
            // Get selected article
            val person = people[absoluteAdapterPosition]

            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARTICLE_EXTRA, person)
            context.startActivity(intent)
        }
    }
}