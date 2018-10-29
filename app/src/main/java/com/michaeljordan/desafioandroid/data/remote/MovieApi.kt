package com.michaeljordan.desafioandroid.data.remote

import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET("/")
    fun search(
        @Query("apikey") key: String,
        @Query("s") title: String,
        @Query("type") type: String = "movie"
    ): Call<Search>

    @GET("/")
    fun getMovieById(
        @Query("apikey") key: String,
        @Query("i") title: String,
        @Query("plot") plot: String = "full"
    ): Call<Movie>
}