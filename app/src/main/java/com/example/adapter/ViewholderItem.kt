package com.example.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R

class ViewholderItem(itemView:View): RecyclerView.ViewHolder(itemView) {
    internal var mView:View = itemView
    private var mClickListener: ClickListener? = null

    init{
        itemView.setOnClickListener { view -> mClickListener!!.onItemClick(view, adapterPosition) }
        itemView.setOnLongClickListener { view ->
            mClickListener!!.onItemLongClick(view, adapterPosition)
            true
        }
    }

    fun setDetails(ctx: Context, nama_item:String, harga:String, stok:String) {
        val namaItem = mView.findViewById(R.id.namaItem) as TextView
        val stokItem = mView.findViewById(R.id.stokItem) as TextView
        val hargaItem = mView.findViewById(R.id.hargaItem) as TextView

        namaItem.text = nama_item
        stokItem.text = harga
        hargaItem.text = stok
    }

    interface ClickListener {
        fun onItemClick(view:View, position:Int)
        fun onItemLongClick(view:View, position:Int)
    }

    fun setOnClickListener(clickListener:ViewholderItem.ClickListener) {
        mClickListener = clickListener
    }
}