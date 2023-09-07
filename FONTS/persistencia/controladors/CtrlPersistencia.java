package persistencia.controladors;

import persistencia.model.*;

import java.util.ArrayList;

public class CtrlPersistencia {

    private final RankingClassicData rankingClassicData;
    private final RankingCronoData rankingCronoData;
    private final RecordsData recordsData;
    private final PartidaData partidaData;


    /**
     * Creadora de la classe CtrlPersistencia.
     */
    public CtrlPersistencia() {
        rankingClassicData = new RankingClassicData();
        rankingClassicData.creaArchives();

        rankingCronoData = new RankingCronoData();
        rankingCronoData.creaArchives();

        recordsData = new RecordsData();
        recordsData.creaArchive();

        partidaData = new PartidaData();
        partidaData.creaArchive();
    }

    /**
     * Aquest mètode s'encarrega de passar el ranking seleccionat a la classe RankingClassicData perquè el guardi en un arxiu.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent.
     * @param ranking és un ArrayList de strings que representa les posicions del ranking.
     */
    public void guardarRankingClassic(int numBoles, boolean modeAjuda, boolean colorsRepetits, ArrayList<String> ranking){
        rankingClassicData.setRuta(numBoles, modeAjuda, colorsRepetits);
        rankingClassicData.guardarRanking(ranking);
    }

    /**
     * Aquest mètode s'encarrega de passar el ranking seleccionat a la classe RankingClassicData perquè el guardi en un arxiu.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent.
     * @param cronometre és un enter que representa el temps del cronòmetre.
     * @param ranking és un ArrayList de strings que representa les posicions del ranking.
     */
    public void guardarRankingCrono(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre, ArrayList<String> ranking){
        rankingCronoData.setRuta(numBoles, modeAjuda, colorsRepetits, cronometre);
        rankingCronoData.guardarRanking(ranking);
    }

    /**
     * Aquest mètode s'encarrega de passar el ranking seleccionat a la classe RankingClassicData perquè el carregui des de l'arxiu corresponent.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent.
     * @return un ArrayList de strings amb les posicions del ranking seleccionat.
     */
    public ArrayList<String> carregarRankingClassic(int numBoles, boolean modeAjuda, boolean colorsRepetits){
        rankingClassicData.setRuta(numBoles, modeAjuda, colorsRepetits);
        return rankingClassicData.carregarRanking();
    }

    /**
     * Aquest mètode s'encarrega de passar el ranking seleccionat a la classe RankingCronoData perquè el carregui des de l'arxiu corresponent.
     *
     * @param numBoles és un enter que representa el nombre de boles per a seleccionar l'arxiu corresponent.
     * @param modeAjuda és un booleà que representa si has jugat en mode ajuda o no per a seleccionar l'arxiu corresponent.
     * @param colorsRepetits és un booleà que representa si has jugat en mode colors repetits o no per a seleccionar l'arxiu corresponent.
     * @param cronometre és un enter que representa el temps del cronòmetre.
     * @return un ArrayList de strings amb les posicions del ranking seleccionat.
     */
    public ArrayList<String> carregarRankingCrono(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre){
        rankingCronoData.setRuta(numBoles, modeAjuda, colorsRepetits, cronometre);
        return rankingCronoData.carregarRanking();
    }

    /**
     * Aquest mètode s'encarrega de passar els records a la classe RecordData perquè els guardi en un arxiu.
     *
     * @param records és un ArrayList de strings que representa els records.
     */
    public void guardarRecords(ArrayList<String> records){
        recordsData.guardarRecords(records);
    }

    /**
     * Aquest mètode s'encarrega de passar els records a la classe RecordData perquè el carregui des de l'arxiu corresponent.
     *
     * @return un ArrayList de strings amb els records.
     */
    public ArrayList<String> carregarRecords(){
        return recordsData.carregarRecords();
    }

    /**
     * Aquest mètode s'encarrega de passar la partida a la classe PartidaData perquè la guardi en un arxiu.
     *
     * @param partida és un ArrayList de strings que representa la partida
     */
    public void guardarPartida(ArrayList<String> partida){
        partidaData.guardarPartida(partida);
    }

    /**
     * Aquest mètode s'encarrega de passar la partida a la classe PartidaRecord perquè la carregui des de l'arxiu.
     *
     * @return un ArrayList de strings amb la partida.
     */
    public ArrayList<String> carregarPartida(){
        return partidaData.carregarPartida();
    }

    /**
     * Aquest mètode s'encarrega de comprovar si hi ha una partida guardada.
     *
     * @return un booleà. Retorna true en cas d'haver-hi una partida guardada, un false en cas contrari.
     */
    public boolean partidaGuardada(){
        return partidaData.partidaGuardada();
    }

    /**
     * Aquest mètode s'encarrega de ficar l'arxiu d'una partida amb el model estandar per a resetejarla.
     */
    public void resetejarPartida(){
        if (partidaData.partidaGuardada()) partidaData.resetejarPartida();
    }

}