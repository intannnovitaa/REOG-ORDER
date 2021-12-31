package com.example.reogorder

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.ViewholderBeranda
import com.example.model.Sanggar
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*

class FragmentBeranda : Fragment() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView

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
        val query = FirebaseDatabase.getInstance().getReference("sanggar")
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Sanggar, ViewholderBeranda>(
            Sanggar::class.java,
            R.layout.card_beranda,
            ViewholderBeranda::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder:ViewholderBeranda, model:Sanggar, position:Int) {
                viewHolder.setDetails(activity!!.applicationContext, model.nama, model.alamat, model.nohp)
            }
            override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewholderBeranda {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderBeranda.ClickListener {
                    override fun onItemClick(view:View, position:Int) {
                        val intent = Intent(activity, ActivityItem::class.java)
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