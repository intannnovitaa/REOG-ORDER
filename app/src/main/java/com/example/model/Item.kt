package com.example.model

class Item {
    lateinit var id: String
    lateinit var nama: String
    lateinit var harga: String
    lateinit var stok: String

    constructor(){}
    constructor(id:String, nama:String, harga:String, stok:String){
        this.id = id
        this.nama = nama
        this.harga = harga
        this.stok = stok
    }
}