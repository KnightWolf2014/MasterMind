package domini.exceptions;

/**
 * Aquesta excepció es crida en cas que s'introdueixi un nombre no possible en els patrons i les combinacions.
 */
public class ColorsIncorrectes extends Exception {

    private final String error;

    /**
     * Creadora de la classe ColorsIncorrectes.
     */
    public ColorsIncorrectes(String error){
        super();
        this.error = error;
    }

    /**
     * Aquest mètode retorna un missatge quan es cridi al warning.
     */
    public String getMessage(){
        String missatge = "";
        //per als patrons
        if (error.equals("Patro")){
            missatge = "Error: els números introduïts han de estar entre 0 i 7.";
        }
        //per a les correccions línia
        else if (error.equals("CorreccioLinea")) {
            missatge = "Error: els números introduïts han de estar entre 0 i 2.";
        }
        return missatge;
    }
}