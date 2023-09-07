package domini.tests;

import domini.stubs.StubJugador;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JugadorTest {

    /**
     * Aquest test comprova que la constructora funcioni.
     * <p>
     * Casos de prova:
     * - Un nou jugador com a codebreaker.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testConstructor(){
        int rol = 1;
        StubJugador j = new StubJugador(rol);
        assertEquals(rol, j.getRol());
    }
}
