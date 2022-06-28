package com.example.citmoviedatabase_mf.repository.nowplaying

import com.example.citmoviedatabase_mf.models.MovieModel


interface NowPlayingRepository {

    suspend fun getAllMoviesNowPlaying() : NowPlayingStatus

    suspend fun favoriteMovie(movieModel: MovieModel) : NowPlayingStatus

    suspend fun disfavorMovie(movieModel: MovieModel) : NowPlayingStatus

    fun getFavoriteList() : NowPlayingStatus
}