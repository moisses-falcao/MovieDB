package com.example.citmoviedatabase_mf.ui.details.Casting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase_mf.databinding.CastAndCrewModelBinding
import com.ciandt.service.models.CastAndCrewModel

class CastingAdapter(private val castAndCrewList: List<CastAndCrewModel>) : RecyclerView.Adapter<CastingAdapter.ViewHolder>() {

    class ViewHolder (val binding: CastAndCrewModelBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CastAndCrewModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(castAndCrewList[position]){
                Glide.with(binding.ivActorPicture).load("https://image.tmdb.org/t/p/w342" + actorPicture).into(binding.ivActorPicture)
                binding.tvActorName.text = actorName

                if (character.length <= 22 ){
                    binding.tvActorChar.text = "••• " + character
                }else{
                    binding.tvActorChar.text = character
                }
            }
        }
    }

    override fun getItemCount(): Int = castAndCrewList.size
}