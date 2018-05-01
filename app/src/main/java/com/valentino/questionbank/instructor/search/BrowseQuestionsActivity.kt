package com.valentino.questionbank.instructor.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R

class BrowseQuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_question)
        loadSearchQuestionsFragment()
    }


    private fun loadSearchQuestionsFragment() {
        val searchQuestionsFragment = SearchQuestionsFragment()
        val args = Bundle()
        searchQuestionsFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .add(R.id.content, searchQuestionsFragment)
                .commit()
    }
}
