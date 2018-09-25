package br.com.cemobile.moviescoreseeker.search

import br.com.cemobile.moviescoreseeker.model.Movie

interface OnMovieSelectedListener {
    fun onMovieSelected(movie: Movie)
}