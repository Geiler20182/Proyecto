package Pista

import Usuario._

class Vuelos {
    // Atributos
    var _comidas : Int = _
    var _combustible : Double = _ 
    var _tripulacion : Tripulacion = _
    var _falla : String = "Ninguna"
    var _referencia : String = _
    var _diaSalida : Int = _
    var _horaSalida : String = _
    var _horaInicio : String = _
    var _horaLlegada : String = _
    var _avion : Avion = new Avion
    var _cantidadPasajeros : Int = _avion._cantidadPasajeros
    var _pasajeros : List[Pasajero] = List()
    var _estadoVuelo : Boolean = false

    // Getters
    def comidas = _comidas
    def combustible = _combustible
    def tripulacion = _tripulacion
    def falla = _falla
    def referencia = _referencia
    def diaSalida = _diaSalida
    def horaSalida = _horaSalida
    def horaInicio = _horaInicio
    def horaLlegada = _horaLlegada
    def horaLlegadaFinal = _horaLlegadaFinal
    def avion = _avion
    def cantidadPasajeros = _cantidadPasajeros
    def estadoVuelo = _estadoVuelo
    
    // Setters
    def comidas_= (nuevaComida : Int) = _comidas = nuevaComida
    def combustible_= (nuevoCombustible : Double) = _combustible = nuevoCombustible
    def tripulacion_= (nuevaTripulacion : Tripulacion) = _tripulacion = nuevaTripulacion
    def falla_= (nuevaFalla : String) = _falla = nuevaFalla
    def referencia_= (nuevaReferencia : String) = _referencia = nuevaReferencia
    def diaSalida_= (nuevoDiaSalida : Int) = _diaSalida = nuevoDiaSalida
    def horaSalida_= (nuevaHoraSalida : String) = _horaSalida = nuevaHoraSalida
    def horaInicio_= (nuevaHoraInicio : String) = _horaInicio = nuevaHoraInicio
    def horaLlegada_= (nuevaHoraLlegada : String) = _horaLlegada = nuevaHoraLlegada
    def horaLlegadaFinal_= (nuevaHoraFinal : String) = _horaLlegadaFinal = nuevaHoraFinal
    def avion_= (nuevoAvion : Avion) = _avion = nuevoAvion
    def cantidadPasajeros_= (nuevaCantidad : Int) = _cantidadPasajeros = nuevaCantidad
    def estadoVuelo_= (nuevoEstado : Boolean) = _estadoVuelo = nuevoEstado
}