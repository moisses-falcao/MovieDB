package com.example.citmoviedatabase_mf.details.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.databinding.ActivityCastAndCrewDialogBinding
import com.example.citmoviedatabase_mf.databinding.CastAndCrewModelBinding
import com.example.citmoviedatabase_mf.models.CastAndCrewModel

class CastAndCrewDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCastAndCrewDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastAndCrewDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}