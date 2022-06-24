package com.example.citmoviedatabase_mf.ui.activities.main

import com.example.citmoviedatabase_mf.models.MovieModel

sealed interface MainViewModelStatus {
    object EmptyList : MainViewModelStatus

    data class Success(val favoriteList: List<MovieModel>) : MainViewModelStatus
    data class Error(val error: Throwable) : MainViewModelStatus
}