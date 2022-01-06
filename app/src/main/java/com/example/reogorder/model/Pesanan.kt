package com.example.reogorder.model

class Pesanan {
    lateinit var id_pesanan: String
    lateinit var sanggar: Sanggar
    lateinit var item: ArrayList<Item>

    constructor(){}
    constructor(id_pesanan:String, sanggar: Sanggar, item: ArrayList<Item>){
        this.id_pesanan = id_pesanan
        this.sanggar = sanggar
        this.item    = item
    }
}