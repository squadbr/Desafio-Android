package com.michaeljordan.desafioandroid.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("imdbID")
    val imdbId: String,
    @SerializedName("Poster")
    val poster: String,
    val imdbRating: String,
    @SerializedName("Plot")
    val plot: String
) : Parcelable