package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TAG = "ShowsFragment"

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}


/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the MovieDB API.
 */
class ShowsFragment : Fragment() {
    private val shows = mutableListOf<Show>()

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        val context = view.context
        // TODO: Set up ArticleAdapter with articles
        val showAdapter = ShowAdapter(context, shows)
        recyclerView.adapter = showAdapter

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView, showAdapter)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView, showAdapter: ShowAdapter) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        client["https://api.themoviedb.org/3/tv/popular?", params, object:
            JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)

                // The wait for a response is over
                progressBar.hide()

                // If the error is not null, log it!
                throwable?.message?.let {
                    Log.e(TAG, "Failed to fetch shows: $statusCode")
                }
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "Successfully fetched articles: $json")

                // The wait for a response is over
                progressBar.hide()

                try {
                    // TODO: Create the parsedJSON
                    val parsedJson = createJson().decodeFromString(
                        SearchShowsResults.serializer(),
                        json.jsonObject.toString()
                    )

                    // TODO: Do something with the returned json (contains article information)
                    parsedJson.results?.let { list ->
                        shows.addAll(list)
                    }

                    // TODO: Save the articles and reload the screen
                    parsedJson.results?.let { list ->
                        shows.addAll(list)

                        // Reload the screen
                        showAdapter.notifyDataSetChanged()
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }

            }


        }
        ]

    }


}