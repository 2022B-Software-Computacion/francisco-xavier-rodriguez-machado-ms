package com.example.rmfxaplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Francisco", "f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Xavier", "x@x.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Rodriguez", "r@r.com")
                )
        }
    }
}