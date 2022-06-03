package com.example.citmoviedatabase_mf.details

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus
import kotlinx.coroutines.launch


class DetailsViewModel(private val detailsRepository: DetailsRepository): ViewModel() {

    val statusDetails =  MediatorLiveData<DetailsViewModelStatus>()
    val statusCredits =  MediatorLiveData<DetailsViewModelStatus>()
    val statusScenes =  MediatorLiveData<DetailsViewModelStatus>()

    fun getMovieDetails(movieId: String){

        viewModelScope.launch{
            when(val response = detailsRepository.getMovieDetails(movieId)){
                is DetailsStatus.SuccessDetails ->{
                    statusDetails.value = DetailsViewModelStatus.SuccessDetails(response.movieDetails)
                }
                is DetailsStatus.Error ->{
                    statusDetails.value = DetailsViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    fun getMovieCredits(movieId: String){

        viewModelScope.launch{
            when(val response = detailsRepository.getMovieCredits(movieId)){
                is DetailsStatus.SuccessCredits ->{
                    statusCredits.value = DetailsViewModelStatus.SuccessCredits(response.casting)
                }
                is DetailsStatus.NotFound ->{
                    statusCredits.value = DetailsViewModelStatus.NotFound
                }
                is DetailsStatus.Error ->{
                    statusCredits.value = DetailsViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    fun getMovieScenes(movieId: String){

        viewModelScope.launch{
            when(val response = detailsRepository.getMovieScenes(movieId)){
                is DetailsStatus.SuccessScenes ->{
                    statusScenes.value = DetailsViewModelStatus.SuccessScenes(response.scenes)
                }
                is DetailsStatus.NotFound ->{
                    statusScenes.value = DetailsViewModelStatus.NotFound
                }
                is DetailsStatus.Error ->{
                    statusScenes.value = DetailsViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }
}