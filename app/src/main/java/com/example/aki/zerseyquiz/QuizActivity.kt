package com.example.aki.zerseyquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.aki.zerseyquiz.Database.DatabaseHelper
import com.example.aki.zerseyquiz.Modal.Question



class QuizActivity : AppCompatActivity(),View.OnClickListener {

    var questionList: List<Question>? = null
    var score = 0
    var questionID = 0

    var currentQuestion: Question? = null
    var txtQuestion: TextView? = null
    var rbtnA: RadioButton? = null
    var rbtnB:RadioButton? = null
    var btnNext: Button? = null
    var scoreTxt: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val dba = DatabaseHelper(this)
        questionList = dba.getAllQuestions()
        txtQuestion = findViewById<View>(R.id.ques) as TextView
        rbtnA = findViewById<View>(R.id.o1) as RadioButton
        rbtnB = findViewById<View>(R.id.o2) as RadioButton
        btnNext = findViewById<View>(R.id.next) as Button
        btnNext!!.setOnClickListener(this)
        scoreTxt=findViewById<View>(R.id.ans) as TextView
        setQuestionView()
    }

    private fun setQuestionView() {
        currentQuestion = questionList!!.get(questionID)
        txtQuestion!!.setText(currentQuestion!!.question)
        rbtnA!!.setText(currentQuestion!!.optionA)
        rbtnB!!.setText(currentQuestion!!.optionB)
        if (rbtnA!!.isSelected && !rbtnB!!.isSelected){
            if (rbtnA!!.text.toString().equals(currentQuestion!!.answer)){score++}
        }
        else
        if (rbtnB!!.isSelected && !rbtnA!!.isSelected){
            if (rbtnB!!.text.toString().equals(currentQuestion!!.answer)){score++}
        }
    }
    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id){
                R.id.next->{
                    if (questionID<=3)
                    {++questionID
                    setQuestionView()}
                    else{
                        scoreTxt!!.visibility=View.VISIBLE
                        scoreTxt!!.text= score.toString()
                    }
                }
            }
        }
    }
}
