package com.example.reogorder

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class ActivityWelcome : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var btnGuest: Button
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        alertDialog = AlertDialog.Builder(this)
        btnGuest = findViewById(R.id.btnGuest)
        btnLogin = findViewById(R.id.btnLogin)

        btnGuest.setOnClickListener {
            val intent = Intent(this, ActivityUtama::class.java)
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        alertDialog.setTitle("Keluar Aplikasi")
        alertDialog.setMessage("Apakah anda ingin keluar aplikasi ?")
            .setCancelable(false)
            .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    finishAffinity()
                }
            })
            .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    dialog.cancel()
                }
            }).create().show()
    }
}