package com.example.screenmirror

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_videos)

        // Handle Window Insets
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI Elements
        val tabAllVideos = findViewById<TextView>(R.id.tabAllVideos)
        val tabAlbumVideos = findViewById<TextView>(R.id.tabAlbumVideos)
        val scrollVideos = findViewById<View>(R.id.scrollVideos)
        val scrollAlbumVideos = findViewById<View>(R.id.scrollAlbumVideos)
        val btnBack = findViewById<FrameLayout>(R.id.btnBack)

        // Back Button
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tab Switching Logic
        tabAllVideos.setOnClickListener {
            updateTabUI(isAllVideosSelected = true)
            scrollVideos.visibility = View.VISIBLE
            scrollAlbumVideos.visibility = View.GONE
        }

        tabAlbumVideos.setOnClickListener {
            updateTabUI(isAllVideosSelected = false)
            scrollVideos.visibility = View.GONE
            scrollAlbumVideos.visibility = View.VISIBLE
        }
    }

    private fun updateTabUI(isAllVideosSelected: Boolean) {
        val tabAll = findViewById<TextView>(R.id.tabAllVideos)
        val tabAlbum = findViewById<TextView>(R.id.tabAlbumVideos)

        if (isAllVideosSelected) {
            tabAll.setBackgroundResource(R.drawable.bg_segmented_toggle_active)
            tabAll.setTextColor(Color.WHITE)
            tabAll.setTypeface(null, Typeface.BOLD)

            tabAlbum.setBackgroundResource(android.R.color.transparent)
            tabAlbum.setTextColor(Color.parseColor("#4B5563"))
            tabAlbum.setTypeface(null, Typeface.NORMAL)
        } else {
            tabAlbum.setBackgroundResource(R.drawable.bg_segmented_toggle_active)
            tabAlbum.setTextColor(Color.WHITE)
            tabAlbum.setTypeface(null, Typeface.BOLD)

            tabAll.setBackgroundResource(android.R.color.transparent)
            tabAll.setTextColor(Color.parseColor("#4B5563"))
            tabAll.setTypeface(null, Typeface.NORMAL)
        }
    }
}