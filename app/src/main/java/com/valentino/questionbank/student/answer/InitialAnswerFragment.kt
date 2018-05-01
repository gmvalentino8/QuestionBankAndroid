package com.valentino.questionbank.student.answer


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
import com.valentino.questionbank.utilities.MODE_PARAM
import com.valentino.questionbank.utilities.COURSE_PARAM
import com.valentino.questionbank.utilities.FOLDER_PARAM
import com.valentino.questionbank.utilities.QUESTION_PARAM
import com.valentino.questionbank.utilities.SELECTED_ANSWER_PARAM
import com.valentino.questionbank.utilities.RATIONALE_PARAM

import kotlinx.android.synthetic.main.fragment_initial_answer.view.*

class InitialAnswerFragment : Fragment(), View.OnClickListener {
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private lateinit var answers: List<Answer>
    private lateinit var rootView: View
    private var selectedAnswer: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getParcelable(COURSE_PARAM)!!
        folder = arguments?.getParcelable(FOLDER_PARAM)!!
        question = arguments?.getParcelable(QUESTION_PARAM)!!
        answers = question.answers

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_initial_answer, container, false)

        rootView.questionTextView.text = question.content
        rootView.answerATextView.text = answers.get(0).content
        rootView.answerBTextView.text = answers.get(1).content
        rootView.answerCTextView.text = answers.get(2).content
        rootView.answerDTextView.text = answers.get(3).content
        rootView.answerACardView.setOnClickListener(this)
        rootView.answerBCardView.setOnClickListener(this)
        rootView.answerCCardView.setOnClickListener(this)
        rootView.answerDCardView.setOnClickListener(this)
        rootView.submitButton.setOnClickListener(this)

        return rootView
    }

    override fun onClick(v: View?) {
        when (v) {
            rootView.answerACardView -> {
                deselectPreviousAnswer()
                selectAnswer(0)
            }
            rootView.answerBCardView -> {
                deselectPreviousAnswer()
                selectAnswer(1)
            }
            rootView.answerCCardView -> {
                deselectPreviousAnswer()
                selectAnswer(2)
            }
            rootView.answerDCardView -> {
                deselectPreviousAnswer()
                selectAnswer(3)
            }
            rootView.submitButton -> {
                if (validate()) {
                    val feedbackFragment = FeedbackFragment()
                    val args = Bundle()
                    args.putParcelable(COURSE_PARAM, course)
                    args.putParcelable(FOLDER_PARAM, folder)
                    args.putParcelable(QUESTION_PARAM, question)
                    args.putInt(SELECTED_ANSWER_PARAM, selectedAnswer)
                    args.putString(RATIONALE_PARAM, rootView.rationaleEditText.text.toString())
                    feedbackFragment.arguments = args
                    fragmentManager?.beginTransaction()
                            ?.replace(R.id.content, feedbackFragment)
                            ?.commit()
                }
            }
        }
    }

    private fun deselectPreviousAnswer() {
        val cardBackground = context?.getColor(R.color.white)!!
        val textColor = context?.getColor(R.color.black)!!
        val labelTextColor = context?.getColor(R.color.colorPrimaryDark)!!

        when (selectedAnswer) {
            0 -> {
                rootView.answerACardView.setCardBackgroundColor(cardBackground)
                rootView.answerALabel.setTextColor(labelTextColor)
                rootView.answerATextView.setTextColor(textColor)
            }
            1 -> {
                rootView.answerBCardView.setCardBackgroundColor(cardBackground)
                rootView.answerBLabel.setTextColor(labelTextColor)
                rootView.answerBTextView.setTextColor(textColor)
            }
            2 -> {
                rootView.answerCCardView.setCardBackgroundColor(cardBackground)
                rootView.answerCLabel.setTextColor(labelTextColor)
                rootView.answerCTextView.setTextColor(textColor)
            }
            3 -> {
                rootView.answerDCardView.setCardBackgroundColor(cardBackground)
                rootView.answerDLabel.setTextColor(labelTextColor)
                rootView.answerDTextView.setTextColor(textColor)
            }
        }
    }

    private fun selectAnswer(index: Int) {
        val cardBackground = context?.getColor(R.color.colorAccent)!!
        val textColor = context?.getColor(R.color.white)!!
        when (index) {
            0 -> {
                rootView.answerACardView.setCardBackgroundColor(cardBackground)
                rootView.answerALabel.setTextColor(textColor)
                rootView.answerATextView.setTextColor(textColor)
            }
            1 -> {
                rootView.answerBCardView.setCardBackgroundColor(cardBackground)
                rootView.answerBLabel.setTextColor(textColor)
                rootView.answerBTextView.setTextColor(textColor)
            }
            2 -> {
                rootView.answerCCardView.setCardBackgroundColor(cardBackground)
                rootView.answerCLabel.setTextColor(textColor)
                rootView.answerCTextView.setTextColor(textColor)
            }
            3 -> {
                rootView.answerDCardView.setCardBackgroundColor(cardBackground)
                rootView.answerDLabel.setTextColor(textColor)
                rootView.answerDTextView.setTextColor(textColor)
            }
        }
        selectedAnswer = index
    }

    private fun validate(): Boolean {
        return selectedAnswer != -1 && rootView.rationaleEditText.text.isNotEmpty()
    }

}
