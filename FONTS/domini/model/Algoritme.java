package domini.model;

import domini.exceptions.NoCorreccioDisponible;
import domini.exceptions.NoSolucioDisponible;
import domini.exceptions.PosicioPatroNoDisponible;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Algoritme extends Jugador implements Maquina{

    /**
     * Variables de la classe Algoritme.
     */
    private Patro solucio;
    public Algoritme(int rol) {
        super(rol);
    }

    /**
     * Creadora de la classe Algoritme.
     */
    public Algoritme() {}

    /**
     * Aquest mètode s'encarrega de crear una solució per quan es juga en mode codeBreaker.
     *
     * @return un Patró que representa la solució de la partida.
     */
    public Patro setSolucio() {
        solucio = new Patro();
        boolean repetit = solucio.getColorsRepetits();
        int numBoles = solucio.getNumBoles();
        int[] codi = new int[numBoles];
        if(!repetit){
            for(int i = 0; i < numBoles; i++){
                int random = ThreadLocalRandom.current().nextInt(0, 8);
                while(conte(codi, i, random)){
                    random = ThreadLocalRandom.current().nextInt(0, 8);
                }
                codi[i] = random;
            }
        }
        else {
            for(int i = 0; i < numBoles; i++){
                int random = ThreadLocalRandom.current().nextInt(0, 8);
                codi[i] = random;
            }
        }
        solucio.setPatro(codi);
        return solucio;
    }

    /**
     * Aquest mètode s'encarrega de retornar la solució.
     *
     * @return un Patró que representa la solució.
     */
    public Patro getSolucio(){ return solucio;}

    /**
     * Aquest mètode decideix el pròxim intent que es provarà.
     *
     * @return el següent patró que es provarà.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public Patro getIntent() throws PosicioPatroNoDisponible{
        return new Patro();
    }

    /**
     * Aquest mètode s'encarrega de comprovar si el valor del vector ja està ficat. Usat en el mode de no colors repetits.
     *
     * @param codi és un vector d'enters que és la solució que es porta creada.
     * @param i és la posició màxima que es porta de solució.
     * @param num és un enter que és el número a ficar en el vector.
     * @return retorna un booleà. Retorna un true en cas de que el número ja sigui dins del vector, retorna false en cas contrari.
     */
    private boolean conte(int[] codi, int i, int num){
        for(int j = 0; j < i; j++){
            if(codi[j] == num) return true;
        }
        return false;
    }

    /**
     * Aquest mètode s'encarrega de guardar la correcció feta per l'usuari.
     *
     * @param solucio és la combinació donada per l'usuari que conté el nombre de pius blancs i negres.
     */
    public void setCorreccio(CorreccioLinea solucio){}

    /**
     * Aquest mètode genera una llista amb tots els intents que s'han provat.
     *
     * @param solution llista amb les solucions.
     * @return una llista amb tots els patrons que s'han provat.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció.
     */
    public List<List<Integer>>  solve (List<Integer> solution) throws Exception {
        return new ArrayList<>();
    }

    /**
     * Aquest mètode compara dos patrons diferents.
     *
     * @param combinacio és una combinació de colors amb la qual comparar.
     * @param intentAnterior és una combinació de colors a avaluar.
     * @return Correcció línia amb el nombre de pius negres i blancs
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public CorreccioLinea comparaIntents (Patro combinacio, Patro intentAnterior) throws PosicioPatroNoDisponible {
        int negres = 0;
        int blanques = 0;

        //s'ha de fer una còpia, ja que la funció setColor() escriu en la posició de memòria i aquesta és la mateixa
        int[] sol = combinacio.getPatro();
        int[] s = Arrays.copyOf(sol, sol.length);
        Patro auxComb = new Patro(s);

        int[] guess = intentAnterior.getPatro();
        int[] a = Arrays.copyOf(guess, guess.length);
        Patro auxGuess = new Patro(a);

        int numBolesSol = auxComb.getPatro().length;
        int numBolesGuess = auxGuess.getPatro().length;

        //El 8 significa que no hi ha color
        //Calcula pius blancs
        for (int i = 0; i < numBolesSol; ++i) {
            if (auxComb.getColor(i) == auxGuess.getColor(i)) {
                negres += 1;
                auxComb.setColor(i, 8);
                auxGuess.setColor(i, 8);
            }
        }

        //Calcula pius negres
        for (int i = 0; i < numBolesSol; ++i) {
            boolean trobat = false;
            for (int j = 0; j < numBolesGuess; ++j) {
                if (!trobat && auxGuess.getColor(j) < 8) {
                    if (auxGuess.getColor(j) == auxComb.getColor(i)) {
                        blanques += 1;
                        trobat = true;
                        auxGuess.setColor(j, 8);
                    }
                }
            }
        }
        return new CorreccioLinea(negres, blanques);
    }
}
