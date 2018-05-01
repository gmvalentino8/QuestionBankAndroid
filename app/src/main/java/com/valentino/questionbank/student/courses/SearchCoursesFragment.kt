package com.valentino.questionbank.student.courses


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valentino.questionbank.R
import com.valentino.questionbank.utilities.CODE_PARAM
import com.valentino.questionbank.utilities.NAME_PARAM
import com.valentino.questionbank.utilities.TERM_PARAM
import kotlinx.android.synthetic.main.fragment_search_courses.view.*



class SearchCoursesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_courses, container, false)
        view.submitButton.setOnClickListener({
            val selectCourseFragment = SelectCourseFragment()
            val args = Bundle()
            args.putString(NAME_PARAM, view.nameTextView.text.toString())
            args.putString(CODE_PARAM, view.codeTextView.text.toString())
            args.putString(TERM_PARAM, view.termTextView.text.toString())
            selectCourseFragment.arguments = args
            fragmentManager?.beginTransaction()
                    ?.addToBackStack("searchCourse")
                    ?.replace(R.id.content, selectCourseFragment)
                    ?.commit()
        })

        return view
    }


}
