package com.example.reogorder.customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.reogorder.model.Item
import com.example.reogorder.model.Sanggar
import com.example.reogorder.R
import com.google.firebase.database.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

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
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        loadItem()
        setJumlah()

        btnCheckout.setOnClickListener {
            val intent = Intent(this, ActivityCheckout::class.java)
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
                        namaBarongItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokBarongItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaBarongItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Jathil"){
                        namaJathilItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokJathilItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaJathilItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Klonosewandono"){
                        namaKlonosewandonoItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokKlonosewandonoItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaKlonosewandonoItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Bujang Ganong"){
                        namaBujangItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokBujangItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaBujangItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Warog"){
                        namaWarogItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokWarogItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaWarogItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Gendang"){
                        namaGendangItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokGendangItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaGendangItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Ketipung"){
                        namaKetipungItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokKetipungItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaKetipungItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Slompret"){
                        namaSlompretItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokSlompretItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaSlompretItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Kenong"){
                        namaKenongItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokKenongItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaKenongItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Gong"){
                        namaGongItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokGongItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaGongItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                    }
                    if(allocation.nama_item == "Angklung"){
                        namaAngklungItem.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        stokAngklungItem.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                        hargaAngklungItem.text = Editable.Factory.getInstance().newEditable(allocation.harga)
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
}