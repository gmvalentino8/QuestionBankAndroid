package com.valentino.questionbank.shared.folders

import android.app.Activity
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
import kotlinx.android.synthetic.main.fragment_folders.view.*
import android.util.Log
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.instructor.AddFolderActivity
import com.valentino.questionbank.utilities.*

class FoldersFragment : Fragment() {
    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FoldersAdapter
    var foldersData = arrayListOf<Folder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable<Course>(COURSE_PARAM)!!
        adapter = FoldersAdapter(foldersData)
        loadData()
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
                if (resultCode == Activity.RESULT_OK) {
                    loadData()
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun loadData() {
        ApiService.getFolders(prefs(context!!).session, course.cid!!) {
            foldersData = ArrayList(it)
            adapter.addItems(foldersData)
        }
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
