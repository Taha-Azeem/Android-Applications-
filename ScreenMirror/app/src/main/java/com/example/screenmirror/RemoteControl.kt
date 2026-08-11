package com.example.screenmirror

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.TextView

class RemoteControl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_remote_control)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nextButtonRemote = findViewById<Button>(R.id.nextButtonRemote)
        nextButtonRemote.setOnClickListener {
            val intent = Intent(this, Plan::class.java)
            startActivity(intent)
        }

        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener {
            val intent = Intent(this, Plan::class.java)
            startActivity(intent)
        }
    }

}