package persistencia.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import persistencia.model.RankingClassicData;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingClassicDataTest {
    RankingClassicData rankingClassicData;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzarTests(){
        rankingClassicData = new RankingClassicData();
    }

    /**
     * Aquesta regla crea una carpeta temporal per a poder guardar i carregar els arxius durant el test.
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Aquest test comprova si es creen els arxius del path del ranking classic.
     * <p>
     * Casos de prova:
     * - Cridar a la funció que crea el path.
     * <p>
     * Resultat esperat:
     * - Que el path existeixi, i si no, el creï.
     */
    @Test
    public void testCrearArchives(){
        rankingClassicData.creaArchives();
        File archivoTest = new File(System.getProperty("user.dir") + File.separator + "DATA" + File.separator + "RankingClassic" + File.separator + "RankingClassic_4.txt");
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
    public void testGuardarICarregarRankingClassic() throws IOException {

        String ruta = "RecordsTest.txt";
        File archivo = testFolder.newFile(ruta);

        rankingClassicData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");
        rankingClassicData.guardarRanking(datos);

        assertTrue(archivo.exists());

        ArrayList<String> solucion = rankingClassicData.carregarRanking();
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

        rankingClassicData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");


        rankingClassicData.guardarRanking(datos);

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

        rankingClassicData.setRuta(ruta);

        ArrayList<String> solucion = rankingClassicData.carregarRanking();
        for (String s : solucion) {
            System.out.println(s);
        }
    }

}