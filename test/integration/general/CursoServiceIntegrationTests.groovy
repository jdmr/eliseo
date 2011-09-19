package general

import static org.junit.Assert.*
import org.junit.*

class CursoServiceIntegrationTests extends BaseIntegrationTest {

    def cursoService

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void debieraObtenerListaDeCursos() {
        def escuela = new Escuela (
            codigo: 'TEST-1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assert escuela

        def escuela2 = new Escuela (
            codigo: 'TEST-2'
            , nombre: 'TEST-2'
            , nombreCompleto: 'TEST-2'
        ).save()
        assert escuela2

        authenticateAdmin(escuela)
        def usuario = Usuario.findByUsername('david.mendoza@um.edu.mx')

        for (i in 1..20) {
            new Curso (
                codigo: "TEST-$i"
                , nombre : "TEST-$i"
                , descripcion : "TEST-$i"
                , escuela : escuela
                , propietario : usuario 
            ).save()
        }

        usuario.escuela = escuela2
        usuario.save()

        for (i in 1..20) {
            new Curso (
                codigo: "TEST-$i"
                , nombre : "TEST-$i"
                , descripcion : "TEST-$i"
                , escuela : escuela2
                , propietario : usuario 
            ).save()
        }

        def params = [:]
        params.max = 10
        def lista = cursoService.lista(params)

        assert lista
        assert 20 == lista.cantidad
        assert 10 == lista.cursos?.size()
        logout()
    }

    @Test
    void debieraCrearCurso() {
        authenticateAdmin()
        def curso = new Curso (
            codigo: 'TEST-1'
            , nombre : 'TEST-1'
            , descripcion : 'TEST-1'
        )

        curso = cursoService.crea(curso)

        assert curso
        logout()
    }

    @Test
    void debieraObtenerCurso() {
        authenticateAdmin()

        def curso = new Curso (
            codigo: 'TEST-1'
            , nombre : 'TEST-1'
            , descripcion : 'TEST-1'
        )

        curso = cursoService.crea(curso)

        assert curso

        def x = cursoService.obtiene(curso.id)
        assert x
        assert 'TEST-1' == x.codigo
        logout()
    }
}
