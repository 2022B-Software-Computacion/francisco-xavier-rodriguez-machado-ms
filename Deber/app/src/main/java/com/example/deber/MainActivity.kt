package com.example.deber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber.adapter.KfcAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        //initRecyclerView2()
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerkfc)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = KfcAdapter(KfcProveedor.kfcLista1)
    }

    /*fun initRecyclerView2(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerkfcbatalla)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = KfcAdapter2(KfcProveedor2.kfcLista2)
    }*/

}