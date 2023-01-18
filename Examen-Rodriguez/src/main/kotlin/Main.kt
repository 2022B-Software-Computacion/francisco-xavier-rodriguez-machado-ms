fun main(args: Array<String>) {

    class Opciones {
        fun mostrarOpcionesProvincias(){
            println("CRUD de las Provincias")
            println("1. Crear una Provincia")
            println("2. Listar las Provincias")
            println("3. Actualizar una Provincia - Ciudad")
            println("4. Eliminar una Provincia")
            println("5. Salir" + "\n")
            println("Ingresé el número de la acción a realizar: ")
        }

        fun mostrarOpcionesCiudades(){
            println("CRUD de Ciudades")
            println("1. Crear una Ciudad")
            println("2. Listar las ciudades")
            println("3. Actualizar una ciudad")
            println("4. Eliminar una ciudad")
            println("5. Atrás" + "\n")
            println("Ingresé el número de la acción a realizar: ")
        }

    }

    val rutaArchivoProvincias = "Provincias.txt"
    var rutaArchivoCiudades: String
    val opciones = Opciones()
    val opcionesProvincias = Opciones()
    val listarCiudades = Ciudad()
    val listarProvincias = Provincia()
    var opcionProvincia = true
    var opcionCiudad = true

    println("\nSistema de Gestión de Provincias y Ciudades\n")

    try {
        while (opcionProvincia){
            opcionesProvincias.mostrarOpcionesProvincias()
            when(readLine()!!.toInt()){
                1 -> { // Creación
                    val nuevaProvincia = listarProvincias.crearProvincia()
                    val aux = listarProvincias.leerProvincia(rutaArchivoProvincias)
                    listarProvincias.escribirProvincia(rutaArchivoProvincias, nuevaProvincia, aux)
                    println()
                }
                2 -> { // Listar
                    println("Provincias registradas: ")
                    val aLecturaProvincia = listarProvincias.leerProvincia(rutaArchivoProvincias)
                    println(aLecturaProvincia)
                }
                3 -> { //Actualización
                    println("Operaciones CRUD: ")
                    println("1. Actualizar Información de la Provincia: ")
                    println("2. Opciones de las Ciudades: ")
                    println("Ingresé el número de la opción a realizar: ")
                    when(readLine()!!.toInt()){
                        1 -> {
                            println("\n Provincias registradas: ")
                            val aLecturaProvincia = listarProvincias.leerProvincia(rutaArchivoProvincias)
                            println(aLecturaProvincia+"\n")
                            println("Ingresé el nombre de la provincia que desea actualizar:")
                            val actualizarProvincia = readLine()!!
                            println("Actualizar información de la Provincia")
                            listarProvincias.actualizarProvincia(actualizarProvincia,aLecturaProvincia,
                                rutaArchivoProvincias)
                        }
                        2 -> {
                            try {
                                println("\nIngresé el nombre de la Provincia a la cual va a pertenecer la ciudad:")
                                val actualizarProvincia = readLine()!!
                                rutaArchivoCiudades = "$actualizarProvincia.txt"
                                while (opcionCiudad){
                                    opciones.mostrarOpcionesCiudades()
                                    when(readLine()!!.toInt()){
                                        1 -> { // Crear
                                            val nuevaCiudad = listarCiudades.crearCiudad()
                                            var aux = listarCiudades.leerCiudad(rutaArchivoCiudades)
                                            listarCiudades.escribirCiudad(rutaArchivoCiudades, nuevaCiudad, aux)
                                            println()
                                        }
                                        2 -> { // Listar
                                            println("Ciudades registradas: ")
                                            val aLecturaCiudades = listarCiudades.leerCiudad(rutaArchivoCiudades)
                                            println(aLecturaCiudades)
                                        }
                                        3 -> { //Actualizar
                                            println("Ciudades registradas: ")
                                            val alecturaCiudades = listarCiudades.leerCiudad(rutaArchivoCiudades)
                                            println(alecturaCiudades)
                                            println("\nIngrese el nombre de la Cuidad que desea actualizar: ")
                                            val actualizarCiudad = readLine()!!
                                            println("\nActualizar información de la Ciudad\n")
                                            listarCiudades.actualizarCiudad(actualizarCiudad, alecturaCiudades, rutaArchivoCiudades)
                                        }
                                        4 -> { //Eliminar
                                            println("Ciudades registradas: ")
                                            val alecturaCiudades = listarCiudades.leerCiudad(rutaArchivoCiudades)
                                            println(alecturaCiudades)
                                            println()
                                            println("Ingresé el nombre de la Cuidad que desea eliminar: ")
                                            val eliminarCuidad = readLine()!!
                                            listarCiudades.eliminarCuidad(eliminarCuidad, alecturaCiudades,rutaArchivoCiudades)
                                            println("\nCiudades registradas: ")
                                            val aLecturaCiudades = listarCiudades.leerCiudad(rutaArchivoCiudades)
                                            println(aLecturaCiudades+"\n")
                                        }
                                        5 -> {
                                            println("Atrás"+"\n")
                                            opcionCiudad = false
                                        }
                                        else -> {
                                            println("Error la opción ingresada no existe !!!")
                                        }
                                    }
                                }
                            }catch (e: Exception){
                                println(" Error en las ciudades !! $e")
                            }
                        }
                    }
                }
                4 -> { // Eliminar
                    println("Provincias registradas: ")
                    val aLecturaProvincia = listarProvincias.leerProvincia(rutaArchivoProvincias)
                    println(aLecturaProvincia + "\n")
                    println("Ingresé el nombre de la Provincia que desea eliminar: ")
                    val eliminarProvincia = readLine()!!
                    val auxI = listarProvincias.eliminarProvincia(eliminarProvincia,aLecturaProvincia,rutaArchivoProvincias)
                    println("Provincias registradas: ")
                    //val aLecturaProvincia = listarProvincias.leerProvincias(rutaArchivoProvincias)
                    println(auxI+"\n")
                }
                5 -> { // Salir
                    println("Vuelva Pronto")
                    opcionProvincia = false
                    //exitProcess(0)
                }
            }
        }
    }catch (e: Exception){
        println("Error en el Menu $e")
    }
}


