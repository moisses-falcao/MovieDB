package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import kotlinx.coroutines.flow.Flow

sealed class MainStatus{

    data class Success(val favoriteList: Flow<List<MovieModel>>) : MainStatus()
    data class Error(val error: Throwable) : MainStatus()

    object SuccessDeleteFromFavorites : MainStatus()
}
