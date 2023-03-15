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

        val btnGuardarProvincias = findViewById<Button>(R.id.btnGuardarProvincia)
        btnGuardarProvincias.setOnClickListener {
            if (etNombreProvincia.text.isNotEmpty()
                && etFechaDeFundacion.text.isNotEmpty()
                && etGradoDeSeguridad.text.isNotEmpty()
                && etEstaEnFiestasProvinciales.text.isNotEmpty()
            ) {
                val transformacionEstaEnFiestasProvinciales = etEstaEnFiestasProvinciales.text.toString()
                val resultadoEstaEnFiestasProvinciales = transformacionEstaEnFiestasProvinciales.toBoolean()

                val transformacionGradoDeSeguridad = etGradoDeSeguridad.text.toString()
                val resultadoGradoDeSeguridadTransformado = transformacionGradoDeSeguridad.toDouble()

                val data = hashMapOf(
                    "nombre" to etNombreProvincia.text.toString(),
                    "estaEnFiestasProvinciales" to resultadoEstaEnFiestasProvinciales,
                    "fechaDeFundacion" to etFechaDeFundacion.text.toString(),
                    "gradoDeSeguridad" to resultadoGradoDeSeguridadTransformado
                )

                db.collection("Provincias").add(data).addOnSuccessListener {
                    Toast.makeText(this, "Se creó la provincia con éxito", Toast.LENGTH_SHORT)
                        .show()
                    goActivity(MainProvincia::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Se produjo un error al crear la provincia", Toast.LENGTH_SHORT).show()
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