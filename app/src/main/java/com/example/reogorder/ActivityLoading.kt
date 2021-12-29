package com.example.reogorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityLoading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val backgrond = object : Thread(){
            override fun run() {
                try {
                    sleep(2500)
                    val intent = Intent(applicationContext, ActivityWelcome::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        backgrond.start()
    }
}