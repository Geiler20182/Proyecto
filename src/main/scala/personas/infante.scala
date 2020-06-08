package personas

import elementos._

class Infante(name : String, id : String, edad : Int) extends Pasajero(name, id, edad){
    //Variables nuevas
    var _asistente : Trabajador =_

    //metodos

    //Getters
    def asistente = _asistente
    
    //Setters
    def asistente_= (asist : Trabajador) = _asistente = asist

    override def servicio_= (serv : ServicioEspecial) ={
        //Necesita que el pasajero no tenga todavia un servicio
        if(_servicio == null){
            _servicio = serv
        }
    }
}
