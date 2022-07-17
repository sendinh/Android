
package com.example.recyclersample.studentList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.addStudent.AddStudentActivity
import com.example.recyclersample.studentDetail.StudentDetailActivity
import com.example.recyclersample.R
import com.example.recyclersample.addStudent.STUDENT_DESCRIPTION
import com.example.recyclersample.addStudent.STUDENT_NAME
import com.example.recyclersample.data.Student

const val STUDENT_ID = "flower id"

class StudentsListActivity : AppCompatActivity() {
    private val newStudentActivityRequestCode = 1
    private val studentsListViewModel by viewModels<StudentsListViewModel> {
        StudentListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerAdapter = HeaderAdapter()
        val studentsAdapter = StudentsAdapter { student -> adapterOnClick(student) }
        val concatAdapter = ConcatAdapter(headerAdapter, studentsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        studentsListViewModel.studentsLiveData.observe(this, {
            it?.let {
                studentsAdapter.submitList(it as MutableList<Student>)
                headerAdapter.updateStudentCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    private fun adapterOnClick(student: Student) {
        val intent = Intent(this, StudentDetailActivity()::class.java)
        intent.putExtra(STUDENT_ID, student.id)
        startActivity(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivityForResult(intent, newStudentActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newStudentActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val studentName = data.getStringExtra(STUDENT_NAME)
                val studentDescription = data.getStringExtra(STUDENT_DESCRIPTION)

                studentsListViewModel.insertStudent(studentName, studentDescription)
            }
        }
    }
}