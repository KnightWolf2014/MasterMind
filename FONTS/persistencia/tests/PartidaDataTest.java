package persistencia.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import persistencia.model.PartidaData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PartidaDataTest {

    PartidaData partidaData;

    /**
     * Aquest mètode s'encarrega d'inicialitzar les variables dels tests.
     */
    @Before
    public void inicialitzarTests(){
        partidaData = new PartidaData();
    }

    /**
     * Aquesta regla crea una carpeta temporal per a poder guardar i carregar els arxius durant el test.
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Aquest test comprova si es creen els arxius del path de la PartidaData.
     * <p>
     * Casos de prova:
     * - Cridar a la funció que crea el path.
     * <p>
     * Resultat esperat:
     * - Que el path existeixi, i si no, el creï.
     */
    @Test
    public void testCrearArchives(){
        partidaData.creaArchive();
        File archivoTest = new File(System.getProperty("user.dir") + File.separator + "DATA" + File.separator + "PartidaGuardada" + File.separator + "PartidaGuardada.txt");
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
    public void testGuardarICarregarPartida() throws IOException {

        String ruta = "PartidaTest.txt";
        File archivo = testFolder.newFile(ruta);

        partidaData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");
        partidaData.guardarPartida(datos);

        assertTrue(archivo.exists());

        ArrayList<String> solucion = partidaData.carregarPartida();
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

        partidaData.setRuta(ruta);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Prueba del test 1");
        datos.add("Prueba del test 2");
        datos.add("Prueba del test 3");


        partidaData.guardarPartida(datos);

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

        partidaData.setRuta(ruta);

        ArrayList<String> solucion = partidaData.carregarPartida();
        for (String s : solucion) {
            System.out.println(s);
        }
    }
}
