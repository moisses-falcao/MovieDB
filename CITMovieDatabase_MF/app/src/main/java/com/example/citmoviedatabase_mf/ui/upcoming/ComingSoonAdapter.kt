package com.example.citmoviedatabase_mf.ui.upcoming

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.ui.details.DetailsActivity
import com.ciandt.service.models.MovieModel

class ComingSoonAdapter(var movies: List<MovieModel>) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    private var holdToFavoriteMovie : ((MovieModel) -> Unit)? = null

    fun holdToFavorite(listener: (MovieModel) -> Unit){
        holdToFavoriteMovie = listener
    }

    class ViewHolder (val binding: MovieModelBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(movies[position]){
                Glide.with(binding.ivMoviePoster).load("https://image.tmdb.org/t/p/w500" + posterPath).into(binding.ivMoviePoster)
                binding.tvMovieTitle.text = title

                binding.tvMovieGenreAndDate.text = " • " + releaseDate//Concatenar genero
                binding.tvMovieVoteAverage.text = "|" + voteAverage.toString()

                binding.ivMoviePoster.setOnClickListener {
                    val intent = Intent(it.context, DetailsActivity::class.java)
                    intent.putExtra(DetailsActivity.MOVIE_ID, id)
                    it.context.startActivity(intent)
                }

                binding.ivMoviePoster.setOnLongClickListener {
                    holdToFavoriteMovie?.let {
                        it(movies[position])
                        return@setOnLongClickListener true
                    } == true
                }
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}