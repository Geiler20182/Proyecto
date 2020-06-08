package Usuario

class Pasajero {
    // Atributos
    var _nombre : String = _ 
    //var _referenciaVuelo : String = _
    var _numSilla : Int = _

    // Getters
    def nombre = _nombre
    //def refereniaVuelo = _referenciaVuelo
    def numSilla = _numSilla

    // Setters
    def nombre_= (nuevoNombre : String) = _nombre = nuevoNombre
    //def refereniaVuelo_= (nuevaReferencia : String) = _referenciaVuelo = nuevaReferencia
    def numSilla_= (nuevaSilla : Int) = _numSilla = nuevaSilla
}