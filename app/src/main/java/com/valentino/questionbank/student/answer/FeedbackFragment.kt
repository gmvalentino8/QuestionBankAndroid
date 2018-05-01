package com.valentino.questionbank.student.answer


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.model.*
import com.valentino.questionbank.utilities.MODE_PARAM
import com.valentino.questionbank.utilities.COURSE_PARAM
import com.valentino.questionbank.utilities.FOLDER_PARAM
import com.valentino.questionbank.utilities.QUESTION_PARAM
import com.valentino.questionbank.utilities.SELECTED_ANSWER_PARAM
import com.valentino.questionbank.utilities.RATIONALE_PARAM
import com.valentino.questionbank.utilities.RATING_PARAM

import kotlinx.android.synthetic.main.fragment_feedback.view.*

class FeedbackFragment : Fragment(), View.OnClickListener {
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private var selectedAnswer = -1
    private lateinit var rationale: String
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
        question = arguments?.getParcelable(QUESTION_PARAM)!!
        selectedAnswer = arguments?.getInt(SELECTED_ANSWER_PARAM)!!
        rationale = arguments?.getString(RATIONALE_PARAM)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_feedback, container, false)
        rootView.initialAnswerTextView.text = question.answers[selectedAnswer].content
        rootView.submitButton.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View?) {
        when(v) {
            rootView.submitButton -> {
                val alternativeAnswerFragment = AlternativeAnswerFragment()
                val args = Bundle()
                args.putParcelable(COURSE_PARAM, course)
                args.putParcelable(FOLDER_PARAM, folder)
                args.putParcelable(QUESTION_PARAM, question)
                args.putInt(SELECTED_ANSWER_PARAM, selectedAnswer)
                args.putString(RATIONALE_PARAM, rationale)
                val rating = Rating(rootView.ratingOneSeekBar.progress,
                        rootView.ratingTwoSeekBar.progress,
                        rootView.ratingThreeSeekBar.progress,
                        rootView.ratingFourSeekBar.progress)
                args.putParcelable(RATING_PARAM, rating)
                alternativeAnswerFragment.arguments = args
                fragmentManager?.beginTransaction()
                        ?.replace(R.id.content, alternativeAnswerFragment)
                        ?.commit()
            }
        }
    }

}
