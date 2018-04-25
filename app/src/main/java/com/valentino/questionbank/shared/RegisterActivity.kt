package com.valentino.questionbank.shared

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentino.questionbank.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var type : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        type = intent.getStringExtra("Type")
        toolbar.title = "$type Register"
    }
}
