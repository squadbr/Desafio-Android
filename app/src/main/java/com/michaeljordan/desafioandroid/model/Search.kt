package com.michaeljordan.desafioandroid.model

import com.google.gson.annotations.SerializedName

data class Search(
        @SerializedName("Search")
        var movies: List<Movie?>,
        val totalResults: String
)