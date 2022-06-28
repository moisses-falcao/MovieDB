package com.example.citmoviedatabase_mf.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import com.example.citmoviedatabase_mf.ui.nowplaying.NowPlayingViewModelStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ComingSoonViewModel(private val comingSoonRepository: ComingSoonRepository) : ViewModel() {

    val status = MutableLiveData<ComingSoonViewModelStatus>()

    fun getAllMoviesUpcoming() {

        viewModelScope.launch {

            when (val response = comingSoonRepository.getAllMoviesUpcoming()) {
                is ComingSoonStatus.Success -> {
                    status.value = ComingSoonViewModelStatus.Success(response.listComingSoon)
                }
                is ComingSoonStatus.NotFound -> {
                    status.value = ComingSoonViewModelStatus.NotFound
                }
                is ComingSoonStatus.Error -> {
                    status.value = ComingSoonViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusFavorite = MutableLiveData<ComingSoonViewModelStatus>()
    val statusFavorite: MutableLiveData<ComingSoonViewModelStatus>
        get() = _statusFavorite

    fun favoriteMovie(movieModel: MovieModel) {
        viewModelScope.launch {

            when (val response = comingSoonRepository.favoriteMovie(movieModel)) {
                is ComingSoonStatus.SuccessInsertOnFavorites -> {
                    response
                    _statusFavorite.value = ComingSoonViewModelStatus.SuccessInsertOnFavorites
                }
                is ComingSoonStatus.Error -> {
                    _statusFavorite.value = ComingSoonViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusDisfavor = MutableLiveData<ComingSoonViewModelStatus>()
    val statusDisfavor: MutableLiveData<ComingSoonViewModelStatus>
        get() = _statusDisfavor

    fun disfavorMovie(movieModel: MovieModel) {
        viewModelScope.launch {

            when (val response = comingSoonRepository.disfavorMovie(movieModel)) {
                is ComingSoonStatus.SuccessDeleteFromFavorites -> {
                    response
                    _statusDisfavor.value = ComingSoonViewModelStatus.SuccessDeleteFromFavorites
                }
                is ComingSoonStatus.Error -> {
                    _statusDisfavor.value = ComingSoonViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusFavoriteList = MutableStateFlow<ComingSoonViewModelStatus>(
        ComingSoonViewModelStatus.EmptyList)
    val statusFavoriteList: MutableStateFlow<ComingSoonViewModelStatus>
        get() = _statusFavoriteList

    fun getFavoriteList() {
        viewModelScope.launch {
            when (val response = comingSoonRepository.getFavoriteList()) {
                is ComingSoonStatus.SuccessFavoriteList -> {
                    response.favoriteList.collect {

                        _statusFavoriteList.value = ComingSoonViewModelStatus.SuccessFavoriteList(it)
                    }
                }
                is ComingSoonStatus.Error -> {
                    _statusFavoriteList.value = ComingSoonViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }
}