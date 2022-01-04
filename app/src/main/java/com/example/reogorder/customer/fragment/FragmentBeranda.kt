package com.example.reogorder.customer.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLayoutManager = LinearLayoutManager(requireActivity())
        mRecyclerView = requireView().findViewById(R.id.recyclerBeranda)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager
    }

    override fun onStart() {
        super.onStart()
        SP = activity?.applicationContext!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val txtName = activity?.findViewById<TextView>(R.id.txtName)
        txtName!!.text = SP.getString("nama", "")

        val query = FirebaseDatabase.getInstance().getReference("sanggar")
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Sanggar, ViewholderBeranda>(
            Sanggar::class.java,
            R.layout.card_beranda,
            ViewholderBeranda::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder:ViewholderBeranda, model:Sanggar, position:Int) {
                viewHolder.setDetails(activity!!.applicationContext, model.id_sanggar, model.nama_sanggar, model.alamat_sanggar, model.nohp_sanggar)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewholderBeranda {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderBeranda.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        val intent = Intent(view.context, ActivityItem::class.java)
                        intent.putExtra("id_sanggar", viewHolder.id_sanggar)
                        startActivity(intent)
                    }
                    override fun onItemLongClick(view:View, position:Int) {}
                })
                return viewHolder
            }
        }
        mRecyclerView.adapter = firebaseRecyclerAdapter
    }
}