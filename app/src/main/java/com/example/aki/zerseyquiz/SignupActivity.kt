package com.example.aki.zerseyquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.aki.zerseyquiz.Database.DatabaseHelper
import com.example.aki.zerseyquiz.Modal.User

class SignupActivity : AppCompatActivity(),View.OnClickListener {

    private val activity = this@SignupActivity

    private lateinit var textInputEditTextName: EditText
    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var textInputEditTextConfirmPassword: EditText

    private lateinit var signupBtn: Button
    private lateinit var loginBtn: Button

    private lateinit var inputValidation: validate
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        supportActionBar!!.hide()


        initViews()

        initListeners()

        initObjects()
    }


    private fun initViews() {

        textInputEditTextName = findViewById<View>(R.id.nameTxt) as EditText
        textInputEditTextEmail = findViewById<View>(R.id.emailTxt) as EditText
        textInputEditTextPassword = findViewById<View>(R.id.passTxt) as EditText
        textInputEditTextConfirmPassword = findViewById<View>(R.id.conpassTxt) as EditText

        signupBtn = findViewById<View>(R.id.signupBtn) as Button
        loginBtn = findViewById<View>(R.id.loginBtn) as Button

    }


    private fun initListeners() {
        signupBtn!!.setOnClickListener(this)
        loginBtn!!.setOnClickListener(this)

    }


    private fun initObjects() {
        inputValidation = validate(activity)
        databaseHelper = DatabaseHelper(activity)


    }



    override fun onClick(v: View) {
        when (v.id) {

            R.id.signupBtn -> postDataToSQLite()

            R.id.loginBtn -> finish()
        }
    }


    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword ,getString(R.string.error_password_match))) {
            return
        }

        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {

            var user = User(name = textInputEditTextName!!.text.toString().trim(),
                    email = textInputEditTextEmail!!.text.toString().trim(),
                    password = textInputEditTextPassword!!.text.toString().trim())

            databaseHelper!!.addUser(user)


            Toast.makeText(this,getString(R.string.success_message),Toast.LENGTH_SHORT).show();
            emptyInputEditText()


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(this,getString(R.string.error_email_exists),Toast.LENGTH_SHORT).show();
        }


    }


    private fun emptyInputEditText() {
        textInputEditTextName!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
        textInputEditTextConfirmPassword!!.text = null
    }
}
