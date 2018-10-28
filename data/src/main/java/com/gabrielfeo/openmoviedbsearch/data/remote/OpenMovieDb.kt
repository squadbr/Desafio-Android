package com.gabrielfeo.openmoviedbsearch.data.remote

import com.gabrielfeo.openmoviedbsearch.data.remote.net.RetrofitProvider.retrofit
import com.gabrielfeo.openmoviedbsearch.model.response.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal class OpenMovieDb private constructor() {

    companion object {
        val moviesService: MoviesService by lazy { retrofit.create(MoviesService::class.java) }
    }

    interface MoviesService {

        @GET("?type=movie")
        fun searchMovies(
            @Query("s") query: String,
            @Query("page") page: Int = 1
        ): Call<MoviesSearchResponse>

    }

}