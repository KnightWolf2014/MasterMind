package domini.tests;

import domini.model.PosJugador;
import domini.model.Records;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RecordsTest {

    /**
     * Variables de la classe RecordsTest.
     */
    private Records records;
    private ArrayList<PosJugador> posRecords;
    private ArrayList<PosJugador> solucioRecords;
    private ArrayList<String> solucioStringMostrar;
    private ArrayList<String> solucioString;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzaTest() {
        records = new Records();
        posRecords = new ArrayList<>();
        solucioRecords = new ArrayList<>();
        solucioStringMostrar = new ArrayList<>();
        solucioString = new ArrayList<>();
        carregarRecordsBuit();
    }

    /**
     * Aquest mètode s'encarrega d'omplir uns records amb les posicions buides.
     */
    private void carregarRecordsBuit() {
        posRecords.clear();
        for (int i = 0; i < 12; ++i) {
            int[] temps = new int[]{99,99};
            PosJugador pos = new PosJugador(99, "EMPTY", temps);
            posRecords.add(i, pos);
        }
        for (int i = 12; i < 36; ++i) {
            PosJugador pos = new PosJugador(99, "EMPTY", -1);
            posRecords.add(i, pos);
        }
        records.setRecords(posRecords);
    }

    /**
     * Aquest mètode s'encarrega d'omplir els records.
     */
    private void carregarRecordsPle() {
        int[] temps;

        temps = new int[]{1, 2};
        records.afegirRecordClassic(2, "Carlos", temps, 4, false, false);
        temps = new int[]{2, 36};
        records.afegirRecordClassic(3, "Joel", temps, 4, true, false);
        temps = new int[]{12, 22};
        records.afegirRecordClassic(2, "Carlos", temps, 4, false, true);
        temps = new int[]{14, 25};
        records.afegirRecordClassic(3, "Rosa", temps, 4, true, true);
        temps = new int[]{17, 24};
        records.afegirRecordClassic(3, "Joel", temps, 6, false, false);
        temps = new int[]{14, 23};
        records.afegirRecordClassic(3, "Alba", temps, 6, true, false);
        temps = new int[]{13, 26};
        records.afegirRecordClassic(2, "Joel", temps, 6, false, true);
        temps = new int[]{16, 27};
        records.afegirRecordClassic(2, "Alba", temps, 6, true, true);
        temps = new int[]{17, 54};
        records.afegirRecordClassic(4, "Rosa", temps, 8, false, false);
        temps = new int[]{15, 58};
        records.afegirRecordClassic(3, "Alba", temps, 8, true, false);
        temps = new int[]{34, 28};
        records.afegirRecordClassic(4, "Joel", temps, 8, false, true);
        temps = new int[]{12, 21};
        records.afegirRecordClassic(2, "Alba", temps, 8, true, true);

        records.afegirRecordCrono(22, "Nico", 12, 4, false, false, 5);
        records.afegirRecordCrono(31, "Joel", 11, 4, true, false, 5);
        records.afegirRecordCrono(19, "Carlos", 12, 4, false, true, 5);
        records.afegirRecordCrono(32, "Rosa", 11, 4, true, true, 5);
        records.afegirRecordCrono(31, "Joel", 10, 6, false, false, 5);
        records.afegirRecordCrono(24, "Alba", 13, 6, true, false, 5);
        records.afegirRecordCrono(17, "Joel", 10, 6, false, true, 5);
        records.afegirRecordCrono(16, "Alba", 9, 6, true, true, 5);
        records.afegirRecordCrono(21, "Rosa", 14, 8, false, false, 5);
        records.afegirRecordCrono(31, "Joel", 6, 8, true, false, 5);
        records.afegirRecordCrono(18, "Nico", 8, 8, false, true, 5);
        records.afegirRecordCrono(16, "Nico", 9, 8, true, true, 5);

        records.afegirRecordCrono(22, "Joel", 21, 4, false, false, 10);
        records.afegirRecordCrono(31, "Carlos", 20, 4, true, false, 10);
        records.afegirRecordCrono(19, "Carlos", 22, 4, false, true, 10);
        records.afegirRecordCrono(32, "Rosa", 19, 4, true, true, 10);
        records.afegirRecordCrono(31, "Rosa", 17, 6, false, false, 10);
        records.afegirRecordCrono(24, "Rosa", 19, 6, true, false, 10);
        records.afegirRecordCrono(17, "Joel", 21, 6, false, true, 10);
        records.afegirRecordCrono(16, "Carlos", 16, 6, true, true, 10);
        records.afegirRecordCrono(21, "Rosa", 13, 8, false, false, 10);
        records.afegirRecordCrono(31, "Rosa", 10, 8, true, false, 10);
        records.afegirRecordCrono(18, "Joel", 9, 8, false, true, 10);
        records.afegirRecordCrono(16, "Carlos", 9, 8, true, true, 10);

    }

    /**
     * Aquest mètode s'encarrega de carregar una llista de records buida de manera que quedi un ArrayList de strings.
     */
    public void carregarSolucionsStringBuida(){
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: NO -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: NO -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: SI -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: SI -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
    }

    /**
     * Aquest test comprova si es pot afegir un jugador a la part del classic de la llista de records buida.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet un record.
     * <p>
     * Resultat esperat:
     * - Una llista de records buida amb en Pep a la posició de 4 boles amb mode ajuda i colors repetits desactivats.
     */
    @Test
    public void testAfegirJugadorClassicBuit() {

        PosJugador sol;

        int[] temps = new int[]{99, 99};
        for (int i = 0; i < 12; ++i) {
            if (i == 0) {
                int[]tempsPep = new int[]{12, 32};
                sol = new PosJugador(7, "Pep", tempsPep);
                solucioRecords.add(i, sol);
            }
            else {
                sol = new PosJugador(99, "EMPTY", temps);
                solucioRecords.add(i, sol);
            }
        }
        for (int i = 12; i < 36; ++i) {
            sol = new PosJugador(99, "EMPTY", -1);
            solucioRecords.add(i, sol);
        }

        int intents1 = 7;
        String player1 = "Pep";
        int[] temps1 = new int[]{12, 32};

        records.afegirRecordClassic(intents1, player1, temps1, 4, false, false);
        posRecords = records.getRecords();

        for (int i = 0; i < posRecords.size(); ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova si es pot afegir un record a la part del classic a una llista de records plena.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu GOD, que ha fet un record.
     * <p>
     * Resultat esperat:
     * - Una llista de records plena amb en GOD a la posició de 4 boles amb mode ajuda i colors repetits activats.
     */
    @Test
    public void testAfegirJugadorClassicPle() {

        carregarRecordsPle();

        solucioRecords = records.getRecords();

        solucioRecords.remove(9);
        int[] temps = new int[]{0,1};
        PosJugador pos = new PosJugador(1, "GOD", temps);
        solucioRecords.add(9 ,pos);

        int intents1 = 1;
        String player1 = "GOD";
        int[] temps1 = new int[]{0,1};

        records.afegirRecordClassic(intents1, player1, temps1, 4,true, true);
        posRecords = records.getRecords();

        for (int i = 0; i < posRecords.size(); ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }


    /**
     * Aquest test comprova si es pot afegir un jugador a la part del crono de la llista de records buida.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu Pep, que ha fet un record.
     * <p>
     * Resultat esperat:
     * - Una llista de records buida amb en Pep a la posició de 4 boles amb mode ajuda i colors repetits desactivats i cronometre a 5 minuts.
     */
    @Test
    public void testAfegirJugadorCronoBuit() {

        PosJugador sol;

        int[] temps = new int[]{99,99};
        for (int i = 0; i < 12; ++i) {
            sol = new PosJugador(99, "EMPTY", temps);
            solucioRecords.add(i, sol);
        }
        for (int i = 12; i < 36; ++i){
            if (i == 12) {
                sol = new PosJugador(27, "Pep", 3);
                solucioRecords.add(i, sol);
            } else {
                sol = new PosJugador(99, "EMPTY", -1);
                solucioRecords.add(i, sol);
            }
        }

        int intents1 = 27;
        String player1 = "Pep";
        int victories1 = 3;

        records.afegirRecordCrono(intents1, player1, victories1, 4, false, false, 5);
        posRecords = records.getRecords();

        for (int i = 0; i < posRecords.size(); ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova si es pot afegir un jugador a la part del crono de la llista de records plena.
     * <p>
     * Casos de prova:
     * - Afegir una persona que es diu GOD, que ha fet un record.
     * <p>
     * Resultat esperat:
     * - Una llista de records buida amb en Pep a la posició de 4 boles amb mode ajuda i colors repetits desactivats i cronometre a 5 minuts.
     */
    @Test
    public void testAfegirJugadorCronoPle() {

        carregarRecordsPle();

        solucioRecords = records.getRecords();

        solucioRecords.remove(12);
        PosJugador pos = new PosJugador(10, "GOD", 10);
        solucioRecords.add(12 ,pos);

        int intents1 = 10;
        String player1 = "GOD";
        int victories1 = 10;

        records.afegirRecordCrono(intents1, player1, victories1, 4, false, false, 5);
        posRecords = records.getRecords();

        for (int i = 0; i < posRecords.size(); ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova com es veu una llista de records buida.
     * <p>
     * Casos de prova:
     * - Mostrar la llista de records buida.
     * <p>
     * Resultat esperat:
     * - La llista de records buida.
     */
   @Test
    public void testMostrarRankingBuit(){

       ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
       System.setOut(new PrintStream(sortidaTerminal));

       records.mostrarRecords();

       String[] liniesTerminal = sortidaTerminal.toString().split(System.lineSeparator());
       String[] liniesUtils = Arrays.copyOfRange(liniesTerminal, 0, liniesTerminal.length);
       String output = String.join(System.lineSeparator(), liniesUtils);

       solucioStringMostrar.add("MODE CLASSIC:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("MODE CRONO 5 minuts:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("MODE CRONO 10 minuts:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");

       String separator = System.lineSeparator();
       String resultat = String.join(separator, solucioStringMostrar);

       assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));

    }

    /**
     * Aquest test comprova com es veu una llista de records mig plena.
     * <p>
     * Casos de prova:
     * - Mostrar la llista de records mig plena.
     * <p>
     * Resultat esperat:
     * - La llista de records mig plena.
     */
   @Test
    public void testMostrarRecordsMigPle(){

       ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
       System.setOut(new PrintStream(sortidaTerminal));

       int[] temps = new int[]{10, 10};
       records.afegirRecordClassic(4, "Joel", temps, 4, false, false);
       temps = new int[]{6, 15};
       records.afegirRecordClassic(4, "Carlos", temps, 6, false, false);
       temps = new int[]{9, 21};
       records.afegirRecordClassic(4, "Rosa", temps, 4, true, false);
       temps = new int[]{12, 56};
       records.afegirRecordClassic(4, "Pep", temps, 8, true, true);

       records.afegirRecordCrono(23, "Joel", 4, 4, false, false, 5);
       records.afegirRecordCrono(12, "Pep", 3, 4, true, true, 5);
       records.afegirRecordCrono(34, "Susanna", 5, 6, true, false, 5);

       records.afegirRecordCrono(23, "Joel", 3, 4, false, false, 10);
       records.afegirRecordCrono(27, "Rosa", 3, 4, true, false, 10);


       records.mostrarRecords();

       String[] liniesTerminal = sortidaTerminal.toString().split(System.lineSeparator());
       String[] liniesUtils = Arrays.copyOfRange(liniesTerminal, 0, liniesTerminal.length);
       String output = String.join(System.lineSeparator(), liniesUtils);

       solucioStringMostrar.add("MODE CLASSIC:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Intents: 4, Temps: 10:10");
       solucioStringMostrar.add("NumBoles: 6 -> Player: Carlos, Intents: 4, Temps: 6:15");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Rosa, Intents: 4, Temps: 9:21");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Intents: 99, Temps: 99:99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: Pep, Intents: 4, Temps: 12:56");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("MODE CRONO 5 minuts:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Victories: 4, Intents: 23");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: Susanna, Victories: 5, Intents: 34");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Pep, Victories: 3, Intents: 12");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("MODE CRONO 10 minuts:");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Victories: 3, Intents: 23");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: Rosa, Victories: 3, Intents: 27");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("");
       solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
       solucioStringMostrar.add("NumBoles: 4 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 6 -> Player: EMPTY, Victories: -1, Intents: 99");
       solucioStringMostrar.add("NumBoles: 8 -> Player: EMPTY, Victories: -1, Intents: 99");


       String separator = System.lineSeparator();
       String resultat = String.join(separator, solucioStringMostrar);

       assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));
    }

    /**
     * Aquest test comprova com es veu la llista de rankings plena.
     * <p>
     * Casos de prova:
     * - Mostrar la llista de records plena.
     * <p>
     * Resultat esperat:
     * - La llista de records plena.
    */
     @Test
    public void testMostrarRecordsPle(){

        ByteArrayOutputStream sortidaTerminal = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sortidaTerminal));

        carregarRecordsPle();

        records.mostrarRecords();

        String[] liniesTerminal = sortidaTerminal.toString().split(System.lineSeparator());
        String[] liniesUtils = Arrays.copyOfRange(liniesTerminal, 0, liniesTerminal.length);
        String output = String.join(System.lineSeparator(), liniesUtils);

         solucioStringMostrar.add("MODE CLASSIC:");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Carlos, Intents: 2, Temps: 1:2");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Joel, Intents: 3, Temps: 17:24");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Rosa, Intents: 4, Temps: 17:54");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Carlos, Intents: 2, Temps: 12:22");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Joel, Intents: 2, Temps: 13:26");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Joel, Intents: 4, Temps: 34:28");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Intents: 3, Temps: 2:36");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Alba, Intents: 3, Temps: 14:23");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Alba, Intents: 3, Temps: 15:58");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Rosa, Intents: 3, Temps: 14:25");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Alba, Intents: 2, Temps: 16:27");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Alba, Intents: 2, Temps: 12:21");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("MODE CRONO 5 minuts:");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Nico, Victories: 12, Intents: 22");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Joel, Victories: 10, Intents: 31");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Rosa, Victories: 14, Intents: 21");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Carlos, Victories: 12, Intents: 19");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Joel, Victories: 10, Intents: 17");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Nico, Victories: 8, Intents: 18");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Victories: 11, Intents: 31");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Alba, Victories: 13, Intents: 24");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Joel, Victories: 6, Intents: 31");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Rosa, Victories: 11, Intents: 32");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Alba, Victories: 9, Intents: 16");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Nico, Victories: 9, Intents: 16");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("MODE CRONO 10 minuts:");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Joel, Victories: 21, Intents: 22");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Rosa, Victories: 17, Intents: 31");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Rosa, Victories: 13, Intents: 21");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Carlos, Victories: 22, Intents: 19");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Joel, Victories: 21, Intents: 17");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Joel, Victories: 9, Intents: 18");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Carlos, Victories: 20, Intents: 31");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Rosa, Victories: 19, Intents: 24");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Rosa, Victories: 10, Intents: 31");
         solucioStringMostrar.add("");
         solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
         solucioStringMostrar.add("NumBoles: 4 -> Player: Rosa, Victories: 19, Intents: 32");
         solucioStringMostrar.add("NumBoles: 6 -> Player: Carlos, Victories: 16, Intents: 16");
         solucioStringMostrar.add("NumBoles: 8 -> Player: Carlos, Victories: 9, Intents: 16");

        String separator = System.lineSeparator();
        String resultat = String.join(separator, solucioStringMostrar);

        assertEquals(resultat.replaceAll(System.lineSeparator(), "\n"), output.replaceAll(System.lineSeparator(), "\n"));
    }

    /**
     * Aquest test comprova que es tradueix una llista de records buida a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir una llista de records buida a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual a la llista de records buida.
     */
    @Test
    public void testGuardarRecordsBuit(){

        carregarSolucionsStringBuida();

        carregarRecordsBuit();
        records.setRecords(posRecords);

        ArrayList<String> resultatString = records.guardarRecords();

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix una llista de records mig plena a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir una llista de records mig plena a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual a la llista de records mig plena.
     */
    @Test
    public void testGuardarRecordsMigPlena(){

        carregarSolucionsStringBuida();

        solucioString.remove(0);
        solucioString.add(0, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO -> Player: Joel, Intents: 4, Temps: 10:10");
        solucioString.remove(1);
        solucioString.add(1, "NumBoles: 6, ModeAjuda: NO, ColorsRepetits: NO -> Player: Carlos, Intents: 4, Temps: 6:15");
        solucioString.remove(6);
        solucioString.add(6, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI -> Player: Rosa, Intents: 4, Temps: 9:21");
        solucioString.remove(11);
        solucioString.add(11, "NumBoles: 8, ModeAjuda: SI, ColorsRepetits: SI -> Player: Pep, Intents: 4, Temps: 12:56");
        solucioString.remove(12);
        solucioString.add(12, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: Joel, Victories: 4, Intents: 23");
        solucioString.remove(30);
        solucioString.add(30, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: Rosa, Victories: 3, Intents: 27");

        carregarRecordsBuit();

        int[] temps = new int[]{10, 10};
        records.afegirRecordClassic(4, "Joel", temps, 4, false, false);
        temps = new int[]{6, 15};
        records.afegirRecordClassic(4, "Carlos", temps, 6, false, false);
        temps = new int[]{9, 21};
        records.afegirRecordClassic(4, "Rosa", temps, 4, true, false);
        temps = new int[]{12, 56};
        records.afegirRecordClassic(4, "Pep", temps, 8, true, true);

        records.afegirRecordCrono(23, "Joel", 4, 4, false, false, 5);

        records.afegirRecordCrono(27, "Rosa", 3, 4, true, false, 10);
        records.setRecords(posRecords);

        ArrayList<String> resultatString = records.guardarRecords();

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix una llista de records plena a un ArrayList de strings.
     * <p>
     * Casos de prova:
     * - Traduir una llista de records plena a ArrayList de strings.
     * <p>
     * Resultat esperat:
     * - Un ArrayList igual a la llista de records plena.
     */
    @Test
    public void testGuardarRecordsPlena(){

        carregarRecordsPle();
        records.setRecords(posRecords);

        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO -> Player: Carlos, Intents: 2, Temps: 1:2");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: NO -> Player: Joel, Intents: 3, Temps: 17:24");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: NO -> Player: Rosa, Intents: 4, Temps: 17:54");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: NO -> Player: Carlos, Intents: 2, Temps: 12:22");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: NO -> Player: Joel, Intents: 2, Temps: 13:26");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: NO -> Player: Joel, Intents: 4, Temps: 34:28");
        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI -> Player: Joel, Intents: 3, Temps: 2:36");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: SI -> Player: Alba, Intents: 3, Temps: 14:23");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: SI -> Player: Alba, Intents: 3, Temps: 15:58");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: SI -> Player: Rosa, Intents: 3, Temps: 14:25");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: SI -> Player: Alba, Intents: 2, Temps: 16:27");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: SI -> Player: Alba, Intents: 2, Temps: 12:21");

        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: Nico, Victories: 12, Intents: 22");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: Joel, Victories: 10, Intents: 31");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: Rosa, Victories: 14, Intents: 21");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 5 -> Player: Carlos, Victories: 12, Intents: 19");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 5 -> Player: Joel, Victories: 10, Intents: 17");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 5 -> Player: Nico, Victories: 8, Intents: 18");
        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 5 -> Player: Joel, Victories: 11, Intents: 31");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 5 -> Player: Alba, Victories: 13, Intents: 24");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 5 -> Player: Joel, Victories: 6, Intents: 31");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 5 -> Player: Rosa, Victories: 11, Intents: 32");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 5 -> Player: Alba, Victories: 9, Intents: 16");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 5 -> Player: Nico, Victories: 9, Intents: 16");

        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 10 -> Player: Joel, Victories: 21, Intents: 22");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 10 -> Player: Rosa, Victories: 17, Intents: 31");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 10 -> Player: Rosa, Victories: 13, Intents: 21");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 10 -> Player: Carlos, Victories: 22, Intents: 19");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 10 -> Player: Joel, Victories: 21, Intents: 17");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 10 -> Player: Joel, Victories: 9, Intents: 18");
        solucioString.add("NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: Carlos, Victories: 20, Intents: 31");
        solucioString.add("NumBoles: 6, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: Rosa, Victories: 19, Intents: 24");
        solucioString.add("NumBoles: 8, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: Rosa, Victories: 10, Intents: 31");
        solucioString.add("NumBoles: 4, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 10 -> Player: Rosa, Victories: 19, Intents: 32");
        solucioString.add("NumBoles: 6, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 10 -> Player: Carlos, Victories: 16, Intents: 16");
        solucioString.add("NumBoles: 8, ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 10 -> Player: Carlos, Victories: 9, Intents: 16");

        ArrayList<String> resultatString = records.guardarRecords();

        for (int i = 0; i < resultatString.size(); ++i){
            assertEquals(resultatString.get(i), solucioString.get(i));
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings a una llista de records buida.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings a una llista de records buida.
     * <p>
     * Resultat esperat:
     * - Una llista de records igual al ArrayList de strings.
     */
    @Test
    public void testCarregaRecordsBuits(){

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }

        records.carregarRecords(solucioString);
        posRecords = records.getRecords();

        carregarRecordsBuit();
        solucioRecords = records.getRecords();


        for (int i = 0; i < 12; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertArrayEquals(posRecords.get(i).getTemps(), solucioRecords.get(i).getTemps());
        }

        for (int i = 12; i < 36; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings a una llista de records mig plena.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings a una llista de records mig plena.
     * <p>
     * Resultat esperat:
     * - Una llista de records igual al ArrayList de strings.
     */
    @Test
    public void testCarregaRecordsMigPlena(){

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
        }

        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }
        for (int j = 4; j <= 8; j += 2) {
            solucioString.add("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
        }

        solucioString.remove(0);
        solucioString.add(0, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO -> Player: Joel, Intents: 4, Temps: 10:10");
        solucioString.remove(1);
        solucioString.add(1, "NumBoles: 6, ModeAjuda: NO, ColorsRepetits: NO -> Player: Carlos, Intents: 4, Temps: 6:15");
        solucioString.remove(6);
        solucioString.add(6, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI -> Player: Rosa, Intents: 4, Temps: 9:21");
        solucioString.remove(11);
        solucioString.add(11, "NumBoles: 8, ModeAjuda: SI, ColorsRepetits: SI -> Player: Pep, Intents: 4, Temps: 12:56");
        solucioString.remove(12);
        solucioString.add(12, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> Player: Joel, Victories: 4, Intents: 23");
        solucioString.remove(30);
        solucioString.add(30, "NumBoles: 4, ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> Player: Rosa, Victories: 3, Intents: 27");

        records.carregarRecords(solucioString);
        posRecords = records.getRecords();

        carregarRecordsBuit();
        int[] temps = new int[]{10, 10};
        records.afegirRecordClassic(4, "Joel", temps, 4, false, false);
        temps = new int[]{6, 15};
        records.afegirRecordClassic(4, "Carlos", temps, 6, false, false);
        temps = new int[]{9, 21};
        records.afegirRecordClassic(4, "Rosa", temps, 4, true, false);
        temps = new int[]{12, 56};
        records.afegirRecordClassic(4, "Pep", temps, 8, true, true);

        records.afegirRecordCrono(23, "Joel", 4, 4, false, false, 5);

        records.afegirRecordCrono(27, "Rosa", 3, 4, true, false, 10);
        solucioRecords = records.getRecords();

        for (int i = 0; i < 12; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertArrayEquals(posRecords.get(i).getTemps(), solucioRecords.get(i).getTemps());
        }

        for (int i = 12; i < 36; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova que es tradueix un ArrayList de strings a una llista de records plena.
     * <p>
     * Casos de prova:
     * - Traduir un ArrayList de strings a una llista de records plena.
     * <p>
     * Resultat esperat:
     * - Una llista de records igual al ArrayList de strings.
     */
    @Test
    public void testCarregaRecordsPlena(){

        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Carlos, Intents: 2, Temps: 1:2");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Joel, Intents: 3, Temps: 17:24");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Rosa, Intents: 4, Temps: 17:54");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Carlos, Intents: 2, Temps: 12:22");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Joel, Intents: 2, Temps: 13:26");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Joel, Intents: 4, Temps: 34:28");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Joel, Intents: 3, Temps: 2:36");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Alba, Intents: 3, Temps: 14:23");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Alba, Intents: 3, Temps: 15:58");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Rosa, Intents: 3, Temps: 14:25");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Alba, Intents: 2, Temps: 16:27");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0 -> Player: Alba, Intents: 2, Temps: 12:21");

        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Nico, Victories: 12, Intents: 22");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Joel, Victories: 10, Intents: 31");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Rosa, Victories: 14, Intents: 21");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Carlos, Victories: 12, Intents: 19");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Joel, Victories: 10, Intents: 17");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Nico, Victories: 8, Intents: 18");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Joel, Victories: 11, Intents: 31");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Alba, Victories: 13, Intents: 24");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Joel, Victories: 6, Intents: 31");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Rosa, Victories: 11, Intents: 32");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Alba, Victories: 9, Intents: 16");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: Nico, Victories: 9, Intents: 16");

        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Joel, Victories: 21, Intents: 22");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Rosa, Victories: 17, Intents: 31");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Rosa, Victories: 13, Intents: 21");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Carlos, Victories: 22, Intents: 19");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Joel, Victories: 21, Intents: 17");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Joel, Victories: 9, Intents: 18");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Carlos, Victories: 20, Intents: 31");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Rosa, Victories: 19, Intents: 24");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Rosa, Victories: 10, Intents: 31");
        solucioString.add("NumBoles: 4, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Rosa, Victories: 19, Intents: 32");
        solucioString.add("NumBoles: 6, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Carlos, Victories: 16, Intents: 16");
        solucioString.add("NumBoles: 8, ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: Carlos, Victories: 9, Intents: 16");

        records.carregarRecords(solucioString);
        posRecords = records.getRecords();

        carregarRecordsPle();

        solucioRecords = records.getRecords();


        for (int i = 0; i < 12; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertArrayEquals(posRecords.get(i).getTemps(), solucioRecords.get(i).getTemps());
        }

        for (int i = 12; i < 36; ++i) {
            assertEquals(posRecords.get(i).getIntents(), solucioRecords.get(i).getIntents());
            assertEquals(posRecords.get(i).getPlayer(), solucioRecords.get(i).getPlayer());
            assertEquals(posRecords.get(i).getVictories(), solucioRecords.get(i).getVictories());
        }
    }

    /**
     * Aquest test comprova com es veu la llista de records buida des de la presentació.
     * <p>
     * Casos de prova:
     * - Mostrar la llista de records buida.
     * <p>
     * Resultat esperat:
     * - La llista de records buida.
     */
    @Test
    public void testGetRecordsPresentacioBuits(){
        carregarRecordsBuit();

        ArrayList<String> matRanking = records.getRecordsPresentacio();

        solucioStringMostrar.add("MODE CLASSIC:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("MODE CRONO 5 minuts:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("MODE CRONO 10 minuts:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioStringMostrar.get(i), matRanking.get(i));
        }
    }

    /**
     * Aquest test comprova com es veu la llista de records mig plena des de la presentació.
     * <p>
     * Casos de prova:
     * - Mostrar la llista de records mig plena.
     * <p>
     * Resultat esperat:
     * - La llista de records mig plena.
     */
    @Test
    public void testGetRecordsPresentacioMigPlena(){
        carregarRecordsBuit();

        int[] temps = new int[]{10, 10};
        records.afegirRecordClassic(4, "Joel", temps, 4, false, false);
        temps = new int[]{6, 15};
        records.afegirRecordClassic(4, "Carlos", temps, 6, false, false);
        temps = new int[]{9, 21};
        records.afegirRecordClassic(4, "Rosa", temps, 4, true, false);
        temps = new int[]{12, 56};
        records.afegirRecordClassic(4, "Pep", temps, 8, true, true);

        records.afegirRecordCrono(23, "Joel", 4, 4, false, false, 5);
        records.afegirRecordCrono(12, "Pep", 3, 4, true, true, 5);
        records.afegirRecordCrono(34, "Susanna", 5, 6, true, false, 5);

        records.afegirRecordCrono(23, "Joel", 3, 4, false, false, 10);
        records.afegirRecordCrono(27, "Rosa", 3, 4, true, false, 10);

        ArrayList<String> matRanking = records.getRecordsPresentacio();

        solucioStringMostrar.add("MODE CLASSIC:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Joel, Intents: 4, Temps: 10:10");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: Carlos, Intents: 4, Temps: 6:15");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Rosa, Intents: 4, Temps: 9:21");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Intents: --- , Temps: --- : --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: Pep, Intents: 4, Temps: 12:56");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("MODE CRONO 5 minuts:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Joel, Victories: 4, Intents: 23");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: Susanna, Victories: 5, Intents: 34");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Pep, Victories: 3, Intents: 12");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("MODE CRONO 10 minuts:");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Joel, Victories: 3, Intents: 23");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: NO, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: NO:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: Rosa, Victories: 3, Intents: 27");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add(" ");
        solucioStringMostrar.add("ModeAjuda: SI, ColorsRepetits: SI:");
        solucioStringMostrar.add("Nombre de boles: 4 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 6 -> Player: --------- , Victories: --- , Intents: --- ");
        solucioStringMostrar.add("Nombre de boles: 8 -> Player: --------- , Victories: --- , Intents: --- ");

        for (int i = 0; i < matRanking.size(); ++i){
            assertEquals(solucioStringMostrar.get(i), matRanking.get(i));
        }
    }
}
