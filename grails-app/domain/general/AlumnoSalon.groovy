package general

import org.apache.commons.lang.builder.HashCodeBuilder

class AlumnoSalon implements Serializable {

    Alumno alumno
    Salon salon
    Date dateCreated
    Date lastUpdated
    BigDecimal evaluacion = new BigDecimal('0')
    Integer cantidadEvaluaciones = 0

    boolean equals(other) {
        if (!(other instanceof AlumnoSalon)) {
            return false
        }
        other.alumno?.id == alumno?.id &&
        other.salon.id == salon?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (alumno) builder.append(alumno.id)
        if (rol) builder.append(salon.id)
        builder.toHashCode()
    }

    static constraints = {
        evaluacion(scale:2, precision:8)
    }

    static mapping = {
        id composite: ['salon', 'alumno']
        version false
        table 'alumno_salon'
    }

    static AlumnoSalon get(long alumnoId, long salonId) {
        find 'from AlumnoSalon where alumno.id = :alumnoId and salon.id = :salonId', [alumnoId: alumnoId, salonId: salonId]
    }

    static AlumnoSalon create(Alumno alumno, Salon salon, boolean flush = false) {
        new AlumnoSalon(alumno: alumno, salon: salon).save(flush: flush, insert: true)
    }

    static boolean remove(Alumno alumno, Salon salon, boolean flush = false) {
        AlumnoSalon instance = AlumnoSalon.findByAlumnoAndSalon(alumno, salon)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(Alumno alumno) {
        executeUpdate 'DELETE FROM AlumnoSalon WHERE alumno = :alumno', [alumno: alumno]
    }

    static void removeAll(Salon salon) {
        executeUpdate 'DELETE FROM AlumnoSalon WHERE salon = :salon', [salon: salon]
    }
}
