package com.example.dialoguebox

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dialoguebox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }

        binding.btn1.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Are You Sure?")
            builder1.setMessage("Do you want to close this application ?")
            builder1.setIcon(R.drawable.arrow_back)
            builder1.setPositiveButton("Yes" , DialogInterface.OnClickListener { dialogInterface, i ->

                finish()
            })
            builder1.setNegativeButton("No" , DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })

            builder1.show()

        }

        binding.btn2.setOnClickListener {
            val options = arrayOf("Option 1", "Option 2", "Option 3")
            val builder2 = AlertDialog.Builder(this)
            builder2.setTitle("Choose an Option")
            builder2.setSingleChoiceItems(options,0, DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(this, "You clicked on ${options[which]}", Toast.LENGTH_SHORT).show()
            })
            builder2.setPositiveButton("Submit" , DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            builder2.setNegativeButton("Decline" , DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            builder2.show()



        }

        binding.btn3.setOnClickListener {
            val options = arrayOf("Option 1", "Option 2", "Option 3")
            val builder2 = AlertDialog.Builder(this)
            builder2.setTitle("Choose an Option")
            builder2.setMultiChoiceItems(options, null, DialogInterface.OnMultiChoiceClickListener{ dialog, which, isChecked->
                Toast.makeText(this, "You clicked on ${options[which]}", Toast.LENGTH_SHORT).show()
            })
            builder2.setPositiveButton("Submit" , DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            builder2.setNegativeButton("Decline" , DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            builder2.show()
        }
    }
}