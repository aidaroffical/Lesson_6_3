package com.example.lesson_6_3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.media.MediaPlayer


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private val audioUrls = listOf(
        "https://muzuy.net/uploads/music/2023/06/Jax_02_14_FREEMAN_996_BSBB.mp3", //Jax
        "https://dl2.mp3party.net/online/10881595.mp3", //Скриптонит
        "https://dl2.mp3party.net/online/10872173.mp3", //Travis Scott
        "https://dl2.mp3party.net/online/10273425.mp3", //Swedish House Mafia
        "https://dl2.mp3party.net/online/9471092.mp3", //Timati
        "https://dl2.mp3party.net/online/9127152.mp3", //Phantogram
        "https://dl2.mp3party.net/online/10619466.mp3", //Freeman996
        "https://dl2.mp3party.net/online/8567654.mp3", //Weeknd
        "https://muzem.net/uploads/music/2023/06/Jax_Nel_02_14_Koka_lova.mp3", //Jax
        "https://dl.muzish.net/files/track/2021/04/Kot_Balu_Ulukmanapo_K_Chante_Sredi_soten_dorog.mp3", //Ulukmanapo
        "https://cdn7.sefon.pro/prev/cbrUS7sV9SeSRO-bmqgvhw/1693104874/487/Ulukmanapo%20feat.%20%D0%91%D0%B5%D0%B3%D0%B8%D1%88%20-%20%D0%9B%D1%8E%D0%B1%D0%BE%D0%B2%D1%8C%20%D0%9A%D0%B0%D0%BA%20%D0%A1%D0%BE%D0%BD%20%28192kbps%29.mp3", //Ulukmanapo

    )
    private var currentAudioIndex = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer()

        val btnPlayPause = findViewById<Button>(R.id.btnPlay)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnPrev = findViewById<Button>(R.id.btnPrev)

        btnPlayPause.setOnClickListener {
            togglePlay()
        }

        btnStop.setOnClickListener {
            stopPlayback()
        }

        btnNext.setOnClickListener {
            playNext()
        }

        btnPrev.setOnClickListener {
            playPrevious()
        }
    }

    private fun togglePlay() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    private fun stopPlayback() {
        mediaPlayer.stop()
    }

    private fun playNext() {
        currentAudioIndex = (currentAudioIndex + 1) % audioUrls.size
        playCurrentAudio()
    }

    private fun playPrevious() {
        currentAudioIndex = if (currentAudioIndex > 0) currentAudioIndex - 1 else audioUrls.size - 1
        playCurrentAudio()
    }

    private fun playCurrentAudio() {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(audioUrls[currentAudioIndex])
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
