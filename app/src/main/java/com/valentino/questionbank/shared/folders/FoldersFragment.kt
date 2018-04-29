package com.valentino.questionbank.shared.folders

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.shared.questions.QuestionsFragment
import com.valentino.questionbank.utilities.OnItemClickListener
import com.valentino.questionbank.utilities.addOnItemClickListener
import kotlinx.android.synthetic.main.fragment_folders.view.*
import android.util.Log
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.instructor.AddFolderActivity
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session

private const val SUCCESS_CODE = 1
private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"

class FoldersFragment : Fragment() {
    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FoldersAdapter
//    var foldersData = arrayListOf(
//            Folder(1,"Folder 1", "Description 1"),
//            Folder(2,"Folder 2", "Description 2"),
//            Folder(3,"Folder 3", "Description 3"),
//            Folder(4,"Folder 4", "Description 4")
//    )
    var foldersData = arrayListOf<Folder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable<Course>(COURSE_PARAM)!!
        adapter = FoldersAdapter(foldersData)
        ApiService.getFolders(defaultPrefs(context!!).session, course.cid!!) {
            foldersData = ArrayList(it)
            adapter.addItems(foldersData)
        }


    }

    override fun onResume() {
        super.onResume()
        activity?.title = course.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_folders, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.foldersRecyclerView.layoutManager = linearLayoutManager
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

        Log.d("FoldersFragment", "Mode: $mode")

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


    private fun initInstructorViews(view: View) {
        view.fab.visibility = View.VISIBLE
        view.fab.setOnClickListener({
            val intent = Intent(context, AddFolderActivity::class.java)
            intent.putExtra(COURSE_PARAM, course)
            startActivityForResult(intent, SUCCESS_CODE)
        })
    }

    private fun initStudentViews(view: View) {
        view.fab.visibility = View.GONE
    }


}
