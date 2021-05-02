package com.example.exam_gamebox_arnaud_thibal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.exam_gamebox_arnaud_thibal.R
import android.widget.Toast

open class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0
    protected fun OnCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset)
        buttonReset.setOnClickListener { }
    }

    override fun onClick(v: View) {
        if ((v as Button).text.toString() != "") {
            return
        }
        if (player1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != ""
    }

    private fun player1Wins() {
        Toast.makeText(this, "Registered player wins !", Toast.LENGTH_SHORT).show()
        reset()
    }

    private fun player2Wins() {
        Toast.makeText(this, "Guest player wins !", Toast.LENGTH_SHORT).show()
        reset()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        reset()
    }

    private fun reset() {
        roundCount = 0
        player1Turn = true
    }
}