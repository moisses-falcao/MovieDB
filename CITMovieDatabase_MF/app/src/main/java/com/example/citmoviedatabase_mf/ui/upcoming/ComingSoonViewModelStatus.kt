package com.example.citmoviedatabase_mf.ui.upcoming

import com.ciandt.service.models.MovieModel
import com.ciandt.service.models.Results

sealed class ComingSoonViewModelStatus{
    object NotFound: ComingSoonViewModelStatus()
    data class Success(val listComingSoon: Results): ComingSoonViewModelStatus()
    data class Error(val error: Throwable): ComingSoonViewModelStatus()

    object SuccessInsertOnFavorites : ComingSoonViewModelStatus()
    object SuccessDeleteFromFavorites : ComingSoonViewModelStatus()
    object EmptyList: ComingSoonViewModelStatus()
    data class SuccessFavoriteList(val favoriteList: List<MovieModel>) : ComingSoonViewModelStatus()
}
