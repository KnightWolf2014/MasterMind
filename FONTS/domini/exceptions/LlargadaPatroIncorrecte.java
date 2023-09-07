package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que s'introdueixi una llargada incorrecta.
 */
public class LlargadaPatroIncorrecte extends Exception {

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public LlargadaPatroIncorrecte(){
        super( "Error: a llargada de la seqüència introduïda no quadra amb la llargada de la seqüència establerta.");
    }
}