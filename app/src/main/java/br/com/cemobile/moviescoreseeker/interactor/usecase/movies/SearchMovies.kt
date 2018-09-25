package br.com.cemobile.moviescoreseeker.interactor.usecase.movies

import br.com.cemobile.moviescoreseeker.model.Movie

interface SearchMovies {

    suspend fun searchMovies(text: String): List<Movie>

}