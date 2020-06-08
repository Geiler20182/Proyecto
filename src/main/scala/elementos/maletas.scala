package elementos

import Array._

class Maleta(dim : Array[Int], pes : Int){
    //Atributos 
    var _dimension : Array[Int] = dim
    var _peso : Int = pes

    //Metodos

    //Getters
    def dimension = _dimension
    def peso = _peso

    //setters
    def dimension_= (dim : Array[Int]) = _dimension = dim
    def peso_= (peso : Int) = _peso = pes
}
