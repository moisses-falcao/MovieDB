package com.example.citmoviedatabase_mf.di

import android.app.Application
import com.example.citmoviedatabase_mf.di.NowPlaying.nowPlayingModule
import com.example.citmoviedatabase_mf.di.database.databaseModule
import com.example.citmoviedatabase_mf.di.details.casting.castingModule
import com.example.citmoviedatabase_mf.di.details.detailsModule
import com.example.citmoviedatabase_mf.di.details.photos.photosModule
import com.example.citmoviedatabase_mf.di.main.mainModule
import com.example.citmoviedatabase_mf.di.upcoming.comingSoonModule
import com.example.citmoviedatabase_mf.remoteconfig.RemoteConfigUtils
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {

            if(BuildConfig.DEBUG){
                androidLogger()
            }

            androidContext(this@MyApp)

            modules(networkModule, nowPlayingModule, comingSoonModule, detailsModule, castingModule, photosModule, databaseModule, mainModule)
        }

        //Init RemoteConfig
        RemoteConfigUtils.init()
    }
}