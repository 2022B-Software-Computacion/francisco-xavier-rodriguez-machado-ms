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


// Tipos de Arreglos

// Arreglo estático

    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    println(arregloEstatico)
    // Arreglos Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Siven para los arreglos estaticos y dinamicos

    // FOR EACH - Unit

    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed {indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")

        }
    println(respuestaForEach)

    //OPERADOR: tranforma el arreglo, crea un nuevo arreglo MAP

    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble() + 100
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map{ it + 15}
    //.map { valorActual: Int ->
//       return@map valorActual + 15
// }
    println(respuestaMapDos)
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

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){//Bloque codigo constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializacion")
    }
}

//CLASE KOTLIN
abstract class Numeros( // Constructor Primario
    // uno: Int,  // Parametro
    // public var uno: Int, // Propiedad de la clase publica
    // var uno: Int, // Propiedad de la clase publica
    protected val numeroUno: Int // Propiedad de la clase protected
    protected val numeroDos: Int // Propiedad de la clase private
){
    // protected val numeroUno: Int
    // var cedula String = "";
    // public var cedula: String = "";
    init {  // Bloque de codigo del constructor primario
    // this.numeroUno = uno
    this.numeroUno
        numeroUno
    this.numeroDos
        numeroDos
    println("Inicializacion")
    }
}

class Suma( // Constructor Primario Suma
    uno: Int, // Parametro
    dos: Int  // Parametro
): Numeros(uno, dos){
    init { // Bloque de Constructor Primario
        this.numeroUno
        this.numeroDos
    }
    constructor( // Segundo Constructor
        uno: Int?, // parametro
        dos: Int  // parametro
    ): this( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ){

    }
    constructor( // tercer Constructor
        uno: Int?, // parametro
        dos: Int  // parametro
    ): this( // llamada constructor primario
        uno,
        if (dos == null) 0 else uno
    )
    constructor( // cuarto Constructor
        uno: Int?, // parametro
        dos: Int  // parametro
    ): this( // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )
    public fun sumar(): Int{
        return numeroUno + numeroDos
    }
    companion object{ // atributos y metodos "compartidos" entre las instancias
        val pi = 3.14
    fun elevarAlCuadrado(num: Int): Int{
        return num * num
    }
        val historiaSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historiaSumas.add(valorNuevaSuma)
        }
    }
}


