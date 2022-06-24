package com.example.citmoviedatabase_mf.ui.upcoming

import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.ui.activities.main.MainActivity
import org.junit.Test

class ComingSoonFragmentTest{

    private lateinit var scenario: FragmentScenario<NavHostFragment>

    @Test
    fun testingFilm(){

        scenario = launchFragment(themeResId = R.style.Theme_CITMovieDatabase_MF)

        scenario.onFragment{
            val intent = Intent(it.context, MainActivity::class.java)
            it.startActivity(intent)
        }
    }
}