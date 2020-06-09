package personas

class Piloto(name : String, id : String, edad : Int) extends Persona{
    //Atributos
    override var _nombre : String = name
    override var _id : String = id
    override var _edad : Int = edad
}
