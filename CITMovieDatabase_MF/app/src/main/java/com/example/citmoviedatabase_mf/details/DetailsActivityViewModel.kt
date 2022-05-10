package com.example.citmoviedatabase_mf.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivityViewModel(val repository: Repository): ViewModel() {

    val movieDetails = MutableLiveData<MovieDetailsModel>()
    val errorMessage = MutableLiveData<String>()

    fun getMovieDetails(){
        val response = repository.getMovieDetails()
        response.enqueue(object : Callback<MovieDetailsModel> {
            override fun onResponse(call: Call<MovieDetailsModel>, response: Response<MovieDetailsModel>) {
                movieDetails.postValue(response.body())
            }
            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}