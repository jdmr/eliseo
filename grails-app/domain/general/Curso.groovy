package general

class Curso implements Serializable {
    String codigo
    String nombre
    String descripcion
    BigDecimal evaluacion = new BigDecimal('0')
    Integer cantidadEvaluaciones = 0
    BigDecimal calificacion = new BigDecimal('0')
    Integer cantidadCalificaciones = 0
    Integer cantidadSalones = 0
    Escuela escuela
    Usuario propietario
    Set<Salon> salones
    Set<Etiqueta> etiquetas
    String tipo
    Date inicia
    Date termina
    String url
    String telefono
    String estado
    String pais
    String verCurso

    static transients = ['tipo','inicia','termina','url','telefono','estado','pais','verCurso']

    static belongsTo = [escuela:Escuela, propietario:Usuario]

    static hasMany = [salones:Salon, etiquetas:Etiqueta]

    static constraints = {
        codigo(blank:false,maxSize:32,unique:'escuela')
        nombre(blank:false,maxSize:128,unique:'escuela')
        descripcion(blank:false,maxSize:4000)
        evaluacion(scale:2,precision:8)
        calificacion(scale:2,precision:8)
    }

    static mapping = {
        table 'cursos'
    }
}
