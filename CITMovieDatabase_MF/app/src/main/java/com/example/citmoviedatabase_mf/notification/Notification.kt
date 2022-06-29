package com.example.citmoviedatabase_mf.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.constants.Constants.CHANNEL_ID
import com.example.citmoviedatabase_mf.constants.Constants.MESSAGE_EXTRA
import com.example.citmoviedatabase_mf.constants.Constants.NOTIFICATION_ID
import com.example.citmoviedatabase_mf.constants.Constants.TITLE_EXTRA

class Notification : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_ciandt_movie_database)
            .setContentTitle(intent?.getStringExtra(TITLE_EXTRA))
            .setContentText(intent?.getStringExtra(MESSAGE_EXTRA))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}