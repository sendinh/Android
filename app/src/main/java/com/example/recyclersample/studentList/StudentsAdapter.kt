
package com.example.recyclersample.studentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R
import com.example.recyclersample.data.Student

class StudentsAdapter(private val onClick: (Student) -> Unit) :
    ListAdapter<Student, StudentsAdapter.StudentViewHolder>(StudentDiffCallback) {

    class StudentViewHolder(itemView: View, val onClick: (Student) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val studentTextView: TextView = itemView.findViewById(R.id.student_text)
        private val studentImageView: ImageView = itemView.findViewById(R.id.student_image)
        private var currentStudent: Student? = null

        init {
            itemView.setOnClickListener {
                currentStudent?.let {
                    onClick(it)
                }
            }
        }

        fun bind(student: Student) {
            currentStudent = student

            studentTextView.text = student.name
            if (student.image != null) {
                studentImageView.setImageResource(student.image)
            } else {
                studentImageView.setImageResource(R.drawable.rose)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.bind(student)

    }
}

object StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }
}