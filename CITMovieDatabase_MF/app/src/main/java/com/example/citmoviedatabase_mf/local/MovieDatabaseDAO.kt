package com.example.citmoviedatabase_mf.local

import androidx.room.*
import com.example.citmoviedatabase_mf.models.MovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun favoriteMovie(movieModel: MovieModel)

    @Delete
    suspend fun disfavorMovie(movieModel: MovieModel)

    @Query("SELECT * FROM movies")
    fun getFavoriteList() : Flow<List<MovieModel>>
}