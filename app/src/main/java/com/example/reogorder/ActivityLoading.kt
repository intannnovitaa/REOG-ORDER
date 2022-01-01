package com.example.reogorder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityLoading : AppCompatActivity() {
    lateinit var SP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)

        val backgrond = object : Thread(){
            override fun run() {
                try {
                    sleep(2500)
                    var intent = Intent(applicationContext, ActivityWelcome::class.java)
                    if(SP.getString("role", "") == "user"){
                        intent =    Intent(applicationContext, ActivityUtama::class.java)
                    }
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