package com.example.citmoviedatabase_mf.repository.nowplaying

import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import kotlinx.coroutines.flow.Flow

sealed class NowPlayingStatus {
    object NotFound : NowPlayingStatus()
    data class Success(val listNowPlaying: Results): NowPlayingStatus()
    data class Error(val error: Throwable): NowPlayingStatus()

    object SuccessInsertOnFavorites : NowPlayingStatus()
    object SuccessDeleteFromFavorites : NowPlayingStatus()
    data class SuccessFavoriteList(val favoriteList: Flow<List<MovieModel>>) : NowPlayingStatus()
}