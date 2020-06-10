package baseDeDatos

import elementos._
import Controles._
import moduloCheck._
import personas._
import Pista._
import moduloSalaAbordaje._
import baseDeDatos._
import scala.io.Source
import java.io.{FileNotFoundException, IOException}

object BBDD {

    var aviones : List[Avion] = List()
    var pasajeros : List[Pasajero] = List()
    var vuelos : List[Vuelo] = List()
    
    
    def cargarVuelos() : List[Vuelo] = {

        val archivo =  "C:\\Users\\Mejia\\Documents\\POO\\ProyectoVersion4\\Proyecto\\src\\main\\scala\\baseDeDatos\\datos\\vuelos.txt"
        try { 
            
            var datos : List[List[String]] = List()
            var temp : List[String] = List()
            var cant_vuelos : Int = 0
            var iter : Int = 0

            for (line <- Source.fromFile(archivo).getLines) {

                var l : Array[String] = line.split("=")
                if (l(0) == "vuelos") 
                    cant_vuelos = l(1).toInt
             
                else {
                    
                    temp = l(1) :: temp

                    if (iter == 11) {
                        datos = temp :: datos
                        temp = List()
                        iter = 0
                    }
                }

                iter += 1
            }  

            
            for (d <- datos) {

                val pas = cargarPasajeros(d(0))
                val trip = cargarTripulacion(d(1))
                var vuelo : Vuelo  = new Vuelo()
                vuelo.tripulacion_=(trip)
                vuelo.pasajeros_=(pas)
                vuelo.cantidadPasajeros_=(d(2).toInt)

                for ( a <- aviones) {
                    if (a.referencia == d(3)) 
                        vuelo.avion_=(a)
                }

                vuelo.horaLlegadaFinal_=(d(4))
                vuelo.horaLlegada_=(d(5))
                vuelo.horaInicio_=(d(6))
                vuelo.horaSalida_=(d(7))
                vuelo.diaSalida_=(d(8).toInt)
                vuelo.combustible_=(d(9).toDouble)
                vuelo.comidas_=(d(10).toInt)

                vuelos = vuelo :: vuelos
            }
            

        } catch {
            case e: FileNotFoundException => println("No se pudo abrir el archivo " + archivo)
            case e: IOException => println("Got an IOException!")
        }
        

        return vuelos
    
    }

    def cargarAviones() : List[Avion] = {

        val archivo =  "C:\\Users\\Mejia\\Documents\\POO\\ProyectoVersion4\\Proyecto\\src\\main\\scala\\baseDeDatos\\datos\\aviones.txt"
        
        try { 
            
            for (line <- Source.fromFile(archivo).getLines) {
                aviones = new Avion(line) :: aviones
            }

        } catch {
            case e: FileNotFoundException => println("No se pudo abrir el archivo " + archivo)
            case e: IOException => println("Got an IOException!")
        }

        return aviones
    }

    def cargarTripulacion(archivo : String) : Tripulacion = {

        val arch =  "C:\\Users\\Mejia\\Documents\\POO\\ProyectoVersion4\\Proyecto\\src\\main\\scala\\baseDeDatos\\datos\\" + archivo
        
        try { 

            var pilotos : List[Piloto] = List()
            var azafatas : List[Azafata] = List()
            var cant_pilotos : Int = 0
            var cant_azafatas : Int = 0
            var bandera : Boolean = false

            for (line <- Source.fromFile(arch).getLines) {
                
                var l : Array[String] = line.split("=")
                if (l(0) == "PILOTOS")  {
                    cant_pilotos = l(1).toInt
                }
                else if(l(0) == "AZAFATAS") {
                     cant_azafatas = l(1).toInt

                }
                else if (cant_pilotos > 0 ){
                    var lst : Array[String] = l(1).split(",")
                    pilotos = new Piloto(lst(0), lst(1), lst(2).toInt) :: pilotos
                    cant_pilotos -= 1
                }  
                else if (cant_azafatas > 0) {
                    var lst : Array[String] = l(1).split(",")
                    azafatas = new Azafata(lst(0), lst(1), lst(2).toInt) ::  azafatas
                    cant_azafatas -= 1
                }

            }
            
            return new Tripulacion(pilotos, azafatas)
            
        } catch {
            case e: FileNotFoundException => println("No se pudo abrir el archivo " + archivo)
            case e: IOException => println("Got an IOException!")
        }

        return null

    }

    def cargarPasajeros(archivo : String) : List[Pasajero] = {
        
        val arch =  "C:\\Users\\Mejia\\Documents\\POO\\ProyectoVersion4\\Proyecto\\src\\main\\scala\\baseDeDatos\\datos\\" + archivo
        
        try { 
            
            var pasaj : List[Pasajero] = List()
            for (line <- Source.fromFile(arch).getLines) {
                var lst : Array[String] = line.split(",")
                val nuevo_pasajero = new Pasajero(lst(0), lst(1), lst(2).toInt) 
                nuevo_pasajero.maleta_=(new Maleta(Array(0,0,0), 0))
                nuevo_pasajero.pasaje_=(cargarPasaje(lst(3)))
                pasaj = nuevo_pasajero :: pasaj
                pasajeros = nuevo_pasajero :: pasajeros
            }

            return pasaj

        } catch {
            case e: FileNotFoundException => println("No se pudo abrir el archivo " + archivo)
            case e: IOException => println("Got an IOException!")
        }

        return null
    }

    def cargarPasaje(archivo : String) : Pasaje = {
        
        val arch =  "C:\\Users\\Mejia\\Documents\\POO\\ProyectoVersion4\\Proyecto\\src\\main\\scala\\baseDeDatos\\datos\\" + archivo
        
        try { 


            var origen : String = ""
            var destino : String = ""
            var silla : String = ""

            for (line <- Source.fromFile(arch).getLines) {
                var lst : Array[String] =  line.split("=")
                if (lst(0) == "origen")
                    origen = lst(1)
                else if (lst(0) == "destino")
                    destino = lst(1)
            }   
            return  new Pasaje(origen, destino, "")


        } catch {
            case e: FileNotFoundException => println("No se pudo abrir el archivo " + arch)
            case e: IOException => println("Got an IOException!")
        }

        return null    
    }
    def cargarPasajeros() : List[Pasajero] = {

        return pasajeros
    }
}
