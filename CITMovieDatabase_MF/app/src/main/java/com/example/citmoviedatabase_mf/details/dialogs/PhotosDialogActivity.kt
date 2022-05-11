package com.example.citmoviedatabase_mf.details.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityPhotosDialogBinding
import com.example.citmoviedatabase_mf.details.PhotoAdapter
import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PhotosDialogActivity : AppCompatActivity() {

    private var movieId:Int = 0
    private lateinit var binding: ActivityPhotosDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        movieId = intent.getIntExtra(MOVIE_ID_PHOTO_DIALOG, 0)
        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
        val call = movieDatabaseService.getMovieScenes(movieId.toString())

        call.enqueue(object : Callback, retrofit2.Callback<SceneModel> {
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                val photoDialogAdapter = response.body()?.scenarios?.let {
                    PhotoDialogAdapter(it)
                }
                binding.rvPhotos.adapter = photoDialogAdapter
            }
            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
            }
        })
    }

    companion object{
        const val MOVIE_ID_PHOTO_DIALOG = "task_id"
    }
}