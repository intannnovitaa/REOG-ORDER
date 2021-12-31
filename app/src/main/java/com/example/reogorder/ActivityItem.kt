package com.example.reogorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Sanggar
import com.google.firebase.database.*

class ActivityItem : AppCompatActivity() {
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var databaseReference: DatabaseReference
    lateinit var sanggarItem: TextView
    lateinit var alamatItem: TextView
    lateinit var nohpItem: TextView
//    lateinit var id: String

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

        databaseReference = FirebaseDatabase.getInstance().getReference("sanggar")
        val query = databaseReference.orderByChild("nama").equalTo(intent.getStringExtra("nama"))
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot != null) {
                    for (snapshot1 in datasnapshot.children) {
                        val allocation = snapshot1.getValue(Sanggar::class.java)
//                        id = allocation!!.id
                        sanggarItem.text = allocation!!.nama
                        alamatItem.text = allocation.alamat
                        nohpItem.text = allocation.nohp
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

//    override fun onStart() {
//        super.onStart()
//        val query = FirebaseDatabase.getInstance().getReference("sanggar").child("$id").orderByChild("item")
//        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Item, ViewholderItem>(
//            Item::class.java,
//            R.layout.card_item,
//            ViewholderItem::class.java,
//            query
//        ) {
//            override fun populateViewHolder(viewHolder: ViewholderItem, model: Item, position:Int) {
//                viewHolder.setDetails(applicationContext, model.nama, model.stok, model.harga)
//            }
//            override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewholderItem {
//                val viewHolder = super.onCreateViewHolder(parent, viewType)
//                viewHolder.setOnClickListener(object: ViewholderItem.ClickListener {
//                    override fun onItemClick(view: View, position:Int) {}
//                    override fun onItemLongClick(view: View, position:Int) {}
//                })
//                return viewHolder
//            }
//        }
//        mRecyclerView.adapter = firebaseRecyclerAdapter
//    }
}