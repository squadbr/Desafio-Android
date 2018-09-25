package br.com.cemobile.moviescoreseeker.di

import br.com.cemobile.moviescoreseeker.core.AppExecutors
import br.com.cemobile.moviescoreseeker.core.Executors
import br.com.cemobile.moviescoreseeker.data.remote.MoviesServiceFactory
import br.com.cemobile.moviescoreseeker.data.repository.MoviesRepository
import br.com.cemobile.moviescoreseeker.data.repository.MoviesRepositoryImpl
import br.com.cemobile.moviescoreseeker.details.MovieDetailsViewModel
import br.com.cemobile.moviescoreseeker.details.MovieDetailsViewModelFactory
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.GetMovieDetails
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.GetMovieDetailsImpl
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMovies
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMoviesImpl
import br.com.cemobile.moviescoreseeker.search.SearchMoviesViewModel
import br.com.cemobile.moviescoreseeker.search.SearchMoviesViewModelFactory
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {

    bean { AppExecutors() as Executors }

    bean { MoviesServiceFactory.create(debuggable = true) }

    bean { MoviesRepositoryImpl(get()) as MoviesRepository }

    bean { SearchMoviesImpl(get()) as SearchMovies}

    bean { GetMovieDetailsImpl(get()) as GetMovieDetails }

    factory { SearchMoviesViewModelFactory(get()) }

    factory { MovieDetailsViewModelFactory(get()) }

    viewModel { SearchMoviesViewModel(get()) }

    viewModel { MovieDetailsViewModel(get()) }

}