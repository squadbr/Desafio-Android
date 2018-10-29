package com.michaeljordan.desafioandroid.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        @SerializedName("Title")
        var title: String,
        @SerializedName("imdbID")
        var imdbId: String,
        @SerializedName("Poster")
        var poster: String,
        var imdbRating: String,
        @SerializedName("Plot")
        var plot: String
) : Parcelable