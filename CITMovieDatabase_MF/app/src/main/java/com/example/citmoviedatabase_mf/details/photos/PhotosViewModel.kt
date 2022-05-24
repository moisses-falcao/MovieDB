package com.example.citmoviedatabase_mf.details.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepositoryImpl
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus

class PhotosViewModel(private val photosRepository: PhotosRepository): ViewModel() {

    fun getMovieScenes(movieId: String): LiveData<PhotosStatus>{
        return photosRepository.getMovieScenes(movieId)
    }
}