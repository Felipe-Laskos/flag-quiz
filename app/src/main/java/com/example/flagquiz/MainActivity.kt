package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val main = findViewById<View>(R.id.main)
        val padding = main.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                padding + systemBars.left,
                padding + systemBars.top,
                padding + systemBars.right,
                padding + systemBars.bottom
            )
            insets
        }
    }

    fun startGame(view: View) {
        val editTextName =
            findViewById<EditText>(R.id.editTextName)

        val playerName =
            editTextName.text.toString().trim()

        if (playerName.isEmpty()) {
            Toast.makeText(
                this,
                "Digite seu nome para começar.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val intent = Intent(this, QuizActivity::class.java)

        intent.putExtra("playerName", playerName)

        startActivity(intent)
    }
}
