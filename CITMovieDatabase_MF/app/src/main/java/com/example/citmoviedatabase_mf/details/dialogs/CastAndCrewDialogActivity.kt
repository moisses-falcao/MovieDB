package com.example.citmoviedatabase_mf.details.dialogs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityCastAndCrewDialogBinding
import com.example.citmoviedatabase_mf.databinding.CastAndCrewModelBinding
import com.example.citmoviedatabase_mf.details.CastAndCrewAdapter
import com.example.citmoviedatabase_mf.details.DetailsActivity
import com.example.citmoviedatabase_mf.models.CastAndCrewModel
import com.example.citmoviedatabase_mf.models.CastModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CastAndCrewDialogActivity : AppCompatActivity() {

    private var movieId:Int = 0
    private lateinit var binding: ActivityCastAndCrewDialogBinding

    @SuppressLint("ResourceAsColor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastAndCrewDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        movieId = intent.getIntExtra(CastAndCrewDialogActivity.MOVIE_ID_CAST_DIALOG, 0)
        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
        val call = movieDatabaseService.getMovieCredits(movieId.toString())

        call.enqueue(object : Callback, retrofit2.Callback<CastModel> {
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                val castAndCrewDialogAdapter = response.body()?.cast?.let {
                    CastAndCrewDialogAdapter(it)
                }
                binding.rvCastAndCrewDialog.adapter = castAndCrewDialogAdapter
            }
            override fun onFailure(call: Call<CastModel>, t: Throwable) {
            }
        })
    }

    companion object{
        const val MOVIE_ID_CAST_DIALOG = "task_id"
    }
}