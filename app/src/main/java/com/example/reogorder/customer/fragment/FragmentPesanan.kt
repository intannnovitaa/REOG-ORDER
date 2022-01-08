package com.example.reogorder.customer.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R
import com.example.reogorder.adapter.ViewholderPesanan
import com.example.reogorder.admin.ActivityUtamaAdmin
import com.example.reogorder.customer.ActivityUtama
import com.example.reogorder.model.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentPesanan : Fragment() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var SP: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pesanan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLayoutManager = LinearLayoutManager(requireActivity())
        mRecyclerView = requireView().findViewById(R.id.recyclerPesanan)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager
    }

    override fun onStart() {
        super.onStart()
        SP = activity?.applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val query = FirebaseDatabase.getInstance().getReference("pesanan").orderByChild("id").equalTo(SP.getString("id", ""))
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Pesanan, ViewholderPesanan>(
            Pesanan::class.java,
            R.layout.card_pesanan,
            ViewholderPesanan::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder: ViewholderPesanan, model: Pesanan, position:Int) {
                viewHolder.setDetails(activity!!.applicationContext, model)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewholderPesanan {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderPesanan.ClickListener {
                    override fun onItemClick(view:View, position:Int) {}
                    override fun onItemLongClick(view:View, position:Int) {}
                })
                return viewHolder
            }
        }
        mRecyclerView.adapter = firebaseRecyclerAdapter
    }

}