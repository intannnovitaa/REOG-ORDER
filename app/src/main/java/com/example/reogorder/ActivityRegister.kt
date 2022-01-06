package com.example.reogorder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.reogorder.model.User
import com.example.reogorder.customer.ActivityUtama
import com.google.firebase.database.FirebaseDatabase

class ActivityRegister : AppCompatActivity() {
    lateinit var btnRegister: Button
    lateinit var textLogin: TextView
    lateinit var textNama: TextView
    lateinit var textEmail: TextView
    lateinit var textHp: TextView
    lateinit var textPassword: TextView
    lateinit var textAlamat: TextView

    lateinit var SP: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btnRegister)
        textLogin = findViewById(R.id.textLogin)
        textNama = findViewById(R.id.textUsername)
        textEmail = findViewById(R.id.textEmail)
        textHp = findViewById(R.id.textNohp)
        textPassword = findViewById(R.id.textPassword)
        textAlamat = findViewById(R.id.textAlamat)
        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)


        btnRegister.setOnClickListener {
            if(validate()){
                register()
            }
        }
        textLogin.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        if(textNama.text.toString() == ""){
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if(textEmail.text.toString() == ""){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if(textHp.text.toString() == ""){
            Toast.makeText(this, "Nomor Hp tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if(textPassword.text.toString() == ""){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if(textAlamat.text.toString() == ""){
            Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun register(){
        btnRegister.isEnabled = false
        btnRegister.isClickable = false
        btnRegister.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))

        val ref = FirebaseDatabase.getInstance().getReference("user")
        val id  = ref.push().key.toString()
        val model = User(id, textNama.text.toString(), textEmail.text.toString(), textPassword.text.toString(), textHp.text.toString(), textAlamat.text.toString(), "user")

        val vm = this
        ref.child(id).setValue(model).addOnCompleteListener {
            val editor = SP.edit()
            editor.putString("role", model.role)
            editor.putString("id", id)
            editor.putString("nama", textNama.text.toString())
            editor.putString("email", textEmail.text.toString())
            editor.putString("password", textPassword.text.toString())
            editor.putString("nohp", textHp.text.toString())
            editor.putString("alamat", textAlamat.text.toString())
            editor.apply()

            val intent = Intent(vm, ActivityUtama::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            btnRegister.isEnabled = true
            btnRegister.isClickable = true
            btnRegister.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))

            Toast.makeText(this, "Gagal Register", Toast.LENGTH_SHORT).show()
        }
    }
}