package pruebas

import moduloCheck._
import elementos._
import personas._
import Array._
import scala.io._
import scala.util._

object Main extends App{
    var pasajero : Pasajero = new Pasajero("Marcos", "1", 27)
    var ver : Boolean = true
    while(ver){
        println("===============================")
        println("Bienvenido Al Sistema del aeropuerto")
        println("===============================")
        println("Que desea hacer: ")
        println("[1] Chek In")
        println("[2] Sala de espera")
        println("[3] Pax Control")
        println("[4] salir")
        println("===============================")
        var opcion : Try[Int] = Try(StdIn.readInt())
        var num : Int = 0;
        opcion match{
            case Success(s) => num = s
            case Failure(f) => println("Error a la hora de escoger")
        }

         num match{
            case 1 => chekIn(pasajero)
            case 2 => println("En creación")
            case 3 => println("En creación")
            case 4 => ver = false
            case _ => println("Error al intentar escoger un servicio")
        }

        def chekIn(pasajero : Pasajero){
            var modulo : ModuloCheckIn = new ModuloCheckIn(pasajero)
            modulo.inicio()
        }
    }
}