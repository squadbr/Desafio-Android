package com.michaeljordan.desafioandroid.repository

import com.michaeljordan.desafioandroid.BuildConfig
import com.michaeljordan.desafioandroid.data.remote.MovieApi
import com.michaeljordan.desafioandroid.data.remote.RetrofitFactory
import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.model.Search
import retrofit2.Call

class MovieRepository {
    private val service: MovieApi = RetrofitFactory.create()

    fun search(title: String): Call<Search> {
        return service.search(BuildConfig.API_KEY, title)
    }

    fun getMovieById(title: String): Call<Movie> {
        return service.getMovieById(BuildConfig.API_KEY, title)
    }

    object MovieRepositoryProvider {
        fun provideMovieRepository(): MovieRepository {
            return MovieRepository()
        }
    }
}