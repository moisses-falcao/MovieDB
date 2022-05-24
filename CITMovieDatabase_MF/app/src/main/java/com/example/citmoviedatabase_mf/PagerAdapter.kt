package com.example.citmoviedatabase_mf

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.nowplaying.NowPlayingFragment
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepositoryImpl
import com.example.citmoviedatabase_mf.upcoming.ComingSoonFragment
import com.example.citmoviedatabase_mf.upcoming.ComingSoonViewModel

class PagerAdapter(private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {


        return when (position) {
            0 -> {
                NowPlayingFragment()
            }
            1 -> {
                ComingSoonFragment()
            }
            else -> {
                throw Resources.NotFoundException("Position not found!")
            }
        }
    }
}