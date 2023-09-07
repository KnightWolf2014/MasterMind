package domini.tests;

import domini.exceptions.ColorsIncorrectes;
import domini.exceptions.LlargadaPatroIncorrecte;
import domini.model.CorreccioLinea;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CorreccioLineaTest{

    /**
     * Test per comprovar que no crida cap excepció si els parametres són correctes.
     * <p>
     * Casos de prova:
     * - Fiquem parametres correctes en una CorreccioLinea.
     * - ModeAjuda activat.
     * <p>
     * Resultats esperats:
     * - No salta cap excepció.
     */
    @Test
    public void testComprovacioParametresCorrectesModeAjuda() {
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);
        int[] p = new int[] {1, 0, 0, 2};
        try {
            correccioLinea.comprovarParametres(p);
        } catch (Exception e) {
            fail("Excepción lanzada");
        }
    }

    /**
     * Test per comprovar que no crida cap excepció si els parametres són correctes.
     * <p>
     * Casos de prova:
     * - Fiquem parametres correctes en una CorreccioLinea.
     * - ModeAjuda no activat.
     * <p>
     * Resultats esperats:
     * - No salta cap excepció.
     */
    @Test
    public void testComprovacioParametresCorrectes() {
        CorreccioLinea correccioLinea = new CorreccioLinea(4, false);
        int[] p = new int[] {1, 0, 0, 2};
        try {
            correccioLinea.comprovarParametres(p);
        } catch (Exception e) {
            fail("Excepción lanzada");
        }
    }

    /**
     * Test per comprovar que s'ha d'introduir vectors amb la llargada igual al nombre de boles previament indicades.
     * <p>
     * Casos de proves:
     * - Omplim el Patro amb una llargada que no coincideix amb el nombre de coles que hem configurat.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció LlargadaPatroIncorrecte.
     */
    @Test(expected = LlargadaPatroIncorrecte.class)
    public void testComprovacioParametresLlargadaIncorrecte() throws Exception {
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);
        int[] p = new int[]{1, 0, 0, 2, 0};
        correccioLinea.comprovarParametres(p);
    }

    /**
     * Test per comprovar que només es pot introduir colors de codi de 0 a 7, si no salta l'excepció.
     * <p>
     * Casos de prova:
     * - Omplim el Patro amb nombres majors a 7.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció ColorsIncorrectes.
     */
    @Test(expected = ColorsIncorrectes.class)
    public void testComprovacioParametresColorsIncorrectes() throws Exception{
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);
        int[] p = new int[] {1, 0, 8, 2};
        correccioLinea.comprovarParametres(p);
    }

    /**
     * Test per comprovar que només es pot introduir colors de codi de 0 a 7, si no salta l'excepció.
     * <p>
     * Casos de prova:
     * - Omplim el Patro amb nombres menor a 0.
     * <p>
     * Resultats esperats:
     * - Salta l'excepció ColorsIncorrectes.
     */
    @Test(expected = ColorsIncorrectes.class)
    public void testComprovacioParametresColorsNegatius() throws Exception{
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);
        int[] p = new int[] {-6, 0, -1, 2};
        correccioLinea.comprovarParametres(p);
    }


    /**
     * Test per comprovar que la correcció no es randomitza amb el modeAjuda activat.
     * <p>
     * Casos de prova:
     * - Volem veure si no es randomitza la correcció.
     * <p>
     * Resultat esperat:
     * - Un vector d'enters igual a l'anterior.
     */
    @Test
    public void testActualitzaCorreccioLineaModeAjuda(){
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);
        int[] p = new int[] {1, 0, 0, 2};
        CorreccioLinea result = correccioLinea.actualitzaCorreccioLinea(p);
        int[] resultV = result.getCorreccioLinea();
        assert(Arrays.equals(resultV, p));
    }

    /**
     * Test per comprovar que la correcció es randomitza amb el modeAjuda desactivat.
     * <p>
     * Casos de prova:
     * - Volem veure si es randomitza la correcció.
     * <p>
     * Resultat esperat:
     * - Un vector d'enters randomitzat.
     */
    @Test
    public void testActualitzaCorreccioLineaNoModeAjuda(){
        CorreccioLinea correccioLinea = new CorreccioLinea(4, false);
        int[] p = new int[] {1, 0, 0, 2};

        CorreccioLinea result = correccioLinea.actualitzaCorreccioLinea(p);
        int[] resultV = result.getCorreccioLinea();

        boolean ordenado = Arrays.equals(p, resultV);
        assertFalse(ordenado);
    }

    /**
     * Test per comprovar que el Patro es tradueix bé a una matriu d'enters.
     * <p>
     * Casos de prova:
     * - Provem de traduir un ArrayList de Patro a una matriu d'enters.
     * <p>
     * Resultat esperat:
     * - Una matriu d'enters.
     */
    @Test
    public void testCorreccioToInt(){
        CorreccioLinea correccioLinea = new CorreccioLinea(4, true);

        int[] v1 = new int[]{0, 0, 1, 1};
        int[] v2 = new int[]{2, 0, 0, 1};
        int[] v3 = new int[]{0, 2, 0, 1};
        int[] v4 = new int[]{1, 2, 1, 1};


        CorreccioLinea cl1 = new CorreccioLinea(v1);
        CorreccioLinea cl2 = new CorreccioLinea(v2);
        CorreccioLinea cl3 = new CorreccioLinea(v3);
        CorreccioLinea cl4 = new CorreccioLinea(v4);

        ArrayList<CorreccioLinea> ArrayCorr = new ArrayList<>();
        ArrayCorr.add(cl1);
        ArrayCorr.add(cl2);
        ArrayCorr.add(cl3);
        ArrayCorr.add(cl4);

        int[][] matRes = correccioLinea.correccioToInt(ArrayCorr);

        int[][] matSol = new int[][]{v1, v2, v3, v4};
        for (int i = 0; i < 4; ++i){
            for (int j = 0; j < 4; ++j) {
                assertEquals(matRes[i][j], matSol[i][j]);
            }
        }
    }
}
