package com.example.reogorder.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.example.reogorder.R
import com.example.reogorder.model.Item
import com.example.reogorder.model.Sanggar
import com.google.firebase.database.*
import java.text.DecimalFormat
import java.text.NumberFormat

class ActivityEditAdmin : AppCompatActivity() {
    lateinit var namaSanggar: EditText
    lateinit var alamatSanggar: EditText
    lateinit var telpSanggar: EditText

    lateinit var namaBarong: TextView
    lateinit var hargaBarong: EditText
    lateinit var stokBarong: EditText
    lateinit var namaJathil: TextView
    lateinit var hargaJathil: EditText
    lateinit var stokJathil: EditText
    lateinit var namaKlonosewandono: TextView
    lateinit var hargaKlonosewandono: EditText
    lateinit var stokKlonosewandono: EditText
    lateinit var namaBujang: TextView
    lateinit var hargaBujang: EditText
    lateinit var stokBujang: EditText
    lateinit var namaWarog: TextView
    lateinit var hargaWarog: EditText
    lateinit var stokWarog: EditText
    lateinit var namaGendang: TextView
    lateinit var hargaGendang: EditText
    lateinit var stokGendang: EditText
    lateinit var namaKetipung: TextView
    lateinit var hargaKetipung: EditText
    lateinit var stokKetipung: EditText
    lateinit var namaSlompret: TextView
    lateinit var hargaSlompret: EditText
    lateinit var stokSlompret: EditText
    lateinit var namaKenong: TextView
    lateinit var hargaKenong: EditText
    lateinit var stokKenong: EditText
    lateinit var namaGong: TextView
    lateinit var hargaGong: EditText
    lateinit var stokGong: EditText
    lateinit var namaAngklung: TextView
    lateinit var hargaAngklung: EditText
    lateinit var stokAngklung: EditText

    lateinit var btnSimpan: Button
    lateinit var btnHapus: Button
    lateinit var databaseSanggar: DatabaseReference
    lateinit var databaseItem: DatabaseReference
    var idSanggar = ""
    var idItem = arrayListOf<String>()

    data class harga(val nama: String = "", val harga_item: String = "")
    var hargaItem = arrayListOf<harga>()
    var formatNumber: NumberFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_admin)

        namaSanggar = findViewById(R.id.namaSanggar)
        alamatSanggar = findViewById(R.id.alamatSanggar)
        telpSanggar = findViewById(R.id.telpSanggar)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnHapus = findViewById(R.id.btnHapus)

        namaBarong = findViewById(R.id.namaBarong)
        hargaBarong = findViewById(R.id.hargaBarong)
        stokBarong = findViewById(R.id.stokBarong)
        namaJathil = findViewById(R.id.namaJathil)
        hargaJathil = findViewById(R.id.hargaJathil)
        stokJathil = findViewById(R.id.stokJathil)
        namaKlonosewandono = findViewById(R.id.namaKlonosewandono)
        hargaKlonosewandono = findViewById(R.id.hargaKlonosewandono)
        stokKlonosewandono = findViewById(R.id.stokKlonosewandono)
        namaBujang = findViewById(R.id.namaBujang)
        hargaBujang = findViewById(R.id.hargaBujang)
        stokBujang = findViewById(R.id.stokBujang)
        namaWarog = findViewById(R.id.namaWarog)
        hargaWarog = findViewById(R.id.hargaWarog)
        stokWarog = findViewById(R.id.stokWarog)
        namaGendang = findViewById(R.id.namaGendang)
        hargaGendang = findViewById(R.id.hargaGendang)
        stokGendang = findViewById(R.id.stokGendang)
        namaKetipung = findViewById(R.id.namaKetipung)
        hargaKetipung = findViewById(R.id.hargaKetipung)
        stokKetipung = findViewById(R.id.stokKetipung)
        namaSlompret = findViewById(R.id.namaSlompret)
        hargaSlompret = findViewById(R.id.hargaSlompret)
        stokSlompret = findViewById(R.id.stokSlompret)
        namaKenong = findViewById(R.id.namaKenong)
        hargaKenong = findViewById(R.id.hargaKenong)
        stokKenong = findViewById(R.id.stokKenong)
        namaGong = findViewById(R.id.namaGong)
        hargaGong = findViewById(R.id.hargaGong)
        stokGong = findViewById(R.id.stokGong)
        namaAngklung = findViewById(R.id.namaAngklung)
        hargaAngklung = findViewById(R.id.hargaAngklung)
        stokAngklung = findViewById(R.id.stokAngklung)

        readSanggar()

        btnSimpan.setOnClickListener {
            setSanggar()
        }
        btnHapus.setOnClickListener {
            deleteItem()
        }
    }

    private fun setSanggar() {
        val nama_sanggar = namaSanggar.text.toString().trim()
        val alamat_sanggar = alamatSanggar.text.toString().trim()
        val nohp_sanggar = telpSanggar.text.toString().trim()

        databaseSanggar = FirebaseDatabase.getInstance().getReference("sanggar")
        if(idSanggar.equals("")) {
            idSanggar = databaseSanggar.push().key.toString()

            for (n in 0..10)
                idItem.add(idSanggar + n.toString())
        }

        if(!TextUtils.isEmpty(nama_sanggar) && !TextUtils.isEmpty(alamat_sanggar) && !TextUtils.isEmpty(nohp_sanggar)) {
            val add = Sanggar(idSanggar, nama_sanggar, alamat_sanggar, nohp_sanggar)
            databaseSanggar.child(idSanggar).setValue(add).addOnCompleteListener {
                addItem()
            }
            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun addItem() {
        databaseItem = FirebaseDatabase.getInstance().getReference("item").child(idSanggar)

        val nama_barong = namaBarong.text.toString().trim()
        val harga_barong = hargaBarong.text.toString().trim()
        val stok_barong = stokBarong.text.toString().trim()
        val nama_jathil = namaJathil.text.toString().trim()
        val harga_jathil = hargaJathil.text.toString().trim()
        val stok_jathil = stokJathil.text.toString().trim()
        val nama_klonosewandono = namaKlonosewandono.text.toString().trim()
        val harga_klonosewandono = hargaKlonosewandono.text.toString().trim()
        val stok_klonosewandono = stokKlonosewandono.text.toString().trim()
        val nama_bujang = namaBujang.text.toString().trim()
        val harga_bujang = hargaBujang.text.toString().trim()
        val stok_bujang = stokBujang.text.toString().trim()
        val nama_warog = namaWarog.text.toString().trim()
        val harga_warog = hargaWarog.text.toString().trim()
        val stok_warog = stokWarog.text.toString().trim()
        val nama_gendang = namaGendang.text.toString().trim()
        val harga_gendang = hargaGendang.text.toString().trim()
        val stok_gendang = stokGendang.text.toString().trim()
        val nama_ketipung = namaKetipung.text.toString().trim()
        val harga_ketipung = hargaKetipung.text.toString().trim()
        val stok_ketipung = stokKetipung.text.toString().trim()
        val nama_slompret = namaSlompret.text.toString().trim()
        val harga_slompret = hargaSlompret.text.toString().trim()
        val stok_slompret = stokSlompret.text.toString().trim()
        val nama_kenong = namaKenong.text.toString().trim()
        val harga_kenong = hargaKenong.text.toString().trim()
        val stok_kenong = stokKenong.text.toString().trim()
        val nama_gong = namaGong.text.toString().trim()
        val harga_gong = hargaGong.text.toString().trim()
        val stok_gong = stokGong.text.toString().trim()
        val nama_angklung = namaAngklung.text.toString().trim()
        val harga_angklung = hargaAngklung.text.toString().trim()
        val stok_angklung = stokAngklung.text.toString().trim()

        val item = arrayListOf(
            Item(idItem[0], idSanggar, nama_barong, harga_barong, stok_barong),
            Item(idItem[1], idSanggar, nama_jathil, harga_jathil, stok_jathil),
            Item(idItem[2], idSanggar, nama_klonosewandono, harga_klonosewandono, stok_klonosewandono),
            Item(idItem[3], idSanggar, nama_bujang, harga_bujang, stok_bujang),
            Item(idItem[4], idSanggar, nama_warog, harga_warog, stok_warog),
            Item(idItem[5], idSanggar, nama_gendang, harga_gendang, stok_gendang),
            Item(idItem[6], idSanggar, nama_ketipung, harga_ketipung, stok_ketipung),
            Item(idItem[7], idSanggar, nama_slompret, harga_slompret, stok_slompret),
            Item(idItem[8], idSanggar, nama_kenong, harga_kenong, stok_kenong),
            Item(idItem[9], idSanggar, nama_gong, harga_gong, stok_gong),
            Item(idItem[10], idSanggar, nama_angklung, harga_angklung, stok_angklung)
        )

        if(!TextUtils.isEmpty(nama_barong) && !TextUtils.isEmpty(harga_barong) && !TextUtils.isEmpty(stok_barong) &&
            !TextUtils.isEmpty(nama_jathil) && !TextUtils.isEmpty(harga_jathil) && !TextUtils.isEmpty(stok_jathil) &&
            !TextUtils.isEmpty(nama_klonosewandono) && !TextUtils.isEmpty(harga_klonosewandono) &&
            !TextUtils.isEmpty(stok_klonosewandono) && !TextUtils.isEmpty(nama_bujang) && !TextUtils.isEmpty(harga_bujang) &&
            !TextUtils.isEmpty(stok_bujang) && !TextUtils.isEmpty(nama_warog) && !TextUtils.isEmpty(harga_warog) &&
            !TextUtils.isEmpty(stok_warog) && !TextUtils.isEmpty(nama_gendang) && !TextUtils.isEmpty(harga_gendang) &&
            !TextUtils.isEmpty(stok_gendang) && !TextUtils.isEmpty(nama_ketipung) && !TextUtils.isEmpty(harga_ketipung) &&
            !TextUtils.isEmpty(stok_ketipung) && !TextUtils.isEmpty(nama_slompret) && !TextUtils.isEmpty(harga_slompret) &&
            !TextUtils.isEmpty(stok_slompret) && !TextUtils.isEmpty(nama_kenong) && !TextUtils.isEmpty(harga_kenong) &&
            !TextUtils.isEmpty(stok_kenong) && !TextUtils.isEmpty(nama_gong) && !TextUtils.isEmpty(harga_gong) &&
            !TextUtils.isEmpty(stok_gong) && !TextUtils.isEmpty(nama_angklung) && !TextUtils.isEmpty(harga_angklung) &&
            !TextUtils.isEmpty(stok_angklung)) {

            var i = 0
            item.forEach {
                databaseItem.child(idItem[i]).setValue(it)
                i++
            }

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun readSanggar() {
        databaseSanggar = FirebaseDatabase.getInstance().reference
        val querySanggar = databaseSanggar.child("sanggar").orderByKey().equalTo(intent.getStringExtra("id_sanggar").toString())
        querySanggar.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Sanggar::class.java)
                    namaSanggar.text = Editable.Factory.getInstance().newEditable(allocation!!.nama_sanggar)
                    alamatSanggar.text = Editable.Factory.getInstance().newEditable(allocation.alamat_sanggar)
                    telpSanggar.text = Editable.Factory.getInstance().newEditable(allocation.nohp_sanggar)

                    idSanggar = allocation.id_sanggar
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        readItem()
    }

    private fun readItem() {
        databaseItem = FirebaseDatabase.getInstance().reference
        val queryItem = databaseItem.child("item").child(intent.getStringExtra("id_sanggar").toString())
        queryItem.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Item::class.java)
                    if(allocation!!.nama_item == "Barong"){
                        namaBarong.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaBarong.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokBarong.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Jathil"){
                        namaJathil.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaJathil.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokJathil.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Klonosewandono"){
                        namaKlonosewandono.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaKlonosewandono.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokKlonosewandono.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Bujang Ganong"){
                        namaBujang.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaBujang.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokBujang.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Warog"){
                        namaWarog.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaWarog.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokWarog.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Gendang"){
                        namaGendang.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaGendang.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokGendang.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Ketipung"){
                        namaKetipung.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaKetipung.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokKetipung.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Slompret"){
                        namaSlompret.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaSlompret.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokSlompret.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Kenong"){
                        namaKenong.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaKenong.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokKenong.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Gong"){
                        namaGong.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaGong.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokGong.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }
                    if(allocation.nama_item == "Angklung"){
                        namaAngklung.text = Editable.Factory.getInstance().newEditable(allocation.nama_item)
                        hargaAngklung.text = Editable.Factory.getInstance().newEditable(allocation.harga)
                        stokAngklung.text = Editable.Factory.getInstance().newEditable(allocation.stok)
                    }

                    hargaItem.add(harga(allocation.nama_item, allocation.harga))
                    idItem.add(allocation.id_item)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    private fun deleteItem() {
        databaseSanggar = FirebaseDatabase.getInstance().getReference("sanggar")
        val deleteSanggar = databaseSanggar.child(intent.getStringExtra("id_sanggar").toString())
        deleteSanggar.removeValue()
        databaseItem = FirebaseDatabase.getInstance().getReference("item")
        val deleteItem = databaseItem.child(intent.getStringExtra("id_sanggar").toString())
        deleteItem.removeValue()
        finish()
    }
}