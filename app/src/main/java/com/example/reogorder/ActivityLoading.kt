package com.example.reogorder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reogorder.admin.ActivityUtamaAdmin
import com.example.reogorder.customer.ActivityUtama

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
                    if(SP.getString("role", "") == "user")
                        intent =    Intent(applicationContext, ActivityUtama::class.java)
                    else if(SP.getString("role", "") == "admin")
                        intent =    Intent(applicationContext, ActivityUtamaAdmin::class.java)
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