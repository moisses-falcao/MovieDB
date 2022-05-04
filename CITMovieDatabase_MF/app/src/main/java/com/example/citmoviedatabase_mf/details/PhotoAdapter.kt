package com.example.citmoviedatabase_mf.details

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.databinding.PhotoModelBinding
import com.example.citmoviedatabase_mf.details.DetailsActivity
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.PhotoModel
import kotlinx.coroutines.NonDisposableHandle.parent

class PhotoAdapter(private val photos: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder (val binding: PhotoModelBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(photos[position]){
                binding.ivScenePhoto.setImageResource(scenePhoto)
            }
        }
    }

    override fun getItemCount(): Int = photos.size
}