package br.com.cemobile.moviescoreseeker.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(
    version = 1,
    entities = [MovieEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): MoviesDao
}