package com.valentino.questionbank.shared.folders

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.valentino.questionbank.R
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.shared.questions.QuestionsFragment
import com.valentino.questionbank.utilities.OnItemClickListener
import com.valentino.questionbank.utilities.addOnItemClickListener
import kotlinx.android.synthetic.main.fragment_folders.view.*
import android.support.v7.widget.DividerItemDecoration



private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"

class FoldersFragment : Fragment() {
    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FoldersAdapter
    var foldersData = arrayListOf(
            Folder(1,"Folder 1", "Description 1"),
            Folder(2,"Folder 2", "Description 2"),
            Folder(3,"Folder 3", "Description 3"),
            Folder(4,"Folder 4", "Description 4")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable<Course>(COURSE_PARAM)!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_folders, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.foldersRecyclerView.layoutManager = linearLayoutManager
        adapter = FoldersAdapter(foldersData)
        view.foldersRecyclerView.adapter = adapter
        view.foldersRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val questionsFragment = QuestionsFragment()
                val args = Bundle()
                args.putString(MODE_PARAM, mode)
                args.putParcelable(COURSE_PARAM, course)
                args.putParcelable(FOLDER_PARAM, foldersData[position])
                questionsFragment.arguments = args
                fragmentManager?.beginTransaction()
                        ?.replace(R.id.content, questionsFragment)
                        ?.addToBackStack("folders")
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
