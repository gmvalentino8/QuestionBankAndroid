package com.valentino.questionbank.instructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Course
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.activity_add_class.*

private const val SUCCESS_CODE = 1

class AddClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)
        submitButton.setOnClickListener({
            if (validate()) {
                val newCourse = Course(nameTextView.text.toString(),
                        codeTextView.text.toString(),
                        termTextView.text.toString())
                ApiService.postCourse(defaultPrefs(this).session, newCourse) {
                    finish()
                }
            }
        })
    }

    private fun validate() : Boolean {
        return (nameTextView.text.isNotBlank()
                && codeTextView.text.isNotBlank()
                && termTextView.text.isNotBlank())
    }
}
