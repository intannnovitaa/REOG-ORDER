package com.example.reogorder.model

class Detail {
    lateinit var id_detail: String
    lateinit var jumlah: String

    constructor(){}
    constructor(id_detail: String, jumlah: String){
        this.id_detail = id_detail
        this.jumlah = jumlah
    }
}