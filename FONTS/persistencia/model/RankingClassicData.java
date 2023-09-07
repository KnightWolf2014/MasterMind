package persistencia.model;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RankingClassicData {

    private String ruta;
    private File file;

    /**
     * Creadora de la classe RankingClassicData.
     */
    public RankingClassicData(){
    }

    /**
     * Aquest mètode s'encarrega de crear un ArrayList de strings amb tots els paths dels rankings classic.
     *
     * @return un ArrayList de strings que conté el path de tots els rankings classic.
     */
    private ArrayList<String> pathRankingsClassic(){
        ArrayList<String> path = new ArrayList<>();
        path.add(archiveRankingClassic(4, false, false));
        path.add(archiveRankingClassic(6, false, false));
        path.add(archiveRankingClassic(8, false, false));

        path.add(archiveRankingClassic(4, false, true));
        path.add(archiveRankingClassic(6, false, true));
        path.add(archiveRankingClassic(8, false, true));

        path.add(archiveRankingClassic(4, true, false));
        path.add(archiveRankingClassic(6, true, false));
        path.add(archiveRankingClassic(8, true, false));

        path.add(archiveRankingClassic(4, true, true));
        path.add(archiveRankingClassic(6, true, true));
        path.add(archiveRankingClassic(8, true, true));
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
     * Aquest mètode s'encarrega de localitzar el path de la carpeta RankingClassic.
     *
     * @return el path de la carpeta RankingClassic.
     */
    private String folderRankingClassic(){
        String separator = File.separator;

        String pathFolderRanking = folderDATA() + separator + "RankingClassic";
        File folderRankingClassic = new File(pathFolderRanking);
        if (!folderRankingClassic.exists()){
            if (folderRankingClassic.mkdir()) System.out.println("Carpeta RankingClassic creada");
        }

        return pathFolderRanking;
    }

    /**
     * Aquest mètode s'encarrega de localitzar el path del arxiu seleccionat.
     *
     * @param numBoles es un enter per filtrar el ranking per nombre de boles.
     * @param modeAjuda és un booleà per filtrar el ranking per si has jugat amb el mode ajuda o no.
     * @param colorsRepetits és un booleà per filtrar el ranking per si has jugat amb el mode de colors repetits o no.
     * @return el path del arxiu seleccionat.
     */
    private String archiveRankingClassic(int numBoles, boolean modeAjuda, boolean colorsRepetits){
        String ruta = "";
        String separator = File.separator;
        if (!modeAjuda){
            if (!colorsRepetits){
                if (numBoles == 4) ruta = folderRankingClassic() + separator + "RankingClassic_4.txt";
                else if (numBoles == 6) ruta = folderRankingClassic() + separator + "RankingClassic_6.txt";
                else if (numBoles == 8) ruta = folderRankingClassic() + separator + "RankingClassic_8.txt";
            }
            else{
                if (numBoles == 4) ruta = folderRankingClassic() + separator + "RankingClassic_4_C.txt";
                else if (numBoles == 6) ruta = folderRankingClassic() + separator + "RankingClassic_6_C.txt";
                else if (numBoles == 8) ruta = folderRankingClassic() + separator + "RankingClassic_8_C.txt";
            }
        }
        else{
            if (!colorsRepetits){
                if (numBoles == 4) ruta = folderRankingClassic() + separator + "RankingClassic_4_A.txt";
                else if (numBoles == 6) ruta = folderRankingClassic() + separator + "RankingClassic_6_A.txt";
                else if (numBoles == 8) ruta = folderRankingClassic() + separator + "RankingClassic_8_A.txt";
            }
            else{
                if (numBoles == 4) ruta = folderRankingClassic() + separator + "RankingClassic_4_A_C.txt";
                else if (numBoles == 6) ruta = folderRankingClassic() + separator + "RankingClassic_6_A_C.txt";
                else if (numBoles == 8) ruta = folderRankingClassic() + separator + "RankingClassic_8_A_C.txt";
            }
        }

        return ruta;
    }

    /**
     * Aquest mètode s'encarrega de guardar una ruta en la classe.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent.
     */
    public void setRuta(int numBoles, boolean modeAjuda, boolean colorsRepetits){
        this.ruta = archiveRankingClassic(numBoles, modeAjuda, colorsRepetits);
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
        ArrayList<String> path = pathRankingsClassic();
        for (String s : path) {
            file = new File(s);
            if (!file.exists()) {
                try {
                    file = new File(s);
                    if (!file.createNewFile()) System.out.println("Fitxer creat");

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for (int j = 0; j < 10; ++j) {
                        bw.write("Top " + (j + 1) + ") -- Player: EMPTY, Intents: 99, Temps: 99:99");
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