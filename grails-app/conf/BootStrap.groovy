class BootStrap {

    def init = { servletContext ->
        log.info "Inicializando aplicacion"

        log.info "Validando escuelas"
        def escuela
        if (general.Escuela.count() == 0) {
            escuela = new general.Escuela (
                codigo : 'UM'
                , nombre : 'UM'
                , nombreCompleto: 'Universidad de Montemorelos, A.C.'
            ).save()
        }

        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (general.Rol.count() != 3) {
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            def rolMaestro = general.Rol.findByAuthority('ROLE_MAESTRO')
            if (!rolMaestro) {
                rolMaestro = new general.Rol(authority: 'ROLE_MAESTRO').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        }

        log.info "Validando usuarios"
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            if (!escuela) {
                def p = [:]
                p.max = 1
                def escuelas = general.Escuela.list(p)
                escuela = escuelas[0]
            }
            admin = new general.Usuario (
                username: 'david.mendoza@um.edu.mx'
                , password : 'admin'
                , nombre: 'David'
                , apellido: 'Mendoza'
                , escuela: escuela
            )
            admin.save(flush:true)
            log.debug("Admin: $admin")
            log.debug("Rol:   $rolAdmin")
            //general.UsuarioRol.create(admin, rolAdmin, true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }

        log.info "Aplicacion inicializada"
    }
    def destroy = {
    }
}
