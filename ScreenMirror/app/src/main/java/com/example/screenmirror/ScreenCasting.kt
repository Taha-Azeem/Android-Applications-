package com.example.screenmirror

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.TextView

class ScreenCasting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_casting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextButtonCast = findViewById<Button>(R.id.nextButtonCast)

        nextButtonCast.setOnClickListener {
            val intent = Intent(this, RemoteControl::class.java)
            startActivity(intent)
        }
        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener {
            val intent = Intent(this, Plan::class.java)
            startActivity(intent)
        }

        // why iam having error in skip textview tell me why it is not working


    }

}