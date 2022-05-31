package com.example.citmoviedatabase_mf.details.photos

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus

class PhotosViewModel(private val photosRepository: PhotosRepository): ViewModel() {

    val status = MediatorLiveData<PhotosViewModelStatus>()

    fun getMovieScenes(movieId: String){

        photosRepository.getMovieScenes(movieId){
            when(it){
                is PhotosStatus.Success ->{
                    status.value = PhotosViewModelStatus.Success(it.scenes)
                }
                is PhotosStatus.NotFound ->{
                    status.value = PhotosViewModelStatus.NotFound
                }
                is PhotosStatus.Error ->{
                    status.value = PhotosViewModelStatus.Error(it.error)
                }
            }
        }
    }
}