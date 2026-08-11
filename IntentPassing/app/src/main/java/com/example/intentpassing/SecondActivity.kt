package com.example.intentpassing

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGoBack = findViewById<Button>(R.id.btnGoBack)

        btnGoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("title", "Home")
            intent.putExtra("Student Name", "Taha")
            intent.putExtra("Roll No",1122)
            startActivity(intent)
        }
    }
}