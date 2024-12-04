package com.example.iqtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Start Quiz button
        val startQuizButton: Button = findViewById(R.id.startQuizButton)
        // Find the Developed By button
        val developedByButton: Button = findViewById(R.id.developedByButton)

        // Set a click listener on the Start Quiz button
        startQuizButton.setOnClickListener {
            // Navigate to QuizActivity when the button is clicked
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Set a click listener on the Developed By button
        developedByButton.setOnClickListener {
            // Navigate to DeveloperProfileActivity when the button is clicked
            val intent = Intent(this, DeveloperProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
