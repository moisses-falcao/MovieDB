package com.example.citmoviedatabase_mf.ui.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.repository.main.MainRepository
import com.example.citmoviedatabase_mf.repository.main.MainStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _status = MutableStateFlow<MainViewModelStatus>(MainViewModelStatus.EmptyList)
    val status: MutableStateFlow<MainViewModelStatus>
        get() = _status

    init {
        getFavoriteList()
    }

    fun getFavoriteList() {
        viewModelScope.launch {

            when (val response = mainRepository.getFavoriteList()) {
                is MainStatus.Success -> {
                    _status.value = MainViewModelStatus.Success(response.favoriteList)
                }
                is MainStatus.EmptyList ->{
                    _status.value = MainViewModelStatus.EmptyList
                }
                is MainStatus.Error ->{
                    _status.value = MainViewModelStatus.Error(response.error)
                }
            }
        }
    }
}