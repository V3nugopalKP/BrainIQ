package com.example.iqtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("What comes next in the pattern: J, F, M, A, M, J, J, __?", listOf("A", "S", "O", "D"), 2),
        Question("I have keys but no locks. I have space but no room. You can enter but can’t go outside. What am I?", listOf("Keyboard", "Door", "Safe", "House"), 0),
        Question("Find the odd one out: 121, 144, 169, 190, 225.", listOf("121", "190", "169", "225"), 1),
        Question("I am an odd number. Take away one letter, and I become even. What number am I?", listOf("Seven", "Eleven", "Nine", "Thirteen"), 0),
        Question("What is the missing number in the series: 1, 3, 6, 10, 15, __?", listOf("18", "21", "20", "19"), 1),
        Question("What can fill a room but takes up no space?", listOf("Light", "Air", "Water", "Sound"), 0),
        Question("Find the odd one out: 64, 125, 216, 343, 512.", listOf("64", "125", "216", "343"), 1),
        Question("If an electric train is traveling north, which way is the smoke going?", listOf("North", "South", "East", "None"), 3),
        Question("What begins with T, ends with T, and has T in it?", listOf("Tea", "Tent", "Teapot", "Train"), 2),
        Question("Which letter comes next: A, B, D, G, K, __?", listOf("L", "O", "P", "M"), 1),
        Question("What has hands but can’t clap?", listOf("Clock", "Table", "Mirror", "Door"), 0),
        Question("What has to be broken before you can use it?", listOf("Glass", "Egg", "Door", "Code"), 1),
        Question("The more of me you take, the more you leave behind. What am I?", listOf("Air", "Steps", "Shadow", "Time"), 1),
        Question("What has a head, a tail, but no body?", listOf("Coin", "Snake", "Pin", "Bottle"), 0),
        Question("What is so fragile that saying its name breaks it?", listOf("Glass", "Silence", "Ice", "Heart"), 1),
        Question("I am tall when I’m young, and I’m short when I’m old. What am I?", listOf("Candle", "Tree", "Shadow", "Pencil"), 0),
        Question("The more you take, the more you leave behind. What is it?", listOf("Time", "Footsteps", "Memories", "Water"), 1),
        Question("What has one eye but can’t see?", listOf("Needle", "Storm", "Key", "Cloud"), 0)

    )

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var timer: CountDownTimer
    private var selectedOptionIndex = -1 // To track the currently selected option

    private val QUIZ_DURATION = 180000 // milliseconds (1 minute)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText: TextView = findViewById(R.id.questionText)
        val questionNumberText: TextView = findViewById(R.id.questionNumber)
        val option1: Button = findViewById(R.id.option1)
        val option2: Button = findViewById(R.id.option2)
        val option3: Button = findViewById(R.id.option3)
        val option4: Button = findViewById(R.id.option4)
        val nextButton: Button = findViewById(R.id.nextButton)
        val finishButton: Button = findViewById(R.id.finishButton) // New Finish Button
        val timerText: TextView = findViewById(R.id.timerText)

        timer = object : CountDownTimer(QUIZ_DURATION.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000
                val minutes = remainingSeconds / 60
                val seconds = remainingSeconds % 60
                timerText.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                Toast.makeText(this@QuizActivity, "Time's Up!", Toast.LENGTH_SHORT).show()
                submitQuiz()
            }
        }

        fun loadQuestion() {
            val question = questions[currentQuestionIndex]
            questionNumberText.text = "Question ${currentQuestionIndex + 1}/${questions.size}"
            questionText.text = question.text
            option1.text = question.options[0]
            option2.text = question.options[1]
            option3.text = question.options[2]
            option4.text = question.options[3]
            selectedOptionIndex = -1 // Reset selected option for each question
        }

        loadQuestion()
        timer.start() // Start the timer when the quiz begins

        nextButton.setOnClickListener {
            if (selectedOptionIndex == -1) {
                Toast.makeText(this, "Please select an option before proceeding.", Toast.LENGTH_SHORT).show()
            } else {
                checkAnswer(selectedOptionIndex)
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    loadQuestion()
                } else {
                    submitQuiz()
                }
            }
        }

        // Finish button implementation
        finishButton.setOnClickListener {
            timer.cancel() // Cancel the timer
            submitQuiz() // Directly submit the quiz
        }

        // Set click listeners for buttons to track selected option
        option1.setOnClickListener { selectedOptionIndex = 0 }
        option2.setOnClickListener { selectedOptionIndex = 1 }
        option3.setOnClickListener { selectedOptionIndex = 2 }
        option4.setOnClickListener { selectedOptionIndex = 3 }
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val question = questions[currentQuestionIndex]
        val correctOptionIndex = question.correctOption

        if (selectedOptionIndex == correctOptionIndex) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect. The answer is ${question.options[correctOptionIndex]}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun submitQuiz() {
        timer.cancel() // Stop the timer
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        startActivity(intent)
        finish()
    }

    data class Question(
        val text: String,
        val options: List<String>,
        val correctOption: Int
    )
}
