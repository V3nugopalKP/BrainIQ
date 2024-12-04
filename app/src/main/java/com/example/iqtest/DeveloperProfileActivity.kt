package com.example.iqtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DeveloperProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_profile)

        val profileImage: ImageView = findViewById(R.id.profileImage)
        val profileName: TextView = findViewById(R.id.profileName)
        val profileCollege: TextView = findViewById(R.id.profileCollege)
        val profileTechnologies: TextView = findViewById(R.id.profileTechnologies)

        // Populate the fields with your details
        profileImage.setImageResource(R.drawable.image) // Replace with your image in `res/drawable`
        profileName.text = "Venugopal K P"
        profileCollege.text = "Amrita Vishwa Vidyapeetham,Amritapuri"
        profileTechnologies.text = "Technologies: Android, Java, Kotlin, Python, Machine Learning"
    }
}
