package com.example.fragments

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.fragments.ui.login.LoginFragment

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
        val buttonClock = findViewById<Button>(R.id.btnTime)
        val buttonExam = findViewById<Button>(R.id.btnExam)
        val buttonValidate = findViewById<Button>(R.id.btnValidate)



        buttonClock.setOnClickListener {
            replaceFrameWithFragment(Clock_Fragment())
        }

        buttonExam.setOnClickListener {
            replaceFrameWithFragment(Exam_Fragment())
        }

        buttonValidate.setOnClickListener {

            replaceFrameWithFragment(LoginFragment())
        }

        replaceFrameWithFragment(Clock_Fragment())

    }


    private fun replaceFrameWithFragment(frag : Fragment){
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameLayout, frag)
        fragTransaction.commit()

    }
}