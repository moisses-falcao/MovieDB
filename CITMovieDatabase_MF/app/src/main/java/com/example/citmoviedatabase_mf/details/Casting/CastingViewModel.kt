package com.example.citmoviedatabase_mf.details.Casting

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingStatus

class CastingViewModel (private val castingRepository: CastingRepository) : ViewModel() {

    val status = MediatorLiveData<CastingViewModelStatus>()

    fun getMovieCredits(movieId: String){

        status.addSource(castingRepository.getMovieCredits(movieId)){
            when(it){
                is CastingStatus.Success ->{
                    status.value = CastingViewModelStatus.Success(it.casting)
                }
                is CastingStatus.NotFound ->{
                    status.value = CastingViewModelStatus.NotFound
                }
                is CastingStatus.Error ->{
                    status.value = CastingViewModelStatus.Error(it.error)
                }
            }
        }
    }
}