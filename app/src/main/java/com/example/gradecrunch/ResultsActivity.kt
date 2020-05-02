package com.example.gradecrunch

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*


class ResultsActivity : AppCompatActivity() {

    private val grade = Array(8) {0.0}
    private val weight = Array(8) {0.0}
    private var finalGrade = 0.0
    private val handler = Handler()
    private var gradePercent: TextView? = null
    private var gradeLetter: TextView? = null
    private var resultText: TextView? = null
    private var letterGrade = ""
    private var minimum = ""
    private var minimumScore = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var intent = intent
        minimum = intent.getStringExtra("Minimum")

        gradePercent = findViewById(R.id.gradePercent) as TextView
        gradeLetter = findViewById(R.id.gradeLetter) as TextView
        resultText = findViewById(R.id.resultText) as TextView

        handler.post(object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                timeRemaining()
            }
        })
        calculateGrade()
        getGrade()
        satisfiesMinimum()
    }

    private fun calculateGrade() {
        for (i in 0 until InputAdapter.editModelArrayList.size) {
            val str: String = InputAdapter.editModelArrayList.get(i).getInputValues()
            val delimiter = ","
            if (!str.equals(" ")) {
                val parts = str.split(delimiter)
                weight[i] = parts[1].toDouble()
                grade[i] = parts[0].toDouble()
                finalGrade += grade[i] * (weight[i] / 100)
            }
        }
        gradePercent!!.text = finalGrade.toString() + "%"
    }

    private fun getGrade() {
        if (finalGrade >= 97.0) {
            letterGrade = "A+"
        } else if (finalGrade >= 93.0) {
            letterGrade = "A"
        } else if (finalGrade >= 90.0) {
            letterGrade = "A-"
        } else if (finalGrade >= 87.0) {
            letterGrade = "B+"
        } else if (finalGrade >= 83.0) {
            letterGrade = "B"
        } else if (finalGrade >= 80.0) {
            letterGrade = "B-"
        } else if (finalGrade >= 77.0) {
            letterGrade = "C+"
        } else if (finalGrade >= 73.0) {
            letterGrade = "C"
        } else if (finalGrade >= 70.0) {
            letterGrade = "C-"
        } else if (finalGrade >= 67.0) {
            letterGrade = "D+"
        } else if (finalGrade >= 63.0) {
            letterGrade = "D"
        } else if (finalGrade >= 60.0) {
            letterGrade = "D-"
        } else if (finalGrade < 60.0) {
            letterGrade = "F"
        }
        gradeLetter!!.text = letterGrade
    }

    private fun satisfiesMinimum() {
        if (minimum.equals("A+")){
            minimumScore = 97.0
        } else if (minimum.equals("A")) {
            minimumScore = 93.0
        } else if (minimum.equals("A-")) {
            minimumScore = 90.0
        } else if (minimum.equals("B+")) {
            minimumScore = 87.0
        } else if (minimum.equals("B")) {
            minimumScore = 83.0
        } else if (minimum.equals("B-")) {
            minimumScore = 80.0
        } else if (minimum.equals("C+")) {
            minimumScore = 77.0
        } else if (minimum.equals("C")) {
            minimumScore = 73.0
        } else if (minimum.equals("C-")) {
            minimumScore = 70.0
        } else if (minimum.equals("D+")) {
            minimumScore = 67.0
        } else if (minimum.equals("D")) {
            minimumScore = 63.0
        } else if (minimum.equals("D-")) {
            minimumScore = 60.0
        } else if (minimum.equals("F")) {
            minimumScore = 0.0
        }
        if (minimumScore <= finalGrade) {
            resultText!!.text = "Keep the Letter Grade! You've earned it!"
        } else {
            resultText!!.text = "Consider converting it to credit/no-credit."
        }
    }

    fun timeRemaining() {
        val presentDate = Calendar.getInstance()
        val deadlineDate = Calendar.getInstance()
        deadlineDate[Calendar.YEAR] = 2020
        deadlineDate[Calendar.MONTH] = 4
        deadlineDate[Calendar.DAY_OF_MONTH] = 7
        deadlineDate[Calendar.HOUR] = 0
        deadlineDate[Calendar.MINUTE] = 0
        deadlineDate[Calendar.SECOND] = 0
        deadlineDate.timeZone = TimeZone.getTimeZone("GMT-5")

        val timeLeft = deadlineDate.timeInMillis - presentDate.timeInMillis
        val daysLeft = timeLeft / (24 * 60 * 60 * 1000)
        val hoursLeft = timeLeft / (1000 * 60 * 60) % 24
        val minutesLeft = timeLeft / (1000 * 60) % 60
        val secondsLeft = (timeLeft / 1000) % 60

        timer.text = "${daysLeft}d ${hoursLeft}h ${minutesLeft}m ${secondsLeft}s"
        endTimer(presentDate, deadlineDate)
    }

    private fun endTimer(currentdate: Calendar, eventdate: Calendar) {
        if (currentdate.time >= eventdate.time) {
            timer.text = "The deadline has passed :("
            handler.removeMessages(0)
        }
    }

}