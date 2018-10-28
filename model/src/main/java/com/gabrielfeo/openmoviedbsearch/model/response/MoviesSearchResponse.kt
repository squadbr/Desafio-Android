package com.gabrielfeo.openmoviedbsearch.model.response

import com.gabrielfeo.openmoviedbsearch.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesSearchResponse(
    @SerializedName("Search") override val results: List<Movie>,
    @SerializedName("Response") val hasResults: Boolean,
    @SerializedName("totalResults") val numberOfSearchResults: Int
) : SearchResponse<List<Movie>> {

    override var page: Int? = null

}