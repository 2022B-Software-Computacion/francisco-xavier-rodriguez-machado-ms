package com.example.rmfxaplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Francisco", "f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Xavier", "x@x.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Rodriguez", "r@r.com")
                )
        }
    }
}