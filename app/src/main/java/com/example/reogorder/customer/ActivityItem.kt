package com.example.reogorder.customer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.reogorder.model.Item
import com.example.reogorder.model.Sanggar
import com.example.reogorder.R
import com.example.reogorder.model.Pesanan
import com.google.firebase.database.*
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken

class ActivityItem : AppCompatActivity() {
    lateinit var databaseSanggar: DatabaseReference
    lateinit var databaseItem: DatabaseReference
    lateinit var sanggarItem: TextView
    lateinit var alamatItem: TextView
    lateinit var nohpItem: TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        SP = getSharedPreferences("Login", Context.MODE_PRIVATE)

        sanggarItem = findViewById(R.id.sanggarItem)
        alamatItem = findViewById(R.id.alamatItem)
        nohpItem = findViewById(R.id.nohpItem)
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

        databaseSanggar = FirebaseDatabase.getInstance().getReference("sanggar")
        val query = databaseSanggar.orderByKey().equalTo(intent.getStringExtra("id_sanggar").toString())
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Sanggar::class.java)
                    sanggarItem.text = allocation!!.nama_sanggar
                    alamatItem.text = allocation.alamat_sanggar
                    nohpItem.text = allocation.nohp_sanggar

                    idSanggar = allocation.id_sanggar
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        loadItem()
        setJumlah()
        checkOldData()

//        btnCheckout.setOnClickListener {
//            val id_pesanan = getRandomString(6)
//            val id_sanggar = intent.getStringExtra("id_sanggar").toString()
//            for (it in itemArray){
//                if(it.nama_item == "Barong")
//                    it.stok = countBarong.toString()
//                else if(it.nama_item == "Jathil")
//                    it.stok = countJathil.toString()
//                else if(it.nama_item == "Klonosewandono")
//                    it.stok = countKlonosewandono.toString()
//                else if(it.nama_item == "Bujang Ganong")
//                    it.stok = countBujang.toString()
//                else if(it.nama_item == "Warog")
//                    it.stok = countWarog.toString()
//                else if(it.nama_item == "Gendang")
//                    it.stok = countGendang.toString()
//                else if(it.nama_item == "Ketipung")
//                    it.stok = countKetipung.toString()
//                else if(it.nama_item == "Slompret")
//                    it.stok = countSlompret.toString()
//                else if(it.nama_item == "Kenong")
//                    it.stok = countKenong.toString()
//                else if(it.nama_item == "Gong")
//                    it.stok = countGong.toString()
//                else
//                    it.stok = countAngklung.toString()
//            }
//
//            val pesanan = Pesanan(
//                id_pesanan,
//                Sanggar(id_sanggar, sanggarItem.text.toString(), alamatItem.text.toString(), nohpItem.text.toString()),
//                itemArray
//            )
//
//            var semua_pesanan = arrayListOf<Pesanan>()
//            val sp_pesanan = SP.getString("pesanan", "").toString()
//            if(sp_pesanan != ""){
//                semua_pesanan = Gson().fromJson<ArrayList<Pesanan>>(sp_pesanan, object : TypeToken<ArrayList<Pesanan>>(){}.type)
//            }
//            semua_pesanan.add(pesanan)
//
//            val editor = SP.edit()
//            editor.putString("pesanan", Gson().toJson(semua_pesanan))
//            editor.apply()
//
//            val intent = Intent(this, ActivityCheckout::class.java)
//            startActivity(intent)
//        }

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
            val hargaBarongI = hargaBarongItem.text.toString()
            val jumlahBarongI = jumlahBarongItem.text.toString()
            val namaJathilI = namaJathilItem.text.toString()
            val hargaJathilI = hargaJathilItem.text.toString()
            val jumlahJathilI = jumlahJathilItem.text.toString()
            val namaKlonosewandonoI = namaKlonosewandonoItem.text.toString()
            val hargaKlonosewandonoI = hargaKlonosewandonoItem.text.toString()
            val jumlahKlonosewandonoI = jumlahKlonosewandonoItem.text.toString()
            val namaBujangI = namaBujangItem.text.toString()
            val hargaBujangI = hargaBujangItem.text.toString()
            val jumlahBujangI = jumlahBujangItem.text.toString()
            val namaWarogI = namaWarogItem.text.toString()
            val hargaWarogI = hargaWarogItem.text.toString()
            val jumlahWarogI = jumlahWarogItem.text.toString()
            val namaGendangI = namaGendangItem.text.toString()
            val hargaGendangI = hargaGendangItem.text.toString()
            val jumlahGendangI = jumlahGendangItem.text.toString()
            val namaKetipungI = namaKetipungItem.text.toString()
            val hargaKetipungI = hargaKetipungItem.text.toString()
            val jumlahKetipungI = jumlahKetipungItem.text.toString()
            val namaSlompretI = namaSlompretItem.text.toString()
            val hargaSlompretI = hargaSlompretItem.text.toString()
            val jumlahSlompretI = jumlahSlompretItem.text.toString()
            val namaKenongI = namaKenongItem.text.toString()
            val hargaKenongI = hargaKenongItem.text.toString()
            val jumlahKenongI = jumlahKenongItem.text.toString()
            val namaGongI = namaGongItem.text.toString()
            val hargaGongI = hargaGongItem.text.toString()
            val jumlahGongI = jumlahGongItem.text.toString()
            val namaAngklungI = namaAngklungItem.text.toString()
            val hargaAngklungI = hargaAngklungItem.text.toString()
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
            startActivity(intent)
        }
    }


//    fun getRandomString(length: Int) : String {
//        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
//        return (1..length)
//            .map { charset.random() }
//            .joinToString("")
//    }

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
                        hargaBarongItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Jathil"){
                        idJathil = allocation.id_item
                        namaJathilItem.text = allocation.nama_item
                        stokJathilItem.text = allocation.stok
                        hargaJathilItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Klonosewandono"){
                        idKlonosewandono = allocation.id_item
                        namaKlonosewandonoItem.text = allocation.nama_item
                        stokKlonosewandonoItem.text = allocation.stok
                        hargaKlonosewandonoItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Bujang Ganong"){
                        idBujang = allocation.id_item
                        namaBujangItem.text = allocation.nama_item
                        stokBujangItem.text = allocation.stok
                        hargaBujangItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Warog"){
                        idWarog = allocation.id_item
                        namaWarogItem.text = allocation.nama_item
                        stokWarogItem.text = allocation.stok
                        hargaWarogItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Gendang"){
                        idGendang = allocation.id_item
                        namaGendangItem.text = allocation.nama_item
                        stokGendangItem.text = allocation.stok
                        hargaGendangItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Ketipung"){
                        idKetipung = allocation.id_item
                        namaKetipungItem.text = allocation.nama_item
                        stokKetipungItem.text = allocation.stok
                        hargaKetipungItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Slompret"){
                        idSlompret = allocation.id_item
                        namaSlompretItem.text = allocation.nama_item
                        stokSlompretItem.text = allocation.stok
                        hargaSlompretItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Kenong"){
                        idKenong = allocation.id_item
                        namaKenongItem.text = allocation.nama_item
                        stokKenongItem.text = allocation.stok
                        hargaKenongItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Gong"){
                        idGong = allocation.id_item
                        namaGongItem.text = allocation.nama_item
                        stokGongItem.text = allocation.stok
                        hargaGongItem.text = allocation.harga
                    }
                    if(allocation.nama_item == "Angklung"){
                        idAngklung = allocation.id_item
                        namaAngklungItem.text = allocation.nama_item
                        stokAngklungItem.text = allocation.stok
                        hargaAngklungItem.text = allocation.harga
                    }

//                    itemArray.add(allocation)
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
//                            Log.d("olddata", value!!.status)
                            if(value!!.id_sanggar.equals(intent.getStringExtra("id_sanggar").toString()) && value.status == "Diproses"){
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
                                                        if(vl!!.nama_item == "Barong"){
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
}