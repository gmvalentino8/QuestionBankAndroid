package com.valentino.questionbank.student


import Student.SummaryFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.model.Answer
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import kotlinx.android.synthetic.main.fragment_alternative_answer.view.*

private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"
private const val QUESTION_PARAM = "question"
private const val SELECTED_ANSWER_PARAM = "selected_answer"
private const val RATIONALE_PARAM = "rationale"
private const val RATING1_PARAM = "rating1"
private const val RATING2_PARAM = "rating2"
private const val RATING3_PARAM = "rating3"
private const val RATING4_PARAM = "rating4"
private const val FINAL_ANSWER_PARAM = "final_answer"

class AlternativeAnswerFragment : Fragment(), View.OnClickListener {

    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private var selectedAnswer = -1
    private lateinit var rationale: String
    private var rating1 = 0
    private var rating2 = 0
    private var rating3 = 0
    private var rating4 = 0
    private var alternativeAnswer = -1
    private var finalAnswer = -1
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mode = arguments?.getString(MODE_PARAM)!!
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
        question = arguments?.getParcelable(QUESTION_PARAM)!!
        selectedAnswer = arguments?.getInt(SELECTED_ANSWER_PARAM)!!
        rationale = arguments?.getString(RATIONALE_PARAM)!!
        rating1 = arguments?.getInt(RATING1_PARAM)!!
        rating2 = arguments?.getInt(RATING2_PARAM)!!
        rating3 = arguments?.getInt(RATING3_PARAM)!!
        rating4 = arguments?.getInt(RATING4_PARAM)!!
        alternativeAnswer = generateAlternativeAnswer()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_alternative_answer, container, false)
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
                    args.putString(MODE_PARAM, mode)
                    args.putParcelable(COURSE_PARAM, course)
                    args.putParcelable(FOLDER_PARAM, folder)
                    args.putParcelable(QUESTION_PARAM, question)
                    args.putInt(SELECTED_ANSWER_PARAM, selectedAnswer)
                    args.putString(RATIONALE_PARAM, rationale)
                    args.putInt(RATING1_PARAM, rating1)
                    args.putInt(RATING2_PARAM, rating2)
                    args.putInt(RATING3_PARAM, rating3)
                    args.putInt(RATING4_PARAM, rating4)
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
        val labelTextColor = context?.getColor(R.color.colorPrimary)!!

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