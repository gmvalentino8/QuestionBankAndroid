package com.valentino.questionbank.shared.courses

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.instructor.AddClassActivity
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.shared.folders.FoldersFragment
import com.valentino.questionbank.student.courses.JoinCourseActivity
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.fragment_courses.view.*


class CoursesFragment : Fragment() {
    private lateinit var mode: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CoursesAdapter
    var courseData = arrayListOf<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        adapter = CoursesAdapter(courseData)
        loadData()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Courses"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.coursesRecyclerView.layoutManager = linearLayoutManager
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
            MODE_INSTRUCTOR -> {
                initInstructorViews(view)
            }
            MODE_STUDENT -> {
                initStudentViews(view)
            }
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            SUCCESS_CODE -> {
                if (resultCode == RESULT_OK) {
                    loadData()
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun loadData() {
        ApiService.getCourses(prefs(context!!).session) {
            courseData = ArrayList(it)
            adapter.addItems(courseData)
        }
    }

    private fun initInstructorViews(view: View) {
        view.fab.setOnClickListener({
            val intent = Intent(context, AddClassActivity::class.java)
            startActivityForResult(intent, SUCCESS_CODE)
        })
    }

    private fun initStudentViews(view: View) {
        view.fab.setOnClickListener({
            val intent = Intent(context, JoinCourseActivity::class.java)
            startActivityForResult(intent, SUCCESS_CODE)
        })
    }

}
