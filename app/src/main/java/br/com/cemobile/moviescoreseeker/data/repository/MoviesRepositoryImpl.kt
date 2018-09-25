package br.com.cemobile.moviescoreseeker.data.repository

import br.com.cemobile.moviescoreseeker.data.remote.MoviesServices
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.MovieDetails
import br.com.cemobile.moviescoreseeker.util.ToMovieDetails
import br.com.cemobile.moviescoreseeker.util.execute

open class MoviesRepositoryImpl(private val moviesServices: MoviesServices) : MoviesRepository {

    override suspend fun searchMovies(text: String): List<Movie> {
        val response = moviesServices.searchMovies(text, page = 1).execute()
        return response.movies ?: emptyList()
    }

    override suspend fun getDetails(id: String): MovieDetails {
        val response = moviesServices.getDetails(id).execute()
        return ToMovieDetails(response)
    }

}