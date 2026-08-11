package com.example.loginapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var btnRegister: MaterialButton
    private lateinit var txtLogin: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Views
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)

        btnRegister = findViewById(R.id.btnRegister)
        txtLogin = findViewById(R.id.txtLogin)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        // Register Button
        btnRegister.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Enter username"
                }

                email.isEmpty() -> {
                    etEmail.error = "Enter email"
                }

                password.isEmpty() -> {
                    etPassword.error = "Enter password"
                }

                confirmPassword.isEmpty() -> {
                    etConfirmPassword.error = "Confirm your password"
                }

                password != confirmPassword -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }

                else -> {

                    sharedPreferences.edit()
                        .putString("username", username)
                        .putString("email", email)
                        .putString("password", password)
                        .apply()

                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
        // Go back to Login Screen
        txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}