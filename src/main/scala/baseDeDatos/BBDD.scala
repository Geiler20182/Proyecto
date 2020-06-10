package baseDeDatos

import elementos._
import Controles._
import moduloCheck._
import personas._
import Pista._
import moduloSalaAbordaje._
import baseDeDatos._


object BBDD {

    var aviones : List[Avion] = List()
    var pasajeros : List[Pasajero] = List()
    var vuelos : List[Vuelo] = List()

    var a1 : Avion = new Avion("A100")
    var a2 : Avion = new Avion("A200")
    var a3 : Avion = new Avion("A300")

    aviones = a1 :: a2 :: a3 :: aviones

    var v1 : Vuelo = new Vuelo()
    var v2 : Vuelo = new Vuelo()
    var v3 : Vuelo = new Vuelo()

    var pil1 : Piloto = new Piloto("geiler","1", 21)
    var pil2 : Piloto = new Piloto("luis","2", 21)

    var azaf1 : Azafata = new Azafata("laura", "3", 2)
    var azaf2 : Azafata = new Azafata("aleja", "4", 2)
    
    
    var tripulacion1 : Tripulacion = new Tripulacion( List(pil1, pil2), List(azaf1, azaf2))
    v1.tripulacion_=(tripulacion1)
    v2.tripulacion_=(tripulacion1)
    v3.tripulacion_=(tripulacion1)
   
    v1.avion_=(a1)
    v2.avion_=(a2)
    v3.avion_=(a3)

    vuelos = v1 :: v2 :: v3 :: vuelos

    var t1 : Pasaje = new Pasaje("Bogota", "Miami", v1.referencia)
    var t2 : Pasaje = new Pasaje("Bogota", "Miami", v2.referencia)
    var t3 : Pasaje = new Pasaje("Bogota", "Paris", v3.referencia)

    t1.silla =("A1")
    t1.silla =("A1")
    t1.silla =("A1")

    var p1 : Pasajero = new Pasajero("Guido Salazar", "1", 20)
    var p2 : Pasajero = new Pasajero("Luis Salazar", "2", 20)
    var p3 : Pasajero = new Pasajero("Geiler Mejia", "3", 20)

    p1.pasaje = (t1)
    p2.pasaje = (t2)
    p3.pasaje = (t3)


    pasajeros = p1 :: p2 :: p3 :: pasajeros
    
    def cargarVuelos() : List[Vuelo] = {

        return vuelos
    }

    def cargarAviones() : List[Avion] = {

        return aviones
    }

    def cargarPasajeros() : List[Pasajero] = {

        return pasajeros
    }
}
