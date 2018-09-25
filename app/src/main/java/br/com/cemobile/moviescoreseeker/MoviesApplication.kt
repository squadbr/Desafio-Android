package br.com.cemobile.moviescoreseeker

import android.app.Application
import br.com.cemobile.moviescoreseeker.di.appModule
import br.com.cemobile.moviescoreseeker.di.databaseModule
import org.koin.android.ext.android.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, databaseModule))
    }

}