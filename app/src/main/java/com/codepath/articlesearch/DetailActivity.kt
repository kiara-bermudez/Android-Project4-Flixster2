package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        overviewTextView = findViewById(R.id.mediaOverview)

        // TODO: Get the extra from the Intent
        val show = intent.getSerializableExtra(SHOW_EXTRA) as Show

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = show.name
        overviewTextView.text = show.overview

        // TODO: Load the media image
        Glide.with(this)
            .load(show.posterUrl)
            .into(mediaImageView)
    }
}