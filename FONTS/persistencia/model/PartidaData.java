package persistencia.model;

import java.io.*;
import java.util.ArrayList;

public class PartidaData {
    private File file;
    private String ruta;

    /**
     * Creadora de la clase PartidaData.
     */
    public PartidaData(){
        this.ruta = archivePartidaGuardada();
    }

    /**
     * Aquest mètode s'encarrega de guardar una ruta en la classe.
     *
     * @param path és un string amb el path de la nova ruta.
     */
    public void setRuta(String path){
        this.ruta = path;
    }

    /**
     * Aquest mètode s'encarrega de localitzar els paths de la carpeta DATA.
     *
     * @return el path de la carpeta DATA.
     */
    private String folderDATA() {
        String path = System.getProperty("user.dir");
        String separator = File.separator;

        String pathFolderBase = path + separator + "DATA";
        File folderDATA = new File(pathFolderBase);
        if (!folderDATA.exists()) {
            if (folderDATA.mkdir()) System.out.println("Carpeta DATA creada");
        }

        return pathFolderBase;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path de la carpeta PartidaGuardada.
     *
     * @return el path de la carpeta PartidaGuardada.
     */
    private String folderPartidaGuardada(){
        String separator = File.separator;

        String pathFolderRanking = folderDATA() + separator + "PartidaGuardada";
        File folderRankingClassic = new File(pathFolderRanking);
        if (!folderRankingClassic.exists()){
            if (folderRankingClassic.mkdir()) System.out.println("Carpeta PartidaGuardada creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path del arxiu seleccionat.
     *
     * @return el path del arxiu seleccionat.
     */
    private String archivePartidaGuardada(){
        return folderPartidaGuardada() + File.separator + "PartidaGuardada.txt";
    }

    /**
     * Aquest mètode s’encarrega de comprovar si hi ha una partida guardada.
     *
     * @return un booleà. Retorna true en cas que hi hagi una partida guardada, un false en cas contrari.
     */
    public boolean partidaGuardada() {
        int valor = 0;
        try {
            file = new File(this.ruta);
            BufferedReader br = new BufferedReader(new FileReader(file));
            valor = Integer.parseInt(br.readLine());
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return valor != 0;
    }

    /**
     * Aquest mètode s’encarrega de ficar l’arxiu d’una partida amb el model estàndard per a resetejarla.
     */
    public void resetejarPartida() {
        this.ruta = archivePartidaGuardada();
        try {
            file = new File(this.ruta);
            boolean borrat = file.delete();
            if (borrat) System.out.println("Borrat amb exit");
            else throw new IOException("No s'ha pogut borrar l'arxiu " + file);
        } catch (IOException e) {
            System.err.println("No s'ha pogut borrar l'arxiu " + file);
        }
        creaArchive();
    }

    /**
     * Aquest mètode s'encarrega de comprovar que l'arxiu de Partida existeix. Si no existeix, el crea.
     */
    public void creaArchive(){
        file = new File(this.ruta);
        if (!file.exists()) {
            try {
                file = new File(this.ruta);
                if (!file.createNewFile()) System.out.println("Fitxer creat");

                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("0");
                bw.newLine();
                bw.write("numBoles: 9, modeAjuda: 9, colorsRepetits: 9, intents: 9, temps: 99:99");
                bw.newLine();
                for (int j = 0; j < 19; ++j) {
                    bw.write("9-9-9-9-9-9-9-9");
                    if (j != 18) bw.newLine();
                }
                bw.close();

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Aquest mètode s'encarrega de guardar l'Arraylist que conté la informació de la partida a l'arxiu.
     *
     * @param datos es un ArrayList de strings que conté les dades de la partida.
     */
    public void guardarPartida(ArrayList<String> datos) {
        file = new File(this.ruta);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < datos.size(); ++i){
                bw.write(datos.get(i));
                if (i < datos.size()-1) bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error al guardar la informació en el fitxer");
            e.printStackTrace();
        }
    }

    /**
     * Aquest mètode s'encarrega de carregar el contingut de la partida guardada i retornarla.
     *
     * @return un ArrayList de strings amb el contingut de la partida.
     */
    public ArrayList<String> carregarPartida(){
        ArrayList<String> partida = new ArrayList<>();
        try {
            file = new File(this.ruta);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                partida.add(linea);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al carregar la informació desde el fitxer");
            e.printStackTrace();
        }
        return partida;
    }
}