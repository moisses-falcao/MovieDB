package com.example.citmoviedatabase_mf.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel(private val repository: Repository): ViewModel() {

    val results = MutableLiveData<Results>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMoviesNowPlaying(){
        val response = repository.getAllMoviesPlayingNow()
        response.enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                results.postValue(response.body())
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}