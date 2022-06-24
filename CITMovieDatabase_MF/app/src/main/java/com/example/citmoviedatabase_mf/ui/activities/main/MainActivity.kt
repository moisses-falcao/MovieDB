package com.example.citmoviedatabase_mf.ui.activities.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.citmoviedatabase_mf.databinding.ActivityMainBinding
import com.example.citmoviedatabase_mf.databinding.FavoriteDialogBinding
import com.example.citmoviedatabase_mf.ui.PagerAdapter
import com.example.citmoviedatabase_mf.ui.nowplaying.NowPlayingAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainTabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var dialogBinding: FavoriteDialogBinding
    lateinit var mainAdapter: MainAdapter
    private val viewModel: MainViewModel by viewModel()


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

    override fun onResume() {
        super.onResume()

        binding.ivFavorite.setOnClickListener {
            setupDialog()
        }
    }


    private fun setupDialog(){
        dialogBinding = FavoriteDialogBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog(this)

        dialog.setContentView(dialogBinding.root)
        dialog.show()

        setupRecyclerView(dialog)

        dialog.setOnCancelListener {
            dialog.hide()
        }
    }

    private fun setupRecyclerView(dialog: Dialog) = lifecycleScope.launch{
        viewModel.getFavoriteList()

        viewModel.status.collect{
            when(it){
                is MainViewModelStatus.Success ->{
                    mainAdapter = MainAdapter(it.favoriteList)
                    dialogBinding.rvFavoriteRecyclerView.adapter = mainAdapter

                    mainAdapter.notifyDataSetChanged()
                }
                is MainViewModelStatus.EmptyList ->{
                    dialog.hide()
                    Toast.makeText(this@MainActivity, "Lista de favoritos vazia, clique e segure em um filme para favorita-lo", Toast.LENGTH_LONG).show()
                }
                is MainViewModelStatus.Error ->{
                    Toast.makeText(this@MainActivity, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}