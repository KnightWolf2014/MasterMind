package domini.model;

public class Usuari extends Jugador{

    /**
     * Variables de la classe Usuari.
     */
    private Patro solucio;
    private Patro ultimIntent;
    private CorreccioLinea ultimaCorreccio;

    /**
     * Creador de la classe Usuari.
     *
     * @param rol és el rol que farà l'usuari durant la partida.
     */
    public Usuari(int rol) {
        super(rol);
    }

    /**
     * Aquest mètode s'encarrega de guardar una solució a la classe.
     *
     * @param pSolucio és un Patro que representa una solució.
     */
    public void setSolucio(Patro pSolucio){ solucio = pSolucio;}

    /**
     * Aquest mètode s'encarrega de guardar un intent a la classe.
     *
     * @param intent és un Patro que representa un intent.
     */
    public void setIntent(Patro intent){ ultimIntent = intent;}

    /**
     * Aquest mètode s'encarrega de guardar una correcció a la classe.
     *
     * @param correccio és una CorreccioLinea que representa una correcció.
     */
    public void setCorreccio(CorreccioLinea correccio){ultimaCorreccio = correccio;}

    /**
     *
     *
     * @return una CorreccioLinea...
     */
    public CorreccioLinea corregirMaquina(){ return ultimaCorreccio;}

    public Patro getIntent(){return ultimIntent;}

    public Patro getSolucio(){return solucio;}
}
