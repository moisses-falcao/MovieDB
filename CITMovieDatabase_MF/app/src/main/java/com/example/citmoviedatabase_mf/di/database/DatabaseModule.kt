package com.example.citmoviedatabase_mf.di.database

import android.app.Application
import androidx.room.Room
import com.example.citmoviedatabase_mf.local.FavoriteDatabase
import com.example.citmoviedatabase_mf.local.MovieDatabaseDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application) : FavoriteDatabase{
        return Room.databaseBuilder(application, FavoriteDatabase::class.java, "FAVORITE_DATABASE").build()
    }

    fun provideDao(database: FavoriteDatabase) : MovieDatabaseDAO{
        return database.movieDatabaseDAO()
    }

    single{ provideDatabase(androidApplication()) }
    single{ provideDao(get()) }
}