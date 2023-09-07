package domini.model;

import java.util.ArrayList;

public class RankingClassic {

    /**
     * Variable de la classe RankingClassic.
     */
    ArrayList<PosJugador> ranking;

    /**
     * Creadora de la classe RankingClassic.
     */
    public RankingClassic(){
        ranking = new ArrayList<>();
    }

    /**
     * Aquest mètode s'encarrega de guardar un rankingClassic.
     *
     * @param ranking és un ArrayList de PosJugador que representa el ranking.
     */
    public void setRanking(ArrayList<PosJugador> ranking){
        this.ranking = ranking;
    }

    /**
     * Aquest mètode s'encarrega de carregar un rankingClassic.
     *
     * @return un ArrayList de PosJugador que representa el ranking.
     */
    public ArrayList<PosJugador> getRanking(){
        return ranking;
    }

    /**
     * Aquest mètode s'encarrega de traduir un ArrayList de strings a un ranking.
     *
     * @param ranking és un ArrayList de strings on cada string és una posició del ranking.
     */
    public void carregarRankingClassic(ArrayList<String> ranking) {

        if (!ranking.isEmpty()) this.ranking.clear();

        for (int i = 0; i < ranking.size(); ++i){
            String pos = ranking.get(i);
            int startIndex = pos.indexOf("Player: ") + "Player: ".length();
            int endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String player = pos.substring(startIndex, endIndex);

            startIndex = pos.indexOf("Intents: ") + "Intents: ".length();
            endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String stringIntents = pos.substring(startIndex, endIndex);
            int intents = Integer.parseInt(stringIntents);

            int indexTemps = pos.indexOf("Temps: ") + "Temps: ".length();
            String stringTemps = pos.substring(indexTemps);

            String[] numeros = stringTemps.split(":");
            int minutos = Integer.parseInt(numeros[0]);
            int segundos = Integer.parseInt(numeros[1]);
            int[] temps = new int[] {minutos, segundos};

            PosJugador PRC = new PosJugador(intents, player, temps);
            this.ranking.add(i, PRC);
        }
    }

    /**
     * Aquest mètode s'encarrega de traduir un ranking a un ArrayList de strings.
     *
     * @return un ArrayList de string que és el contingut que després anirà guardat a un arxiu.
     */
    public ArrayList<String> guardarRankingClassic() {
        ArrayList<String> matRankings = new ArrayList<>();

        for (int i = 0; i < ranking.size(); ++i){
            PosJugador prc = ranking.get(i);
            String fila = "Top " + (i+1) + ") " + prc.obtePosicionsClassic();
            matRankings.add(fila);
        }

        return matRankings;
    }

    /**
     * Aquest mètode s'encarrega de comparar els resultats de la nova partida amb les partidas guardades al ranking per veure quina posició és millor.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param player és un string que representa el nom del jugador.
     * @param temps és un vector d'enters que representa el temps fet pel jugador.
     * @return un boolèa. En cas que la nova posició sigui millor que les anteriors retorna un true, en cas contrari, un false.
     */
    public boolean afegirJugadorRanking(int intents, String player, int[] temps){
        PosJugador novaPosicio = new PosJugador(intents, player, temps);
        boolean anadido = false;

        if (ranking.isEmpty()){
            ranking.add(novaPosicio);
            anadido = true;
        }
        else {
            boolean pos_trobada = false;
            for (int i = 0; !pos_trobada && i < ranking.size(); ++i) {
                PosJugador posicionsGuardades = ranking.get(i);
                if (intents < posicionsGuardades.getIntents()) {
                    pos_trobada = true;
                    ranking.add(i, novaPosicio);
                    anadido = true;
                } else if (intents == posicionsGuardades.getIntents() && comparaTemps(temps, posicionsGuardades.getTemps())) {
                    pos_trobada = true;
                    ranking.add(i, novaPosicio);
                    anadido = true;
                }
            }
            if (!pos_trobada && ranking.size() < 10){
                ranking.add(novaPosicio);
                anadido = true;
                pos_trobada = true;
            }
            if (pos_trobada && ranking.size() > 10) ranking.remove(10);
        }
        return anadido;
    }

    /**
     * Aquest mètode s'encarrega de comparar el temps de la partida amb el millor temps per veure si és un record.
     *
     * @param tempsNou és un vector d'enters que és el temps de la partida.
     * @param tempsPrimeraPos és un vector d'enters que és el temps de la primera posició.
     * @return un booleà. En cas que el temps de la nova posició sigui menor es retorna un true, en cas contrari, un false.
     */
    private boolean comparaTemps(int[] tempsNou, int[] tempsPrimeraPos){
        //  0     1
        //minuts segons
        if (tempsNou[0] < tempsPrimeraPos[0]) return true;
        else{
            return tempsNou[1] < tempsPrimeraPos[1];
        }
    }

    /**
     * Aquest mètode s'encarrega de comprovar si la nova posició és un record.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param temps és un vector d'enters que representa el temps fet pel jugador.
     * @return un booleà. Retorna un true en cas que la nova posició sigui un record, un false en cas contrari.
     */
    public boolean primeraPosicio(int intents, int[] temps){
        if (intents < ranking.get(0).getIntents()) return true;
        else return intents == ranking.get(0).getIntents() && comparaTemps(temps, ranking.get(0).getTemps());
    }

    /**
     *Aquest mètode s'encarrega de comprovar si el jugador accedirà al ranking.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param temps és un vector d'enters que representa el temps fet pel jugador.
     * @return un boolèa. Retorna true en cas que el jugador accedeixi al ranking, un false en cas contrari.
     */
    public boolean novaPosRanking(int intents, int[] temps){
        if (ranking.isEmpty()) return true;
        else {
            for (PosJugador posicionsGuardades : ranking) {
                if (intents < posicionsGuardades.getIntents()) return true;
                else if (intents == posicionsGuardades.getIntents() && comparaTemps(temps, posicionsGuardades.getTemps())) return true;
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
     */
    public void mostrarRanking(int numBoles, boolean modeAjuda, boolean colorsRepetits){
        int top = 1;

        String ajuda;
        if (modeAjuda) ajuda = "SI";
        else ajuda = "NO";

        String repetits;
        if (colorsRepetits) repetits = "SI";
        else repetits = "NO";

        System.out.println("Ranking Mode Classic -> " + numBoles + " boles");
        System.out.println("ModeAjuda: " + ajuda + " --- ColorsRepetits: " + repetits);
        for (PosJugador pos : ranking) {
            System.out.println("Top " + top + ") Player: " + pos.getPlayer() + ", Intents: " + pos.getIntents() + ", Temps: " + pos.getTemps()[0] + ":" + pos.getTemps()[1]);
            ++top;
        }
    }

    /**
     * Aquest mètode s'encarrega de preparar l'Array de Strings dels ranking classic que es veuran des de la presentació.
     *
     * @return un ArrayList de Strings que representan els rankings.
     */
    public ArrayList<String> getRankingsPresentacio(){
        ArrayList<String> matRankings = new ArrayList<>();
        String fila;
        for (int i = 0; i < ranking.size(); ++i){
            PosJugador prc = ranking.get(i);
            if (!prc.getPlayer().equals("EMPTY")) {
                fila = "Top " + (i + 1) + ") " + prc.obtePosicionsClassic();
            }
            else{
                if (i <= 8) fila = "Top " + (i + 1) + ") " + "Player: --------- , Intents: --- , Temps: --- : --- ";
                else fila = "Top " + (i + 1) + ") " + "Player: ------- , Intents: --- , Temps: --- : --- ";
            }
            matRankings.add(fila);
        }
        return matRankings;
    }
}