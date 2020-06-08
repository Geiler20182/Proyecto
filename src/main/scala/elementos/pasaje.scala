package elementos

class Pasaje(ini : String, fin : String){
    //Atributos
    var _ciudadIni : String = ini
    var _ciudadFin : String = fin
    var _silla : String = _

    //Metodos

    //Getters
    def ciudadIni = _ciudadIni
    def ciudadFin = _ciudadFin
    def silla = _silla

    //Setters
    def ciudadIni_= (ini : String) = _ciudadIni = ini
    def ciudadFin_= (fin : String) = _ciudadFin = fin
    def silla_= (asiento : String) = _silla = asiento 
}
