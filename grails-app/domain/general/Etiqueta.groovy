package general

class Etiqueta implements Serializable {

    String nombre
    Escuela escuela

    static belongsTo = [Escuela]

    static constraints = {
        nombre(blank:false, maxSize:64,unique:'escuela')
    }

    static mapping = {
        table 'etiquetas'
    }
}
