package com.example.multiscreenapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    companion object{
        const val Key= "com.example.MultiScreenApp.MainActivity.KEY"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnOrder = findViewById<Button>(R.id.button)
        val et1 = findViewById<EditText>(R.id.editTextOrder1)
        val et2 = findViewById<EditText>(R.id.editTextOrder2)
        val et3 = findViewById<EditText>(R.id.editTextOrder3)
        val et4 = findViewById<EditText>(R.id.editTextOrder4)
        btnOrder.setOnClickListener {
            val oderList = et1.text.toString() + " " + et2.text.toString() + " " + et3.text.toString() + " " + et4.text.toString()

            intent = Intent(this, order::class.java)
            intent.putExtra(Key, oderList)
            startActivity(intent)
        }

    }
}