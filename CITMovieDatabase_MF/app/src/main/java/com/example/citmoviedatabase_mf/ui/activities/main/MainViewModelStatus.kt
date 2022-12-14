package com.example.citmoviedatabase_mf.ui.activities.main

import com.ciandt.service.models.MovieModel

sealed class MainViewModelStatus {
    object EmptyList : MainViewModelStatus()

    data class Success(val favoriteList: List<MovieModel>) : MainViewModelStatus()
    data class Error(val error: Throwable) : MainViewModelStatus()
    object SuccessDeleteFromFavorites : MainViewModelStatus()
}