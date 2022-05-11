package com.example.citmoviedatabase_mf.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.activities.MainActivity
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityDetailsBinding
import com.example.citmoviedatabase_mf.details.dialogs.CastAndCrewDialogActivity
import com.example.citmoviedatabase_mf.details.dialogs.PhotosDialogActivity
import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.PhotoModel
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailsActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsActivityViewModel
    var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideBars()

        backNavigationDetailsIconFunction()

        populatingPhotos()

        populatingDetails()

        populatingCastAndCrew()

        viewAll()
    }

    private fun viewAll() {
        binding.tvViewAllCastAndCrew.setOnClickListener {
            val intent = Intent(this, CastAndCrewDialogActivity::class.java)
            intent.putExtra(CastAndCrewDialogActivity.MOVIE_ID_CAST_DIALOG, movieId)
            startActivity(intent)
        }
        binding.tvViewAllPhotos.setOnClickListener {
            val intent = Intent(this, PhotosDialogActivity::class.java)
            startActivity(intent)
        }
    }

    private fun populatingDetails() {
        movieId = intent.getIntExtra(MOVIE_ID, 0)
        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
        val call = movieDatabaseService.getMovieDetails(movieId.toString())

        call.enqueue(object : Callback, retrofit2.Callback<MovieDetailsModel>{
            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                response.body()?.let {
                    Glide.with(binding.ivBackdrop).load("https://image.tmdb.org/t/p/w500" + it.backdropPath).into(binding.ivBackdrop)
                    binding.tvMovieOriginalTitle.text = it.originalTitle
                    binding.tvDuration.text = it.runtime?.let { it1 -> convertToHours(it1) }
                    binding.tvPopularuty.text = it.voteAverage.toString()
                    binding.tvFullSynopsis.text = it.overview

                    var lista: MutableList<String> = mutableListOf()
                    it.genres.forEach {
                        lista.add(it.name)
                    }
                    var generos: String = ""
                    lista.forEach {
                        generos = generos + ", " + it
                    }
                    generos = generos.drop(2)
                    binding.tvGenders.text = generos

                    showMoreShowLess()
                }
            }
            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                throw (IllegalAccessException(t.message))
            }
        })
    }

    private fun showMoreShowLess() {
        if(binding.tvFullSynopsis.lineCount <= 4){
            binding.tvShowMore.visibility = GONE
        }
        binding.tvShowMore.setOnClickListener {
            if(binding.tvShowMore.text == "Show More"){
                binding.tvFullSynopsis.maxLines = 1000
                binding.tvShowMore.text = "Show Less"
            } else{
                binding.tvFullSynopsis.maxLines = 4
                binding.tvShowMore.text = "Show More"
            }
        }
    }

    private fun convertToHours(runtime: Int): String {
        var minutes = runtime
        var hours = 0
        while(minutes >= 60){
            hours = hours+ 1
            minutes = minutes - 60
        }
        return "${hours}h ${minutes}m | R"
    }

    private fun hideBars() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        supportActionBar!!.hide()
    }

    private fun backNavigationDetailsIconFunction() {
        binding.ivBackNavigationDetailsIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun populatingCastAndCrew(){
        movieId = intent.getIntExtra(MOVIE_ID, 0)
        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
        val call = movieDatabaseService.getMovieCredits(movieId.toString())

        call.enqueue(object : Callback, retrofit2.Callback<CastModel> {
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                val castAndCrewAdapter = response.body()?.cast?.let {
                    CastAndCrewAdapter(it)
                }
                binding.rvCastAndCrew.adapter = castAndCrewAdapter
            }
            override fun onFailure(call: Call<CastModel>, t: Throwable) {
            }
        })
    }

    fun populatingPhotos(){
        movieId = intent.getIntExtra(MOVIE_ID, 0)
        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
        val call = movieDatabaseService.getMovieScenes(movieId.toString())

        call.enqueue(object: Callback, retrofit2.Callback<SceneModel>{
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                val photoAdapter = response.body()?.scenarios?.let {
                    PhotoAdapter(it)
                }
                binding.rvPhotos.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvPhotos.adapter = photoAdapter
            }
            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
            }
        })
    }

    companion object{
        const val MOVIE_ID = "task_id"
    }
}