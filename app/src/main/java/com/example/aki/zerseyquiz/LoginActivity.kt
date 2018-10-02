package com.example.aki.zerseyquiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.aki.zerseyquiz.Database.DatabaseHelper

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@LoginActivity

    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button



    private lateinit var inputValidation: validate
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()

        initViews()

        initListeners()

        initObjects()
    }

    private fun initViews() {

        textInputEditTextEmail = findViewById<View>(R.id.emailTxt) as EditText
        textInputEditTextPassword = findViewById<View>(R.id.passTxt) as EditText
        loginButton= findViewById<View>(R.id.loginBtn) as Button
        signupButton = findViewById<View>(R.id.signupBtn) as Button




    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {

        loginButton!!.setOnClickListener(this)
        signupButton!!.setOnClickListener(this)
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {

        databaseHelper = DatabaseHelper(activity)
        inputValidation = validate(activity)

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginBtn -> verifyFromSQLite()
            R.id.signupBtn -> {

                val intentRegister = Intent(applicationContext, SignupActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }


    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, getString(R.string.error_message_email))) {
            return
        }

        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' })) {


            val accountsIntent = Intent(activity, MainActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)


        } else {

            // Snack Bar to show success message that record is wrong
            Toast.makeText(this,"Wrong Email or Password", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }
}
