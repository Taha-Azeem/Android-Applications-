package com.example.screenmirror

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Intent
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        val btnSettings = findViewById<ImageView>(R.id.btnSettings)

        btnMenu.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val ivRemoteIcon = findViewById<ImageView>(R.id.ivRemoteIcon)
        val barRemote = findViewById<ConstraintLayout>(R.id.barRemote)
        val remoteArrow = findViewById<ImageView>(R.id.remoteArrow)

        ivRemoteIcon.setOnClickListener {
            val intent = Intent(this, Remote::class.java)
            startActivity(intent)
        }

        barRemote.setOnClickListener {
            val intent = Intent(this, Remote::class.java)
            startActivity(intent)
        }
        remoteArrow.setOnClickListener {
            val intent = Intent(this, Remote::class.java)
            startActivity(intent)
        }

        val photos = findViewById<ImageView>(R.id.photos)

        photos.setOnClickListener {
            val intent = Intent(this, Photos::class.java)
            startActivity(intent)
        }

        val audio = findViewById<ImageView>(R.id.audio_tab)
        audio.setOnClickListener {
            val intent = Intent(this, Audios::class.java)
            startActivity(intent)
        }

        val btn_video = findViewById<ImageView>(R.id.btn_video)
        btn_video.setOnClickListener {
            val intent = Intent(this, VideosActivity::class.java)
            startActivity(intent)
        }
    }
}