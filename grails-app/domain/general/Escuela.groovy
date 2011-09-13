package general

class Escuela implements Serializable {

    String codigo
    String nombre
    String nombreCompleto

    static constraints = {
        codigo(blank:false, maxSize: 32, unique:true)
        nombre(blank:false, maxSize:128, unique:true)
        nombreCompleto(blank:false, maxSize:254)
    }

    static mapping = {
        table 'escuelas'
    }
}
