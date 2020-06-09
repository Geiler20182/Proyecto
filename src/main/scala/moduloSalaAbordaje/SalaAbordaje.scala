package moduloSalaAbordaje

import personas._
import Pista._

class SalaAbordaje (vuelos : List[Vuelo]){

    var _vuelos : List[Vuelo] = vuelos
    
    

    def verificarTripulacionDocumento(con_referencia: String, tripulacion : Tripulacion) : Boolean = {
        
        var bandera : Boolean = false

        if ( (_vuelos.filter( _.referencia == con_referencia)).length > 0) {

            var tripulacion_registrada = (_vuelos.filter( _.referencia == con_referencia))(0).tripulacion

            var tripulantesR : List[Persona] = tripulacion_registrada.pilotos
            var tripulantesC : List[Persona] = tripulacion.pilotos
                            
            var iterR : Int = 0
            var iterC : Int = 0
            var iguales : Int = 0

            if (tripulantesR.length == tripulantesC.length) {

                while (iterR < tripulantesR.length) {
                    iterC = 0
                    while(iterC < tripulantesC.length) {
                        if (tripulantesR(iterR).id == tripulantesC(iterC).id) iguales += 1 
                        iterC += 1
                    }
                    iterR += 1
                }

            }

            tripulantesR = tripulacion_registrada.azafatas
            tripulantesC  = tripulacion.azafatas

            if (tripulantesR.length == tripulantesC.length) {
                iterR = 0
                while (iterR < tripulantesR.length) {
                    iterC = 0
                    while(iterC < tripulantesC.length) {
                        if (tripulantesR(iterR).id == tripulantesC(iterC).id) iguales += 1 
                        iterC += 1
                    }
                    iterR += 1
                }

            }

            if ( (tripulantesC.length + tripulantesR.length) == iguales)
                bandera =  true
              

        }
         
        return bandera
    }
    
    def verificarTripulacionNombre(con_referencia : String, tripulacion : Tripulacion) : Boolean = {
        
        var bandera : Boolean = false

        if ( (_vuelos.filter( _.referencia == con_referencia)).length > 0) {

            var tripulacion_registrada = (_vuelos.filter( _.referencia == con_referencia))(0).tripulacion

            var tripulantesR : List[Persona] = tripulacion_registrada.pilotos
            var tripulantesC : List[Persona] = tripulacion.pilotos
                            
            var iterR : Int = 0
            var iterC : Int = 0
            var iguales : Int = 0

            if (tripulantesR.length == tripulantesC.length) {
                while (iterR < tripulantesR.length) {
                    iterC = 0
                    while(iterC < tripulantesC.length) {
                        if (tripulantesR(iterR).nombre == tripulantesC(iterC).nombre) iguales += 1
                        iterC += 1
                    }
                    iterR += 1
                }

            }

            tripulantesR = tripulacion_registrada.azafatas
            tripulantesC  = tripulacion.azafatas

            if (tripulantesR.length == tripulantesC.length) {
                iterR = 0
                while (iterR < tripulantesR.length) {
                    iterC = 0
                    while(iterC < tripulantesC.length) {
                        if (tripulantesR(iterR).nombre == tripulantesC(iterC).nombre) iguales += 1
                        iterC += 1
                    }
                    iterR += 1
                }

            }

            if ( (tripulantesC.length + tripulantesR.length) == iguales)
                bandera =  true
              

        }
         
        return bandera
    }

    def verificarDisponibilidadSillas(con_referencia : String) : Int = {

        var sillas_dip : Int = 0

        if ( (_vuelos.filter( _.referencia == con_referencia)).length > 0 )
            sillas_dip = 200 - ((_vuelos.filter( _.referencia == con_referencia))(0).cantidadPasajeros)
        

        return sillas_dip
    } 

    def verificarPasajerosSillaRueda() : Unit = {}
    
}