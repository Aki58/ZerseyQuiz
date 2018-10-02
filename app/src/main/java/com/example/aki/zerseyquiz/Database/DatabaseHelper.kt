package com.example.aki.zerseyquiz.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.aki.zerseyquiz.Modal.Question
import com.example.aki.zerseyquiz.Modal.Quiz
import com.example.aki.zerseyquiz.Modal.User

class DatabaseHelper(context: Context ) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")

    /*private val CREATE_QUIZES_TABLE = ("CREATE TABLE " + TABLE_QUIZES + "("
            + COLUMN_QUIZES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUIZ_NAME + " TEXT,"
            +  ")")*/

    private var CREATE_TABLE = ("CREATE TABLE " + TABLE_TECH + " ( "
            + COLUMN_QUESTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION_NAME + " TEXT, "
            + ANSWER + " TEXT, " + OPTIONA + " TEXT, " + OPTIONB + " TEXT)")

    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_TECH_TABLE = "DROP TABLE IF EXISTS $TABLE_TECH"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_TABLE)
        addQuestions(db)
        //db.execSQL(CREATE_QUIZES_TABLE)
    }

    private fun addQuestions(db: SQLiteDatabase) {
        val q1 = Question(question = "Electric motor was invented by?",optionA =  "Michael Faraday",
                optionB = "Michael Faraday",answer =  "Michael Faraday")
        this.addQuestion(q1,db)

        val q2 = Question(question = "Nuclear power was first used to produce electricity in?",
                optionA = "1951",optionB =  "1941",answer =  "1951")
        this.addQuestion(q2,db)

        val q3 = Question(question = "Printing press was invented by?",
                optionA = "Mary Anderson",optionB =  "Johannes Gutenberg",answer =  "Johannes Gutenberg")
        this.addQuestion(q3,db)

        val q4 = Question(question = "What does 'ATM' stands for?",
                optionA = "Automated Teller Machine",optionB =  "Automatic Transactions Machine",answer =  "Automated Teller Machine")
        this.addQuestion(q4,db)

        val q5 = Question(question = "World Wide Web was first publicly introduced in" ,
                optionA = "1970",optionB =  "1990",answer =  "1990")
        this.addQuestion(q5,db)
    }
    
    fun addQuestion(question: Question,db: SQLiteDatabase) {


        val values=ContentValues()
        values.put(COLUMN_QUESTION_NAME, question.question)
        values.put(ANSWER, question.answer)
        values.put(OPTIONA, question.optionA)
        values.put(OPTIONB, question.optionB)
        db.insert(TABLE_TECH, null, values)
        //db.close()
    }

    fun getAllQuestions(): List<Question> {
        val questionList = ArrayList<Question>()

        val query = "SELECT * FROM $TABLE_TECH"
        var db = this.readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val quest = Question(id =cursor.getInt(0) ,question = cursor.getString(1),optionA = cursor.getString(2),optionB =cursor.getString(3) ,answer =cursor.getString(4))
                questionList.add(quest)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return questionList
    }

    fun rowCount(): Int {
        var row = 0
        val query = "SELECT * FROM $TABLE_TECH"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        row = cursor.count

        cursor.close()
        return row
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_TECH_TABLE)
        onCreate(db)

    }

    fun getAllUser(): List<User> {

        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_PASSWORD)
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()
        val db = this.readableDatabase

        val cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                sortOrder)
        if (cursor.moveToFirst()) {
            do {
                val user = User(id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                        name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                        email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                        password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))

                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }


    fun addUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, values)
        db.close()
    }

    /*fun addQuiz(quiz: Quiz){
        val db=this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_QUIZ_NAME,quiz.name)
        db.insert(TABLE_QUIZES,null,values)
        db.close()
    }*/

    fun updateUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)


        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
                arrayOf(user.id.toString()))
        db.close()
    }

    fun deleteUser(user: User) {

        val db = this.writableDatabase

        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
                arrayOf(user.id.toString()))
        db.close()


    }

    fun checkUser(email: String): Boolean {


        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase


        val selection = "$COLUMN_USER_EMAIL = ?"


        val selectionArgs = arrayOf(email)

        val cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null)


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }


    fun checkUser(email: String, password: String): Boolean {

        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase


        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"


        val selectionArgs = arrayOf(email, password)


        val cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null)

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }

    companion object {


        private val DATABASE_VERSION = 1


        private val DATABASE_NAME = "UserManager.db"


        private val TABLE_USER = "user"

        //private val TABLE_QUIZES="quizes"
        private val TABLE_TECH="tech"

        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"

        //private val COLUMN_QUIZES_ID = "quiz_id"
        //private val COLUMN_QUIZ_NAME = "quiz_name"

        private val COLUMN_QUESTIONS_ID = "question_id"
        private val COLUMN_QUESTION_NAME = "qestion_name"
        private val ANSWER = "answer"
        private val OPTIONA = "option_a"
        private val OPTIONB="option_b"

    }
}