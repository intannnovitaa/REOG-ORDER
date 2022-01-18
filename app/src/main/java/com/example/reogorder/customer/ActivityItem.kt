package com.example.reogorder.customer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.reogorder.model.Item
import com.example.reogorder.model.Sanggar
import com.example.reogorder.R
import com.example.reogorder.model.Pesanan
import com.google.firebase.database.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.pow

class ActivityItem : AppCompatActivity() {
    lateinit var databaseSanggar: DatabaseReference
    lateinit var databaseItem: DatabaseReference
    lateinit var sanggarItem: TextView
    lateinit var alamatItem: TextView
    lateinit var nohpItem: TextView
    lateinit var totalSewa: TextView
    lateinit var btnCheckout: Button

    lateinit var namaBarongItem: TextView
    lateinit var stokBarongItem: TextView
    lateinit var hargaBarongItem: TextView
    lateinit var jumlahBarongItem: EditText
    lateinit var tambahBarongItem: ImageView
    lateinit var kurangBarongItem: ImageView
    lateinit var namaJathilItem: TextView
    lateinit var stokJathilItem: TextView
    lateinit var hargaJathilItem: TextView
    lateinit var jumlahJathilItem: EditText
    lateinit var tambahJathilItem: ImageView
    lateinit var kurangJathilItem: ImageView
    lateinit var namaKlonosewandonoItem: TextView
    lateinit var stokKlonosewandonoItem: TextView
    lateinit var hargaKlonosewandonoItem: TextView
    lateinit var jumlahKlonosewandonoItem: EditText
    lateinit var tambahKlonosewandonoItem: ImageView
    lateinit var kurangKlonosewandonoItem: ImageView
    lateinit var namaBujangItem: TextView
    lateinit var stokBujangItem: TextView
    lateinit var hargaBujangItem: TextView
    lateinit var jumlahBujangItem: EditText
    lateinit var tambahBujangItem: ImageView
    lateinit var kurangBujangItem: ImageView
    lateinit var namaWarogItem: TextView
    lateinit var stokWarogItem: TextView
    lateinit var hargaWarogItem: TextView
    lateinit var jumlahWarogItem: EditText
    lateinit var tambahWarogItem: ImageView
    lateinit var kurangWarogItem: ImageView
    lateinit var namaGendangItem: TextView
    lateinit var stokGendangItem: TextView
    lateinit var hargaGendangItem: TextView
    lateinit var jumlahGendangItem: EditText
    lateinit var tambahGendangItem: ImageView
    lateinit var kurangGendangItem: ImageView
    lateinit var namaKetipungItem: TextView
    lateinit var stokKetipungItem: TextView
    lateinit var hargaKetipungItem: TextView
    lateinit var jumlahKetipungItem: EditText
    lateinit var tambahKetipungItem: ImageView
    lateinit var kurangKetipungItem: ImageView
    lateinit var namaSlompretItem: TextView
    lateinit var stokSlompretItem: TextView
    lateinit var hargaSlompretItem: TextView
    lateinit var jumlahSlompretItem: EditText
    lateinit var tambahSlompretItem: ImageView
    lateinit var kurangSlompretItem: ImageView
    lateinit var namaKenongItem: TextView
    lateinit var stokKenongItem: TextView
    lateinit var hargaKenongItem: TextView
    lateinit var jumlahKenongItem: EditText
    lateinit var tambahKenongItem: ImageView
    lateinit var kurangKenongItem: ImageView
    lateinit var namaGongItem: TextView
    lateinit var stokGongItem: TextView
    lateinit var hargaGongItem: TextView
    lateinit var jumlahGongItem: EditText
    lateinit var tambahGongItem: ImageView
    lateinit var kurangGongItem: ImageView
    lateinit var namaAngklungItem: TextView
    lateinit var stokAngklungItem: TextView
    lateinit var hargaAngklungItem: TextView
    lateinit var jumlahAngklungItem: EditText
    lateinit var tambahAngklungItem: ImageView
    lateinit var kurangAngklungItem: ImageView

    lateinit var cardBarong: CardView
    lateinit var cardJathil: CardView
    lateinit var cardKlonosewandono: CardView
    lateinit var cardBujang: CardView
    lateinit var cardWarog: CardView
    lateinit var cardGendang: CardView
    lateinit var cardKetipung: CardView
    lateinit var cardSlompret: CardView
    lateinit var cardKenong: CardView
    lateinit var cardGong: CardView
    lateinit var cardAngklung: CardView

    var idSanggar = ""
    var idBarong = ""
    var idJathil = ""
    var idKlonosewandono = ""
    var idBujang = ""
    var idWarog = ""
    var idGendang = ""
    var idKetipung = ""
    var idSlompret = ""
    var idKenong = ""
    var idGong = ""
    var idAngklung = ""

    var countBarong = 0
    var countJathil = 0
    var countKlonosewandono = 0
    var countBujang = 0
    var countWarog = 0
    var countGendang = 0
    var countKetipung = 0
    var countSlompret = 0
    var countKenong = 0
    var countGong = 0
    var countAngklung = 0

    var idPesanan = ""
    var tglPesanan = ""
    var wktPesanan = ""
    var lokPesanan = ""
    lateinit var SP: SharedPreferences
    var formatNumber: NumberFormat = DecimalFormat("#,###")

    var hargaBarong = ""
    var hargaJathil = ""
    var hargaKlonosewandono = ""
    var hargaBujang = ""
    var hargaWarog = ""
    var hargaGendang = ""
    var hargaKetipung = ""
    var hargaSlompret = ""
    var hargaKenong = ""
    var hargaGong = ""
    var hargaAngklung = ""

//    lateinit var btnSaran1: Button
//    lateinit var btnSaran2: Button
//    lateinit var btnSemua: Button
    var clusterBarong = ""
    var clusterJathil = ""
    var clusterKlonosewandono = ""
    var clusterBujang = ""
    var clusterWarog = ""
    var clusterGendang = ""
    var clusterKetipung = ""
    var clusterSlompret = ""
    var clusterKenong = ""
    var clusterGong = ""
    var clusterAngklung = ""

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)
        sanggarItem = findViewById(R.id.sanggarItem)
        alamatItem = findViewById(R.id.alamatItem)
        nohpItem = findViewById(R.id.nohpItem)
        totalSewa = findViewById(R.id.rent)
        btnCheckout = findViewById(R.id.btnCheckout)

        namaBarongItem = findViewById(R.id.namaBarongItem)
        stokBarongItem = findViewById(R.id.stokBarongItem)
        hargaBarongItem = findViewById(R.id.hargaBarongItem)
        jumlahBarongItem = findViewById(R.id.jumlahBarongItem)
        tambahBarongItem = findViewById(R.id.tambahBarongItem)
        kurangBarongItem = findViewById(R.id.kurangBarongItem)
        namaJathilItem = findViewById(R.id.namaJathilItem)
        stokJathilItem = findViewById(R.id.stokJathilItem)
        hargaJathilItem = findViewById(R.id.hargaJathilItem)
        jumlahJathilItem = findViewById(R.id.jumlahJathilItem)
        tambahJathilItem = findViewById(R.id.tambahJathilItem)
        kurangJathilItem = findViewById(R.id.kurangJathilItem)
        namaKlonosewandonoItem = findViewById(R.id.namaKlonosewandonoItem)
        stokKlonosewandonoItem = findViewById(R.id.stokKlonosewandonoItem)
        hargaKlonosewandonoItem = findViewById(R.id.hargaKlonosewandonoItem)
        jumlahKlonosewandonoItem = findViewById(R.id.jumlahKlonosewandonoItem)
        tambahKlonosewandonoItem = findViewById(R.id.tambahKlonosewandonoItem)
        kurangKlonosewandonoItem = findViewById(R.id.kurangKlonosewandonoItem)
        namaBujangItem = findViewById(R.id.namaBujangItem)
        stokBujangItem = findViewById(R.id.stokBujangItem)
        hargaBujangItem = findViewById(R.id.hargaBujangItem)
        jumlahBujangItem = findViewById(R.id.jumlahBujangItem)
        tambahBujangItem = findViewById(R.id.tambahBujangItem)
        kurangBujangItem = findViewById(R.id.kurangBujangItem)
        namaWarogItem = findViewById(R.id.namaWarogItem)
        stokWarogItem = findViewById(R.id.stokWarogItem)
        hargaWarogItem = findViewById(R.id.hargaWarogItem)
        jumlahWarogItem = findViewById(R.id.jumlahWarogItem)
        tambahWarogItem = findViewById(R.id.tambahWarogItem)
        kurangWarogItem = findViewById(R.id.kurangWarogItem)
        namaGendangItem = findViewById(R.id.namaGendangItem)
        stokGendangItem = findViewById(R.id.stokGendangItem)
        hargaGendangItem = findViewById(R.id.hargaGendangItem)
        jumlahGendangItem = findViewById(R.id.jumlahGendangItem)
        tambahGendangItem = findViewById(R.id.tambahGendangItem)
        kurangGendangItem = findViewById(R.id.kurangGendangItem)
        namaKetipungItem = findViewById(R.id.namaKetipungItem)
        stokKetipungItem = findViewById(R.id.stokKetipungItem)
        hargaKetipungItem = findViewById(R.id.hargaKetipungItem)
        jumlahKetipungItem = findViewById(R.id.jumlahKetipungItem)
        tambahKetipungItem = findViewById(R.id.tambahKetipungItem)
        kurangKetipungItem = findViewById(R.id.kurangKetipungItem)
        namaSlompretItem = findViewById(R.id.namaSlompretItem)
        stokSlompretItem = findViewById(R.id.stokSlompretItem)
        hargaSlompretItem = findViewById(R.id.hargaSlompretItem)
        jumlahSlompretItem = findViewById(R.id.jumlahSlompretItem)
        tambahSlompretItem = findViewById(R.id.tambahSlompretItem)
        kurangSlompretItem = findViewById(R.id.kurangSlompretItem)
        namaKenongItem = findViewById(R.id.namaKenongItem)
        stokKenongItem = findViewById(R.id.stokKenongItem)
        hargaKenongItem = findViewById(R.id.hargaKenongItem)
        jumlahKenongItem = findViewById(R.id.jumlahKenongItem)
        tambahKenongItem = findViewById(R.id.tambahKenongItem)
        kurangKenongItem = findViewById(R.id.kurangKenongItem)
        namaGongItem = findViewById(R.id.namaGongItem)
        stokGongItem = findViewById(R.id.stokGongItem)
        hargaGongItem = findViewById(R.id.hargaGongItem)
        jumlahGongItem = findViewById(R.id.jumlahGongItem)
        tambahGongItem = findViewById(R.id.tambahGongItem)
        kurangGongItem = findViewById(R.id.kurangGongItem)
        namaAngklungItem = findViewById(R.id.namaAngklungItem)
        stokAngklungItem = findViewById(R.id.stokAngklungItem)
        hargaAngklungItem = findViewById(R.id.hargaAngklungItem)
        jumlahAngklungItem = findViewById(R.id.jumlahAngklungItem)
        tambahAngklungItem = findViewById(R.id.tambahAngklungItem)
        kurangAngklungItem = findViewById(R.id.kurangAngklungItem)

        cardBarong = findViewById(R.id.cardBarong)
        cardJathil = findViewById(R.id.cardJathil)
        cardKlonosewandono = findViewById(R.id.cardKlonosewandono)
        cardBujang = findViewById(R.id.cardBujang)
        cardWarog = findViewById(R.id.cardWarog)
        cardGendang = findViewById(R.id.cardGendang)
        cardKetipung = findViewById(R.id.cardKetipung)
        cardSlompret = findViewById(R.id.cardSlompret)
        cardKenong = findViewById(R.id.cardKenong)
        cardGong = findViewById(R.id.cardGong)
        cardAngklung = findViewById(R.id.cardAngklung)

//        btnSaran1 = findViewById(R.id.btnSaran1)
//        btnSaran2 = findViewById(R.id.btnSaran2)
//        btnSemua = findViewById(R.id.btnSemua)

        databaseSanggar = FirebaseDatabase.getInstance().getReference("sanggar")
        val query = databaseSanggar.orderByKey().equalTo(intent.getStringExtra("id_sanggar").toString())
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Sanggar::class.java)
                    sanggarItem.text = allocation!!.nama_sanggar
                    alamatItem.text = allocation.alamat_sanggar
                    nohpItem.text = allocation.nohp_sanggar
                    totalSewa.text = allocation.total_sewa + " kali disewa"

                    idSanggar = allocation.id_sanggar
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

//        btnSemua.setOnClickListener {
//            btnSemua.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
//            btnSaran1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            btnSaran2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            cardBarong.visibility = View.VISIBLE
//            cardJathil.visibility = View.VISIBLE
//            cardKlonosewandono.visibility = View.VISIBLE
//            cardBujang.visibility = View.VISIBLE
//            cardWarog.visibility = View.VISIBLE
//            cardGendang.visibility = View.VISIBLE
//            cardKetipung.visibility = View.VISIBLE
//            cardSlompret.visibility = View.VISIBLE
//            cardKenong.visibility = View.VISIBLE
//            cardGong.visibility = View.VISIBLE
//            cardAngklung.visibility = View.VISIBLE
//        }
//        btnSaran1.setOnClickListener {
//            btnSemua.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            btnSaran1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
//            btnSaran2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            if(clusterBarong == "C1") {
//                cardBarong.visibility = View.VISIBLE
//            }
//            if(clusterBarong == "C2") {
//                cardBarong.visibility = View.GONE
//            }
//            if(clusterJathil == "C1") {
//                cardJathil.visibility = View.VISIBLE
//            }
//            if(clusterJathil == "C2") {
//                cardJathil.visibility = View.GONE
//            }
//            if(clusterKlonosewandono == "C1") {
//                cardKlonosewandono.visibility = View.VISIBLE
//            }
//            if(clusterKlonosewandono == "C2") {
//                cardKlonosewandono.visibility = View.GONE
//            }
//            if(clusterBujang == "C1") {
//                cardBujang.visibility = View.VISIBLE
//            }
//            if(clusterBujang == "C2") {
//                cardBujang.visibility = View.GONE
//            }
//            if(clusterWarog == "C1") {
//                cardWarog.visibility = View.VISIBLE
//            }
//            if(clusterWarog == "C2") {
//                cardWarog.visibility = View.GONE
//            }
//            if(clusterGendang == "C1") {
//                cardGendang.visibility = View.VISIBLE
//            }
//            if(clusterGendang == "C2") {
//                cardGendang.visibility = View.GONE
//            }
//            if(clusterKetipung == "C1") {
//                cardKetipung.visibility = View.VISIBLE
//            }
//            if(clusterKetipung == "C2") {
//                cardKetipung.visibility = View.GONE
//            }
//            if(clusterSlompret == "C1") {
//                cardSlompret.visibility = View.VISIBLE
//            }
//            if(clusterSlompret == "C2") {
//                cardSlompret.visibility = View.GONE
//            }
//            if(clusterKenong == "C1") {
//                cardKenong.visibility = View.VISIBLE
//            }
//            if(clusterKenong == "C2") {
//                cardKenong.visibility = View.GONE
//            }
//            if(clusterGong == "C1") {
//                cardGong.visibility = View.VISIBLE
//            }
//            if(clusterGong == "C2") {
//                cardGong.visibility = View.GONE
//            }
//            if(clusterAngklung == "C1") {
//                cardAngklung.visibility = View.VISIBLE
//            }
//            if(clusterAngklung == "C2") {
//                cardAngklung.visibility = View.GONE
//            }
//        }
//        btnSaran2.setOnClickListener {
//            btnSemua.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            btnSaran1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
//            btnSaran2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
//            if(clusterBarong == "C2") {
//                cardBarong.visibility = View.VISIBLE
//            }
//            if(clusterBarong == "C1") {
//                cardBarong.visibility = View.GONE
//            }
//            if(clusterJathil == "C2") {
//                cardJathil.visibility = View.VISIBLE
//            }
//            if(clusterJathil == "C1") {
//                cardJathil.visibility = View.GONE
//            }
//            if(clusterKlonosewandono == "C2") {
//                cardKlonosewandono.visibility = View.VISIBLE
//            }
//            if(clusterKlonosewandono == "C1") {
//                cardKlonosewandono.visibility = View.GONE
//            }
//            if(clusterBujang == "C2") {
//                cardBujang.visibility = View.VISIBLE
//            }
//            if(clusterBujang == "C1") {
//                cardBujang.visibility = View.GONE
//            }
//            if(clusterWarog == "C2") {
//                cardWarog.visibility = View.VISIBLE
//            }
//            if(clusterWarog == "C1") {
//                cardWarog.visibility = View.GONE
//            }
//            if(clusterGendang == "C2") {
//                cardGendang.visibility = View.VISIBLE
//            }
//            if(clusterGendang == "C1") {
//                cardGendang.visibility = View.GONE
//            }
//            if(clusterKetipung == "C2") {
//                cardKetipung.visibility = View.VISIBLE
//            }
//            if(clusterKetipung == "C1") {
//                cardKetipung.visibility = View.GONE
//            }
//            if(clusterSlompret == "C2") {
//                cardSlompret.visibility = View.VISIBLE
//            }
//            if(clusterSlompret == "C1") {
//                cardSlompret.visibility = View.GONE
//            }
//            if(clusterKenong == "C2") {
//                cardKenong.visibility = View.VISIBLE
//            }
//            if(clusterKenong == "C1") {
//                cardKenong.visibility = View.GONE
//            }
//            if(clusterGong == "C2") {
//                cardGong.visibility = View.VISIBLE
//            }
//            if(clusterGong == "C1") {
//                cardGong.visibility = View.GONE
//            }
//            if(clusterAngklung == "C2") {
//                cardAngklung.visibility = View.VISIBLE
//            }
//            if(clusterAngklung == "C1") {
//                cardAngklung.visibility = View.GONE
//            }
//        }

        loadItem()
        setJumlah()
        checkOldData()
        val ppn = intent.getIntExtra("ppn", 0)
//        fuzzyCMeans()
        btnCheckout.setOnClickListener {
            val idBarongI = idBarong.trim()
            val idJathilI = idJathil.trim()
            val idKlonosewandonoI = idKlonosewandono.trim()
            val idBujangI = idBujang.trim()
            val idWarogI = idWarog.trim()
            val idGendangI = idGendang.trim()
            val idKetipungI = idKetipung.trim()
            val idSlompretI = idSlompret.trim()
            val idKenongI = idKenong.trim()
            val idGongI = idGong.trim()
            val idAngklungI = idAngklung.trim()

            val sanggarI = sanggarItem.text.toString()
            val alamatI = alamatItem.text.toString()
            val namaBarongI = namaBarongItem.text.toString()
            val hargaBarongI = hargaBarong
            val jumlahBarongI = jumlahBarongItem.text.toString()
            val namaJathilI = namaJathilItem.text.toString()
            val hargaJathilI = hargaJathil
            val jumlahJathilI = jumlahJathilItem.text.toString()
            val namaKlonosewandonoI = namaKlonosewandonoItem.text.toString()
            val hargaKlonosewandonoI = hargaKlonosewandono
            val jumlahKlonosewandonoI = jumlahKlonosewandonoItem.text.toString()
            val namaBujangI = namaBujangItem.text.toString()
            val hargaBujangI = hargaBujang
            val jumlahBujangI = jumlahBujangItem.text.toString()
            val namaWarogI = namaWarogItem.text.toString()
            val hargaWarogI = hargaWarog
            val jumlahWarogI = jumlahWarogItem.text.toString()
            val namaGendangI = namaGendangItem.text.toString()
            val hargaGendangI = hargaGendang
            val jumlahGendangI = jumlahGendangItem.text.toString()
            val namaKetipungI = namaKetipungItem.text.toString()
            val hargaKetipungI = hargaKetipung
            val jumlahKetipungI = jumlahKetipungItem.text.toString()
            val namaSlompretI = namaSlompretItem.text.toString()
            val hargaSlompretI = hargaSlompret
            val jumlahSlompretI = jumlahSlompretItem.text.toString()
            val namaKenongI = namaKenongItem.text.toString()
            val hargaKenongI = hargaKenong
            val jumlahKenongI = jumlahKenongItem.text.toString()
            val namaGongI = namaGongItem.text.toString()
            val hargaGongI = hargaGong
            val jumlahGongI = jumlahGongItem.text.toString()
            val namaAngklungI = namaAngklungItem.text.toString()
            val hargaAngklungI = hargaAngklung
            val jumlahAngklungI = jumlahAngklungItem.text.toString()
            
            val intent = Intent(this, ActivityCheckout::class.java)
            intent.putExtra("idPesanan", idPesanan)
            intent.putExtra("idSanggar", idSanggar)
            intent.putExtra("sanggar", sanggarI)
            intent.putExtra("alamat", alamatI)
            intent.putExtra("namaBarong", namaBarongI)
            intent.putExtra("hargaBarong", hargaBarongI)
            intent.putExtra("jumlahBarong", jumlahBarongI)
            intent.putExtra("namaJathil", namaJathilI)
            intent.putExtra("hargaJathil", hargaJathilI)
            intent.putExtra("jumlahJathil", jumlahJathilI)
            intent.putExtra("namaKlonosewandono", namaKlonosewandonoI)
            intent.putExtra("hargaKlonosewandono", hargaKlonosewandonoI)
            intent.putExtra("jumlahKlonosewandono", jumlahKlonosewandonoI)
            intent.putExtra("namaBujang", namaBujangI)
            intent.putExtra("hargaBujang", hargaBujangI)
            intent.putExtra("jumlahBujang", jumlahBujangI)
            intent.putExtra("namaWarog", namaWarogI)
            intent.putExtra("hargaWarog", hargaWarogI)
            intent.putExtra("jumlahWarog", jumlahWarogI)
            intent.putExtra("namaGendang", namaGendangI)
            intent.putExtra("hargaGendang", hargaGendangI)
            intent.putExtra("jumlahGendang", jumlahGendangI)
            intent.putExtra("namaKetipung", namaKetipungI)
            intent.putExtra("hargaKetipung", hargaKetipungI)
            intent.putExtra("jumlahKetipung", jumlahKetipungI)
            intent.putExtra("namaSlompret", namaSlompretI)
            intent.putExtra("hargaSlompret", hargaSlompretI)
            intent.putExtra("jumlahSlompret", jumlahSlompretI)
            intent.putExtra("namaKenong", namaKenongI)
            intent.putExtra("hargaKenong", hargaKenongI)
            intent.putExtra("jumlahKenong", jumlahKenongI)
            intent.putExtra("namaGong", namaGongI)
            intent.putExtra("hargaGong", hargaGongI)
            intent.putExtra("jumlahGong", jumlahGongI)
            intent.putExtra("namaAngklung", namaAngklungI)
            intent.putExtra("hargaAngklung", hargaAngklungI)
            intent.putExtra("jumlahAngklung", jumlahAngklungI)

            intent.putExtra("idBarong", idBarongI)
            intent.putExtra("idJathil", idJathilI)
            intent.putExtra("idKlonosewandono", idKlonosewandonoI)
            intent.putExtra("idBujang", idBujangI)
            intent.putExtra("idWarog", idWarogI)
            intent.putExtra("idGendang", idGendangI)
            intent.putExtra("idKetipung", idKetipungI)
            intent.putExtra("idSlompret", idSlompretI)
            intent.putExtra("idKenong", idKenongI)
            intent.putExtra("idGong", idGongI)
            intent.putExtra("idAngklung", idAngklungI)

            intent.putExtra("tglPesanan", tglPesanan)
            intent.putExtra("wktPesanan", wktPesanan)
            intent.putExtra("lokPesanan", lokPesanan)

            intent.putExtra("ppn", ppn)
            startActivity(intent)
        }
    }

    fun loadItem() {
        databaseItem = FirebaseDatabase.getInstance().getReference("item")
        val queryItem = databaseItem.child(intent.getStringExtra("id_sanggar").toString())
        queryItem.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Item::class.java)
                    if(allocation!!.nama_item == "Barong"){
                        idBarong = allocation.id_item
                        namaBarongItem.text = allocation.nama_item
                        stokBarongItem.text = allocation.stok
                        hargaBarong = allocation.harga
                        hargaBarongItem.text = "Rp. " + formatNumber.format(hargaBarong.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Jathil"){
                        idJathil = allocation.id_item
                        namaJathilItem.text = allocation.nama_item
                        stokJathilItem.text = allocation.stok
                        hargaJathil = allocation.harga
                        hargaJathilItem.text = "Rp. " + formatNumber.format(hargaJathil.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Klonosewandono"){
                        idKlonosewandono = allocation.id_item
                        namaKlonosewandonoItem.text = allocation.nama_item
                        stokKlonosewandonoItem.text = allocation.stok
                        hargaKlonosewandono = allocation.harga
                        hargaKlonosewandonoItem.text = "Rp. " + formatNumber.format(hargaKlonosewandono.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Bujang Ganong"){
                        idBujang = allocation.id_item
                        namaBujangItem.text = allocation.nama_item
                        stokBujangItem.text = allocation.stok
                        hargaBujang = allocation.harga
                        hargaBujangItem.text = "Rp. " + formatNumber.format(hargaBujang.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Warog"){
                        idWarog = allocation.id_item
                        namaWarogItem.text = allocation.nama_item
                        stokWarogItem.text = allocation.stok
                        hargaWarog = allocation.harga
                        hargaWarogItem.text = "Rp. " + formatNumber.format(hargaWarog.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Gendang"){
                        idGendang = allocation.id_item
                        namaGendangItem.text = allocation.nama_item
                        stokGendangItem.text = allocation.stok
                        hargaGendang = allocation.harga
                        hargaGendangItem.text = "Rp. " + formatNumber.format(hargaGendang.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Ketipung"){
                        idKetipung = allocation.id_item
                        namaKetipungItem.text = allocation.nama_item
                        stokKetipungItem.text = allocation.stok
                        hargaKetipung = allocation.harga
                        hargaKetipungItem.text = "Rp. " + formatNumber.format(hargaKetipung.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Slompret"){
                        idSlompret = allocation.id_item
                        namaSlompretItem.text = allocation.nama_item
                        stokSlompretItem.text = allocation.stok
                        hargaSlompret = allocation.harga
                        hargaSlompretItem.text = "Rp. " + formatNumber.format(hargaSlompret.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Kenong"){
                        idKenong = allocation.id_item
                        namaKenongItem.text = allocation.nama_item
                        stokKenongItem.text = allocation.stok
                        hargaKenong = allocation.harga
                        hargaKenongItem.text = "Rp. " + formatNumber.format(hargaKenong.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Gong"){
                        idGong = allocation.id_item
                        namaGongItem.text = allocation.nama_item
                        stokGongItem.text = allocation.stok
                        hargaGong = allocation.harga
                        hargaGongItem.text = "Rp. " + formatNumber.format(hargaGong.toInt()) + ",00"
                    }
                    if(allocation.nama_item == "Angklung"){
                        idAngklung = allocation.id_item
                        namaAngklungItem.text = allocation.nama_item
                        stokAngklungItem.text = allocation.stok
                        hargaAngklung = allocation.harga
                        hargaAngklungItem.text = "Rp. " + formatNumber.format(hargaAngklung.toInt()) + ",00"
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun setJumlah() {
        tambahBarongItem.setOnClickListener {
            if (jumlahBarongItem.text.toString() == stokBarongItem.text.toString()) { }
            else {
                countBarong++
                jumlahBarongItem.setText(countBarong.toString())
            }
        }
        kurangBarongItem.setOnClickListener {
            if (jumlahBarongItem.text.toString() == "0") { }
            else {
                countBarong--
                jumlahBarongItem.setText(countBarong.toString())
            }
        }
        tambahJathilItem.setOnClickListener {
            if (jumlahJathilItem.text.toString() == stokJathilItem.text.toString()) { }
            else {
                countJathil++
                jumlahJathilItem.setText(countJathil.toString())
            }
        }
        kurangJathilItem.setOnClickListener {
            if (jumlahJathilItem.text.toString() == "0") { }
            else {
                countJathil--
                jumlahJathilItem.setText(countJathil.toString())
            }
        }
        tambahKlonosewandonoItem.setOnClickListener {
            if (jumlahKlonosewandonoItem.text.toString() == stokKlonosewandonoItem.text.toString()) { }
            else {
                countKlonosewandono++
                jumlahKlonosewandonoItem.setText(countKlonosewandono.toString())
            }
        }
        kurangKlonosewandonoItem.setOnClickListener {
            if (jumlahKlonosewandonoItem.text.toString() == "0") { }
            else {
                countKlonosewandono--
                jumlahKlonosewandonoItem.setText(countKlonosewandono.toString())
            }
        }
        tambahBujangItem.setOnClickListener {
            if (jumlahBujangItem.text.toString() == stokBujangItem.text.toString()) { }
            else {
                countBujang++
                jumlahBujangItem.setText(countBujang.toString())
            }
        }
        kurangBujangItem.setOnClickListener {
            if (jumlahBujangItem.text.toString() == "0") { }
            else {
                countBujang--
                jumlahBujangItem.setText(countBujang.toString())
            }
        }
        tambahWarogItem.setOnClickListener {
            if (jumlahWarogItem.text.toString() == stokWarogItem.text.toString()) { }
            else {
                countWarog++
                jumlahWarogItem.setText(countWarog.toString())
            }
        }
        kurangWarogItem.setOnClickListener {
            if (jumlahWarogItem.text.toString() == "0") { }
            else {
                countWarog--
                jumlahWarogItem.setText(countWarog.toString())
            }
        }
        tambahGendangItem.setOnClickListener {
            if (jumlahGendangItem.text.toString() == stokGendangItem.text.toString()) { }
            else {
                countGendang++
                jumlahGendangItem.setText(countGendang.toString())
            }
        }
        kurangGendangItem.setOnClickListener {
            if (jumlahGendangItem.text.toString() == "0") { }
            else {
                countGendang--
                jumlahGendangItem.setText(countGendang.toString())
            }
        }
        tambahKetipungItem.setOnClickListener {
            if (jumlahKetipungItem.text.toString() == stokKetipungItem.text.toString()) { }
            else {
                countKetipung++
                jumlahKetipungItem.setText(countKetipung.toString())
            }
        }
        kurangKetipungItem.setOnClickListener {
            if (jumlahKetipungItem.text.toString() == "0") { }
            else {
                countKetipung--
                jumlahKetipungItem.setText(countKetipung.toString())
            }
        }
        tambahSlompretItem.setOnClickListener {
            if (jumlahSlompretItem.text.toString() == stokSlompretItem.text.toString()) { }
            else {
                countSlompret++
                jumlahSlompretItem.setText(countSlompret.toString())
            }
        }
        kurangSlompretItem.setOnClickListener {
            if (jumlahSlompretItem.text.toString() == "0") { }
            else {
                countSlompret--
                jumlahSlompretItem.setText(countSlompret.toString())
            }
        }
        tambahKenongItem.setOnClickListener {
            if (jumlahKenongItem.text.toString() == stokKenongItem.text.toString()) { }
            else {
                countKenong++
                jumlahKenongItem.setText(countKenong.toString())
            }
        }
        kurangKenongItem.setOnClickListener {
            if (jumlahKenongItem.text.toString() == "0") { }
            else {
                countKenong--
                jumlahKenongItem.setText(countKenong.toString())
            }
        }
        tambahGongItem.setOnClickListener {
            if (jumlahGongItem.text.toString() == stokGongItem.text.toString()) { }
            else {
                countGong++
                jumlahGongItem.setText(countGong.toString())
            }
        }
        kurangGongItem.setOnClickListener {
            if (jumlahGongItem.text.toString() == "0") { }
            else {
                countGong--
                jumlahGongItem.setText(countGong.toString())
            }
        }
        tambahAngklungItem.setOnClickListener {
            if (jumlahAngklungItem.text.toString() == stokAngklungItem.text.toString()) { }
            else {
                countAngklung++
                jumlahAngklungItem.setText(countAngklung.toString())
            }
        }
        kurangAngklungItem.setOnClickListener {
            if (jumlahAngklungItem.text.toString() == "0") { }
            else {
                countAngklung--
                jumlahAngklungItem.setText(countAngklung.toString())
            }
        }
    }

    fun checkOldData() {
        FirebaseDatabase.getInstance().getReference("pesanan")
            .orderByChild("id").equalTo(SP.getString("id", "").toString())
            .addValueEventListener( object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()){
                        for (h in p0.children){
                            val value = h.getValue(Pesanan::class.java)
                            if(value!!.id_sanggar == intent.getStringExtra("id_sanggar").toString() && value.status == "Diproses"){
                                idPesanan = value.id_pesanan
                                tglPesanan = value.tanggal
                                wktPesanan = value.waktu
                                lokPesanan = value.lokasi
                                for (i in value.item){
                                    FirebaseDatabase.getInstance().getReference("item")
                                        .child(value.id_sanggar).orderByKey().equalTo(i.id_detail)
                                        .addListenerForSingleValueEvent( object : ValueEventListener{
                                            override fun onDataChange(p1: DataSnapshot) {
                                                if(p1.exists()){
                                                    for (j in p1.children){
                                                        val vl = j.getValue(Item::class.java)
                                                        Log.d("olddata", vl!!.nama_item)
                                                        if(vl.nama_item == "Barong"){
                                                            countBarong = i.jumlah.toInt()
                                                            jumlahBarongItem.setText(countBarong.toString())
                                                        }
                                                        if(vl.nama_item == "Jathil"){
                                                            countJathil = i.jumlah.toInt()
                                                            jumlahJathilItem.setText(countJathil.toString())
                                                        }
                                                        if(vl.nama_item == "Klonosewandono"){
                                                            countKlonosewandono = i.jumlah.toInt()
                                                            jumlahKlonosewandonoItem.setText(countKlonosewandono.toString())
                                                        }
                                                        if(vl.nama_item == "Bujang Ganong"){
                                                            countBujang = i.jumlah.toInt()
                                                            jumlahBujangItem.setText(countBujang.toString())
                                                        }
                                                        if(vl.nama_item == "Warog"){
                                                            countWarog = i.jumlah.toInt()
                                                            jumlahWarogItem.setText(countWarog.toString())
                                                        }
                                                        if(vl.nama_item == "Gendang"){
                                                            countGendang = i.jumlah.toInt()
                                                            jumlahGendangItem.setText(countGendang.toString())
                                                        }
                                                        if(vl.nama_item == "Ketipung"){
                                                            countKetipung = i.jumlah.toInt()
                                                            jumlahKetipungItem.setText(countKetipung.toString())
                                                        }
                                                        if(vl.nama_item == "Slompret"){
                                                            countSlompret = i.jumlah.toInt()
                                                            jumlahSlompretItem.setText(countSlompret.toString())
                                                        }
                                                        if(vl.nama_item == "Kenong"){
                                                            countKenong = i.jumlah.toInt()
                                                            jumlahKenongItem.setText(countKenong.toString())
                                                        }
                                                        if(vl.nama_item == "Gong"){
                                                            countGong = i.jumlah.toInt()
                                                            jumlahGongItem.setText(countGong.toString())
                                                        }
                                                        if(vl.nama_item == "Angklung"){
                                                            countAngklung = i.jumlah.toInt()
                                                            jumlahAngklungItem.setText(countAngklung.toString())
                                                        }
                                                    }
                                                }
//                                                btnSaran1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))
//                                                btnSaran1.isClickable = false
//                                                btnSaran2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray))
//                                                btnSaran2.isClickable = false
//                                                btnSemua.isClickable = false
                                            }
                                            override fun onCancelled(p0: DatabaseError) { }
                                        })
                                }
                            }
                        }
                    }
                }
                override fun onCancelled(p0: DatabaseError) { }
            })
    }



    private fun fuzzyCMeans() {
        val x1Barong = (stokBarongItem.text.toString().toFloat() * 1000000)/100000
        val x1Jathil = (stokJathilItem.text.toString().toFloat() * 150000)/100000
        val x1Klonosewandono = (stokKlonosewandonoItem.text.toString().toFloat() * 100000)/100000
        val x1Bujang = (stokBujangItem.text.toString().toFloat() * 150000)/100000
        val x1Warog = (stokWarogItem.text.toString().toFloat() * 100000)/100000
        val x1Gendang = (stokGendangItem.text.toString().toFloat() * 400000)/100000
        val x1Ketipung = (stokKetipungItem.text.toString().toFloat() * 300000)/100000
        val x1Slompret = (stokSlompretItem.text.toString().toFloat() * 300000)/100000
        val x1Kenong = (stokKenongItem.text.toString().toFloat() * 200000)/100000
        val x1Gong = (stokGongItem.text.toString().toFloat() * 300000)/100000
        val x1Angklung = (stokAngklungItem.text.toString().toFloat() * 200000)/100000

        val c1Barong = 0.3
        val c2Barong = 0.7
        val c1Jathil = 0.0
        val c2Jathil = 1.0
        val c1Klonosewandono = 0.9
        val c2Klonosewandono = 0.1
        val c1Bujang = 1.0
        val c2Bujang = 0.0
        val c1Warog = 0.4
        val c2Warog = 0.6
        val c1Gendang = 0.1
        val c2Gendang = 0.9
        val c1Ketipung = 0.7
        val c2Ketipung = 0.3
        val c1Slompret = 0.9
        val c2Slompret = 0.1
        val c1Kenong = 1.0
        val c2Kenong = 0.0
        val c1Gong = 1.0
        val c2Gong = 0.0
        val c1Angklung = 0.9
        val c2Angklung = 0.1

        val miuC1Barong = (c1Barong.pow(2)).toFloat()
        val miuC2Barong = (c2Barong.pow(2)).toFloat()
        val miuC1Jathil = (c1Jathil.pow(2)).toFloat()
        val miuC2Jathil = (c2Jathil.pow(2)).toFloat()
        val miuC1Klonosewandono = (c1Klonosewandono.pow(2)).toFloat()
        val miuC2Klonosewandono = (c2Klonosewandono.pow(2)).toFloat()
        val miuC1Bujang = (c1Bujang.pow(2)).toFloat()
        val miuC2Bujang = (c2Bujang.pow(2)).toFloat()
        val miuC1Warog = (c1Warog.pow(2)).toFloat()
        val miuC2Warog = (c2Warog.pow(2)).toFloat()
        val miuC1Gendang = (c1Gendang.pow(2)).toFloat()
        val miuC2Gendang = (c2Gendang.pow(2)).toFloat()
        val miuC1Ketipung = (c1Ketipung.pow(2)).toFloat()
        val miuC2Ketipung = (c2Ketipung.pow(2)).toFloat()
        val miuC1Slompret = (c1Slompret.pow(2)).toFloat()
        val miuC2Slompret = (c2Slompret.pow(2)).toFloat()
        val miuC1Kenong = (c1Kenong.pow(2)).toFloat()
        val miuC2Kenong = (c2Kenong.pow(2)).toFloat()
        val miuC1Gong = (c1Gong.pow(2)).toFloat()
        val miuC2Gong = (c2Gong.pow(2)).toFloat()
        val miuC1Angklung = (c1Angklung.pow(2)).toFloat()
        val miuC2Angklung = (c2Angklung.pow(2)).toFloat()

        val x1C1Barong = x1Barong * c1Barong.toFloat()
        val x1C2Barong = x1Barong * c2Barong.toFloat()
        val x1C1Jathil = x1Jathil * c1Jathil.toFloat()
        val x1C2Jathil = x1Jathil * c2Jathil.toFloat()
        val x1C1Klonosewandono = x1Klonosewandono * c1Klonosewandono.toFloat()
        val x1C2Klonosewandono = x1Klonosewandono * c2Klonosewandono.toFloat()
        val x1C1Bujang = x1Bujang * c1Bujang.toFloat()
        val x1C2Bujang = x1Bujang * c2Bujang.toFloat()
        val x1C1Warog = x1Warog * c1Warog.toFloat()
        val x1C2Warog = x1Warog * c2Warog.toFloat()
        val x1C1Gendang = x1Gendang * c1Gendang.toFloat()
        val x1C2Gendang = x1Gendang * c2Gendang.toFloat()
        val x1C1Ketipung = x1Ketipung * c1Ketipung.toFloat()
        val x1C2Ketipung = x1Ketipung * c2Ketipung.toFloat()
        val x1C1Slompret = x1Slompret * c1Slompret.toFloat()
        val x1C2Slompret = x1Slompret * c2Slompret.toFloat()
        val x1C1Kenong = x1Kenong * c1Kenong.toFloat()
        val x1C2Kenong = x1Kenong * c2Kenong.toFloat()
        val x1C1Gong = x1Gong * c1Gong.toFloat()
        val x1C2Gong = x1Gong * c2Gong.toFloat()
        val x1C1Angklung = x1Angklung * c1Angklung.toFloat()
        val x1C2Angklung = x1Angklung * c2Angklung.toFloat()

        val pX1 = (x1C1Barong + x1C1Jathil + x1C1Klonosewandono + x1C1Bujang
                + x1C1Warog + x1C1Gendang + x1C1Ketipung + x1C1Slompret
                + x1C1Kenong + x1C1Gong + x1C1Angklung) / (miuC1Barong
                + miuC1Jathil + miuC1Klonosewandono + miuC1Bujang + miuC1Warog
                + miuC1Gendang + miuC1Ketipung + miuC1Slompret + miuC1Kenong
                + miuC1Gong + miuC1Angklung)
        val pX2 = (x1C2Barong + x1C2Jathil + x1C2Klonosewandono + x1C2Bujang
                + x1C2Warog + x1C2Gendang + x1C2Ketipung + x1C2Slompret
                + x1C2Kenong + x1C2Gong + x1C2Angklung) / (miuC2Barong
                + miuC2Jathil + miuC2Klonosewandono + miuC2Bujang + miuC2Warog
                + miuC2Gendang + miuC2Ketipung + miuC2Slompret + miuC2Kenong
                + miuC2Gong + miuC2Angklung)

        val l1Barong = ((x1Barong - pX1).pow(2)).pow(-1)
        val l2Barong = ((x1Barong - pX2).pow(2)).pow(-1)
        val l1Jathil = ((x1Jathil - pX1).pow(2)).pow(-1)
        val l2Jathil = ((x1Jathil - pX2).pow(2)).pow(-1)
        val l1Klonosewandono = ((x1Klonosewandono - pX1).pow(2)).pow(-1)
        val l2Klonosewandono = ((x1Klonosewandono - pX2).pow(2)).pow(-1)
        val l1Bujang = ((x1Bujang - pX1).pow(2)).pow(-1)
        val l2Bujang = ((x1Bujang - pX2).pow(2)).pow(-1)
        val l1Warog = ((x1Warog - pX1).pow(2)).pow(-1)
        val l2Warog = ((x1Warog - pX2).pow(2)).pow(-1)
        val l1Gendang = ((x1Gendang - pX1).pow(2)).pow(-1)
        val l2Gendang = ((x1Gendang - pX2).pow(2)).pow(-1)
        val l1Ketipung = ((x1Ketipung - pX1).pow(2)).pow(-1)
        val l2Ketipung = ((x1Ketipung - pX2).pow(2)).pow(-1)
        val l1Slompret = ((x1Slompret - pX1).pow(2)).pow(-1)
        val l2Slompret = ((x1Slompret - pX2).pow(2)).pow(-1)
        val l1Kenong = ((x1Kenong - pX1).pow(2)).pow(-1)
        val l2Kenong = ((x1Kenong - pX2).pow(2)).pow(-1)
        val l1Gong = ((x1Gong - pX1).pow(2)).pow(-1)
        val l2Gong = ((x1Gong - pX2).pow(2)).pow(-1)
        val l1Angklung = ((x1Angklung - pX1).pow(2)).pow(-1)
        val l2Angklung = ((x1Angklung - pX2).pow(2)).pow(-1)

        if(l1Barong > l2Barong) {
            clusterBarong = "C1"
        }
        if(l1Barong < l2Barong) {
            clusterBarong = "C2"
        }
        if(l1Jathil > l2Jathil) {
            clusterJathil = "C1"
        }
        if(l1Jathil < l2Jathil) {
            clusterJathil = "C2"
        }
        if(l1Klonosewandono > l2Klonosewandono) {
            clusterKlonosewandono = "C1"
        }
        if(l1Klonosewandono < l2Klonosewandono) {
            clusterKlonosewandono = "C2"
        }
        if(l1Bujang > l2Bujang) {
            clusterBujang = "C1"
        }
        if(l1Bujang < l2Bujang) {
            clusterBujang = "C2"
        }
        if(l1Warog > l2Warog) {
            clusterWarog = "C1"
        }
        if(l1Warog < l2Warog) {
            clusterWarog = "C2"
        }
        if(l1Gendang > l2Gendang) {
            clusterGendang = "C1"
        }
        if(l1Gendang < l2Gendang) {
            clusterGendang = "C2"
        }
        if(l1Ketipung > l2Ketipung) {
            clusterKetipung = "C1"
        }
        if(l1Ketipung < l2Ketipung) {
            clusterKetipung = "C2"
        }
        if(l1Slompret > l2Slompret) {
            clusterSlompret = "C1"
        }
        if(l1Slompret < l2Slompret) {
            clusterSlompret = "C2"
        }
        if(l1Kenong > l2Kenong) {
            clusterKenong = "C1"
        }
        if(l1Kenong < l2Kenong) {
            clusterKenong = "C2"
        }
        if(l1Gong > l2Gong) {
            clusterGong = "C1"
        }
        if(l1Gong < l2Gong) {
            clusterGong = "C2"
        }
        if(l1Angklung > l2Angklung) {
            clusterAngklung = "C1"
        }
        if(l1Angklung < l2Angklung) {
            clusterAngklung = "C2"
        }
    }
}