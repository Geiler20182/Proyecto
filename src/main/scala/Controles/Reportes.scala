package Controles

import Pista._
import Usuario._

class Reporte {
    // Atributos
    var _tripulantesVuelo : Tripulacion = _
    var _referenciaVuelo : String = _
    var _avionVuelo : Avion = _
    var _estadoVuelo : Boolean = _
    var _cantidadPasajeros : Int = 0
    var _cantidadComida : Int = _
    var _pasajerosVuelo : List[Pasajero] = List()
    var _retraso : Boolean = _

    // Getters
    def tripulantesVuelo = _tripulantesVuelo
    def referenciaVuelo = _referenciaVuelo
    def avionVuelo = _avionVuelo
    def estadoVuelo = _estadoVuelo
    def cantidadPasajeros = _cantidadPasajeros
    def cantidadComida = _cantidadComida
    def retraso = _retraso

    // Setters
    def tripulantesVuelo_= (nuevosTripulantes : Tripulacion) = _tripulantesVuelo = nuevosTripulantes
    def referenciaVuelo_= (nuevaReferencia : String) = _referenciaVuelo = nuevaReferencia
    def avionVuelo_= (nuevoAvion : Avion) = _avionVuelo = nuevoAvion
    def estadoVuelo_= (nuevoEstado : Boolean) = _estadoVuelo = nuevoEstado
    def cantidadPasajeros_= (nuevaCantidad : Int) = _cantidadPasajeros = nuevaCantidad
    def cantidadComida_= (nuevaComida : Int) = _cantidadComida = nuevaComida
    def retraso_= (nuevoRetraso : Boolean) = _retraso = nuevoRetraso
}