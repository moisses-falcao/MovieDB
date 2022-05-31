package com.example.citmoviedatabase_mf.details

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus


class DetailsViewModel(private val detailsRepository: DetailsRepository): ViewModel() {

    val statusDetails =  MediatorLiveData<DetailsViewModelStatus>()
    val statusCredits =  MediatorLiveData<DetailsViewModelStatus>()
    val statusScenes =  MediatorLiveData<DetailsViewModelStatus>()

    fun getMovieDetails(movieId: String){

        detailsRepository.getMovieDetails(movieId){
            when(it){
                is DetailsStatus.SuccessDetails ->{
                    statusDetails.value = DetailsViewModelStatus.SuccessDetails(it.movieDetails)
                }
                is DetailsStatus.NotFound ->{
                    statusDetails.value = DetailsViewModelStatus.NotFound
                }
                is DetailsStatus.Error ->{
                    statusDetails.value = DetailsViewModelStatus.Error(it.error)
                }
                else -> {}
            }
        }
    }

    fun getMovieCredits(movieId: String){

        detailsRepository.getMovieCredits(movieId){
            when(it){
                is DetailsStatus.SuccessCredits ->{
                    statusCredits.value = DetailsViewModelStatus.SuccessCredits(it.casting)
                }
                is DetailsStatus.NotFound ->{
                    statusCredits.value = DetailsViewModelStatus.NotFound
                }
                is DetailsStatus.Error ->{
                    statusCredits.value = DetailsViewModelStatus.Error(it.error)
                }
                else -> {}
            }
        }
    }

    fun getMovieScenes(movieId: String){

        detailsRepository.getMovieScenes(movieId){
            when(it){
                is DetailsStatus.SuccessScenes ->{
                    statusScenes.value = DetailsViewModelStatus.SuccessScenes(it.scenes)
                }
                is DetailsStatus.NotFound ->{
                    statusScenes.value = DetailsViewModelStatus.NotFound
                }
                is DetailsStatus.Error ->{
                    statusScenes.value = DetailsViewModelStatus.Error(it.error)
                }
                else -> {}
            }
        }
    }
}