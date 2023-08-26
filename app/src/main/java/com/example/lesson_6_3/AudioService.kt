package com.example.lesson_6_3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.media.MediaPlayer

class AudioService : Service() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val audioUrl = intent?.getStringExtra(EXTRA_AUDIO_URL)
        audioUrl?.let {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(it)
            mediaPlayer.prepare()
            mediaPlayer.start()
            showNotification()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun showNotification() {
        val channelId = "AudioChannel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Now Playing")
            .setContentText("Audio is playing")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Audio Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        const val EXTRA_AUDIO_URL = "extra_audio_url"
        const val NOTIFICATION_ID = 1
    }
}