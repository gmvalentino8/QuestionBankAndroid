package com.valentino.questionbank.student.answer

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.*
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.android.synthetic.main.fragment_summary.view.*

class SummaryFragment : Fragment() {
    private lateinit var mode: String
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private var selectedAnswer = -1
    private lateinit var rationale: String
    private lateinit var rating: Rating
    private var finalAnswer = -1
    private lateinit var rootView: View
    private var disableSeekbar = View.OnTouchListener { _, _ -> true }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
        question = arguments?.getParcelable(QUESTION_PARAM)!!
        selectedAnswer = arguments?.getInt(SELECTED_ANSWER_PARAM)!!
        rationale = arguments?.getString(RATIONALE_PARAM)?:""
        rating = arguments?.getParcelable(RATING_PARAM)!!
        finalAnswer = arguments?.getInt(FINAL_ANSWER_PARAM)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_summary, container, false)
        rootView.questionTextView.text = question.content
        rootView.initialAnswerTextView.text = question.answers[selectedAnswer].content
        rootView.rationaleTextView.text = rationale
        rootView.finalAnswerTextView.text = question.answers[finalAnswer].content
        rootView.correctAnswerTextView.text = question.answers[question.correct].content
        rootView.ratingOneSeekBar.setProgress(rating.rating1)
        rootView.ratingOneSeekBar.setOnTouchListener(disableSeekbar)
        rootView.ratingTwoSeekBar.setProgress(rating.rating2)
        rootView.ratingTwoSeekBar.setOnTouchListener(disableSeekbar)
        rootView.ratingThreeSeekBar.setProgress(rating.rating3)
        rootView.ratingThreeSeekBar.setOnTouchListener(disableSeekbar)
        rootView.ratingFourSeekBar.setProgress(rating.rating4)
        rootView.ratingFourSeekBar.setOnTouchListener(disableSeekbar)

        if (mode == "answer") {
            val studentAnswer = StudentAnswer(question.qid!!, rationale, rating, selectedAnswer, finalAnswer)
            ApiService.postAnswer(prefs(context!!).session, studentAnswer) {
                Log.d("Summary Fragment", "Answer Posted")
                activity?.setResult(RESULT_OK)
            }
        }
        rootView.submitButton.setOnClickListener({
            Log.d("Summary Fragment", "Finish Clicked")
            activity?.finish()
        })

        return rootView
    }

}
