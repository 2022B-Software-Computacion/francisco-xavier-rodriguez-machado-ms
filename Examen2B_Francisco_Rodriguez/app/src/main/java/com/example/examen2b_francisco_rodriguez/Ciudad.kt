package com.example.examen2b_francisco_rodriguez

data class Ciudad(
    var id: String = "",
    var nombre: String = "",
    var numeroDeHabitantes: Int = 0,
    var fechaDeFundacion: String = "",
    var estaEnFiestasPatronales: Boolean? = null
)