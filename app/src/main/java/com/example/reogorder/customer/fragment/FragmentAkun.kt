package com.example.reogorder.customer.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.reogorder.model.User
import com.example.reogorder.ActivityAbout
import com.example.reogorder.ActivityWelcome
import com.example.reogorder.R
import com.google.firebase.database.FirebaseDatabase

class FragmentAkun : Fragment() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var SP: SharedPreferences

    lateinit var nama: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var nohp: EditText
    lateinit var alamat: EditText
    lateinit var btnSimpan: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        val actionBar = requireActivity().findViewById(R.id.toolbarAkun) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(actionBar)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.bar_akun, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.about) {
            val intent = Intent(activity, ActivityAbout::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        SP = activity?.applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)

        nama        = activity?.findViewById(R.id.namaAkun)!!
        email       = activity?.findViewById(R.id.emailAkun)!!
        password    = activity?.findViewById(R.id.passwordAkun)!!
        nohp        = activity?.findViewById(R.id.nohpAkun)!!
        alamat      = activity?.findViewById(R.id.alamatAkun)!!
        btnSimpan   = activity?.findViewById(R.id.btnSimpan)!!

        nama.setText(SP.getString("nama", ""))
        email.setText(SP.getString("email", ""))
        password.setText(SP.getString("password", ""))
        nohp.setText(SP.getString("nohp", ""))
        alamat.setText(SP.getString("alamat", ""))

        // logout action
        alertDialog = AlertDialog.Builder(context)
        val logout = activity?.findViewById<Button>(R.id.btnLogout)
        logout!!.setOnClickListener {
            alertDialog.setTitle("Logout")
            alertDialog.setMessage("Apakah anda mau keluar dari akun ini ?")
                .setCancelable(false)
                .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        val editor = SP.edit()
                        editor.putString("role", "")
                        editor.putString("id", "")
                        editor.putString("nama", "")
                        editor.putString("email", "")
                        editor.putString("password", "")
                        editor.putString("nohp", "")
                        editor.putString("alamat", "")
                        editor.apply()

                        val intent = Intent(context, ActivityWelcome::class.java)
                        startActivity(intent)
                    }
                })

                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.cancel()
                    }
                }).create().show()
        }

        // save action
        btnSimpan.setOnClickListener {
            alertDialog.setTitle("Simpan data")
            alertDialog.setMessage("Yakin mengubah data anda ?")
                .setCancelable(false)
                .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        btnSimpan.isClickable = true
                        btnSimpan.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))

                        val user_id = SP.getString("id", "").toString()
                        val model = User(
                            user_id,
                            nama.text.toString(),
                            email.text.toString(),
                            password.text.toString(),
                            nohp.text.toString(),
                            alamat.text.toString(),
                            SP.getString("role", "").toString()
                        )

                        val ref = FirebaseDatabase.getInstance().getReference("user")
                        ref.child(user_id).setValue(model).addOnCompleteListener{
                            btnSimpan.isClickable = true
                            btnSimpan.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
                        }.addOnFailureListener {
                            Toast.makeText(context, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
                        }.addOnSuccessListener {
                            val editor = SP.edit()
                            editor.putString("nama", model.nama)
                            editor.putString("email", model.email)
                            editor.putString("password", model.password)
                            editor.putString("nohp", model.nohp)
                            editor.putString("alamat", model.alamat)
                            editor.apply()

                            Toast.makeText(context, "Berhasil memperbarui data", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.cancel()
                    }
                }).create().show()
        }
    }
}