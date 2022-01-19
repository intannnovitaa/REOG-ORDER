package com.example.reogorder.customer.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.adapter.ViewholderBeranda
import com.example.reogorder.model.Sanggar
import com.example.reogorder.customer.ActivityItem
import com.example.reogorder.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*

class FragmentBeranda : Fragment() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var SP: SharedPreferences

    lateinit var btnHigh: Button
    lateinit var btnMedium: Button
    lateinit var btnLow: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLayoutManager = LinearLayoutManager(requireActivity())
        mRecyclerView = requireView().findViewById(R.id.recyclerBeranda)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager

        btnHigh = requireView().findViewById(R.id.btnHigh)
        btnMedium = requireView().findViewById(R.id.btnMedium)
        btnLow = requireView().findViewById(R.id.btnLow)
    }

    override fun onStart() {
        super.onStart()

        SP = activity?.applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val txtName = activity?.findViewById<TextView>(R.id.txtName)
        txtName!!.text = SP.getString("nama", "")

        val query = FirebaseDatabase.getInstance().getReference("sanggar").orderByChild("cluster").equalTo("high")
        recycleView(query)
        btnHigh.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        btnMedium.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        btnLow.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))

        btnHigh.setOnClickListener {
            val query = FirebaseDatabase.getInstance().getReference("sanggar").orderByChild("cluster").equalTo("high")
            recycleView(query)
            btnHigh.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            btnMedium.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            btnLow.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        }
        btnMedium.setOnClickListener {
            val query = FirebaseDatabase.getInstance().getReference("sanggar").orderByChild("cluster").equalTo("medium")
            recycleView(query)
            btnHigh.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            btnMedium.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            btnLow.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        }
        btnLow.setOnClickListener {
            val query = FirebaseDatabase.getInstance().getReference("sanggar").orderByChild("cluster").equalTo("low")
            recycleView(query)
            btnHigh.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            btnMedium.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            btnLow.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    fun recycleView(query: Query){
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Sanggar, ViewholderBeranda>(
            Sanggar::class.java,
            R.layout.card_beranda,
            ViewholderBeranda::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder:ViewholderBeranda, model:Sanggar, position:Int) {
                viewHolder.setDetails(model)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewholderBeranda {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderBeranda.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        val intent = Intent(view.context, ActivityItem::class.java)
                        intent.putExtra("id_sanggar", viewHolder.sanggar.id_sanggar)
                        val ppn = fuzzyCmeans(viewHolder.sanggar.total_sewa.toInt())
                        Log.d("ppn", ppn.toString())
                        intent.putExtra("ppn", ppn)
                        startActivity(intent)
                    }
                    override fun onItemLongClick(view:View, position:Int) {}
                })
                return viewHolder
            }
        }
        mRecyclerView.adapter = firebaseRecyclerAdapter
    }

    fun fuzzyCmeans(jumlah: Int): Int {
        if(jumlah == 0)
            return 0
        else if(jumlah > 0 && jumlah < 5)
            return 150000
        else if(jumlah == 5)
            return 300000
        else
            return 500000
    }
}