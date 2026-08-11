package com.example.loginapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        // Check if user is already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            if (username == savedUsername && password == savedPassword) {

                sharedPreferences.edit()
                    .putBoolean("isLoggedIn", true)
                    .apply()

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }

        }
        val txtRegister = findViewById<TextView>(R.id.txtRegister)

        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }



    }
}