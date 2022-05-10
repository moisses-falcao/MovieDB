package com.example.citmoviedatabase_mf.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(

    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val genres: List<GenreModel>,
    val runtime: Int?
)
