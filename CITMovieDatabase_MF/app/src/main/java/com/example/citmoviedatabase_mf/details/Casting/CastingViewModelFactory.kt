package com.example.citmoviedatabase_mf.details.Casting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citmoviedatabase_mf.repository.casting.CastingRepositoryImpl
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl

class CastingViewModelFactory constructor(private val castingRepositoryImpl: CastingRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CastingViewModel::class.java)) {
            return CastingViewModel(castingRepositoryImpl) as T
        } else {
            throw (IllegalAccessException("ViewModel não encontrada"))
        }
    }
}