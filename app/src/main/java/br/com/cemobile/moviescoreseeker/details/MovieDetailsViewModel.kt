package br.com.cemobile.moviescoreseeker.details

import android.arch.lifecycle.MutableLiveData
import br.com.cemobile.moviescoreseeker.base.BaseViewModel
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.GetMovieDetails
import br.com.cemobile.moviescoreseeker.model.MovieDetails
import br.com.cemobile.moviescoreseeker.model.Resource

open class MovieDetailsViewModel(private val useCase: GetMovieDetails) : BaseViewModel() {

    val movieDetailsLiveData = MutableLiveData<Resource<MovieDetails>>()

    fun getMovieDetails(imdbId: String) {
        runJob {
            showLoading()
            try {
                val details = useCase.getDetails(imdbId)
                showDetails(details)
            } catch (t: Throwable) {
                showError(t)
            }
        }
    }

    private fun showLoading() {
        movieDetailsLiveData.postValue(Resource.loading(null))
    }

    private fun showDetails(details: MovieDetails) {
        movieDetailsLiveData.postValue(Resource.success(details))
    }

    private fun showError(error: Throwable) {
        movieDetailsLiveData.postValue(Resource.error(error, null))
    }


}