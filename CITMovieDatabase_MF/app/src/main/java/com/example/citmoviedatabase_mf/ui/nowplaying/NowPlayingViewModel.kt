package com.example.citmoviedatabase_mf.ui.nowplaying

import androidx.lifecycle.*
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import kotlinx.coroutines.launch

class NowPlayingViewModel(private val nowPlayingRepository: NowPlayingRepository): ViewModel() {

    val status = MutableLiveData<NowPlayingViewModelStatus>()

    fun getAllMoviesNowPlaying() {
        viewModelScope.launch {

            when(val response = nowPlayingRepository.getAllMoviesNowPlaying()){
                is NowPlayingStatus.Success ->{
                    status.value = NowPlayingViewModelStatus.Success(response.listNowPlaying)
                }
                is NowPlayingStatus.NotFound ->{
                    status.value = NowPlayingViewModelStatus.NotFound
                }
                is NowPlayingStatus.Error ->{
                    status.value = NowPlayingViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusFavorite = MutableLiveData<NowPlayingViewModelStatus>()
    val statusFavorite: MutableLiveData<NowPlayingViewModelStatus>
        get() = _statusFavorite

    fun favoriteMovie(movieModel: MovieModel){
        viewModelScope.launch {

            when(val response = nowPlayingRepository.favoriteMovie(movieModel)){
                is NowPlayingStatus.SuccessInsertOnFavorites ->{
                    response
                    _statusFavorite.value = NowPlayingViewModelStatus.SuccessInsertOnFavorites
                }
                is NowPlayingStatus.Error->{
                    _statusFavorite.value = NowPlayingViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusDisfavor = MutableLiveData<NowPlayingViewModelStatus>()
    val statusDisfavor: MutableLiveData<NowPlayingViewModelStatus>
        get() = _statusDisfavor

    fun disfavorMovie(movieModel: MovieModel){
        viewModelScope.launch {

            when(val response = nowPlayingRepository.disfavorMovie(movieModel)){
                is NowPlayingStatus.SuccessDeleteFromFavorites ->{
                    response
                    _statusDisfavor.value = NowPlayingViewModelStatus.SuccessDeleteFromFavorites
                }
                is NowPlayingStatus.Error ->{
                    _statusDisfavor.value = NowPlayingViewModelStatus.Error(response.error)
                }
                else -> {}
            }
        }
    }

    private val _statusFavoriteList = MutableLiveData<NowPlayingViewModelStatus>()
    val statusFavoriteList: MutableLiveData<NowPlayingViewModelStatus>
        get() = _statusFavoriteList

    fun getFavoriteList(){
        viewModelScope.launch {
            when(val response = nowPlayingRepository.getFavoriteList()){
                is NowPlayingStatus.SuccessFavoriteList ->{
                    _statusFavoriteList.value = NowPlayingViewModelStatus.SuccessFavoriteList(response.favoriteList)
                }
                is NowPlayingStatus.EmptyList ->{
                    _statusFavoriteList.value = NowPlayingViewModelStatus.EmptyList
                }
                is NowPlayingStatus.Error ->{
                    _statusFavoriteList.value = NowPlayingViewModelStatus.Error(response.error)
                }
            }
        }
    }
}


