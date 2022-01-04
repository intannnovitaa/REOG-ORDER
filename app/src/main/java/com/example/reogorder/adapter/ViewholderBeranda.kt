package com.example.reogorder.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R

class ViewholderBeranda(itemView:View): RecyclerView.ViewHolder(itemView) {
    internal var mView:View = itemView
    private var mClickListener: ClickListener? = null
    var id_sanggar = ""

    init{
        itemView.setOnClickListener { view -> mClickListener!!.onItemClick(view, adapterPosition) }
        itemView.setOnLongClickListener { view ->
            mClickListener!!.onItemLongClick(view, adapterPosition)
            true
        }
    }

    fun setDetails(ctx: Context, id:String, nama_sanggar:String, alamat_sanggar:String, nohp_sanggar:String) {
        this.id_sanggar = id
        val namaSanggar = mView.findViewById(R.id.namaSanggar) as TextView
        val alamatSanggar = mView.findViewById(R.id.alamatSanggar) as TextView
        val nohpSanggar = mView.findViewById(R.id.nohpSanggar) as TextView

        namaSanggar.text = nama_sanggar
        alamatSanggar.text = alamat_sanggar
        nohpSanggar.text = nohp_sanggar
    }

    interface ClickListener {
        fun onItemClick(view:View, position:Int)
        fun onItemLongClick(view:View, position:Int)
    }

    fun setOnClickListener(clickListener:ViewholderBeranda.ClickListener) {
        mClickListener = clickListener
    }
}