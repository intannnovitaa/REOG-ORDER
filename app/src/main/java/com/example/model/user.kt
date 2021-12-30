package com.example.model

data class user(val id: Int, val email: String, val nohp: String, val alamat: String, val password: String){
    constructor() : this(0, "", "", "", "")
}