package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.local.MovieDatabaseDAO
import com.ciandt.service.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList

class MainRepositoryImpl(val movieDatabaseDAO: MovieDatabaseDAO) : MainRepository {

    override fun getFavoriteList(): MainStatus {
        return try {
            val response = movieDatabaseDAO.getFavoriteList()

                MainStatus.Success(response)
        }catch (e: Exception){
            MainStatus.Error(e)
        }
    }

    override suspend fun disfavorMovie(movieModel: MovieModel): MainStatus {
        return try {
            movieDatabaseDAO.disfavorMovie(movieModel)

            MainStatus.SuccessDeleteFromFavorites
        } catch (e: Exception){
            MainStatus.Error(e)
        }
    }
}