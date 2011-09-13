package general

class Usuario implements Serializable {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
    String nombre
    String apellido
    String telefono
    String estado
    String pais
    Date dateCreated
    Date lastUpdated

	static constraints = {
		username blank: false, unique: true
		password blank: false
        nombre   blank: false, maxSize:128
        apellido blank: false, maxSize:128
        telefono nullable: true, maxSize:32
        estado   nullable: true, maxSize:64
        pais     nullable: true, maxSize:64
	}

	static mapping = {
		password column: '`password`'
        table 'usuarios'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
