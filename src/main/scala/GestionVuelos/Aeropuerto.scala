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

class Aeropuerto {

    private var _reportes : List[Reporte] = List()
    
    private var _vuelos : List[Vuelo] = BBDD.cargarVuelos()
    private var _aviones : List[Avion] = BBDD.cargarAviones()
    private var _pasajeros : List[Pasajero] = BBDD.cargarPasajeros()
    private var _sala_abordaje :  SalaAbordaje = new SalaAbordaje(_vuelos)
    private var _pax_control : PaxControl = new PaxControl(_vuelos, _aviones)


    // Getters

    def getVuelos() : List[Vuelo] = return _vuelos
    def getSalaAbordaje() : SalaAbordaje = return _sala_abordaje
    def getPaxControl() : PaxControl = return _pax_control
    def getPasajeros() : List[Pasajero] = return _pasajeros
    
    // Metodos

    def aplicarCheckIn(nombre_pasajero : String) : Unit = {

        var cont : Int = 0

        breakable {
            while (cont < _pasajeros.length) {
                if (_pasajeros(cont).nombre == nombre_pasajero) {
                    var check : ModuloCheckIn = new ModuloCheckIn(_pasajeros(cont))
                    check.inicio()
                    _pasajeros(cont).pasaje = check.getPasajero().pasaje 
                    _pasajeros(cont).maleta = check.getPasajero().maleta
                    _pasajeros(cont).servicio = check.getPasajero().servicio
                    _pasajeros(cont).chekeado = check.getPasajero().chekeado 
                    break 
                }
                cont += 1
            }
        }
    }

    def verPasajeros() : Unit = {

        println("\tPASAJEROS")
        var cont : Int = 1
        for (p <- _pasajeros) {
            println("[" + cont + "] " + p.nombre)
            cont += 1
        }
    }

    def verVuelos() :  Unit = {

        println("\tVUELOS") 
        var cont : Int = 1
        for (v <- _vuelos) {
            println("[" + cont + "] " + v.referencia)
            cont += 1
        }
        
    }

    def checkIn(num : Int) : Unit = {

        if ( num-1 >= 0 && num-1 < _pasajeros.length)
            aplicarCheckIn(_pasajeros(num-1).nombre)
    }

    def update() : Unit = {
       _vuelos = _pax_control.vuelos
       _aviones = _pax_control._aviones
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
        
        println("===============================")
        println("Reporte [" + indice + 1 + "] Vuelo: " + _vuelos(indice).referencia)
        println("===============================")
       /* _reporte._tripulantesVuelo
        _reporte._referenciaVuelo 
        _reporte._avionVuelo
        _reporte._estadoVuelo
        _reporte._cantidadPasajeros
        _reporte._cantidadComida 
        _reporte._pasajerosVuelo 
        _reporte._retraso*/
    }

    def agregarReporte(r : Reporte) : Unit = {
        _reportes = r :: _reportes
    }
    
}   
