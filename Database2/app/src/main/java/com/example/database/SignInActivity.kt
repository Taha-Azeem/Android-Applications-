package com.example.database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    companion object{
        const val KEY1 = "com.example.day16database.SignInActivity.mail"
        const val KEY2 = "com.example.day16database.SignInActivity.name"
        const val KEY3 = "com.example.day16database.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth and Credential Manager
        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.userNameEditText)

        signInButton.setOnClickListener {
            val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            } else{
                Toast.makeText(this, "Please enter user id", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoogleSignIn = findViewById<Button>(R.id.btnGoogleSignIn)
        btnGoogleSignIn.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.clientId))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = this@SignInActivity,
                    request = request
                )
                handleSignIn(result)
            } catch (e: GetCredentialException) {
                Toast.makeText(this@SignInActivity, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSignIn(result: androidx.credentials.GetCredentialResponse) {
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                firebaseAuthWithGoogle(idToken)
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(this, "Received invalid google id token", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val intentWelcome = Intent(this, WelcomeActivity::class.java)
                    intentWelcome.putExtra(KEY1, user?.email)
                    intentWelcome.putExtra(KEY2, user?.displayName)
                    intentWelcome.putExtra(KEY3, user?.uid)
                    startActivity(intentWelcome)
                    finish()
                } else {
                    Toast.makeText(this, "Firebase authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(uniqueId).get().addOnSuccessListener {

            // if user exist or not
            if(it.exists()){
                // welcome user in your app, with intent and also pass
                val email = it.child("mail").value
                val name = it.child("name").value
                val userId = it.child("userName").value

                val intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.putExtra(com.example.database.SignInActivity.Companion.KEY1, email.toString())
                intentWelcome.putExtra(com.example.database.SignInActivity.Companion.KEY2, name.toString())
                intentWelcome.putExtra(com.example.database.SignInActivity.Companion.KEY3, userId.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed, Error in DB", Toast.LENGTH_SHORT).show()
        }

    }


}