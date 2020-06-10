package Controles

import personas._
import Pista._
import scala.io._

class PaxControl(nuevos_vuelos : List[Vuelo], aviones : List[Avion]) {
    // Atributos
    var _vuelos : List[Vuelo] = nuevos_vuelos
    var _aviones : List[Avion] = aviones
    var _reporte : Reporte = new  Reporte

    def vuelos = _vuelos
    // Métodos
    def enviarInformacion(indiceVuelo : Int) : Tripulacion = return _vuelos(indiceVuelo)._tripulacion
    def asignarComida(indiceVuelo : Int, cantComida : Int) : Unit = _vuelos(indiceVuelo)._comidas = cantComida
    def asignarCombustible(indiceVuelo : Int, cantCombustible : Double) : Unit = _vuelos(indiceVuelo)._combustible = cantCombustible
    def verificarFallas(indiceVuelo : Int, falla : String) : Boolean = {
        if(_vuelos(indiceVuelo)._falla == "Ninguna") {
            return true
        }
        return false
    }
    def asignarAvion(indiceVuelo : Int, avion : Avion) : Unit = {
        _vuelos(indiceVuelo)._avion = avion
        _vuelos(indiceVuelo)._avion._estadoVuelo = true
    }
    def asignarReporte(indiceVuelo : Int, hora : String) : Reporte = {
        _reporte._tripulantesVuelo = _vuelos(indiceVuelo)._tripulacion
        _reporte._referenciaVuelo = _vuelos(indiceVuelo)._referencia
        _reporte._avionVuelo = _vuelos(indiceVuelo)._avion
        _reporte._estadoVuelo = _vuelos(indiceVuelo).estadoVuelo
        _reporte._cantidadPasajeros = _vuelos(indiceVuelo)._pasajeros.length
        _reporte._cantidadComida = _vuelos(indiceVuelo)._comidas
        _reporte._pasajerosVuelo = _vuelos(indiceVuelo)._pasajeros
        _reporte._retraso = verificarRetraso(indiceVuelo, hora)

        return _reporte
    }
    def asignarPrimerPasajero(indiceVuelo : Int, nombre : String) : Unit =  _vuelos(indiceVuelo).primerPasajero_=(nombre)

    def agregarMatricula(indiceAvion : Int, matricula : String) : Unit = _vuelos(indiceAvion)._avion._referencia = matricula
    def asignarNombres(nombresPilotos : List[String], nombresAzafatas : List[String], indiceVuelo : Int) : Unit = {
        var n : Int = 0
        for(nombre <- nombresPilotos) {
            _vuelos(indiceVuelo)._tripulacion._pilotos(n)._nombre = nombre
            n = n + 1
        }
        n = 0
        for(nombre <- nombresAzafatas) {
            _vuelos(indiceVuelo)._tripulacion._azafatas(n)._nombre = nombre
            n = n + 1
        }
    }
    def asignarPasajeros(cantidad : Int, indiceVuelo : Int) : Unit = _vuelos(indiceVuelo)._cantidadPasajeros = cantidad
    def asignarSalida(dia : Int, indiceVuelo : Int) : Unit = _vuelos(indiceVuelo)._diaSalida = dia
    def asignarSalidaHora(hora : String, indiceVuelo : Int) = _vuelos(indiceVuelo)._horaSalida = hora
    def asignarLlegadaHora(hora : String, indiceVuelo : Int) = _vuelos(indiceVuelo)._horaLlegada = hora
    def asignarInicioHora(hora : String, indiceVuelo : Int) = _vuelos(indiceVuelo)._horaInicio = hora
    def primerPasajeros(indiceVuelo : Int) : String = return _vuelos(indiceVuelo)._pasajeros(0)._nombre
    def verificarRetraso(indiceVuelo : Int, horaAtual : String) : Boolean = {
        if(_vuelos(indiceVuelo)._horaSalida == horaAtual && _vuelos(indiceVuelo)._estadoVuelo == true) {
            return false
        }
        return true
    }
    def asignarHoraFinal(hora : String, indiceVuelo : Int) : Unit = _vuelos(indiceVuelo)._horaLlegadaFinal = hora

    def agregarTripulacion(indice : Int, trip : Tripulacion) : Unit = _vuelos(indice)._tripulacion = trip
}