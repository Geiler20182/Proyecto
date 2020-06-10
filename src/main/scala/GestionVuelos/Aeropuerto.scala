package GestionVuelos

import elementos._
import Controles._
import moduloCheck._
import personas._
import Pista._
import moduloSalaAbordaje._
import baseDeDatos._
import util.control.Breaks._
import scala.io._
import scala.util._
import java.io.{FileNotFoundException, IOException}

class Aeropuerto {

    private var _reportes : List[Reporte] = List()
    private var _aviones : List[Avion] = BBDD.cargarAviones()
    private var _vuelos : List[Vuelo] = BBDD.cargarVuelos()
    private var _pasajeros : List[Pasajero] = BBDD.cargarPasajeros()
    private var _sala_abordaje :  SalaAbordaje = new SalaAbordaje(_vuelos)
    private var _pax_control : PaxControl = new PaxControl(_vuelos, _aviones)


    // Getters

    def getVuelos() : List[Vuelo] = return _vuelos
    def getSalaAbordaje() : SalaAbordaje = return _sala_abordaje
    def getPaxControl() : PaxControl = return _pax_control
    def getPasajeros() : List[Pasajero] = return _pasajeros
    
    // Metodos

    def aplicarCheckIn(pasajero : Pasajero) : Pasajero = {

        var check : ModuloCheckIn = new ModuloCheckIn(pasajero)
        check.inicio()

        return check.getPasajero()
    }

    def verPasajeros() : Unit = {

        println("\n--------------------------")
        println("|         PASAJEROS       |")
        println("--------------------------")
        var cont : Int = 1
        for (p <- _pasajeros) {
            println("[" + cont + "] " + p.nombre)
            cont += 1
        }
        println("--------------------------")
        println("| Total pasajeros: " + (cont-1) + "      |")
        println("--------------------------\n")
    }

    def verVuelos(detalles : Boolean ) :  Unit = {
        

        println("\n---------------------------------------")
        println("|               VUELOS                |")
        println("---------------------------------------")

        var cont : Int = 1
        for (v <- _vuelos) {
            println("Vuelo [" + cont + "] Ref. " + v.referencia)

            if (detalles) {
                println("   + Dia de salida: " + v.diaSalida)
                println("   + Hora de salida: " + v.horaSalida)
                println("   + Hora de llegada: " + v.horaLlegada)
                println("   + Hora de inicio de abordaje: " + v.horaInicio)
                println("   + Hora final de abordaje: " + v.horaLlegadaFinal)
                print("   + Avion: ")

                if (v.avion != null) println(v.avion.referencia)
                else println("sin asignar")
                
                println("   + Cantidad de pasajeros: " + v.cantidadPasajeros)
                println("   + Comidas: " + v.comidas)

            }
            cont += 1
        }
        println("---------------------------------------")
        println("| Total vuelos en cola: " + (cont-1) + "             |")
        println("---------------------------------------\n")
        
    }

    def checkIn(num : Int) : Unit = {

        if (_pasajeros.length > 0) {
            if ( num-1 >= 0 && num-1 < _pasajeros.length) {
                _pasajeros(num-1).servicio = aplicarCheckIn(_pasajeros(num-1)).servicio
                
                var p : Int = 0
                var v : Int = 0

                while (v < _vuelos.length) {
                    p = 0
                    while (p < _vuelos(v).pasajeros.length) {
                        
                        if (_vuelos(v).pasajeros(p).nombre == _pasajeros(num-1).nombre)
                            _vuelos(v).pasajeros(p).servicio = _pasajeros(num-1).servicio
                        p += 1
                    }
                    v += 1
                }
            }

        }
    }

    def update() : Unit = {
       _vuelos = _pax_control.vuelos
       _aviones = _pax_control._aviones
       _sala_abordaje._vuelos =_vuelos
    }     

    def indiceVuelo(vuelo : String) : Int = {
        
        var ind : Int = -1
        var cont : Int = 0

        for(p <- _vuelos) {
            if(p.referencia == vuelo)
                ind = cont
            cont += 1
        }

        return ind
    }

    def verReporte(indice : Int) : Unit = {
        
        println("\n----------------------------------------------")
        println("Reporte [" + (indice + 1) + "] Vuelo: " + _vuelos(indice).referencia)
        println("----------------------------------------------\n")

    }

    def agregarReporte(r : Reporte) : Unit = {
        _reportes = r :: _reportes
    }
    
    def crearVuelo() : Unit = {
        val v = new Vuelo
        _vuelos = v :: _vuelos
        _pax_control._vuelos = _vuelos

        println("\n--------------------------------------------------")
        println("| El vuelo se creo correctamente con ref. " + v.referencia + "  |")
        println("--------------------------------------------------\n")
    }

    def verAvionesEnPista() : Unit = {
        
        var cont : Int = 1
        println("\n---------------------------------------")
        println("|               AVIONES               |")
        println("---------------------------------------")  

        for (a <- _aviones) {
            println("[" + cont + "] " + a.referencia)
            cont += 1
        }

        println("---------------------------------------")
        println("| Total aviones en pista: " + (cont-1) + "           |")
        println("---------------------------------------\n")
    }

    
}   
