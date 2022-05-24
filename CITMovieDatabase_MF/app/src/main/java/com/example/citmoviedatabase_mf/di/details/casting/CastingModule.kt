package com.example.citmoviedatabase_mf.di.details.casting

import com.example.citmoviedatabase_mf.details.Casting.CastingViewModel
import com.example.citmoviedatabase_mf.details.DetailsViewModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingRepositoryImpl
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepositoryImpl
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl
import com.example.citmoviedatabase_mf.upcoming.ComingSoonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val castingModule = module {
    single {
        CastingRepositoryImpl(get()) as CastingRepository
    }

    viewModel {
        CastingViewModel(get())
    }
}

