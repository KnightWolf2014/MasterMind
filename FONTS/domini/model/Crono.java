package domini.model;

import java.util.ArrayList;

public class Crono extends Partida{

    /**
     * VARIABLES
     */
    private int partidesGuanyades;
    private int numIntentsTotals;


    /**
     * Creadora de la subclasse Crono
     *
     * @param nBol és un enter representa el nombre de boles que tindran els patrons de la Partida
     * @param modeA és un booleà que representa si s'ha activat o no el modeAjuda, que significa una Partida més fàcil
     * @param colorsR és un booleà que representa si es vol la possibilitat que la solució de la Partida pugui contenir colors repetits
     */
    public Crono(int nBol,boolean modeA, boolean colorsR){
        super(nBol, modeA, colorsR);
        partidesGuanyades = 0;
        numIntentsTotals = 0;
    }

    public int getPartidesGuanyades(){
        return this.partidesGuanyades;
    }

    /**
     * Aquest mètode gestiona la Partida perquè es pugui passar a una Partida nova
     */
    public void nextPartida(){
        //comencem una nova partida, per tant, fiquem l'atribut guanyat a false
        //en aquest mode utilitzem l'atribut guanyat per passar o no a la següent partida
        ++partidesGuanyades;
        setGuanyat(false);
        numIntentsTotals += getMidaIntents();
        setIntents(new ArrayList<>());
        setCorreccions(new ArrayList<>());
        passarSolucio(null);
    }


    /**
     * Aquest mètode indica si la Partida ha sigut acabada o no
     *
     * @return un booleà que indica si la Partida ha sigut acabada o no
     */
    public boolean partidaAcabada(){
        //partida Acabada refereix a acabar el mode crono, no la partida actual que estem jugant
        //només es miraria amb els intents, pel temps s'hauria de fer interrupt
        //encara no hem esgotat el temps

        //que n hagi guanyat la partida, perquè aleshores acabaríem la partida, no el mode crono
        //i hem esgotat els intents de la partida actual
        //no s'ha acabat el temps i no s'ha acabat els intents
        return !getGuanyat() && getMidaIntents() > getNIntents();
    }

    /**
     * Aquest mètode retorna la puntuació de la Partida feta per l'Usuari
     *
     * @return una llista d'enters amb el nombre de partides guanyades i el nombre d'intents de tota la partida
     */
    public int[] obtenirPuntuacio(){
        //només passem la puntuació si aquesta és major de 0, si nó seria com "perdre" en el mode crono
        int[] aux = new int[2];
        aux[0] = partidesGuanyades;
        aux[1] = numIntentsTotals;
        return aux;
    }
}
