package br.com.cemobile.moviescoreseeker.di

import android.arch.persistence.room.Room
import br.com.cemobile.moviescoreseeker.data.local.AppDatabase
import org.koin.dsl.module.applicationContext

val databaseModule = applicationContext {

    // AppDatabase
    bean {
        Room.databaseBuilder(get(), AppDatabase::class.java, "movies_score_seeker.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    // AppDao
    bean {
        val db: AppDatabase = get()
        db.dao()
    }

}