package com.example.citmoviedatabase_mf.repository.comingsoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComingSoonRepositoryImpl(private val movieDatabaseService: MovieDatabaseService) : ComingSoonRepository {
    
    override fun getAllMoviesUpcoming(): LiveData<ComingSoonStatus> {
        
        val status = MutableLiveData<ComingSoonStatus>()
        
        movieDatabaseService.getAllMoviesUpcoming().enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { ComingSoonStatus.Success(it)}
                }else{
                    status.value = ComingSoonStatus.NotFound
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                status.value = ComingSoonStatus.Error(t)
            }

        })
        return status
    }
}