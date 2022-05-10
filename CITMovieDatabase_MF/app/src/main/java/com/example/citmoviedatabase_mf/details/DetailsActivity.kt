package com.example.citmoviedatabase_mf.details

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.activities.MainActivity
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityDetailsBinding
import com.example.citmoviedatabase_mf.details.dialogs.CastAndCrewDialogActivity
import com.example.citmoviedatabase_mf.models.CastAndCrewModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.PhotoModel
import com.example.citmoviedatabase_mf.repository.Repository
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

        populatingCastAndCrew()

        populatingPhotos()

        populatingDetails()
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
                }
            }
            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {

            }
        })
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
        val castAndCrewList = listOf<CastAndCrewModel>(
            CastAndCrewModel(0, R.drawable.cast_and_crew_keanu_reeves, "Keanu Reeves", "JOHN WICK"),
            CastAndCrewModel(1, R.drawable.cast_and_crew_halle_barry, "Halle Barry", "SOFIA"),
            CastAndCrewModel(2, R.drawable.cast_and_crew_laurence_fishburn, "Laurence Fishburn", "BOWERY KING"),
            CastAndCrewModel(3, R.drawable.cast_and_crew_mark_dacascos, "Mark Dacascos", "ZERO"),
            CastAndCrewModel(4, R.drawable.cast_and_crew_asia_kate_dillon, "Asia Kate Dillon", "ADJUDICATOR"),
            CastAndCrewModel(5, R.drawable.cast_and_crew_lance_reddick, "Lance Reddick", "CHARON"),
            CastAndCrewModel(6, R.drawable.cast_and_crew_angelica_huston, "Angelica Huston", "DIRECTOR"),
            CastAndCrewModel(7, R.drawable.cast_and_crew_margaret_daly, "Margaret Daly", "OPERATOR"),
            CastAndCrewModel(7, R.drawable.cast_and_crew_jerome_flynn, "Jerome Flynn", "BERRADA")
        )

        val castAndCrewAdapter by lazy { CastAndCrewAdapter(castAndCrewList) }

        binding.rvCastAndCrew.adapter = castAndCrewAdapter
    }

    fun populatingPhotos(){
        val photoList = listOf<PhotoModel>(
            PhotoModel(0, R.drawable.scene1_john_wick),
            PhotoModel(1, R.drawable.scene2_john_wick),
            PhotoModel(2, R.drawable.scene3_john_wick),
        )

        val photoAdapter by lazy { PhotoAdapter(photoList) }

        binding.rvPhotos.adapter = photoAdapter
    }

    companion object{
        const val MOVIE_ID = "task_id"
    }
}