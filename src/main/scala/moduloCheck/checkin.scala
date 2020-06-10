package moduloCheck

import Array._
import elementos._
import personas._

class CheckIn(dimMal : Array[Int], peso : Int, exc : Int, cob : Int){
    //Atributos
    
    //Estos atributos me dicen restricciones
    var _dimMaleta : Array[Int] = dimMal //Restriciones de la maleta
    var _pesoMaxMal : Int = peso //Peso max de las maletas
    var _exceso : Int = exc //El valor que hay que pagar por exceso
    var _cobro : Int  = cob //El cobro de algun servicio
    //Metodos

    //Getters 

    def dimMaleta = _dimMaleta
    def pesoMaxMal = _pesoMaxMal
    def exceso = _exceso
    def cobro = _cobro

    //Setters
    def dimMaleta_= (dimMal : Array[Int]) : Unit = _dimMaleta = dimMal
    def pesoMaxMal_= (peso : Int) : Unit = _pesoMaxMal = peso
    def exceso_= (exc : Int) : Unit = _exceso = exc
    def cobro_= (cob : Int) : Unit = _cobro = cob

    //Metodos de verificacion de datos
    def verficarIdentificacion(persona : Persona, id : String) : Boolean ={
        //Recibe una persona y un id verificando que la persona que se le paso tenga ese id
        var ver : Boolean = false
        if(persona.id == id){
            ver = true
        }
        return ver
    }

    def verificarNombre(persona : Persona, name : String) : Boolean ={
        //Recibe una persona y un posible nombre, verificando que 
        var ver : Boolean = false
        if(persona.nombre == name){
            ver = true
        }
        return ver
    }

    def verificarTamMaleta(maleta : Maleta) : Boolean ={
        //Recibe una maleta y verifica que estas no se excedan del tamaño permitido
        var ver : Boolean = true
        var temp : Array[Int] = maleta.dimension
        if(temp(0) > _dimMaleta(0) || temp(1) > _dimMaleta(1) || temp(2) > _dimMaleta(2)){
            ver = false
        }
        return ver
    }

    def verificarPeso(maleta : Maleta) : Boolean ={
        //Recibe una maleta para verificar el peso
        var ver : Boolean = true
        var tem : Array[Int] = maleta.dimension
        if(maleta.peso > _pesoMaxMal){
            ver = false
        }
        return ver
    }

    def verificarMenorEdad(persona : Persona) : Boolean ={
        //Me revisa si la persona es menor de edad
        var ver : Boolean = false
        if(persona.edad < 18){
            ver = true
        }
        return ver
    }

    //Metodos de cobro
    def cobrarExceso(peso : Int) : Int ={
        //Devuelve el precio a pagar por exceso de peso
        return (peso/_pesoMaxMal)*_exceso
    }

    //Metodos de asociacion
    def asociarServico(pasajero : Pasajero, servicio : ServicioEspecial, num : Int) : Boolean ={
        //Le asigna un servicio especial a un pasajero siempre que halla pagado por este
        var ver : Boolean = false
        if(servicio.descripcion == "servicio de silla" || num >= _cobro){
            pasajero.servicio = servicio
            ver = true
        }
        return ver
    }

    def asociarMaleta(pasajero : Pasajero, maleta : Maleta) : Unit ={
        //Le asocia a un pasajero su maleta
        pasajero.maleta = maleta
    }

    def asociarSilla(pasaje : Pasaje, silla : String) : Unit ={
        //Le asocia al pasaje la silla que deberia estar
        pasaje.silla = silla
    }

    def asignarEncargado(persona : Infante, encargado : Trabajador) : Unit ={
        //Le asigna a un infante un encargado de vuelo
        persona.asistente = encargado
    }
}
