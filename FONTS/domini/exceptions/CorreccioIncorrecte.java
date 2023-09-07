package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que es s'introdueixi una correcció incorrecte en el mode codemaker.
 */
public class CorreccioIncorrecte extends Exception{

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public CorreccioIncorrecte(){
        super("Correcció incorrecta");
    }
}
