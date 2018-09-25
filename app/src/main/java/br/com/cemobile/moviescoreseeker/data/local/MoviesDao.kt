package br.com.cemobile.moviescoreseeker.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveMovies(entities: List<MovieEntity>)

    @Query(value = ALL_MOVIES) fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query(value = DELETE_ALL_MOVIES) fun deleteAllMovies()

    private companion object {
        const val ALL_MOVIES = "SELECT * FROM movies"
        const val DELETE_ALL_MOVIES = "DELETE FROM movies"
    }

}