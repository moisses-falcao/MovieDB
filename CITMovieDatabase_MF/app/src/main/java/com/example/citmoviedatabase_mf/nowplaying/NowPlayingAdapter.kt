package com.example.citmoviedatabase_mf.nowplaying

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.details.DetailsActivity
import com.example.citmoviedatabase_mf.models.GenreModel
import com.example.citmoviedatabase_mf.models.MovieModel
import kotlinx.coroutines.NonDisposableHandle.parent
import java.lang.System.load

class NowPlayingAdapter(var movies: List<MovieModel>) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    class ViewHolder (val binding: MovieModelBinding) : RecyclerView.ViewHolder(binding.root){}

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
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}