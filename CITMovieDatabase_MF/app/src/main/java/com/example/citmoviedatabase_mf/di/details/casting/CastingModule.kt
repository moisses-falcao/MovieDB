package com.example.citmoviedatabase_mf.di.details.casting

import com.example.citmoviedatabase_mf.details.Casting.CastingViewModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingRepositoryImpl
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

