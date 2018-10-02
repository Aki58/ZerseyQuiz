package com.example.aki.zerseyquiz

import android.content.Context

import android.widget.EditText
import android.widget.Toast

class validate (private val context: Context) {

    fun isInputEditTextFilled(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

            return false
        }

        return true
    }

    fun isInputEditTextEmail(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

            return false
        }
        return true
    }

    fun isInputEditTextMatches(textInputEditText1: EditText, textInputEditText2: EditText, message: String): Boolean {
        val value1 = textInputEditText1.text.toString().trim()
        val value2 = textInputEditText2.text.toString().trim()
        if (!value1.contentEquals(value2)) {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
            return false
        }
        return true
    }

}