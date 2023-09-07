package domini.tests;

import domini.exceptions.NoSolucioDisponible;
import domini.exceptions.PosicioPatroNoDisponible;
import domini.model.Classic;
import domini.model.Patro;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassicTest {

    /**
     * Aquest mètode serveix per evitar codi repetit, indica si una partida ha acabat o no.
     * @param c és una partida en mode classic que representa la partida actual.
     * @param intentV és un vector d'enters que representa l'intent de l'Usuari.
     * @param solucioV és un vector d'enters que representa la solució de la Partida.
     * @param nTorn és un enter que representa els torns que volem que passin.
     * @return és un booleà que indica si la Partida ha acabat o no.
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució.
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi.
     */
    private boolean partidaAcabadaFuncio(Classic c, int[] intentV, int[] solucioV, int nTorn) throws Exception{
        Patro intent = new Patro(intentV);
        Patro solucio = new Patro(solucioV);

        c.passarSolucio(solucio);

        for(int i = 0; i <= nTorn; ++i){
            c.checkIntent(intent);
            c.passarIntent(intent);
        }

        return c.partidaAcabada();
    }

    /**
     *Aquest test serveix per comprovar que la funció sap funcionar en el cas que la Partida hagi sigut acabada i guanyada.
     * <p>
     * Casos de prova:
     * - Crea una solució.
     * - Crea un intent idèntic a la solució.
     * - Crea un enter que indiqui que no volem que passin torns.
     * <p>
     * Resultat esperat:
     * - Retorni que la Partida hagi acabat.
     * - Retorni que la Partida ha sigut guanyada.
     */
    @Test
    public void testPartidaAcabadaGuanyada() throws Exception{
        Classic c = new Classic(5, false, false);
        //Per poder testejar la funció necessitem jugar una funció

        int[] intentV = new int[] {1, 2, 3, 4, 5};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};

        assertTrue(partidaAcabadaFuncio(c, intentV, solucioV, 0));
        assertTrue(c.getGuanyat());
    }

    /**
     *Aquest test serveix per comprovar que la funció sap funcionar en el cas que la Partida hagi sigut acabada però no guanyada.
     * <p>
     * Casos de prova:
     * - Crea una solució.
     * - Crea un intent diferent de la solució.
     * - Crea un enter i el passa per fer entendre que volem que passin 8 torns.
     * <p>
     * Resultat esperat:
     * - Retorni que la Partida hagi acabat.
     * - Retorni que la Partida no ha sigut guanyada.
     */
    @Test
    public void testPartidaAcabadaPerduda() throws Exception{
        Classic c = new Classic(5, false, false);
        //Per poder testejar la funció necessitem jugar una funció

        int[] intentV = new int[] {7, 2, 3, 4, 5};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};

        assertTrue(partidaAcabadaFuncio(c, intentV, solucioV, 9));
        assertFalse(c.getGuanyat());
    }

    /**
     * Aquest test serveix per comprovar que la funció sap funcionar en el cas que la Partida no hagi sigut acabada.
     * <p>
     * Casos de prova:
     * - Crea una solució.
     * - Crea un intent diferent de la solució.
     * - Crea un enter que indiqui que no volem que passi cap torn.
     * <p>
     * Resultat esperat:
     * - Retorni que la Partida no hagi acabat.
     */
    @Test
    public void testPartidaNoAcabada() throws Exception{
        Classic c = new Classic(5, false, false);
        //Per poder testejar la funció necessitem jugar una funció

        int[] intentV = new int[] {7, 2, 3, 4, 5};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};

        assertFalse(partidaAcabadaFuncio(c, intentV, solucioV, 0));
    }
}
