
package com.example.recyclersample.data

import android.content.res.Resources
import com.example.recyclersample.R

fun studentList(resources: Resources): List<Student> {
    return listOf(
        Student(
            id = 1,
            name = resources.getString(R.string.student1_name),
            image = R.drawable.rose,
            description = resources.getString(R.string.student1_description)
        ),
        Student(
            id = 2,
            name = resources.getString(R.string.student2_name),
            image = R.drawable.freesia,
            description = resources.getString(R.string.student2_description)
        ),
        Student(
            id = 3,
            name = resources.getString(R.string.student3_name),
            image = R.drawable.lily,
            description = resources.getString(R.string.student3_description)
        ),

    )
}