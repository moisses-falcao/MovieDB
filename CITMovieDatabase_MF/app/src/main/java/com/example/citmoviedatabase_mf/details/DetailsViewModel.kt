package com.example.citmoviedatabase_mf.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus


class DetailsViewModel(private val detailsRepository: DetailsRepository = DetailsRepositoryImpl()): ViewModel() {



    fun getMovieDetails(movieId: String): LiveData<DetailsStatus>{
        return detailsRepository.getMovieDetails(movieId)
    }

    fun getMovieCredits(movieId: String): LiveData<DetailsStatus>{
        return detailsRepository.getMovieCredits(movieId)
    }

    fun getMovieScenes(movieId: String): LiveData<DetailsStatus>{
        return detailsRepository.getMovieScenes(movieId)
    }
}