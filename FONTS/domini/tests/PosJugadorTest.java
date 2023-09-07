package domini.tests;

import domini.model.PosJugador;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class PosJugadorTest {

    private PosJugador posJugador;

    /**
     * Aquest test comprova que es tradueix una posició del ranking classic a un string.
     * <p>
     * Casos de prova:
     * - Traduir una PosJugador del mode classic a string.
     * <p>
     * Resultat esperat:
     * - Un string igual que el PosJugador.
     */
    @Test
    public void testObtePosicionsRankingClassic(){
        int[] temps = new int[] {1,1,1};
        posJugador = new PosJugador(1, "Joel", temps);

        String resultat = posJugador.obtePosicionsClassic();

        String solucio = "Player: Joel, Intents: 1, Temps: 1:1:1";

        assertEquals(resultat, solucio);

    }

    /**
     * Aquest test comprova que es tradueix una posició del ranking crono a un string.
     * <p>
     * Casos de prova:
     * - Traduir una PosJugador del mode crono a string.
     * <p>
     * Resultat esperat:
     * - Un string igual que el PosJugador.
     */
    @Test
    public void testObtePosicionsRankingCrono(){
        posJugador = new PosJugador(10, "Joel", 10);

        String resultat = posJugador.obtePosicionsCrono();

        String solucio = "Player: Joel, Victories: 10, Intents totals: 10";

        assertEquals(resultat, solucio);

    }


}
