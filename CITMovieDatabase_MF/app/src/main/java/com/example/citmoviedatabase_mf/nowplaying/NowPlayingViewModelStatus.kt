package com.example.citmoviedatabase_mf.nowplaying

import com.example.citmoviedatabase_mf.models.Results

sealed class NowPlayingViewModelStatus{

    object NotFound: NowPlayingViewModelStatus()
    data class Success(val listNowPlaying: Results): NowPlayingViewModelStatus()
    data class Error(val error: Throwable): NowPlayingViewModelStatus()
}
