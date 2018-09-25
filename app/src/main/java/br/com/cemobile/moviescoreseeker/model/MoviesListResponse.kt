package br.com.cemobile.moviescoreseeker.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("Search") val movies: List<Movie>? = null,
    @SerializedName("totalResults") val totalResults: Int? = null,
    @SerializedName("Response") val response: Boolean,
    @SerializedName("Error") val error: String? = null
)