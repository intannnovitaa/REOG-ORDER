package com.example.reogorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ActivityLogin : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var textRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        textRegister = findViewById(R.id.textRegister)

        btnLogin.setOnClickListener {
            val intent = Intent(this, ActivityUtama::class.java)
            startActivity(intent)
            finish()
        }
        textRegister.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, ActivityWelcome::class.java)
        startActivity(intent)
        finish()
    }
}