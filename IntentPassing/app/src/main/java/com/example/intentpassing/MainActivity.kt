package com.example.intentpassing

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

        }

        val fromSec = intent
        val title = fromSec.getStringExtra("title")
        val studentName = fromSec.getStringExtra("Student Name")
        val rollNo = fromSec.getIntExtra("Roll No",0)


        val getStudentInfo = findViewById<TextView>(R.id.getStudentInfo)
        getStudentInfo.setText("Student Name : $studentName\n Roll No : $rollNo")
        supportActionBar?.title = title
        this.title = title



    }

}