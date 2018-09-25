package br.com.cemobile.moviescoreseeker.data.repository

import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.MovieDetails
import br.com.cemobile.moviescoreseeker.util.NetworkException

interface MoviesRepository {

    @Throws(NetworkException::class)
    suspend fun searchMovies(text: String): List<Movie>

    @Throws(NetworkException::class)
    suspend fun getDetails(id: String): MovieDetails

}