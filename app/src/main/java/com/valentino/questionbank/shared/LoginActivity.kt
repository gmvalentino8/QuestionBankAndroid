package com.valentino.questionbank.shared

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.model.Login
import com.valentino.questionbank.model.Session
import com.valentino.questionbank.utilities.defaultPrefs
import com.valentino.questionbank.utilities.session
import kotlinx.android.synthetic.main.activity_login.*

private const val MODE_PARAM = "mode"


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token = defaultPrefs(this).session
        if (token != "") {
            ApiService.sessionIsValid(token) {
                if (it) {
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

    private fun login(session: Session) {
        val prefs = defaultPrefs(this)
        prefs.session = session.uid.toString() + ":" + session.token
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MODE_PARAM, "instructor")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

