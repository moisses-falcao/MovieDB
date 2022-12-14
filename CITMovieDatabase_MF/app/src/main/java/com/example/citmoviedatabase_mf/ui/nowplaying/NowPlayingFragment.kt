package com.example.citmoviedatabase_mf.ui.nowplaying

import android.app.*
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.constants.Constants.CHANNEL_ID
import com.example.citmoviedatabase_mf.constants.Constants.HOUR_TO_SHOW_PUSH
import com.example.citmoviedatabase_mf.constants.Constants.NOTIFICATION_ID
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import com.ciandt.service.models.MovieModel
import com.example.citmoviedatabase_mf.notification.Notification
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.text.format.DateFormat
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.coroutines.coroutineContext

class NowPlayingFragment() : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModel()
    private lateinit var adapter: NowPlayingAdapter
    //private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNowPlayingBinding = FragmentNowPlayingBinding.inflate(inflater, container, false)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val call = movieDatabaseService.getAllMoviesNowPlaying()
//        call.enqueue(object: Callback, retrofit2.Callback<Results>{
//            override fun onResponse(call: Call<Results>, response: Response<Results>) {
//                val nowPlayingAdapter = response.body()?.results?.let{
//                    NowPlayingAdapter(it)
//                }
//                binding.rvNowPlaying.adapter = nowPlayingAdapter
//            }
//            override fun onFailure(call: Call<Results>, t: Throwable) {
//
//            }
//        })

        setupRecyclerView()

        createNotificationChannel()
        scheduleNotification()

        //firebaseAnalytics = Firebase.analytics
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {

        val intent = Intent(context, Notification::class.java)
        val title = "T??tulo gen??rico"
        val message = "Mensagem gen??rica"
        intent.putExtra(title, title)
        intent.putExtra(message, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context, NOTIFICATION_ID, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager

        val calendar = GregorianCalendar.getInstance().apply {
            if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
            set(Calendar.HOUR_OF_DAY, 14)
            set(Calendar.MINUTE, 15)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {

        val name = "Notif Channel"
        val description = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description
        val notificationManager =
            activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun favoriteMovie(movieModel: MovieModel) {

        viewModel.favoriteMovie(movieModel)

        Toast.makeText(
            context,
            movieModel.title + " " + "adicionado ?? lista de favoritos com sucesso!",
            Toast.LENGTH_SHORT
        ).show()


//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM){
//            param(FirebaseAnalytics.Param.ITEM_ID, movieModel.id.toString())
//            param(FirebaseAnalytics.Param.ITEM_NAME, movieModel.title)
//            param(FirebaseAnalytics.Param.CONTENT_TYPE, "movie")
//        }
    }

    private fun disfavorMovie(movieModel: MovieModel) {

        viewModel.disfavorMovie(movieModel)

        Toast.makeText(
            context,
            movieModel.title + " " + "removido da lista de favoritos!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupRecyclerView() {

        viewModel.getAllMoviesNowPlaying()

        viewModel.status.observe(viewLifecycleOwner) {
            viewModel.getFavoriteList()

            when (it) {
                is NowPlayingViewModelStatus.Success -> {
                    adapter = NowPlayingAdapter(it.listNowPlaying.results)
                    binding.rvNowPlaying.adapter = adapter


                    lifecycleScope.launch {
                        viewModel.statusFavoriteList.collect { status ->

                            when (status) {
                                is NowPlayingViewModelStatus.SuccessFavoriteList -> {

                                    adapter.holdToFavorite { movieModel ->
                                        if (status.favoriteList.contains(movieModel)) {
                                            disfavorMovie(movieModel)
                                            viewModel.statusFavoriteList
                                        } else {
                                            favoriteMovie(movieModel)
                                        }
                                    }
                                }
                                is NowPlayingViewModelStatus.Error -> {
                                    Log.e("ERRO", status.error.toString())
                                    Toast.makeText(
                                        context,
                                        status.error.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                else -> {

                                }
                            }
                        }
                    }
                }
                is NowPlayingViewModelStatus.NotFound -> {
                    Toast.makeText(
                        context,
                        "N??o foi poss??vel carregar a lista de filmes",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NowPlayingViewModelStatus.Error -> {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
//        viewModel.getAllMoviesNowPlaying().observe(viewLifecycleOwner, Observer {
//            when(it){
//                is NowPlayingStatus.Success -> {
//                    binding.rvNowPlaying.adapter = NowPlayingAdapter(it.listNowPlaying.results)
//                }
//                is NowPlayingStatus.NotFound -> {
//                    Toast.makeText(context, "N??o foi poss??vel carregar a lista de filmes", Toast.LENGTH_LONG).show()
//                }
//                is NowPlayingStatus.Error -> {
//                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }
}
