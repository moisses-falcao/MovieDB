package com.example.citmoviedatabase_mf.di.main

import com.example.citmoviedatabase_mf.repository.main.MainRepository
import com.example.citmoviedatabase_mf.repository.main.MainRepositoryImpl
import com.example.citmoviedatabase_mf.ui.activities.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module{
    single<MainRepository>{
        MainRepositoryImpl(movieDatabaseDAO = get())
    }

    viewModel {
        MainViewModel(get())
    }
}