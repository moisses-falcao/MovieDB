package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.models.MovieModel

interface MainRepository {

    suspend fun getFavoriteList() : MainStatus
}