package com.example.citmoviedatabase_mf.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.Repository
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ComingSoonViewModel(val repository: Repository): ViewModel() {

    val results = MutableLiveData<Results>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMoviesUpcoming(){
        val response = repository.getAllMoviesUpcoming()
        response.enqueue(object: Callback<Results>{
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                results.postValue(response.body())
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}