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

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
class EditAdapter(private val context: Context, editModelArrayList2: ArrayList<EditModel>) : BaseAdapter() {

    init {
        editModelArrayList = editModelArrayList2
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

    override fun getItem(position: Int): Any {
        return editModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.lv_item, null, true)

            holder.editText = convertView!!.findViewById(R.id.editid) as EditText

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.editText!!.setText(editModelArrayList[position].getEditTextValues())

        holder.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                editModelArrayList[position].setEditTextValues(holder.editText!!.text.toString())

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        return convertView
    }

    private inner class ViewHolder {

        var editText: EditText? = null

    }

    companion object {
        lateinit var editModelArrayList: ArrayList<EditModel>
    }

}