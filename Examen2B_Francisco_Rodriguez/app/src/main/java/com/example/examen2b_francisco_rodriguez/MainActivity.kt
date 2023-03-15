package com.example.examen2b_francisco_rodriguez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProvincias = findViewById<Button>(R.id.btnProvincia)
        btnProvincias.setOnClickListener {
            goActivity(MainProvincia::class.java)
        }

        val btnCiudad = findViewById<Button>(R.id.btnCiudad)
        btnCiudad.setOnClickListener {
            goActivity(MainCiudad::class.java)
        }
    }

    private fun goActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}
