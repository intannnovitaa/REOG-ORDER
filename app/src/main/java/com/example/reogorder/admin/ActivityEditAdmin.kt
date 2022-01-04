package com.example.reogorder.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.example.reogorder.R
import com.example.reogorder.model.Item
import com.example.reogorder.model.Sanggar
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference

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
            if(intent.getStringExtra("id_sanggar").toString().isNotEmpty()) {
                addSanggar()
                deleteItem()
            } else {
                addSanggar()
            }
        }
        btnHapus.setOnClickListener {
            deleteItem()
        }
    }

    private fun addSanggar() {
        val nama_sanggar = namaSanggar.text.toString().trim()
        val alamat_sanggar = alamatSanggar.text.toString().trim()
        val nohp_sanggar = telpSanggar.text.toString().trim()

        databaseSanggar = FirebaseDatabase.getInstance().getReference("sanggar")
        idSanggar = databaseSanggar.push().key.toString()

        if(!TextUtils.isEmpty(nama_sanggar) && !TextUtils.isEmpty(alamat_sanggar) && !TextUtils.isEmpty(nohp_sanggar)) {
            val add = Sanggar(idSanggar, nama_sanggar, alamat_sanggar, nohp_sanggar)
            databaseSanggar.child(idSanggar).setValue(add)
            addItem()
            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun addItem() {
        val id_sanggar = idSanggar.trim()
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

        databaseItem = FirebaseDatabase.getInstance().getReference("item").child(id_sanggar)
        val id_item = databaseItem.push().key.toString()

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

            val addBarong = Item(id_item, id_sanggar, nama_barong, harga_barong, stok_barong)
            databaseItem.child(id_item + "1").setValue(addBarong)
            val addJathil = Item(id_item, id_sanggar, nama_jathil, harga_jathil, stok_jathil)
            databaseItem.child(id_item + "2").setValue(addJathil)
            val addKlonosewandono = Item(id_item, id_sanggar, nama_klonosewandono, harga_klonosewandono, stok_klonosewandono)
            databaseItem.child(id_item + "3").setValue(addKlonosewandono)
            val addBujang = Item(id_item, id_sanggar, nama_bujang, harga_bujang, stok_bujang)
            databaseItem.child(id_item + "4").setValue(addBujang)
            val addWarog = Item(id_item, id_sanggar, nama_warog, harga_warog, stok_warog)
            databaseItem.child(id_item + "5").setValue(addWarog)
            val addGendang = Item(id_item, id_sanggar, nama_gendang, harga_gendang, stok_gendang)
            databaseItem.child(id_item + "6").setValue(addGendang)
            val addKetipung = Item(id_item, id_sanggar, nama_ketipung, harga_ketipung, stok_ketipung)
            databaseItem.child(id_item + "7").setValue(addKetipung)
            val addSlompret = Item(id_item, id_sanggar, nama_slompret, harga_slompret, stok_slompret)
            databaseItem.child(id_item + "8").setValue(addSlompret)
            val addKenong = Item(id_item, id_sanggar, nama_kenong, harga_kenong, stok_kenong)
            databaseItem.child(id_item + "9").setValue(addKenong)
            val addGong = Item(id_item, id_sanggar, nama_gong, harga_gong, stok_gong)
            databaseItem.child(id_item + "10").setValue(addGong)
            val addAngklung = Item(id_item, id_sanggar, nama_angklung, harga_angklung, stok_angklung)
            databaseItem.child(id_item + "11").setValue(addAngklung)

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun readSanggar() {
        databaseSanggar = FirebaseDatabase.getInstance().reference
        val querySanggar = databaseSanggar.child("sanggar").orderByKey().equalTo(intent.getStringExtra("id_sanggar").toString())
        querySanggar.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Sanggar::class.java)
                    namaSanggar.text = Editable.Factory.getInstance().newEditable(allocation!!.nama_sanggar)
                    alamatSanggar.text = Editable.Factory.getInstance().newEditable(allocation.alamat_sanggar)
                    telpSanggar.text = Editable.Factory.getInstance().newEditable(allocation.nohp_sanggar)
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