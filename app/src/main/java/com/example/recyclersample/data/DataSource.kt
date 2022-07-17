package com.example.recyclersample.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialStudentList = studentList(resources)
    private val studentsLiveData = MutableLiveData(initialStudentList)

    fun addStudent(student: Student) {
        val currentList = studentsLiveData.value
        if (currentList == null) {
            studentsLiveData.postValue(listOf(student))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, student)
            studentsLiveData.postValue(updatedList)
        }
    }

    fun removeStudent(student: Student) {
        val currentList = studentsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(student)
            studentsLiveData.postValue(updatedList)
        }
    }


    fun getStudentForId(id: Long): Student? {
        studentsLiveData.value?.let { students ->
            return students.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getStudentList(): LiveData<List<Student>> {
        return studentsLiveData
    }

    fun getRandomStudentImageAsset(): Int? {
        val randomNumber = (initialStudentList.indices).random()
        return initialStudentList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}