package com.example.citmoviedatabase_mf.di.NowPlaying

import com.example.citmoviedatabase_mf.nowplaying.NowPlayingViewModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nowPlayingModule = module {
    single {
        NowPlayingRepositoryImpl(get()) as NowPlayingRepository
    }

    viewModel {
        NowPlayingViewModel(get())
    }
}