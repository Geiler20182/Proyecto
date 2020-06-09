package personas

class Tripulacion(n_pilotos : List[Piloto], n_azafatas : List[Azafata]) {
  
    var _pilotos : List[Piloto] = n_pilotos
    var _azafatas : List[Azafata] = n_azafatas


    def azafatas = _azafatas
    def pilotos =  _pilotos
}