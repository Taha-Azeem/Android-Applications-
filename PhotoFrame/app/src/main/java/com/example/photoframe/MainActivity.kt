package com.example.photoframe

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var currentImage = 0
    lateinit var image : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        val prev = findViewById<ImageButton>(R.id.imgPrev)
        val next = findViewById<ImageButton>(R.id.imgNext)

        prev.setOnClickListener {
            val idCurrentImage = "pic$currentImage"
            val idCurrentImageInt = this.resources.getIdentifier(idCurrentImage, "id", packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            currentImage = (4 + currentImage -1) % 4


            val idImageToShow = "pic$currentImage"
            val idImageToShowInt = this.resources.getIdentifier(idImageToShow, "id", packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f

        }

        next.setOnClickListener {
            val idCurrentImage = "pic$currentImage"
            val idCurrentImageInt = this.resources.getIdentifier(idCurrentImage, "id", packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            currentImage = (currentImage + 1) % 4

            val idImageToShow = "pic$currentImage"
            val idImageToShowInt = this.resources.getIdentifier(idImageToShow, "id", packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f

        }

    }
}