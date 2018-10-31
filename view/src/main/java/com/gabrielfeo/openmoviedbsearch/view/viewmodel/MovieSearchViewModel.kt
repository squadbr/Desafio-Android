package com.gabrielfeo.openmoviedbsearch.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielfeo.openmoviedbsearch.data.MovieRepository
import com.gabrielfeo.openmoviedbsearch.model.Movie
import java.util.*

class MovieSearchViewModel : ViewModel() {

    private val movieRepository by lazy { MovieRepository() }

    private val _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun searchMovies(query: String) = movieRepository.search(query) { results, _ ->
        _moviesList.value = results
        mapDetailsToAllResults()
    }

    private fun mapDetailsToAllResults() = moviesList.value?.let { originalList ->
        val newList = ArrayList<Movie>(originalList.size + 1)
        for (position in 0 until originalList.size - 1)
            movieRepository.getDetailsOf(originalList[position], onSuccess = {
                newList.add(it)
                this._moviesList.value = newList.sortedByDescending { movie -> movie.rating }
            })
    }

}