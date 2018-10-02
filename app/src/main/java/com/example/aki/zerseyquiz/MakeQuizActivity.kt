package com.example.aki.zerseyquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.aki.zerseyquiz.Database.DatabaseHelper

class MakeQuizActivity : AppCompatActivity(),View.OnClickListener {

    private val activity = this@MakeQuizActivity

    private lateinit var q1: EditText
    private lateinit var q1o1: EditText
    private lateinit var q1o2: EditText
    private lateinit var q1ans:EditText

    private lateinit var q2: EditText
    private lateinit var q2o1: EditText
    private lateinit var q2o2: EditText
    private lateinit var q2ans:EditText


    private lateinit var q3: EditText
    private lateinit var q3o1: EditText
    private lateinit var q3o2: EditText
    private lateinit var q3ans:EditText


    private lateinit var q4: EditText
    private lateinit var q4o1: EditText
    private lateinit var q4o2: EditText
    private lateinit var q4ans:EditText

    private lateinit var q5: EditText
    private lateinit var q5o1: EditText
    private lateinit var q5o2: EditText
    private lateinit var q5ans:EditText


    private lateinit var q1s: String
    private lateinit var q1o1s: String
    private lateinit var q1o2s: String
    private lateinit var q1anss: String


    private lateinit var q2s: String
    private lateinit var q2o1s: String
    private lateinit var q2o2s: String
    private lateinit var q2anss: String


    private lateinit var q3s: String
    private lateinit var q3o1s: String
    private lateinit var q3o2s: String
    private lateinit var q3anss: String


    private lateinit var q4s: String
    private lateinit var q4o1s: String
    private lateinit var q4o2s: String
    private lateinit var q4anss: String


    private lateinit var q5s: String
    private lateinit var q5o1s: String
    private lateinit var q5o2s: String
    private lateinit var q5anss: String






    private lateinit var createBtn: Button
    private lateinit var quizName:EditText
    private lateinit var quizNames:String

    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_quiz)

        initViews()

        initListeners()

        initObjects()
    }

    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity)

    }

    private fun initListeners() {
        createBtn!!.setOnClickListener(this)

    }

    private fun initViews() {

        q1 = findViewById<View>(R.id.q1) as EditText
        q1o1 = findViewById<View>(R.id.q1o1) as EditText
        q1o2 = findViewById<View>(R.id.q1o2) as EditText

        q2 = findViewById<View>(R.id.q2) as EditText
        q2o1 = findViewById<View>(R.id.q2o1) as EditText
        q2o2 = findViewById<View>(R.id.q2o2) as EditText

        q3 = findViewById<View>(R.id.q3) as EditText
        q3o1 = findViewById<View>(R.id.q3o2) as EditText
        q3o2 = findViewById<View>(R.id.q3o2) as EditText

        q4 = findViewById<View>(R.id.q4) as EditText
        q4o1 = findViewById<View>(R.id.q4o1) as EditText
        q4o2 = findViewById<View>(R.id.q4o2) as EditText

        q5 = findViewById<View>(R.id.q5) as EditText
        q5o1 = findViewById<View>(R.id.q5o1) as EditText
        q5o2 = findViewById<View>(R.id.q5o2) as EditText

        createBtn=findViewById<View>(R.id.create) as Button
        quizName=findViewById<View>(R.id.quiz_name) as EditText
    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.create -> postDataToSQLite()

        }
    }

    private fun postDataToSQLite() {


       /* entries()
        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {

            var user = User(name = textInputEditTextName!!.text.toString().trim(),
                    email = textInputEditTextEmail!!.text.toString().trim(),
                    password = textInputEditTextPassword!!.text.toString().trim())

            databaseHelper!!.addUser(user)


            Toast.makeText(this,getString(R.string.success_message), Toast.LENGTH_SHORT).show();
            emptyInputEditText()


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(this,getString(R.string.error_email_exists), Toast.LENGTH_SHORT).show();
        }*/


    }

    private fun entries() {

        q1s=q1.text.toString().trim()
        q1o1s=q1o1.text.toString().trim()
        q1o2s=q1o2.text.toString().trim()
        q1anss=q1ans.text.toString().trim()

        q2s=q2.text.toString().trim()
        q2o1s=q2o1.text.toString().trim()
        q2o2s=q2o2.text.toString().trim()
        q2anss=q2ans.text.toString().trim()


        q3s=q3.text.toString().trim()
        q3o1s=q3o1.text.toString().trim()
        q3o2s=q3o2.text.toString().trim()
        q3anss=q3ans.text.toString().trim()

        q4s=q4.text.toString().trim()
        q4o1s=q4o1.text.toString().trim()
        q4o2s=q4o2.text.toString().trim()
        q4anss=q4ans.text.toString().trim()

        q5s=q5.text.toString().trim()
        q5o1s=q5o1.text.toString().trim()
        q5o2s=q5o2.text.toString().trim()
        q5anss=q5ans.text.toString().trim()

        quizNames=quizName.text.toString().trim()

    }

    private fun emptyInputEditText() {
        q1!!.text = null
        q1o1!!.text = null
        q1o2!!.text = null
        q1ans!!.text = null

        q2!!.text = null
        q2o1!!.text = null
        q2o2!!.text = null
        q2ans!!.text = null


        q3!!.text = null
        q3o1!!.text = null
        q3o2!!.text = null
        q3ans!!.text = null


        q4!!.text = null
        q4o1!!.text = null
        q4o2!!.text = null
        q4ans!!.text = null


        q5!!.text = null
        q5o1!!.text = null
        q5o2!!.text = null
        q5ans!!.text = null

        quizName!!.text=null


    }
}
