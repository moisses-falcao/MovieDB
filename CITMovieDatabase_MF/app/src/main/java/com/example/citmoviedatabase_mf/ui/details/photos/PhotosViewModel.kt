package com.example.citmoviedatabase_mf.ui.details.photos

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus
import kotlinx.coroutines.launch

class PhotosViewModel(private val photosRepository: PhotosRepository): ViewModel() {

    val status = MediatorLiveData<PhotosViewModelStatus>()

    fun getMovieScenes(movieId: String){

        viewModelScope.launch{
            when(val response = photosRepository.getMovieScenes(movieId)){
                is PhotosStatus.Success ->{
                    status.value = PhotosViewModelStatus.Success(response.scenes)
                }
                is PhotosStatus.NotFound ->{
                    status.value = PhotosViewModelStatus.NotFound
                }
                is PhotosStatus.Error ->{
                    status.value = PhotosViewModelStatus.Error(response.error)
                }
            }
        }
    }
}