package com.example.mosaic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class LevelActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private val songs = listOf(R.raw.one, R.raw.two, R.raw.three)
    private var currentSongIndex = java.util.Random().nextInt(songs.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cols = intent.getIntExtra("cols", 4)
        val rows = intent.getIntExtra("rows", 6)
        val viewModel = MainViewModel(cols, rows)
        val originalId = intent.getIntExtra("original", 0)
        val templateId = intent.getIntExtra("template", 0)
        val (original, template) = Tiler.createBitmaps(
            cols, rows, resources, originalId, templateId
        )
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            DraggableScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.8f)),
                cols,
                rows
            ) {
                val onReturn: () -> Unit = {
                    val navigate = Intent (
                        this@LevelActivity,
                        ImageActivity::class.java
                    )
                    startActivity(navigate)
                }
                MainScreen(viewModel, original, template, onReturn, cols, rows)
            }
        }
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])
        mediaPlayer?.setOnCompletionListener {
            playNextSong()
        }
        mediaPlayer?.isLooping = false
        mediaPlayer?.start()
    }

    private fun playNextSong() {
        currentSongIndex = (currentSongIndex + 1) % songs.size
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])
        mediaPlayer?.setOnCompletionListener {
            playNextSong()
        }
        mediaPlayer?.isLooping = false
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        super.onDestroy()
    }
}