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
import com.valentino.questionbank.model.Rating
import kotlinx.android.synthetic.main.fragment_summary.view.*

private const val MODE_PARAM = "mode"
private const val COURSE_PARAM = "course"
private const val FOLDER_PARAM = "folder"
private const val QUESTION_PARAM = "question"
private const val SELECTED_ANSWER_PARAM = "selected_answer"
private const val RATIONALE_PARAM = "rationale"
private const val RATING_PARAM = "rating"
private const val FINAL_ANSWER_PARAM = "final_answer"

class SummaryFragment : Fragment() {

    private lateinit var mode : String
    private lateinit var course : Course
    private lateinit var folder: Folder
    private lateinit var question: Question
    private var selectedAnswer = -1
    private lateinit var rationale: String
    private lateinit var rating: Rating
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
        rootView.ratingTwoSeekBar.setProgress(rating.rating2)
        rootView.ratingThreeSeekBar.setProgress(rating.rating3)
        rootView.ratingFourSeekBar.setProgress(rating.rating4)

        return rootView
    }

}
