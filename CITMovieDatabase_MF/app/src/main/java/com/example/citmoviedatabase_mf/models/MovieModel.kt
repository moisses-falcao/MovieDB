package com.example.citmoviedatabase_mf.models

import com.example.citmoviedatabase_mf.models.GenreModel

data class MovieModel(
    val id: Int,
    val voteAverage: Double,
    val title: String,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: Int, //Change to String whe using API
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val genres: String //Change to List<Genre> when using API
)
