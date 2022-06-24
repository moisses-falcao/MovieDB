package com.example.citmoviedatabase_mf.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.citmoviedatabase_mf.models.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDatabaseDAO() : MovieDatabaseDAO
}