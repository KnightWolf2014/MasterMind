package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui accedir a la correcció, i aquesta encara no estigui disponible.
 */
public class NoCorreccioDisponible extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public NoCorreccioDisponible(){
        super("No s'ha introduït cap correcció encara");
    }
}
