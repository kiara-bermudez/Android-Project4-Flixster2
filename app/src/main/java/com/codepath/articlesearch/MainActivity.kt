package com.codepath.articlesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codepath.articlesearch.R
import com.codepath.articlesearch.ShowsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, ShowsFragment(), null).commit()
    }
}