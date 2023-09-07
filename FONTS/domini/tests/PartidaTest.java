package domini.tests;

import domini.exceptions.*;
import domini.model.Classic;
import domini.model.CorreccioLinea;
import domini.model.Crono;
import domini.model.Patro;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;


public class PartidaTest {

    /**
     * CHECK INTENT
     * <p>
     * Aquest test comprova que el salt per l'excepció de NoSolucioDisponible, funciona correctament
     * <p>
     * Casos de prova:
     * - Crear un intent
     * - Demanar correcció sobre l'intent
     * <p>
     * Resultat esperat:
     * -Com que no tenim una solució en la Partida, que salti l'excepcio de NoSolucioDisponible
     */
    @Test(expected = NoSolucioDisponible.class)
    public void testCheckIntentExcepcio() throws Exception{
        Classic c = new Classic(5, false, false);
        int[] intentV = new int[] {1, 2, 3, 4, 5};
        Patro intent = new Patro(intentV);

        c.checkIntent(intent);
    }

    /**
     * Funció per evitar repetir codi, diu si la correcció passada per paràmetre i la calculada per 'checkIntent' són iguals.
     *
     * @param intentV és un vector d'enters que representen l'intent actual de l'Usuari
     * @param solucioV és un vector d'enters que representen la solució de la Partida
     * @param x es un vector de enters que representa la correcció correcta que ha de crear la funció
     * @return un booleà que indica si la correcció creada per la funció es correcta
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi
     */
    private boolean checkIntentFuncio(int[] intentV, int[] solucioV, int[] x) throws Exception{
        Classic c = new Classic(5, true, false);

        Patro solucio = new Patro(solucioV);
        c.passarSolucio(solucio);

        Patro intent = new Patro(intentV);
        c.checkIntent(intent);

        CorreccioLinea aux = c.getCorreccioActual();
        int[] auxV = aux.getCorreccioLinea();

        System.out.print(Arrays.toString(auxV));
        return Arrays.equals(auxV, x);
    }

    /**
     * Aquest test comprova el cas de durant l'intent és igual a la solució
     * <p>
     * Casos de prova:
     * - Crear una solució
     * - Crear un intent amb la mateixa seqüència que la solució
     * - Crear la correcció correcta
     * <p>
     * Resultat esperat:
     * - Que la funció checkIntentFuncio retorni 'true', és a dir, que la correcció creada ha sigut com s'esperava
     */
    @Test
    public void testCheckIntentSolucio() throws Exception{
        int[] intentV = new int[] {1, 2, 3, 4, 5};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};
        int[] x = new int[] {2, 2, 2, 2, 2};

        assertTrue(checkIntentFuncio(intentV, solucioV, x));
    }

    /**
     * Aquest test comprova el cas de quan l'intent sigui parcialment com la solució
     * <p>
     * Casos de prova:
     * - Crear una solució
     * - Crear un intent amb la mateixa seqüència que la solució
     * - Crear la correcció correcta
     * <p>
     * Resultat esperat:
     * - Que la funció checkIntentFuncio retorni 'true', és a dir, que la correcció creada ha sigut com s'esperava
     */
    @Test
    public void testCheckIntent_intentFallit() throws Exception{
        int[] intentV = new int[] {1, 6, 3, 7, 5};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};
        int[] x = new int[] {2, 0, 2, 0, 2};

        assertTrue(checkIntentFuncio(intentV, solucioV, x));
    }

    /**
     * Aquest test comprova el cas de quan l'intent és igual a la solució però està mal ordenat
     * <p>
     * Casos de prova:
     * - Crear una solució
     * - Crear un intent amb la mateixa seqüència que la solució
     * - Crear la correcció correcta
     * <p>
     * Resultat esperat:
     * - Que la funció checkIntentFuncio retorni 'true', és a dir, que la correcció creada ha sigut com s'esperava
     */
    @Test
    public void testCheckIntent_intentDesordenat() throws Exception{
        int[] intentV = new int[] {2, 1, 4, 5, 3};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};
        int[] x = new int[] {1, 1, 1, 1, 1};

        assertTrue(checkIntentFuncio(intentV, solucioV, x));
    }

    /**
     * CHECK CORRECCIO
     * <p>
     * Funció per evitar repetir codi, retorna el valor de la funció 'checkCorreccio', que diu si la correcció està ben feta
     *
     * @param intentV és un vector d'enters que representa l'intent actual de l'usuari
     * @param solucioV és un vector d'enters que representa la solució de la Partida
     * @param correccioV és un vector d'enters que representa la correcció feta per l'Usuari
     * @return un boolea que indica si la correcció 'correccioV' està ben feta o no
     * @throws CorreccioIncorrecte en cas que la correcció no sigui correcte
     * @throws NoIntentDisponible en cas que no existeixi cap intent
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció
     */
    private boolean checkCorreccioFuncio(int[] intentV, int[] solucioV, int[] correccioV) throws Exception{
        Classic c = new Classic(5, true, false);

        Patro intent = new Patro(intentV);
        c.passarIntent(intent);

        Patro solucio = new Patro(solucioV);
        c.passarSolucio(solucio);

        CorreccioLinea correccio = new CorreccioLinea(5, false, correccioV);

        return c.checkCorreccio(correccio);
    }

    /**
     * Test per comprovar que checkCorreccio fa bé el cas on la correcció està ben feta
     * <p>
     * Casos de prova:
     * -Crear una solució
     * -Crear un intent
     * -Crear una correcció correcta sobre l'intent respecte a la solució
     * <p>
     * Resultat esperat:
     * -Que la funció 'checkCorreccioFuncio' retorni 'true' és a dir que digui que la correcció sigui bona
     */
    @Test
    public void testCheckCorreccioCorrecte() throws Exception{
        int[] intentV = new int[] {2, 1, 4, 5, 3};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};
        int[] correccioV = new int[] {1, 1, 1, 1, 1};

        assertTrue(checkCorreccioFuncio(intentV, solucioV, correccioV));
    }

    /**
     * Test per comprovar que checkCorreccio fa bé el cas on la correcció no està ben feta
     * <p>
     * Casos de prova:
     * -Crear una solució
     * -Crear un intent
     * -Crear una correcció errònia sobre l'intent respecte a la solució
     * <p>
     * Resultat esperat:
     * -Que la funció 'checkCorreccioFuncio' faci saltar l'excepció 'CorreccioIncorrecte'
     */
    @Test(expected = CorreccioIncorrecte.class)
    public void testCheckCorreccioMalament() throws Exception{
        int[] intentV = new int[] {2, 1, 4, 5, 3};
        int[] solucioV = new int[] {1, 2, 3, 4, 5};
        int[] correccioV = new int[] {1, 1, 2, 1, 1};

        checkCorreccioFuncio(intentV, solucioV, correccioV);

    }

    /**
     * GET INTENT ACTUAL
     * <p>
     * Funció per evitar repeticions de codi, retorna l'intent del torn actual
     *
     * @param mat és un ArrayList de vectors d'enters que representen una seqüència d'intents
     * @param nTorn es un enter que representa el nombre de torns que hem superat
     * @return és un vector d'enter que representa l'intent del torn actual
     * @throws NoIntentDisponible en cas de que encara no existeixi cap intent
     */
    private int[] intentActualFuncio(ArrayList<int[]> mat, int nTorn) throws Exception{
        Classic c = new Classic(5, false, false);


        for (int i = 0; i < nTorn+1; ++i) {
            Patro intent = new Patro(mat.get(i));
            c.passarIntent(intent);
        }

        Patro aux = c.getIntentActual();
        return aux.getPatro();

        //System.out.print(Arrays.toString(x));
    }

    /**
     * Test per confirmar que la funció agafa el patró correcte quan no ha passat cap torn
     * <p>
     * Casos de prova:
     * - Crear un intent
     * - Afegir l'intent a un ArrayList
     * - Indiquem que no volem que passi cap torn
     * <p>
     * Resultat esperat:
     * - Que la funció 'intentActualFuncio' retorni aquest mateix intent
     */
    @Test
    public void testIntentActual() throws Exception{
        int[] intentV = new int[] {2, 1, 4, 5, 3};

        ArrayList<int[]> mat = new ArrayList<>(1);
        mat.add(intentV);
        assert(Arrays.equals(intentActualFuncio(mat, 0), intentV));
        //nTorn -> quants nou torns volem que faci?
    }

    /**
     * Test per confirmar que la funció agafa el patró correcte quan ha passat molts torns
     * <p>
     * Casos de prova:
     * - Crear 3 intents
     * - Afegir els intents a un ArrayList
     * - Indiquem que volem que passin 2 torns
     * <p>
     * Resultat esperat:
     * - Que la funció 'intentActualFuncio' retorni l'intent més recent
     */
    @Test
    public void testIntentActualMoltesRondes() throws Exception{
        int[] intentV = new int[] {2, 1, 4, 5, 3};
        int[] intentV1 = new int[] {7, 1, 2, 5, 3};
        int[] intentV2 = new int[] {3, 2, 1, 5, 4};

        ArrayList<int[]> mat = new ArrayList<>(3);
        mat.add(intentV);
        mat.add(intentV1);
        mat.add(intentV2);

        assert(Arrays.equals(intentActualFuncio(mat, 2), intentV2));
    }

    /**
     * Test per confirmar que la funció fa saltar l'excepció 'NoIntentDisponible'
     * <p>
     * Casos de prova:
     * - Crear un ArrayList
     * - Indiquem que no volem que entri al bucle
     * <p>
     * Resultat esperat:
     * - Que la funció 'intentActualFuncio' faci saltar l'excepció 'NoIntentDisponible'
     */
    @Test(expected = NoIntentDisponible.class)
    public void getIntentInexistent() throws Exception{
        ArrayList<int[]> mat = new ArrayList<>(1);
        intentActualFuncio(mat, -1);
    }

    /**
     * GET CORRECCIÓ ACTUAL
     * <p>
     * Funció per evitar codi repetit, retorna la correcció del torn actual
     *
     * @param mat és un ArrayList de vectors d'enters que representa el conjunt de les correccions de la Partida
     * @param nTorn és un enter que representa quants torns han passat
     * @return un vector d'enters que representa la correcció actual
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció
     */
    private int[] correccioActualFuncio(ArrayList<int[]> mat, int nTorn) throws Exception{
        Classic c = new Classic(5, false, false);

        for (int i = 0; i < nTorn+1; ++i){
            CorreccioLinea correccio = new CorreccioLinea(mat.get(i));
            c.passarCorreccio(correccio);
        }

        CorreccioLinea aux = c.getCorreccioActual();
        return aux.getCorreccioLinea();
    }

    /**
     * Test per comprovar que salta l'excepció 'NoCorreccioDisponible'
     * <p>
     * Casos de prova:
     * - Crear l'ArrayList de vectors d'enters
     * -indicar que no volem que entri al bucle
     * <p>
     * Resultat esperat:
     * - Que salti l'excepció 'NoCorreccioDIsponible'
     */
    @Test(expected = NoCorreccioDisponible.class)
    public void getCorreccioInexistent() throws Exception{
        ArrayList<int[]> mat = new ArrayList<>(1);
        correccioActualFuncio(mat, -1);
        //nTorn = -1, pq així no entri al bucle i no doni error per la matriu
    }

    /**
     * Test per veure que funciona bé passant una correcció, quan no es passen torns
     * <p>
     * Casos de prova:
     * - Crear una correcció
     * - Afegir-la l'ArrayList
     * -Indicar que no volem que passin torns
     * <p>
     * Resultat esperat:
     * - Obtenir la mateixa correcció
     */
    @Test
    public void getCorreccioActualTest() throws Exception{
        int[] correccioV = {1, 1, 1, 1, 1};

        ArrayList<int[]> mat = new ArrayList<>(1);
        mat.add(correccioV);

        assertArrayEquals(correccioActualFuncio(mat, 0), correccioV);
    }

    /**
     * Test per veure que funciona bé passant una correcció, quan es passa 1 torn
     * <p>
     * Casos de prova:
     * - Crear 2 correccions
     * - Afegir-les a l'ArrayList
     * -Indicar que volem que passi 1 torn
     * <p>
     * Resultat esperat:
     * - Obtenir la correcció actual
     */
    @Test
    public void getCorreccioMoltesDespres() throws Exception{
        int[] correccioV = {1, 1, 1, 1, 1};
        int[] correccio1V = {1, 0, 0, 1, 1};

        ArrayList<int[]> mat = new ArrayList<>(2);
        mat.add(correccioV);
        mat.add(correccio1V);

        assertArrayEquals(correccio1V, correccioActualFuncio(mat, 1));
    }


    /**
     * INTENT TO STRING
     * <p>
     * FunciÓ per evitar codi repetit, retorna un booleà en funció de si el string que retorna la funció 'intentToString' és el esperat
     *
     * @param sol el String esperat
     * @param intentV el Patró que volem passar a String
     * @param numB el nombre de boles del String
     * @return un booleà que representa si l'intent passat a String i el String esperats són iguals
     * @throws PosicioPatroNoDisponible en el cas de que es demani una posició no existent
     */
    private boolean intentToStringFuncio(String sol, int[] intentV, int numB) throws Exception{
        Crono c = new Crono(numB, false, false);
        Patro intent = new Patro(intentV);

        System.out.print(c.intentToString(intent));
        return Objects.equals(c.intentToString(intent), sol);
    }

    /**
     * Test per veure que intentToString fa bé la conversió d'intent a String, amb 4 boles
     * <p>
     * Casos de prova:
     * - Crear un Patró de 4 unitats
     * - Crear un String amb el Patró en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'intentToStringFuncio'
     */
    @Test
    public void intentToString4Boles() throws Exception{
        int[] intentV = {1, 2, 3, 4};
        String s = "1-2-3-4-9-9-9-9";

        assertTrue(intentToStringFuncio(s, intentV, 4));
    }

    /**
     * Test per veure que intentToString fa bé la conversió d'intent a String, amb 6 boles
     * <p>
     * Casos de prova:
     * - Crear un Patró de 6 unitats
     * - Crear un String amb el Patró en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'intentToStringFuncio'
     */
    @Test
    public void intentToString6Boles() throws Exception{
        int[] intentV = {1, 2, 3, 4, 5, 6};
        String s = "1-2-3-4-5-6-9-9";

        assertTrue(intentToStringFuncio(s, intentV, 6));
    }

    /**
     * Test per veure que intentToString fa bé la conversió d'intent a String, amb 8 boles
     * <p>
     * Casos de prova:
     * - Crear un Patró de 8 unitats
     * - Crear un String amb el Patró en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'intentToStringFuncio'
     */
    @Test
    public void intentToString8Boles() throws Exception{
        int[] intentV = {1, 2, 3, 4, 5, 6, 7, 8};
        String s = "1-2-3-4-5-6-7-8";

        assertTrue(intentToStringFuncio(s, intentV, 8));
    }

    /**
     * CORRECCIO TO STRING
     * <p>
     * Funcio per evitar codi repetit, retorna un booleà en funció de si el string que retorna la funció 'correccioToString' és el esperat
     *
     * @param sol el String esperat
     * @param correccioV la CorreccioLinea que volem passar a String
     * @param numBoles el nombre de boles del String
     * @return un booleà que representa si la correcció passada a String i el String esperats són iguals
     * @throws PosicioPatroNoDisponible en el cas de que es demani una posició no existent
     */
    private boolean correccioToStringFunction(int[] correccioV, String sol, int numBoles) throws Exception{
        Classic c = new Classic(numBoles, false, false);

        CorreccioLinea correccio = new CorreccioLinea(numBoles, false, correccioV);

        boolean b = Objects.equals(c.correccioToString(correccio), sol);
        System.out.print(c.correccioToString(correccio));
        return b;
    }

    /**
     * Test per veure que intentToString fa bé la conversió de correcció a String, amb 4 boles
     * <p>
     * Casos de prova:
     * - Crear una CorreccioLinea de 4 unitats
     * - Crear un String amb la CorreccioLinea en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'correccioToStringFuncio'
     */
    @Test
    public void correccioToString4Boles() throws Exception{
        int[] correccioV = {1, 1, 1, 1};
        String s = "1-1-1-1-9-9-9-9";

        assertTrue(correccioToStringFunction(correccioV, s, 4));
    }

    /**
     * Test per veure que intentToString fa bé la conversió de correcció a String, amb 6 boles
     * <p>
     * Casos de prova:
     * - Crear una CorreccioLinea de 6 unitats
     * - Crear un String amb la CorreccioLinea en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'correccioToStringFuncio'
     */
    @Test
    public void correccioToString6Boles() throws Exception{
        int[] correccioV = {1, 1, 1, 1, 2, 2};
        String s = "1-1-1-1-2-2-9-9";

        assertTrue(correccioToStringFunction(correccioV, s, 6));
    }

    /**
     * Test per veure que intentToString fa bé la conversió de correccio a String, amb 8 boles
     * <p>
     * Casos de prova:
     * - Crear una CorreccioLinea de 8 unitats
     * - Crear un String amb la CorreccioLinea en forma de String esperat
     * <p>
     * Resultat esperat:
     * - Que el String esperat sigui igual al retornat per la funció 'correccioToStringFuncio'
     */
    @Test
    public void correccioToString8Boles() throws Exception{
        int[] correccioV = {1, 1, 1, 1, 2, 2, 2, 2};
        String s = "1-1-1-1-2-2-2-2";

        assertTrue(correccioToStringFunction(correccioV, s, 8));
    }

    /**
     * GUARDAR PARTIDA
     * <p>
     *Aquest mètode és per estàlvia repetició de codi, fa la funció de cridar a la funció 'guardarPartida'
     *
     * @param numB representa el nombre de boles que hi ha
     * @param intentV representa l'intent de la partida, que és el mateix tota la Partida per simplificar
     * @param nInt representa el nombre d'intents que hem fet
     * @param correccioV representa la correccioV, que és el mateix tota la Partida per simplificar
     * @return una ArrayList que representa la Partida guardar
     * @throws PosicioPatroNoDisponible en cas de que s'accedeixi a una posició del intent no disponible
     * @throws PosicioCorreccioLineaNoDisponible en cas de que s'accedeixi a una posició no disponible
     */
    private ArrayList<String> guardarPartidaFunction(int numB, int[] intentV, int nInt, int[] correccioV, int[] solucioV, int[] temps) throws Exception{
        Classic c = new Classic(numB, false, false);
        //creem una partida

        c.setTemps(temps);

        Patro solucio = new Patro(solucioV);
        c.passarSolucio(solucio);

        Patro intent = new Patro(intentV);
        //passem l'intent a Patró
        for (int i = 0; i < nInt; ++i){
            c.passarIntent(intent); //passem l'intent a la llista per passar-ho
        }

        CorreccioLinea correccio = new CorreccioLinea(numB, false, correccioV);
        for (int i = 0; i < nInt; ++i){
            c.passarCorreccio(correccio);
        }

        imprimirArrayStrings(c.guardarPartida());
        return c.guardarPartida();
    }

    /**
     * Aquest mètode serveix per imprimir l'ArrayList, per facilitar el debug dels errors de tests
     *
     * @param s representa la Partida passada en ArrayList que volem imprimir
     */
    private void imprimirArrayStrings(ArrayList<String> s){
        //imprimir el string separat, maybe fa falta passar la partida too per confirmar?
        for (int i = 0; i < s.size(); ++i){
            System.out.print(s.get(i));
            if (i < s.size()-1) System.out.print("\n");
        }
    }

    /**
     * Test per comprovar que 'guardarPartida' guarda bé una Partida amb 9 intents i 4 boles
     * <p>
     * Casos de prova:
     * - declarem el nombre de boles que juguem
     * - creem l'intent que repetirem per omplir la llista d'intents
     * - creem la correcció que repetirem per omplir la llista de correccions
     * - declarem el nombre d'intents que tindrà la nostra partida
     * <p>
     * Resultat esperat:
     * - La comparació entre la nostra Partida i la Partida ja feta en String siguin iguals
     */
    @Test
    public void guardarPartidaTotsIntents() throws Exception{
        int numB = 4;
        int[] intentV = {1, 1, 0, 4};
        int[] correccioV = {1, 2, 1, 2};
        int[] solucioV = {1, 2, 3, 4};
        int nInt = 9;
        int[] temps = new int[] {10, 21};

        assertEquals(guardarPartidaFunction(numB, intentV, nInt, correccioV, solucioV, temps), partidaEnStringCompleta());
    }

    /**
     * Test per comprovar que 'guardarPartida' guarda bé una Partida amb 9 intents i 8 boles
     * <p>
     * Casos de prova:
     * - declarem el nombre de boles que juguem
     * - creem l'intent que repetirem per omplir la llista d'intents
     * - creem la correcció que repetirem per omplir la llista de correccions
     * - declarem el nombre d'intents que tindrà la nostra partida
     * <p>
     * Resultat esperat:
     * - La comparació entre la nostra Partida i la Partida ja feta en String siguin iguals
     */
    @Test
    public void guardarPartida8Boles() throws Exception{
        int numB = 8;
        int[] intentV = {1, 8, 0, 4, 2, 3, 5, 6};
        int[] correccioV = {1, 2, 0, 1, 1, 1, 1, 1};
        int[] solucioV = {1, 2, 3, 4, 5, 6, 7, 8};
        int nInt = 9;
        int[] temps = new int[] {10, 21};

        assertEquals(guardarPartidaFunction(numB, intentV, nInt, correccioV, solucioV, temps), partidaEnString8Boles());
    }

    /**
     * Test per comprovar que 'guardarPartida' guarda bé una Partida amb 5 intents
     * <p>
     * Casos de prova:
     * - declarem el nombre de boles que juguem
     * - creem l'intent que repetirem per omplir la llista d'intents
     * - creem la correcció que repetirem per omplir la llista de correccions
     * - declarem el nombre d'intents que tindrà la nostra partida
     * <p>
     * Resultat esperat:
     * - La comparació entre la nostra Partida i la Partida ja feta en String siguin iguals
     */
    @Test
    public void guardarPartidaMeitatIntents() throws Exception{
        int numB = 8;
        int[] intentV = {1, 8, 0, 4, 2, 3, 5, 6};
        int[] correccioV = {1, 2, 0, 1, 1, 1, 1, 1};
        int[] solucioV = {1, 2, 3, 4, 5, 6, 7, 8};
        int nInt = 5;
        int[] temps = new int[] {10, 21};

        assertEquals(guardarPartidaFunction(numB, intentV, nInt, correccioV, solucioV, temps), partidaMeitatIntent());
    }

    /**
     * Test per comprovar que 'guardarPartida' guarda bé una Partida amb 5 intents i un temps de Partida menor a 10 minuts
     * <p>
     * Casos de prova:
     * - declarem el nombre de boles que juguem
     * - creem l'intent que repetirem per omplir la llista d'intents
     * - creem la correcció que repetirem per omplir la llista de correccions
     * - declarem el nombre d'intents que tindrà la nostra partida
     * <p>
     * Resultat esperat:
     * - La comparació entre la nostra Partida i la Partida ja feta en String siguin iguals
     */
    @Test
    public void guardarPartidaPocTemps() throws Exception{
        int numB = 8;
        int[] intentV = {1, 8, 0, 4, 2, 3, 5, 6};
        int[] correccioV = {1, 2, 0, 1, 1, 1, 1, 1};
        int[] solucioV = {1, 2, 3, 4, 5, 6, 7, 8};
        int nInt = 5;
        int[] temps = new int[] {9, 9};
        assertEquals(guardarPartidaFunction(numB, intentV, nInt, correccioV, solucioV, temps), partidaPocTemps());
    }

    /**
     * Aquest mètode crea una de les partides per poder comparar amb les creades per funcions
     *
     * @return un ArrayList<String> que representa una Partida de 4 boles, i tots els intents utilitzats
     * en forma de String
     */
    private ArrayList<String> partidaEnStringCompleta(){
        ArrayList<String> s = new ArrayList<>(21);
        s.add(0, "1");
        s.add(1,"numBoles: 4, modeAjuda: 0, colorsRepetits: 0, intents: 9, temps: 10:21");

        //solució:
        s.add(2, "1-2-3-4-9-9-9-9");

        //intents:
        for (int i = 3; i <= 11; ++i) s.add(i, "1-1-0-4-9-9-9-9");

        //correccions:
        for (int j = 12; j <= 20; ++j)s.add(j, "1-2-1-2-9-9-9-9");

        return s;
    }

    /**
     * Aquest mètode crea una de les partides per poder comparar amb les creades per funcions
     *
     * @return un ArrayList<String> que representa una Partida de 8 boles, i tots els intents utilitzats
     * en forma de String
     */
    private ArrayList<String> partidaEnString8Boles(){
        ArrayList<String> s = new ArrayList<>(21);
        s.add(0, "1");
        s.add(1,"numBoles: 8, modeAjuda: 0, colorsRepetits: 0, intents: 9, temps: 10:21");
        //solucio:
        s.add(2, "1-2-3-4-5-6-7-8");

        //intents:
        for (int i = 3; i <= 11; ++i) s.add(i, "1-8-0-4-2-3-5-6");

        //correccions:
        for (int j = 12; j <= 20; ++j) s.add(j, "1-2-0-1-1-1-1-1");

        return s;
    }

    /**
     * Aquest mètode crea una de les partides per poder comparar amb les creades per funcions
     *
     * @return un ArrayList<String> que representa una Partida de 8 boles, i la meitat dels intents utilitzats
     * en forma de String
     */
    private ArrayList<String> partidaMeitatIntent(){
        ArrayList<String> s = new ArrayList<>(21);
        s.add(0, "1");
        s.add(1,"numBoles: 8, modeAjuda: 0, colorsRepetits: 0, intents: 5, temps: 10:21");

        //solució:
        s.add(2, "1-2-3-4-5-6-7-8");

        //intents:
        for (int i = 3; i <= 11; ++i){
            if (i < 8)s.add(i, "1-8-0-4-2-3-5-6");
            else s.add(i, "9-9-9-9-9-9-9-9");
        }

        //correccions:
        for (int j = 12; j <= 20; ++j){
            if (j < 17) s.add(j, "1-2-0-1-1-1-1-1");
            else s.add(j, "9-9-9-9-9-9-9-9");
        }

        return s;
    }

    /**
     * Aquest mètode crea una de les partides per poder comparar amb les creades per funcions
     *
     * @return un ArrayList<String> que representa una Partida de 8 boles, i la meitat dels intents utilitzats
     * en forma de String i un temps menor a 10 minuts
     */
    private ArrayList<String> partidaPocTemps(){
        ArrayList<String> s = new ArrayList<>(21);
        s.add(0, "1");
        s.add(1,"numBoles: 8, modeAjuda: 0, colorsRepetits: 0, intents: 5, temps: 09:09");

        //solució:
        s.add(2, "1-2-3-4-5-6-7-8");

        //intents:
        for (int i = 3; i <= 11; ++i){
            if (i < 8)s.add(i, "1-8-0-4-2-3-5-6");
            else s.add(i, "9-9-9-9-9-9-9-9");
        }

        //correccions:
        for (int j = 12; j <= 20; ++j){
            if (j < 17) s.add(j, "1-2-0-1-1-1-1-1");
            else s.add(j, "9-9-9-9-9-9-9-9");
        }

        return s;
    }

    /**
     * CARREGAR PARTIDA
     * <p>
     * Aquest mètode serveix per ajudar en les comparacions de partides, en concret aquest és per poder imprimir Patrons
     *
     * @param p és un Patró que representa el Patró que volem imprimir
     * @param numBoles és un int que representa el nombre de boles que conforma el Patró
     * @throws PosicioPatroNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private void imprimirPatro(Patro p, int numBoles) throws Exception{
        for (int i = 0; i < numBoles; ++i) {
            System.out.print(p.getColor(i));
            if (i < numBoles-1) System.out.print(" ");
        }
    }

    /**
     * Aquest mètode serveix per ajudar en les comparacions de partides, en concret aquest és per poder imprimir CorreccioLinea
     *
     * @param c és una CorreccioLinea que representa la CorreccioLinea que volem imprimir
     * @param numBoles és un int que representa el nombre de boles que conforma la CorreccióLinea
     * @throws PosicioPatroNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private void imprimirCorreccioLinea(CorreccioLinea c, int numBoles) throws Exception{
        for (int i = 0; i < numBoles; ++i) {
            System.out.print(c.getColor(i));
            if (i < numBoles-1) System.out.print(" ");
        }
    }

    /**
     * Aquest mètode serveix per imprimir les Partides carregades, per detecció més fàcilment de bugs de Partides carregades
     *
     * @param c és una Partida Classic que hem carregat i volem imprimir per poder veure que s'ha carregat correctament
     * @throws PosicioPatroNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private void carregarPartidaImprimir(Classic c) throws Exception{
        ArrayList<Patro> intents = c.getVectorIntents();

        int[] tempsAux = c.getTemps();

        String temps0;
        if (tempsAux[0] < 10){
            temps0 = "0";
            temps0 = temps0 + c.intToChar(tempsAux[0]);
        }
        else temps0 = String.valueOf(tempsAux[0]);

        String temps1;
        if (tempsAux[1] < 10){
            temps1 = "0";
            temps1 = temps1 + c.intToChar(tempsAux[1]);
        }
        else temps1 = String.valueOf(tempsAux[1]);

        System.out.print("numBoles: "+c.getNumBoles()+", modeAjuda: "+c.getModeAjuda()+", colorsRepetits: "+c.getColorsRepetits()+", intents: "+intents.size()+", temps: "+temps0+":"+temps1+"\n");

       imprimirPatro(c.getSolucio(), c.getNumBoles());

        System.out.print("intents: \n");
        for (int i = 0; i < intents.size(); ++i){
            imprimirPatro(intents.get(i), c.getNumBoles());
            if (i < 9) System.out.print("\n");
        }

        ArrayList<CorreccioLinea> correccions = c.getVectorCorreccions();

        System.out.print("correccions: \n");
        for (int j = 0; j < correccions.size(); ++j){
            //System.out.print(correccions.get(j)+"\n");
            imprimirCorreccioLinea(correccions.get(j), c.getNumBoles());
            if (j < 9) System.out.print("\n");
        }
    }

    /**
     * Aquest mètode serveix per saber si dos Patrons són iguals o no
     *
     * @param intent1 és un Patró i representa un dels Patrons que compararem
     * @param intent2 és l'altre Patró que volem comparar
     * @param numB és el nombre de boles que conforma el Patró
     * @return un boolean que representa si els patrons intent1 i intent2 són iguals o no
     * @throws PosicioPatroNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private boolean patronsDiferents(Patro intent1, Patro intent2, int numB) throws Exception{
        for (int i = 0; i < numB; ++i){
            if (intent1.getColor(i) != intent2.getColor(i)) return true;
        }
        return false;
    }

    /**
     * Aquest mètode serveix per saber si dos CorreccionsLinea són iguals o no
     *
     * @param correccio1 és una CorreccionsLinea i representa una de les CorreccionsLinea que compararem
     * @param correccio2 és l'altre CorreccionsLinea que volem comparar
     * @param numB és el nombre de boles que conforma la CorreccionsLinea
     * @return un boolean que representa si les CorreccionsLinea correccio1 i correccio2 són iguals o no
     * @throws PosicioCorreccioLineaNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private boolean correccionsDiferents(CorreccioLinea correccio1, CorreccioLinea correccio2, int numB) throws Exception{
        for (int i = 0; i < numB; ++i){
            if (correccio1.getColor(i) != correccio2.getColor(i)) return true;
        }
        return false;
    }

    /**
     * Aquest mètode retorna si els dos temps passats com a paràmetres són iguals o no
     *
     * @param temps1 és un vector d'integers i representa un dels temps que volem comprar
     * @param temps2 és un vector d'integers i representa l'altre temps que volem comprar
     * @return un boolean que indica si els temps són iguals o no
     */
    private boolean tempsIguals(int[] temps1, int[] temps2){
        if (temps1[0] != temps2[0]) return false;
        return temps1[1] == temps2[1];
    }

    /**
     * Aquest mètode retorna si dues Partides són iguals o no
     *
     * @param c és una Partida i representa una de les Partides que compararem
     * @param c2 és una Partida i representa una de les Partides que compararem
     * @return un boolean que indica si les Partides són iguals o no
     * @throws PosicioCorreccioLineaNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     * @throws PosicioPatroNoDisponible cada vegada que s'intenta accedir a una posició no disponible
     */
    private boolean partidesCarregadesIguals(Classic c, Classic c2) throws Exception{
        if (c.getNumBoles() == c2.getNumBoles()){
            if (c.getColorsRepetits() == c2.getColorsRepetits()){
                if (c.getModeAjuda() == c2.getModeAjuda()){
                    //si les solucions no són iguals
                    if (patronsDiferents(c.getSolucio(), c2.getSolucio(), c.getNumBoles())) return false;
                    if (!tempsIguals(c.getTemps(), c2.getTemps())) return false;

                    //mirem si els intents són iguals
                    ArrayList<Patro> intents1 = c.getVectorIntents();
                    ArrayList<Patro> intents2 = c2.getVectorIntents();

                    for (int i = 0; i < intents1.size(); ++i){
                        if (patronsDiferents(intents1.get(i), intents2.get(i), c.getNumBoles())) return false;
                    }


                    //ara per les correccions
                    ArrayList<CorreccioLinea> correccio1 = c.getVectorCorreccions();
                    ArrayList<CorreccioLinea> correccio2 = c.getVectorCorreccions();
                    for (int j = 0; j < correccio1.size(); ++j){
                        if (correccionsDiferents(correccio1.get(j), correccio2.get(j), c.getNumBoles())) return false;
                    }
                    return true;

                }
            }
        }
        return false;
    }

    /**
     * Aquest mètode serveix per crear Partides amb els atributs passats com a paràmetres
     *
     * @param numB és un integer i representa el nombre de boles que seran els intents, correccions i solucions
     * @param nInt és un integer que representa el nombre d'intents completats fins ara
     * @param intentV és un vector d'integers que representa l'intent que repetirem per omplir tots els intents fets fins ara
     * @param correccioV és un vector d'integers que representa la correcció que repetirem per omplir totes les correccions
     * @param solucioV és un vector d'integers que representa la solució de la Partida
     * @param temps és un vector d'integers que representa el temps de Partida fins ara
     * @return una Partida mode Classic feta amb els paràmetres passats
     */
    private Classic crearPartidaPerCarregar(int numB, int nInt, int[] intentV, int[] correccioV, int[] solucioV, int[] temps){
        Classic c = new Classic(numB, false, false);

        c.setTemps(temps);

        Patro solucio = new Patro(solucioV);
        c.passarSolucio(solucio);

        Patro intent = new Patro(intentV);
        CorreccioLinea correccio = new CorreccioLinea(numB, false, correccioV);
        for (int i = 0; i < nInt; ++i){
            c.passarIntent(intent);
            c.passarCorreccio(correccio);
        }

        return c;
    }

    /**
     * Test per comprovar que carregar una Partida amb tots els intents possibles, ho fa correctament
     * <p>
     * Casos de prova:
     * - crear una Partida
     * - cridar la funció per carregar la Partida des d'un ArrayList de String a una Partida
     * - crear una solució, intent, correcció i temps per poder comprar-ho amb la Partida
     * <p>
     * Resultat esperat:
     * - que la Partida carregada i la creada per comparar-la siguin iguals
     */
    @Test
    public void carregarPartidaTotsIntents() throws Exception{
        Classic c = new Classic();

        c.carregarPartida(partidaEnStringCompleta());   //carreguem la partida que volem dins de Classic c
        carregarPartidaImprimir(c);

        int[] solucioV = new int[] {1, 2, 3, 4};
        int[] intentV = new int[] {1, 1, 0, 4};
        int[] correccioV = new int[] {1, 2, 1, 2};
        int[] temps = new int[] {10, 21};

        assertTrue(partidesCarregadesIguals(c, crearPartidaPerCarregar(4, 9, intentV, correccioV, solucioV, temps)));
    }

    /**
     * Test per comprovar que carregar una Partida amb tots els intents possibles i de 8 boles, ho fa correctament
     * <p>
     * Casos de prova:
     * - crear una Partida
     * - cridar la funció per carregar la Partida des d'un ArrayList de String a una Partida
     * - crear una solució, intent, correcció i temps per poder comprar-ho amb la Partida
     * <p>
     * Resultat esperat:
     * - que la Partida carregada i la creada per comparar-la siguin iguals
     */
    @Test
    public void carregarPartida8Boles() throws Exception{
        Classic c = new Classic();

        c.carregarPartida(partidaEnString8Boles());
        carregarPartidaImprimir(c);

        int[] solucioV = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int[] intentV = new int[] {1, 8, 0, 4, 2, 3, 5, 6};
        int[] correccioV = new int[] {1, 2, 0, 1, 1, 1, 1, 1};
        int[] temps = new int[] {10, 21};

        assertTrue(partidesCarregadesIguals(c, crearPartidaPerCarregar(8, 9, intentV, correccioV, solucioV, temps)));
    }

    /**
     * Test per comprovar que carregar una Partida amb la meitat d'intents i 8 boles, ho fa correctament
     * <p>
     * Casos de prova:
     * - crear una Partida
     * - cridar la funció per carregar la Partida des d'un ArrayList de String a una Partida
     * - crear una solució, intent, correcció i temps per poder comprar-ho amb la Partida
     * <p>
     * Resultat esperat:
     * - que la Partida carregada i la creada per comparar-la siguin iguals
     */
    @Test
    public void carregarPartidaMigIntent() throws Exception{
        Classic c = new Classic();
        c.carregarPartida(partidaMeitatIntent());
        carregarPartidaImprimir(c);

        int[] solucioV = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int[] intentV = new int[] {1, 8, 0, 4, 2, 3, 5, 6};
        int[] correccioV = new int[] {1, 2, 0, 1, 1, 1, 1, 1};
        int[] temps = new int[] {10, 21};

        assertTrue(partidesCarregadesIguals(c, crearPartidaPerCarregar(8, 5, intentV, correccioV, solucioV, temps)));
    }
}