package com.example.reogorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ActivityRegister : AppCompatActivity() {
    lateinit var btnRegister: Button
    lateinit var textLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btnRegister)
        textLogin = findViewById(R.id.textLogin)

        btnRegister.setOnClickListener {
            finish()
        }
        textLogin.setOnClickListener {
            finish()
        }
    }
}