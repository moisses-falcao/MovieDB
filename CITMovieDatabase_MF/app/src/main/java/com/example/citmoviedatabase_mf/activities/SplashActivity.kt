package com.example.citmoviedatabase_mf.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.citmoviedatabase_mf.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        supportActionBar!!.hide()

        setupSplash()
    }

    private fun setupSplash() = with(binding){

        ivLogoCIandT.alpha = 0f
        ivLogoCIandT.animate().setDuration(1200).alpha(1f)

        tvMovieCIandT.alpha = 0f
        tvMovieCIandT.animate().setDuration(1200).alpha(1f).withEndAction {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}