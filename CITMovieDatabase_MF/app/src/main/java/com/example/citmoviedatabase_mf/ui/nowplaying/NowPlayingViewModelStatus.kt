package com.example.citmoviedatabase_mf.ui.nowplaying

import com.ciandt.service.models.MovieModel
import com.ciandt.service.models.Results

sealed class NowPlayingViewModelStatus{

    object NotFound: NowPlayingViewModelStatus()
    data class Success(val listNowPlaying: Results): NowPlayingViewModelStatus()
    data class Error(val error: Throwable): NowPlayingViewModelStatus()

    object SuccessInsertOnFavorites : NowPlayingViewModelStatus()
    object SuccessDeleteFromFavorites : NowPlayingViewModelStatus()
    object EmptyList: NowPlayingViewModelStatus()
    data class SuccessFavoriteList(val favoriteList: List<MovieModel>) : NowPlayingViewModelStatus()
}
