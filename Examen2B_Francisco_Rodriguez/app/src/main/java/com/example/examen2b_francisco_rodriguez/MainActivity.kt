package com.example.examen2b_francisco_rodriguez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       FirebaseApp.initializeApp(this);


        val btnProvincias = findViewById<Button>(R.id.btnProvincia)
        btnProvincias.setOnClickListener {
            irActividad(MainProvincia::class.java)
        }

        val btnCiudad = findViewById<Button>(R.id.btnCiudad)
        btnCiudad.setOnClickListener {
            irActividad(MainCiudad::class.java)
        }
    }

    private fun irActividad(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}
