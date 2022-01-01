package com.example.model

data class User(val id: String, val nama: String, val email: String, val password: String, val nohp: String, val alamat: String){
    constructor() : this("", "", "", "", "", "")
}