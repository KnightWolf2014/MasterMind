package domini.tests;

import domini.model.PosJugador;
import org.junit.Test;
import org.junit.Before;
import domini.model.RankingClassic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RankingClassicTest {

    /**
     * Variables de la classe RankingClassicTest.
     */
    private RankingClassic rankingClassic;
    private ArrayList<PosJugador> ranking;
    private ArrayList<PosJugador> solucioRanking;
    private ArrayList<String> solucioString;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzaTest() {
        rankingClassic = new RankingClassic();
        ranking = new ArrayList<>();
        solucioRanking = new ArrayList<>();
        solucioString = new ArrayList<>();
        carregarRankingBuit();
    }

    /**
     * Aquest mètode s'encarrega d'omplir un ranking amb les posicions buides.
     */
    private void carregarRankingBuit() {
        ranking.clear();
        int[] temps = new int[]{99, 99};
        for (int i = 0; i < 10; ++i) {
            PosJugador pos = new PosJugador(99, "EMPTY", temps);
            ranking.add(i, pos);
        }
        rankingClassic.setRanking(ranking);
    }

    /**
     * Aquest mètode s'encarrega d'omplir el ranking.
     */
    private void carregarRankingPle() {

        boolean carregat = true;

        int[] intents = new int[]{7, 3, 7, 6, 1, 4, 4, 5, 9, 6};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");
        players.add("Bob");
        players.add("Joel");
        players.add("Rosa");
        players.add("Susanna");
        players.add("Nico");
        players.add("Alba");
        players.add("Hector");

        int[][] temps = new int[][]{{23, 12}, {54, 15}, {12, 34}, {23, 12}, {1, 1}, {45, 32}, {45, 30}, {12, 23}, {22, 12}, {34, 32}};

        for (int i = 0; i < 10; ++i) {
            boolean anadido = rankingClassic.afegirJugadorRanking(intents[i], players.get(i), temps[i]);
            if (!anadido) {
                System.out.println("No afegit");
                carregat = false;
            }
        }
        ranking = rankingClassic.getRanking();

        if (carregat) System.out.println("Ranking carregat correctament");
        else System.out.println("Ranking carregat parcialment");
    }

    /**
     * Aquest test comprova si es pot afegir un jugador a un ranking buit.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet 7 intents i que ha trigat 23 minuts i 12 segons.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en Pep a la posició 1 i la resta tot de posicions "Empty".
     */
    @Test
    public void testAfegirJugadorRankingBuit() {

        PosJugador sol;

        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                int[] tempsS = new int[]{23, 12};
                sol = new PosJugador(7, "Pep", tempsS);
                solucioRanking.add(i, sol);
            } else {
                int[] tempsEmpty = new int[]{99, 99};
                sol = new PosJugador(99, "EMPTY", tempsEmpty);
                solucioRanking.add(i, sol);
            }
        }

        int intents1 = 7;
        String player1 = "Pep";
        int[] temps1 = {23, 12};

        boolean anadido = rankingClassic.afegirJugadorRanking(intents1, player1, temps1);
        if (!anadido) System.out.println("No afegit");
        ranking = rankingClassic.getRanking();
        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova si es pot afegir tres jugadors a un ranking buit.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet 7 intents i que ha trigat 23 minuts i 12 segons.
     * - Afegir una persona que es diu Carlos, que ha fet 3 intents i que ha trigat 54 minuts i 15 segons.
     * - Afegir una persona que es diu Ana, que ha fet 7 intents i que ha trigat 12 minuts i 34 segons.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en Carlos, l'Ana i en Pep a les tres primeres posicions (en aquest ordre) i la resta tot de posicions "Empty".
     */
    @Test
    public void testAfegirJugadorRankingMigPle() {

        PosJugador sol;

        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                int[] tempsS = new int[]{54, 15};
                sol = new PosJugador(3, "Carlos", tempsS);
                solucioRanking.add(i, sol);
            } else if (i == 1) {
                int[] tempsS = new int[]{12, 34};
                sol = new PosJugador(7, "Ana", tempsS);
                solucioRanking.add(i, sol);
            } else if (i == 2) {
                int[] tempsS = new int[]{23, 12};
                sol = new PosJugador(7, "Pep", tempsS);
                solucioRanking.add(i, sol);
            } else {
                int[] tempsEmpty = new int[]{99, 99};
                sol = new PosJugador(99, "EMPTY", tempsEmpty);
                solucioRanking.add(i, sol);
            }
        }

        int[] intents = new int[]{7, 3, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[][] temps = new int[][]{{23, 12}, {54, 15}, {12, 34}};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingClassic.afegirJugadorRanking(intents[i], players.get(i), temps[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingClassic.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }


    /**
     * Aquest test comprova si es pot afegir un jugador que ha fet la millor partida a un ranking ple.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu GOD, que ha fet 1 intent i que ha trigat 0 minuts i 1 segon.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en GOD a la primera posició.
     */
    @Test
    public void testAfegirJugadorRankingPleRecordPrimeraPos() {

        PosJugador sol;

        int[] intents = new int[]{1, 1, 3, 4, 4, 5, 6, 6, 7, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("GOD");
        players.add("Joel");
        players.add("Carlos");
        players.add("Susanna");
        players.add("Rosa");
        players.add("Nico");
        players.add("Bob");
        players.add("Hector");
        players.add("Ana");
        players.add("Pep");

        int[][] temps = new int[][]{{0, 1}, {1, 1}, {54, 15}, {45, 30}, {45, 32}, {12, 23}, {23, 12}, {34, 32}, {12, 34}, {23, 12}};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), temps[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int intents11 = 1;
        String player11 = "GOD";
        int[] temps11 = {0, 1};

        boolean anadido = rankingClassic.afegirJugadorRanking(intents11, player11, temps11);
        if (anadido) System.out.println("No Afegit");
        ranking = rankingClassic.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova si es pot afegir un jugador que ha fet una partida normal a un ranking ple.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu MID, que ha fet 5 intents i que ha trigat 5 minuts i 5 segons.
     * <p>
     * Resultat esperat:
     * - Un ranking ple amb en MID a la cinquena posició.
     */
    @Test
    public void testAfegirJugadorRankingPleRecordCinquenaPos() {

        PosJugador sol;

        int[] intents = new int[]{1, 3, 4, 4, 5, 5, 6, 6, 7, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("Joel");
        players.add("Carlos");
        players.add("Susanna");
        players.add("Rosa");
        players.add("MID");
        players.add("Nico");
        players.add("Bob");
        players.add("Hector");
        players.add("Ana");
        players.add("Pep");

        int[][] temps = new int[][]{{1, 1}, {54, 15}, {45, 30}, {45, 32}, {5, 5}, {12, 23}, {23, 12}, {34, 32}, {12, 34}, {23, 12}};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), temps[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int intents11 = 5;
        String player11 = "MID";
        int[] temps11 = {5, 5};

        boolean anadido = rankingClassic.afegirJugadorRanking(intents11, player11, temps11);
        if (anadido) System.out.println("No Afegit");
        ranking = rankingClassic.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova com no s'afegeix un jugador que no ha fet record.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu LOSER, que ha fet 9 intents i que ha trigat 59 minuts i 59 segons.
     * <p>
     * Resultat esperat:
     * - Un ranking ple sense en LOSER.
     */
    @Test
    public void testAfegirJugadorRankingPleNoRecord() {

        PosJugador sol;

        int[] intents = new int[]{1, 3, 4, 4, 5, 6, 6, 7, 7, 9};
        ArrayList<String> players = new ArrayList<>();
        players.add("Joel");
        players.add("Carlos");
        players.add("Susanna");
        players.add("Rosa");
        players.add("Nico");
        players.add("Bob");
        players.add("Hector");
        players.add("Ana");
        players.add("Pep");
        players.add("Alba");

        int[][] temps = new int[][]{{1, 1}, {54, 15}, {45, 30}, {45, 32}, {12, 23}, {23, 12}, {34, 32}, {12, 34}, {23, 12}, {22, 12}};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), temps[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int intents11 = 9;
        String player11 = "LOSER";
        int[] temps11 = {59, 59};

        boolean anadido = rankingClassic.afegirJugadorRanking(intents11, player11, temps11);
        if (anadido) System.out.println("No Afegit");
        ranking = rankingClassic.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova com es veu un ranking buit.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració buit.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking buit.
     */
    @Test
    public void testMostrarRankingBuit(){

        ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortidaTerminal));

        rankingClassic.mostrarRanking(4, false, false);

        String output = sortidaTerminal.toString();

        solucioString.add("Ranking Mode Classic -> 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        for (int i = 0; i < 10; ++i) {
            solucioString.add("Top " + (i + 1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        solucioString.add("");

        String separator = System.lineSeparator();
        String resultat = String.join(separator, solucioString);

        assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));

    }

    /**
     * Aquest test comprova com es veu un ranking mig ple.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració mig ple.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking mig ple.
     */
    @Test
    public void testMostrarRankingMigPle(){


        ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortidaTerminal));

        int[] intents = new int[]{7, 3, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[][] temps = new int[][]{{23, 12}, {54, 15}, {12, 34}};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingClassic.afegirJugadorRanking(intents[i], players.get(i), temps[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingClassic.getRanking();
        rankingClassic.setRanking(ranking);

        rankingClassic.mostrarRanking(4, false, false);

        String output = sortidaTerminal.toString();

        solucioString.add("Ranking Mode Classic -> 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        for (int i = 0; i < 10; ++i) {
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Intents: 3, Temps: 54:15");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Intents: 7, Temps: 12:34");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Intents: 7, Temps: 23:12");
            else solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        solucioString.add("");

        String separator = System.lineSeparator();
        String resultat = String.join(separator, solucioString);

        assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));
    }

    /**
     * Aquest test comprova com es veu un ranking ple.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració ple.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking ple.
     */
    @Test
    public void testMostrarRankingPle(){

        ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortidaTerminal));

        carregarRankingPle();

        rankingClassic.mostrarRanking(4, false, false);

        String[] liniesTerminal = sortidaTerminal.toString().split(System.lineSeparator());
        String[] liniesUtils = Arrays.copyOfRange(liniesTerminal, 1, liniesTerminal.length);
        String output = String.join(System.lineSeparator(), liniesUtils);

        solucioString.add("Ranking Mode Classic -> 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        solucioString.add("Top 1) Player: Joel, Intents: 1, Temps: 1:1");
        solucioString.add("Top 2) Player: Carlos, Intents: 3, Temps: 54:15");
        solucioString.add("Top 3) Player: Susanna, Intents: 4, Temps: 45:30");
        solucioString.add("Top 4) Player: Rosa, Intents: 4, Temps: 45:32");
        solucioString.add("Top 5) Player: Nico, Intents: 5, Temps: 12:23");
        solucioString.add("Top 6) Player: Bob, Intents: 6, Temps: 23:12");
        solucioString.add("Top 7) Player: Hector, Intents: 6, Temps: 34:32");
        solucioString.add("Top 8) Player: Ana, Intents: 7, Temps: 12:34");
        solucioString.add("Top 9) Player: Pep, Intents: 7, Temps: 23:12");
        solucioString.add("Top 10) Player: Alba, Intents: 9, Temps: 22:12");

        String separator = System.lineSeparator();
        String resultat = String.join(separator, solucioString);

        assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));
    }

    /**
     * Aquest test comprova que es tradueix un ranking buit a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir un ranking buit a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual al ranking buit.
     */
    @Test
    public void testRankingToStringBuit(){

        ArrayList<String> resultatString = rankingClassic.guardarRankingClassic();

        for (int i = 0; i < 10; ++i){
            solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix un ranking mig ple a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir un ranking mig ple a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual al ranking mig ple.
     */
    @Test
    public void testRankingToStringMigPle(){

        for (int i = 0; i < 10; ++i){
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Intents: 3, Temps: 54:15");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Intents: 7, Temps: 12:34");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Intents: 7, Temps: 23:12");
            else solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        int[] intents = new int[]{7, 3, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[][] temps = new int[][]{{23, 12}, {54, 15}, {12, 34}};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingClassic.afegirJugadorRanking(intents[i], players.get(i), temps[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingClassic.getRanking();
        rankingClassic.setRanking(ranking);

        ArrayList<String> resultatString = rankingClassic.guardarRankingClassic();

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix un ranking ple a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir un ranking ple a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual al ranking ple.
     */
    @Test
    public void testRankingToStringPle(){

        solucioString.add("Top 1) Player: Joel, Intents: 1, Temps: 1:1");
        solucioString.add("Top 2) Player: Carlos, Intents: 3, Temps: 54:15");
        solucioString.add("Top 3) Player: Susanna, Intents: 4, Temps: 45:30");
        solucioString.add("Top 4) Player: Rosa, Intents: 4, Temps: 45:32");
        solucioString.add("Top 5) Player: Nico, Intents: 5, Temps: 12:23");
        solucioString.add("Top 6) Player: Bob, Intents: 6, Temps: 23:12");
        solucioString.add("Top 7) Player: Hector, Intents: 6, Temps: 34:32");
        solucioString.add("Top 8) Player: Ana, Intents: 7, Temps: 12:34");
        solucioString.add("Top 9) Player: Pep, Intents: 7, Temps: 23:12");
        solucioString.add("Top 10) Player: Alba, Intents: 9, Temps: 22:12");

        carregarRankingPle();
        rankingClassic.setRanking(ranking);

        ArrayList<String> resultatString = rankingClassic.guardarRankingClassic();

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings buit a un ranking.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings buit a un ranking.
     * <p>
     * Resultat esperat:
     * - Un ranking igual al ArrayList de strings buit.
     */
    @Test
    public void testStringToRankingBuit(){

        for (int i = 0; i < 10; ++i){
            solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        rankingClassic.carregarRankingClassic(solucioString);
        ranking = rankingClassic.getRanking();

        PosJugador sol;
        for (int i = 0; i < 10; ++i) {
            int[] tempsEmpty = new int[]{99, 99};
            sol = new PosJugador(99, "EMPTY", tempsEmpty);
            solucioRanking.add(i, sol);
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings mig ple a un ranking.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings mig ple a un ranking.
     * <p>
     * Resultat esperat:
     * - Un ranking igual al ArrayList de strings mig ple.
     */
    @Test
    public void testStringToRankingMigPle(){

        for (int i = 0; i < 10; ++i){
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Intents: 3, Temps: 54:15");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Intents: 7, Temps: 12:34");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Intents: 7, Temps: 23:12");
            else solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        rankingClassic.carregarRankingClassic(solucioString);
        ranking = rankingClassic.getRanking();

        PosJugador sol;
        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                int[] tempsS = new int[]{54, 15};
                sol = new PosJugador(3, "Carlos", tempsS);
                solucioRanking.add(i, sol);
            } else if (i == 1) {
                int[] tempsS = new int[]{12, 34};
                sol = new PosJugador(7, "Ana", tempsS);
                solucioRanking.add(i, sol);
            } else if (i == 2) {
                int[] tempsS = new int[]{23, 12};
                sol = new PosJugador(7, "Pep", tempsS);
                solucioRanking.add(i, sol);
            } else {
                int[] tempsEmpty = new int[]{99, 99};
                sol = new PosJugador(99, "EMPTY", tempsEmpty);
                solucioRanking.add(i, sol);
            }
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings ple a un ranking.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings ple a un ranking.
     * <p>
     * Resultat esperat:
     * - Un ranking igual al ArrayList de strings ple.
     */
    @Test
    public void testStringToRankingPle(){

        solucioString.add("Top 1) Player: Joel, Intents: 1, Temps: 1:1");
        solucioString.add("Top 2) Player: Carlos, Intents: 3, Temps: 54:15");
        solucioString.add("Top 3) Player: Susanna, Intents: 4, Temps: 45:32");
        solucioString.add("Top 4) Player: Rosa, Intents: 4, Temps: 45:32");
        solucioString.add("Top 5) Player: Nico, Intents: 5, Temps: 12:23");
        solucioString.add("Top 6) Player: Bob, Intents: 6, Temps: 23:12");
        solucioString.add("Top 7) Player: Hector, Intents: 6, Temps: 34:32");
        solucioString.add("Top 8) Player: Ana, Intents: 7, Temps: 12:34");
        solucioString.add("Top 9) Player: Pep, Intents: 7, Temps: 23:12");
        solucioString.add("Top 10) Player: Alba, Intents: 9, Temps: 22:12");

        rankingClassic.carregarRankingClassic(solucioString);
        ranking = rankingClassic.getRanking();

        PosJugador sol;
        int[] intents = new int[]{1, 3, 4, 4, 5, 6, 6, 7, 7, 9};
        ArrayList<String> players = new ArrayList<>();
        players.add("Joel");
        players.add("Carlos");
        players.add("Susanna");
        players.add("Rosa");
        players.add("Nico");
        players.add("Bob");
        players.add("Hector");
        players.add("Ana");
        players.add("Pep");
        players.add("Alba");

        int[][] temps = new int[][]{{1, 1}, {54, 15}, {45, 32}, {45, 32}, {12, 23}, {23, 12}, {34, 32}, {12, 34}, {23, 12}, {22, 12}};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), temps[i]);
            solucioRanking.add(i, sol);
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertArrayEquals(ranking.get(i).getTemps(), solucioRanking.get(i).getTemps());
        }
    }

    /**
     * Aquest test comprova que la nova posició sigui la primera posició.
     * <p>
     * Casos de prova:
     * - Un jugador que té la primera posició.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testPrimeraPosCorrecteBuit(){
        carregarRankingBuit();

        int intents = 1;
        int[] temps = {0, 1};

        boolean resultat = rankingClassic.primeraPosicio(intents, temps);

        assertTrue(resultat);
    }

    /**
     * Aquest test comprova que la nova posició sigui la primera posició.
     * <p>
     * Casos de prova:
     * - Un jugador que té la primera posició.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testPrimeraPosCorrectePle(){
        carregarRankingPle();

        int intents = 1;
        int[] temps = {0, 1};

        boolean resultat = rankingClassic.primeraPosicio(intents, temps);

        assertTrue(resultat);
    }

    /**
     * Aquest test comprova que la nova posició no sigui la primera posició.
     * <p>
     * Casos de prova:
     * - Un jugador que no té la primera posició.
     * <p>
     * Resultat esperat:
     * - Un false.
     */
    @Test
    public void testPrimeraPosIncorrectePle(){
        carregarRankingPle();

        int intents = 7;
        int[] temps = {34, 12};

        boolean resultat = rankingClassic.primeraPosicio(intents, temps);

        assertFalse(resultat);
    }

    /**
     * Aquest test comprova que la nova posició entri al ranking una vegada estigui ple.
     * <p>
     * Casos de prova:
     * - Un jugador té una posició que entra en el top 10 del ranking.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testNovaPosRankingAfegit(){
        carregarRankingPle();

        int intents = 5;
        int[] temps = {1, 5};

        boolean resultat = rankingClassic.novaPosRanking(intents, temps);

        assertTrue(resultat);
    }

    /**
     * Aquest test comprova que la nova posició entri al ranking una vegada estigui buit.
     * <p>
     * Casos de prova:
     * - Un jugador té una posició que entra en el top 10 del ranking.
     * <p>
     * Resultat esperat:
     * - Un true.
     */
    @Test
    public void testNovaPosRankingAfegitBuit(){
        carregarRankingBuit();

        int intents = 5;
        int[] temps = {1, 5};

        boolean resultat = rankingClassic.novaPosRanking(intents, temps);

        assertTrue(resultat);
    }

    /**
     * Aquest test comprova que la nova posició no entri al ranking una vegada estigui ple.
     * <p>
     * Casos de prova:
     * - Un jugador té una posició que no entra en el top 10 del ranking.
     * <p>
     * Resultat esperat:
     * - Un false.
     */
    @Test
    public void testNovaPosRankingNoAfegit(){
        carregarRankingPle();

        int intents = 9;
        int[] temps = {22, 12};

        boolean resultat = rankingClassic.novaPosRanking(intents, temps);

        assertFalse(resultat);
    }

    /**
     * Aquest test comprova com es veu un ranking buit des de la presentació.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració buit.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking buit.
     */
    @Test
    public void testGetRankingPresentacioBuits(){
        carregarRankingBuit();

        ArrayList<String> matRanking = rankingClassic.getRankingsPresentacio();

        for (int i = 0; i < 10; ++i){
            if (i <= 8) solucioString.add("Top " + (i+1) + ") Player: --------- , Intents: --- , Temps: --- : --- ");
            else solucioString.add("Top " + (i+1) + ") Player: ------- , Intents: --- , Temps: --- : --- ");
        }

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioString.get(i), matRanking.get(i));
        }
    }

    /**
     * Aquest test comprova com es veu un ranking mig ple des de la presentació.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració mig ple.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking mig ple.
     */
    @Test
    public void testGetRankingPresentacioMigPlens(){

        int[] intents = new int[]{7, 3, 7};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[][] temps = new int[][]{{23, 12}, {54, 15}, {12, 34}};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingClassic.afegirJugadorRanking(intents[i], players.get(i), temps[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingClassic.getRanking();
        rankingClassic.setRanking(ranking);

        ArrayList<String> matRanking = rankingClassic.getRankingsPresentacio();

        solucioString.add("Top 1) Player: Carlos, Intents: 3, Temps: 54:15");
        solucioString.add("Top 2) Player: Ana, Intents: 7, Temps: 12:34");
        solucioString.add("Top 3) Player: Pep, Intents: 7, Temps: 23:12");
        for (int i = 3; i < 10; ++i){
            if (i <= 8) solucioString.add("Top " + (i+1) + ") Player: --------- , Intents: --- , Temps: --- : --- ");
            else solucioString.add("Top " + (i+1) + ") Player: ------- , Intents: --- , Temps: --- : --- ");
        }

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioString.get(i), matRanking.get(i));
        }
    }

    /**
     * Aquest test comprova com es veu un ranking ple des de la presentació.
     * <p>
     * Casos de prova:
     * - Mostrar el ranking amb qualsevol configuració ple.
     * <p>
     * Resultat esperat:
     * - Qualsevol ranking ple.
     */
    @Test
    public void testGetRankingPresentacioPlens(){
        carregarRankingPle();

        ArrayList<String> matRanking = rankingClassic.getRankingsPresentacio();

        solucioString.add("Top 1) Player: Joel, Intents: 1, Temps: 1:1");
        solucioString.add("Top 2) Player: Carlos, Intents: 3, Temps: 54:15");
        solucioString.add("Top 3) Player: Susanna, Intents: 4, Temps: 45:30");
        solucioString.add("Top 4) Player: Rosa, Intents: 4, Temps: 45:32");
        solucioString.add("Top 5) Player: Nico, Intents: 5, Temps: 12:23");
        solucioString.add("Top 6) Player: Bob, Intents: 6, Temps: 23:12");
        solucioString.add("Top 7) Player: Hector, Intents: 6, Temps: 34:32");
        solucioString.add("Top 8) Player: Ana, Intents: 7, Temps: 12:34");
        solucioString.add("Top 9) Player: Pep, Intents: 7, Temps: 23:12");
        solucioString.add("Top 10) Player: Alba, Intents: 9, Temps: 22:12");

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioString.get(i), matRanking.get(i));
        }
    }
}

