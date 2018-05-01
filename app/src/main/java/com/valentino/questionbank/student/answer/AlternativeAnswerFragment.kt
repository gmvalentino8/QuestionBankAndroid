package com.valentino.questionbank.student.answer


import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.*
import com.valentino.questionbank.shared.questions.RationalesAdapter
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.fragment_alternative_answer.view.*

class AlternativeAnswerFragment : Fragment(), View.OnClickListener {

    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private var selectedAnswer = -1
    private lateinit var rationale: String
    private lateinit var rating: Rating
    private var alternativeAnswer = -1
    private var finalAnswer = -1
    private lateinit var rootView: View
    private lateinit var initialLinearLayoutManager: LinearLayoutManager
    var initialRationales = arrayListOf<Rationale>()
    private lateinit var initialRationaleAdapter: RationalesAdapter
    private lateinit var alternativeLinearLayoutManager: LinearLayoutManager
    var alternativeRationales = arrayListOf<Rationale>()
    private lateinit var alternativeRationaleAdapter: RationalesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
        question = arguments?.getParcelable(QUESTION_PARAM)!!
        selectedAnswer = arguments?.getInt(SELECTED_ANSWER_PARAM)!!
        rationale = arguments?.getString(RATIONALE_PARAM)!!
        rating = arguments?.getParcelable(RATING_PARAM)!!
        alternativeAnswer = generateAlternativeAnswer()
        initialRationaleAdapter = RationalesAdapter(initialRationales)
        alternativeRationaleAdapter = RationalesAdapter(alternativeRationales)
        ApiService.getRationales(prefs(context!!).session, question.qid!!, course.cid!!) {
            for (item in it) {
                if (item.answer == selectedAnswer) {
                    initialRationaleAdapter.addItem(item)
                }
                if (item.answer == alternativeAnswer) {
                    alternativeRationaleAdapter.addItem(item)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_alternative_answer, container, false)
        initialLinearLayoutManager = LinearLayoutManager(context)
        alternativeLinearLayoutManager = LinearLayoutManager(context)
        val decorator = DividerItemDecoration(context, HORIZONTAL)
        rootView.initialRationalesRecyclerView.layoutManager = initialLinearLayoutManager
        rootView.initialRationalesRecyclerView.adapter = initialRationaleAdapter
        rootView.initialRationalesRecyclerView.addItemDecoration(decorator)
        rootView.alternativeRationalesRecyclerView.layoutManager = alternativeLinearLayoutManager
        rootView.alternativeRationalesRecyclerView.adapter = alternativeRationaleAdapter
        rootView.alternativeRationalesRecyclerView.addItemDecoration(decorator)
        rootView.initialAnswerTextView.text = question.answers[selectedAnswer].content
        rootView.alternativeAnswerTextView.setText(question.answers.get(alternativeAnswer).content)
        rootView.initialAnswerCardView.setOnClickListener(this)
        rootView.alternativeAnswerCardView.setOnClickListener(this)
        rootView.submitButton.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View?) {
        when(v) {
            rootView.initialAnswerCardView -> {
                selectAnswer(selectedAnswer)
            }
            rootView.alternativeAnswerCardView -> {
                selectAnswer(alternativeAnswer)
            }
            rootView.submitButton -> {
                if (validate()) {
                    val summaryFragment = SummaryFragment()
                    val args = Bundle()
                    args.putString(MODE_PARAM, "answer")
                    args.putParcelable(COURSE_PARAM, course)
                    args.putParcelable(FOLDER_PARAM, folder)
                    args.putParcelable(QUESTION_PARAM, question)
                    args.putInt(SELECTED_ANSWER_PARAM, selectedAnswer)
                    args.putString(RATIONALE_PARAM, rationale)
                    args.putParcelable(RATING_PARAM, rating)
                    args.putInt(FINAL_ANSWER_PARAM, finalAnswer)
                    summaryFragment.arguments = args
                    fragmentManager?.beginTransaction()
                            ?.replace(R.id.content, summaryFragment)
                            ?.commit()
                }
            }
        }
    }

    private fun generateAlternativeAnswer(): Int {
        if (selectedAnswer != question.correct) {
            return question.correct
        }
        else {
            var alternative = selectedAnswer
            while (alternative == selectedAnswer) {
                alternative = (Math.random() * 4).toInt()
            }
            return alternative
        }
    }

    private fun selectAnswer(index: Int) {
        val selectCardBackground = context?.getColor(R.color.colorAccent)!!
        val selectTextColor = context?.getColor(R.color.white)!!
        val cardBackground = context?.getColor(R.color.white)!!
        val textColor = context?.getColor(R.color.black)!!
        val labelTextColor = context?.getColor(R.color.colorPrimaryDark)!!

        if (index == selectedAnswer) {
            rootView.initialAnswerCardView.setCardBackgroundColor(selectCardBackground)
            rootView.initialAnswerLabel.setTextColor(selectTextColor)
            rootView.initialAnswerTextView.setTextColor(selectTextColor)
            rootView.alternativeAnswerCardView.setCardBackgroundColor(cardBackground)
            rootView.alternativeAnswerLabel.setTextColor(labelTextColor)
            rootView.alternativeAnswerTextView.setTextColor(textColor)
            finalAnswer = selectedAnswer
        }
        else {
            rootView.alternativeAnswerCardView.setCardBackgroundColor(selectCardBackground)
            rootView.alternativeAnswerLabel.setTextColor(selectTextColor)
            rootView.alternativeAnswerTextView.setTextColor(selectTextColor)
            rootView.initialAnswerCardView.setCardBackgroundColor(cardBackground)
            rootView.initialAnswerLabel.setTextColor(labelTextColor)
            rootView.initialAnswerTextView.setTextColor(textColor)
            finalAnswer = alternativeAnswer
        }
    }

    fun validate(): Boolean {
        return finalAnswer != -1
    }
}
