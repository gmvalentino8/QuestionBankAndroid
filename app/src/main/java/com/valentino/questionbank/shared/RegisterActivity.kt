package com.valentino.questionbank.shared

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import kotlinx.android.synthetic.main.activity_register.*

private const val MODE_PARAM = "mode"

class RegisterActivity : AppCompatActivity() {
    private lateinit var mode : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mode = intent.getStringExtra(MODE_PARAM)
        toolbar.title = "${mode.capitalize()} Register"
    }
}
