package com.example.citmoviedatabase_mf.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.citmoviedatabase_mf.models.GenreModel
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

    }
}