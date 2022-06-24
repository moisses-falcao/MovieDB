package com.example.citmoviedatabase_mf.di.details.photos

import com.example.citmoviedatabase_mf.ui.details.photos.PhotosViewModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photosModule = module {
    single {
        PhotosRepositoryImpl(get()) as PhotosRepository
    }
    viewModel {
        PhotosViewModel(get())
    }
}