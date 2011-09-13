package general

class Salon implements Serializable {

    String nombre
    String tipo
    Date inicia
    Date termina
    String url
    BigDecimal evaluacion = new BigDecimal('0')
    Integer cantidadEvaluaciones = 0
    Maestro maestro

    static belongsTo = [Maestro]

    static constraints = {
        nombre(blank:false, maxSize:128)
        tipo(maxSize:32, inList:['PAGADO','PATROCINADO'])
        url(maxSize:254, nullable:true)
    }

    static mapping = {
        table 'salones'
    }
}
