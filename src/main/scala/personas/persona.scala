package personas

abstract class Persona{
    //Atributos
    var _nombre : String
    var _id : String
    var _edad : Int

    //Metodos

    //Getters
    def nombre = _nombre
    def id = _id
    def edad = _edad

    //Setters
    def nombre_= (name : String) = _nombre = name
    def id_= (id : String) = _id = id
    def edad_= (edad : Int) = _edad = edad
}
