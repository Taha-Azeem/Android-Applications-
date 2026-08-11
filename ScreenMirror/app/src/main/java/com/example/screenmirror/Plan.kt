package com.example.screenmirror

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.ImageButton
class Plan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_plan)
        val btnStartNow = findViewById<Button>(R.id.btnStartNow)

        btnStartNow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val btnClose = findViewById<ImageButton>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish()
        }
    }


}