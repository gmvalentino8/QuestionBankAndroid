package com.valentino.questionbank.instructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Analysis
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.utilities.QUESTION_PARAM
import com.valentino.questionbank.utilities.prefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.activity_question_overview.*

class QuestionOverviewActivity : AppCompatActivity() {

    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_overview)
        question = intent.getParcelableExtra(QUESTION_PARAM)
        questionTextView.text = question.content
        answerATextView.text = question.answers[0].content
        answerBTextView.text = question.answers[1].content
        answerCTextView.text = question.answers[2].content
        answerDTextView.text = question.answers[3].content
        when (question.correct) {
            0 -> {
                answerALayout.setBackgroundResource(R.drawable.card_view_border)
            }
            1 -> {
                answerBLayout.setBackgroundResource(R.drawable.card_view_border)
            }
            2 -> {
                answerCLayout.setBackgroundResource(R.drawable.card_view_border)
            }
            3 -> {
                answerDLayout.setBackgroundResource(R.drawable.card_view_border)
            }
        }
        ApiService.getAnalysis(prefs(this).session, question.qid!!) {analysis ->
            val percentages = analysis.answerCount.getAnswerPercentages()
            answerAPercentage.text = String.format("%.1f%% (%d)", percentages[0], analysis.answerCount.answer1)
            answerBPercentage.text = String.format("%.1f%% (%d)", percentages[1], analysis.answerCount.answer2)
            answerCPercentage.text = String.format("%.1f%% (%d)", percentages[2], analysis.answerCount.answer3)
            answerDPercentage.text = String.format("%.1f%% (%d)", percentages[3], analysis.answerCount.answer4)
            ratingOneSeekBar.progress = (analysis.averageRatings.rating1 * 10).toInt()
            ratingTwoSeekBar.progress = (analysis.averageRatings.rating2 * 10).toInt()
            ratingThreeSeekBar.progress = (analysis.averageRatings.rating3 * 10).toInt()
            ratingFourSeekBar.progress = (analysis.averageRatings.rating4 * 10).toInt()
            correctToWrongTextView.text = String.format("%.1f%% of students initially selected the right answer, but then changed to the wrong answer", analysis.switchCount.correctToWrong)
            wrongToCorrectTextView.text = String.format("%.1f%% of students initially selected the wrong answer, but then changed to the right answer", analysis.switchCount.wrongToCorrect)
        }
    }
}
