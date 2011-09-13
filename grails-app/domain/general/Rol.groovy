package general

class Rol implements Serializable {

	String authority

	static mapping = {
		cache true
        table 'roles'
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
