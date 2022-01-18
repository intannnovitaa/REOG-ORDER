package com.example.reogorder.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R
import com.example.reogorder.model.Sanggar

class ViewholderBeranda(itemView:View): RecyclerView.ViewHolder(itemView) {
    internal var mView:View = itemView
    private var mClickListener: ClickListener? = null
    var sanggar = Sanggar()

    init{
        itemView.setOnClickListener { view -> mClickListener!!.onItemClick(view, adapterPosition) }
        itemView.setOnLongClickListener { view ->
            mClickListener!!.onItemLongClick(view, adapterPosition)
            true
        }
    }

    fun setDetails(sanggar: Sanggar) {
        this.sanggar = sanggar
        val namaSanggar = mView.findViewById(R.id.namaSanggar) as TextView
        val alamatSanggar = mView.findViewById(R.id.alamatSanggar) as TextView
        val nohpSanggar = mView.findViewById(R.id.nohpSanggar) as TextView

        namaSanggar.text = sanggar.nama_sanggar
        alamatSanggar.text = sanggar.alamat_sanggar
        nohpSanggar.text = sanggar.nohp_sanggar
    }

    interface ClickListener {
        fun onItemClick(view:View, position:Int)
        fun onItemLongClick(view:View, position:Int)
    }

    fun setOnClickListener(clickListener: ClickListener) {
        mClickListener = clickListener
    }
}