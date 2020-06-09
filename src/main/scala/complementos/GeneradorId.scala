package complementos

import scala.util.Random

object GeneradorId {
    
    def crearId() : String = {
       
        var codigo : String = Random.alphanumeric.take(6).mkString("")
        return codigo
    }
}