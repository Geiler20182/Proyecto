package moduloCheck

import elementos._
import personas._
import Array._
import scala.io._
import scala.util._

class ModuloCheckIn(pasa : Pasajero){
    var dimensionMaleta : Array[Int] = Array(40, 55, 23)
    var peso : Int = 20
    var cobro : Int = 10
    var pasaje : Pasaje = new Pasaje("Cali", "Bogota")
    var checkIn : CheckIn = new CheckIn(dimensionMaleta, peso, cobro, cobro)
    var pasajero : Pasajero = pasa
    var infante : Infante = new Infante("Carrasco", "3", 15)
    var ver : Boolean = true
    pasajero.pasaje = pasaje

    def inicio() : Unit = {
        while(ver){
            println("===============================")
            println("Bienvenido Al CheckIn")
            println("===============================")
            println("Que desea hacer: ")
            println("[1] Verificar datos")
            println("[2] Asociar Un servicio")
            println("[3] Pesar la maleta")
            println("[4] salir")
            println("===============================")
            var opcion : Try[Int] = Try(StdIn.readInt())
            var num : Int = 0;
            opcion match{
                case Success(s) => num = s
                case Failure(f) => num = 0
            }

            num match{
                case 1 => verificacionDeDatos(pasajero)
                case 2 => servicios(pasajero)
                case 3 => maletas(pasajero)
                case 4 => ver = false
                case _ => println("Hubo un error a la hora de escoger")
            }


        }

        def verificacionDeDatos(pasajero : Pasajero){
            println("Porfavor ingrese el nombre del pasajero:")
            var nombre : String = StdIn.readLine()
            println("Porfavor ingrese el #documento del pasajero:")
            var id : String = StdIn.readLine()
            var verNombre : Boolean = checkIn.verificarNombre(pasajero, nombre)
            var verId : Boolean = checkIn.verficarIdentificacion(pasajero, id)
            if(verNombre && verId){
                pasajero.chekeado = false
                println("Porfavor ingrese la silla del pasajero:")
                id = StdIn.readLine()
                checkIn.asociarSilla(pasajero.pasaje, id)
                println("Operacion exitosa")
                println(pasajero.chekeado)
                println(pasajero.pasaje.silla)
                if(checkIn.verificarMenorEdad(pasajero)){
                    var trabajador : Trabajador = new Trabajador("Mark", "1", 35)
                    var servicio : ServicioInfantil = new ServicioInfantil
                    checkIn.asociarServico(pasajero.asInstanceOf[Infante], servicio, checkIn.cobro)
                    checkIn.asignarEncargado(pasajero.asInstanceOf[Infante], trabajador)
                }
            }

            else{
                println("Operacion denegada")
            }
        }

        def maletas(pasajero : Pasajero){

            println("Porfavor ingrese el ancho de la maleta:")
            var ancho : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el largo de la maleta:")
            var largo : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el alto de la maleta:")
            var alto : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el peso de la maleta:")
            var peso : Try[Int] = Try(StdIn.readInt())
            var pesoR : Int = 100
            var maletaDim : Array[Int] = Array(100, 100, 100)

            def action(p : Int, q : Int, r : Int, s : Int){
                maletaDim(0) = p
                maletaDim(1) = q
                maletaDim(2) = r
                pesoR = s
            }

            (ancho, largo, alto, peso) match{
                case (Success(p), Success(q), Success(r), Success(s)) => action(p, q, r, s)
                case (_, _, _, _) => pesoR = 100
            }

            var maleta : Maleta = new Maleta(maletaDim, pesoR)
            if(checkIn.verificarTamMaleta(maleta)){
                println("Ya verifique el peso")
                if(checkIn.verificarPeso(maleta)){
                    checkIn.asociarMaleta(pasajero, maleta)
                }

                else{
                    var cantidad : Int = checkIn.cobrarExceso(pesoR)
                    println("La maleta tiene exceso de peso\nSi quiere llevar ese peso debe pagar " + cantidad + " Pesos")
                    var monto : Try[Int] = Try(StdIn.readInt())
                    var pesos = 0
                    var flag : Boolean = false
                    monto match{
                        case Success(s) => {
                                        flag = true
                                        pesos = s
                                    }
                        case Failure(f) => println("Hubo un error en la operación")
                    }

                    if(flag && pesos >= cantidad){
                        checkIn.asociarMaleta(pasajero, maleta)
                        println(pasajero.maleta.peso)
                    }

                    else{
                        println("Hubo un problema con el pago")
                    }
                }
            }

            else{
                println("Dimensiones no aceptadas")
            }
        }

        def servicios(pasajero : Pasajero){
            println("===============================")
            println("Bienvenido A los servicios")
            println("===============================")
            println("Que desea hacer: ")
            println("[1] Silla de rueda")
            println("[2] Mascota")
            println("[3] Mascota por cambina")
            println("===============================")
            var opcion : Try[Int] = Try(StdIn.readInt())
            var num : Int = 0
            opcion match{
                case Success(s) => num = s
                case Failure(f) => println("Error a la hora de escoger")
            }

            num match{
                case 1 => silla(pasajero)
                case 2 => mascotaNormal(pasajero)
                case 3 => mascotaCabina(pasajero)
                case _ => println("Error al intentar escoger un servicio")
            }


            def silla(pasajero : Pasajero){
                var servicio : ServicioSilla = new ServicioSilla
                var ver : Boolean = checkIn.asociarServico(pasajero, servicio, 0)
                println("Servicio agregado con exito")
                println(pasajero.servicio.descripcion)
            }

            def mascotaNormal(pasajero : Pasajero){
                var servicio : ServicioMascota = new ServicioMascota
                println("El precio por este servicio es de " + checkIn.cobro)
                var num : Try[Int] = Try(StdIn.readInt())
                var ver : Boolean = false
                num match{
                    case Success(s) => ver = checkIn.asociarServico(pasajero, servicio, s)
                    case Failure(f) => ver = checkIn.asociarServico(pasajero, servicio, 0)
                }
                if(ver){
                    println("servicio agregado")
                }

                else{
                    println("Servicio denegado")
                }

                var trate : Try[ServicioEspecial] = Try(pasajero.servicio)
                println(trate)
            }

            def mascotaCabina(pasajero : Pasajero){
                var servicio : ServicioMascotaCabina = new ServicioMascotaCabina
                println("El precio por este servicio es de " + checkIn.cobro)
                var num : Try[Int] = Try(StdIn.readInt())
                var ver : Boolean = false
                num match{
                    case Success(s) => ver = checkIn.asociarServico(pasajero, servicio, s)
                    case Failure(f) => ver = checkIn.asociarServico(pasajero, servicio, 0)
                }
                if(ver){
                    println("servicio agregado")
                }

                else{
                    println("Servicio denegado")
                }
                var trate : Try[ServicioEspecial] = Try(pasajero.servicio)
                println(trate)
            }
        }
    }
}
