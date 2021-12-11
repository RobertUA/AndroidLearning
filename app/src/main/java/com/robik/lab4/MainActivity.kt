package com.robik.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import android.media.ThumbnailUtils

import android.graphics.Bitmap

import android.graphics.BitmapFactory
import android.graphics.PorterDuff

import android.media.MediaMetadataRetriever
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.scale
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams


class MainActivity : AppCompatActivity() {

    lateinit var videoPlayer: VideoView

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if(uri != null)
        {
            videoPlayer.setVideoURI(uri)
            videoPlayer.pause()

            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(this, uri)
            val data = mmr.embeddedPicture
            if (data != null)
            {
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                videoPlayer.background = bitmap.toDrawable(resources)
            }
            else
            {
                videoPlayer.background = null
                videoPlayer.seekTo(1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaController = MediaController(this)
        val btnOpen = findViewById<Button>(R.id.button_open)
        val btnUrl = findViewById<Button>(R.id.button_url)
        val urlText = findViewById<EditText>(R.id.url_text)

        videoPlayer = findViewById(R.id.videoPlayer)
        mediaController.setAnchorView(videoPlayer)
        videoPlayer.setMediaController(mediaController)

        btnOpen.setOnClickListener {
            getContent.launch("*/*")
        }
        btnUrl.setOnClickListener{
            videoPlayer.pause()
            //videoPlayer.setVideoPath("https://dl8.webmfiles.org/big-buck-bunny_trailer.webm")
            //videoPlayer.setVideoPath("https://www.ostmusic.org/sound/track/shrek/09.%20Smash%20Mouth%20-%20All%20Star.mp3")
            videoPlayer.setVideoPath(urlText.text.toString())

            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(urlText.text.toString())
            val data = mmr.embeddedPicture
            if (data != null)
            {
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                videoPlayer.background = bitmap.toDrawable(resources)
            }
            else
            {
                videoPlayer.background = null
                videoPlayer.seekTo(1)
            }

        }
    }
}