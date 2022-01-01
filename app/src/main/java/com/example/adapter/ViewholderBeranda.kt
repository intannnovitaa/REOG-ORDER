package com.example.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R

class ViewholderBeranda(itemView:View): RecyclerView.ViewHolder(itemView) {
    internal var mView:View = itemView
    private var mClickListener: ClickListener? = null

    init{
        itemView.setOnClickListener { view -> mClickListener!!.onItemClick(view, adapterPosition) }
        itemView.setOnLongClickListener { view ->
            mClickListener!!.onItemLongClick(view, adapterPosition)
            true
        }
    }

    fun setDetails(ctx: Context, nama:String, alamat:String, nohp:String) {
        val namaSanggar = mView.findViewById(R.id.nama) as TextView
        val alamatSanggar = mView.findViewById(R.id.alamat) as TextView
        val nohpSanggar = mView.findViewById(R.id.nohp) as TextView

        namaSanggar.text = nama
        alamatSanggar.text = alamat
        nohpSanggar.text = nohp
    }

    interface ClickListener {
        fun onItemClick(view:View, position:Int)
        fun onItemLongClick(view:View, position:Int)
    }

    fun setOnClickListener(clickListener:ViewholderBeranda.ClickListener) {
        mClickListener = clickListener
    }
}