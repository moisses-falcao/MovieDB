package com.example.citmoviedatabase_mf.filters

import com.example.citmoviedatabase_mf.models.GenreModel
import com.example.citmoviedatabase_mf.models.MovieModel

class Filters {
    private fun ordenarPorOrdemAlfabetica(list: List<MovieModel>) : List<MovieModel> {
        return list.sortedBy { it.title }
    }

    private fun ordenarPorDataDeLancamento(list: List<MovieModel>) : List<MovieModel> {
        return list.sortedBy { it.releaseDate }
    }

//    private fun filtratPorGenero(genero: List<GenreModel>, list: List<MovieModel>) : List<MovieModel> {
//        return list.filter { it.genres.contains(genero)}
//    }
}