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

class ActualizarCiudad : AppCompatActivity() {

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_ciudad)

        val bundle = intent.extras
        val idCiudad = bundle?.getString("id")

        var docRef = idCiudad?.let { db.collection("ciudades").document(it) }

        val etNombreCiudadActualizada = findViewById<EditText>(R.id.etNombreCiudadActualizada)
        val etFechaDeFundacionCiudadActualizada =
            findViewById<EditText>(R.id.etFechaDeFundacionCiudadActualizada)
        val etNumeroDeHabitantesActualizado =
            findViewById<EditText>(R.id.etNumeroDeHabitantesActualizado)
        val etEstaEnFiestasPatronalesActualizada =
            findViewById<EditText>(R.id.etEstaEnFiestasPatronalesActualizada)
        val btnActualizarCiudad = findViewById<Button>(R.id.btnActualizarCiudad)

        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val nombre = documentSnapshot.getString("nombre")
                    val AuxEstaEnFiestasPatronales =
                        documentSnapshot.getBoolean("estaEnfiestasPatronales")
                    val estaEnfiestasPatronales = AuxEstaEnFiestasPatronales.toString()
                    val fechaDeFundacion = documentSnapshot.getString("fechaDeFundacion")
                    val AuxnumeroHabitantes = documentSnapshot.getDouble("numeroHabitantes")
                    val numeroHabitantes = AuxnumeroHabitantes.toString()


                    etNombreCiudadActualizada.setText(nombre)
                    etFechaDeFundacionCiudadActualizada.setText(fechaDeFundacion)
                    etNumeroDeHabitantesActualizado.setText(numeroHabitantes)
                    etEstaEnFiestasPatronalesActualizada.setText(estaEnfiestasPatronales)
                }
            }
        }

        btnActualizarCiudad.setOnClickListener {
            if (etNombreCiudadActualizada.text.isNotEmpty()
                && etFechaDeFundacionCiudadActualizada.text.isNotEmpty()
                && etNumeroDeHabitantesActualizado.text.isNotEmpty()
                && etEstaEnFiestasPatronalesActualizada.text.isNotEmpty()
            ) {

                val tranformacionEstaEnfiestasPatronales =
                    etEstaEnFiestasPatronalesActualizada.text.toString()
                val resultadoEstaEnfiestasPatronales =
                    tranformacionEstaEnfiestasPatronales.toBoolean()

                val tranformacionNumeroDeHabitantesActualizado =
                    etNumeroDeHabitantesActualizado.text.toString()
                val resultadoNumeroDeHabitantesActualizado =
                    tranformacionNumeroDeHabitantesActualizado.toInt()

                val data = hashMapOf(
                    "nombre" to etNombreCiudadActualizada.text.toString(),
                    "estaEnfiestasPatronales" to resultadoEstaEnfiestasPatronales,
                    "fechaDeFundacion" to etFechaDeFundacionCiudadActualizada.text.toString(),
                    "numeroHabitantes" to resultadoNumeroDeHabitantesActualizado
                )

                if (idCiudad != null) {
                    db.collection("ciudades").document(idCiudad).update(data as Map<String, Any>)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Actualización de la ciudad Exitosa!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            irActividad(MainCiudad::class.java)
                        }.addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Se produjo un error al actualizar la ciudad",
                            Toast.LENGTH_SHORT
                        ).show()
                        irActividad(MainCiudad::class.java)
                    }
                }
            }
        }
    }

    private fun irActividad(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}