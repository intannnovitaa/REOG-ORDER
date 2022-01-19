package com.example.reogorder.model

class Sanggar {
    lateinit var id_sanggar:String
    lateinit var nama_sanggar:String
    lateinit var alamat_sanggar:String
    lateinit var nohp_sanggar:String
    lateinit var total_sewa:String
    lateinit var cluster:String

    constructor(){}
    constructor(id_sanggar:String, nama_sanggar:String, alamat_sanggar:String, nohp_sanggar:String, total_sewa: String, cluster: String){
        this.id_sanggar = id_sanggar
        this.nama_sanggar = nama_sanggar
        this.alamat_sanggar = alamat_sanggar
        this.nohp_sanggar = nohp_sanggar
        this.total_sewa = total_sewa
        this.cluster = cluster
    }
}