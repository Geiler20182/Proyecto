package moduloCheck

import elementos._
import personas._
import Array._
import scala.io._
import scala.util._

class ModuloCheckIn(pasa : Pasajero){

    var dimensionMaleta : Array[Int] = pasa.maleta.dimension//Array(40, 55, 23) //Restricciones de las maletas
    var peso : Int = pasa.maleta.peso//20 //Peso maximo de las maletas
    var cobro : Int = 10 //El cobro por exceso y por
    var pasaje : Pasaje = pasa.pasaje// new Pasaje("Cali", "Bogota") //Creo un pasaje estandar
    var checkIn : CheckIn = new CheckIn(dimensionMaleta, peso, cobro, cobro) //Creo un modulo de checkIn con las restricciones
    var pasajero : Pasajero = pasa //Me iniciañiza el pasajero que yo quiero ver
    var infante : Infante = new Infante("Carrasco", "3", 15) //Guardo un infate como prueba
    var ver : Boolean = true
    pasajero.pasaje = pasaje //Le añado el pasaje al pasajero para hacer pruebas

    def inicio() : Unit = {
        //Esta función es la que me inicializa lo que seria el modulo del check in y sus funcionalidades
        while(ver){
            println("===============================")
            println("Bienvenido Al CheckIn")
            println("===============================")
            println("Que desea hacer: ")
            println("[1] Verificar datos")
            println("[2] Asociar Un servicio")
            println("[3] Pesar la maleta")
            println("[4] salir")
            println("===============================")
            var opcion : Try[Int] = Try(StdIn.readInt())
            var num : Int = 0;
            opcion match{
                //Me verifica que se le haya ingresado un numero
                case Success(s) => num = s
                case Failure(f) => num = 0
            }

            num match{
                case 1 => verificacionDeDatos(pasajero)
                case 2 => servicios(pasajero)
                case 3 => maletas(pasajero)
                case 4 => ver = false
                case _ => println("Hubo un error a la hora de escoger")
            }
        }

        def verificacionDeDatos(pasajero : Pasajero){
            //Me verifica que los datos del pasajero esten bien
            println("Porfavor ingrese el nombre del pasajero:")
            var nombre : String = StdIn.readLine()
            println("Porfavor ingrese el #documento del pasajero:")
            var id : String = StdIn.readLine()
            var verNombre : Boolean = checkIn.verificarNombre(pasajero, nombre) //Verifica el nombre
            var verId : Boolean = checkIn.verficarIdentificacion(pasajero, id) //Verifica el id
            if(verNombre && verId){
                //Me ingresa en caso de que los datos estan bien
                pasajero.chekeado = false //Me actualiza al pasajero (es un metodo que siempre pone en true)
                println("Porfavor ingrese la silla del pasajero:") 
                id = StdIn.readLine() //Ingreso la silla que quiere el pasajer
                checkIn.asociarSilla(pasajero.pasaje, id) //Asocio la silla al pasaje
                println("Operacion exitosa")
                /*
                println(pasajero.chekeado) //Verifico que el usuario se haya chekeado
                println(pasajero.pasaje.silla) //Verifico que el pasaje tenga la silla
                */
                if(checkIn.verificarMenorEdad(pasajero)){
                    //Veo que el usuario sea un menor de edad
                    var trabajador : Trabajador = new Trabajador("Mark", "1", 35)
                    var servicio : ServicioInfantil = new ServicioInfantil
                    checkIn.asociarServico(pasajero.asInstanceOf[Infante], servicio, checkIn.cobro) //Le asocio el servicio al pasajero
                    checkIn.asignarEncargado(pasajero.asInstanceOf[Infante], trabajador) //Le asigno el trabajador encargado
                }
            }

            else{
                //Me ingresa si hay algun dato errone
                println("Operacion denegada")
            }
        }

        def maletas(pasajero : Pasajero){
            //Me verifica las maletas
            println("Porfavor ingrese el ancho de la maleta:")
            var ancho : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el largo de la maleta:")
            var largo : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el alto de la maleta:")
            var alto : Try[Int] = Try(StdIn.readInt())
            println("Porfavor ingrese el peso de la maleta:")
            var peso : Try[Int] = Try(StdIn.readInt())
            var pesoR : Int = 1000000 //Inicia en este valor por si en caso de falla saber el valor a pagar
            var maletaDim : Array[Int] = Array(100, 100, 100) //En caso de fallat todo inicia en 100 para que me devuelva una falla

            def action(p : Int, q : Int, r : Int, s : Int){
                //Me pone todos los valores en el array
                maletaDim(0) = p
                maletaDim(1) = q
                maletaDim(2) = r
                pesoR = s
            }

            (ancho, largo, alto, peso) match{
                case (Success(p), Success(q), Success(r), Success(s)) => action(p, q, r, s)
                case (_, _, _, _) => pesoR = 100
            }

            var maleta : Maleta = new Maleta(maletaDim, pesoR)
            if(checkIn.verificarTamMaleta(maleta)){ //Me verifica las dimensiones de la maleta
                if(checkIn.verificarPeso(maleta)){  //Me verifica el peso
                    checkIn.asociarMaleta(pasajero, maleta) //Si todo esta bien le asigna la maleta al pasajero
                }

                else{
                    var cantidad : Int = checkIn.cobrarExceso(pesoR) //Me Dice la cantidad que hay que pagar por el exceso de peso
                    println("La maleta tiene exceso de peso\nSi quiere llevar ese peso debe pagar " + cantidad + " Pesos")
                    var monto : Try[Int] = Try(StdIn.readInt()) 
                    var pesos = 0
                    var flag : Boolean = false
                    monto match{
                        case Success(s) => {
                                        flag = true
                                        pesos = s
                                    }
                        case Failure(f) => println("Hubo un error en la operación")
                    }

                    if(flag && pesos >= cantidad){ //En caso de que todo cumpla con los parametors se le asgina la maleta
                        checkIn.asociarMaleta(pasajero, maleta)
                        //println(pasajero.maleta.peso) verifico que el pasajero tenga la maleta a traves del peso
                    }

                    else{
                        println("Hubo un problema con el pago")
                    }
                }
            }

            else{
                println("Dimensiones no aceptadas")
            }
        }

        def servicios(pasajero : Pasajero){
            println("===============================")
            println("Bienvenido A los servicios")
            println("===============================")
            println("Que desea hacer: ")
            println("[1] Silla de rueda")
            println("[2] Mascota")
            println("[3] Mascota por cambina")
            println("===============================")
            var opcion : Try[Int] = Try(StdIn.readInt())
            var num : Int = 0
            opcion match{
                case Success(s) => num = s
                case Failure(f) => println("Error a la hora de escoger")
            }

            num match{
                case 1 => silla(pasajero)
                case 2 => mascotaNormal(pasajero)
                case 3 => mascotaCabina(pasajero)
                case _ => println("Error al intentar escoger un servicio")
            }


            def silla(pasajero : Pasajero){ 
                //Me crea el los servicios de siila
                var servicio : ServicioSilla = new ServicioSilla
                var ver : Boolean = checkIn.asociarServico(pasajero, servicio, 0)
                println("Servicio agregado con exito")
                pasajero.servicio = servicio
                //println(pasajero.servicio.descripcion)+
                
            }

            def mascotaNormal(pasajero : Pasajero){
                var servicio : ServicioMascota = new ServicioMascota
                println("El precio por este servicio es de " + checkIn.cobro)
                var num : Try[Int] = Try(StdIn.readInt())
                var ver : Boolean = false
                num match{//trata de devolver el servicio
                    case Success(s) => ver = checkIn.asociarServico(pasajero, servicio, s) //Si el servicio se hizo la funcion me devuleve true
                    case Failure(f) => ver = checkIn.asociarServico(pasajero, servicio, 0) //Lo mas probable es que me devuelva false, a menos que el costo de servicio sea de 0
                }
                if(ver){
                    println("servicio agregado")
                    pasajero.servicio = checkIn.pasajero.servicio
                }

                else{
                    println("Servicio denegado")
                }
                /*
                var trate : Try[ServicioEspecial] = Try(pasajero.servicio) De esta manera me doy cuenta si el pasasjero tiene algun servicio
                println(trate)
                */
            }

            def mascotaCabina(pasajero : Pasajero){
                //Me crea un servicio de mascota que van en cabinaa
                var servicio : ServicioMascotaCabina = new ServicioMascotaCabina
                println("El precio por este servicio es de " + checkIn.cobro)
                var num : Try[Int] = Try(StdIn.readInt())
                var ver : Boolean = false
                num match{
                    case Success(s) => ver = checkIn.asociarServico(pasajero, servicio, s) //Si el servicio se hizo la funcion me devuleve true
                    case Failure(f) => ver = checkIn.asociarServico(pasajero, servicio, 0) //Lo mas probable es que me devuelva false, a menos que el costo de servicio sea de 0
                }
                if(ver){
                    println("servicio agregado")
                    pasajero.servicio = checkIn.pasajero.servicio
                }

                else{
                    println("Servicio denegado")
                }
            }
        }
    }

    def getPasajero() : Pasajero = return pasajero

    def update() : Unit = {
    }
}
