package com.example.reogorder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ActivityLogin : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var textRegister: TextView
    lateinit var textEmail: EditText
    lateinit var textPassword: EditText

    lateinit var SP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin        = findViewById(R.id.btnLogin)
        textRegister    = findViewById(R.id.textRegister)
        textEmail       = findViewById(R.id.textEmail)
        textPassword    = findViewById(R.id.textPassword)

        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            if(validate()){
                login()
            }
        }
        textRegister.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }
    }

    private fun validate(): Boolean{
        if(textEmail.text.toString().equals("")){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if(textPassword.text.toString().equals("")){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun login(){
        val vm = this
        btnLogin.isClickable = false
        btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))

        FirebaseDatabase.getInstance().getReference("user").orderByChild("email").equalTo(textEmail.text.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children){
                        val us = h.getValue(User::class.java)
                        if(us!!.password.equals(textPassword.text.toString())){
                            val editor = SP.edit()
                            editor.putString("role", "user")
                            editor.putString("id", us.id)
                            editor.putString("nama", us.nama)
                            editor.putString("email", us.email)
                            editor.putString("password", us.password)
                            editor.putString("nohp", us.nohp)
                            editor.putString("alamat", us.alamat)
                            editor.apply()

                            val intent = Intent(vm, ActivityUtama::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            btnLogin.isClickable = true
                            btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
                            Toast.makeText(vm, "Password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    btnLogin.isClickable = true
                    btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
                    Toast.makeText(vm, "Email salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, ActivityWelcome::class.java)
        startActivity(intent)
        finish()
    }
}
