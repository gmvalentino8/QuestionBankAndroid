package com.valentino.questionbank.shared.courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.valentino.questionbank.R
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.shared.folders.FoldersFragment
import com.valentino.questionbank.utilities.OnItemClickListener
import com.valentino.questionbank.utilities.addOnItemClickListener
import kotlinx.android.synthetic.main.fragment_courses.view.*

private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"

class CoursesFragment : Fragment() {
    private lateinit var mode: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CoursesAdapter
    var courseData = arrayListOf(
        Course(1, "Intro to Computer Science", "CS125", "Spring 2018"),
        Course(2, "Data Structures", "CS225", "Summer 2018"),
        Course(3, "Computer Architecture", "CS233", "Fall 2017")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        // TODO: Database call for course data
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.coursesRecyclerView.layoutManager = linearLayoutManager
        adapter = CoursesAdapter(courseData)
        view.coursesRecyclerView.adapter = adapter
        
        view.coursesRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val foldersFragment = FoldersFragment()
                val args = Bundle()
                args.putString(MODE_PARAM, mode)
                args.putParcelable(COURSE_PARAM, courseData.get(position))
                foldersFragment.arguments = args
                fragmentManager?.beginTransaction()
                        ?.replace(R.id.content, foldersFragment)
                        ?.addToBackStack("courses")
                        ?.commit()
            }
        })

        when (mode) {
            "instructor" -> {
                initInstructorViews(view)
            }
            "student" -> {
                initStudentViews(view)
            }
        }
        return view
    }

    fun initInstructorViews(view: View) {
        view.fab.visibility = View.VISIBLE
        view.fab.setOnClickListener({
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
        })
    }

    fun initStudentViews(view: View) {
        view.fab.visibility = View.GONE
    }

}