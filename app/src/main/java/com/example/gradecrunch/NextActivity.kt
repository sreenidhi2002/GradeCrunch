package com.example.gradecrunch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {

    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        tv = findViewById(R.id.tv) as TextView

        for (i in 0 until EditAdapter.editModelArrayList.size) {
            var str:String = EditAdapter.editModelArrayList.get(i).getEditTextValues()
            println("User entered: $str")

            tv!!.text =
                tv!!.text.toString() + " " + EditAdapter.editModelArrayList.get(i).getEditTextValues() + System.getProperty(
                    "line.separator"
                )

        }

    }
}