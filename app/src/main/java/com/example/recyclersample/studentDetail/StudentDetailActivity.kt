
package com.example.recyclersample.studentDetail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersample.R
import com.example.recyclersample.studentList.STUDENT_ID

class StudentDetailActivity : AppCompatActivity() {

    private val studentDetailViewModel by viewModels<StudentDetailViewModel> {
        StudentDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_detail_activity)

        var currentStudentId: Long? = null

        val studentName: TextView = findViewById(R.id.student_detail_name)
        val studentImage: ImageView = findViewById(R.id.student_detail_image)
        val studentDescription: TextView = findViewById(R.id.student_detail_description)
        val removeStudentButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentStudentId = bundle.getLong(STUDENT_ID)
        }

        currentStudentId?.let {
            val currentStudent = studentDetailViewModel.getStudentForId(it)
            studentName.text = currentStudent?.name
            if (currentStudent?.image == null) {
                studentImage.setImageResource(R.drawable.rose)
            } else {
                studentImage.setImageResource(currentStudent.image)
            }
            studentDescription.text = currentStudent?.description

            removeStudentButton.setOnClickListener {
                if (currentStudent != null) {
                    studentDetailViewModel.removeStudent(currentStudent)
                }
                finish()
            }
        }

    }
}