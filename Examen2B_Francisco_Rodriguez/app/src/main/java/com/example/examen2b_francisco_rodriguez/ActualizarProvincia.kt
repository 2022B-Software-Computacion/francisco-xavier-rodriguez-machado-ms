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

class ActualizarProvincia : AppCompatActivity() {

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_provincia)

        val bundle = intent.extras
        val idProvincia = bundle?.getString("id")
        var docRef = idProvincia?.let { db.collection("provincias").document(it) }

        val etNombreProvinciaActualizada = findViewById<EditText>(R.id.etNombreProvinciaActualizada)
        val etFechaDeFundacionProvinciaActualizada =
            findViewById<EditText>(R.id.etFechaDeFundacionProvinciaActualizada)
        val etGradoDeSeguridadActualizado =
            findViewById<EditText>(R.id.etGradoDeSeguridadActualizado)
        val etEstaEnFiestasProvincialesActualizada =
            findViewById<EditText>(R.id.etEstaEnFiestasProvincialesActualizada)
        val btnActualizarProvincia = findViewById<Button>(R.id.btnActualizarProvincia)

        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val nombre = documentSnapshot.getString("nombre")
                    val AuxestaEnFiestasProvinciales =
                        documentSnapshot.getBoolean("estaEnFiestasProvinciales")
                    val estaEnFiestasProvinciales = AuxestaEnFiestasProvinciales.toString()
                    val fechaDeFundacionProvincia = documentSnapshot.getString("fechaDeFundacion")
                    val AuxgradoDeSeguridad = documentSnapshot.getDouble("gradoDeSeguridad")
                    val gradoDeSeguridad = AuxgradoDeSeguridad.toString()


                    etNombreProvinciaActualizada.setText(nombre)
                    etFechaDeFundacionProvinciaActualizada.setText(fechaDeFundacionProvincia)
                    etGradoDeSeguridadActualizado.setText(gradoDeSeguridad)
                    etEstaEnFiestasProvincialesActualizada.setText(estaEnFiestasProvinciales)
                }
            }
        }

        btnActualizarProvincia.setOnClickListener {
            if (etNombreProvinciaActualizada.text.isNotEmpty()
                && etFechaDeFundacionProvinciaActualizada.text.isNotEmpty()
                && etGradoDeSeguridadActualizado.text.isNotEmpty()
                && etEstaEnFiestasProvincialesActualizada.text.isNotEmpty()
            ) {

                val tranformacionEstaEnfiestasProvinciales =
                    etEstaEnFiestasProvincialesActualizada.text.toString()
                val resultadoEstaEnfiestasProvinciales =
                    tranformacionEstaEnfiestasProvinciales.toBoolean()

                val tranformacionGradoDeSeguridadActualizado =
                    etGradoDeSeguridadActualizado.text.toString()
                val resultadoGradoDeSeguridadActualizado =
                    tranformacionGradoDeSeguridadActualizado.toDouble()

                val data = hashMapOf(
                    "nombre" to etNombreProvinciaActualizada.text.toString(),
                    "estaEnFiestasProvinciales" to resultadoEstaEnfiestasProvinciales,
                    "fechaDeFundacionProvincia" to etFechaDeFundacionProvinciaActualizada.text.toString(),
                    "gradoDeSeguridad" to resultadoGradoDeSeguridadActualizado
                )

                if (idProvincia != null) {
                    db.collection("provincias").document(idProvincia)
                        .update(data as Map<String, Any>).addOnSuccessListener {
                        Toast.makeText(
                            this,
                            "Actualización de la provincia Exitosa!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        irActividad(MainProvincia::class.java)
                    }.addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Se produjo un error al actualizar la provincia",
                            Toast.LENGTH_SHORT
                        ).show()
                        irActividad(MainProvincia::class.java)
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