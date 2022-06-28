package com.example.citmoviedatabase_mf.ui.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.main.MainRepository
import com.example.citmoviedatabase_mf.repository.main.MainStatus
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import com.example.citmoviedatabase_mf.ui.nowplaying.NowPlayingViewModelStatus
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _status = MutableStateFlow<MainViewModelStatus>(MainViewModelStatus.Success(emptyList()))
    val status: MutableStateFlow<MainViewModelStatus>
        get() = _status

    fun getFavoriteList() {
        viewModelScope.launch {

            when (val response = mainRepository.getFavoriteList()) {
                is MainStatus.Success -> {
                    response.favoriteList.collect {
                        if (it.isNotEmpty()){
                            _status.value = MainViewModelStatus.Success(it)
                        }else{
                            _status.value = MainViewModelStatus.EmptyList
                        }
                    }
                }
                is MainStatus.Error ->{
                    _status.value = MainViewModelStatus.Error(response.error)
                }
            }
        }
    }

    private val _statusDisfavor = MutableLiveData<MainViewModelStatus>()
    val statusDisfavor: MutableLiveData<MainViewModelStatus>
        get() = _statusDisfavor

    fun disfavorMovie(movieModel: MovieModel) {
        viewModelScope.launch {

            when (val response = mainRepository.disfavorMovie(movieModel)) {
                is MainStatus.SuccessDeleteFromFavorites -> {
                    response
                    _statusDisfavor.value = MainViewModelStatus.SuccessDeleteFromFavorites
                }
                is MainStatus.Error -> {
                    _statusDisfavor.value = MainViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }
}