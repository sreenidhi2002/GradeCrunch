package com.example.gradecrunch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class InputMinGradeActivity : AppCompatActivity() {

    private var submitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minimumgrade)

        val minGrade = findViewById<EditText>(R.id.minimumGradeText)
        submitButton = findViewById(R.id.submitButton) as Button

        submitButton!!.setOnClickListener {
            var minMin = minGrade.text.toString()
            val intent = Intent(this@InputMinGradeActivity, ResultsActivity::class.java)
            intent.putExtra("Minimum", minMin)
            startActivity(intent)
        }
    }
}