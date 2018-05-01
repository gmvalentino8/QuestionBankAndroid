package com.valentino.questionbank.instructor.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.shared.questions.QuestionsAdapter
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.fragment_select_question.view.*

class SelectQuestionFragment : Fragment() {

    private lateinit var name: String
    private lateinit var tags: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: QuestionsAdapter
    var questionData = arrayListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = QuestionsAdapter(questionData)
        arguments?.let {
            name = it.getString(NAME_PARAM)
            tags = it.getString(TAG_PARAM)
        }

        ApiService.filterQuestions(prefs(context!!).session, name, tags) {
            questionData = ArrayList(it)
            adapter.addItems(questionData)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_question, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.questionsRecyclerView.layoutManager = linearLayoutManager
        view.questionsRecyclerView.adapter = adapter
        view.questionsRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(context!!, questionData[position].content, Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }


}
