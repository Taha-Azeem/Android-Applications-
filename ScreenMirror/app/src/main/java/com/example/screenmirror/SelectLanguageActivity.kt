package com.example.screenmirror

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelectLanguageActivity : AppCompatActivity() {

    private lateinit var rows: List<View>
    private lateinit var checks: List<ImageView>
    private lateinit var textViews: List<TextView>
    private lateinit var dividers: List<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_language)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Initialize all views from the static XML layout
        rows = listOf(
            findViewById(R.id.langEnglish),
            findViewById(R.id.langFrench),
            findViewById(R.id.langGerman),
            findViewById(R.id.langHebrew),
            findViewById(R.id.langHindi),
            findViewById(R.id.langItalic),
            findViewById(R.id.langKorean),
            findViewById(R.id.langPoland),
            findViewById(R.id.langBrazil)
        )

        checks = listOf(
            findViewById(R.id.ivCheckEng),
            findViewById(R.id.ivCheckFrench),
            findViewById(R.id.ivCheckGerman),
            findViewById(R.id.ivCheckHebrew),
            findViewById(R.id.ivCheckHindi),
            findViewById(R.id.ivCheckItalic),
            findViewById(R.id.ivCheckKorean),
            findViewById(R.id.ivCheckPoland),
            findViewById(R.id.ivCheckBrazil)
        )

        textViews = listOf(
            findViewById(R.id.tvEnglish),
            findViewById(R.id.tvFrench),
            findViewById(R.id.tvGerman),
            findViewById(R.id.tvHebrew),
            findViewById(R.id.tvHindi),
            findViewById(R.id.tvItalic),
            findViewById(R.id.tvKorean),
            findViewById(R.id.tvPoland),
            findViewById(R.id.tvBrazil)
        )

        dividers = listOf(
            View(this), // Placeholder for English which has no divider
            findViewById(R.id.divFrench),
            findViewById(R.id.divGerman),
            findViewById(R.id.divHebrew),
            findViewById(R.id.divHindi),
            findViewById(R.id.divItalic),
            findViewById(R.id.divKorean),
            findViewById(R.id.divPoland),
            findViewById(R.id.divBrazil)
        )

        // 2. Set click listeners for every row
        rows.forEachIndexed { index, view ->
            view.setOnClickListener {
                updateSelection(index)
            }
        }

        // Back button
        findViewById<View>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateSelection(selectedIndex: Int) {
        rows.forEachIndexed { index, row ->
            if (index == selectedIndex) {
                // Apply 'Selected' visual state
                row.setBackgroundResource(R.drawable.bg_selected_language_item)
                checks[index].visibility = View.VISIBLE
                textViews[index].setTextColor(Color.parseColor("#2A3547"))
                if (index > 0) dividers[index].visibility = View.GONE // Hide divider for selected item
            } else {
                // Apply 'Default' visual state
                row.setBackgroundResource(0) // Transparent
                checks[index].visibility = View.GONE
                textViews[index].setTextColor(Color.parseColor("#364152"))
                if (index > 0) dividers[index].visibility = View.VISIBLE // Show divider for unselected items
            }
        }
    }
}