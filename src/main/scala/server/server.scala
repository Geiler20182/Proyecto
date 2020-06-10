// package server

// import akka.http.scaladsl.Http
// import akka.http.scaladsl.server._
// import akka.actor.ActorSystem
// import akka.stream.ActorMaterializer
// import pruebas._
// import personas._
// import moduloCheck._

// object Server extends App with Directives {
//     implicit val system = ActorSystem("actor-system")
//     implicit val materializer : ActorMaterializer = ActorMaterializer()
//     val principal : Main = new Main()
//     var check : ModuloCheckIn = new ModuloCheckIn(new Pasajero("M", "M",  12))
//     val routes : Route = 
//         path("aeropuerto"){
//             get{
//             complete(principal.menu())
//             }
//         } 

//         path("aeropuerto" / Segment){ (choose) =>
//             complete(principal.aeropuerto.verPasajeros())
//         }

//         path("aeropuerto" / Segment / Segment){ (data1, data2) =>
//             principal.aeropuerto.checkIn(data2.asInstanceOf[Int])
//             complete(check.menu())
//         }

//     Http().bindAndHandle(routes,"0.0.0.0", 8002)
// }  