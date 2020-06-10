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

object Main extends App{
    
    var ver : Boolean = true
    var aeropuerto : Aeropuerto = new Aeropuerto()

    while(ver){

        menu()

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
                            println("Se asignaron las comidas correctamente")
                        }
                        else println("Error al asignar las comidas")

                    }
                    case 2 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Reporte asignado...")
                        val nuevo_reporte = aeropuerto.getPaxControl().asignarReporte(indice, "00:00")
                        aeropuerto.agregarReporte(nuevo_reporte)
                        aeropuerto.update()

                    }
                    case 3 => {

                        println("Ingrese la matricula para el avión:")
                        val matricula  = StdIn.readLine()
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        aeropuerto.getPaxControl().agregarMatricula(indice, matricula)
                        aeropuerto.update()
                    }
                    case 4 => {
                        
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        val cantidad_pilotos = aeropuerto.getVuelos()(indice).tripulacion.pilotos.length
                        val cantidad_azafatas = aeropuerto.getVuelos()(indice).tripulacion.azafatas.length

                        var nombres_pilotos : List[String] =  List()
                        var nombres_azafatas : List[String] =  List()

                        var iter : Int = 0
                        while (iter < cantidad_pilotos) {
                            println("Ingrese el nombre para el piloto #" + iter + 1)
                            val nombre = StdIn.readLine()
                            nombres_pilotos = nombre :: nombres_pilotos
                            iter += 1
                        }
                        iter = 0
                        while (iter < cantidad_azafatas) {
                            println("Ingrese el nombre para la azafata #" + (iter + 1))
                            val nombre = StdIn.readLine()
                            nombres_azafatas = nombre :: nombres_azafatas
                            iter += 1
                        }

                        println("Nombres agregados correctamente")
                        aeropuerto.update()
                    }
                    case 5 => {

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
                        }


                        else println("Error al asignar el dia de salida")
                    }
                    case 7 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de salida:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarSalidaHora(hora, indice)
                        aeropuerto.update()
                        
                        println("Hora de salida agregada correctamente")
                    }
                    case 8 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de llegada:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarLlegadaHora(hora, indice)
                        aeropuerto.update()
                        
                        println("Hora de llegada agregada correctamente")
                    }
                    case 9 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de inicio de abordaje:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarInicioHora(hora, indice)
                        aeropuerto.update()
                        
                        println("Hora de abordaje agregada correctamente")
                    }
                    case 10 => {
                        val indice = aeropuerto.indiceVuelo(vuelo.referencia)
                        println("Ingrese la hora de final de abordaje:")
                        val hora = StdIn.readLine()
                        
                        aeropuerto.getPaxControl().asignarHoraFinal(hora, indice)
                        aeropuerto.update()
                        
                        println("Hora de abordaje  agregada correctamente")
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

                        else println("Error al agregar el pasajero")
                    }
                    case 12 => ver = false
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
                println("[4] Asignar nombres a la tripulacion")
                println("[5] Asignar pasajeros")
                println("[6] Asignar dia de salida")
                println("[7] Asignar hora de salida")
                println("[8] Asignar hora de llegada")
                println("[9] Asignar hora de inico de abordaje")
                println("[10] Asignar hora final de abordaje")
                println("[11] Asignar el primer pasajero en abordar")
                println("[12] salir")
                println("==========================================\n")
            }
        }   
    }

    def menu() : Unit = {
        
        println("\n=======================================")
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
        println("=======================================\n")
    }
}
