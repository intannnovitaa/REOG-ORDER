package com.example.reogorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.ViewholderItem
import com.example.model.Item
import com.example.model.Sanggar
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*

class ActivityItem : AppCompatActivity() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var databaseReference: DatabaseReference
    lateinit var sanggarItem: TextView
    lateinit var alamatItem: TextView
    lateinit var nohpItem: TextView
    lateinit var idsItem: TextView
    var idsang: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView = findViewById(R.id.recyclerItem)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = mLayoutManager

        sanggarItem = findViewById(R.id.sanggarItem)
        alamatItem = findViewById(R.id.alamatItem)
        nohpItem = findViewById(R.id.nohpItem)
        idsItem = findViewById(R.id.idsItem)

        databaseReference = FirebaseDatabase.getInstance().getReference("sanggar")
        val query = databaseReference.orderByChild("nama_sanggar").equalTo(intent.getStringExtra("nama"))
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot1 in datasnapshot.children) {
                    val allocation = snapshot1.getValue(Sanggar::class.java)
                    idsItem.text = allocation!!.id_sanggar
                    idsang = idsItem.text.toString()
                    sanggarItem.text = allocation.nama_sanggar
                    alamatItem.text = allocation.alamat_sanggar
                    nohpItem.text = allocation.nohp_sanggar
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, idsang.toString(), Toast.LENGTH_SHORT).show()

        val query = FirebaseDatabase.getInstance().getReference("item").orderByChild("id_sanggar").equalTo(idsItem.text.toString())
        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Item, ViewholderItem>(
            Item::class.java,
            R.layout.card_item,
            ViewholderItem::class.java,
            query
        ) {
            override fun populateViewHolder(viewHolder: ViewholderItem, model: Item, position:Int) {
                viewHolder.setDetails(applicationContext, model.nama_item, model.stok, model.harga)
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewholderItem {
                val viewHolder = super.onCreateViewHolder(parent, viewType)
                viewHolder.setOnClickListener(object: ViewholderItem.ClickListener {
                    override fun onItemClick(view: View, position:Int) {}
                    override fun onItemLongClick(view: View, position:Int) {}
                })
                return viewHolder
            }
        }
        mRecyclerView.adapter = firebaseRecyclerAdapter
    }
}