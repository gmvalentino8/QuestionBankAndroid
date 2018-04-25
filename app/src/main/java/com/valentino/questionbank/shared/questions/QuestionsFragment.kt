package com.valentino.questionbank.shared.questions


import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.valentino.questionbank.R
import com.valentino.questionbank.model.Answer
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.shared.courses.CoursesAdapter
import com.valentino.questionbank.shared.folders.FoldersAdapter
import com.valentino.questionbank.student.InitialAnswerFragment
import com.valentino.questionbank.utilities.OnItemClickListener
import com.valentino.questionbank.utilities.addOnItemClickListener
import kotlinx.android.synthetic.main.fragment_questions.view.*

private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"
private const val QUESTION_PARAM = "question"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionsFragment : Fragment() {
    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: QuestionsAdapter

    var questionsData = arrayListOf(
            Question(1, "This is question 1",
                    arrayListOf(
                        Answer(1, "This is answer 1.1"),
                        Answer(2, "This is answer 1.2"),
                        Answer(3, "This is answer 1.3"),
                        Answer(4, "This is answer 1.4")
                    ), 1, arrayListOf("Tag 1", "Tag 2", "Tag 3")
            ),
            Question(2, "This is question 2",
                    arrayListOf(
                        Answer(1, "This is answer 1.1"),
                        Answer(2, "This is answer 1.2"),
                        Answer(3, "This is answer 1.3"),
                        Answer(4, "This is answer 1.4")
                    ), 2, arrayListOf("Tag 1", "Tag 2", "Tag 3")
            ),
            Question(3, "This is question 3",
                    arrayListOf(
                        Answer(1, "This is answer 1.1"),
                        Answer(2, "This is answer 1.2"),
                        Answer(3, "This is answer 1.3"),
                        Answer(4, "This is answer 1.4")
                    ), 3, arrayListOf("Tag 1", "Tag 2", "Tag 3")
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_questions, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.questionsRecyclerView.layoutManager = linearLayoutManager
        adapter = QuestionsAdapter(questionsData)
        view.questionsRecyclerView.adapter = adapter
        view.questionsRecyclerView.addItemDecoration(DividerItemDecoration(context, HORIZONTAL))
        view.questionsRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val initialAnswerFragment = InitialAnswerFragment()
                val args = Bundle()
                args.putString(MODE_PARAM, mode)
                args.putParcelable(COURSE_PARAM, course)
                args.putParcelable(FOLDER_PARAM, folder)
                args.putParcelable(QUESTION_PARAM, questionsData[position])
                initialAnswerFragment.arguments = args
                fragmentManager?.beginTransaction()
                        ?.replace(R.id.content, initialAnswerFragment)
                        ?.commit()
            }

        })

        return view
    }


}
