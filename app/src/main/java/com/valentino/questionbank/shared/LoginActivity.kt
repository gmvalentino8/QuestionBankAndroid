package com.valentino.questionbank.shared

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.valentino.questionbank.R
import kotlinx.android.synthetic.main.activity_login.*

private const val MODE_PARAM = "mode"


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener(this)
        studentRegisterButton.setOnClickListener(this)
        instructorRegisterButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            loginButton -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MODE_PARAM, "student")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            studentRegisterButton -> {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra(MODE_PARAM, "student")
                startActivity(intent)
            }
            instructorRegisterButton -> {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra(MODE_PARAM, "instructor")
                startActivity(intent)
            }
        }
    }
}
