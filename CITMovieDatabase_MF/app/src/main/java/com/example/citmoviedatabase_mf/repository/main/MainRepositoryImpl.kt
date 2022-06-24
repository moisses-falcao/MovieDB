package com.example.citmoviedatabase_mf.repository.main

import com.example.citmoviedatabase_mf.local.MovieDatabaseDAO
import com.example.citmoviedatabase_mf.models.MovieModel

class MainRepositoryImpl(val movieDatabaseDAO: MovieDatabaseDAO) : MainRepository {

    override suspend fun getFavoriteList(): MainStatus {
        return try {
            val response = movieDatabaseDAO.getFavoriteList()
            if (response.isNotEmpty()){
                return MainStatus.Success(response)
            }else{
                MainStatus.EmptyList
            }
        }catch (e: Exception){
            MainStatus.Error(e)
        }
    }
}