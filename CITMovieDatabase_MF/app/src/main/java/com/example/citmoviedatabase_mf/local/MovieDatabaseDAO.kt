package com.example.citmoviedatabase_mf.local

import androidx.room.*
import com.example.citmoviedatabase_mf.models.MovieModel

@Dao
interface MovieDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun favoriteMovie(movieModel: MovieModel)

    @Delete
    suspend fun disfavorMovie(movieModel: MovieModel)

    @Query("SELECT * FROM movies")
    suspend fun getFavoriteList() : List<MovieModel>
}