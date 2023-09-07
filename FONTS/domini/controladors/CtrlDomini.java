package domini.controladors;

import domini.exceptions.*;
import domini.model.*;
import persistencia.controladors.CtrlPersistencia;

import java.util.ArrayList;

public class CtrlDomini {

    /**
     * Variables de la classe CtrlDomini.
     */
    private Partida partida;
    private Algoritme algoritme;
    private Usuari usuari;
    private Patro patro;
    private CorreccioLinea correccioLinea;
    private RankingClassic rankingClassic;
    private RankingCrono rankingCrono;
    private final CtrlPersistencia ctrlPersistencia;
    private Records records;
    private static int numBoles;
    private static boolean colorsRepetits;
    private static boolean modeAjuda;
    private static int rol; //0 codemaker -- 1 codebreaker
    private static int cronometre; //0 -> modeClassic, 5 -> 5 minuts, 10 -> 10 minuts
    private static int[] temps; //[0] -> minuts -- [1] --> segons

    /**
     * Creadora de la classe CtrlDomini.
     */
    public CtrlDomini() {
        ctrlPersistencia = new CtrlPersistencia();
    }

    /**
     * Aquest mètode s'encarrega d'inicialitzar un joc i tot el que comporta aquesta acció amb les variables donades.
     *
     * @param rol            és un enter que identifica si la partida s'està jugant com a codebreaker o com a codemaker.
     * @param numBoles       és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant amb colors repetits o no.
     * @param modeAjuda      és un booleà que representa si la partida s'està jugant amb el mode ajuda activat o no.
     * @param cronometre     és un enter que identifica el cronometre de la partida en modo crono.
     */
    public void inicialitzarJoc(int rol, int numBoles, boolean colorsRepetits, boolean modeAjuda, int modeJoc, int cronometre) {

        //Inicialitzar parametres
        CtrlDomini.numBoles = numBoles;
        CtrlDomini.colorsRepetits = colorsRepetits;
        CtrlDomini.modeAjuda = modeAjuda;
        CtrlDomini.rol = rol;
        CtrlDomini.cronometre = cronometre;

        patro = new Patro(numBoles, colorsRepetits);
        correccioLinea = new CorreccioLinea(numBoles, modeAjuda);
        if (CtrlDomini.rol == 0) {
            if (numBoles == 4 || (numBoles == 6 && !colorsRepetits)) algoritme = new FiveGuess(rol);
            else algoritme = new Genetic(rol);
        } else algoritme = new Algoritme(rol);
        usuari = new Usuari(rol);
        records = new Records();

        if (modeJoc == 0) {
            partida = new Classic(numBoles, modeAjuda, colorsRepetits);
            rankingClassic = new RankingClassic();
        } else {
            partida = new Crono(numBoles, modeAjuda, colorsRepetits);
            rankingCrono = new RankingCrono();
        }

        if (CtrlDomini.rol == 1 && modeJoc == 0) resetejarPartida();
    }

    /**
     * Aquest mètode s'encarrega de donar la solució del joc per al mode codemaker.
     *
     * @param solucioV és un vector d'enters que representa la solució donada.
     * @return un booleà. Retorna un true en cas que calgui posar una altre solució i retorna un false en cas contrari.
     * @throws LlargadaPatroIncorrecte en cas que la mida de la correcció introduïda sigui diferent el nombre de boles amb les quals s'està jugant a la partida.
     */
    public boolean marcarSolucio(int[] solucioV) throws Exception {
        boolean ex = false;
        try {
            patro.comprovarParametres(solucioV);
        } catch (ColorsRepetitsNoActivats | ColorsIncorrectes e) {
            ex = true;
        }
        if (!ex) {
            Patro solucio = new Patro(solucioV);
            usuari.setSolucio(solucio);
            partida.passarSolucio(solucio);
        }
        return ex;
    }

    /**
     * Aquest mètode s'encarrega de retornar una matriu amb els intents que es porten a la partida.
     *
     * @return una matriu d'enters amb els intents. Cada fila és un intent i cada columna un valor de l'intent.
     */
    public int[][] getVectorIntents() {
        ArrayList<Patro> vecArrayInt = partida.getVectorIntents();
        return patro.patroToInt(vecArrayInt);
    }

    /**
     * Aquest mètode s'encarrega de retornar la solució guardada.
     *
     * @return un vector d'enters que representa la solució guardada.
     */
    public int[] getSolucio() {
        return partida.getSolucio().getPatro();
    }

    /**
     * Aquest mètode s'encarrega de retornar una matriu amb les correccions que es porten a la partida.
     *
     * @return una matriu d'enters amb les correccions. Cada fila és una correcció i cada columna un valor de la correcció.
     */
    public int[][] getVectorCorreccions() {
        ArrayList<CorreccioLinea> vecArrayCorr = partida.getVectorCorreccions();
        return correccioLinea.correccioToInt(vecArrayCorr);
    }

    /**
     * Aquest mètode s'encarrega de demanar-l'hi a la màquina l'intent.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void ferTorn() throws PosicioPatroNoDisponible {
        Patro intent = algoritme.getIntent();
        partida.passarIntent(intent);
    }

    /**
     * Aquest mètode s'encarrega d'enviar a la partida la correcció feta pel jugador.
     *
     * @param correccioV és un vector d'enters que representa una correcció.
     * @return un booleà. Retorna true en cas que la correcció donada per l'usuari sigui correcte, un false en cas contrari.
     * @throws CorreccioIncorrecte      en cas que la correccio no sigui correcte.
     * @throws NoIntentDisponible       en cas que no existeixi cap intent.
     * @throws NoSolucioDisponible      en cas que encara no s'hagi passat la solució.
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi.
     * @throws NoCorreccioDisponible    en cas de que encara no existeixi cap correcció.
     * @throws LlargadaPatroIncorrecte  en cas que la mida del torn introduït sigui diferent el nombre de boles amb les quals s'està jugant a la partida.
     * @throws ColorsIncorrectes        en cas que els colors del torn introduït sigui diferent dels colors disponibles de la partida.
     * @throws NoCorreccioDisponible    en cas de que encara no existeixi cap correcció.
     */
    public boolean ferCorreccio(int[] correccioV) throws Exception {
        try {
            correccioLinea.comprovarParametres(correccioV);
        } catch (ColorsIncorrectes e) {
            return false;
        }
        CorreccioLinea correccioLinea = new CorreccioLinea(correccioV);
        try {
            partida.checkCorreccio(correccioLinea);
        } catch (CorreccioIncorrecte e) {
            return false;
        } finally {
            algoritme.setCorreccio(partida.getCorreccioActual());
        }
        return true;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si la partida s'ha acabat o no.
     *
     * @return un booleà. Retorna un true en cas afirmatiu i un false en cas contrari.
     */
    public boolean partidaAcabada() {
        return partida.partidaAcabada();
    }


    /**
     * Aquest mètode s'encarrega de comprovar si la partida jugada s'ha guanyat o no.
     *
     * @return un booleà. Retorna un true en cas afirmatiu i un false en cas contrari.
     */
    public boolean partidaGuanyada() {
        return partida.getGuanyat();
    }

    /**
     * Aquest mètode s'encarrega que la màquina estableixi una solució per a la partida.
     */
    public void establirSolucio() {
        Patro solucio = algoritme.setSolucio();
        partida.passarSolucio(solucio);
    }

    /**
     * Aquest mètode s'encarrega d'enviar a la partida l'intent fet pel jugador.
     *
     * @param intentV és un vector d'enters que representa l'intent fet.
     * @throws LlargadaPatroIncorrecte  en cas que la mida del torn introduït sigui diferent el nombre de boles amb les quals s'està jugant a la partida.
     * @throws ColorsIncorrectes        en cas que els colors del torn introduït sigui diferent dels colors disponibles de la partida.
     * @throws NoSolucioDisponible      en cas que encara no s'hagi passat la solució.
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi.
     */
    public void ferIntent(int[] intentV) throws Exception {
        boolean ex = false;
        try {
            patro.comprovarParametres(intentV);
        } catch (ColorsRepetitsNoActivats | ColorsIncorrectes e) {
            ex = true;
        }
        if (!ex) {
            Patro intent = new Patro(intentV);
            partida.passarIntent(intent);
            partida.checkIntent(intent);
        }
    }

    /**
     * Aquest mètode s'encarrega de fer començar una nova partida.
     */
    public void nextPartida(){
        partida.nextPartida();
    }

    /**
     * Aquest mètode s'encarrega de retornar el nombre de partides guanyades.
     *
     * @return un enter que representa el nombre de partides guanyades.
     */
    public int getPartidesGuanyades(){
        return partida.getPartidesGuanyades();
    }

    /**
     * Aquest mètode s'encarrega d'obtenir la puntuació de la partida.
     *
     * @return un vector d'enters. En cas que el mode de joc sigui el classic, un vector amb el nombre d'intents. En cas contrari, un vector amb el nombre de victories i d'intents totals.
     */
    public int[] obtenirPuntuacio() {
        resetejarPartida();
        return partida.obtenirPuntuacio();
    }

    /**
     * Aquest mètode s'encarrega d'inicialitzar la persistencia de l'aplicació.
     */
    public void inicialitzarPersistencia() {
        records = new Records();

        rankingClassic = new RankingClassic();
        rankingCrono = new RankingCrono();
    }

    /**
     * Aquest mètode s'encarrega de carregar els rankings classic al joc.
     *
     * @param numBoles       és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant amb colors repetits o no.
     * @param modeAjuda      és un booleà que representa si la partida s'està jugant amb el mode ajuda activat o no.
     */
    public void carregaRankingsClassic(int numBoles, boolean modeAjuda, boolean colorsRepetits) {
        ArrayList<String> ranking = ctrlPersistencia.carregarRankingClassic(numBoles, modeAjuda, colorsRepetits);
        rankingClassic.carregarRankingClassic(ranking);
    }

    /**
     * Aquest mètode s'encarrega de carregar els rankings crono al joc.
     *
     * @param numBoles       és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant amb colors repetits o no.
     * @param modeAjuda      és un booleà que representa si la partida s'està jugant amb el mode ajuda activat o no.
     * @param cronometre     és un enter que identifica el cronometre de la partida en modo crono.
     */
    public void carregaRankingsCrono(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre) {
        ArrayList<String> ranking = ctrlPersistencia.carregarRankingCrono(numBoles, modeAjuda, colorsRepetits, cronometre);
        rankingCrono.carregarRankingCrono(ranking);
    }

    /**
     * Aquest mètode s'encarrega de mostrar el ranking classic seleccionat.
     *
     * @param numBoles       és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant amb colors repetits o no.
     * @param modeAjuda      és un booleà que representa si la partida s'està jugant amb el mode ajuda activat o no.
     */
    public void mostrarRankingsClassic(int numBoles, boolean modeAjuda, boolean colorsRepetits) {
        rankingClassic.mostrarRanking(numBoles, modeAjuda, colorsRepetits);
    }

    /**
     * Aquest mètode s'encarrega de mostrar el ranking crono seleccionat.
     *
     * @param numBoles       és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant amb colors repetits o no.
     * @param modeAjuda      és un booleà que representa si la partida s'està jugant amb el mode ajuda activat o no.
     * @param cronometre     és un enter que identifica el cronometre de la partida en modo crono.
     */
    public void mostrarRankingsCrono(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre) {
        rankingCrono.mostrarRanking(numBoles, modeAjuda, colorsRepetits, cronometre);
    }

    /**
     * Aquest mètode s'encarrega de retornar el ranking classic perquè es pugui veure a la presentació.
     *
     * @return un ArrayList de Strings que representa el ranking.
     */
    public ArrayList<String> getRankingClassic() {
        return rankingClassic.getRankingsPresentacio();
    }

    /**
     * Aquest mètode s'encarrega de retornar el ranking crono perquè es pugui veure a la presentació.
     *
     * @return un ArrayList de Strings que representa el ranking.
     */
    public ArrayList<String> getRankingCrono() {
        return rankingCrono.getRankingsPresentacio();
    }

    /**
     * Aquest mètode s'encarrega de retornar la llista de records perquè es puguin veure a la presentació.
     *
     * @return un ArrayList de Strings que representa la llista de records.
     */
    public ArrayList<String> getRecords() {
        return records.getRecordsPresentacio();
    }

    /**
     * Aquest mètode s'encarrega de guardar el ranking classic a l'arxiu.
     *
     * @param player  és un string que és el nom del jugador.
     * @param temps   és un vector d'enters que és el temps que ha durat la partida.
     * @param intents és un enter que és el nombre d'intents trigat en completar la partida.
     * @return un booleà. Retorna true en cas que la nova posició sigui la primera, un false en cas contrari.
     */
    public boolean guardarRankingClassic(String player, int[] temps, int intents) {
        boolean record = false;
        if (rankingClassic.primeraPosicio(intents, temps)) {
            record = true;
            records.afegirRecordClassic(intents, player, temps, numBoles, modeAjuda, colorsRepetits);
        }
        if (rankingClassic.afegirJugadorRanking(intents, player, temps)) {
            ArrayList<String> ranking = rankingClassic.guardarRankingClassic();
            ctrlPersistencia.guardarRankingClassic(numBoles, modeAjuda, colorsRepetits, ranking);
        }
        return record;
    }

    /**
     * Aquest mètode s'encarrega de guardar el ranking crono a l'arxiu.
     *
     * @param player    és un string que és el nom del jugador.
     * @param victories és un enter que és el nombre de victories fetes pel jugador.
     * @param intents   és un enter que és el nombre d'intents totals fets pel jugador a la partida.
     * @return un booleà. Retorna true en cas que la nova posició sigui la primera, un false en cas contrari.
     */
    public boolean guardarRankingCrono(String player, int victories, int intents) {
        boolean record = false;
        if (rankingCrono.primeraPosicio(intents, victories)) {
            record = true;
            records.afegirRecordCrono(intents, player, victories, numBoles, modeAjuda, colorsRepetits, cronometre);
        }
        if (rankingCrono.afegirJugadorRanking(intents, player, victories)) {
            ArrayList<String> ranking = rankingCrono.guardarRankingCrono();
            ctrlPersistencia.guardarRankingCrono(numBoles, modeAjuda, colorsRepetits, cronometre, ranking);
        }
        return record;
    }

    /**
     * Aquest mètode s'encarrega de guardar la partida a l'arxiu.
     *
     * @param temps és un vector d'enters que és el temps de la partida.
     * @throws PosicioPatroNoDisponible          en cas de que s'accedeixi a una posició del intent no disponible.
     * @throws PosicioCorreccioLineaNoDisponible en cas de que s'accedeixi a una posicio no disponible.
     */
    public void guardarPartida(int[] temps) throws Exception {
        partida.setTemps(temps);
        ArrayList<String> partidaString = partida.guardarPartida();
        ctrlPersistencia.guardarPartida(partidaString);
    }

    /**
     * Aquest mètode s'encarrega de carregar la partida al joc.
     */
    public void carregarPartida() {

        rol = 1;
        cronometre = 0;

        partida = new Classic();
        patro = new Patro();

        ArrayList<String> partidaString = ctrlPersistencia.carregarPartida();
        partida.carregarPartida(partidaString);
        numBoles = partida.getNumBoles();
        modeAjuda = partida.getModeAjuda();
        colorsRepetits = partida.getColorsRepetits();
        temps = partida.getTemps();

        rankingClassic = new RankingClassic();
        patro = new Patro(numBoles, colorsRepetits);
        correccioLinea = new CorreccioLinea(numBoles, modeAjuda);
        algoritme = new Algoritme(rol);
        usuari = new Usuari(rol);
        records = new Records();
    }

    /**
     * Aquest mètode s'encarrega de retornar el temps.
     *
     * @return un vector d'enters que representa els minuts i els segons.
     */
    public int[] getTemps() {
        return temps;
    }

    /**
     * Aquest mètode s'encarrega de retornar si la partida s'està jugant en mode ajuda o no.
     *
     * @return un booleà que representa si la partida té el mode ajuda activat.
     */
    public boolean getModeAjuda(){
        return modeAjuda;
    }

    /**
     * Aquest mètode s'encarrega de retornar si la partida s'està jugant en colors repetits o no.
     *
     * @return un booleà que representa si la partida té els colors repetits activats.
     */
    public boolean getColorsRepetits() {
        return colorsRepetits;
    }

    /**
     * Aquest mètode s'encarrega de retornar el nombre de boles guardat.
     *
     * @return un enter amb el nombre de boles amb la que s'està jugant la partida.
     */
    public int getNumBoles() {
        return numBoles;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si el jugador accedirà al ranking classic.
     *
     * @param intents és un enter que són els intents fets pel jugador a la partida.
     * @param temps   és un vector d'enters que és el temps fet pel jugador a la partida.
     * @return un boolèa. Retorna true en cas que el jugador accedeixi al ranking, un false en cas contrari.
     */
    public boolean novaPosRankingClassic(int intents, int[] temps) {
        return rankingClassic.novaPosRanking(intents, temps);
    }

    /**
     * Aquest mètode s'encarrega de comprovar si el jugador accedirà al ranking crono.
     *
     * @param victories és un enter que són el nombre de victories fetes pel jugador a la partida.
     * @param intents   és un enter que són els intents fets pel jugador a la partida.
     * @return un boolèa. Retorna true en cas que el jugador accedeixi al ranking, un false en cas contrari.
     */
    public boolean novaPosRankingCrono(int victories, int intents) {
        if (victories == 0) return false;
        return rankingCrono.novaPosRanking(victories, intents);
    }

    /**
     * Aquest mètode s'encarrega de comprovar si hi ha una partida guardada en els arxius.
     *
     * @return un booleà. Retorna true en cas que existeixi, un false en cas contrari.
     */
    public boolean partidaGuardada() {
        return ctrlPersistencia.partidaGuardada();
    }

    /**
     * Aquest mètode s'encarrega de guardar els records a l'arxiu.
     */
    public void guardarRecords() {
        ArrayList<String> recordsString = records.guardarRecords();
        ctrlPersistencia.guardarRecords(recordsString);
    }

    /**
     * Aquest mètode s'encarrega de carregar els records al joc.
     */
    public void carregarRecords() {
        ArrayList<String> recordString = ctrlPersistencia.carregarRecords();
        records.carregarRecords(recordString);
    }

    /**
     * Aquest mètode s'encarrega de mostrar el llistat de records.
     */
    public void mostrarRecords() {
        records.mostrarRecords();
    }

    /**
     * Aquest mètode s'encarrega de ficar l'arxiu d'una partida amb el model estandar per a resetejarla.
     */
    public void resetejarPartida(){
        ctrlPersistencia.resetejarPartida();
    }
}
