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

class Audios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_audios)

        // Handle Window Insets
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI Elements
        val tabAll = findViewById<TextView>(R.id.tabAll)
        val tabAlbums = findViewById<TextView>(R.id.tabAlbums)
        val tabArtist = findViewById<TextView>(R.id.tabArtist)
        
        val scrollAll = findViewById<View>(R.id.scrollAll)
        val scrollAlbums = findViewById<View>(R.id.scrollAlbums)
        val scrollArtist = findViewById<View>(R.id.scrollArtist)
        
        val btnBack = findViewById<FrameLayout>(R.id.btnBack)

        // Back Button
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tab Switching
        tabAll.setOnClickListener {
            setActiveTab(tabAll, tabAlbums, tabArtist, scrollAll, scrollAlbums, scrollArtist, 0)
        }

        tabAlbums.setOnClickListener {
            setActiveTab(tabAll, tabAlbums, tabArtist, scrollAll, scrollAlbums, scrollArtist, 1)
        }

        tabArtist.setOnClickListener {
            setActiveTab(tabAll, tabAlbums, tabArtist, scrollAll, scrollAlbums, scrollArtist, 2)
        }
    }

    private fun setActiveTab(
        tabAll: TextView,
        tabAlbums: TextView,
        tabArtist: TextView,
        viewAll: View,
        viewAlbums: View,
        viewArtist: View,
        index: Int
    ) {
        // Reset all tabs
        val tabs = listOf(tabAll, tabAlbums, tabArtist)
        val views = listOf(viewAll, viewAlbums, viewArtist)

        for (i in tabs.indices) {
            if (i == index) {
                tabs[i].setBackgroundResource(R.drawable.bg_segmented_toggle_active)
                tabs[i].setTextColor(Color.WHITE)
                tabs[i].setTypeface(null, Typeface.BOLD)
                views[i].visibility = View.VISIBLE
            } else {
                tabs[i].setBackgroundResource(android.R.color.transparent)
                tabs[i].setTextColor(Color.parseColor("#4B5563"))
                tabs[i].setTypeface(null, Typeface.NORMAL)
                views[i].visibility = View.GONE
            }
        }
    }
}