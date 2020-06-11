package personas

import elementos._

class Pasajero(name : String, id : String, edad : Int) extends Persona{
    //Atributos

    //Heredados
    override var _nombre : String = name
    override var _id : String = id
    override var _edad : Int = edad

    //Propios
    var _pasaje : Pasaje = _
    var _maleta : Maleta = null
    var _servicio : ServicioEspecial = null
    var _chekeado : Boolean = false

    //Metodos

    //Getters
    def pasaje = _pasaje
    def maleta = _maleta
    def servicio = _servicio
    def chekeado = _chekeado

    //Setters
    def pasaje_= (pass : Pasaje) ={
        //Necesita que el pasajero no tenga todavia un pasaje
        if(_pasaje == null){
            _pasaje = pass
        }
    }

    def maleta_= (mal : Maleta) = _maleta = mal
    
    def servicio_= (serv : ServicioEspecial) = _servicio = serv

    def chekeado_= (bool : Boolean) = _chekeado = true //Me dice que el pasajero ya paso por el chek-in
}