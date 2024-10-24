package com.example.homework1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var tvText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnMora: Button
    private lateinit var tvName: TextView
    private lateinit var tvWinner: TextView
    private lateinit var tvMyMora: TextView
    private lateinit var tvTargetMora: TextView
    private lateinit var tvPicture: TextView
    private lateinit var tvPicture3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        edName = findViewById(R.id.edName)
        tvText = findViewById(R.id.tvText)
        radioGroup = findViewById(R.id.radioGroup)
        btnMora = findViewById(R.id.btnMora)
        tvName = findViewById(R.id.tvName)
        tvWinner = findViewById(R.id.tvWinner)
        tvMyMora = findViewById(R.id.tvMyMora)
        tvTargetMora = findViewById(R.id.tvTargetMora)
        tvPicture = findViewById(R.id.tvPicture)
        tvPicture3 = findViewById(R.id.tvPicture3)
        btnMora.setOnClickListener {
            val playerName = edName.text.toString()
            if (playerName.isBlank()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            val myMora = playerbtn()
            val targetMora = (0..2).random()

            display(playerName, myMora, targetMora)
            compare(playerName, myMora, targetMora)
        }
    }

    private fun playerbtn(): Int = when (radioGroup.checkedRadioButtonId) {
        R.id.btnScissor -> 0
        R.id.btnStone -> 1
        R.id.btnPaper -> 2
        else -> 3
    }

    private fun display(playerName: String, myMora: Int, targetMora: Int) {45
        tvName.text = "名字\n$playerName"
        tvMyMora.text = "我方出拳\n${getMoraString(myMora)}"
        tvTargetMora.text = "電腦出拳\n${getMoraString(targetMora)}"
        tvPicture.text = "${punch(myMora)}"

        tvPicture3.text = "${punch(targetMora)}"
    }

    private fun compare(playerName: String, myMora: Int, targetMora: Int) {
        val (winner, message, ) = when {
            myMora == targetMora -> "平手" to "DRAW"
            (myMora == 0 && targetMora == 2) ||
            (myMora == 1 && targetMora == 0) ||
            (myMora == 2 && targetMora == 1) -> playerName to "WIN"
            else -> "電腦" to "LOSE"
        }
        tvWinner.text = "勝利者\n$winner"
        tvText.text = message

    }

    private fun getMoraString(mora: Int) = when (mora) {
        0 -> "剪刀"
        1 -> "石頭"
        2 -> "布"
        else -> ""
    }

    private fun punch(mora: Int) = when (mora) {
        0 -> "    _______         \n" +
                "---'   ____)____\n" +
                "          ______)  \n" +
                "       __________) \n" +
                "      (____)\n" +
                "---.__(___)"

        1 -> "    _______\n" +
                "---'   ____)  \n" +
                "      (_____) \n" +
                "      (_____) \n" +
                "      (____)\n" +
                "---.__(___)"

        2 -> "    _______ \n" +
                "---'   ____)____ \n" +
                "          ______) \n" +
                "          _______)\n" +
                "         _______) \n" +
                "---.__________)  "
        else -> ""
    }
}
