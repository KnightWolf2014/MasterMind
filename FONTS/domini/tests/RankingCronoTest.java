package domini.tests;

import domini.model.PosJugador;
import org.junit.Test;
import org.junit.Before;
import domini.model.RankingCrono;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RankingCronoTest {

    /**
     * Variables de la classe RankingCronoTest.
     */
    private RankingCrono rankingCrono;
    private ArrayList<PosJugador> ranking;
    private ArrayList<PosJugador> solucioRanking;
    private ArrayList<String> solucioString;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzaTest() {
        rankingCrono = new RankingCrono();
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
        for (int i = 0; i < 10; ++i) {
            PosJugador pos = new PosJugador(99, "EMPTY", -1);
            ranking.add(i, pos);
        }
        rankingCrono.setRanking(ranking);
    }

    /**
     * Aquest mètode s'encarrega d'omplir el ranking.
     */
    private void carregarRankingPle() {

        boolean carregat = true;

        int[] victories = new int[] {3, 7, 3, 6, 10, 6, 7, 6, 3, 5};
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

        int[] intents = new int[]{27, 67, 20, 54, 15, 45, 69, 48, 27, 41};


        for (int i = 0; i < 10; ++i) {
            boolean anadido = rankingCrono.afegirJugadorRanking(intents[i], players.get(i), victories[i]);
            if (!anadido) {
                System.out.println("No afegit");
                carregat = false;
            }
        }
        ranking = rankingCrono.getRanking();

        if (carregat) System.out.println("Ranking carregat correctament");
        else System.out.println("Ranking carregat parcialment");
    }

    /**
     * Aquest test comprova si es pot afegir un jugador a un ranking buit.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet 3 victories i 27 intents.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en Pep a la posició 1 i la resta tot de posicions "Empty".
     */
    @Test
    public void testAfegirJugadorRankingBuit() {

        PosJugador sol;

        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                sol = new PosJugador(27, "Pep", 3);
                solucioRanking.add(i, sol);
            } else {
                sol = new PosJugador(99, "EMPTY", -1);
                solucioRanking.add(i, sol);
            }
        }

        int intents1 = 27;
        String player1 = "Pep";
        int victories1 = 3;

        boolean anadido = rankingCrono.afegirJugadorRanking(intents1, player1, victories1);
        if (!anadido) System.out.println("No afegit");
        ranking = rankingCrono.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova si es pot afegir tres jugadors a un ranking buit.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet 3 victories i 27 intents.
     * - Afegir una persona que es diu Carlos, que ha fet 7 victories i 67 intents.
     * - Afegir una persona que es diu Ana, que ha fet 3 victories i 20 intents.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en Carlos, l'Ana i en Pep a les tres primeres posicions (en aquest ordre) i la resta tot de posicions "Empty".
     */
    @Test
    public void testAfegirJugadorRankingMigPle() {

        PosJugador sol;

        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                sol = new PosJugador(67, "Carlos", 7);
                solucioRanking.add(i, sol);
            } else if (i == 1) {
                sol = new PosJugador(20, "Ana", 3);
                solucioRanking.add(i, sol);
            } else if (i == 2) {
                sol = new PosJugador(27, "Pep", 3);
                solucioRanking.add(i, sol);
            } else {
                sol = new PosJugador(99, "EMPTY", -1);
                solucioRanking.add(i, sol);
            }
        }

        int[] victories = new int[] {3, 7, 3};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[] intents = new int[]{27, 67, 20};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingCrono.afegirJugadorRanking(intents[i], players.get(i), victories[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingCrono.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova si es pot afegir un jugador que ha fet la millor partida a un ranking ple.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu GOD, que ha fet 10 victories i 10 intents.
     * <p>
     * Resultat esperat:
     * - Un ranking amb en GOD a la primera posició.
     */
    @Test
    public void testAfegirJugadorRankingPleRecordPrimeraPos() {

        PosJugador sol;

        int[] victories = new int[]{10, 10, 7, 7, 6, 6, 6, 5, 3, 3};
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

        int[] intents = new int[]{10, 15, 67, 69, 45, 48, 54, 41, 20, 27};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), victories[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int victories11 = 10;
        String player11 = "GOD";
        int intents11 = 10;

        boolean anadido = rankingCrono.afegirJugadorRanking(intents11, player11, victories11);
        if (!anadido) System.out.println("No afegit");
        ranking = rankingCrono.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova si es pot afegir un jugador que ha fet una partida normal a un ranking ple.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu MID, que ha fet 6 victories i 46 intents.
     * <p>
     * Resultat esperat:
     * - Un ranking ple amb en MID a la cinquena posició.
     */
    @Test
    public void testAfegirJugadorRankingPleRecordCinquenaPos() {

        PosJugador sol;

        int[] victories = new int[]{10, 7, 7, 6, 6, 6, 6, 5, 3, 3};
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

        int[] intents = new int[]{15, 67, 69, 45, 46, 48, 54, 41, 20, 27};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), victories[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int intents11 = 46;
        String player11 = "MID";
        int victories11 = 6;

        boolean anadido = rankingCrono.afegirJugadorRanking(intents11, player11, victories11);
        if (anadido) System.out.println("No Afegit");
        ranking = rankingCrono.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova com no s'afegeix un jugador que no ha fet record.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu LOSER, que ha fet 1 victoria i 9 intents.
     * <p>
     * Resultat esperat:
     * - Un ranking ple sense en LOSER.
     */
    @Test
    public void testAfegirJugadorRankingPleNoRecord() {

        PosJugador sol;

        int[] victories = new int[]{10, 7, 7, 6, 6, 6, 5, 3, 3, 3};
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

        int[] intents = new int[]{15, 67, 69, 45,  48, 54, 41, 20, 27, 27};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), victories[i]);
            solucioRanking.add(i, sol);
        }

        carregarRankingPle();

        int intents11 = 9;
        String player11 = "LOSER";
        int victories11 = 1;

        boolean anadido = rankingCrono.afegirJugadorRanking(intents11, player11, victories11);
        if (anadido) System.out.println("No Afegit");
        ranking = rankingCrono.getRanking();

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
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

        rankingCrono.mostrarRanking(4, false, false, 5);

        String output = sortidaTerminal.toString();

        solucioString.add("Ranking Mode Crono -> 5 minuts amb 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        for (int i = 0; i < 10; ++i) {
            solucioString.add("Top " + (i + 1) + ") Player: EMPTY, Victories: -1, Intents: 99");
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

        int[] victories = new int[] {3, 7, 3};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[] intents = new int[]{27, 67, 20};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingCrono.afegirJugadorRanking(intents[i], players.get(i), victories[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingCrono.getRanking();
        rankingCrono.setRanking(ranking);

        rankingCrono.mostrarRanking(4, false, false, 5);

        String output = sortidaTerminal.toString();

        solucioString.add("Ranking Mode Crono -> 5 minuts amb 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        for (int i = 0; i < 10; ++i) {
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Victories: 7, Intents: 67");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Victories: 3, Intents: 20");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Victories: 3, Intents: 27");
            else solucioString.add("Top " + (i + 1) + ") Player: EMPTY, Victories: -1, Intents: 99");
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

        rankingCrono.mostrarRanking(4, false, false, 5);

        String[] liniesTerminal = sortidaTerminal.toString().split(System.lineSeparator());
        String[] liniesUtils = Arrays.copyOfRange(liniesTerminal, 1, liniesTerminal.length);
        String output = String.join(System.lineSeparator(), liniesUtils);

        solucioString.add("Ranking Mode Crono -> 5 minuts amb 4 boles");
        solucioString.add("ModeAjuda: NO --- ColorsRepetits: NO");
        solucioString.add("Top 1) Player: Joel, Victories: 10, Intents: 15");
        solucioString.add("Top 2) Player: Carlos, Victories: 7, Intents: 67");
        solucioString.add("Top 3) Player: Susanna, Victories: 7, Intents: 69");
        solucioString.add("Top 4) Player: Rosa, Victories: 6, Intents: 45");
        solucioString.add("Top 5) Player: Nico, Victories: 6, Intents: 48");
        solucioString.add("Top 6) Player: Bob, Victories: 6, Intents: 54");
        solucioString.add("Top 7) Player: Hector, Victories: 5, Intents: 41");
        solucioString.add("Top 8) Player: Ana, Victories: 3, Intents: 20");
        solucioString.add("Top 9) Player: Pep, Victories: 3, Intents: 27");
        solucioString.add("Top 10) Player: Alba, Victories: 3, Intents: 27");

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

        ArrayList<String> resultatString = rankingCrono.guardarRankingCrono();

        for (int i = 0; i < 10; ++i){
            solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Victories: -1, Intents: 99");
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
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Victories: 7, Intents: 67");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Victories: 3, Intents: 20");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Victories: 3, Intents: 27");
            else solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Victories: -1, Intents: 99");
        }

        int[] victories = new int[] {3, 7, 3};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[] intents = new int[]{27, 67, 20};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingCrono.afegirJugadorRanking(intents[i], players.get(i), victories[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingCrono.getRanking();
        rankingCrono.setRanking(ranking);

        ArrayList<String> resultatString = rankingCrono.guardarRankingCrono();

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

        solucioString.add("Top 1) Player: Joel, Victories: 10, Intents: 15");
        solucioString.add("Top 2) Player: Carlos, Victories: 7, Intents: 67");
        solucioString.add("Top 3) Player: Susanna, Victories: 7, Intents: 69");
        solucioString.add("Top 4) Player: Rosa, Victories: 6, Intents: 45");
        solucioString.add("Top 5) Player: Nico, Victories: 6, Intents: 48");
        solucioString.add("Top 6) Player: Bob, Victories: 6, Intents: 54");
        solucioString.add("Top 7) Player: Hector, Victories: 5, Intents: 41");
        solucioString.add("Top 8) Player: Ana, Victories: 3, Intents: 20");
        solucioString.add("Top 9) Player: Pep, Victories: 3, Intents: 27");
        solucioString.add("Top 10) Player: Alba, Victories: 3, Intents: 27");

        carregarRankingPle();
        rankingCrono.setRanking(ranking);

        ArrayList<String> resultatString = rankingCrono.guardarRankingCrono();

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
            solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Victories: -1, Intents: 99");
        }

        rankingCrono.carregarRankingCrono(solucioString);
        ranking = rankingCrono.getRanking();

        PosJugador sol;
        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(99, "EMPTY", -1);
            solucioRanking.add(i, sol);
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
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
            if (i == 0) solucioString.add("Top 1) Player: Carlos, Victories: 7, Intents: 67");
            else if (i == 1) solucioString.add("Top 2) Player: Ana, Victories: 3, Intents: 20");
            else if (i == 2) solucioString.add("Top 3) Player: Pep, Victories: 3, Intents: 27");
            else solucioString.add("Top "  + (i+1) + ") Player: EMPTY, Victories: -1, Intents: 99");
        }
        rankingCrono.carregarRankingCrono(solucioString);
        ranking = rankingCrono.getRanking();

        PosJugador sol;
        for (int i = 0; i < 10; ++i) {
            if (i == 0) {
                sol = new PosJugador(67, "Carlos", 7);
                solucioRanking.add(i, sol);
            } else if (i == 1) {
                sol = new PosJugador(20, "Ana", 3);
                solucioRanking.add(i, sol);
            } else if (i == 2) {
                sol = new PosJugador(27, "Pep", 3);
                solucioRanking.add(i, sol);
            } else {
                sol = new PosJugador(99, "EMPTY", -1);
                solucioRanking.add(i, sol);
            }
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
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

        solucioString.add("Top 1) Player: Joel, Victories: 10, Intents: 15");
        solucioString.add("Top 2) Player: Carlos, Victories: 7, Intents: 67");
        solucioString.add("Top 3) Player: Susanna, Victories: 7, Intents: 69");
        solucioString.add("Top 4) Player: Rosa, Victories: 6, Intents: 45");
        solucioString.add("Top 5) Player: Nico, Victories: 6, Intents: 48");
        solucioString.add("Top 6) Player: Bob, Victories: 6, Intents: 54");
        solucioString.add("Top 7) Player: Hector, Victories: 5, Intents: 41");
        solucioString.add("Top 8) Player: Ana, Victories: 3, Intents: 20");
        solucioString.add("Top 9) Player: Pep, Victories: 3, Intents: 27");
        solucioString.add("Top 10) Player: Alba, Victories: 3, Intents: 27");

        rankingCrono.carregarRankingCrono(solucioString);
        ranking = rankingCrono.getRanking();

        PosJugador sol;
        int[] victories = new int[]{10, 7, 7, 6, 6, 6, 5, 3, 3, 3};
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

        int[] intents = new int[]{15, 67, 69, 45, 48, 54, 41, 20, 27, 27};

        for (int i = 0; i < 10; ++i) {
            sol = new PosJugador(intents[i], players.get(i), victories[i]);
            solucioRanking.add(i, sol);
        }

        for (int i = 0; i < 10; ++i) {
            assertEquals(ranking.get(i).getIntents(), solucioRanking.get(i).getIntents());
            assertEquals(ranking.get(i).getPlayer(), solucioRanking.get(i).getPlayer());
            assertEquals(ranking.get(i).getVictories(), solucioRanking.get(i).getVictories());
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

        int intents = 10;
        int victories = 10;

        boolean resultat = rankingCrono.primeraPosicio(intents, victories);

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

        int intents = 10;
        int victories = 10;

        boolean resultat = rankingCrono.primeraPosicio(intents, victories);

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

        int intents = 25;
        int victories = 4;

        boolean resultat = rankingCrono.primeraPosicio(intents, victories);

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

        int intents = 10;
        int victories = 7;

        boolean resultat = rankingCrono.novaPosRanking(victories, intents);

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

        int victories = 5;
        int intents = 32;

        boolean resultat = rankingCrono.novaPosRanking(victories, intents);

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
        int victories = 1;

        boolean resultat = rankingCrono.novaPosRanking(victories, intents);

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

        ArrayList<String> matRanking = rankingCrono.getRankingsPresentacio();

        for (int i = 0; i < 10; ++i){
            if (i <= 8) solucioString.add("Top " + (i + 1) + ") " + "Player: --------- , Victories: --- , Intents: --- ");
            else solucioString.add("Top " + (i + 1) + ") " + "Player: ------- , Victories: --- , Intents: --- ");
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

        int[] victories = new int[] {3, 7, 3};
        ArrayList<String> players = new ArrayList<>();
        players.add("Pep");
        players.add("Carlos");
        players.add("Ana");

        int[] intents = new int[]{27, 67, 20};

        for (int i = 0; i < 3; ++i) {
            boolean anadido = rankingCrono.afegirJugadorRanking(intents[i], players.get(i), victories[i]);
            if (!anadido) System.out.println("No afegit");
        }
        ranking = rankingCrono.getRanking();
        rankingCrono.setRanking(ranking);

        ArrayList<String> matRanking = rankingCrono.getRankingsPresentacio();

        solucioString.add("Top 1) Player: Carlos, Victories: 7, Intents: 67");
        solucioString.add("Top 2) Player: Ana, Victories: 3, Intents: 20");
        solucioString.add("Top 3) Player: Pep, Victories: 3, Intents: 27");
        for (int i = 3; i < 10; ++i){
            if (i <= 8) solucioString.add("Top " + (i + 1) + ") " + "Player: --------- , Victories: --- , Intents: --- ");
            else solucioString.add("Top " + (i + 1) + ") " + "Player: ------- , Victories: --- , Intents: --- ");
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

        ArrayList<String> matRanking = rankingCrono.getRankingsPresentacio();

        solucioString.add("Top 1) Player: Joel, Victories: 10, Intents: 15");
        solucioString.add("Top 2) Player: Carlos, Victories: 7, Intents: 67");
        solucioString.add("Top 3) Player: Susanna, Victories: 7, Intents: 69");
        solucioString.add("Top 4) Player: Rosa, Victories: 6, Intents: 45");
        solucioString.add("Top 5) Player: Nico, Victories: 6, Intents: 48");
        solucioString.add("Top 6) Player: Bob, Victories: 6, Intents: 54");
        solucioString.add("Top 7) Player: Hector, Victories: 5, Intents: 41");
        solucioString.add("Top 8) Player: Ana, Victories: 3, Intents: 20");
        solucioString.add("Top 9) Player: Pep, Victories: 3, Intents: 27");
        solucioString.add("Top 10) Player: Alba, Victories: 3, Intents: 27");

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioString.get(i), matRanking.get(i));
        }
    }
}

