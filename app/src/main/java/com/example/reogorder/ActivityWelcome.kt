package com.example.reogorder

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.reogorder.admin.ActivityUtamaAdmin
import com.example.reogorder.customer.ActivityUtama

class ActivityWelcome : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var btnGuest: Button
    lateinit var btnLogin: Button
    lateinit var SP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)
        alertDialog = AlertDialog.Builder(this)
        btnGuest = findViewById(R.id.btnGuest)
        btnLogin = findViewById(R.id.btnLogin)

        btnGuest.setOnClickListener {
            val editor = SP.edit()
            editor.putString("role", "")
            editor.putString("id", "")
            editor.putString("nama", "Guest")
            editor.putString("email", "")
            editor.putString("password", "")
            editor.putString("nohp", "")
            editor.putString("alamat", "")
            editor.apply()

            val intent = Intent(this, ActivityUtama::class.java)
            startActivity(intent)
            finish()

//            val intent = Intent(this, ActivityUtamaAdmin::class.java)
//            startActivity(intent)
//            finish()
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