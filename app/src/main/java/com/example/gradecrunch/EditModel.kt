package com.example.gradecrunch

class EditModel {

    var editTextValue: String? = null

    fun getEditTextValues(): String {
        return editTextValue.toString()
    }

    fun setEditTextValues(editTextValue: String) {
        this.editTextValue = editTextValue
    }

}