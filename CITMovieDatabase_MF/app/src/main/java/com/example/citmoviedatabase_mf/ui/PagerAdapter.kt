package com.example.citmoviedatabase_mf.ui

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.citmoviedatabase_mf.ui.nowplaying.NowPlayingFragment
import com.example.citmoviedatabase_mf.ui.upcoming.ComingSoonFragment

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