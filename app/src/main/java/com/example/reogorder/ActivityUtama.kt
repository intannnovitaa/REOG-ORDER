package com.example.reogorder

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityUtama : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var bottomNav: BottomNavigationView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.home -> {
                replaceFragment(FragmentBeranda())
                return@OnNavigationItemSelectedListener true
            }
            R.id.pesanan -> {
                replaceFragment(FragmentPesanan())
                return@OnNavigationItemSelectedListener true
            }
            R.id.akun -> {
                replaceFragment(FragmentAkun())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        alertDialog = AlertDialog.Builder(this)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(FragmentBeranda())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
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