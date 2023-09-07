package domini.model;

import java.util.ArrayList;

public class RankingCrono {

    /**
     * Variable de la classe RankingCrono.
     */
    ArrayList<PosJugador> ranking;

    /**
     * Creadora de la classe RankingCrono.
     */
    public RankingCrono(){
        ranking = new ArrayList<>();
    }

    /**
     * Aquest mètode s'encarrega de guardar un rankingCrono.
     *
     * @param ranking és un ArrayList de PosJugador que representa el ranking.
     */
    public void setRanking(ArrayList<PosJugador> ranking){
        this.ranking = ranking;
    }

    /**
     * Aquest mètode s'encarrega de carregar un rankingCrono.
     *
     * @return  ranking és un ArrayList de PosJugador que representa el ranking.
     */
    public ArrayList<PosJugador> getRanking(){
        return ranking;
    }

    /**
     * Aquest mètode s'encarrega de traduir un ArrayList de strings a un ranking.
     *
     * @param ranking és un ArrayList de strings on cada string és una posició del ranking.
     */
    public void carregarRankingCrono(ArrayList<String> ranking) {

        if (!ranking.isEmpty()) this.ranking.clear();

        for (int i = 0; i < ranking.size(); ++i){
            String pos = ranking.get(i);
            int startIndex = pos.indexOf("Player: ") + "Player: ".length();
            int endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String player = pos.substring(startIndex, endIndex);

            startIndex = pos.indexOf("Victories: ") + "Victories: ".length();
            endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String stringVictories = pos.substring(startIndex, endIndex);
            int victories = Integer.parseInt(stringVictories);

            int indexIntents = pos.indexOf("Intents: ") + "Intents: ".length();
            String stringIntents = pos.substring(indexIntents);
            int intents = Integer.parseInt(stringIntents);

            PosJugador PRC = new PosJugador(intents, player, victories);
            this.ranking.add(i, PRC);
        }
    }

    /**
     * Aquest mètode s'encarrega de traduir un ranking a un ArrayList de strings.
     *
     * @return un ArrayList de string que és el contingut que després anirà guardat a un arxiu.
     */
    public ArrayList<String> guardarRankingCrono() {
        ArrayList<String> matRankings = new ArrayList<>();

        for (int i = 0; i < ranking.size(); ++i){
            PosJugador prc = ranking.get(i);
            String fila = "Top " + (i+1) + ") " + prc.obtePosicionsCrono();
            matRankings.add(fila);
        }

        return matRankings;
    }

    /**
     * Aquest mètode s'encarrega de comparar els resultats de la nova partida amb les partidas guardades al ranking per veure quina posició és millor.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param player és un string que representa el nom del jugador.
     * @param victories és un enter que representa el nombre de victories fetes pel jugador.
     * @return un boolèa. En cas que la nova posició sigui millor que les anteriors retorna un true, en cas contrari, un false.
     */
    public boolean afegirJugadorRanking(int intents, String player, int victories){
        PosJugador novaPosicio = new PosJugador(intents, player, victories);
        boolean anadido = false;

        if (ranking.isEmpty()){
            ranking.add(novaPosicio);
            anadido = true;
        }
        else {
            boolean pos_trobada = false;
            for (int i = 0; !pos_trobada && i < ranking.size(); ++i) {
                PosJugador posicionsGuardades = ranking.get(i);
                if (victories > posicionsGuardades.getVictories()) {
                    pos_trobada = true;
                    ranking.add(i, novaPosicio);
                    anadido = true;
                } else if (victories == posicionsGuardades.getVictories() && intents < posicionsGuardades.getIntents()) {
                    pos_trobada = true;
                    ranking.add(i, novaPosicio);
                    anadido = true;
                }
            }
            if (!pos_trobada && ranking.size() < 10) {
                ranking.add(novaPosicio);
                anadido = true;
                pos_trobada = true;
            }
            if (pos_trobada && ranking.size() > 10) ranking.remove(10);
        }

        return anadido;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si la nova posició és un record.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param victories és un enter que representa les victories fetes pel jugador.
     * @return un booleà. Retorna un true en cas que la nova posició sigui un record, un false en cas contrari.
     */
    public boolean primeraPosicio(int intents, int victories){
        if (victories > ranking.get(0).getVictories()) return true;
        else return victories == ranking.get(0).getVictories() && intents < ranking.get(0).getIntents();
    }

    /**
     * Aquest mètode s'encarrega de comprovar si el jugador accedirà al ranking.
     *
     * @param victories és un enter que representa les victories fetes pel jugador.
     * @param intents és un enter que representa els intents fets pel jugador.
     * @return un boolèa. Retorna true en cas que el jugador accedeixi al ranking, un false en cas contrari.
     */
    public boolean novaPosRanking(int victories, int intents){
        if (ranking.isEmpty()) return true;
        else {
            for (PosJugador posicionsGuardades : ranking) {
                if (victories > posicionsGuardades.getVictories()) return true;
                else if (victories == posicionsGuardades.getVictories() && intents < posicionsGuardades.getIntents())
                    return true;
            }
        }
        return false;
    }

    /**
     * Aquest mètode s'encarrega de mostrar el ranking per pantalla.
     *
     * @param numBoles es un enter per indicar el nombre de boles.
     * @param modeAjuda és un booleà per indicar si has jugat amb el mode ajuda o no.
     * @param colorsRepetits és un booleà per indicar si has jugat amb el mode de colors repetits o no.
     * @param cronometre és un enter que indica el temps triat per al mode crono.
     */
    public void mostrarRanking(int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre){
        int top = 1;

        String ajuda;
        if (modeAjuda) ajuda = "SI";
        else ajuda = "NO";

        String repetits;
        if (colorsRepetits) repetits = "SI";
        else repetits = "NO";

        System.out.println("Ranking Mode Crono -> " + cronometre + " minuts amb " + numBoles + " boles");
        System.out.println("ModeAjuda: " + ajuda + " --- ColorsRepetits: " + repetits);
        for (PosJugador pos : ranking) {
            System.out.println("Top " + top + ") Player: " + pos.getPlayer() + ", Victories: " + pos.getVictories() + ", Intents: " + pos.getIntents());
            ++top;
        }
    }

    /**
     * Aquest mètode s'encarrega de preparar l'Array de Strings dels ranking crono que es veuran des de la presentació.
     *
     * @return un ArrayList de Strings que representan els rankings.
     */
    public ArrayList<String> getRankingsPresentacio(){
        ArrayList<String> matRankings = new ArrayList<>();
        String fila;
        for (int i = 0; i < ranking.size(); ++i){
            PosJugador prc = ranking.get(i);
            if (!prc.getPlayer().equals("EMPTY")) {
                fila = "Top " + (i + 1) + ") " + prc.obtePosicionsCrono();
            }
            else{
                if (i <= 8) fila = "Top " + (i + 1) + ") " + "Player: --------- , Victories: --- , Intents: --- ";
                else fila = "Top " + (i + 1) + ") " + "Player: ------- , Victories: --- , Intents: --- ";
            }
            matRankings.add(fila);
        }
        return matRankings;
    }
}