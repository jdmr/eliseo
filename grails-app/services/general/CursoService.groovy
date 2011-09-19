package general

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class CursoService {

    def springSecurityService

    def lista(params) {
        def usuario = springSecurityService.currentUser
        log.debug "Buscando cursos por escuela ${usuario?.escuela}"
        def cursos = Curso.findAllByEscuela(usuario.escuela, params)
        def cantidad = Curso.countByEscuela(usuario.escuela)
        return [cursos:cursos, cantidad:cantidad]
    }

    def crea(curso) {
        log.debug "Creando el curso $curso"
        def usuario = springSecurityService.currentUser
        curso.escuela = usuario.escuela
        if (!curso.propietario) {
            curso.propietario = usuario
        }
        curso.save()
        return curso
    }

}
