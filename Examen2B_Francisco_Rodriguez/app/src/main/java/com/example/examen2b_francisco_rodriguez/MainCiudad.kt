package com.example.examen2b_francisco_rodriguez

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_francisco_rodriguez.adapter.CiudadAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainCiudad : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listaCiudades: ArrayList<Ciudad>
    private var db = Firebase.firestore
    var idCiudad = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad_main)

        recyclerView = findViewById(R.id.recyclerCiudades)
        recyclerView.layoutManager = LinearLayoutManager(this)

        listaCiudades = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Ciudad").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val itemCiudad: Ciudad? = document.toObject(Ciudad::class.java)
                    if (itemCiudad != null) {
                        itemCiudad.id = document.id
                        println(itemCiudad.id)
                        listaCiudades.add(itemCiudad)
                    }
                }
                val adapter = CiudadAdapter(listaCiudades)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : CiudadAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        //Toast.makeText(this@RestaurantMain, "Test $position", Toast.LENGTH_SHORT).show()
                        val idCiudad = listaCiudades[position].id
                        Toast.makeText(this@MainCiudad, "Id:   $idCiudad", Toast.LENGTH_SHORT)
                            .show()
                        val btnEditarCiudad = findViewById<Button>(R.id.btnEditarCiudad)
                        btnEditarCiudad.setOnClickListener {
                            sentDataToOtherActiity(ActualizarCiudad::class.java, idCiudad)
                        }
                    }
                })
            }
        }

            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCrearCiudad = findViewById<Button>(R.id.btnCrearCiudad)
        btnCrearCiudad.setOnClickListener {
            goActivity(CrearCiudad::class.java)
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