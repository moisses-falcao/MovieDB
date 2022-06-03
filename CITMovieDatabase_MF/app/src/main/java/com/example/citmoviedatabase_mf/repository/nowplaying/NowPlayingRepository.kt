package com.example.citmoviedatabase_mf.repository.nowplaying


interface NowPlayingRepository {

    suspend fun getAllMoviesNowPlaying() : NowPlayingStatus
}