package com.example.citmoviedatabase_mf.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.activities.MainActivity
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.databinding.ActivityDetailsBinding
import com.example.citmoviedatabase_mf.details.Casting.CastingActivity
import com.example.citmoviedatabase_mf.details.photos.PhotosActivity
import com.example.citmoviedatabase_mf.repository.details.DetailsRepositoryImpl
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    var movieId: Int = 0
    val movieDatabaseService: MovieDatabaseService by inject()
    val viewModel: DetailsViewModel by viewModel()

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
            val intent = Intent(this, CastingActivity::class.java)
            intent.putExtra(CastingActivity.MOVIE_ID_CAST_DIALOG, movieId)
            startActivity(intent)
        }
        binding.tvViewAllPhotos.setOnClickListener {
            val intent = Intent(this, PhotosActivity::class.java)
            intent.putExtra(PhotosActivity.MOVIE_ID_PHOTO_DIALOG, movieId)
            startActivity(intent)
        }
    }

    private fun populatingDetails() {
//        val movieDatabaseService = MovieDatabaseService.movieDatabaseService
//        val call = movieDatabaseService.getMovieDetails(movieId.toString())

//        call.enqueue(object : Callback, retrofit2.Callback<MovieDetailsModel>{
//            override fun onResponse(
//                call: Call<MovieDetailsModel>,
//                response: Response<MovieDetailsModel>
//            ) {
//                response.body()?.let {
//                    Glide.with(binding.ivBackdrop).load("https://image.tmdb.org/t/p/w500" + it.backdropPath).into(binding.ivBackdrop)
//                    binding.tvMovieOriginalTitle.text = it.originalTitle
//                    binding.tvDuration.text = it.runtime?.let { it1 -> convertToHours(it1) }
//                    binding.tvPopularuty.text = it.voteAverage.toString()
//                    binding.tvFullSynopsis.text = it.overview
//
//                    var lista: MutableList<String> = mutableListOf()
//                    it.genres.forEach {
//                        lista.add(it.name)
//                    }
//                    var generos: String = ""
//                    lista.forEach {
//                        generos = generos + ", " + it
//                    }
//                    generos = generos.drop(2)
//                    binding.tvGenders.text = generos
//
//                    showMoreShowLess()
//                }
//            }
//            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
//                throw (IllegalAccessException(t.message))
//            }
//        })

        movieId = intent.getIntExtra(MOVIE_ID, 0)

        viewModel.getMovieDetails(movieId.toString())

        viewModel.statusDetails.observe(this){
            when(it){
                is DetailsViewModelStatus.SuccessDetails ->{
                    Glide.with(binding.ivBackdrop).load("https://image.tmdb.org/t/p/w500" + it.movieDetails.backdropPath).into(binding.ivBackdrop)
                    binding.tvMovieOriginalTitle.text = it.movieDetails.originalTitle
                    binding.tvDuration.text = it.movieDetails.runtime?.let { it1 -> convertToHours(it1) }
                    binding.tvPopularuty.text = it.movieDetails.voteAverage.toString()
                    binding.tvFullSynopsis.text = it.movieDetails.overview

                    var lista: MutableList<String> = mutableListOf()
                    it.movieDetails.genres.forEach {
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
//                is DetailsViewModelStatus.NotFound ->{
//                    Toast.makeText(this, "Não foi possível carregar os detalhes deste filme", Toast.LENGTH_LONG).show()
//                }
                is DetailsViewModelStatus.Error ->{
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
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
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()

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

        viewModel.getMovieCredits(movieId.toString())

        viewModel.statusCredits.observe(this){
            when(it){
                is DetailsViewModelStatus.SuccessCredits ->{
                    binding.rvCastAndCrew.adapter = CastAndCrewAdapter(it.casting.cast)
                }
                is DetailsViewModelStatus.NotFound ->{ Toast.makeText(this, "Não foi possível carregar o elenco.", Toast.LENGTH_LONG).show() }
                is DetailsViewModelStatus.Error ->{ Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show() }
                else -> {}
            }
        }
    }

    fun populatingPhotos(){

        movieId = intent.getIntExtra(MOVIE_ID, 0)

        viewModel.getMovieScenes(movieId.toString())

        viewModel.statusScenes.observe(this){
            when(it){
                is DetailsViewModelStatus.SuccessScenes ->{
                    binding.rvPhotos.adapter = PhotoAdapter(it.scenes.scenarios)
                }
                is DetailsViewModelStatus.NotFound ->{ Toast.makeText(this, "Não foi possível carregar as cenas do filme.", Toast.LENGTH_LONG).show() }
                is DetailsViewModelStatus.Error ->{ Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show() }
                else -> {}
            }
        }
    }

    companion object{
        const val MOVIE_ID = "task_id"
    }
}