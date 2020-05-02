package com.example.gradecrunch

class InputModel {
    var inputValue: String? = null
    fun setInputValues(editTextValue: String) {
        this.inputValue = editTextValue
    }
    fun getInputValues(): String {
        return inputValue.toString()
    }
}