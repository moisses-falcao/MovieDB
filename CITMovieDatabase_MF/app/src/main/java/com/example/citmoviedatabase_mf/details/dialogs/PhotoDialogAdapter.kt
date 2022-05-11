package com.example.citmoviedatabase_mf.details.dialogs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.databinding.PhotoModelBinding
import com.example.citmoviedatabase_mf.databinding.PhotoModelToDialogBinding
import com.example.citmoviedatabase_mf.details.DetailsActivity
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.PhotoModel
import kotlinx.coroutines.NonDisposableHandle.parent

class PhotoDialogAdapter(private val photos: List<PhotoModel>) : RecyclerView.Adapter<PhotoDialogAdapter.ViewHolder>() {

    class ViewHolder (val binding: PhotoModelToDialogBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoModelToDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(photos[position]){
                Glide.with(binding.ivScenePhotoDialog).load("https://image.tmdb.org/t/p/w342" + scenePath).into(binding.ivScenePhotoDialog)
            }
        }
    }

    override fun getItemCount(): Int = photos.size
}