package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui guardar una partida, i aquest ja està finalitzada.
 */
public class PartidaNoGuardable extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public PartidaNoGuardable(){
        super("Partida no es pot guardar perquè ja està acabada");
    }
}
