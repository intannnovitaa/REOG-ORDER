package com.example.reogorder.model

class Item {
    lateinit var id_item: String
    lateinit var id_sanggar: String
    lateinit var nama_item: String
    lateinit var harga: String
    lateinit var stok: String

    constructor(){}
    constructor(id_item:String, id_sanggar:String, nama_item:String, harga:String, stok:String){
        this.id_item = id_item
        this.id_sanggar = id_sanggar
        this.nama_item = nama_item
        this.harga = harga
        this.stok = stok
    }
}