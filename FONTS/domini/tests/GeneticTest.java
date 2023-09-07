package domini.tests;

import domini.exceptions.PosicioPatroNoDisponible;
import domini.model.CorreccioLinea;
import domini.model.Genetic;
import domini.model.Patro;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneticTest {
    //CREAR EL PRIMER INTENT

    /**
     * Aquest test comprova la funció primerIntent amb 6 boles.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 6 boles i colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 0, 1, 1, 2, 3}
     */
    @Test
    public void testPrimerIntent_6BolesRepetits() {
        Genetic g = new Genetic(6, 8, true);
        Patro intent = g.primerIntent();

        int[] vec = intent.getPatro();

        System.out.print("Combinació inicial: " + Arrays.toString(vec) + "\n");

        int[] x = new int[]{0, 0, 1, 1, 2, 3};
        assertArrayEquals(x, vec);
    }

    /**
     * Aquest test comprova la funció primerIntent amb 8 boles amb repeticions.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 8 boles amb colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 0, 1, 1, 2, 2, 3, 4}
     */
    @Test
    public void testPrimerIntent_8BolesRepetits() {
        Genetic g = new Genetic(8, 8, true);
        Patro intent = g.primerIntent();

        int [] vec = intent.getPatro();

        System.out.print("Combinació inicial: "+ Arrays.toString(vec) + "\n");

        int[] x = new int[] {0, 0, 1, 1, 2, 2, 3, 4};
        assertArrayEquals(x, vec);
    }


    /**
     * Aquest test comprova la funció primerIntent amb 8 boles sense repeticions.
     * <p>
     * Casos de prova:
     * - Es genera el primer intent en el cas de 8 boles sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el vector generat sigui igual a {0, 1, 2, 3, 4, 5, 6, 7}
     */
    @Test
    public void testPrimerIntent_8Boles() {
        Genetic g = new Genetic(8, 8, false);
        Patro intent = g.primerIntent();

        int [] vec = intent.getPatro();

        System.out.print("Combinació inicial: "+ Arrays.toString(vec) + "\n");

        int[] x = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(x, vec);
    }


    //INICIAR POBLACIÓ

    /**
     * Test sobre la funció iniciarPoblacio.
     * <p>
     * Casos de prova:
     * - Es generen una nova població.
     * <p>
     * Resultats esperats:
     * - que es generi de forma correcta tota la població.
     */
    @Test
    public void testIniciarPoblacio() {
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();
    }


    //CALCULAR APTITUD

    /**
     * Aquest test comprova la funció calcularAptitud
     * <p>
     * Casos de prova:
     * - Es calculen les aptituds de la nova població
     * <p>
     * Resultats esperats:
     * - que es generi un arraylist amb totes les aptituds
     */
    @Test
    public void testCalcularAptitud() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();
        g.calcularAptitud();
    }


    //SWAP INTEGER

    /**
     * Aquest test comprova la funció swapInteger.
     * <p>
     * Casos de prova:
     * - canvia l'ordre de variables tipus integer d'un arrayList.
     * <p>
     * Resultats esperats:
     * - que la posició 0 tingui el valor 6 i la posició 2 de 5.
     */
    @Test
    public void testSwapInteger() {
        Genetic g = new Genetic(6, 8, true);
        ArrayList<Integer> apt = new ArrayList<>();
        apt.add(0, 5);
        apt.add(1, 8);
        apt.add(2, 6);
        g.swapInteger(apt, 0, 2);

        int i = apt.get(0);
        assertEquals(6, i);
        int j = apt.get(2);
        assertEquals(5, j);
    }


    //SWAP PATRÓ

    /**
     * Aquest test comprova la funció swapPatro.
     * <p>
     * Casos de prova:
     * - canvia l'ordre de variables tipus patró d'un arrayList.
     * <p>
     * Resultats esperats:
     * - que la posició 0 tingui el valor 6 i la posició 2 de 5.
     */
    @Test
    public void testSwapPatro() {
        Genetic g = new Genetic(6, 8, true);
        ArrayList<Patro> patrons = new ArrayList<>();
        int[] x = new int[] {7, 1, 3, 2, 0, 4};
        int[] y = new int[] {0, 1, 2, 6, 3, 7};
        int[] z = new int[] {6, 5, 2, 6, 7, 7};
        patrons.add(0, new Patro(x));
        patrons.add(1, new Patro(y));
        patrons.add(2, new Patro(z));

        g.swapPatro(patrons, 0, 2);

        int[] i = patrons.get(0).getPatro();
        assertArrayEquals(z, i);
        int[] j = patrons.get(2).getPatro();
        assertArrayEquals(x, j);
    }


    //CROSSOVER 1

    /**
     * Aquest test comprova la funció crossover1, que fa modificacions en dos patrons aleatoris.
     * <p>
     * Casos de prova:
     * - que faci bé els canvis entre les dues posicions de dos patrons de 6 boles de forma correcta.
     * <p>
     * Resultats esperats:
     * - un nou patró de longitud 6 modificat de forma aleatòria.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testCrossover1_sisBoles() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] k = {8, 8, 8, 8, 8, 8};
            novaPoblacio.add(i, new Patro(k));
        }
        g.crossover1(novaPoblacio, 0, 1);
        //no es pot verificar quins patrons s'agafen de la població, ja que es fa dins de la funció.
        System.out.print("Aquest és el nou patró: " + Arrays.toString(novaPoblacio.get(0).getPatro()) + "\n");
    }

    /**
     * Aquest test comprova la funció crossover1, que fa modificacions en dos patrons aleatoris.
     * <p>
     * Casos de prova:
     * - que faci bé els canvis entre les dues posicions de dos patrons de 8 boles de forma correcta.
     * <p>
     * Resultats esperats:
     * - un nou patró de longitud 8 modificat de forma aleatòria.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testCrossover1_vuitBoles() throws PosicioPatroNoDisponible{
        Genetic g = new Genetic(8, 8, false);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] k = {8, 8, 8, 8, 8, 8, 8, 8};
            novaPoblacio.add(i, new Patro(k));
        }

        g.crossover1(novaPoblacio, 0, 1);
        //no es pot verificar quins patrons s'agafen de la població, ja que es fa dins de la funció.
        System.out.print("Aquest és el nou patró: " + Arrays.toString(novaPoblacio.get(0).getPatro()) + "\n");
    }


    //CROSSOVER 2

    /**
     * Aquest test comprova la funció crossover2, que fa modificacions en dos patrons aleatoris.
     * <p>
     * Casos de prova:
     * - que faci bé els canvis entre les tres posicions de dos patrons de 6 boles de forma correcta.
     * <p>
     * Resultats esperats:
     * - un nou patró de longitud 6 modificat de forma aleatòria.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testCrossover2_sisBoles() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] k = {8, 8, 8, 8, 8, 8};
            novaPoblacio.add(i, new Patro(k));
        }
        g.crossover2(novaPoblacio, 0, 1);
        //no es pot verificar quins patrons s'agafen de la població, ja que es fa dins de la funció.
        System.out.print("Aquest és el nou patró: " + Arrays.toString(novaPoblacio.get(0).getPatro()) + "\n");
    }

    /**
     * Aquest test comprova la funció crossover2, que fa modificacions en dos patrons aleatoris.
     * <p>
     * Casos de prova:
     * - que faci bé els canvis entre les tres posicions de dos patrons de 8 boles de forma correcta.
     * <p>
     * Resultats esperats:
     * - un nou patró de longitud 8 modificat de forma aleatòria.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testCrossover2_vuitBoles() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(8, 8, false);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] k = {8, 8, 8, 8, 8, 8, 8, 8};
            novaPoblacio.add(i, new Patro(k));
        }

        g.crossover2(novaPoblacio, 0, 1);
        //no es pot verificar quins patrons s'agafen de la població, ja que es fa dins de la funció.
        System.out.print("Aquest és el nou patró: " + Arrays.toString(novaPoblacio.get(0).getPatro()) + "\n");
    }

    /**
     * Aquest test comprova la funció mutar, que modifica un patró.
     * <p>
     * Casos de prova:
     * - que faci bé la mutació d'una posició random del patró, per un color aleatori.
     * <p>
     * Resultats esperats:
     * - un nou patró amb el color d'una posició canviat.
     */
    @Test
    public void testMutar() {
        Genetic g = new Genetic(6, 8, true);
        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] k = {8, 8, 8, 8, 8, 8};
            novaPoblacio.add(i, new Patro(k));
        }

        System.out.print("Vector: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
        g.mutar(novaPoblacio, 58);
        System.out.print("Mutació: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
    }

    /**
     * Aquest test comprova la funció permutar, que modifica un patró.
     * <p>
     * Casos de prova:
     * - que faci bé la permutació entre dues posicions randoms del patró.
     * <p>
     * Resultats esperats:
     * - un nou patró amb els colors d'entre dues posicions permutats.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testPermutar() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] r = new int[6];
            Random rand = new Random();
            for (int j = 0; j < 6; ++j) r[j] = rand.nextInt(8);
            Patro intent = new Patro();
            intent.setPatro(r);
            novaPoblacio.add(i, intent);
        }

        System.out.print("Vector: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
        g.permutar(novaPoblacio, 58);
        System.out.print("Mutació: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
    }

    /**
     * Aquest test comprova la funció invertir, que modifica un patró
     * <p>
     * Casos de prova:
     * - que faci bé la inversió entre dues posicions randoms del patró.
     * <p>
     * Resultats esperats:
     * - un nou patró amb els colors d'entre dues posicions invertits.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testInvertir() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);

        ArrayList<Patro> novaPoblacio = new ArrayList<>(10000);

        for (int i = 0; i < 10000; ++i) {
            int[] r = new int[6];
            Random rand = new Random();
            for (int j = 0; j < 6; ++j) r[j] = rand.nextInt(8);
            Patro intent = new Patro();
            intent.setPatro(r);
            novaPoblacio.add(i, intent);
        }

        System.out.print("Vector: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
        g.invertir(novaPoblacio, 58);
        System.out.print("Mutació: " + Arrays.toString(novaPoblacio.get(58).getPatro()) + "\n");
    }

    //GET POSICIÓ PARE

    /**
     * Aquest test comprova la funció getPosicioPare
     * <p>
     * Casos de prova:
     * - que generi un nombre aleatori
     * <p>
     * Resultats esperats:
     * - un enter random.
     */
    @Test
    public void testGetPosicioPare() {
        Genetic g = new Genetic(8, 8, false);
        int i = g.getPosicioPares();
        System.out.print("Número generat de forma aleatòria " + i + "\n");
    }


    //ES REPETICIÓ

    /**
     * Aquest test comprova la funció esRepetició.
     * <p>
     * Casos de prova:
     * - que es repeteixi un patró en la població inicial i la nova.
     * <p>
     * Resultats esperats:
     * - false, ja que el patró {8, 8, 8, 8, 8, 8} no existeix.
     */
    @Test
    public void testEsRepeticio_false(){
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>();

        int[] x = new int[] {8, 8, 8, 8, 8, 8};
        novaPoblacio.add(0, new Patro(x));

        boolean repetit = g.esRepeticio(novaPoblacio, 0);
        System.out.print(repetit + "\n");
        assertFalse(repetit);
    }

    /**
     * Aquest test comprova la funció esRepetició.
     * <p>
     * Casos de prova:
     * - que es repeteixi un patró en la població inicial i la nova.
     * <p>
     * Resultats esperats:
     * - false o true, ja que el patró com la població inicial es genera de forma aleatòria no és segur que es trobi en la llista.
     */
    @Test
    public void testEsRepeticio(){
        Genetic g = new Genetic(6, 8, true);
        g.iniciarPoblacio();

        ArrayList<Patro> novaPoblacio = new ArrayList<>();

        int[] x = new int[] {0, 3,  3, 5, 6, 7};
        novaPoblacio.add(0, new Patro(x));

        boolean repetit = g.esRepeticio(novaPoblacio, 0);
        System.out.print(repetit + "\n");
    }


    /**
     * Aquest test comprova la funció getIntent amb 6 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 6 boles amb colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el dècim intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_6boles() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(6, 8, true);
        int [] s = {7, 3, 1, 1, 3, 6};
        Patro sol = new Patro(6, false);
        sol.setPatro(s);
        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = g.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 0, 1, 1, 2, 3};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = g.comparaIntents(sol, intent);
        g.setCorreccio(cor);

        Patro segonIntent = g.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = g.comparaIntents(sol, segonIntent);
        g.setCorreccio(cor2);

        Patro intent3 = g.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = g.comparaIntents(sol, intent3);
        g.setCorreccio(cor3);

        Patro intent4 = g.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = g.comparaIntents(sol, intent4);
        g.setCorreccio(cor4);

        Patro intent5 = g.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        CorreccioLinea cor5 = g.comparaIntents(sol, intent5);
        g.setCorreccio(cor5);

        Patro intent6 = g.getIntent();
        System.out.print("6e intent: "+ Arrays.toString(intent6.getPatro()) + "\n");

        CorreccioLinea cor6 = g.comparaIntents(sol, intent6);
        g.setCorreccio(cor6);

        Patro intent7 = g.getIntent();
        System.out.print("7e intent: "+ Arrays.toString(intent7.getPatro()) + "\n");

        CorreccioLinea cor7 = g.comparaIntents(sol, intent7);
        g.setCorreccio(cor7);

        Patro intent8 = g.getIntent();
        System.out.print("8e intent: "+ Arrays.toString(intent8.getPatro()) + "\n");

        CorreccioLinea cor8 = g.comparaIntents(sol, intent8);
        g.setCorreccio(cor8);

        Patro intent9 = g.getIntent();
        System.out.print("9e intent: "+ Arrays.toString(intent9.getPatro()) + "\n");

        CorreccioLinea cor9 = g.comparaIntents(sol, intent9);
        g.setCorreccio(cor9);

        Patro intent10 = g.getIntent();
        System.out.print("10e intent: "+ Arrays.toString(intent10.getPatro()) + "\n");

        assertArrayEquals(s, intent10.getPatro());
    }

    /**
     * Aquest test comprova la funció getIntent amb 8 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 8 boles amb colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el dècim intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_8boles() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(8, 8, true);
        int [] s = {7, 4, 7, 0, 5, 0, 3, 2};
        Patro sol = new Patro(8, true);
        sol.setPatro(s);

        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = g.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 0, 1, 1, 2, 2, 3, 4};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = g.comparaIntents(sol, intent);
        g.setCorreccio(cor);

        Patro segonIntent = g.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = g.comparaIntents(sol, segonIntent);
        g.setCorreccio(cor2);

        Patro intent3 = g.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = g.comparaIntents(sol, intent3);
        g.setCorreccio(cor3);

        Patro intent4 = g.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = g.comparaIntents(sol, intent4);
        g.setCorreccio(cor4);

        Patro intent5 = g.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        CorreccioLinea cor5 = g.comparaIntents(sol, intent5);
        g.setCorreccio(cor5);

        Patro intent6 = g.getIntent();
        System.out.print("6e intent: "+ Arrays.toString(intent6.getPatro()) + "\n");

        CorreccioLinea cor6 = g.comparaIntents(sol, intent6);
        g.setCorreccio(cor6);

        Patro intent7 = g.getIntent();
        System.out.print("7e intent: "+ Arrays.toString(intent7.getPatro()) + "\n");

        CorreccioLinea cor7 = g.comparaIntents(sol, intent7);
        g.setCorreccio(cor7);

        Patro intent8 = g.getIntent();
        System.out.print("8e intent: "+ Arrays.toString(intent8.getPatro()) + "\n");

        CorreccioLinea cor8 = g.comparaIntents(sol, intent8);
        g.setCorreccio(cor8);

        Patro intent9 = g.getIntent();
        System.out.print("9e intent: "+ Arrays.toString(intent9.getPatro()) + "\n");

        CorreccioLinea cor9 = g.comparaIntents(sol, intent9);
        g.setCorreccio(cor9);

        Patro intent10 = g.getIntent();
        System.out.print("10e intent: "+ Arrays.toString(intent10.getPatro()) + "\n");

        assertArrayEquals(s, intent10.getPatro());
    }

    /**
     * Aquest test comprova la funció getIntent amb 8 boles, agafant la correcció de la funció comparaIntents
     * <p>
     * Casos de prova:
     * - endevinar una combinació de colors especifica de 8 boles sense colors repetits.
     * <p>
     * Resultats esperats:
     * - que el patró endevinat en el dècim intent sigui igual a la solució inicial.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    @Test
    public void testGetIntent_8bolesSenseRepetits() throws PosicioPatroNoDisponible {
        Genetic g = new Genetic(8, 8, false);
        int [] s = {7, 4, 1, 0, 5, 6, 3, 2};
        Patro sol = new Patro(8, false);
        sol.setPatro(s);

        System.out.print("Solució: "+ Arrays.toString(s) + "\n" + "\n");

        Patro intent = g.getIntent();
        int [] vec1 = intent.getPatro();

        System.out.print("1r intent: "+ Arrays.toString(vec1) + "\n");

        int [] x = {0, 1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(x, vec1);

        CorreccioLinea cor = g.comparaIntents(sol, intent);
        g.setCorreccio(cor);

        Patro segonIntent = g.getIntent();
        System.out.print("2n intent: "+ Arrays.toString(segonIntent.getPatro()) + "\n");

        CorreccioLinea cor2 = g.comparaIntents(sol, segonIntent);
        g.setCorreccio(cor2);

        Patro intent3 = g.getIntent();
        System.out.print("3r intent: "+ Arrays.toString(intent3.getPatro()) + "\n");

        CorreccioLinea cor3 = g.comparaIntents(sol, intent3);
        g.setCorreccio(cor3);

        Patro intent4 = g.getIntent();
        System.out.print("4t intent: "+ Arrays.toString(intent4.getPatro()) + "\n");

        CorreccioLinea cor4 = g.comparaIntents(sol, intent4);
        g.setCorreccio(cor4);

        Patro intent5 = g.getIntent();
        System.out.print("5e intent: "+ Arrays.toString(intent5.getPatro()) + "\n");

        CorreccioLinea cor5 = g.comparaIntents(sol, intent5);
        g.setCorreccio(cor5);

        Patro intent6 = g.getIntent();
        System.out.print("6e intent: "+ Arrays.toString(intent6.getPatro()) + "\n");

        CorreccioLinea cor6 = g.comparaIntents(sol, intent6);
        g.setCorreccio(cor6);

        Patro intent7 = g.getIntent();
        System.out.print("7e intent: "+ Arrays.toString(intent7.getPatro()) + "\n");

        CorreccioLinea cor7 = g.comparaIntents(sol, intent7);
        g.setCorreccio(cor7);

        Patro intent8 = g.getIntent();
        System.out.print("8e intent: "+ Arrays.toString(intent8.getPatro()) + "\n");

        CorreccioLinea cor8 = g.comparaIntents(sol, intent8);
        g.setCorreccio(cor8);

        Patro intent9 = g.getIntent();
        System.out.print("9e intent: "+ Arrays.toString(intent9.getPatro()) + "\n");

        CorreccioLinea cor9 = g.comparaIntents(sol, intent9);
        g.setCorreccio(cor9);

        Patro intent10 = g.getIntent();
        System.out.print("10e intent: "+ Arrays.toString(intent10.getPatro()) + "\n");

        assertArrayEquals(s, intent10.getPatro());
    }
}
