package com.example.citmoviedatabase_mf.details.dialogs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citmoviedatabase_mf.databinding.CastAndCrewModelBinding
import com.example.citmoviedatabase_mf.databinding.MovieModelBinding
import com.example.citmoviedatabase_mf.details.DetailsActivity
import com.example.citmoviedatabase_mf.models.CastAndCrewModel
import com.example.citmoviedatabase_mf.models.MovieModel
import kotlinx.coroutines.NonDisposableHandle.parent

class CastAndCrewDialogAdapter(private val castAndCrewList: List<CastAndCrewModel>) : RecyclerView.Adapter<CastAndCrewDialogAdapter.ViewHolder>() {

    class ViewHolder (val binding: CastAndCrewModelBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CastAndCrewModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(castAndCrewList[position]){
                binding.ivActorPicture.setImageResource(actorPicture)
                binding.tvActorName.text = actorName
                binding.tvActorChar.text = "•••      " + actorChar
            }
        }
    }

    override fun getItemCount(): Int = castAndCrewList.size
}