package com.example.listviewcustomized

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var userArrayList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = arrayOf("Person 1","Person 2","Person 3","Person 4","Person 5","Person 6","Person 7","Person 8","Person 9","Person 10")
        val lastMsg = arrayOf("Hey, Just checking on you?","How are you?","Gd Bye","How are you?","Did you eat today?","Bro pick up the call","Call me later","How are you?","Gd Bye","How are you")
        val lastMsgTime = arrayOf("5:30 PM","4:30 PM","7:30 PM","5:30 PM","4:30 PM","7:30 PM","5:30 PM","4:30 PM","7:30 PM","5:30 PM")
        val phoneNumber = arrayOf("987654321","884737483","987654321","884737483","987654321","884737483","987654321","884737483","987654321","884737483")
        val imageId = arrayOf(R.drawable.img,
            R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img)

        userArrayList = ArrayList()
        for (i in name.indices) {
            userArrayList.add(User(name[i], lastMsg[i], lastMsgTime[i], phoneNumber[i], imageId[i]))
        }

        val listView = findViewById<ListView>(R.id.listView)
        listView.isClickable = true
        listView.adapter = MyAdapter(this, userArrayList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val userName = name[position]
            val userPhone = phoneNumber[position]
            val imageId = imageId[position]

            val i = Intent(this, UserActivity::class.java)
            i.putExtra("name", userName)
            i.putExtra("phone", userPhone)
            i.putExtra("imageId", imageId)
            startActivity(i)


        }
    }
}