package server

import akka.http.scaladsl.Http
import akka.http.scaladsl.server._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import pruebas._
import personas._
import moduloCheck._

object Server extends App with Directives {
    implicit val system = ActorSystem("actor-system")
    implicit val materializer : ActorMaterializer = ActorMaterializer()
    // val principal : Main = new Main()
    // val pasa : Pasajero = new Pasajero("M", "M", 12)
    // var check : ModuloCheckIn = new ModuloCheckIn(new Pasajero("M", "M",  12))
    val routes : Route = 
        path("aeropuerto"){
            get{
            // complete(principal.menu())
            complete("Hola")
            }
        } ~
        path("aeropuerto" / Segment){ (choose) =>
            get{
            //complete(principal.aeropuerto.verPasajeros())
            complete("Mundo")
            }
        } ~
        path("aeropuerto" / Segment / Segment){ (data1, data2) =>
            get{
            //principal.aeropuerto.checkIn(data2.asInstanceOf[Int])
            complete("Cruel")
            }
        }
    Http().bindAndHandle(routes,"127.0.0.1", 8002)
}  