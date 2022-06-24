package com.example.citmoviedatabase_mf.ui.nowplaying

import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.fragment.NavHostFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.ui.activities.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NowPlayingFragmentTest{

    private lateinit var scenario: FragmentScenario<NavHostFragment>

    @Test
    fun testingFilm(){

        scenario = launchFragmentInContainer(themeResId = R.style.Theme_CITMovieDatabase_MF)

        scenario.onFragment{
            val intent = Intent(it.context, MainActivity::class.java)
            it.startActivity(intent)
        }
    }
}