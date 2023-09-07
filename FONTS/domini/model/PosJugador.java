package domini.model;

public class PosJugador {

    /**
     * Variables de la classe PosJugador.
     */
    private int intents;
    private int[] temps;
    private String player;
    private int victories;

    /**
     * Creadora de la classe PosJugador. Usada per als rankings classic.
     *
     * @param intents és un enter que representa els intents fets a la partida.
     * @param player és un string que és el nom del jugador.
     * @param temps és un vector d'enters que indica el temps que s'ha trigat a completar la partida.
     */
    public PosJugador(int intents, String player, int[] temps){
        this.intents = intents;
        this.temps = temps;
        this.player = player;
    }

    /**
     * Creadora de la classe PosJugador. Usada per als rankings crono.
     *
     * @param intents és un enter que representa els intents fets a la partida.
     * @param player és un string que és el nom del jugador.
     * @param victories és un enter que indica la quantitat de victories fetes.
     */
    public PosJugador(int intents, String player, int victories){
        this.intents = intents;
        this.player = player;
        this.victories = victories;
    }

    /**
     * Aquest mètode s'encarrega de traduir els atributs de la classe en un string per al ranking classic.
     *
     * @return un string que conté la informació dels atributs de la classe.
     */
    public String obtePosicionsClassic(){
        return "Player: " + this.player + ", Intents: " + this.intents + ", Temps: " + this.temps[0] + ":" + this.temps[1];
    }

    /**
     * Aquest mètode s'encarrega de traduir els atributs de la classe en un string per al ranking crono.
     *
     * @return un string que conté la informació dels atributs de la classe.
     */
    public String obtePosicionsCrono(){
        return "Player: " + this.player + ", Victories: " + this.victories + ", Intents: " + this.intents;
    }


    /**
     * Aquest mètode s'encarrega guardar els intents en la classe.
     *
     * @param intents és un enter que correspon al nombre d'intents.
     */
    public void setIntents(int intents){ this.intents = intents; }

    /**
     * Aquest mètode s'encarrega de guardar els intents en la classe.
     *
     * @param temps és un vector d'enters que guarda els minuts, els segons.
     */
    public void setTemps(int[] temps){ this.temps = temps; }

    /**
     * Aquest mètode s'encarrega de guardar el jugador en la classe.
     *
     * @param player és un string que correspon al nom del jugador.
     */
    public void setPlayer(String player){
        this.player = player;
    }

    /**
     * Aquest mètode s'encarrega guardar les victories en la classe.
     *
     * @param victories és un enter que correspon al nombre de victories.
     */
    public void setVictories(int victories){ this.victories = victories; }

    /**
     * Aquest mètode s'encarrega de retornar els intents.
     *
     * @return un enter que és el nombre d'intents.
     */
    public int getIntents(){
        return this.intents;
    }

    /**
     * Aquest mètode s'encarrega de retornar el temps.
     *
     * @return un vector d'enters que representa els minuts, els segons.
     */
    public int[] getTemps(){
        return this.temps;
    }

    /**
     * Aquest mètode s'encarrega de retornar el jugador.
     *
     * @return un string que és el nom del jugador.
     */
    public String getPlayer(){
        return this.player;
    }

    /**
     * Aquest mètode s'encarrega de retornar les victories.
     *
     * @return un enter que és el nombre de victories.
     */
    public int getVictories(){
        return this.victories;
    }

}