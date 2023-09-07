package persistencia.model;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RankingCronoData {

    private String ruta;
    private File file;

    /**
     * Creadora de la classe RankingClassicData.
     */
    public RankingCronoData(){
    }

    /**
     * Aquest mètode s'encarrega de crear un ArrayList de strings amb tots els paths dels rankings crono.
     *
     * @return un ArrayList de strings que conté el path de tots els rankings crono.
     */
    private ArrayList<String> pathRankingsCrono(){
        ArrayList<String> path = new ArrayList<>();
        path.add(archiveRankingCrono(4, false, false, 5));
        path.add(archiveRankingCrono(6, false, false, 5));
        path.add(archiveRankingCrono(8, false, false, 5));

        path.add(archiveRankingCrono(4, false, true, 5));
        path.add(archiveRankingCrono(6, false, true, 5));
        path.add(archiveRankingCrono(8, false, true, 5));

        path.add(archiveRankingCrono(4, true, false, 5));
        path.add(archiveRankingCrono(6, true, false, 5));
        path.add(archiveRankingCrono(8, true, false, 5));

        path.add(archiveRankingCrono(4, true, true, 5));
        path.add(archiveRankingCrono(6, true, true, 5));
        path.add(archiveRankingCrono(8, true, true, 5));

        path.add(archiveRankingCrono(4, false, false, 10));
        path.add(archiveRankingCrono(6, false, false, 10));
        path.add(archiveRankingCrono(8, false, false, 10));

        path.add(archiveRankingCrono(4, false, true, 10));
        path.add(archiveRankingCrono(6, false, true, 10));
        path.add(archiveRankingCrono(8, false, true, 10));

        path.add(archiveRankingCrono(4, true, false, 10));
        path.add(archiveRankingCrono(6, true, false, 10));
        path.add(archiveRankingCrono(8, true, false, 10));

        path.add(archiveRankingCrono(4, true, true, 10));
        path.add(archiveRankingCrono(6, true, true, 10));
        path.add(archiveRankingCrono(8, true, true, 10));
        return path;
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
     * Aquest mètode s'encarrega de localitzar el path de la carpeta RankingCrono.
     *
     * @return el path de la carpeta RankingCrono.
     */
    private String folderRankingCrono(){
        String separator = File.separator;

        String pathFolderRanking = folderDATA() + separator + "RankingCrono";
        File folderRankingCrono = new File(pathFolderRanking);
        if (!folderRankingCrono.exists()){
            if (folderRankingCrono.mkdir()) System.out.println("Carpeta RankingCrono creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path de la carpeta RankingCrono_5minutes.
     *
     * @return el path de la carpeta RankingCrono_5minutes.
     */
    private String folderRankingCrono_5minutes(){
        String separator = File.separator;

        String pathFolderRanking = folderRankingCrono() + separator + "RankingCrono_5minutes";
        File folderRankingClassic_5minutes = new File(pathFolderRanking);
        if (!folderRankingClassic_5minutes.exists()){
            if (folderRankingClassic_5minutes.mkdir()) System.out.println("Carpeta RankingCrono_5minutes creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path de la carpeta RankingCrono_10minutes.
     *
     * @return el path de la carpeta RankingCrono_10minutes.
     */
    private String folderRankingCrono_10minutes(){
        String separator = File.separator;

        String pathFolderRanking = folderRankingCrono() + separator + "RankingCrono_10minutes";
        File folderRankingClassic_10minutes = new File(pathFolderRanking);
        if (!folderRankingClassic_10minutes.exists()){
            if (folderRankingClassic_10minutes.mkdir()) System.out.println("Carpeta RankingCrono_10minutes creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path del arxiu seleccionat.
     *
     * @param numBoles es un enter per filtrar el ranking per nombre de boles.
     * @param modeAjuda és un booleà per filtrar el ranking per si has jugat amb el mode ajuda o no.
     * @param colorsRepetits és un booleà per filtrar el ranking per si has jugat amb el mode de colors repetits o no.
     * @param cronometre és un enter que representa el temps del cronòmetre.
     * @return El path del arxiu seleccionat.
     */
    private String archiveRankingCrono(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre){
        String ruta = "";
        String separator = File.separator;
        if (cronometre == 5) {
            if (!modeAjuda) {
                if (!colorsRepetits) {
                    if (numBoles == 4) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_4.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_6.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_8.txt";
                } else {
                    if (numBoles == 4) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_4_C.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_6_C.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_8_C.txt";
                }
            } else {
                if (!colorsRepetits) {
                    if (numBoles == 4) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_4_A.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_6_A.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_8_A.txt";
                } else {
                    if (numBoles == 4) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_4_A_C.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_6_A_C.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_5minutes() + separator + "RankingCrono_5_8_A_C.txt";
                }
            }
        }
        else{
            if (!modeAjuda) {
                if (!colorsRepetits) {
                    if (numBoles == 4) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_4.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_6.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_8.txt";
                } else {
                    if (numBoles == 4) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_4_C.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_6_C.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_8_C.txt";
                }
            } else {
                if (!colorsRepetits) {
                    if (numBoles == 4) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_4_A.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_6_A.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_8_A.txt";
                } else {
                    if (numBoles == 4) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_4_A_C.txt";
                    else if (numBoles == 6) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_6_A_C.txt";
                    else if (numBoles == 8) ruta = folderRankingCrono_10minutes() + separator + "RankingCrono_10_8_A_C.txt";
                }
            }
        }

        return ruta;
    }

    /**
     * Aquest mètode s'encarrega de guardar una ruta en la classe.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent
     * @param cronometre és un enter que representa el temps del cronòmetre.
     */
    public void setRuta(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre){
        this.ruta = archiveRankingCrono(numBoles, modeAjuda, colorsRepetits, cronometre);
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
     * Aquest mètode s'encarrega de comprovar que els arxius de ranking clàssic existeixin. Si no existeixen, els crea.
     */
    public void creaArchives(){
        ArrayList<String> path = pathRankingsCrono();
        for (String s : path) {
            file = new File(s);
            if (!file.exists()) {
                try {
                    file = new File(s);
                    if (!file.createNewFile()) System.out.println("Fitxer creat");

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for (int j = 0; j < 10; ++j) {
                        bw.write("Top " + (j + 1) + ") -- Player: EMPTY, Victories: -1, Intents: 999");
                        if (j != 9) bw.newLine();
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
    }

    /**
     * Aquest mètode s'encarrega de guardar l'Arraylist que conté la informació del ranking amb el path guardat anteriorment a la classe.
     *
     * @param datos és un ArrayList de strings que conté les dades del ranking seleccionat.
     */
    public void guardarRanking(ArrayList<String> datos) {
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
     * Aquest mètode s'encarrega de carregar el contingut del ranking guardat i retornarlo.
     *
     * @return un ArrayList de strings amb el contingut del ranking seleccionat.
     */
    public ArrayList<String> carregarRanking(){
        ArrayList<String> ranking = new ArrayList<>();
        try {
            file = new File(this.ruta);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                ranking.add(linea);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al carregar la informació desde el fitxer");
            e.printStackTrace();
        }
        return ranking;
    }

}