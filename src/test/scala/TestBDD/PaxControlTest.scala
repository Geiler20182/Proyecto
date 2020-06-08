import org.scalatest.GivenWhenThen
import org.scalatest.FeatureSpec
import Controles._
import Pista._
import personas._

class PaxControlTest extends FeatureSpec with GivenWhenThen {
    var paxCotrol : PaxControl = new PaxControl

    var piloto : Piloto = new Piloto
    piloto._nombre = "Luis"
    piloto._genero = "M" // Masculino
    var pilotos : List[Piloto] = List(piloto)
    var azafata : Azafata = new Azafata
    azafata._nombre = "María"
    azafata._genero = "P" // Pansexual
    var azafatas : List[Azafata] = List(azafata) 
    var tripulacion : Tripulacion = new Tripulacion
    tripulacion._pilotos = pilotos
    tripulacion._azafatas = azafatas
    var avion : Avion = new Avion
    var pasajero : Pasajero = new Pasajero
    pasajero._nombre = "Guido"
    pasajero._numSilla = 100
    var pasajeros : List[Pasajero] = List(pasajero)

    var vuelo : Vuelos = new Vuelos
    vuelo._comidas = 50
    vuelo._combustible = 60.5
    vuelo._tripulacion = tripulacion
    vuelo._referencia = "XX23584D"
    vuelo._diaSalida = 10
    vuelo._horaSalida = "10:00" // Formato 24 horas
    vuelo._horaInicio = "9:00" // Hora de Inicio de abordaje
    vuelo._horaLlegada = "13:00"
    vuelo._horaLlegadaFinal = "9:40" // Hora final de abordaje
    vuelo._avion = avion
    vuelo._pasajeros = pasajeros
    vuelo._estadoVuelo = false

    paxCotrol._vuelos = vuelo::paxCotrol._vuelos

    feature("Enviar Informacion") {
        scenario("Información de los tripulantes") {
            Given("Dado un grupo de tripulantes")
            var tripulantes2 : Tripulacion = tripulacion

            When("Cuando se agrega a un vuelo los tirpulantes")
            paxCotrol._vuelos(0)._tripulacion = tripulantes2

            Then("Se envía la información de los tripulantes")
            var tripulantes3 : Tripulacion = paxCotrol.enviarInformacion(0)
            assert(tripulantes2 == tripulantes3)
        }
    }

    feature("Asignar comida") {
        scenario("Comida para un vuelo") {
            Given("Dada una cantidad de comida")
            var comida : Int = 100

            When("Cuando haya un vuelo para asignarle")
            var vuelo2 : Vuelos = vuelo
            paxCotrol._vuelos = vuelo2::paxCotrol._vuelos

            Then("Se le asigna la cantidad de comida deseada")
            paxCotrol.asignarComida(1, comida)
            assert(paxCotrol._vuelos(1)._comidas == 100)
        }
    }

    feature("Asignar Combustible") {
        scenario("Cobustible para el avión") {
            Given("Dado unca cantidad de combustible")
            var combustible : Double = 65.5

            When("Cuando haya un vuelo para asignarle combustible")
            var vuelo3 : Vuelos = vuelo
            paxCotrol._vuelos = vuelo3::paxCotrol._vuelos

            Then("Se le asigna el combustible deseado")
            paxCotrol.asignarCombustible(2, combustible)
            assert(paxCotrol._vuelos(1)._combustible == combustible)
        }
    }

    feature("Verificar Fallas") {
        var estadoFalla : Boolean = true
        scenario("El vuelo no tiene fallas") {
            Given("Dado que no hay una falla")
            var falla : String = "Ninguna"

            When("Cuando haya un vuelo que no tenga alguna falla")
            var vuelo4 : Vuelos = vuelo
            paxCotrol._vuelos = vuelo4::paxCotrol._vuelos

            Then("El vuelo va a decir que no tiene ninguna falla")
            estadoFalla = paxCotrol.verificarFallas(3, falla)
            assert(paxCotrol._vuelos(3)._falla == falla && estadoFalla)
        }
        scenario("El vuelo puede contener una falla") {
            Given("Dada una falla")
            var falla2 : String = "El tren de aterrizaje está fallando"

            When("Cuando haya un vuelo que pueda tener alguna falla")
            var vuelo5 : Vuelos = vuelo
            vuelo5._falla = falla2
            paxCotrol._vuelos = vuelo5::paxCotrol._vuelos

            Then("Se va a notificar la falla del vuelo")
            estadoFalla = paxCotrol.verificarFallas(4, falla2)
            assert(paxCotrol._vuelos(4)._falla == falla2 && estadoFalla == false)
        }
    }

    feature("Asignar vuelo con avión") {
        scenario("El vuelo está esperando un avión") {
            Given("Dado un avión desocupado")
            var avion2 : Avion = new Avion
            var estadoOriginal : Boolean = avion2._estadoVuelo

            When("Cuando se le asigne a un vuelo")
            var vuelo6 : Vuelos = vuelo
            paxCotrol._vuelos = vuelo6::paxCotrol._vuelos
            paxCotrol.asignarVuelo(5, avion2)

            Then("El avión desocupado va a tener un vuelo asignado")
            assert(paxCotrol._vuelos(5)._avion._estadoVuelo == true && estadoOriginal == false) 
        }
    }

    feature("Asignar reportes") {
        scenario("Hacer un reporte de un vuelo") {
            Given("Dado un reporte")
            var reporte : Reporte = new Reporte
            paxCotrol._reporte = reporte

            When("Cuando haya un vuelo")
            var vuelo7 : Vuelos = vuelo
            paxCotrol._vuelos = vuelo7::paxCotrol._vuelos

            Then("Se hará un reporte del vuelo")
            paxCotrol.asignarReporte(6, "18:00")
            assert(paxCotrol._reporte._cantidadPasajeros > 0)
        }
    }

    feature("Asignar Matricula") {
        scenario("Un avión necesita una matricula según el vuelo") {
            Given("Dada una matricula")
            var referencia : String = "DDD8456X"

            When("Haya un vuelo con un avión sin matricula")
            var vuelo8 : Vuelos = vuelo
            var referenciaOriginal : String = vuelo8._avion._referencia
            paxCotrol._vuelos = vuelo8::paxCotrol._vuelos

            Then("El avion sin matricula va a tener una")
            paxCotrol.agregarMatricula(7, referencia)
            assert(paxCotrol._vuelos(7)._avion._referencia == referencia && referenciaOriginal == "")
        }
    }
}