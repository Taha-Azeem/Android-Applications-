package com.example.database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<EditText>(R.id.etName)
        val etMail = findViewById<EditText>(R.id.etMail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etUserName = findViewById<EditText>(R.id.etUserName)
        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val password = etPassword.text.toString()
            val userName = etUserName.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name, mail, password, userName)
            database.child(userName).setValue(User).addOnSuccessListener {
                etName.text.clear()
                etMail.text.clear()
                etPassword.text.clear()
                etUserName.text.clear()
                Toast.makeText(this, "User data inserted successfully", Toast.LENGTH_SHORT).show()

            }
        }

        val signIntext = findViewById<TextView>(R.id.tvSignIN)
        signIntext.setOnClickListener {
            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }
    }
}