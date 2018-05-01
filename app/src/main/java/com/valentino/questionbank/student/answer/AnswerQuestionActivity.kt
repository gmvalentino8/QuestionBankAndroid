package com.valentino.questionbank.student.answer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.utilities.prefs
import com.valentino.questionbank.utilities.session
import com.valentino.questionbank.utilities.MODE_PARAM
import com.valentino.questionbank.utilities.COURSE_PARAM
import com.valentino.questionbank.utilities.FOLDER_PARAM
import com.valentino.questionbank.utilities.QUESTION_PARAM
import com.valentino.questionbank.utilities.SELECTED_ANSWER_PARAM
import com.valentino.questionbank.utilities.RATIONALE_PARAM
import com.valentino.questionbank.utilities.RATING_PARAM
import com.valentino.questionbank.utilities.FINAL_ANSWER_PARAM

class AnswerQuestionActivity : AppCompatActivity() {

    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_question)
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
            ApiService.getAnswer(prefs(this).session, it) {
                val summaryFragment = SummaryFragment()
                val args = Bundle()
                args.putString(MODE_PARAM, "summary")
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
        args.putParcelable(COURSE_PARAM, course)
        args.putParcelable(FOLDER_PARAM, folder)
        args.putParcelable(QUESTION_PARAM, question)
        initialAnswerFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .add(R.id.content, initialAnswerFragment)
                .commit()
    }
}
