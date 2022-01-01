package com.example.model

class User {
    lateinit var id: String
    lateinit var nama: String
    lateinit var email: String
    lateinit var password: String
    lateinit var nohp: String
    lateinit var alamat: String

    constructor(){}
    constructor(id:String, nama:String, email:String, password:String, nohp:String, alamat:String){
        this.id = id
        this.nama = nama
        this.email = email
        this.password = password
        this.nohp = nohp
        this.alamat = alamat
    }
}