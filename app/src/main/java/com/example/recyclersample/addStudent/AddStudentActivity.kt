
package com.example.recyclersample.addStudent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersample.R
import com.google.android.material.textfield.TextInputEditText

const val STUDENT_NAME = "name"
const val STUDENT_DESCRIPTION = "description"

class AddStudentActivity : AppCompatActivity() {
    private lateinit var addStudentName: TextInputEditText
    private lateinit var addStudentDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_student_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addStudent()
        }
        addStudentName = findViewById(R.id.add_student_name)
        addStudentDescription = findViewById(R.id.add_student_description)
    }

    private fun addStudent() {
        val resultIntent = Intent()

        if (addStudentName.text.isNullOrEmpty() || addStudentDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addStudentName.text.toString()
            val description = addStudentDescription.text.toString()
            resultIntent.putExtra(STUDENT_NAME, name)
            resultIntent.putExtra(STUDENT_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}