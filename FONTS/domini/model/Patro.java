package domini.model;

import domini.exceptions.*;

import java.util.ArrayList;

public class Patro{

    /**
     * Variables de la classe Patro.
     */
    private int[] patro;
    private static int numBoles;
    private static boolean colorsRepetits;

    /**
     * Creadora de la classe Patro.
     */
    public Patro(){
    }

    /**
     * Creadora de la classe Patro.
     *
     * @param vec és un vector d'enters que representa un torn.
     */
    public Patro(int[] vec){
        this.patro = vec;
    }

    /**
     * Creadora de la classe Patro.
     *
     * @param numBoles és un enter que representa el nombre de boles de la partida.
     * @param colorsRepetits és un booleà que representa si la partida té el mode colors repetits activat o no.
     */
    public Patro(int numBoles, boolean colorsRepetits){
        Patro.numBoles = numBoles;
        Patro.colorsRepetits = colorsRepetits;
    }

    /**
     * Aquest mètode s'encarrega de traduir un ArrayList de patrons a una matriu d'enters.
     *
     * @param matArrayInt és un Arraylist de la classe Patro que representa el conjunt de torns fets fins ara a la partida.
     * @return una matriu de ints que és igual que l'ArrayList de Patrons.
     */
    public int[][] patroToInt(ArrayList<Patro> matArrayInt) {
        int[][] matInt = new int[matArrayInt.size()][numBoles];
        for (int i = 0; i < matArrayInt.size(); ++i){
            if (numBoles >= 0) System.arraycopy(matArrayInt.get(i).patro, 0, matInt[i], 0, numBoles);
        }
        return matInt;
    }

    /**
     * Aquest mètode s'encarrega de comprovar si els paramètres donats són correctes.
     *
     * @param vecPatro és un vector d'enters que representa un torn.
     * @throws LlargadaPatroIncorrecte en cas que la mida del torn introduït sigui diferent el nombre de boles amb les quals s'està jugant a la partida.
     * @throws ColorsIncorrectes en cas que els colors del torn introduït sigui diferent dels colors disponibles de la partida.
     * @throws ColorsRepetitsNoActivats en cas que es posin colors repetits jugant amb els colors repetits desactivats.
     */
    public void comprovarParametres(int[] vecPatro) throws LlargadaPatroIncorrecte, ColorsIncorrectes, ColorsRepetitsNoActivats {
        if (vecPatro.length != numBoles) throw new LlargadaPatroIncorrecte();
        boolean[] patroAux = new boolean[8];
        for (int j : vecPatro) {
            if (j > 7 || j < 0) throw new ColorsIncorrectes("Patro");
            if (!colorsRepetits) {
                if (patroAux[j]) throw new ColorsRepetitsNoActivats();
                patroAux[j] = true;
            }
        }
    }

    /**
     * Aquest mètode s'encarrega de retornar el patro guardat.
     *
     * @return un vector d'enters que representa un torn.
     */
    public int[] getPatro() {
        return patro;
    }

    /**
     * Aquest mètode s'encarrega de guardar un patro en la classe.
     *
     * @param vecPatro és un vector d'enters que representa un torn.
     */
    public void setPatro(int[] vecPatro) {
        this.patro = vecPatro;
    }

    /**
     * Aquest mètode s'encarrega de guardar els colors en un patro.
     *
     * @param pos és un enter que representa la posició on es vol guardar el color.
     * @param color és un enter que representa el color que es vol guardar.
     */
    public void setColor(int pos, int color) {
        this.patro[pos] = color;
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
     * Aquest mètode s'encarrega de retornar si la partida s'està jugant en colors repetits o no.
     *
     * @return un booleà que representa si la partida té els colors repetits activats.
     */
    public boolean getColorsRepetits() {
        return colorsRepetits;
    }

    /**
     * Aquest mètode s'encarrega d'agafar el color d'una posició del patro guardat.
     *
     * @param pos és un enter que representa la posició del color que volem agafar.
     * @return un enter que és el color que volem agafar.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public int getColor(int pos) throws PosicioPatroNoDisponible {
        if (pos >= patro.length || pos <= -1) throw new PosicioPatroNoDisponible();
        return patro[pos];
    }
}
