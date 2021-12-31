package com.example.model

class Sanggar {
    lateinit var id:String
    lateinit var nama:String
    lateinit var alamat:String
    lateinit var nohp:String
    lateinit var item: ArrayList<Item>

    constructor(){}
    constructor(id:String, nama:String, alamat:String, nohp:String, item:ArrayList<Item>){
        this.id = id
        this.nama = nama
        this.alamat = alamat
        this.nohp = nohp
        this.item = item
    }
}