package com.example.citmoviedatabase_mf.ui.details.photos

import com.example.citmoviedatabase_mf.models.SceneModel

sealed class PhotosViewModelStatus{
    data class Success(val scenes: SceneModel) : PhotosViewModelStatus()
    data class Error(val error: Throwable) : PhotosViewModelStatus()
    object NotFound : PhotosViewModelStatus()
}
