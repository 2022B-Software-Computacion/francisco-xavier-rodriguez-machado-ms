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

class CrearCiudad : AppCompatActivity() {

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_ciudad)


        val etNombreCiudad = findViewById<EditText>(R.id.etNombreCiudad)
        val etFechaDeFundacionCiudad = findViewById<EditText>(R.id.etFechaDeFundacionCiudad)
        val etNumeroDeHabitantes = findViewById<EditText>(R.id.etNumeroDeHabitantes)
        val etEstaEnFiestasPatronales = findViewById<EditText>(R.id.etEstaEnFiestasPatronales)

        val btnGuardarCiudad = findViewById<Button>(R.id.btnGuardarCiudad)
        btnGuardarCiudad.setOnClickListener {
            if (etNombreCiudad.text.isNotEmpty()
                && etFechaDeFundacionCiudad.text.isNotEmpty()
                && etNumeroDeHabitantes.text.isNotEmpty()
                && etEstaEnFiestasPatronales.text.isNotEmpty()
            ) {
                val tranformarEstado = etEstaEnFiestasPatronales.text.toString()
                val resultadoTransformacion = tranformarEstado.toBoolean()

                val tranformarNumHabitantes = etNumeroDeHabitantes.text.toString()
                val resultadoTransformacionNumHabitantes = tranformarNumHabitantes.toInt()

                val data = hashMapOf(
                    "nombre" to etNombreCiudad.text.toString(),
                    "estaEnFiestasPatronales" to resultadoTransformacion,
                    "fechaDeFundacion" to etFechaDeFundacionCiudad.text.toString(),
                    "numeroDeHabitantes" to resultadoTransformacionNumHabitantes
                )

                db.collection("ciudades").add(data).addOnSuccessListener {
                    Toast.makeText(this, "Creación éxitosa de la ciudad", Toast.LENGTH_SHORT).show()
                    irActividad(MainProvincia::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Se produjo un error al crear la ciudad", Toast.LENGTH_SHORT).show()
                    irActividad(MainProvincia::class.java)
                }
            }
        }
    }

    private fun irActividad(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}