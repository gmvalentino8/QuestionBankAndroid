package com.valentino.questionbank.student.answer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session

private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"
private const val QUESTION_PARAM = "question"
private const val SELECTED_ANSWER_PARAM = "selected_answer"
private const val RATIONALE_PARAM = "rationale"
private const val RATING_PARAM = "rating"
private const val FINAL_ANSWER_PARAM = "final_answer"

class AnswerQuestionActivity : AppCompatActivity() {

    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_question)
        mode = intent.getStringExtra(MODE_PARAM)!!
        course = intent.getParcelableExtra(COURSE_PARAM)!!
        folder = intent.getParcelableExtra(FOLDER_PARAM)!!
        question = intent.getParcelableExtra(QUESTION_PARAM)!!
        if (question.solved) {
            loadAnswerSummaryFragment()
        }
        else {
            loadInitialAnswerFragment()
        }
    }

    private fun loadAnswerSummaryFragment() {
        question.qid?.let {
            ApiService.getAnswer(defaultPrefs(this).session, it) {
                val summaryFragment = SummaryFragment()
                val args = Bundle()
                args.putString(MODE_PARAM, mode)
                args.putParcelable(COURSE_PARAM, course)
                args.putParcelable(FOLDER_PARAM, folder)
                args.putParcelable(QUESTION_PARAM, question)
                args.putInt(SELECTED_ANSWER_PARAM, it.initialAnswer)
                args.putString(RATIONALE_PARAM, it.rationale)
                args.putParcelable(RATING_PARAM, it.rating)
                args.putInt(FINAL_ANSWER_PARAM, it.finalAnswer)
                summaryFragment.arguments = args
                supportFragmentManager.beginTransaction()
                        .add(R.id.content, summaryFragment)
                        .commit()
            }
        }
    }

    private fun loadInitialAnswerFragment() {
        val initialAnswerFragment = InitialAnswerFragment()
        val args = Bundle()
        args.putString(MODE_PARAM, mode)
        args.putParcelable(COURSE_PARAM, course)
        args.putParcelable(FOLDER_PARAM, folder)
        args.putParcelable(QUESTION_PARAM, question)
        initialAnswerFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .add(R.id.content, initialAnswerFragment)
                .commit()
    }
}
