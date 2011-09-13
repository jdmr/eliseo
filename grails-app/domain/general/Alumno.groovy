package general

class Alumno implements Serializable {

    Usuario usuario
    BigDecimal evaluacion = new BigDecimal('0')
    Integer cantidadEvaluaciones = 0

    static constraints = {
        evaluacion(scale:2, precision:8)
    }

    static mapping = {
        table 'alumnos'
    }

}
