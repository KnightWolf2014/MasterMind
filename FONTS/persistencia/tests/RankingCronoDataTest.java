package persistencia.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import persistencia.model.RankingCronoData;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingCronoDataTest {
    RankingCronoData rankingCronoData;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzarTests(){
        rankingCronoData = new RankingCronoData();
    }

    /**
     * Aquesta regla crea una carpeta temporal per a poder guardar i carregar els arxius durant el test.
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Aquest test comprova si es creen els arxius del path del ranking crono de 5 minuts.
     * <p>
     * Casos de prova:
     * - Cridar a la funció que crea el path.
     * <p>
     * Resultat esperat:
     * - Que el path existeixi, i si no, el creï.
     */
    @Test
    public void testCrearArchives5minutes(){
        rankingCronoData.creaArchives();
        File archivoTest = new File(System.getProperty("user.dir") + File.separator + "DATA" + File.separator + "RankingCrono" + File.separator + "RankingCrono_5minutes" + File.separator + "RankingCrono_5_4.txt");
        assertTrue(archivoTest.exists());
    }

    /**
     * Aquest test comprova si es creen els arxius del path del ranking crono de 10 minuts.
     * <p>
     * Casos de prova:
     * - Cridar a la funció que crea el path.
     * <p>
     * Resultat esperat:
     * - Que el path existeixi, i si no, el creï.
     */
    @Test
    public void testCrearArchives10minutes(){
        rankingCronoData.creaArchives();
        File archivoTest = new File(System.getProperty("user.dir") + File.separator + "DATA" + File.separator + "RankingCrono" + File.separator + "RankingCrono_10minutes" + File.separator + "RankingCrono_10_4.txt");
        assertTrue(archivoTest.exists());
    }

    /**
     * Aquest test comprova si es pot guardar i carregar un arxiu.
     * <p>
     * Casos de prova:
     * - Passar una ruta que existeix i donar-li un contingut al fitxer temporal.
     * <p>
     * Resultat esperat:
     * - Que es puguin carregar les dades donades al guardar l'arxiu.
     */
    @Test
    public void testGuardarICarregarRankingCrono() throws IOException {

        String ruta = "RankingCronoTest.txt";
        File archivo = testFolder.newFile(ruta);

        rankingCronoData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");
        rankingCronoData.guardarRanking(datos);

        assertTrue(archivo.exists());

        ArrayList<String> solucion = rankingCronoData.carregarRanking();
        for (int i = 0; i < solucion.size(); ++i) {
            assertEquals(datos.get(i), solucion.get(i));
        }
    }

    /**
     * Aquest test comprova si salta l'excepció al guardar un arxiu que no existeix.
     * <p>
     * Casos de prova:
     * - Passar una ruta que no existeix.
     * <p>
     * Resultat esperat:
     * - Salta l'excepció.
     */
    @Test(expected = IOException.class)
    public void testGuardarIOException() throws IOException {

        String ruta = "RutaFalsa/test.txt";
        File archivo = testFolder.newFile(ruta);
        if (!archivo.exists()) System.out.println("Arxiu no existeix -> es llençarà l'excepció");

        rankingCronoData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");


        rankingCronoData.guardarRanking(datos);

    }

    /**
     * Aquest test comprova si salta l'excepció al carregar un arxiu que no existeix.
     * <p>
     * Casos de prova:
     * - Passar una ruta que no existeix.
     * <p>
     * Resultat esperat:
     * - Salta l'excepció.
     */
    @Test(expected = IOException.class)
    public void testCarregarIOException() throws IOException {

        String ruta = "RutaFalsa/test.txt";
        File archivo = testFolder.newFile(ruta);
        if (!archivo.exists()) System.out.println("Arxiu no existeix -> es llençarà l'excepció");

        rankingCronoData.setRuta(ruta);

        ArrayList<String> solucion = rankingCronoData.carregarRanking();
        for (String s : solucion) {
            System.out.println(s);
        }
    }

}