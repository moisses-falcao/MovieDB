package com.example.citmoviedatabase_mf.di

import android.app.Application
import com.example.citmoviedatabase_mf.di.NowPlaying.nowPlayingModule
import com.example.citmoviedatabase_mf.di.details.casting.castingModule
import com.example.citmoviedatabase_mf.di.details.detailsModule
import com.example.citmoviedatabase_mf.di.details.photos.photosModule
import com.example.citmoviedatabase_mf.di.upcoming.comingSoonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin


class MyApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)

            modules(networkModule, nowPlayingModule, comingSoonModule, detailsModule, castingModule, photosModule)
        }
    }
}