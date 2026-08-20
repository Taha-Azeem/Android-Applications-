package com.example.bottomnavigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        supportActionBar?.hide()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        replaceWithFragment(home())


        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item1 -> replaceWithFragment(home())
                R.id.item2 -> replaceWithFragment(search())
                R.id.item3 -> replaceWithFragment(profile())
                else -> {
                    Toast.makeText(this, "Contact the developer", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }
}