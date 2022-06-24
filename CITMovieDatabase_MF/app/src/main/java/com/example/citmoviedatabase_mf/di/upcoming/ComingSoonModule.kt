package com.example.citmoviedatabase_mf.di.upcoming

import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepositoryImpl
import com.example.citmoviedatabase_mf.ui.upcoming.ComingSoonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comingSoonModule = module {
    single<ComingSoonRepository> {
        ComingSoonRepositoryImpl(get())
    }

    viewModel {
        ComingSoonViewModel(get())
    }
}

