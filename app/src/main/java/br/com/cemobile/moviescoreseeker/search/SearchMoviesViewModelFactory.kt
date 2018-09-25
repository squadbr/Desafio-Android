package br.com.cemobile.moviescoreseeker.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMovies

class SearchMoviesViewModelFactory(private val searchMovies: SearchMovies) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        SearchMoviesViewModel(searchMovies) as T

}