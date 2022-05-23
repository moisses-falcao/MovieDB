package com.example.citmoviedatabase_mf.details.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityPhotosDialogBinding
import com.example.citmoviedatabase_mf.models.SceneModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepositoryImpl
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PhotosActivity : AppCompatActivity() {

    private var movieId:Int = 0
    private lateinit var binding: ActivityPhotosDialogBinding
    private lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val photosViewModelFactory = PhotosViewModelFactory(PhotosRepositoryImpl())
        viewModel = ViewModelProvider(this, photosViewModelFactory).get(PhotosViewModel::class.java)

       setupRecyclerView()
    }

    private fun setupRecyclerView() {
        movieId = intent.getIntExtra(MOVIE_ID_PHOTO_DIALOG, 0)

        viewModel.getMovieScenes(movieId.toString()).observe(this, Observer {
            when(it){
                is PhotosStatus.Success -> {
                    binding.rvPhotos.adapter = PhotoAdapter(it.scenes.scenarios)
                }
                is PhotosStatus.NotFound -> {Toast.makeText(this, "Não foi possível carregar as cenas deste filme", Toast.LENGTH_LONG).show()}
                is PhotosStatus.Error -> {Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()}
            }
        })
    }

    companion object{
        const val MOVIE_ID_PHOTO_DIALOG = "task_id"
    }
}