package servicios

class Compensacion (n : Boolean) {

    // Atributos

    private var _dinero : Double = 2000 
    private var _millas : Double = 1000
    private var _vuelos : Int = 1
    private var _hospedaje : Boolean = n
    private var _transporte : Boolean = n

    // getters

    def getDinero() : Double = _dinero
    def getMillas() : Double = _millas
    def getVuelos() : Int = _vuelos
    def getHospedaje() : Boolean = _hospedaje
    def getTransporte() : Boolean = _transporte
    
}