package com.example.reogorder

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.reogorder.R
import com.example.reogorder.admin.ActivityUtamaAdmin
import com.example.reogorder.customer.ActivityUtama
import com.example.reogorder.model.*
import com.google.firebase.database.*
import java.text.DecimalFormat
import java.text.NumberFormat

class ActivityDetail : AppCompatActivity() {
    lateinit var databaseDetail: DatabaseReference
    lateinit var namaDetail: TextView
    lateinit var nohpDetail: TextView
    lateinit var namaSanggarDetail: TextView
    lateinit var alamatSanggarDetail: TextView
    lateinit var waktuDetail: TextView
    lateinit var tanggalDetail: TextView
    lateinit var lokasiDetail: TextView
    lateinit var totalBiayaDetail: TextView
    lateinit var totalBiayaBarangDetail: TextView
    lateinit var totalBiayaPpnDetail: TextView
    lateinit var totalBiayaJarakDetail: TextView
    lateinit var btnSelesai: Button

    lateinit var jumlahBarongDetail: TextView
    lateinit var namaBarongDetail: TextView
    lateinit var hargaBarongDetail: TextView
    lateinit var jumlahJathilDetail: TextView
    lateinit var namaJathilDetail: TextView
    lateinit var hargaJathilDetail: TextView
    lateinit var jumlahKlonosewandonoDetail: TextView
    lateinit var namaKlonosewandonoDetail: TextView
    lateinit var hargaKlonosewandonoDetail: TextView
    lateinit var jumlahBujangDetail: TextView
    lateinit var namaBujangDetail: TextView
    lateinit var hargaBujangDetail: TextView
    lateinit var jumlahWarogDetail: TextView
    lateinit var namaWarogDetail: TextView
    lateinit var hargaWarogDetail: TextView
    lateinit var jumlahGendangDetail: TextView
    lateinit var namaGendangDetail: TextView
    lateinit var hargaGendangDetail: TextView
    lateinit var jumlahKetipungDetail: TextView
    lateinit var namaKetipungDetail: TextView
    lateinit var hargaKetipungDetail: TextView
    lateinit var jumlahSlompretDetail: TextView
    lateinit var namaSlompretDetail: TextView
    lateinit var hargaSlompretDetail: TextView
    lateinit var jumlahKenongDetail: TextView
    lateinit var namaKenongDetail: TextView
    lateinit var hargaKenongDetail: TextView
    lateinit var jumlahGongDetail: TextView
    lateinit var namaGongDetail: TextView
    lateinit var hargaGongDetail: TextView
    lateinit var jumlahAngklungDetail: TextView
    lateinit var namaAngklungDetail: TextView
    lateinit var hargaAngklungDetail: TextView

    lateinit var layoutBarongDetail: RelativeLayout
    lateinit var layoutJathilDetail: RelativeLayout
    lateinit var layoutKlonosewandonoDetail: RelativeLayout
    lateinit var layoutBujangDetail: RelativeLayout
    lateinit var layoutWarogDetail: RelativeLayout
    lateinit var layoutGendangDetail: RelativeLayout
    lateinit var layoutKetipungDetail: RelativeLayout
    lateinit var layoutSlompretDetail: RelativeLayout
    lateinit var layoutKenongDetail: RelativeLayout
    lateinit var layoutGongDetail: RelativeLayout
    lateinit var layoutAngklungDetail: RelativeLayout

    var id_user = ""
    var id_sanggar = ""
    var total_sewa = ""
    var pesananItem = arrayListOf<Detail>()
    var formatNumber: NumberFormat = DecimalFormat("#,###")
    lateinit var SP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        namaDetail = findViewById(R.id.namaDetail)
        nohpDetail = findViewById(R.id.nohpDetail)
        namaSanggarDetail = findViewById(R.id.namaSanggarDetail)
        alamatSanggarDetail = findViewById(R.id.alamatSanggarDetail)
        waktuDetail = findViewById(R.id.waktuDetail)
        tanggalDetail = findViewById(R.id.tanggalDetail)
        lokasiDetail = findViewById(R.id.lokasiDetail)
        totalBiayaDetail = findViewById(R.id.totalBiayaDetail)
        totalBiayaBarangDetail = findViewById(R.id.totalBiayaBarangDetail)
        totalBiayaJarakDetail = findViewById(R.id.totalBiayaJarakDetail)
        totalBiayaPpnDetail = findViewById(R.id.totalBiayaPpnDetail)
        btnSelesai = findViewById(R.id.btnSelesai)

        jumlahBarongDetail = findViewById(R.id.jumlahBarongDetail)
        namaBarongDetail = findViewById(R.id.namaBarongDetail)
        hargaBarongDetail = findViewById(R.id.hargaBarongDetail)
        jumlahJathilDetail = findViewById(R.id.jumlahJathilDetail)
        namaJathilDetail = findViewById(R.id.namaJathilDetail)
        hargaJathilDetail = findViewById(R.id.hargaJathilDetail)
        jumlahKlonosewandonoDetail = findViewById(R.id.jumlahKlonosewandonoDetail)
        namaKlonosewandonoDetail = findViewById(R.id.namaKlonosewandonoDetail)
        hargaKlonosewandonoDetail = findViewById(R.id.hargaKlonosewandonoDetail)
        jumlahBujangDetail = findViewById(R.id.jumlahBujangDetail)
        namaBujangDetail = findViewById(R.id.namaBujangDetail)
        hargaBujangDetail = findViewById(R.id.hargaBujangDetail)
        jumlahWarogDetail = findViewById(R.id.jumlahWarogDetail)
        namaWarogDetail = findViewById(R.id.namaWarogDetail)
        hargaWarogDetail = findViewById(R.id.hargaWarogDetail)
        jumlahGendangDetail = findViewById(R.id.jumlahGendangDetail)
        namaGendangDetail = findViewById(R.id.namaGendangDetail)
        hargaGendangDetail = findViewById(R.id.hargaGendangDetail)
        jumlahKetipungDetail = findViewById(R.id.jumlahKetipungDetail)
        namaKetipungDetail = findViewById(R.id.namaKetipungDetail)
        hargaKetipungDetail = findViewById(R.id.hargaKetipungDetail)
        jumlahSlompretDetail = findViewById(R.id.jumlahSlompretDetail)
        namaSlompretDetail = findViewById(R.id.namaSlompretDetail)
        hargaSlompretDetail = findViewById(R.id.hargaSlompretDetail)
        jumlahKenongDetail = findViewById(R.id.jumlahKenongDetail)
        namaKenongDetail = findViewById(R.id.namaKenongDetail)
        hargaKenongDetail = findViewById(R.id.hargaKenongDetail)
        jumlahGongDetail = findViewById(R.id.jumlahGongDetail)
        namaGongDetail = findViewById(R.id.namaGongDetail)
        hargaGongDetail = findViewById(R.id.hargaGongDetail)
        jumlahAngklungDetail = findViewById(R.id.jumlahAngklungDetail)
        namaAngklungDetail = findViewById(R.id.namaAngklungDetail)
        hargaAngklungDetail = findViewById(R.id.hargaAngklungDetail)

        layoutBarongDetail = findViewById(R.id.layoutBarongDetail)
        layoutJathilDetail = findViewById(R.id.layoutJathilDetail)
        layoutKlonosewandonoDetail = findViewById(R.id.layoutKlonosewandonoDetail)
        layoutBujangDetail = findViewById(R.id.layoutBujangDetail)
        layoutWarogDetail = findViewById(R.id.layoutWarogDetail)
        layoutGendangDetail = findViewById(R.id.layoutGendangDetail)
        layoutKetipungDetail = findViewById(R.id.layoutKetipungDetail)
        layoutSlompretDetail = findViewById(R.id.layoutSlompretDetail)
        layoutKenongDetail = findViewById(R.id.layoutKenongDetail)
        layoutGongDetail = findViewById(R.id.layoutGongDetail)
        layoutAngklungDetail = findViewById(R.id.layoutAngklungDetail)

        SP = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        if(SP.getString("role", "").equals("admin")) {
            btnSelesai.visibility = View.VISIBLE
        }

        databaseDetail = FirebaseDatabase.getInstance().reference
        val querySanggar = databaseDetail.child("pesanan").orderByKey().equalTo(intent.getStringExtra("id_pesanan").toString())
        querySanggar.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Pesanan::class.java)
                    id_user = allocation!!.id
                    id_sanggar = allocation.id_sanggar
                    waktuDetail.text = allocation.waktu
                    tanggalDetail.text = allocation.tanggal
                    lokasiDetail.text = allocation.lokasi
                    totalBiayaDetail.text = "Rp. " + formatNumber.format(allocation.total_bayar.toInt()) + ",00"
                    totalBiayaJarakDetail.text = "Rp. " + formatNumber.format(allocation.jarak.toInt()) + ",00"
                    totalBiayaBarangDetail.text = "Rp. " + formatNumber.format(allocation.barang.toInt()) + ",00"
                    totalBiayaPpnDetail.text = "Rp. " + formatNumber.format(allocation.ppn.toInt()) + ",00"
                    pesananItem = allocation.item

                    FirebaseDatabase.getInstance().getReference("user").orderByKey().equalTo(id_user).get().addOnSuccessListener {
                        for(h in it.children){
                            val valueuser = h.getValue(User::class.java)
                            namaDetail.text = valueuser!!.nama
                            nohpDetail.text = valueuser.nohp
                        }
                    }
                    FirebaseDatabase.getInstance().getReference("sanggar").orderByKey().equalTo(id_sanggar).get().addOnSuccessListener {
                        for (j in it.children){
                            val vl = j.getValue(Sanggar::class.java)
                            namaSanggarDetail.text = vl!!.nama_sanggar
                            alamatSanggarDetail.text = vl.alamat_sanggar
                            total_sewa = vl.total_sewa
                        }
                    }
                    FirebaseDatabase.getInstance().getReference("item").child(allocation.id_sanggar)
                        .addValueEventListener( object : ValueEventListener{
                            override fun onDataChange(p0: DataSnapshot) {
                                if (p0.exists()){
                                    for (n in p0.children){
                                        val value = n.getValue(Item::class.java)
                                        allocation.item.forEach {
                                            if(it.id_detail.equals(value!!.id_item)){
                                                if(value.nama_item == "Barong"){
                                                    jumlahBarongDetail.text = it.jumlah
                                                    namaBarongDetail.text = value.nama_item
                                                    hargaBarongDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutBarongDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Jathil"){
                                                    jumlahJathilDetail.text = it.jumlah
                                                    namaJathilDetail.text = value.nama_item
                                                    hargaJathilDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutJathilDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Klonosewandono"){
                                                    jumlahKlonosewandonoDetail.text = it.jumlah
                                                    namaKlonosewandonoDetail.text = value.nama_item
                                                    hargaKlonosewandonoDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutKlonosewandonoDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Bujang Ganong"){
                                                    jumlahBujangDetail.text = it.jumlah
                                                    namaBujangDetail.text = value.nama_item
                                                    hargaBujangDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutBujangDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Warog"){
                                                    jumlahWarogDetail.text = it.jumlah
                                                    namaWarogDetail.text = value.nama_item
                                                    hargaWarogDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutWarogDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Gendang"){
                                                    jumlahGendangDetail.text = it.jumlah
                                                    namaGendangDetail.text = value.nama_item
                                                    hargaGendangDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutGendangDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Ketipung"){
                                                    jumlahKetipungDetail.text = it.jumlah
                                                    namaKetipungDetail.text = value.nama_item
                                                    hargaKetipungDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutKetipungDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Slompret"){
                                                    jumlahSlompretDetail.text = it.jumlah
                                                    namaSlompretDetail.text = value.nama_item
                                                    hargaSlompretDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutSlompretDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Kenong"){
                                                    jumlahKenongDetail.text = it.jumlah
                                                    namaKenongDetail.text = value.nama_item
                                                    hargaKenongDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutKenongDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Gong"){
                                                    jumlahGongDetail.text = it.jumlah
                                                    namaGongDetail.text = value.nama_item
                                                    hargaGongDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutGongDetail.visibility = View.VISIBLE
                                                }
                                                if(value.nama_item == "Angklung"){
                                                    jumlahAngklungDetail.text = it.jumlah
                                                    namaAngklungDetail.text = value.nama_item
                                                    hargaAngklungDetail.text = formatNumber.format(it.jumlah.toInt() * value.harga.toInt())
                                                    layoutAngklungDetail.visibility = View.VISIBLE
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            override fun onCancelled(p0: DatabaseError) { }
                        })
                    if(btnSelesai.visibility.equals(View.VISIBLE) && allocation.status.equals("Selesai"))
                        btnSelesai.visibility = View.GONE
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        btnSelesai.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Ubah Status Pesanan")
            alertDialog.setMessage("Ubah status pesanan menjadi selesai ?")
                .setCancelable(false)
                .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id:Int) {
                        btnSelesai.isClickable = false
                        btnSelesai.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))

                        val oldItem = arrayListOf<Item>()
                        FirebaseDatabase.getInstance().getReference("item").child(id_sanggar)
                            .addValueEventListener( object : ValueEventListener{
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    for (h in snapshot.children){
                                        val value = h.getValue(Item::class.java)

                                        oldItem.add(value!!)
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {  }

                            })

                        FirebaseDatabase.getInstance().getReference("pesanan")
                            .child(intent.getStringExtra("id_pesanan").toString())
                            .child("status")
                            .setValue("Selesai")
                            .addOnCompleteListener {
                                FirebaseDatabase.getInstance().getReference("pesanan")
                                    .child(intent.getStringExtra("id_pesanan").toString())
                                    .child("idNstatus")
                                    .setValue(id_user+ "|Selesai")

                                FirebaseDatabase.getInstance().getReference("sanggar")
                                    .child(id_sanggar)
                                    .child("total_sewa")
                                    .setValue((total_sewa.toInt() + 1).toString())

                                pesananItem.forEach { itm ->
                                    oldItem.forEach { old ->
                                        if(old.id_item.equals(itm.id_detail)) {
                                            val stok = (old.stok.toInt() + itm.jumlah.toInt()).toString()
                                            FirebaseDatabase.getInstance().getReference("item")
                                                .child(old.id_sanggar)
                                                .child(old.id_item)
                                                .child("stok")
                                                .setValue(stok)
                                        }
                                    }
                                }

                                Toast.makeText(this@ActivityDetail, "Berhasil",Toast.LENGTH_LONG).show()
                                val intent = Intent(this@ActivityDetail, ActivityUtamaAdmin::class.java)
                                intent.putExtra("pesanan", "true")
                                startActivity(intent)
                                finish()
                            }
                    }
                })
                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id:Int) {
                        dialog.cancel()
                    }
                }).create().show()
        }
    }
}