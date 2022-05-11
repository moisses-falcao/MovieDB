package com.example.citmoviedatabase_mf.models

import com.google.gson.annotations.SerializedName

data class SceneModel(
    @SerializedName("backdrops")
    val scenarios : List<PhotoModel>
)
