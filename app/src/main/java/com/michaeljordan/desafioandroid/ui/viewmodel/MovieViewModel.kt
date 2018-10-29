package com.michaeljordan.desafioandroid.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.model.Search
import com.michaeljordan.desafioandroid.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    val repository = MovieRepository.MovieRepositoryProvider.provideMovieRepository()
    private lateinit var searchResult: MutableLiveData<List<Movie?>>

    fun getSearchMoviesObservable(): MutableLiveData<List<Movie?>> {
        if (!::searchResult.isInitialized) {
            searchResult = MutableLiveData()
        }
        return searchResult
    }

    fun searchMovies(title: String) {
        doSearch(title)
    }

    private fun doSearch(title: String) {
        val movies: ArrayList<Movie?> = ArrayList()
        val callSearch = repository.search(title)

        callSearch.enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.d("Movie", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val result = response.body()

                if (result?.movies == null) {
                    searchResult.value = ArrayList()
                } else {
                    result.movies.forEach {
                        val callInfo = repository.getMovieById(it!!.imdbId)

                        callInfo.enqueue(object : Callback<Movie> {
                            override fun onFailure(call: Call<Movie>, t: Throwable) {
                                Log.d("Movie", "Error: ${t.message}")
                            }

                            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                                movies.add(response.body())
                                searchResult.value = movies
                            }

                        })
                    }
                }
            }

        })
    }
}