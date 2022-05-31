package com.example.citmoviedatabase_mf.repository.nowplaying


interface NowPlayingRepository {

    fun getAllMoviesNowPlaying(nowPlayingStatus: (NowPlayingStatus) -> Unit)
}