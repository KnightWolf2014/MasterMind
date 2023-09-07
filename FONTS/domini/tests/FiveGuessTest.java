package domini.tests;

import domini.exceptions.PosicioPatroNoDisponible;
import domini.model.CorreccioLinea;
import domini.model.FiveGuess;
import domini.model.Patro;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class FiveGuessTest {
    //TROBAR COMBINACIONS

    /**
     * Test sobre la funció trobarCombinacions sense repeticions amb 4 boles.
     * <p>
     * Casos de prova:
     * - Es generen totes les combinacions possibles amb quatre boles.
     * <p>
     * Resultats esperats:
     * - que es trobin totes les combinacions possibles.
     */
    @Test
    public void testTrobarCombinacions_quatreBoles() {
        FiveGuess f = new FiveGuess(4, 8, true);

        int pos = 0;
        int[] p = new int[4];
        Patro comb = new Patro(p);

        f.trobarCombinacions(pos, comb);
    }

    //TROBAR COMBINACIONS SENSE REPETICIONS

    /**
     * Test sobre la funció trobarCombinacionsSenseRepeticions amb 4 boles.
     * <p>
     * Casos de prova:
     * - Es generen totes les combinacions possibles amb 4 boles, sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que es trobin totes les combinacions possibles sense repeticions.
     */
    @Test
    public void testTrobarCombinacionsSenseRepeticions_quatreBoles() {
        FiveGuess f = new FiveGuess(4, 8, false);

        int[] p = new int[4];
        Patro comb = new Patro(p);
        boolean[] usat =  new boolean[8];

        f.trobarCombinacionsSenseRepeticions(0, comb, usat);
    }

    /**
     * Test sobre la funció trobarCombinacionsSenseRepeticions amb 6 boles.
     * <p>
     * Casos de prova:
     * - Es generen totes les combinacions possibles amb 6 boles, sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que es trobin totes les combinacions possibles sense repeticions.
     */
    @Test
    public void testTrobarCombinacionsSenseRepeticions_sisBoles() {
        FiveGuess f = new FiveGuess(6, 8, false);

        int[] p = new int[6];
        Patro comb = new Patro(p);
        boolean[] usat =  new boolean[8];

        f.trobarCombinacionsSenseRepeticions(0, comb, usat);
    }

    //CREAR EL PRIMER INTENT

    /**
     * Test sobre la funció primerIntent amb 4 boles.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 4 boles i colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 0, 1, 1}
     */
    @Test
    public void testPrimerIntent_quatreBolesRepetits() {
        FiveGuess f = new FiveGuess(4, 8, true);
        new Patro(4, true);
        Patro intent;
        intent = f.primerIntent();
        int [] vec = intent.getPatro();

        System.out.print("Combinació inicial: "+ Arrays.toString(vec) + "\n");

        int[] x = new int[] {0, 0, 1, 1};
        assertArrayEquals(x, vec);
    }

    /**
     * Test sobre la funció primerIntent amb 4 boles sense repeticions.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 4 boles i sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 1, 2, 3}
     */
    @Test
    public void testPrimerIntent_quatreBoles() {
        FiveGuess f = new FiveGuess(4, 8, false);
        new Patro(4, false);
        Patro intent = f.primerIntent();

        int [] vec = intent.getPatro();

        System.out.print("Combinació inicial: "+ Arrays.toString(vec) + "\n");

        int[] x = new int[] {0, 1, 2, 3};
        assertArrayEquals(x, vec);
    }

    /**
     * Test sobre la funció primerIntent amb 6 boles sense repeticions.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 6 boles i sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 1, 2, 3, 4, 5}
     */
    @Test
    public void testPrimerIntent_sisBoles() {
        FiveGuess f = new FiveGuess(6, 8, false);
        Patro intent = f.primerIntent();

        int [] vec = intent.getPatro();

        System.out.print("Combinació inicial: "+ Arrays.toString(vec) + "\n");

        int[] x = new int[] {0, 1, 2, 3, 4, 5};
        assertArrayEquals(x, vec);
    }


    //ACTUALITZA LES COMBINACIONS

    /**
     * Test sobre la funció actualitzaCombinacions amb 4 boles.
     * <p>
     * Casos de prova:
     * - S'actualitzen totes les combinacions possibles amb 4 boles.
     * <p>
     * Resultats esperats:
     * - que s'actualitzi la llista de combinacions possibles.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testActualitzaCombinacions_4Boles() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(4, 8, false);

        f.actualitzaCombinacions();
    }

    /**
     * Test sobre la funció actualitzaCombinacions amb 6 boles.
     * <p>
     * Casos de prova:
     * - S'actualitzen totes les combinacions possibles amb 6 boles.
     * <p>
     * Resultats esperats:
     * - que s'actualitzi la llista de combinacions possibles.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testActualitzaCombinacions_6Boles() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(6, 8, false);

        f.actualitzaCombinacions();
    }


    //CÀLCUL MAXIM

    /**
     * Test sobre la funció maxim.
     * <p>
     * Casos de prova:
     * - calcula el màxim d'encerts que tindrà un intent específic {1, 2, 0, 4}. Aquest màxim com es calcula amb totes les combinacions
     * possibles generades sempre hauria de donar 504.
     * <p>
     * Resultats esperats:
     * - que el màxim sigui igual a 504.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testMaxim() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(4, 8, true);
        boolean[] usat = new boolean[8];
        f.trobarCombinacionsSenseRepeticions(0, f.primerIntent(), usat);

        int[] p = new int[] {1, 2, 0, 4};
        Patro intent = new Patro(p);

        int max = f.maxim(intent);

        assertEquals(504, max);
    }


    //GET INTENT

    /**
     * Test sobre la funció getIntent amb 4 boles i colors repetits, agafant la correcció de la funció comparaIntents.
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 4 boles amb colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el cinquè intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_4BolesColorsRepetits() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(4, 8, true);
        int [] s = {7, 1, 6, 6};
        Patro sol = new Patro(4, true);
        sol.setPatro(s);
        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = f.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 0, 1, 1};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = f.comparaIntents(sol, intent);
        f.setCorreccio(cor);

        Patro segonIntent = f.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = f.comparaIntents(sol, segonIntent);
        f.setCorreccio(cor2);

        Patro intent3 = f.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = f.comparaIntents(sol, intent3);
        f.setCorreccio(cor3);

        Patro intent4 = f.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = f.comparaIntents(sol, intent4);
        f.setCorreccio(cor4);

        Patro intent5 = f.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        assertArrayEquals(s, intent5.getPatro());
    }

    /**
     * Test sobre la funció getIntent amb 4 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 4 boles sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el cinquè intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_4Boles() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(4, 8, false);
        int [] s = {7, 3, 5, 4};
        Patro sol = new Patro(4, false);
        sol.setPatro(s);
        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = f.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 1, 2, 3};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = f.comparaIntents(sol, intent);
        f.setCorreccio(cor);

        Patro segonIntent = f.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = f.comparaIntents(sol, segonIntent);
        f.setCorreccio(cor2);

        Patro intent3 = f.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = f.comparaIntents(sol, intent3);
        f.setCorreccio(cor3);

        Patro intent4 = f.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = f.comparaIntents(sol, intent4);
        f.setCorreccio(cor4);

        Patro intent5 = f.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        assertArrayEquals(s, intent5.getPatro());
    }

    /**
     * Test sobre la funció getIntent amb 6 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 6 boles sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el dècim intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_6boles() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(6, 8, false);
        int [] s = {7, 4, 1, 0, 5, 6};
        Patro sol = new Patro(6, false);
        sol.setPatro(s);
        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = f.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 1, 2, 3, 4, 5};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = f.comparaIntents(sol, intent);
        f.setCorreccio(cor);

        Patro segonIntent = f.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = f.comparaIntents(sol, segonIntent);
        f.setCorreccio(cor2);

        Patro intent3 = f.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = f.comparaIntents(sol, intent3);
        f.setCorreccio(cor3);

        Patro intent4 = f.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = f.comparaIntents(sol, intent4);
        f.setCorreccio(cor4);

        Patro intent5 = f.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        CorreccioLinea cor5 = f.comparaIntents(sol, intent5);
        f.setCorreccio(cor5);

        Patro intent6 = f.getIntent();
        System.out.print("6e intent: "+ Arrays.toString(intent6.getPatro()) + "\n");

        CorreccioLinea cor6 = f.comparaIntents(sol, intent6);
        f.setCorreccio(cor6);

        Patro intent7 = f.getIntent();
        System.out.print("7e intent: "+ Arrays.toString(intent7.getPatro()) + "\n");

        CorreccioLinea cor7 = f.comparaIntents(sol, intent7);
        f.setCorreccio(cor7);

        Patro intent8 = f.getIntent();
        System.out.print("8e intent: "+ Arrays.toString(intent8.getPatro()) + "\n");

        CorreccioLinea cor8 = f.comparaIntents(sol, intent8);
        f.setCorreccio(cor8);

        Patro intent9 = f.getIntent();
        System.out.print("9e intent: "+ Arrays.toString(intent9.getPatro()) + "\n");

        CorreccioLinea cor9 = f.comparaIntents(sol, intent9);
        f.setCorreccio(cor9);

        Patro intent10 = f.getIntent();
        System.out.print("10e intent: "+ Arrays.toString(intent10.getPatro()) + "\n");

        assertArrayEquals(s, intent10.getPatro());
    }


    /**
     * Test sobre la funció getIntent amb 4 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 4 boles amb l'opció colors repetits, però no repetint-los.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el cinquè intent no sigui igual a la solució inicial. Però en el sisè sí.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_4BolesMes5Intents() throws PosicioPatroNoDisponible {
        FiveGuess f = new FiveGuess(4, 8, true);
        int [] s = {4, 7, 2, 3};
        Patro sol = new Patro(4, true);
        sol.setPatro(s);
        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = f.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 0, 1, 1};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = f.comparaIntents(sol, intent);
        f.setCorreccio(cor);

        Patro segonIntent = f.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = f.comparaIntents(sol, segonIntent);
        f.setCorreccio(cor2);

        Patro intent3 = f.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = f.comparaIntents(sol, intent3);
        f.setCorreccio(cor3);

        Patro intent4 = f.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = f.comparaIntents(sol, intent4);
        f.setCorreccio(cor4);

        Patro intent5 = f.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        assertNotEquals(s, intent5.getPatro());
        System.out.print("No s'ha pogut adivinar en 5 intents \n");

        CorreccioLinea cor5 = f.comparaIntents(sol, intent5);
        f.setCorreccio(cor5);

        Patro intent6 = f.getIntent();
        System.out.print("6e intent: "+ Arrays.toString(intent6.getPatro()) + "\n");
        assertArrayEquals(s, intent6.getPatro());
    }
}