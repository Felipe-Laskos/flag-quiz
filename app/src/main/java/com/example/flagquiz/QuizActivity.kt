package com.example.flagquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {

    private var playerName = ""
    private var currentQuestion = 0
    private var hits = 0
    private var score = 0

    private val totalQuestions = 5
    private val pointsPerQuestion = 20

    private val flags = arrayOf(
        R.drawable.flag_brazil,
        R.drawable.flag_argentina,
        R.drawable.flag_uruguay,
        R.drawable.flag_chile,
        R.drawable.flag_colombia,
        R.drawable.flag_peru,
        R.drawable.flag_mexico,
        R.drawable.flag_canada,
        R.drawable.flag_portugal,
        R.drawable.flag_spain,
        R.drawable.flag_france,
        R.drawable.flag_italy,
        R.drawable.flag_germany,
        R.drawable.flag_belgium,
        R.drawable.flag_switzerland,
        R.drawable.flag_ireland,
        R.drawable.flag_sweden,
        R.drawable.flag_norway,
        R.drawable.flag_denmark,
        R.drawable.flag_poland,
        R.drawable.flag_ukraine,
        R.drawable.flag_russia,
        R.drawable.flag_greece,
        R.drawable.flag_turkey,
        R.drawable.flag_japan,
        R.drawable.flag_china,
        R.drawable.flag_south_korea,
        R.drawable.flag_india,
        R.drawable.flag_australia,
        R.drawable.flag_south_africa
    )

    private val countries = arrayOf(
        "Brasil",
        "Argentina",
        "Uruguai",
        "Chile",
        "Colômbia",
        "Peru",
        "México",
        "Canadá",
        "Portugal",
        "Espanha",
        "França",
        "Itália",
        "Alemanha",
        "Bélgica",
        "Suíça",
        "Irlanda",
        "Suécia",
        "Noruega",
        "Dinamarca",
        "Polônia",
        "Ucrânia",
        "Rússia",
        "Grécia",
        "Turquia",
        "Japão",
        "China",
        "Coreia do Sul",
        "Índia",
        "Austrália",
        "África do Sul"
    )

    private val gameFlags = IntArray(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
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

        val bundle = intent.extras
        if (bundle != null) {
            playerName = bundle.getString("playerName", "")
        }

        sortFlags()
        showQuestion()
    }

    private fun sortFlags() {
        var position = 0
        while (position < totalQuestions) {
            val number = Random.nextInt(flags.size)
            var repeated = false
            for (i in 0 until position) {
                if (gameFlags[i] == number) {
                    repeated = true
                }
            }
            if (!repeated) {
                gameFlags[position] = number
                position++
            }
        }
    }

    private fun showQuestion() {
        val txtQuestionNumber =
            findViewById<TextView>(R.id.txtQuestionNumber)
        val imgFlag =
            findViewById<ImageView>(R.id.imgFlag)
        val editTextAnswer =
            findViewById<EditText>(R.id.editTextAnswer)
        val txtResult =
            findViewById<TextView>(R.id.txtResult)
        val btnAnswer =
            findViewById<Button>(R.id.btnAnswer)
        val btnNext =
            findViewById<Button>(R.id.btnNext)

        val flagIndex = gameFlags[currentQuestion]

        txtQuestionNumber.text =
            "Pergunta ${currentQuestion + 1} de $totalQuestions"

        imgFlag.setImageResource(flags[flagIndex])

        editTextAnswer.setText("")
        editTextAnswer.isEnabled = true
        txtResult.text = ""

        btnAnswer.isEnabled = true
        btnNext.visibility = View.INVISIBLE
    }

    fun answerQuestion(view: View) {
        val editTextAnswer =
            findViewById<EditText>(R.id.editTextAnswer)
        val txtResult =
            findViewById<TextView>(R.id.txtResult)
        val btnAnswer =
            findViewById<Button>(R.id.btnAnswer)
        val btnNext =
            findViewById<Button>(R.id.btnNext)

        val answer =
            editTextAnswer.text.toString().trim()

        if (answer.isEmpty()) {
            Toast.makeText(
                this,
                "Digite o nome do país.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val flagIndex = gameFlags[currentQuestion]
        val correctAnswer = countries[flagIndex]

        if (removeAccents(answer).lowercase() == removeAccents(correctAnswer).lowercase()) {
            hits++
            score += pointsPerQuestion
            txtResult.text = "Correto!"
            txtResult.setTextColor(Color.parseColor("#2E7D32"))
        } else {
            txtResult.text = "Incorreto!\nA resposta correta é: $correctAnswer"
            txtResult.setTextColor(Color.parseColor("#C62828"))
        }

        editTextAnswer.isEnabled = false
        btnAnswer.isEnabled = false
        btnNext.visibility = View.VISIBLE

        if (currentQuestion == totalQuestions - 1) {
            btnNext.text = "VER RESULTADO"
        } else {
            btnNext.text = "PRÓXIMA"
        }
    }

    fun nextQuestion(view: View) {
        currentQuestion++

        if (currentQuestion < totalQuestions) {
            showQuestion()
        } else {
            showFinalResult()
        }
    }

    private fun showFinalResult() {
        val intent = Intent(this, ResultActivity::class.java)

        intent.putExtra("playerName", playerName)
        intent.putExtra("score", score)
        intent.putExtra("hits", hits)
        intent.putExtra("totalQuestions", totalQuestions)

        startActivity(intent)

        finish()
    }

    private fun removeAccents(text: String): String {
        val withAccents = "áàâãéêíóôõúüçÁÀÂÃÉÊÍÓÔÕÚÜÇ"
        val withoutAccents = "aaaaeeiooouucAAAAEEIOOOUUC"

        var result = text
        for (i in withAccents.indices) {
            result = result.replace(withAccents[i], withoutAccents[i])
        }
        return result
    }
}
