package com.example.reogorder.admin.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.reogorder.R
import com.example.reogorder.ActivityAbout
import com.example.reogorder.ActivityWelcome

class FragmentAkunAdmin : Fragment() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var SP: SharedPreferences
    lateinit var nama: TextView
    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var nohp: TextView
    lateinit var alamat: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_akun_admin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        val actionBar = requireActivity().findViewById(R.id.toolbarAkunAdmin) as Toolbar
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
        nama        = activity?.findViewById(R.id.namaAkunAdmin)!!
        email       = activity?.findViewById(R.id.emailAkunAdmin)!!
        password    = activity?.findViewById(R.id.passwordAkunAdmin)!!
        nohp        = activity?.findViewById(R.id.nohpAkunAdmin)!!
        alamat      = activity?.findViewById(R.id.alamatAkunAdmin)!!

        nama.text = SP.getString("nama", "")
        email.text = SP.getString("email", "")
        password.text = SP.getString("password", "")
        nohp.text = SP.getString("nohp", "")
        alamat.text = SP.getString("alamat", "")

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
                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.cancel()
                    }
                }).create().show()
        }
    }
}