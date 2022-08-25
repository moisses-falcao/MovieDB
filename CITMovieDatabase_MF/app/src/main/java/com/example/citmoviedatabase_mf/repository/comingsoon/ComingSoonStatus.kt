package com.example.citmoviedatabase_mf.repository.comingsoon

import com.ciandt.service.models.MovieModel
import com.ciandt.service.models.Results
import kotlinx.coroutines.flow.Flow

sealed class ComingSoonStatus {
    object NotFound: ComingSoonStatus()
    data class Success(val listComingSoon: Results): ComingSoonStatus()
    data class Error(val error: Throwable): ComingSoonStatus()

    object SuccessInsertOnFavorites : ComingSoonStatus()
    object SuccessDeleteFromFavorites : ComingSoonStatus()
    data class SuccessFavoriteList(val favoriteList: Flow<List<MovieModel>>) : ComingSoonStatus()
}