package com.example.screenmirror

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class Photos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photos)
        
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tabAllPhotos = findViewById<TextView>(R.id.tabAllPhotos)
        val tabAlbumPhotos = findViewById<TextView>(R.id.tabAlbumPhotos)
        val scrollPhotos = findViewById<View>(R.id.scrollPhotos)
        val scrollAlbums = findViewById<View>(R.id.scrollAlbums)

        tabAllPhotos.setOnClickListener {
            // Update Tab UI
            tabAllPhotos.setBackgroundResource(R.drawable.bg_segmented_toggle_active)
            tabAllPhotos.setTextColor(Color.WHITE)
            tabAllPhotos.setTypeface(null, Typeface.BOLD)

            tabAlbumPhotos.setBackgroundResource(android.R.color.transparent)
            tabAlbumPhotos.setTextColor(Color.parseColor("#4B5563"))
            tabAlbumPhotos.setTypeface(null, Typeface.NORMAL)

            // Show Photos, Hide Albums
            scrollPhotos.visibility = View.VISIBLE
            scrollAlbums.visibility = View.GONE
        }

        tabAlbumPhotos.setOnClickListener {
            // Update Tab UI
            tabAlbumPhotos.setBackgroundResource(R.drawable.bg_segmented_toggle_active)
            tabAlbumPhotos.setTextColor(Color.WHITE)
            tabAlbumPhotos.setTypeface(null, Typeface.BOLD)

            tabAllPhotos.setBackgroundResource(android.R.color.transparent)
            tabAllPhotos.setTextColor(Color.parseColor("#4B5563"))
            tabAllPhotos.setTypeface(null, Typeface.NORMAL)

            // Hide Photos, Show Albums
            scrollPhotos.visibility = View.GONE
            scrollAlbums.visibility = View.VISIBLE
        }
        val photosBack = findViewById< ImageView>(R.id.photosBack)

        photosBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}