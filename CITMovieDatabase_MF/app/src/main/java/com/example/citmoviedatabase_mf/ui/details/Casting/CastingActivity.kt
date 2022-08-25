package com.example.citmoviedatabase_mf.ui.details.Casting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ciandt.service.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityCastAndCrewDialogBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastingActivity : AppCompatActivity() {

    private var movieId:Int = 0
    private lateinit var binding: ActivityCastAndCrewDialogBinding
    val movieDatabaseService: MovieDatabaseService by inject()
    val viewModel: CastingViewModel by viewModel()

    @SuppressLint("ResourceAsColor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastAndCrewDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        movieId = intent.getIntExtra(CastingActivity.MOVIE_ID_CAST_DIALOG, 0)

        viewModel.getMovieCredits(movieId.toString())

        viewModel.status.observe(this){
            when(it){
                is CastingViewModelStatus.Success -> {
                    binding.rvCastAndCrewDialog.adapter = CastingAdapter(it.casting.cast)
                }
                is CastingViewModelStatus.NotFound -> {Toast.makeText(this, "Não foi possível carregar o elenco deste filme.", Toast.LENGTH_LONG).show()}
                is CastingViewModelStatus.Error -> {Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()}
            }
        }
    }

    companion object{
        const val MOVIE_ID_CAST_DIALOG = "task_id"
    }
}