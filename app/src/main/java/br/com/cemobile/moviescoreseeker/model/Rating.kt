package br.com.cemobile.moviescoreseeker.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Rating(
    @SerializedName("Source") val source: String,
    @SerializedName("value") val value: String
) : Parcelable