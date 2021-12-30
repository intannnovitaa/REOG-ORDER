package com.example.reogorder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.model.user
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
        val vm = this

        btnLogin.setOnClickListener {
            Log.d("Email", textEmail.text.toString())
            FirebaseDatabase.getInstance().getReference("user").orderByChild("email").equalTo(textEmail.text.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()){
                        for (h in p0.children){
                            val us = h.getValue(user::class.java)
                            if(us!!.password.equals(textPassword.text.toString())){
                                val editor = SP.edit()
                                editor.putString("role", "user")
                                editor.putString("status", "login")
                                editor.putInt("id", us.id)
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
                                Toast.makeText(vm, "Password salah", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(vm, "Email salah", Toast.LENGTH_SHORT).show()
                    }
                }
            })

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
