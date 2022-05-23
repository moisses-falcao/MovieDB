package com.example.citmoviedatabase_mf.details.Casting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingRepositoryImpl
import com.example.citmoviedatabase_mf.repository.casting.CastingStatus

class CastingViewModel (val castingRepository: CastingRepository = CastingRepositoryImpl()) : ViewModel() {

    fun getMovieCredits(movieId: String): LiveData<CastingStatus>{
        return castingRepository.getMovieCredits(movieId)
    }
}