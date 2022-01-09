package com.example.reogorder.customer

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.reogorder.R
import com.example.reogorder.model.Detail
import com.example.reogorder.model.Pesanan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class ActivityCheckout : AppCompatActivity() {
    lateinit var databasePesanan: DatabaseReference
    lateinit var SP: SharedPreferences
    lateinit var namaCo: TextView
    lateinit var nohpCo: TextView
    lateinit var namaSanggarCo: TextView
    lateinit var alamatSanggarCo: TextView
    lateinit var waktuCo: TextView
    lateinit var tanggalCo: TextView
    lateinit var lokasiCo: EditText
    lateinit var totalBiayaCo: TextView
    lateinit var btnCheckout: Button

    lateinit var jumlahBarongCo: TextView
    lateinit var namaBarongCo: TextView
    lateinit var hargaBarongCo: TextView
    lateinit var jumlahJathilCo: TextView
    lateinit var namaJathilCo: TextView
    lateinit var hargaJathilCo: TextView
    lateinit var jumlahKlonosewandonoCo: TextView
    lateinit var namaKlonosewandonoCo: TextView
    lateinit var hargaKlonosewandonoCo: TextView
    lateinit var jumlahBujangCo: TextView
    lateinit var namaBujangCo: TextView
    lateinit var hargaBujangCo: TextView
    lateinit var jumlahWarogCo: TextView
    lateinit var namaWarogCo: TextView
    lateinit var hargaWarogCo: TextView
    lateinit var jumlahGendangCo: TextView
    lateinit var namaGendangCo: TextView
    lateinit var hargaGendangCo: TextView
    lateinit var jumlahKetipungCo: TextView
    lateinit var namaKetipungCo: TextView
    lateinit var hargaKetipungCo: TextView
    lateinit var jumlahSlompretCo: TextView
    lateinit var namaSlompretCo: TextView
    lateinit var hargaSlompretCo: TextView
    lateinit var jumlahKenongCo: TextView
    lateinit var namaKenongCo: TextView
    lateinit var hargaKenongCo: TextView
    lateinit var jumlahGongCo: TextView
    lateinit var namaGongCo: TextView
    lateinit var hargaGongCo: TextView
    lateinit var jumlahAngklungCo: TextView
    lateinit var namaAngklungCo: TextView
    lateinit var hargaAngklungCo: TextView

    var hasilBarong = 0
    var hasilJathil = 0
    var hasilKlonosewandono = 0
    var hasilBujang = 0
    var hasilWarog = 0
    var hasilGendang = 0
    var hasilKetipung = 0
    var hasilSlompret = 0
    var hasilKenong = 0
    var hasilGong = 0
    var hasilAngklung = 0

    lateinit var layoutBarongCo: RelativeLayout
    lateinit var layoutJathilCo: RelativeLayout
    lateinit var layoutKlonosewandonoCo: RelativeLayout
    lateinit var layoutBujangCo: RelativeLayout
    lateinit var layoutWarogCo: RelativeLayout
    lateinit var layoutGendangCo: RelativeLayout
    lateinit var layoutKetipungCo: RelativeLayout
    lateinit var layoutSlompretCo: RelativeLayout
    lateinit var layoutKenongCo: RelativeLayout
    lateinit var layoutGongCo: RelativeLayout
    lateinit var layoutAngklungCo: RelativeLayout

    @SuppressLint("NewApi")
    var formateDate = SimpleDateFormat("dd MMM YYYY")
    val date = Calendar.getInstance()
    var formateTime = SimpleDateFormat("hh:mm aa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        namaCo = findViewById(R.id.namaCo)
        nohpCo = findViewById(R.id.nohpCo)
        namaSanggarCo = findViewById(R.id.namaSanggarCo)
        alamatSanggarCo = findViewById(R.id.alamatSanggarCo)
        waktuCo = findViewById(R.id.waktuCo)
        tanggalCo = findViewById(R.id.tanggalCo)
        lokasiCo = findViewById(R.id.lokasiCo)
        totalBiayaCo = findViewById(R.id.totalBiayaCo)
        btnCheckout = findViewById(R.id.btnCheckout)

        jumlahBarongCo = findViewById(R.id.jumlahBarongCo)
        namaBarongCo = findViewById(R.id.namaBarongCo)
        hargaBarongCo = findViewById(R.id.hargaBarongCo)
        jumlahJathilCo = findViewById(R.id.jumlahJathilCo)
        namaJathilCo = findViewById(R.id.namaJathilCo)
        hargaJathilCo = findViewById(R.id.hargaJathilCo)
        jumlahKlonosewandonoCo = findViewById(R.id.jumlahKlonosewandonoCo)
        namaKlonosewandonoCo = findViewById(R.id.namaKlonosewandonoCo)
        hargaKlonosewandonoCo = findViewById(R.id.hargaKlonosewandonoCo)
        jumlahBujangCo = findViewById(R.id.jumlahBujangCo)
        namaBujangCo = findViewById(R.id.namaBujangCo)
        hargaBujangCo = findViewById(R.id.hargaBujangCo)
        jumlahWarogCo = findViewById(R.id.jumlahWarogCo)
        namaWarogCo = findViewById(R.id.namaWarogCo)
        hargaWarogCo = findViewById(R.id.hargaWarogCo)
        jumlahGendangCo = findViewById(R.id.jumlahGendangCo)
        namaGendangCo = findViewById(R.id.namaGendangCo)
        hargaGendangCo = findViewById(R.id.hargaGendangCo)
        jumlahKetipungCo = findViewById(R.id.jumlahKetipungCo)
        namaKetipungCo = findViewById(R.id.namaKetipungCo)
        hargaKetipungCo = findViewById(R.id.hargaKetipungCo)
        jumlahSlompretCo = findViewById(R.id.jumlahSlompretCo)
        namaSlompretCo = findViewById(R.id.namaSlompretCo)
        hargaSlompretCo = findViewById(R.id.hargaSlompretCo)
        jumlahKenongCo = findViewById(R.id.jumlahKenongCo)
        namaKenongCo = findViewById(R.id.namaKenongCo)
        hargaKenongCo = findViewById(R.id.hargaKenongCo)
        jumlahGongCo = findViewById(R.id.jumlahGongCo)
        namaGongCo = findViewById(R.id.namaGongCo)
        hargaGongCo = findViewById(R.id.hargaGongCo)
        jumlahAngklungCo = findViewById(R.id.jumlahAngklungCo)
        namaAngklungCo = findViewById(R.id.namaAngklungCo)
        hargaAngklungCo = findViewById(R.id.hargaAngklungCo)

        layoutBarongCo = findViewById(R.id.layoutBarongCo)
        layoutJathilCo = findViewById(R.id.layoutJathilCo)
        layoutKlonosewandonoCo = findViewById(R.id.layoutKlonosewandonoCo)
        layoutBujangCo = findViewById(R.id.layoutBujangCo)
        layoutWarogCo = findViewById(R.id.layoutWarogCo)
        layoutGendangCo = findViewById(R.id.layoutGendangCo)
        layoutKetipungCo = findViewById(R.id.layoutKetipungCo)
        layoutSlompretCo = findViewById(R.id.layoutSlompretCo)
        layoutKenongCo = findViewById(R.id.layoutKenongCo)
        layoutGongCo = findViewById(R.id.layoutGongCo)
        layoutAngklungCo = findViewById(R.id.layoutAngklungCo)

        loadData()

        waktuCo.setOnClickListener {
            val time = TimePickerDialog(this, {
                    view, hourOfDay, minute -> val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)

                waktuCo.text = formateTime.format(selectedTime.time)
            }, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), false)
            time.show()
        }

        tanggalCo.setOnClickListener {
            val date = DatePickerDialog(this, {
                    view, year, month, dayOfMonth -> val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                tanggalCo.text = formateDate.format(selectedDate.time)
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))
            date.show()
        }

        btnCheckout.setOnClickListener {
            if(addPesanan()) {
                Toast.makeText(this, "Pesanan dibuat",Toast.LENGTH_LONG).show()
                val intent = Intent(this, ActivityUtama::class.java)
                intent.putExtra("pesanan", "true")
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Lengkapi Data",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadData() {
        SP = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        namaCo.text = SP.getString("nama", "")
        nohpCo.text = SP.getString("nohp", "")

        if(intent.getStringExtra("idPesanan").toString() != ""){
            tanggalCo.text = intent.getStringExtra("tglPesanan").toString()
            waktuCo.text = intent.getStringExtra("wktPesanan").toString()
            lokasiCo.setText(intent.getStringExtra("tglPesanan").toString())
        }

        namaSanggarCo.text = intent.getStringExtra("sanggar").toString()
        alamatSanggarCo.text = intent.getStringExtra("alamat").toString()
        namaBarongCo.text = intent.getStringExtra("namaBarong").toString()
        hargaBarongCo.text = intent.getStringExtra("hargaBarong").toString()
        jumlahBarongCo.text = intent.getStringExtra("jumlahBarong").toString()
        namaJathilCo.text = intent.getStringExtra("namaJathil").toString()
        hargaJathilCo.text = intent.getStringExtra("hargaJathil").toString()
        jumlahJathilCo.text = intent.getStringExtra("jumlahJathil").toString()
        namaKlonosewandonoCo.text = intent.getStringExtra("namaKlonosewandono").toString()
        hargaKlonosewandonoCo.text = intent.getStringExtra("hargaKlonosewandono").toString()
        jumlahKlonosewandonoCo.text = intent.getStringExtra("jumlahKlonosewandono").toString()
        namaBujangCo.text = intent.getStringExtra("namaBujang").toString()
        hargaBujangCo.text = intent.getStringExtra("hargaBujang").toString()
        jumlahBujangCo.text = intent.getStringExtra("jumlahBujang").toString()
        namaWarogCo.text = intent.getStringExtra("namaWarog").toString()
        hargaWarogCo.text = intent.getStringExtra("hargaWarog").toString()
        jumlahWarogCo.text = intent.getStringExtra("jumlahWarog").toString()
        namaGendangCo.text = intent.getStringExtra("namaGendang").toString()
        hargaGendangCo.text = intent.getStringExtra("hargaGendang").toString()
        jumlahGendangCo.text = intent.getStringExtra("jumlahGendang").toString()
        namaKetipungCo.text = intent.getStringExtra("namaKetipung").toString()
        hargaKetipungCo.text = intent.getStringExtra("hargaKetipung").toString()
        jumlahKetipungCo.text = intent.getStringExtra("jumlahKetipung").toString()
        namaSlompretCo.text = intent.getStringExtra("namaSlompret").toString()
        hargaSlompretCo.text = intent.getStringExtra("hargaSlompret").toString()
        jumlahSlompretCo.text = intent.getStringExtra("jumlahSlompret").toString()
        namaKenongCo.text = intent.getStringExtra("namaKenong").toString()
        hargaKenongCo.text = intent.getStringExtra("hargaKenong").toString()
        jumlahKenongCo.text = intent.getStringExtra("jumlahKenong").toString()
        namaGongCo.text = intent.getStringExtra("namaGong").toString()
        hargaGongCo.text = intent.getStringExtra("hargaGong").toString()
        jumlahGongCo.text = intent.getStringExtra("jumlahGong").toString()
        namaAngklungCo.text = intent.getStringExtra("namaAngklung").toString()
        hargaAngklungCo.text = intent.getStringExtra("hargaAngklung").toString()
        jumlahAngklungCo.text = intent.getStringExtra("jumlahAngklung").toString()

        if(intent.getStringExtra("jumlahBarong").toString().toInt() == 0) {
            layoutBarongCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahJathil").toString().toInt() == 0) {
            layoutJathilCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahKlonosewandono").toString().toInt() == 0) {
            layoutKlonosewandonoCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahBujang").toString().toInt() == 0) {
            layoutBujangCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahWarog").toString().toInt() == 0) {
            layoutWarogCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahGendang").toString().toInt() == 0) {
            layoutGendangCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahKetipung").toString().toInt() == 0) {
            layoutKetipungCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahSlompret").toString().toInt() == 0) {
            layoutSlompretCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahKenong").toString().toInt() == 0) {
            layoutKenongCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahGong").toString().toInt() == 0) {
            layoutGongCo.visibility = View.GONE
        }
        if(intent.getStringExtra("jumlahAngklung").toString().toInt() == 0) {
            layoutAngklungCo.visibility = View.GONE
        }
        hasilBarong = intent.getStringExtra("hargaBarong")!!.toInt() * intent.getStringExtra("jumlahBarong")!!.toInt()
        hasilJathil = intent.getStringExtra("hargaJathil")!!.toInt() * intent.getStringExtra("jumlahJathil")!!.toInt()
        hasilKlonosewandono = intent.getStringExtra("hargaKlonosewandono")!!.toInt() * intent.getStringExtra("jumlahKlonosewandono")!!.toInt()
        hasilBujang = intent.getStringExtra("hargaBujang")!!.toInt() * intent.getStringExtra("jumlahBujang")!!.toInt()
        hasilWarog = intent.getStringExtra("hargaWarog")!!.toInt() * intent.getStringExtra("jumlahWarog")!!.toInt()
        hasilGendang = intent.getStringExtra("hargaGendang")!!.toInt() * intent.getStringExtra("jumlahGendang")!!.toInt()
        hasilKetipung = intent.getStringExtra("hargaKetipung")!!.toInt() * intent.getStringExtra("jumlahKetipung")!!.toInt()
        hasilSlompret = intent.getStringExtra("hargaSlompret")!!.toInt() * intent.getStringExtra("jumlahSlompret")!!.toInt()
        hasilKenong = intent.getStringExtra("hargaKenong")!!.toInt() * intent.getStringExtra("jumlahKenong")!!.toInt()
        hasilGong = intent.getStringExtra("hargaGong")!!.toInt() * intent.getStringExtra("jumlahGong")!!.toInt()
        hasilAngklung = intent.getStringExtra("hargaAngklung")!!.toInt() * intent.getStringExtra("jumlahAngklung")!!.toInt()

        totalBiayaCo.text = (hasilBarong + hasilJathil + hasilKlonosewandono + hasilBujang + hasilWarog + hasilGendang +
                hasilKetipung + hasilSlompret + hasilKenong + hasilGong + hasilAngklung).toString()
    }

    private fun addPesanan(): Boolean {
        SP = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val item = arrayListOf<Detail>()
        val id = SP.getString("id", "").toString().trim()
        val waktu = waktuCo.text.toString().trim()
        val tanggal = tanggalCo.text.toString().trim()
        val lokasi = lokasiCo.text.toString().trim()
        val total = totalBiayaCo.text.toString().trim()
        val status = "Diproses"

        val idSanggar = intent.getStringExtra("idSanggar").toString()
        val idItemBarong = intent.getStringExtra("idBarong").toString().trim()
        val jumlahBarong = jumlahBarongCo.text.toString().trim()
        val idItemJathil = intent.getStringExtra("idJathil").toString().trim()
        val jumlahJathil = jumlahJathilCo.text.toString().trim()
        val idItemKlonosewandono = intent.getStringExtra("idKlonosewandono").toString().trim()
        val jumlahKlonosewandono = jumlahKlonosewandonoCo.text.toString().trim()
        val idItemBujang = intent.getStringExtra("idBujang").toString().trim()
        val jumlahBujang = jumlahBujangCo.text.toString().trim()
        val idItemWarog = intent.getStringExtra("idWarog").toString().trim()
        val jumlahWarog = jumlahWarogCo.text.toString().trim()
        val idItemGendang = intent.getStringExtra("idGendang").toString().trim()
        val jumlahGendang = jumlahGendangCo.text.toString().trim()
        val idItemKetipung = intent.getStringExtra("idKetipung").toString().trim()
        val jumlahKetipung = jumlahKetipungCo.text.toString().trim()
        val idItemSlompret = intent.getStringExtra("idSlompret").toString().trim()
        val jumlahSlompret = jumlahSlompretCo.text.toString().trim()
        val idItemKenong = intent.getStringExtra("idKenong").toString().trim()
        val jumlahKenong = jumlahKenongCo.text.toString().trim()
        val idItemGong = intent.getStringExtra("idGong").toString().trim()
        val jumlahGong = jumlahGongCo.text.toString().trim()
        val idItemAngklung = intent.getStringExtra("idAngklung").toString().trim()
        val jumlahAngklung = jumlahAngklungCo.text.toString().trim()

        if(jumlahBarong.toInt() > 0)
            item.add(Detail(idItemBarong, jumlahBarong))
        if(jumlahJathil.toInt() > 0)
            item.add(Detail(idItemJathil, jumlahJathil))
        if(jumlahKlonosewandono.toInt() > 0)
            item.add(Detail(idItemKlonosewandono, jumlahKlonosewandono))
        if(jumlahBujang.toInt() > 0)
            item.add(Detail(idItemBujang, jumlahBujang))
        if(jumlahWarog.toInt() > 0)
            item.add(Detail(idItemWarog, jumlahWarog))
        if(jumlahGendang.toInt() > 0)
            item.add(Detail(idItemGendang, jumlahGendang))
        if(jumlahKetipung.toInt() > 0)
            item.add(Detail(idItemKetipung, jumlahKetipung))
        if(jumlahSlompret.toInt() > 0)
            item.add(Detail(idItemSlompret, jumlahSlompret))
        if(jumlahKenong.toInt() > 0)
            item.add(Detail(idItemKenong, jumlahKenong))
        if(jumlahGong.toInt() > 0)
            item.add(Detail(idItemGong, jumlahGong))
        if(jumlahAngklung.toInt() > 0)
            item.add(Detail(idItemAngklung, jumlahAngklung))

        databasePesanan = FirebaseDatabase.getInstance().getReference("pesanan")
        var idPesanan = databasePesanan.push().key.toString()
        if(intent.getStringExtra("idPesanan").toString() != "")
            idPesanan = intent.getStringExtra("idPesanan").toString()

        if(!TextUtils.isEmpty(waktu) && !TextUtils.isEmpty(tanggal) && !TextUtils.isEmpty(lokasi)) {
            val addPesanan = Pesanan(idPesanan, idSanggar, item, id, waktu, tanggal, lokasi, total, status, (id+'|'+status))
            databasePesanan.child(idPesanan).setValue(addPesanan)
            return true
        } else {
            return false
        }
    }
}