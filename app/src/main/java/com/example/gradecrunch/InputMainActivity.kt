package com.example.gradecrunch

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class InputMainActivity : AppCompatActivity() {

    private var btn: Button? = null
    private var lv: ListView? = null
    private var customeAdapter: EditAdapter? = null
    lateinit var editModelArrayList: ArrayList<EditModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputmain)

        lv = findViewById(R.id.listView) as ListView
        btn = findViewById(R.id.btn) as Button

        editModelArrayList = populateList()
        customeAdapter = EditAdapter(this, editModelArrayList)
        lv!!.adapter = customeAdapter

        btn!!.setOnClickListener {
            val intent = Intent(this@InputMainActivity, ResultsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun populateList(): ArrayList<EditModel> {

        val list = ArrayList<EditModel>()

        for (i in 0..7) {
            val editModel = EditModel()
            editModel.setEditTextValues("")
            list.add(editModel)
        }

        return list
    }

}