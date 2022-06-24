package com.example.citmoviedatabase_mf.ui.upcoming

import com.example.citmoviedatabase_mf.models.Results

sealed class ComingSoonViewModelStatus{
    object NotFound: ComingSoonViewModelStatus()
    data class Success(val listComingSoon: Results): ComingSoonViewModelStatus()
    data class Error(val error: Throwable): ComingSoonViewModelStatus()
}