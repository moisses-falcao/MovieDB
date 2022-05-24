package com.example.citmoviedatabase_mf.di.upcoming

import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepositoryImpl
import com.example.citmoviedatabase_mf.upcoming.ComingSoonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comingSoonModule = module {
    single {
        ComingSoonRepositoryImpl(get()) as ComingSoonRepository
    }

    viewModel {
        ComingSoonViewModel(get())
    }
}

