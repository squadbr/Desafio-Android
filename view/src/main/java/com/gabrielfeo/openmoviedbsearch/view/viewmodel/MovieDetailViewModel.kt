package com.gabrielfeo.openmoviedbsearch.view.viewmodel

import androidx.lifecycle.ViewModel
import com.gabrielfeo.openmoviedbsearch.model.Movie
import com.google.gson.Gson

class MovieDetailViewModel : ViewModel() {

    var movie: Movie? = null

    fun setMovie(serializedMovie: String) {
        movie = Gson().fromJson(serializedMovie, Movie::class.java)
    }

}