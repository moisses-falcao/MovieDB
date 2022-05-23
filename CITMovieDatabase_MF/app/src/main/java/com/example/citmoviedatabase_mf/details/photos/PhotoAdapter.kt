package com.example.citmoviedatabase_mf.details.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.databinding.PhotoModelToDialogBinding
import com.example.citmoviedatabase_mf.models.PhotoModel

class PhotoAdapter(private val photos: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

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