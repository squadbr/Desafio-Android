package com.gabrielfeo.openmoviedbsearch.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("imdbRating") val rating: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Plot") val synopsis: String,
    @SerializedName("Language") val languages: String,
    @SerializedName("Country") val countries: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Poster") val posterUrl: String
) {

    override fun equals(other: Any?): Boolean = (other as? Movie)?.id == this.id

}