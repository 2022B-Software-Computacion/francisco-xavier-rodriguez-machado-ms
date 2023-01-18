import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class Ciudad() {

    class Ciudades(
        var nombreCiudad: String,
        var idCiudad: Int,
        var numeroHabitantes: Int,
        var fechaDeFundacion: LocalDate,
        var fiestasPatronales: Boolean
    ) {
        override fun toString(): String {
            return "$nombreCiudad,$idCiudad,$numeroHabitantes,$fechaDeFundacion,$fiestasPatronales"
        }
    }

    class idAutomatico(from: Int, to: Int) {
        private val numeros = (from..to).toMutableList()
        fun nextInt(): Int {
            val index = kotlin.random.Random.nextInt(numeros.size)
            val number = numeros[index]
            numeros.removeAt(index)
            return number
        }
    }

    private val idCiudades = idAutomatico(1, 18)

    //Creación de la ciudad
    fun crearCiudad():Ciudades{
        println("Ingresé el nombre de la nueva Ciudad:")
        val nombreCiudad = readLine()!!
        val idCiudad = idCiudades.nextInt()
        println("Ingresé el número de habitantes de la cuidad:")
        val numeroHabitantes = readLine()!!.toInt()
        println("Ingresé la fecha de fundación de la Ciudad con el formato YYYY-MM-DD:")
        val auxFechaDeFundacion = readLine()!!
        val fechaDeFundacion = LocalDate.parse(auxFechaDeFundacion,
            DateTimeFormatter.ISO_DATE)
        val fiestasPatronales = true
        val nuevaCiudad = Ciudades(nombreCiudad, idCiudad, numeroHabitantes, fechaDeFundacion,fiestasPatronales)
        return nuevaCiudad
    }

    //Cracion del platillo
    fun escribirCiudad(rutaArchivo: String, ciudad: Ciudades, listarCiudades: ArrayList<Ciudades>){
        listarCiudades.add(ciudad);
        var archivo: File? = null
        var fw: FileWriter? = null
        var pw: PrintWriter? = null
        var texto = ""
        try {
            archivo = File(rutaArchivo)
            fw = FileWriter(archivo, true)
            pw = PrintWriter(fw)
            texto = texto + ciudad.nombreCiudad + ",";
            texto = texto + ciudad.idCiudad + ",";
            texto = texto + ciudad.numeroHabitantes + ",";
            texto = texto + ciudad.fechaDeFundacion + ",";
            texto += ciudad.fiestasPatronales;
            fw.write(texto);
            fw.write("\n");
        }catch (e: Exception){
            println("Error en la escritura de la Provincia $e")
        }finally {
            try {
                if(fw !=null){
                    fw.close()
                }
            }catch (e: Exception){
                println("Error en la escritura de la Provincia $e")
            }
        }
    }

    //Lectura de las ciudades creadas
    fun leerCiudad(rutaArchivo: String): ArrayList<Ciudades> {
        val listaCiudades = ArrayList<Ciudades>()
        try {
            var resultado = ""
            var linea = ""
            val lector = File(rutaArchivo).bufferedReader()
            while (lector.readLine().also { linea = it } != null) {
                val tokens = StringTokenizer(linea, ",")
                var dato = tokens.nextToken()
                val nombreCiudad = dato;
                dato = tokens.nextToken()
                val idCiudad = dato.toInt();
                dato = tokens.nextToken()
                val numHabitantes = dato.toInt();
                dato = tokens.nextToken()
                val fechaFundacion = dato
                dato = tokens.nextToken()
                val fiestasPatronales = dato.toBoolean()

                val fechaDeFundacion = LocalDate.parse(fechaFundacion, DateTimeFormatter.ISO_DATE)
                val nuevaCiudadArchivo = Ciudades(nombreCiudad, idCiudad, numHabitantes, fechaDeFundacion, fiestasPatronales)
                listaCiudades.add(nuevaCiudadArchivo)
                resultado += linea
            }
            lector.close()
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listaCiudades
    }

    //Actualizar Cuidades
    fun actualizarCiudad(buscarCiudad: String, listarCiudades: ArrayList<Ciudades>, rutaArchivo: String): ArrayList<Ciudades>{
        try {
            for (encontrarCiudad in listarCiudades){
                if (encontrarCiudad.nombreCiudad == buscarCiudad){
                    val indexCiudad = listarCiudades.indexOf(encontrarCiudad)
                    println("Informacón de la Ciudad "+"\n")
                    println("1. Nombre de la Ciudad: "+encontrarCiudad.nombreCiudad)
                    println("2. Número de Habitantes: "+encontrarCiudad.numeroHabitantes)
                    println("3. Fiestas Patronales: "+encontrarCiudad.fiestasPatronales)
                    println("Seleccione la información deseas actualizar: ")
                    when (readLine()!!.toInt()){
                        1 -> {
                            println("Ingrese la nueva información:")
                            val nuevoNombre = readLine()
                            encontrarCiudad.nombreCiudad = nuevoNombre.toString()
                            listarCiudades[indexCiudad] = encontrarCiudad
                            actualizacionDatos(listarCiudades, rutaArchivo)
                            println("Los datos de la cuidad se actualizaron con éxito!")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información:")
                            val nuevoNumHabitantes = readLine()!!.toInt()
                            encontrarCiudad.numeroHabitantes = nuevoNumHabitantes
                            listarCiudades.set(indexCiudad,encontrarCiudad)
                            actualizacionDatos(listarCiudades, rutaArchivo)
                            println("Los datos de la cuidad se actualizaron con éxito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            val nuevoEstadoFiestasPatronales = readLine()!!.toBoolean()
                            encontrarCiudad.fiestasPatronales = nuevoEstadoFiestasPatronales
                            listarCiudades.set(indexCiudad,encontrarCiudad)
                            actualizacionDatos(listarCiudades, rutaArchivo)
                            println("Los datos de la cuidad se actualizaron con éxito!")
                            break
                        }
                        else -> {
                            println("Error la opción ingresada no es correcta!")
                            break
                        }
                    }
                }
            }
        }catch (e:Exception){
            println("Error en el proceso de Actualización $e")
        }
        return listarCiudades
    }

    fun actualizacionDatos(listarCiudades: ArrayList<Ciudades>, rutaArchivo: String) {
        try {
            var archivo: File? = null
            var fw: FileWriter? = null
            var pw: PrintWriter? = null
            var texto = ""
            for (ciudades in listarCiudades) {
                try {
                    archivo = File(rutaArchivo)
                    fw = FileWriter(archivo)
                    pw = PrintWriter(fw)
                    texto = texto + ciudades.nombreCiudad + ",";
                    texto = texto + ciudades.idCiudad + ",";
                    texto = texto + ciudades.numeroHabitantes + ",";
                    texto = texto + ciudades.fechaDeFundacion + ",";
                    texto = texto + ciudades.fiestasPatronales + "\n";
                    fw.write(texto);
                } catch (e: Exception) {
                    println("Error en la escritura del archivo ciudades $e")
                } finally {
                    try {
                        if (fw != null) {
                            fw.close()
                        }
                    } catch (e: Exception) {
                        println("Error en la escritura actualización $e")
                    }
                }
            }
        } catch (e: Exception) {
            println("Error en la actualización $e")
        }
    }

    //Eliminar una ciudad
    fun eliminarCuidad(encontrarCiudad: String, listarCiudades: ArrayList<Ciudades>, rutaArchivo: String): ArrayList<Ciudades>{
        try {
            for(buscarCiudad in listarCiudades){
                if(buscarCiudad.nombreCiudad == encontrarCiudad){
                    listarCiudades.remove(buscarCiudad)
                    actualizacionDatos(listarCiudades, rutaArchivo)
                    println("La ciudad se eliminanó con éxito!!")
                    break;
                }else{
                    println("La Ciudad ingresada no existe, ingrese una Ciudad válida")
                }
            }
        }catch (e: Exception){
            println("Error en la eliminación de la ciudad $e")
        }
        return listarCiudades
    }
}