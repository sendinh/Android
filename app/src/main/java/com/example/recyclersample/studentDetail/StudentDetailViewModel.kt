
package com.example.recyclersample.studentDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclersample.data.DataSource
import com.example.recyclersample.data.Student

class StudentDetailViewModel(private val datasource: DataSource) : ViewModel() {

    fun getStudentForId(id: Long) : Student? {
        return datasource.getStudentForId(id)
    }


    fun removeStudent(student: Student) {
        datasource.removeStudent(student)
    }
}

class StudentDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}