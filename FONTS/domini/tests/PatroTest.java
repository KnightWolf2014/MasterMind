package domini.tests;
import domini.exceptions.*;
import domini.model.Patro;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PatroTest {

    /**
     * Test per comprovar que no crida cap excepció si els paràmetres són correctes.
     * <p>
     * Casos de prova:
     * - Fiquem paràmetres correctes en un Patró.
     * - No activem els ColorsRepetits.
     * <p>
     * Resultats esperats:
     * - No salta cap excepció.
     */
    @Test
    public void comprovarParametresCorrectesNoColorsRepetits() throws Exception{
        Patro p  = new Patro(6, false);
        int[] aux = {1, 2, 3, 4, 6, 5};
        p.comprovarParametres(aux);
    }

    /**
     * Test per comprovar que no crida cap excepció en el cas que hi hagi colorsRepetits.
     * <p>
     * Casos de prova:
     * - Fiquem paràmetres correctes en un Patró.
     * <p>
     * Resultats esperats:
     * - No salta cap excepció.
     */
    @Test
    public void comprovarParametresCorrectesColorsRepetits() throws Exception{
        Patro p = new Patro(6, true);
        int[] aux = {1, 2, 3, 4, 4, 5};
        p.comprovarParametres(aux);
    }

    /**
     *  Test per comprovar que si s'introdueix un vector amb colors repetits quan aquests no estan activats, que salti l'excepció 'ColorsRepetitsNoActivats'.
     * <p>
     *  Casos de prova:
     *  - Desactivar ColorsRepetits
     *  - Omplir Patró amb colors repetits.
     * <p>
     *  Resultats esperats:
     *  - Salta l'excepció ColorsRepetitsNoActivitats.
     */
    @Test(expected = ColorsRepetitsNoActivats.class)
    public void comprovarParametresColorsRepetitsException() throws Exception{
        Patro p = new Patro(6, false);
        int[] aux = {1, 1, 2, 3, 4, 5};
        p.comprovarParametres(aux);
    }

    /**
     * Test per comprovar que només es pot introduir colors de codi de 0 a 7, si no salta l'excepció.
     * <p>
     * Casos de prova:
     * - Omplir el Patró amb nombres majors a 7.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció ColorsIncorrectes.
     */
    @Test(expected = ColorsIncorrectes.class)
    public void comprovarParametresColorsIncorrectes() throws Exception{
        Patro p = new Patro(6, false);
        int[] aux = {1, 2, 3, 4, 5, 9};
        p.comprovarParametres(aux);
    }

    /**
     * Test per comprovar que només es pot introduir colors de codi de 0 a 7, si no salta l'excepció.
     * <p>
     * Casos de prova:
     * - Omplir el Patró amb nombres menors a 0.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció ColorsIncorrectes.
     */
    @Test(expected = ColorsIncorrectes.class)
    public void comprovarParametresColorsNegatius() throws Exception{
        Patro p = new Patro(6, false);
        int[] aux = {1, 2, 3, 4, 5, -1};
        p.comprovarParametres(aux);
    }

    /**
     * Test per comprovar que s'ha d'introduir vectors amb la llargada igual al nombre de boles prèviament indicades.
     * <p>
     * Casos de proves:
     * - Omplim el Patró amb una llargada que no coincideix amb el nombre de coles que hem configurat.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció LlargadaPatroIncorrecte.
     */
    @Test(expected = LlargadaPatroIncorrecte.class)
    public void comprovarParametreLlargadaIncorrecte() throws Exception{
        Patro p = new Patro(6, false);
        int[] aux = {1, 2, 3, 4, 5, 6, 7};
        p.comprovarParametres(aux);
    }

    /**
     * Test per comprovar que funciona correctament la funció getColor quan la posició és correcte.
     * <p>
     * Casos de prova:
     * - Intentem accedir a una posició existent dins del Patró.
     * <p>
     * Resultat esperat:
     * - No salta cap excepció.
     */
    @Test
    public void getColorCorrecte() throws Exception{
        int[] aux = {1, 2, 3, 4, 5, 6};
        Patro p = new Patro(aux);
        assertEquals(p.getColor(3), 4);
    }

    /**
     * Test per comprovar que funciona correctament la funció getColor quan la posició és incorrecte.
     * <p>
     * Casos de prova:
     * - Intentem accedir a una posició inexistent.
     * <p>
     * Resultat esperat:
     * - Salta l'excepció PosicioPatroNoDisponible.
     */
    @Test(expected = PosicioPatroNoDisponible.class)
    public void getColorIncorrectePos() throws  Exception{
        int[] aux = {1, 2, 3, 4, 5, 6};
        Patro p = new Patro(aux);
        p.getColor(10);
    }

    /**
     * Test per comprovar que el Patró es tradueix bé a una matriu d'enters.
     * <p>
     * Casos de prova:
     * - Provem de traduir un ArrayList de Patró a una matriu d'enters.
     * <p>
     * Resultat esperat:
     * - Una matriu d'enters.
     */
    @Test
    public void testPatroToInt() {
        Patro patro = new Patro(4, true);

        int[] v1 = new int[]{1, 2, 3, 3};
        int[] v2 = new int[]{2, 0, 3, 6};
        int[] v3 = new int[]{0, 2, 2, 7};
        int[] v4 = new int[]{1, 5, 4, 1};

        Patro p1 = new Patro(v1);
        Patro p2 = new Patro(v2);
        Patro p3 = new Patro(v3);
        Patro p4 = new Patro(v4);

        ArrayList<Patro> ArrayPat = new ArrayList<>();
        ArrayPat.add(p1);
        ArrayPat.add(p2);
        ArrayPat.add(p3);
        ArrayPat.add(p4);

        int[][] matRes = patro.patroToInt(ArrayPat);

        int[][] matSol = new int[][]{v1, v2, v3, v4};
        for (int i = 0; i < 4; ++i){
            for (int j = 0; j < 4; ++j) {
                assertEquals(matRes[i][j], matSol[i][j]);
            }
        }
    }
}
