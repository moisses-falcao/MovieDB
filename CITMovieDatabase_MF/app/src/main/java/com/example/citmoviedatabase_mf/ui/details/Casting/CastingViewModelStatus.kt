package com.example.citmoviedatabase_mf.ui.details.Casting

import com.ciandt.service.models.CastModel

sealed class CastingViewModelStatus{
    data class Success(val casting: CastModel): CastingViewModelStatus()
    data class Error(val error: Throwable): CastingViewModelStatus()
    object NotFound: CastingViewModelStatus()
}
