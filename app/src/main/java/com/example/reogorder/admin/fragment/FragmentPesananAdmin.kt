package com.example.reogorder.admin.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reogorder.R
import com.example.reogorder.adapter.ViewholderPesanan
import com.example.reogorder.ActivityDetail
import com.example.reogorder.model.Pesanan
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class FragmentPesananAdmin : Fragment() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var btnSelesai: Button
    lateinit var btnDiproses: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pesanan_admin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLayoutManager = LinearLayoutManager(requireActivity())
        mRecyclerView = requireView().findViewById(R.id.recyclerPesananAdmin)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()

        btnDiproses = requireView().findViewById(R.id.btnDiprosesAdmin)
        btnSelesai  = requireView().findViewById(R.id.btnSelesaiAdmin)

        val query = FirebaseDatabase.getInstance().getReference("pesanan").orderByChild("status").equalTo("Diproses")
        loadFirebase(query)
        btnDiproses.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        btnSelesai.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))

        btnDiproses.setOnClickListener {
            val query = FirebaseDatabase.getInstance().getReference("pesanan").orderByChild("status").equalTo("Diproses")
            loadFirebase(query)
            btnDiproses.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            btnSelesai.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        }
        btnSelesai.setOnClickListener {
            val query = FirebaseDatabase.getInstance().getReference("pesanan").orderByChild("status").equalTo("Selesai")
            loadFirebase(query)
            btnSelesai.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            btnDiproses.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
        }
    }

    fun loadFirebase(query: Query){
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Pesanan, ViewholderPesanan>(
            Pesanan::class.java,
            R.layout.card_pesanan,
            ViewholderPesanan::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder: ViewholderPesanan, model: Pesanan, position:Int) {
                viewHolder.setDetails(model)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewholderPesanan {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderPesanan.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        val intent = Intent(view.context, ActivityDetail::class.java)
                        intent.putExtra("id_pesanan", viewHolder.id_pesanan)
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