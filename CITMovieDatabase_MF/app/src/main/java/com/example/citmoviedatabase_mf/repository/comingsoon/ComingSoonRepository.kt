package com.example.citmoviedatabase_mf.repository.comingsoon

import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

interface ComingSoonRepository {

    suspend fun getAllMoviesUpcoming() : ComingSoonStatus

    suspend fun favoriteMovie(movieModel: MovieModel) : ComingSoonStatus

    suspend fun disfavorMovie(movieModel: MovieModel) : ComingSoonStatus

    fun getFavoriteList() : ComingSoonStatus
}