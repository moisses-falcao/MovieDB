package com.example.citmoviedatabase_mf.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.models.MovieModel

class NowPlayingAdapter(private val movies: List<MovieModel>) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    class ViewHolder (val binding: MovieModelBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(movies[position]){
                binding.ivMoviePoster.setImageResource(posterPath)
                binding.tvMovieTitle.text = title
                binding.tvMovieGenreAndDate.text = genres + " • " + releaseDate + " "
                binding.tvMovieVoteAverage.text = "|" + voteAverage.toString()
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}