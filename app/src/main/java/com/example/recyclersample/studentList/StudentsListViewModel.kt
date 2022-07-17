
package com.example.recyclersample.studentList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclersample.data.DataSource
import com.example.recyclersample.data.Student
import kotlin.random.Random

class StudentsListViewModel(val dataSource: DataSource) : ViewModel() {

    val studentsLiveData = dataSource.getStudentList()

    fun insertStudent(studentName: String?, studentDescription: String?) {
        if (studentName == null || studentDescription == null) {
            return
        }

        val image = dataSource.getRandomStudentImageAsset()
        val newStudent = Student(
            Random.nextLong(),
            studentName,
            image,
            studentDescription
        )

        dataSource.addStudent(newStudent)
    }
}

class StudentListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentsListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}