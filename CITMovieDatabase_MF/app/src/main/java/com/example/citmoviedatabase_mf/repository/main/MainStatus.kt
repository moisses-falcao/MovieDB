package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.models.MovieModel

sealed class MainStatus{
    object EmptyList : MainStatus()

    data class Success(val favoriteList: List<MovieModel>) : MainStatus()
    data class Error(val error: Throwable) : MainStatus()
}
