package com.valentino.questionbank.shared.courses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valentino.questionbank.R
import com.valentino.questionbank.model.Course
import kotlinx.android.synthetic.main.item_course.view.*
import java.util.*

class CoursesAdapter(var courses: List<Course>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var courseData = courses.toMutableList()

    override fun getItemCount(): Int {
        return  courseData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = courseData[position]
        val placeHolder = holder as CourseViewHolder
        placeHolder.bindData(data)
    }

    fun addItems(items: List<Course>) {
        courseData.clear()
        courseData.addAll(items)
        notifyDataSetChanged()
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView

        fun bindData(course: Course) {
            view.nameTextView.text = course.name
            view.codeTextView.text = course.code
            view.termTextView.text = course.term
        }
    }
}