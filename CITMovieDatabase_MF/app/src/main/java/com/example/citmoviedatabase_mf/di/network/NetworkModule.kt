package com.example.citmoviedatabase_mf.di

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.constants.Constants.BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single(named("BASE_URL")) {
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(MovieDatabaseService::class.java)
    }
}