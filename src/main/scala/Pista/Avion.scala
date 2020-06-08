package Pista

class Avion {
    // Atributos
    var _referencia : String = ""
    var _cantidadPasajeros : Int = 200
    var _estadoVuelo : Boolean = false
    var _referenciaVuelo : String = _

    // Getters
    def referencia = _referencia
    def cantidadPasajeros = _cantidadPasajeros
    def referenciaVuelo = _referenciaVuelo
    def estadoVuelo = _estadoVuelo

    // Setters
    def referencia_= (nuevaReferencia : String) = _referencia = nuevaReferencia
    def cantidadPasajeros_= (nuevaCantidad : Int) = _cantidadPasajeros = nuevaCantidad
    def referenciaVuelo_= (nuevoRVuelo : String) = _referenciaVuelo = nuevoRVuelo
    def estadoVuelo_= (nuevoEstado : Boolean) = _estadoVuelo = nuevoEstado
}