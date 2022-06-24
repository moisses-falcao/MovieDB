package com.example.citmoviedatabase_mf.ui.composeactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.citmoviedatabase_mf.ui.activities.SplashActivity
import com.example.citmoviedatabase_mf.ui.composeactivity.ui.theme.CITMovieDatabase_MFTheme
import com.example.citmoviedatabase_mf.ui.composeactivity.ui.theme.RedCIandT
import java.util.*
import kotlin.concurrent.timerTask

class LoadingComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CITMovieDatabase_MFTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(RedCIandT)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LoadingAnimation()
                    }
                }
            }
        }

        startSplashActivity()
    }

    private fun startSplashActivity() {

        val intent = Intent(this, SplashActivity::class.java)

        val task = timerTask {
            startActivity(intent)
            finish()
        }

        Timer().schedule(task, 1800)
    }
}


@Preview
@Composable
fun DefaultPreview() {
    CITMovieDatabase_MFTheme {
        LoadingAnimation()
    }
}