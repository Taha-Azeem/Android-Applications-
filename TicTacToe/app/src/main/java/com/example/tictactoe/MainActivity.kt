package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
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

        for (i in 0 until buttons.size) {
            buttons[i].setOnClickListener {
                if (gameState[i] == "") {
                    gameState[i] = currentPlayer
                    buttons[i].text = currentPlayer
                    if (checkWin()) {
                        for (button in buttons) {
                            button.isEnabled = false
                        }
                    } else {
                        currentPlayer = if (currentPlayer == "X") "O" else "X"
                    }
                }
            }
        }


    }
    val buttonIds = listOf(
        R.id.button1, R.id.button2, R.id.button3,
        R.id.button4, R.id.button5, R.id.button6,
        R.id.button7, R.id.button8, R.id.button9
    )
    private val buttons by lazy {
        buttonIds.map { findViewById<Button>(it) }
    }

    private fun checkWin(): Boolean {
        for (winPosition in winningPositions) {
            if (gameState[winPosition[0]] != "" &&
                gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]]
            ) {
                return true
            }
        }
        return false
    }

    var currentPlayer = "X"
    var gameState = mutableListOf("","","","","","","","","" )
    var winningPositions = listOf(
        listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
        listOf(0,3,6), listOf(1,4,7), listOf(2,5,8),
        listOf(0,4,8), listOf(2,4,6)
    )


}