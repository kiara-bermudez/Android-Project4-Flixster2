package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The Model for storing a single movie from the MovieDB API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
//class Movie {
//    @JvmField
//    @SerializedName("title")
//    var title: String? = null
//
//    @JvmField
//    @SerializedName("overview")
//    var overview: String? = null
//
//    @JvmField
//    @SerializedName("poster_path")
//    var movieImageUrl: String? = null
//}

@Keep
@Serializable
data class SearchShowsResults(
    @SerialName("results")
    val results: List<Show>?
)

@Keep
@Serializable
data class Show(
    @SerialName("original_name")
    val name: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("poster_path")
    val posterImg: String?,
) : java.io.Serializable {
    val posterUrl = "https://image.tmdb.org/t/p/w500/${posterImg}"
}