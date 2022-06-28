package com.example.citmoviedatabase_mf.repository.comingsoon

import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import kotlinx.coroutines.flow.Flow

sealed class ComingSoonStatus {
    object NotFound: ComingSoonStatus()
    data class Success(val listComingSoon: Results): ComingSoonStatus()
    data class Error(val error: Throwable): ComingSoonStatus()

    object SuccessInsertOnFavorites : ComingSoonStatus()
    object SuccessDeleteFromFavorites : ComingSoonStatus()
    data class SuccessFavoriteList(val favoriteList: Flow<List<MovieModel>>) : ComingSoonStatus()
}