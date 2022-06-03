package com.example.citmoviedatabase_mf.upcoming

import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.Espresso.onView
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.activities.MainActivity
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

        onView(withId(R.id.))
    }
}