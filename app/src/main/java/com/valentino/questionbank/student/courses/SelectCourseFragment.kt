package com.valentino.questionbank.student.courses


import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.shared.courses.CoursesAdapter
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.fragment_select_course.view.*

class SelectCourseFragment : Fragment() {

    private lateinit var name: String
    private lateinit var code: String
    private lateinit var term: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CoursesAdapter
    var courseData = arrayListOf<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CoursesAdapter(courseData)
        arguments?.let {
            name = it.getString(NAME_PARAM)
            code = it.getString(CODE_PARAM)
            term = it.getString(TERM_PARAM)
        }

        ApiService.getUser(prefs(context!!).session, prefs(context!!).user) { user ->
            ApiService.filterCourses(prefs(context!!).session, user.schoolId, name, code, term) {
                courseData = ArrayList(it)
                adapter.addItems(courseData)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Join Course"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_course, container, false)

        linearLayoutManager = LinearLayoutManager(context)
        view.coursesRecyclerView.layoutManager = linearLayoutManager
        view.coursesRecyclerView.adapter = adapter
        view.coursesRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                ApiService.joinCourse(prefs(context!!).session, courseData[position]) {
                    activity?.setResult(RESULT_OK)
                    activity?.finish()
                }
            }
        })
        return view
    }
}
