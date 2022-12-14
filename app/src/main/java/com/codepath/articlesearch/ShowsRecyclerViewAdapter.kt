package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val SHOW_EXTRA = "SHOW_EXTRA"
private const val TAG = "ShowAdapter"

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class ShowAdapter(
    private val context: Context,
    private val shows: List<Show>)
    : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>(){

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView),
        View.OnClickListener {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row

        private val posterImageView = itemView.findViewById<ImageView>(R.id.poster_image)
        private val titleTextView = itemView.findViewById<TextView>(R.id.show_title)

        init {
            mView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(show: Show) {
            titleTextView.text = show.name

            Glide.with(context)
                .load(show.posterUrl)
                .into(posterImageView)
        }

        override fun onClick(p0: View?) {
            // TODO: Get selected article
            val show = shows[absoluteAdapterPosition]

            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(SHOW_EXTRA, show)
            context.startActivity(intent)
        }

    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.fragment_show, parent, false)
        // Return a new holder instance
        return ShowViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        // Get the data model based on position
        val show = shows[position]
        holder.bind(show)

    }

    override fun getItemCount(): Int {
        return shows.size
    }


}