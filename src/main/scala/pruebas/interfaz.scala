package pruebas

import moduloCheck._
import elementos._
import personas._
import Array._
import scala.io._
import scala.util._
import GestionVuelos._
import Pista._
import Controles._
import moduloSalaAbordaje._
import util.control.Breaks._
import baseDeDatos._

class Main{
    
    var ver : Boolean = true
    var aeropuerto : Aeropuerto = new Aeropuerto()

    while(ver){

        var str : String = menu()
        println("Opcion: ")
        var opcion : Try[Int] = Try(StdIn.readInt())
        var num : Int = 0

        opcion match{
            case Success(s) => num = s
            case Failure(f) => println("Error a la hora de escoger")
        }

        num match{

            case 1 => {

                aeropuerto.verPasajeros()
                println("\nPorfavor ingrese el número del pasajero:")
                var op : Try[Int] = Try(StdIn.readInt())
                op match{
                    case Success(s) =>  {
                        aeropuerto.checkIn(s)
                    }
                    case Failure(f) => println("Error a la hora de escoger")
                }

                
            }
            case 2 => {
                var vuelo : Vuelo = seleccionarVuelo()
                if ( vuelo != null)
                    salaAbordaje(vuelo)
            }
            case 3 => {
                var vuelo : Vuelo = seleccionarVuelo()
                if ( vuelo != null)
                    paxControl(vuelo)
            } 
            case 4 => aeropuerto.verPasajeros()
            case 5 => aeropuerto.verVuelos(true)
            case 6 => {
                
                var cont : Int = 1
                println("\n---------------------------------------")
                println("|               VUELOS                |")
                println("---------------------------------------")
                for (v <- aeropuerto.getVuelos()) {
                    println("[" + cont + "] " + v.referencia)
                    cont +=  1
                }
                
                println("---------------------------------------")
                println("| Total vuelos : " + (cont-1) + "                    |")
                println("---------------------------------------\n")

                var cant_vuelos : Int = aeropuerto.getVuelos().length
                println("Porfavor seleccione el vuelo que desea conocer el reporte: ")
                var opcion : Try[Int] = Try(StdIn.readInt())
                var num : Int = 0;
                opcion match{
                    //Me verifica que se le haya ingresado un numero
                    case Success(s) => num = s
                    case Failure(f) => num = 0
                }

                if (num-1  >= 0 && num < cant_vuelos)
                    aeropuerto.verReporte(num)

            }
            case 7 => aeropuerto.crearVuelo()
            case 8 => aeropuerto.verAvionesEnPista()
            case 9 => ver = false
            case _ => println("Error al intentar escoger un servicio")
        }

        def chekIn(pasajero : Pasajero) : Unit = {
            var modulo : ModuloCheckIn = new ModuloCheckIn(pasajero)
            modulo.inicio()

        }

        def seleccionarVuelo() : Vuelo = {

            var cant_vuelos : Int = aeropuerto.getVuelos().length

            if (cant_vuelos > 0) {
                aeropuerto.verVuelos(false)
                println("Porfavor seleccione un vuelo:")
                var opcion : Try[Int] = Try(StdIn.readInt())
                var num : Int = 0
                opcion match{
                    //Me verifica que se le haya ingresado un numero
                    case Success(s) => num = s
                    case Failure(f) => num = 0
                }

                if (num-1  >= 0 && num <= cant_vuelos)
                    return aeropuerto.getVuelos()(num-1)

                
            }

            else  println("No hay vuelos registrados")

            return null
        }

        def salaAbordaje(vuelo : Vuelo) : Unit = {

            var ver : Boolean = true

            while(ver) {
            
                menuSala()
                var opcion : Try[Int] = Try(StdIn.readInt())
                var num : Int = 0;
                opcion match{
                    //Me verifica que se le haya ingresado un numero
                    case Success(s) => num = s
                    case Failure(f) => num = 0
                }

                num match {
                    case 1 => {

                        var tripulacion : Tripulacion = leerDatostripulacion("documento(s)")
                        val result = aeropuerto.getSalaAbordaje().verificarTripulacionDocumento(vuelo.referencia, tripulacion)

                        if (result == true) 
                            println("Documentos correctos")
                        else println("Acceso denegado")
                    }
                    case 2 => {

                        var tripulacion : Tripulacion = leerDatostripulacion("nombre(s)")
                        val result = aeropuerto.getSalaAbordaje().verificarTripulacionNombre(vuelo.referencia, tripulacion)
                        if (result == true) 
                            println("Nombres correctos")
                        else println("Acceso denegado")
                    }
                    case 3 => {
                        val cantidad = aeropuerto.getSalaAbordaje().verificarDisponibilidadSillas(vuelo.referencia)
                        println("La cantidad de sillas disponibles son " + cantidad)
                    }
                    case 4 => aeropuerto.getSalaAbordaje().verificarServiciosEspeciales()
                    case 5 => ver = false
                    case _ => println("Hubo un error a la hora de escoger")
                }

                def leerDatostripulacion(dato : String) : Tripulacion = {
                    
                    println(">> Ingrese la cantidad de pilotos:")
                    var opcion : Try[Int] = Try(StdIn.readInt())
                    var num : Int = 0
                    
                    opcion match{
                        case Success(s) => num = s
                        case Failure(f) => num = 0
                    }
                    
                    var pilotos : List[Piloto] = List()
                    var azafatas : List[Azafata] = List()

                    var cont : Int = 1
                    while (cont <= num) {

                        println("Ingrese el " + dato  + " del piloto #" + cont + ":")
                        var p1 : String = StdIn.readLine()
                        pilotos = new Piloto(p1, p1, 0) :: pilotos
                        cont += 1
                    }

                    println(">> Ingrese la cantidad de afatas:")
                    opcion = Try(StdIn.readInt())
                    num = 0
                    opcion match{

                        case Success(s) => num = s
                        case Failure(f) => num = 0
                    }

                    cont = 1
                    while (cont <= num) {

                        println("Ingrese el " + dato + " de la azafata #" + cont + ":")
                        var a1 : String = StdIn.readLine()
                        azafatas = new Azafata(a1, a1, 0) :: azafatas
                        cont += 1
                    }
                    
                    return new Tripulacion(pilotos, azafatas)
                }
            }

            def menuSala() {

                println("\n==============================================")
                println("Bienvenido a la sala de abordaje")
                println("==============================================")
                println("Ref. de vuelo:  " + vuelo.referencia)
                println("==============================================")
                println("Que desea hacer: ")
                println("[1] Verificar los doc. de la tripulacion")
                println("[2] Verificar los nombres de la tripulacion")
                println("[3] Verificar disponibilidad de sillas")
                println("[4] Verificar servicios especiales")
                println("[5] salir")
                println("===============================================")
            }
        }

        def paxControl(vuelo : Vuelo) : Unit = {

            var ver : Boolean = true

            while(ver) {

                menuPax()
                println("Porfavor seleccione una opcion:")
                var opcion : Try[Int] = Try(StdIn.readInt())
                var num : Int = 0
                
                opcion match{
                    case Success(s) => num = s
                    case Failure(f) => num = 0
                }
                println()
                num match {
                    case 1 => {
                        
                        println("Ingrese la cantidad de comidas:")
                        var n : Try[Int] = Try(StdIn.readInt())
                        var cantidad : Int = 0
                        n match {
                            case Success(s) => cantidad = s
                            case Failure(f) => cantidad = 0
                        }

                        if (cantidad > 0) {
                            val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                            aeropuerto.getPaxControl().asignarComida(indice, cantidad)
                            aeropuerto.update()
                            println("\n----------------------------------------------")
                            println("| Se asignaron las comidas correctamente       |")
                            println("----------------------------------------------\n") 
                        }
                        else println("Error al asignar las comidas")

                    }
                    case 2 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        val nuevo_reporte = aeropuerto.getPaxControl().asignarReporte(indice, "00:00")
                        aeropuerto.agregarReporte(nuevo_reporte)
                        aeropuerto.update()
                        println("\n----------------------------------------------")
                        println("| Reporte asignado correctamente             |")
                        println("----------------------------------------------\n")


                    }
                    case 3 => {

                        /*println("Ingrese la matricula para el avion:")
                        val matricula  = StdIn.readLine()
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        aeropuerto.getPaxControl().agregarMatricula(indice, matricula)
                        aeropuerto.update()*/
                        println("\n---------------------------------------------------")
                        println("| ALERTA: No puedes cambiar la matricula del avion |")
                        println("---------------------------------------------------\n")
                    }
                    case 4 => {
                        
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)

                        if ( aeropuerto.getVuelos()(indice).tripulacion == null) {
                            
                            var azafatas : List[Azafata] = List()
                            var pilotos : List[Piloto] = List()

                            println("Intrese la cantidad de pilotos: ")
                            var n : Try[Int] = Try(StdIn.readInt())
                            var cantidad_pilotos : Int = 0

                            n match {
                                case Success(s) => cantidad_pilotos = s
                                case Failure(f) => cantidad_pilotos = 0
                            }

                            println("Intrese la cantidad de azafatas: ")
                            var a : Try[Int] = Try(StdIn.readInt())
                            var cantidad_azafatas: Int = 0
                            var iter : Int = 0

                            
                            a match {
                                case Success(s) => cantidad_azafatas = s
                                case Failure(f) => cantidad_azafatas = 0
                            }
                            println("-------------------------------------------------")
                            println("|                PILOTOS                        |")
                            while (iter < cantidad_pilotos) {
                                
                                println("-------------------------------------------------")
                                println("Ingrese el nombre para el piloto #" + (iter + 1))
                                val nombre = StdIn.readLine()
                                println("Ingrese el documento para el piloto #" + (iter + 1))
                                val id =  StdIn.readLine()
                                println("Ingrese la edad para el piloto #" + (iter + 1))
                                var e : Try[Int] = Try(StdIn.readInt())
                                var edad : Int = 0
                                
                                e match {
                                    case Success(s) => edad = s
                                    case Failure(f) => edad = 0
                                }

                                pilotos = new Piloto(nombre, id, edad) :: pilotos
                                iter += 1
                            }

                            iter = 0
                            println("-------------------------------------------------")
                            println("|                AZAFATAS                       |")
                            while (iter < cantidad_azafatas) {


                                println("-------------------------------------------------")
                                println("Ingrese el nombre para la azafata #" + (iter + 1))
                                val nombre = StdIn.readLine()
                                println("Ingrese el documento para la azafata #" + (iter + 1))
                                val id =  StdIn.readLine()
                                println("Ingrese la edad para la azafata #" + (iter + 1))
                                var e : Try[Int] = Try(StdIn.readInt())
                                var edad : Int = 0
                                
                                e match {
                                    case Success(s) => edad = s
                                    case Failure(f) => edad = 0
                                }
                                
                                azafatas = new Azafata(nombre, id, edad) :: azafatas
                                iter += 1
                            }

                            val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                            val tripulacion : Tripulacion =  new Tripulacion(pilotos, azafatas)
                            aeropuerto.getPaxControl().agregarTripulacion(indice, tripulacion)
                            aeropuerto.update()
                            println("\n----------------------------------------------")
                            println("| Tripulacion agregada correctamente         |")
                            println("----------------------------------------------\n")
                        }

                        else {
                            println("\n----------------------------------------------")
                            println("| El vuelo ya tiene tripulacion              |")
                            println("----------------------------------------------\n")
                        }

                    }
                    case 5 => {


                        if (vuelo.avion != null) {

                            val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                            println("\nIngrese la cantidad de pasajeros que desea asignar al vuelo: ")
                            var cantidad : Try[Int] = Try(StdIn.readInt())
                            var num : Int = 0;
                            cantidad match{
                                case Success(s) => num = s
                                case Failure(f) => num = 0
                            }
                            if (num > 0 &&  (vuelo.cantidadPasajeros + num) < vuelo.avion.cantidadPasajeros) {
                                aeropuerto.getPaxControl().asignarPasajeros(num, indice)
                                aeropuerto.update()
                                println("\n----------------------------------------------")
                                println("| Se asignaron correctamente los pasajeros   |")
                                println("----------------------------------------------\n")
                            }

                            else {
                                println("Hubo un error al asignar los pasajeros")
                            }
                        } else {
                            println("\n------------------------------------------------")
                            println("| ALERTA: El vuelo no tiene un avion registrado |")
                            println("------------------------------------------------\n")
                        }


                    }
                    case 6 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese el dia de salida:")
                        var n : Try[Int] = Try(StdIn.readInt())
                        var dia : Int = 0
                        n match {
                            case Success(s) => dia = s
                            case Failure(f) => dia = 0
                        }

                        if (dia > 0 && dia <= 31) {
                            aeropuerto.getPaxControl().asignarSalida(dia, indice)
                            aeropuerto.update()
                            println("\n----------------------------------------------")
                            println("| Dia agregado correctamente                  |")
                            println("----------------------------------------------\n")
                        }


                        else println("Error al asignar el dia de salida")
                    }
                    case 7 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de salida:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarSalidaHora(hora, indice)
                        aeropuerto.update()
                        println("\n----------------------------------------------")
                        println("|  de salida agregada correctamente          |")
                        println("----------------------------------------------\n")
                    }
                    case 8 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de llegada:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarLlegadaHora(hora, indice)
                        aeropuerto.update()
                        println("\n----------------------------------------------")
                        println("| Hora de llegada agregada correctamente      |")
                        println("----------------------------------------------\n")
                    }
                    case 9 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de inicio de abordaje:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarInicioHora(hora, indice)
                        aeropuerto.update()
                        println("\n----------------------------------------------")
                        println("| Hora de abordaje agregada correctamente     |")
                        println("----------------------------------------------\n")
                    }
                    case 10 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de final de abordaje:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarHoraFinal(hora, indice)
                        aeropuerto.update()
                        println("\n----------------------------------------------")
                        println("| Hora de abordaje  agregada correctamente    |")
                        println("----------------------------------------------\n")
                    }
                    case 11 => {
                        
                        val cantidad_pasajeros = aeropuerto.getPasajeros().length
                        aeropuerto.verPasajeros()
                        println("Seleccione un pasajero:")
                        var n : Try[Int] = Try(StdIn.readInt())
                        var pasajero : Int = 0
                        n match {
                            case Success(s) => pasajero = s
                            case Failure(f) => pasajero = 0
                        }

                        if (pasajero > 0 && pasajero <= cantidad_pasajeros) {

                            /* 

                                NOTA: Aqui se debe realizar un filtro

                            */
                            val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                            val pasa = aeropuerto.getPasajeros()(pasajero).nombre
                            aeropuerto.getPaxControl().asignarPrimerPasajero(indice, pasa)
                            aeropuerto.update()
                        }

                        else  {
                            println("\n----------------------------------------------")
                            println("| Error al agregar el pasajero               |")
                            println("----------------------------------------------\n")
                        }
                    }
                    case 12 => {
                        
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)

                        if (aeropuerto.getVuelos()(indice).avion == null) {
                            aeropuerto.verAvionesEnPista() 
                            println("Porfavor seleccione una avion:")
                            var opcion : Try[Int] = Try(StdIn.readInt())
                            var num : Int = 0
                            
                            opcion match{
                                case Success(s) => num = s
                                case Failure(f) => num = 0
                            }
                            
                            if (aeropuerto.getAviones().length > 0 && num > 0 && num <= aeropuerto.getAviones().length) {
                                val avioncito =  aeropuerto.getAviones()(num-1)
                                aeropuerto.getPaxControl().asignarAvion(indice, avioncito)
                                println("\n----------------------------------------------")
                                println("| Se asigno correctamento el avion "+ avioncito.referencia + "       |")
                                println("----------------------------------------------\n")
                            }               
                        }
                        else {
                            println("\n----------------------------------------------")
                            println("| El vuelo ta tiene un avion asignado        |")
                            println("----------------------------------------------\n")
                        }

                    }   
                    case 13 => ver = false
                    case _ => println("Error al seleccionar una opción")
                }
            }
            
            def menuPax() {
                
                println("\n==========================================")
                println("|        Bienvenidad al PaxControl       |")
                println("==========================================")
                println("Ref. de vuelo:  " + vuelo.referencia)
                println("==========================================")
                println("[1] Asignar comidas")
                println("[2] Asignar reporte")
                println("[3] Agregar matricula")
                println("[4] Asignar tripulacion")
                println("[5] Asignar pasajeros")
                println("[6] Asignar dia de salida")
                println("[7] Asignar hora de salida")
                println("[8] Asignar hora de llegada")
                println("[9] Asignar hora de inico de abordaje")
                println("[10] Asignar hora final de abordaje")
                println("[11] Asignar el primer pasajero en abordar")
                println("[12] Asignar avion")
                println("[13] salir")
                println("==========================================\n")
            }
        }   
    }

    def menu() : String = {
        println("=======================================")
        println("| Bienvenido Al Sistema del aeropuerto |")
        println("=======================================")
        println("Que desea hacer: ")
        println("[1] Chek In")
        println("[2] Sala de abordaje")
        println("[3] Pax Control")
        println("[4] Ver pasajeros")
        println("[5] Ver vuelos")
        println("[6] Ver reportes")
        println("[7] Crear vuelo")
        println("[8] Ver aviones en pista")
        println("=======================================")
        println("[9] salir                              |")
        println("=======================================")
        return "=======================================\n| Bienvenido Al Sistema del aeropuerto |\n=======================================\nQue desea hacer: \n[1] Chek In"
    }
}
