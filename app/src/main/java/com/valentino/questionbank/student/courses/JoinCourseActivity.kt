package com.valentino.questionbank.student.courses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.valentino.questionbank.R

class JoinCourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_course)
        loadSearchCoursesFragment()
    }

    private fun loadSearchCoursesFragment() {
        val searchCoursesFragment = SearchCoursesFragment()
        val args = Bundle()
        searchCoursesFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .add(R.id.content, searchCoursesFragment)
                .commit()
    }

}
