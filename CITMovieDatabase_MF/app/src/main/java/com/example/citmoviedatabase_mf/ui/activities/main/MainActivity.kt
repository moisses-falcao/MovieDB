package com.example.citmoviedatabase_mf.ui.activities.main

import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.ciandt.service.models.MovieModel
import com.example.citmoviedatabase_mf.databinding.ActivityMainBinding
import com.example.citmoviedatabase_mf.databinding.FavoriteDialogBinding
import com.example.citmoviedatabase_mf.remoteconfig.RemoteConfigUtils
import com.example.citmoviedatabase_mf.ui.PagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.delay
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
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        firebaseAnalytics = Firebase.analytics

        supportActionBar!!.hide()

        val pagerAdapter: PagerAdapter by lazy { PagerAdapter(this) }

        mainTabLayout = binding.tlMainTabLayout
        viewPager = binding.vp2MainViewPager
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(mainTabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    "Now Playing"
                }
                1 -> {
                    "Coming Soon "
                }
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        }.attach()

        setupRemoteConfig()

        val url: String
        url = "https://wa.me/5511999910621/?text=Preciso%20de%20ajuda%20com%20a%20mudança%20de%20endereço"

        binding.ivWhatsapp.setOnClickListener {
            redirectToWhatsapp(url)
        }
    }

    private fun redirectToWhatsapp(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun setupRemoteConfig() {
        binding.tvToolbar.apply {
            text = RemoteConfigUtils.getMainActivityAppName()
            setTextColor(Color.parseColor(RemoteConfigUtils.getMainActivityAppNameColor()))
        }
    }

    override fun onResume() {
        super.onResume()

        binding.ivFavorite.setOnClickListener {
            setupDialog()
        }
    }


    private fun setupDialog() {
        dialogBinding = FavoriteDialogBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog(this)

        dialog.setContentView(dialogBinding.root)
        dialog.show()

        setupRecyclerView(dialog)

        dialog.setOnCancelListener {
            dialog.hide()
        }
    }

    private fun setupRecyclerView(dialog: Dialog) = lifecycleScope.launch {
        viewModel.getFavoriteList()

        viewModel.status.collect {
            when (it) {
                is MainViewModelStatus.Success -> {

                    mainAdapter = MainAdapter(it.favoriteList)
                    dialogBinding.rvFavoriteRecyclerView.adapter = mainAdapter

                    mainAdapter.holdToFavorite { movieModel ->
                        disfavorMovie(movieModel)
                    }
                }
                is MainViewModelStatus.EmptyList -> {

                    delay(3000)

                    dialog.hide()

                    Toast.makeText(
                        applicationContext,
                        "Lista de favoritos vazia, clique e segure em um filme se deseja favorita-lo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is MainViewModelStatus.Error -> {
                    Toast.makeText(applicationContext, it.error.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    private fun disfavorMovie(movieModel: MovieModel) {
        viewModel.disfavorMovie(movieModel)

        Toast.makeText(
            this,
            movieModel.title + " " + "removido da lista de favoritos!",
            Toast.LENGTH_SHORT
        ).show()

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM){
            param(FirebaseAnalytics.Param.ITEM_ID, movieModel.id.toString())
            param(FirebaseAnalytics.Param.ITEM_NAME, movieModel.title)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "movie")
        }
    }
}