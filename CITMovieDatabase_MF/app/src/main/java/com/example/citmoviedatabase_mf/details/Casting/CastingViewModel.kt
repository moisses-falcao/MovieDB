package com.example.citmoviedatabase_mf.details.Casting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingStatus
import kotlinx.coroutines.launch

class CastingViewModel (private val castingRepository: CastingRepository) : ViewModel() {

    val status = MutableLiveData<CastingViewModelStatus>()

    fun getMovieCredits(movieId: String){

        viewModelScope.launch {
            when(val response = castingRepository.getMovieCredits(movieId)){
                is CastingStatus.Success ->{
                    status.value = CastingViewModelStatus.Success(response.casting)
                }
                is CastingStatus.NotFound ->{
                    status.value = CastingViewModelStatus.NotFound
                }
                is CastingStatus.Error ->{
                    status.value = CastingViewModelStatus.Error(response.error)
                }
            }
        }
    }
}