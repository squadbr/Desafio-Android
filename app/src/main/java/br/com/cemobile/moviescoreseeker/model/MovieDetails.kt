package br.com.cemobile.moviescoreseeker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    val imdbId: String,
    val title: String,
    val year: String,
    val plot: String,
    val poster: String,
    val imdbRating: String,
    val ratings: List<Rating>
) : Parcelable