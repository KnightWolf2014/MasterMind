package domini.model;

import domini.exceptions.PosicioPatroNoDisponible;

public abstract class Jugador {

    /**
     * Variables de la classe Jugador.
     */
    private final int rolJugador;

    /**
     * Creadora de la classe Jugador.
     *
     * @param rol és el rol que farà l'usuari durant la partida.
     */
    public Jugador(int rol){
        rolJugador = rol;
    }

    /**
     * Creador de la classe Jugador.
     */
    protected Jugador() {rolJugador = 0;}

    /**
     * Aquest mètode s'encarrega de retornar el rol de la partida.
     *
     * @return un enter amb el nombre que identifica el rol, 0 per al codemaker i 1 per al codebreaker.
     */
    public int getRol(){
        return rolJugador;
    }

    /**
     * Aquest mètode s'encarrega de retornar la solució.
     *
     * @return un Patro que representa la solució.
     */
    public abstract Patro getSolucio();

    /**
     * Aquest mètode decideix el pròxim intent que es provarà.
     *
     * @return el següent patró que es provarà.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public abstract Patro getIntent() throws Exception;

    /**
     * Aquest mètode s'encarrega de guardar la correcció feta per l'usuari.
     *
     * @param solucio és la combinació donada per l'usuari que conté el nombre de pius blancs i negres.
     */
    public abstract void setCorreccio(CorreccioLinea solucio);
}