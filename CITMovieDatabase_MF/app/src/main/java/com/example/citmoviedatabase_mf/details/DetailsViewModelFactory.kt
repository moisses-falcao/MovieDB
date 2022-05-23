package com.example.citmoviedatabase_mf.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl

class DetailsViewModelFactory constructor(private val detailsRepositoryImpl: DetailsRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(detailsRepositoryImpl) as T
        } else {
            throw (IllegalAccessException("ViewModel não encontrada"))
        }
    }
}