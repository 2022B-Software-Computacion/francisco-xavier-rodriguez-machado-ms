package com.example.examen2b_francisco_rodriguez

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_francisco_rodriguez.adapter.ProvinciaAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainProvincia : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaProvincias: ArrayList<Provincia>
    private var db = Firebase.firestore
    var idProvincia = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincia_main)

        recyclerView = findViewById(R.id.recyclerProvincias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        listaProvincias = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Provincias").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val itemProvincia: Provincia? = document.toObject(Provincia::class.java)
                    if (itemProvincia != null) {
                        itemProvincia.id = document.id
                        println(itemProvincia.id)
                        listaProvincias.add(itemProvincia)
                    }
                }

                val adapter = ProvinciaAdapter(listaProvincias)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : ProvinciaAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        //Toast.makeText(this@RestaurantMain, "Test $position", Toast.LENGTH_SHORT).show()
                        val idProvincia = listaProvincias[position].id
                        Toast.makeText(this@MainProvincia, "Id:   $idProvincia", Toast.LENGTH_SHORT)
                            .show()
                        val btnEditRestaurant = findViewById<Button>(R.id.btnEditarProvincia)
                        btnEditRestaurant.setOnClickListener {
                            sentDataToOtherActiity(ActualizarProvincia::class.java, idProvincia)
                        }
                    }
                })
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCreateRestaurant = findViewById<Button>(R.id.btnCrearProvincia)
        btnCreateRestaurant.setOnClickListener {
            goActivity(CrearProvincia::class.java)
        }

    }

    private fun goActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun sentDataToOtherActiity(activity: Class<*>, id: String) {
        val intent = Intent(this, activity)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}