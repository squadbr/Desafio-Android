package br.com.cemobile.moviescoreseeker.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "imdb_id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "poster") val poster: String
)