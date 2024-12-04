package com.example.iqtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreText: TextView = findViewById(R.id.scoreText)
        val iqText: TextView = findViewById(R.id.iqText)
        val restartButton: Button = findViewById(R.id.restartButton)

        scoreText.text = "Your Score: $score"
        iqText.text = when (score) {
            in 0..5 -> "IQ: Low"
            in 6..10 -> "IQ: Below Average"
            in 11..15 -> "IQ: Average"
            in 16..19 -> "IQ: Above Average"
            20 -> "IQ: Genius"
            else -> "IQ: Undefined"
        }

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
