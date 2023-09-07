package domini.model;

public class Classic extends Partida {

    /**
     * Creadora de la subclasse Classic
     */
    public Classic(){
        super();
    }

    /**
     * Creadora de la subclasse Classic
     *
     * @param nBol és un enter representa el nombre de boles que tindran els patrons de la Partida
     * @param modeA és un booleà que representa si s'ha activat o no el modeAjuda, que significa una Partida més fàcil
     * @param colorsR és un booleà que representa si es vol la possibilitat que la solució de la Partida pugui contenir colors repetits
     */
    public Classic(int nBol,boolean modeA, boolean colorsR){
        super(nBol, modeA, colorsR);
    }

    /**
     * Aquesta mètode indica si la Partida ha sigut acabada o no
     *
     * @return un booleà que indica si la Partida ha sigut acabada o no
     */
    public boolean partidaAcabada(){
        if (getGuanyat()) {
            return true;
        }

        else {
            return getMidaCorreccions() > getNIntents();
        }
    }

    /**
     * Aquest mètode retorna la puntuació de la Partida feta per l'Usuari
     *
     * @return una llista d'enters que representen els diferents paràmetres que avaluem per categoritzar si una Partida ha sigut millor o pitjor
     */
    public int[] obtenirPuntuacio(){
        //retorna un vector amb el nombre d'intents i el temps transcorregut
        //he fet servir un arraylist perquè pair no trobava manera de fer-ho en intellij
        if (getGuanyat()){
            int[] aux = new int[1];
            aux[0] = getMidaIntents();
            return aux;
        }
       return null;
    }

    public void nextPartida(){}

    public int getPartidesGuanyades(){
        return -1;
    }

}

