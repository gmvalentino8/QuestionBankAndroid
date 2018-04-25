package Student

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.model.Folder
import com.valentino.questionbank.model.Question
import kotlinx.android.synthetic.main.fragment_summary.view.*

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

class SummaryFragment : Fragment() {

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
        rootView.ratingOneSeekBar.setProgress(rating1)
        rootView.ratingTwoSeekBar.setProgress(rating2)
        rootView.ratingThreeSeekBar.setProgress(rating3)
        rootView.ratingFourSeekBar.setProgress(rating4)

        return rootView
    }

}
