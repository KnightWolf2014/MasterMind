package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui accedir a una posició de correcció línia, i aquest encara no existeix.
 */
public class PosicioCorreccioLineaNoDisponible extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public PosicioCorreccioLineaNoDisponible(){
        super("La posició que intenta accedir no existeix");
    }
}
