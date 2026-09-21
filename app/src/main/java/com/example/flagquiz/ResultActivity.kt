package com.example.flagquiz

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
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

        val txtPlayerName =
            findViewById<TextView>(R.id.txtPlayerName)
        val txtScore =
            findViewById<TextView>(R.id.txtScore)
        val txtHits =
            findViewById<TextView>(R.id.txtHits)

        val bundle = intent.extras
        if (bundle != null) {
            val playerName = bundle.getString("playerName", "")
            val score = bundle.getInt("score", 0)
            val hits = bundle.getInt("hits", 0)
            val totalQuestions = bundle.getInt("totalQuestions", 0)

            txtPlayerName.text = "Jogador: $playerName"
            txtScore.text = "Pontuação final: $score pontos"
            txtHits.text = "Você acertou $hits de $totalQuestions perguntas"
        }
    }

    fun restartGame(view: View) {
        finish()
    }
}
