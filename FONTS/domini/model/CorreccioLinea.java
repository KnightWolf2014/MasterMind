package domini.model;
import domini.exceptions.*;

import java.util.Random;
import java.util.ArrayList;

public class CorreccioLinea {

    /**
     * Variables de la classe CorreccioLinea.
     */
    private int[] correccioLinea;
    private static int numBoles;
    private static boolean modeAjuda;
    private int negres;
    private int blanques;

    /**
     * Creadora de la classe CorreccioLinea.
     */
    public CorreccioLinea(){
    }

    /**
     * Creadora de la classe CorreccioLinea.
     *
     * @param vecCorreccioLinea és un vector d'enters que representa la correcció del torn actual.
     */
    public CorreccioLinea(int[] vecCorreccioLinea){
        this.correccioLinea = vecCorreccioLinea;
        for (int i = 0; i < numBoles; ++i) {
            if (vecCorreccioLinea[i] == 1) ++blanques;
            if (vecCorreccioLinea[i] == 2) ++negres;
        }
    }

    /**
     * Creadora de la classe CorreccioLinea.
     *
     * @param negres és un enter que representa el nombre de pius negres de la correcció del torn actual.
     * @param blanques és un enter que representa el nombre de pius blancs de la correcció del torn actual.
     */
    public CorreccioLinea(int negres, int blanques) {
        this.negres = negres;
        this.blanques = blanques;
    }

    /**
     * Creadora  de la classe CorreccioLinea.
     *
     * @param numBoles és un enter que representa el nombre de boles de la partida.
     * @param modeAjuda és un booleà que representa si la partida s'està jugant o no amb el mode ajuda.
     */
    public CorreccioLinea(int numBoles, boolean modeAjuda){
        CorreccioLinea.numBoles = numBoles;
        CorreccioLinea.modeAjuda = modeAjuda;
    }

    /**
     * Creadora  de la classe CorreccioLinea.
     *
     * @param numBoles és un enter que representa el nombre de boles de la partida.
     * @param modeAjuda és un booleà que representa si la partida s'està jugant o no amb el mode ajuda.
     * @param vecCorreccioLinea és un vector d'enters que representa la correcció del torn actual.
     */
    public CorreccioLinea(int numBoles, boolean modeAjuda, int[] vecCorreccioLinea) {
        this.correccioLinea = vecCorreccioLinea;
        CorreccioLinea.numBoles = numBoles;
        CorreccioLinea.modeAjuda = modeAjuda;
    }

    /**
     * Aquest mètode s'encarrega de traduir un ArrayList de CorreccioLinea a una matriu d'enters.
     *
     * @param matArrayCorr és un Arraylist de la classe CorreccioLinea que representa el conjunt de correccions fetes fins ara a la partida.
     * @return una matriu de ints que és igual que l'ArrayList de CorreccioLinea.
     */
    public int[][] correccioToInt(ArrayList<CorreccioLinea> matArrayCorr) {
        int[][] matCorr = new int[matArrayCorr.size()][numBoles];

        for (int i = 0; i < matArrayCorr.size(); ++i){
            if (numBoles >= 0) System.arraycopy(matArrayCorr.get(i).correccioLinea, 0, matCorr[i], 0, numBoles);
        }
        return matCorr;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si els paràmetres donats són correctes.
     *
     * @param vecCorreccioLinea és un vector d'enters que representa una correcció.
     * @throws LlargadaPatroIncorrecte en cas que la mida de la correcció introduïda sigui diferent el nombre de boles amb les quals s'està jugant a la partida.
     * @throws ColorsIncorrectes en cas que els colors de la correcció introduïda sigui diferent dels colors disponibles a la correcció.
     */
    public void comprovarParametres(int[] vecCorreccioLinea) throws LlargadaPatroIncorrecte, ColorsIncorrectes {
        if (vecCorreccioLinea.length != numBoles) throw new LlargadaPatroIncorrecte();
        for (int j : vecCorreccioLinea) {
            if (j > 2 || j < 0) throw new ColorsIncorrectes("CorreccioLinea");
        }
    }

    /**
     * Aquest mètode s'encarrega de retornar el nombre de boles guardat.
     *
     * @return un enter amb el nombre de boles amb la que s'està jugant la partida.
     */
    public int getNumBoles() {
        return numBoles;
    }

    /**
     * Aquest mètode s'encarrega de retornar si la partida s'està jugant en mode ajuda o no.
     *
     * @return un booleà que representa si la partida té el mode ajuda activat.
     */
    public boolean getModeAjuda() {
        return modeAjuda;
    }

    /**
     * Aquest mètode s'encarrega de retornar la correcció guardada.
     *
     * @return un vector d'enters que representa una correcció.
     */
    public int[] getCorreccioLinea() {
        return this.correccioLinea;
    }

    /**
     * Aquest mètode s'encarrega d'agafar el color d'una posició de la correcció guardada.
     *
     * @param pos és un enter que representa la posició del color que volem agafar.
     * @return un enter que és el color que volem agafar.
     * @throws PosicioCorreccioLineaNoDisponible en cas que la posició donada no existeixi.
     */
    public int getColor(int pos) throws PosicioCorreccioLineaNoDisponible {
        if (pos >= correccioLinea.length || pos <= -1) throw new PosicioCorreccioLineaNoDisponible();
        return this.correccioLinea[pos];
    }

    /**
     * Aquest mètode s'encarrega d'agafar el nombre de pius negres de la correcció.
     *
     * @return un enter que representa el nombre de pius negres.
     */
    public int getNegres() {
        return negres;
    }

    /**
     * Aquest mètode s'encarrega d'agafar el nombre de pius blancs de la correcció.
     *
     * @return un enter que representa el nombre de pius blancs.
     */
    public int getBlanques() {
        return blanques;
    }

    /**
     * Aquest mètode s'encarrega de retornar la CorreccióLinea passada com a paràmetre, pero de dos possibles maneres.
     * Si esta el mode ajuda activat, retorna la correcció tal qual, en cas contrari, randomitza la correcció.
     *
     * @param vecCorreccioLinea és un vector d'enters que representa la correcció del torn actual.
     * @return una CorreccioLinea randomitzada o no.
     */
    public CorreccioLinea actualitzaCorreccioLinea(int[] vecCorreccioLinea) {
        CorreccioLinea aux;
        if (modeAjuda) aux = new CorreccioLinea(vecCorreccioLinea);
        else aux = new CorreccioLinea(randomitzaCorreccio(vecCorreccioLinea));
        return aux;
    }

    /**
     * Aquest mètode s'encarrega de randomitzar la correcció que es passa com a paràmetre.
     *
     * @param vecCorreccioLinea és un vector d'enters que representa una correcció.
     * @return el mateix vector d'enters però randomitzat.
     */
    private int[] randomitzaCorreccio(int[] vecCorreccioLinea) {
        Random random = new Random();

        //vector a retornar randomitzat
        int[] solucio = new int[vecCorreccioLinea.length];

        //vector de falses per a comprovar els canvis
        boolean[] patroAux = new boolean[vecCorreccioLinea.length];

        //randomitzar el vector
        int i = 0;
        while (i < vecCorreccioLinea.length){
            int pos = random.nextInt(vecCorreccioLinea.length);
            if (!patroAux[pos]){
                solucio[i] = vecCorreccioLinea[pos];
                patroAux[pos] = true;
                ++i;
            }
        }
        return solucio;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si el paràmetre és igual a la correcció.
     *
     * @param o objecte que pot pertànyer a qualsevol classe.
     * @return un booleà. En cas que sigui igual retorna una true, en cas contrari, un false.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorreccioLinea controlRow = (CorreccioLinea) o;

        try {
            return negres == controlRow.getNegres() && blanques == controlRow.getBlanques();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
