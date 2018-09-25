package br.com.cemobile.moviescoreseeker.search

import android.arch.lifecycle.MutableLiveData
import br.com.cemobile.moviescoreseeker.base.BaseViewModel
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMovies
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.Resource
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main

open class SearchMoviesViewModel(private val useCase: SearchMovies) : BaseViewModel() {

    private companion object {
        const val MIN_CHARS_TO_BEGIN_SEARCH = 2
        const val DELAY_400_MS = 400L
    }

    val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()
    var queryTextChangedJob: Job? = null
    var query = ""
        set(value) {
            field = value
            onQueryTextChange(value)
        }

    fun searchMovies(text: String) {
        if (text.length > MIN_CHARS_TO_BEGIN_SEARCH) {
            runJob {
                showLoading()
                try {
                    val movies = useCase.searchMovies(text)
                    showMoviesList(movies)
                } catch (t: Throwable) {
                    showError(t)
                }
            }
        } else {
            showNone()
        }
    }

    private fun onQueryTextChange(query: String) {
        queryTextChangedJob?.cancel()
        queryTextChangedJob = GlobalScope.launch(Dispatchers.Main) {
            delay(DELAY_400_MS)
            searchMovies(query)
        }
    }

    private fun showLoading() {
//        moviesLiveData.postValue(Resource.loading(null))
        loadingLiveData.postValue(true)
    }

    private fun hideLoading() {
        loadingLiveData.postValue(false)
    }

    private fun showMoviesList(movies: List<Movie>) {
        moviesLiveData.postValue(Resource.success(movies))
    }

    private fun showError(error: Throwable) {
//        moviesLiveData.postValue(Resource.error(error, null))
        errorLiveData.postValue(error)
    }

    private fun showNone() {
        moviesLiveData.postValue(Resource.none())
    }

}