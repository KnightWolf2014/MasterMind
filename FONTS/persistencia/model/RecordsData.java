package persistencia.model;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecordsData {
    private File file;
    private String ruta;

    /**
     * Creadora de la classe RecordsData.
     */
    public RecordsData(){
        this.ruta = archiveRecords();
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
     * Aquest mètode s'encarrega de localitzar el path de la carpeta DATA.
     *
     * @return El path de la carpeta DATA.
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
     * Aquest mètode s'encarrega de localitzar el path de la carpeta Records.
     *
     * @return El path de la carpeta Records.
     */
    private String folderRecords(){
        String separator = File.separator;

        String pathFolderRanking = folderDATA() + separator + "Records";
        File folderRankingClassic = new File(pathFolderRanking);
        if (!folderRankingClassic.exists()){
            if (folderRankingClassic.mkdir()) System.out.println("Carpeta Records creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path del arxiu seleccionat.
     *
     * @return El path del arxiu seleccionat.
     */
    private String archiveRecords(){
        return folderRecords() + File.separator + "Records.txt";
    }

    /**
     * Aquest mètode s'encarrega de comprovar que l'arxiu de Records existeixi. Si no existeix, el crea.
     */
    public void creaArchive(){
        file = new File(this.ruta);
        if (!file.exists()) {
            try {
                file = new File(this.ruta);
                if (!file.createNewFile()) System.out.println("Fitxer creat");

                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99:999");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0 -> Player: EMPTY, Intents: 99, Temps: 99:99:999");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99:999");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1 -> Player: EMPTY, Intents: 99, Temps: 99:99:999");
                    bw.newLine();
                }

                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 5 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }

                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 0, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 0, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
                    bw.newLine();
                }
                for (int j = 4; j <= 8; j += 2) {
                    bw.write("NumBoles: " + j + ", ModeAjuda: 1, ColorsRepetits: 1, Cronometre: 10 -> Player: EMPTY, Victories: -1, Intents: 99");
                    if (j != 8) bw.newLine();
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
     * Aquest mètode s'encarrega de guardar l'ArrayList que conté la informació dels Records a l'arxiu.
     *
     * @param datos és un ArrayList de strings que conté les dades del Records.
     */
    public void guardarRecords(ArrayList<String> datos) {
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
     * Aquest mètode s'encarrega de carregar el contingut dels Records guardats i retornarlo.
     *
     * @return un ArrayList de strings amb el contingut dels Records.
     */
    public ArrayList<String> carregarRecords(){
        ArrayList<String> records = new ArrayList<>();
        try {
            file = new File(this.ruta);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                records.add(linea);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al carregar la informació desde el fitxer");
            e.printStackTrace();
        }
        return records;
    }

}
