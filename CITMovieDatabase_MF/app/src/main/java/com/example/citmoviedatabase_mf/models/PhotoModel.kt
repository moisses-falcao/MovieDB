package com.example.citmoviedatabase_mf.models

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    val id: Int,
    @SerializedName("file_path")
    val scenePath: String
)
