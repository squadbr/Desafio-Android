package br.com.cemobile.moviescoreseeker.interactor.usecase.movies

import br.com.cemobile.moviescoreseeker.data.repository.MoviesRepository

open class SearchMoviesImpl(private val moviesRepository: MoviesRepository) : SearchMovies {

    override suspend fun searchMovies(text: String) = moviesRepository.searchMovies(text)

}