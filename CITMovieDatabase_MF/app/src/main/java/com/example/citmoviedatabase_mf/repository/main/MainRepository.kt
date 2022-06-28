package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

interface MainRepository {

    fun getFavoriteList() : MainStatus

    suspend fun disfavorMovie(movieModel: MovieModel) : MainStatus
}