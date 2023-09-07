package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui accedir a un intent, i aquest encara no s'hagi fet cap.
 */
public class NoIntentDisponible extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public NoIntentDisponible(){
        super("No s'ha introduït cap intent encara");
    }
}
