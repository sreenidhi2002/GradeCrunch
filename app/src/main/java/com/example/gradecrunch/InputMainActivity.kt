package com.example.gradecrunch

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class InputMainActivity : AppCompatActivity() {
    lateinit var theInputArrayList: ArrayList<InputModel>
    private var theInputAdapter: InputAdapter? = null
    private var theButton: Button? = null
    private var theListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputmain)

        theListView = findViewById(R.id.gradesListView) as ListView
        theButton = findViewById(R.id.calculateButton) as Button
        theInputArrayList = populateList()
        theInputAdapter = InputAdapter(this, theInputArrayList)
        theListView!!.adapter = theInputAdapter

        theButton!!.setOnClickListener {
            val myIntent = Intent(this@InputMainActivity, InputMinGradeActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun populateList(): ArrayList<InputModel> {
        val populatedList = ArrayList<InputModel>()
        for (i in 0..7) {
            val input = InputModel()
            input.setInputValues(" ")
            populatedList.add(input)
        }
        return populatedList
    }
}