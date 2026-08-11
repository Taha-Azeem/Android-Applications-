package com.example.loginapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var txtUsername: TextView
    private lateinit var btnLogout: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtUsername = findViewById(R.id.txtUsername)
        btnLogout = findViewById(R.id.btnLogout)

        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "User")
        txtUsername.text = username

        btnLogout.setOnClickListener {

            sharedPreferences.edit().clear().apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}