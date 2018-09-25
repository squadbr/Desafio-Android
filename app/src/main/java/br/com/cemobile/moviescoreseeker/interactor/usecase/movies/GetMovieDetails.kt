package br.com.cemobile.moviescoreseeker.interactor.usecase.movies

import br.com.cemobile.moviescoreseeker.model.MovieDetails

interface GetMovieDetails {

    suspend fun getDetails(imdbId: String): MovieDetails

}