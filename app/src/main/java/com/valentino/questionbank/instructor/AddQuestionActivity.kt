package com.valentino.questionbank.instructor

import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Answer
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import com.valentino.questionbank.utilities.FOLDER_PARAM
import com.valentino.questionbank.utilities.prefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.activity_add_question.*

class AddQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var folder: Folder
    private var selectedAnswer: Int = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)
        folder = intent.getParcelableExtra(FOLDER_PARAM)
        answerACardView.setOnClickListener(this)
        answerBCardView.setOnClickListener(this)
        answerCCardView.setOnClickListener(this)
        answerDCardView.setOnClickListener(this)
        submitButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            answerACardView -> {
                deselectPreviousAnswer()
                selectAnswer(0)
            }
            answerBCardView -> {
                deselectPreviousAnswer()
                selectAnswer(1)
            }
            answerCCardView -> {
                deselectPreviousAnswer()
                selectAnswer(2)
            }
            answerDCardView -> {
                deselectPreviousAnswer()
                selectAnswer(3)
            }
            submitButton -> {
                if (validate()) {
                    val answerArray = arrayListOf(
                            Answer(answerATextView.text.toString()),
                            Answer(answerBTextView.text.toString()),
                            Answer(answerCTextView.text.toString()),
                            Answer(answerDTextView.text.toString())
                    )
                    val tags = tagsTextView.text.toString().toLowerCase().split(", ")
                    val newQuestion = Question(folder.fid!!,
                            questionTextView.text.toString(),
                            answerArray, selectedAnswer, false, tags)
                    ApiService.postQuestion(prefs(this).session, newQuestion) {
                        setResult(RESULT_OK)
                        finish()
                    }
                }
            }
        }
    }

    private fun validate() : Boolean {
        return (questionTextView.text.isNotBlank()
                && answerATextView.text.isNotBlank()
                && answerBTextView.text.isNotBlank()
                && answerCTextView.text.isNotBlank()
                && answerDTextView.text.isNotBlank()
                && selectedAnswer != -1)
    }

    private fun deselectPreviousAnswer() {
        val cardBackground = getColor(R.color.white)
        val textColor = getColor(R.color.black)
        val labelTextColor = getColor(R.color.colorPrimary)

        when (selectedAnswer) {
            0 -> {
                answerACardView.setCardBackgroundColor(cardBackground)
                answerALabel.setTextColor(labelTextColor)
                answerATextView.setTextColor(textColor)
            }
            1 -> {
                answerBCardView.setCardBackgroundColor(cardBackground)
                answerBLabel.setTextColor(labelTextColor)
                answerBTextView.setTextColor(textColor)
            }
            2 -> {
                answerCCardView.setCardBackgroundColor(cardBackground)
                answerCLabel.setTextColor(labelTextColor)
                answerCTextView.setTextColor(textColor)
            }
            3 -> {
                answerDCardView.setCardBackgroundColor(cardBackground)
                answerDLabel.setTextColor(labelTextColor)
                answerDTextView.setTextColor(textColor)
            }
        }
    }

    private fun selectAnswer(index: Int) {
        val cardBackground = getColor(R.color.colorAccent)
        val textColor = getColor(R.color.white)
        when (index) {
            0 -> {
                answerACardView.setCardBackgroundColor(cardBackground)
                answerALabel.setTextColor(textColor)
                answerATextView.setTextColor(textColor)
                answerATextView.backgroundTintList = ColorStateList.valueOf(textColor)
            }
            1 -> {
                answerBCardView.setCardBackgroundColor(cardBackground)
                answerBLabel.setTextColor(textColor)
                answerBTextView.setTextColor(textColor)
            }
            2 -> {
                answerCCardView.setCardBackgroundColor(cardBackground)
                answerCLabel.setTextColor(textColor)
                answerCTextView.setTextColor(textColor)
            }
            3 -> {
                answerDCardView.setCardBackgroundColor(cardBackground)
                answerDLabel.setTextColor(textColor)
                answerDTextView.setTextColor(textColor)
            }
        }
        selectedAnswer = index
    }
}
