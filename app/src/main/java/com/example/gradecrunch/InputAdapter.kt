package com.example.gradecrunch

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import java.util.ArrayList

class InputAdapter(private val context: Context, gradesList: ArrayList<InputModel>) : BaseAdapter() {

    init {
        editModelArrayList = gradesList
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getCount(): Int {
        return editModelArrayList.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any {
        return editModelArrayList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: myView
        if (convertView == null) {
            holder = myView()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.input_listview, null, true)

            holder.viewEditText = convertView!!.findViewById(R.id.gradeInput) as EditText

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as myView
        }
        holder.viewEditText!!.setText(editModelArrayList[position].getInputValues())
        holder.viewEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                editModelArrayList[position].setInputValues(holder.viewEditText!!.text.toString())

            }
            override fun afterTextChanged(editable: Editable) {
            }
        })
        return convertView
    }

    private inner class myView {
        var viewEditText: EditText? = null
    }
    companion object {
        lateinit var editModelArrayList: ArrayList<InputModel>
    }
}