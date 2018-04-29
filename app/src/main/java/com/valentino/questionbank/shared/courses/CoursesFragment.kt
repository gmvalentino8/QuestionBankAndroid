package com.valentino.questionbank.shared.courses

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.instructor.AddClassActivity
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.shared.folders.FoldersFragment
import com.valentino.questionbank.utilities.OnItemClickListener
import com.valentino.questionbank.utilities.addOnItemClickListener
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.fragment_courses.view.*

private const val SUCCESS_CODE = 1
private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"

class CoursesFragment : Fragment() {
    private lateinit var mode: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CoursesAdapter
//    var courseData = arrayListOf(
//        Course(1, "Intro to Computer Science", "CS125", "Spring 2018"),
//        Course(2, "Data Structures", "CS225", "Summer 2018"),
//        Course(3, "Systems Programming", "CS241", "Fall 2018")
//    )
    var courseData = arrayListOf<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        adapter = CoursesAdapter(courseData)
        ApiService.getCourses(defaultPrefs(context!!).session) {
            courseData = ArrayList(it)
            adapter.addItems(courseData)
        }
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
            val intent = Intent(context, AddClassActivity::class.java)
            startActivityForResult(intent, SUCCESS_CODE)
        })
    }

    fun initStudentViews(view: View) {
        view.fab.visibility = View.GONE
    }

}
