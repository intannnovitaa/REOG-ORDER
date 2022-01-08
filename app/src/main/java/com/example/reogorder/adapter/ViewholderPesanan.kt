package com.example.reogorder.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R
import com.example.reogorder.model.Detail
import com.example.reogorder.model.Pesanan
import com.example.reogorder.model.Sanggar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewholderPesanan(itemView: View): RecyclerView.ViewHolder(itemView) {
    internal var mView: View = itemView
    private var mClickListener: ClickListener? = null

    init{
        itemView.setOnClickListener { view -> mClickListener!!.onItemClick(view, adapterPosition) }
        itemView.setOnLongClickListener { view ->
            mClickListener!!.onItemLongClick(view, adapterPosition)
            true
        }
    }

    fun setDetails(ctx: Context, pesanan: Pesanan) {
        val namaSanggar = mView.findViewById(R.id.sanggarPesanan) as TextView
        val tanggalPesanan = mView.findViewById(R.id.tanggalPesanan) as TextView
        val waktuPesanan = mView.findViewById(R.id.waktuPesanan) as TextView
        val alamatPesanan = mView.findViewById(R.id.alamatPesanan) as TextView
        val bayarPesanan = mView.findViewById(R.id.bayarPesanan) as TextView

        val query = FirebaseDatabase.getInstance().getReference("sanggar").orderByChild("id_sanggar").equalTo(pesanan.id_sanggar)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children) {
                        val us = h.getValue(Sanggar::class.java)
                        namaSanggar.text = us!!.nama_sanggar
                    }
                }
            }
        })
        tanggalPesanan.text = pesanan.tanggal
        waktuPesanan.text = pesanan.waktu
        alamatPesanan.text = pesanan.lokasi
        bayarPesanan.text = pesanan.total_bayar
    }

    interface ClickListener {
        fun onItemClick(view: View, position:Int)
        fun onItemLongClick(view: View, position:Int)
    }

    fun setOnClickListener(clickListener:ViewholderPesanan.ClickListener) {
        mClickListener = clickListener
    }
}