package com.example.examen2b_francisco_rodriguez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProvincia : AppCompatActivity() {

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_provincia)

        val etNombreProvincia = findViewById<EditText>(R.id.etNombreProvincia)
        val etFechaDeFundacion = findViewById<EditText>(R.id.etFechaDeFundacion)
        val etGradoDeSeguridad = findViewById<EditText>(R.id.etGradoDeSeguridad)
        val etEstaEnFiestasProvinciales = findViewById<EditText>(R.id.etEstaEnFiestasProvinciales)

        val btnSaveRestaurant = findViewById<Button>(R.id.btnGuardarProvincia)
        btnSaveRestaurant.setOnClickListener {
            if (etNombreProvincia.text.isNotEmpty()
                && etFechaDeFundacion.text.isNotEmpty()
                && etGradoDeSeguridad.text.isNotEmpty()
                && etEstaEnFiestasProvinciales.text.isNotEmpty()
            ) {
                val transformAvailableRestaurant = etEstaEnFiestasProvinciales.text.toString()
                val resultAvailableRestaurant = transformAvailableRestaurant.toBoolean()

                val transformRatingRestaurant = etGradoDeSeguridad.text.toString()
                val resultRatingRestaurant = transformRatingRestaurant.toDouble()

                val data = hashMapOf(
                    "name" to etNombreProvincia.text.toString(),
                    "available" to resultAvailableRestaurant,
                    "openingDate" to etFechaDeFundacion.text.toString(),
                    "rating" to resultRatingRestaurant
                )

                db.collection("Provincias").add(data).addOnSuccessListener {
                    Toast.makeText(this, "Successfully created restaurant", Toast.LENGTH_SHORT)
                        .show()
                    goActivity(MainProvincia::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error creating a restaurant", Toast.LENGTH_SHORT).show()
                    goActivity(MainProvincia::class.java)
                }
            }
        }
    }

    private fun goActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}