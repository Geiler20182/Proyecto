package elementos

class Pasaje (ini : String, fin : String, vuel : String){
    //Atributos
    var _vuelo : String = vuel
    var _ciudadIni : String = ini
    var _ciudadFin : String = fin
    var _silla : String = _

    //Metodos

    //Getters
    def vuelo = _vuelo
    def ciudadIni = _ciudadIni
    def ciudadFin = _ciudadFin
    def silla = _silla

    //Setters
    def vuelo_= (vuel : String) = _vuelo = vuel
    def ciudadIni_= (ini : String) = _ciudadIni = ini
    def ciudadFin_= (fin : String) = _ciudadFin = fin
    def silla_= (asiento : String) = _silla = asiento 
}
