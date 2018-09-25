package br.com.cemobile.moviescoreseeker.interactor.usecase.movies

import br.com.cemobile.moviescoreseeker.data.repository.MoviesRepository

open class GetMovieDetailsImpl(private val moviesRepository: MoviesRepository) : GetMovieDetails {

    override suspend fun getDetails(imdbId: String) = moviesRepository.getDetails(imdbId)

}