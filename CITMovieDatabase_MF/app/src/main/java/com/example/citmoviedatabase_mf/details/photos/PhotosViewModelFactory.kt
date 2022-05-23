package com.example.citmoviedatabase_mf.details.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepositoryImpl
import com.example.citmoviedatabase_mf.repository.casting.CastingRepositoryImpl
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl

class PhotosViewModelFactory constructor(private val photosRepositoryImpl: PhotosRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(photosRepositoryImpl) as T
        } else {
            throw (IllegalAccessException("ViewModel não encontrada"))
        }
    }
}