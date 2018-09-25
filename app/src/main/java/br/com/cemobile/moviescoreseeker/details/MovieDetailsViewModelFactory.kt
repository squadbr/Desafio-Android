package br.com.cemobile.moviescoreseeker.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.GetMovieDetails

class MovieDetailsViewModelFactory(
    private val getMovieDetails: GetMovieDetails
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            MovieDetailsViewModel(getMovieDetails) as T

}