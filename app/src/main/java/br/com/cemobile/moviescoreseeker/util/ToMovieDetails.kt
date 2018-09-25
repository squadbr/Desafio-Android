package br.com.cemobile.moviescoreseeker.util

import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.MovieDetails
import br.com.cemobile.moviescoreseeker.model.MovieDetailsResponse

object ToMovieDetails {

    operator fun invoke(response: MovieDetailsResponse): MovieDetails =
            with (response) {
                MovieDetails(
                    imdbId,
                    title,
                    year,
                    plot,
                    poster,
                    imdbRating,
                    ratings
                )
            }

    operator fun invoke(movie: Movie): MovieDetails =
            with (movie) {
                MovieDetails(
                    imdbId,
                    title,
                    year,
                    "",
                    poster,
                    "",
                    arrayListOf()
                )
            }

}