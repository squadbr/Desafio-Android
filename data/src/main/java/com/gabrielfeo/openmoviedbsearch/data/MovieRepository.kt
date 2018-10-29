package com.gabrielfeo.openmoviedbsearch.data

import com.gabrielfeo.openmoviedbsearch.data.remote.OpenMovieDb
import com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler.SearchResponseHandler
import com.gabrielfeo.openmoviedbsearch.model.Movie
import com.gabrielfeo.openmoviedbsearch.model.response.MoviesSearchResponse

class MovieRepository {

    fun search(query: String, onResultsReceived: (results: List<Movie>, page: Int?) -> Unit) {
        val responseHandler = SearchResponseHandler<MoviesSearchResponse, List<Movie>>()
        responseHandler.onSuccessfulResponseResults = onResultsReceived
        OpenMovieDb.moviesService.searchMovies(query).enqueue(responseHandler)
    }

}