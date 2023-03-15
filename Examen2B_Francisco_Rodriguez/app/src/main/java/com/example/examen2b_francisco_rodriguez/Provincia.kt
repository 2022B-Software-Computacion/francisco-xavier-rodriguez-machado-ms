package com.example.examen2b_francisco_rodriguez

data class Provincia(
    var id: String = "",
    var nombre: String = "",
    var gradoDeSeguridad: Double = 0.0,
    var fechaDeFundacion: String = "",
    var estaEnFiestasProvinciales: Boolean? = null,
)