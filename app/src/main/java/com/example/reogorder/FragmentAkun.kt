package com.example.reogorder

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentAkun : Fragment() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var SP: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        SP = view.context.getSharedPreferences("Login", Context.MODE_PRIVATE)
        alertDialog = AlertDialog.Builder(view.context)
        val logout = view.findViewById<Button>(R.id.btnLogout)
        logout.setOnClickListener {
            alertDialog.setTitle("Logout")
            alertDialog.setMessage("Apakah anda mau keluar dari akun ini ?")
                .setCancelable(false)
                .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        val editor = SP.edit()
                        editor.putString("status","")
                        editor.apply()

                        val intent = Intent(view.context, ActivityWelcome::class.java)
                        startActivity(intent)
                    }
                })

                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.cancel()
                    }
                }).create().show()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}