// package server

// import akka.http.scaladsl.Http
// import akka.http.scaladsl.server._
// import akka.actor.ActorSystem
// import akka.stream.ActorMaterializer

// object Server extends App with Directives {
//     implicit val system = ActorSystem("actor-system")
//     implicit val materializer : ActorMaterializer = ActorMaterializer()

//     val routes : Route =
//         path(){
//             get{

//             }
//         } 
// } 