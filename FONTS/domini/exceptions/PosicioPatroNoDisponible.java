package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es vulgui accedir a una posició de patró, i aquesta sigui inaccessible.
 */
public class PosicioPatroNoDisponible extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public PosicioPatroNoDisponible(){
        super("La posició que intenta accedir no existeix");
    }
}
