package com.valentino.questionbank.shared

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Login
import com.valentino.questionbank.model.Session
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token = prefs(this).session
        if (token != "" && prefs(this).user != "") {
            ApiService.sessionIsValid(token) {
                Log.d("Login Activity", it.toString())
                if (it) {
                    Log.d("Login Activity", "Go to Main")
                    goToMainActivity()
                }
            }
        }

        loginButton.setOnClickListener(this)
        studentRegisterButton.setOnClickListener(this)
        instructorRegisterButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            loginButton -> {
                val loginInfo = Login(emailEditText.text.toString(), passwordEditText.text.toString())
                ApiService.loginUser(loginInfo, ::login)
            }
            studentRegisterButton -> {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra(MODE_PARAM, MODE_STUDENT)
                startActivity(intent)
            }
            instructorRegisterButton -> {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra(MODE_PARAM, MODE_INSTRUCTOR)
                startActivity(intent)
            }
        }
    }

    private fun login(session: Session) {
        val prefs = prefs(this)
        prefs.session = session.uid.toString() + ":" + session.token
        prefs.user = session.uid.toString()
        goToMainActivity()
    }

    private fun goToMainActivity() {
        ApiService.getUser(prefs(this).session, prefs(this).user) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MODE_PARAM, it.type)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}

