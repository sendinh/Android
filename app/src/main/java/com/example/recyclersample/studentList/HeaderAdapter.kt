
package com.example.recyclersample.studentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R


class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var studentCount: Int = 0


    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val studentNumberTextView: TextView = itemView.findViewById(R.id.student_number_text)

        fun bind(studentCount: Int) {
            studentNumberTextView.text = studentCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }


    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(studentCount)
    }


    override fun getItemCount(): Int {
        return 1
    }


    fun updateStudentCount(updatedStudentCount: Int) {
        studentCount = updatedStudentCount
        notifyDataSetChanged()
    }
}