package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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