package com.example.citmoviedatabase_mf.di.details

import com.example.citmoviedatabase_mf.details.DetailsViewModel
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single {
        DetailsRepositoryImpl(get()) as DetailsRepository
    }

    viewModel {
        DetailsViewModel(get())
    }
}

