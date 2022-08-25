package com.example.citmoviedatabase_mf.ui.details

import com.ciandt.service.models.CastModel
import com.ciandt.service.models.MovieDetailsModel
import com.ciandt.service.models.SceneModel

sealed class DetailsViewModelStatus{
    object NotFound: DetailsViewModelStatus()
    data class Error(val error: Throwable) : DetailsViewModelStatus()

    data class SuccessDetails(val movieDetails: MovieDetailsModel) : DetailsViewModelStatus()
    data class SuccessCredits(val casting: CastModel) : DetailsViewModelStatus()
    data class SuccessScenes(val scenes: SceneModel) : DetailsViewModelStatus()
}
