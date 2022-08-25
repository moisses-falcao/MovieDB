package com.example.citmoviedatabase_mf.ui.details.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ciandt.service.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityPhotosDialogBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosActivity : AppCompatActivity() {

    private var movieId:Int = 0
    private lateinit var binding: ActivityPhotosDialogBinding
    val movieDatabaseService: MovieDatabaseService by inject()
    private val viewModel: PhotosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

       setupRecyclerView()
    }

    private fun setupRecyclerView() {

        movieId = intent.getIntExtra(MOVIE_ID_PHOTO_DIALOG, 0)

        viewModel.getMovieScenes(movieId.toString())

        viewModel.status.observe(this){
            when(it){
                is PhotosViewModelStatus.Success -> {
                    binding.rvPhotos.adapter = PhotoAdapter(it.scenes.scenarios)
                }
                is PhotosViewModelStatus.NotFound -> {Toast.makeText(this, "Não foi possível carregar as cenas deste filme", Toast.LENGTH_LONG).show()}
                is PhotosViewModelStatus.Error -> {Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()}
            }
        }
    }

    companion object{
        const val MOVIE_ID_PHOTO_DIALOG = "task_id"
    }
}