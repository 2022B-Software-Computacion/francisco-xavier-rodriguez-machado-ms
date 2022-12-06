import java.util.*

// Main.kt
fun main(){
    println("Hola mundo")

    // TIPOS DE VARIABLES

    // Una variable es INMUTABLE cuando no se puede reasignar
    val inmutable: String = "Francisco";
    // Una variable es MUTABLES cuando se puede asignar
    var mutable: String = "Rodriguez";
    mutable = "Francisco";

    // val > var  --> siempre vamos a preferir el "val" antes que "var"

    //Sintaxis Duck typing

    val ejemploVariable = "Ejemplo"
    val edadEjemplo: int = 12
    ejemploVariable.trim()


    //Variables primitivas

    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    //Clase JAVA

    val fechaNacimiento: Date = Date()

    //Sentencias en Kotlin

    if (true){
    }else{
    }

    // Switch no existe

    val estadoCivilWhen = "S"

    when (estadoCivilWhen){
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if(estadoCivilWhen == "S") "SI" else "NO"
}

// FUNCIONES

// void imprimirNombre(String nombre)
// Unit == void

fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

// ? significa que el tipo de dato puede ser nulo

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (Defecto)
    bonoEspecial: Double? = null, // Opcional (Null) --> nullable
): Double{
    //String -> String?
    //Int -> Int?
    //Date -> Date?

    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}
