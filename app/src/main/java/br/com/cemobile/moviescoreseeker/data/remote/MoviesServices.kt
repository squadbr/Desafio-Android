package br.com.cemobile.moviescoreseeker.data.remote

import br.com.cemobile.moviescoreseeker.model.MovieDetailsResponse
import br.com.cemobile.moviescoreseeker.model.MoviesListResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {

    @GET("/")
    fun searchMovies(
        @Query("s") text: String,
        @Query("page") page: Int
    ): Deferred<MoviesListResponse>

    @GET("/")
    fun getDetails(@Query("i") id: String): Deferred<MovieDetailsResponse>

}