import java.io.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Provincia {

    class Provincias(
        var nombreProvincia: String,
        var idProvincia: Int,
        var gradoDeSeguridad: Float,
        var fechaDeFundacion: LocalDate,
        var fiestasProvinciales: Boolean
    ) {

        override fun toString(): String {
            return "Nombre de la Provincia: $nombreProvincia ,ID de la Provincia: $idProvincia ," +
                    "Grado de Seguridad: $gradoDeSeguridad ,Fecha de Fundación: $fechaDeFundacion ," +
                    "Está en fiestas Provinciales: $fiestasProvinciales"
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

    private val idProvincias = idAutomatico(1, 24)

    //Creación de las provincias
    fun crearProvincia(): Provincias{
        println("Ingresé el nombre de la nueva Provincia:")
        val nombreProvincia = readLine()!!
        val id = idProvincias.nextInt()
        println("Ingresé el grado de seguridad de la Provincia, de (0.0-5.0):")
        val gradoDeSeguridad = readLine()!!.toFloat()
        println("Ingresé la fecha de fundación de la Provincia con el formato YYYY-MM-DD:")
        val auxFechaFundacion = readLine()
        val fechaDeFundacion = LocalDate.parse(auxFechaFundacion, DateTimeFormatter.ISO_DATE)
        val fiestasProvinciales = true
        val nuevaProvincia = Provincias(nombreProvincia, id, gradoDeSeguridad, fechaDeFundacion, fiestasProvinciales)
        return nuevaProvincia
    }

    //Creación de la provincia
    fun escribirProvincia(rutaArchivo: String, provincias: Provincias, listarProvincias: ArrayList<Provincias>){
        listarProvincias.add(provincias)
        var archivo: File? = null
        var fw: FileWriter? = null
        var pw: PrintWriter? = null
        var texto = ""
        try {
            archivo = File(rutaArchivo)
            fw = FileWriter(archivo, true)
            pw = PrintWriter(fw)
            texto = texto + provincias.nombreProvincia + ",";
            texto = texto + provincias.idProvincia + ",";
            texto = texto + provincias.gradoDeSeguridad + ",";
            texto = texto + provincias.fechaDeFundacion + ",";
            texto = texto + provincias.fiestasProvinciales + "\n"
            fw.write(texto)
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

    //Lectura de las provincias
    fun leerProvincia(rutaArchivo: String): ArrayList<Provincias> {
        val listarProvincias = ArrayList<Provincias>()
        try {
            var resultado = ""
            var linea = ""
            val lector = File(rutaArchivo).bufferedReader()
            while (lector.readLine().also { linea = it } != null) {
                val tokens = StringTokenizer(linea, ",")
                var dato = tokens.nextToken()
                val nombreProvincia = dato;
                dato = tokens.nextToken()
                val idProvincia = dato.toInt();
                dato = tokens.nextToken()
                val gradoDeSeguridad = dato.toFloat();
                dato = tokens.nextToken()
                val fechaDeFundacion = dato
                dato = tokens.nextToken()
                val fiestasProvinciales = dato.toBoolean()

                val fechaDeFundacionProvincia = LocalDate.parse(fechaDeFundacion, DateTimeFormatter.ISO_DATE)
                val nuevaProvinciaArchivo = Provincias(nombreProvincia, idProvincia, gradoDeSeguridad, fechaDeFundacionProvincia,
                    fiestasProvinciales)
                listarProvincias.add(nuevaProvinciaArchivo)
                resultado += linea
            }
            lector.close()
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listarProvincias
    }

    //Actualizar
    fun actualizarProvincia(encontrarProvincia: String, listarProvincias: ArrayList<Provincias>, rutaArchivo: String):
            ArrayList<Provincias>{
        try {
            for (hallarProvincia in listarProvincias){
                if (hallarProvincia.nombreProvincia == encontrarProvincia){
                    val indexProvincia = listarProvincias.indexOf(hallarProvincia)
                    println("Informacón de la Provincia "+"\n")
                    println("1. Nombre del Provincia: "+hallarProvincia.nombreProvincia)
                    println("2. Grado de Seguridad (1-5): "+hallarProvincia.gradoDeSeguridad)
                    println("3. Fiestas Provinciales: "+hallarProvincia.fiestasProvinciales)
                    println("Seleccione la información deseas actualizar: ")
                    when (readLine()!!.toInt()){
                        1 -> {
                            println("Ingrese la nueva información:")
                            val nuevoNombre = readLine()
                            hallarProvincia.nombreProvincia = nuevoNombre.toString()
                            listarProvincias[indexProvincia] = hallarProvincia
                            actualizarDatos(listarProvincias, rutaArchivo)
                            println("Los datos de la provincia se actualizaron con éxito!")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información:")
                            val nuevoGradoSeguridad = readLine()!!.toFloat()
                            hallarProvincia.gradoDeSeguridad = nuevoGradoSeguridad
                            listarProvincias.set(indexProvincia,hallarProvincia)
                            actualizarDatos(listarProvincias, rutaArchivo)
                            println("Los datos de la provincia se actualizaron con éxito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            val nuevaDisponibilidadFiestas = readLine()!!.toBoolean()
                            hallarProvincia.fiestasProvinciales = nuevaDisponibilidadFiestas
                            listarProvincias.set(indexProvincia,hallarProvincia)
                            actualizarDatos(listarProvincias, rutaArchivo)
                            println("Los datos de la provincia se actualizaron con éxito!")
                            break
                        }
                        else -> {
                            println("Error la acción ingresada no existe!")
                            break
                        }
                    }
                }
            }
        }catch (e:Exception){
            println("Error en el proceso de actualización $e")
        }
        return listarProvincias
    }

    fun actualizarDatos(listarProvincias: ArrayList<Provincias>, rutaArchivo: String) {
        try {
            var archivo: File? = null
            var fw: FileWriter? = null
            var pw: PrintWriter? = null
            var texto = ""
            for (provincia in listarProvincias) {
                try {
                    archivo = File(rutaArchivo)
                    fw = FileWriter(archivo)
                    pw = PrintWriter(fw)
                    texto = texto + provincia.nombreProvincia + ",";
                    texto = texto + provincia.idProvincia + ",";
                    texto = texto + provincia.gradoDeSeguridad + ",";
                    texto = texto + provincia.fechaDeFundacion + ",";
                    texto = texto + provincia.fiestasProvinciales + "\n";
                    fw.write(texto);
                } catch (e: Exception) {
                    println("Error en la escritura del archivo provincias $e")
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

    //Eliminar Provincia
    fun eliminarProvincia(encontrarProvincia: String, listarProvincias: ArrayList<Provincias>, rutaArchivo: String):
            ArrayList<Provincias>{
        try {
            for(hallarProvincia in listarProvincias){
                if(hallarProvincia.nombreProvincia == encontrarProvincia){
                    listarProvincias.remove(hallarProvincia)
                    actualizarDatos(listarProvincias, rutaArchivo)
                    println("Provincia eliminada con éxito!!")
                    break
                }else{
                    println("La Provincia ingresada no existe, ingrese una Provincia válido")
                }
            }
        }catch (e: Exception){
            println("Error en la eliminacion de la Provincia $e")
        }
        return listarProvincias
    }

}