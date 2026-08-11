package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val weightEdt = findViewById<TextView>(R.id.weight_Edt)
        val heightEdt = findViewById<TextView>(R.id.height_Edt)
        val heightInInchesEdt = findViewById<TextView>(R.id.height_in_inches_Edt)
        val calculateBtn = findViewById<Button>(R.id.calculate_btn)
        val resultTxt = findViewById<TextView>(R.id.result_txt)


        calculateBtn.setOnClickListener {
            val weight = weightEdt.text.toString()
            val height = heightEdt.text.toString()
            val heightInInches = heightInInchesEdt.text.toString()
            if (weight.isNotEmpty() && height.isNotEmpty() && heightInInches.isNotEmpty()) {
                val weightValue = weight.toFloat()
                val heightValue = height.toFloat()
                val heightInInchesValue = heightInInches.toFloat()
                val bmi = 703 * (weightValue / (heightValue * heightInInchesValue))
                resultTxt.text = bmi.toString()
                if(bmi < 18.5){
                    resultTxt.text = "Underweight"
                    resultTxt.setTextColor(getColor(R.color.red))
                }else if(bmi >= 18.5 && bmi < 24.9){
                    resultTxt.text = "Normal"
                    resultTxt.setTextColor(getColor(R.color.green))
                }else if(bmi >= 24.9 && bmi < 29.9){
                    resultTxt.text = "Overweight"
                    resultTxt.setTextColor(getColor(R.color.orange))
                }else{
                    resultTxt.text = "Obese"
                    resultTxt.setTextColor(getColor(R.color.red))
                }
            } else {
                resultTxt.text = "Please enter valid values"
                resultTxt.setTextColor(getColor(R.color.red))
                resultTxt.textSize = 20f
                resultTxt.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                resultTxt.setPadding(10, 10, 10, 10)
                resultTxt.setBackgroundColor(getColor(R.color.white))
                resultTxt.setOnClickListener {
                    resultTxt.text = ""
                }
            }
            }


    }
}