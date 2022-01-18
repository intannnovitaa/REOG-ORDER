package com.example.reogorder.model

class Pesanan {
    lateinit var id_pesanan: String
    lateinit var id_sanggar: String
    lateinit var item: ArrayList<Detail>
    lateinit var id: String
    lateinit var waktu: String
    lateinit var tanggal: String
    lateinit var lokasi: String
    lateinit var barang: String
    lateinit var ppn: String
    lateinit var jarak: String
    lateinit var total_bayar: String
    lateinit var status: String
    lateinit var idNstatus: String

    constructor(){}
    constructor(id_pesanan: String, id_sanggar: String, item: ArrayList<Detail>, id: String, waktu: String, tanggal: String,
                lokasi: String, total_bayar: String, status: String, idNstatus: String, barang: String, jarak: String, ppn: String){
        this.id_pesanan = id_pesanan
        this.id_sanggar = id_sanggar
        this.item = item
        this.id = id
        this.waktu = waktu
        this.tanggal = tanggal
        this.lokasi = lokasi
        this.total_bayar = total_bayar
        this.status = status
        this.idNstatus = idNstatus
        this.barang = barang
        this.jarak = jarak
        this.ppn = ppn
    }
}