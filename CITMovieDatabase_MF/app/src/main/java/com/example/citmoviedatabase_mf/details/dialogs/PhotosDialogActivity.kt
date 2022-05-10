package com.example.citmoviedatabase_mf.details.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.databinding.ActivityPhotosDialogBinding

class PhotosDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}