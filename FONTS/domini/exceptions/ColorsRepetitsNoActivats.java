package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que s'introdueixi una combinació de colors repetits amb l'opció desactivada.
 */
public class ColorsRepetitsNoActivats extends Exception {

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public ColorsRepetitsNoActivats(){
        super("Error: colors repetits no activats.");
    }
}