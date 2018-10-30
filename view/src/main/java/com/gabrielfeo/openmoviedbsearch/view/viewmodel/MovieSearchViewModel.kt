package com.gabrielfeo.openmoviedbsearch.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielfeo.openmoviedbsearch.data.MovieRepository
import com.gabrielfeo.openmoviedbsearch.model.Movie

class MovieSearchViewModel : ViewModel() {

    private val movieRepository by lazy { MovieRepository() }

    private val _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun searchMovies(query: String) =
        movieRepository.search(query) { results, _ -> _moviesList.value = results }

}