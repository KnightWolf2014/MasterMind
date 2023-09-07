package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui accedir a la solució, i aquesta encara no estigui assignada.
 */
public class NoSolucioDisponible extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public NoSolucioDisponible(){
        super("No s'ha introduït encara la solució");
    }
}
