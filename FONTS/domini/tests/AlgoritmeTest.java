package domini.tests;

import domini.model.Algoritme;
import domini.model.CorreccioLinea;
import domini.model.FiveGuess;
import domini.model.Patro;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlgoritmeTest {

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
        Algoritme m = new Algoritme(rol);
        assertEquals(rol, m.getRol());
    }

    /**
     * Aquest test comprova que es crei una solució sense valors repetits.
     * <p>
     * Casos de prova:
     * - Es crea una solució amb els colorsRepetits desactivats.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testSetSolucioNoRepetits(){
        Algoritme m = new Algoritme(1);
        Patro p2 = m.setSolucio();
        int[] a = p2.getPatro();
        for (int j : a) System.out.print(j);
        System.out.print("");
        assertTrue(noRepetits(a));
    }

    /**
     * Aquest test comprova que es crei una solució amb valors repetits.
     * <p>
     * Casos de prova:
     * - Es crea una solució amb els colorsRepetits activats.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testSetSolucioRepetits(){
        Algoritme m = new Algoritme(1);
        Patro p2 = m.setSolucio();
        int[] a = p2.getPatro();
        for (int j : a) System.out.print(j);
        System.out.print("");
        assertNotNull(a);
    }

    /**
     * Aquest mètode serveix per a comprovar que el vector d'enters no té valors repetits.
     *
     * @param a és un vector d'enters.
     * @return retorna un true en cas que no hi hagi valors repetits, un false en cas contrari.
     */
    private boolean noRepetits(int[] a){
        int[] t = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
        for (int j : a) t[j] = t[j] + 1;
        for (int j : t) if (j > 1) return false;
        return true;
    }

    /**
     * Aquest test comprova que es comparin bé els intents.
     * <p>
     * Casos de prova:
     * - Un intent i una solució amb els colors correctes però en posicions correctes.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testComparaIntents_solucio() throws Exception {
        FiveGuess f = new FiveGuess(4, 8, false);
        int[] p = new int[] {1, 7, 0, 6};
        Patro solucio = new Patro(p);
        int[] i = new int[] {1, 7, 0, 6};
        Patro intent = new Patro(i);

        CorreccioLinea correccio = f.comparaIntents(solucio, intent);
        int negres = correccio.getNegres();
        int blanques = correccio.getBlanques();

        System.out.print("Boles negres: "+ negres + "\n");
        System.out.print("Boles blanques: "+ blanques + "\n");

        assertEquals(4, negres);
        assertEquals(0, blanques);
    }

    /**
     * Aquest test comprova que es comparin bé els intents.
     * <p>
     * Casos de prova:
     * - Un intent i una solució amb els colors incorrectes.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testComparaIntents_intentIncorrecte() throws Exception {
        FiveGuess f = new FiveGuess(6, 8, false);
        int[] p = new int[] {1, 7, 0, 6};
        Patro solucio = new Patro(p);
        int[] i = new int[] {2, 5, 3, 4};
        Patro intent = new Patro(i);

        CorreccioLinea correccio = f.comparaIntents(solucio, intent);
        int negres = correccio.getNegres();
        int blanques = correccio.getBlanques();

        System.out.print("Boles negres: "+ negres + "\n");
        System.out.print("Boles blanques: "+ blanques + "\n");

        assertEquals(0, negres);
        assertEquals(0, blanques);
    }

    /**
     * Aquest test comprova que es comparin bé els intents.
     * <p>
     * Casos de prova:
     * - Un intent i una solució amb els colors correctes però en posicions incorrectes.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testComparaIntents_intentDesordenat() throws Exception {
        FiveGuess f = new FiveGuess(8, 8, false);
        int[] p = new int[] {1, 7, 0, 6, 5, 4, 3, 2};
        Patro solucio = new Patro(p);
        int[] i = new int[] {0, 6, 1, 7, 2, 3, 4, 5};
        Patro intent = new Patro(i);

        CorreccioLinea correccio = f.comparaIntents(solucio, intent);
        int negres = correccio.getNegres();
        int blanques = correccio.getBlanques();

        System.out.print("Boles negres: " + negres + "\n");
        System.out.print("Boles blanques: " + blanques + "\n");

        assertEquals(0, negres);
        assertEquals(8, blanques);
    }

    /**
     * Aquest test comprova que es comparin bé els intents.
     * <p>
     * Casos de prova:
     * - Un intent i una solució amb els colors correctes però amb dos colors en posicions correctes i 2 en posicions incorrectes.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testComparaIntents_parcialmentCorrecte() throws Exception {
        FiveGuess f = new FiveGuess(6, 8, false);
        int[] p = new int[] {1, 7, 0, 6, 5, 4};
        Patro solucio = new Patro(p);
        int[] i = new int[] {1, 6, 0, 7, 2, 3};
        Patro intent = new Patro(i);

        CorreccioLinea correccio = f.comparaIntents(solucio, intent);
        int negres = correccio.getNegres();
        int blanques = correccio.getBlanques();

        System.out.print("Boles negres: " + negres + "\n");
        System.out.print("Boles blanques: " + blanques + "\n");

        assertEquals(2, negres);
        assertEquals(2, blanques);
    }
}
