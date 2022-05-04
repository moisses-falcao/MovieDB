package com.example.citmoviedatabase_mf.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.citmoviedatabase_mf.PagerAdapter
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.databinding.ActivityMainBinding
import com.example.citmoviedatabase_mf.nowplaying.NowPlayingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainTabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val pagerAdapter: PagerAdapter by lazy { PagerAdapter(this) }

        mainTabLayout = binding.tlMainTabLayout
        viewPager = binding.vp2MainViewPager
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(mainTabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> {"Now Playing"}
                1 -> {"Coming Soon "}
                else -> {throw Resources.NotFoundException("Position not found")}
            }
        }.attach()
    }
}