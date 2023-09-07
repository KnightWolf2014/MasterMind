package domini.model;

import domini.exceptions.*;

import java.util.ArrayList;


public abstract class Partida{

    /**
     * VARIABLES
     */
    private int numBoles;
    private boolean modeAjuda;
    private boolean colorsRepetits;

    private final int nIntents;

    private boolean guanyat;    //ja que en mode crono per passar d'una partida a una altre es necessita un paràmetre per marcar-ho

    private ArrayList<Patro> intents;
    private ArrayList<CorreccioLinea> correccions;

    private Patro solucio;

    private final int[] temps;     //2, els minuts i els segons

    /**
    * Creadora de la classe Partida
    */
    public Partida(){
        this.numBoles = 0;
        this.modeAjuda = false;
        this.colorsRepetits = false;
        this.nIntents = 9;  //nombre d'intents fix, 9 perquè contem torn des de 0
        this.guanyat = false;

        this.intents = new ArrayList<>();
        this.correccions = new ArrayList<>();
        this.solucio = null;
        this.temps = new int[2];
    }

    /**
     * Creadora de la classe Partida
     *
     * @param nBol és un enter que representa el nombre de boles dels patrons de la Partida
     * @param modeA és un booleà que representa si s'ha activat o no el modeAjuda, que significa una Partida més fàcil
     * @param colorsR és un booleà que representa si es vol la possibilitat que la solució de la Partida pugui contindré colors repetits
     */
    public Partida(int nBol,boolean modeA, boolean colorsR){
        this.numBoles = nBol;
        this.modeAjuda = modeA;
        this.colorsRepetits = colorsR;
        this.nIntents = 9;  //nombre d'intents fix, 9 perquè contem torn des de 0
        this.guanyat = false;

        this.intents = new ArrayList<>();
        this.correccions = new ArrayList<>();
        this.solucio = null;
        this.temps = new int[2];
    }

    /**
     * Aquest mètode retorna el nombre de boles de la Partida
     *
     * @return un integer que representa el nombre de boles de la Partida
     */
    public int getNumBoles(){
        return this.numBoles;
    }

    /**
     * Aquest mètode retorna l'atribut modeAjuda
     *
     * @return un booleà que indica si el modeAjuda està activat o no
     */
    public boolean getModeAjuda(){
        return this.modeAjuda;
    }

    /**
     * Aquest mètode retorna l'atribut colorsRepetits
     *
     * @return un booleà que indica si els colorsRepetits estan activats o no
     */
    public boolean getColorsRepetits(){
        return this.colorsRepetits;
    }

    /**
     * Aquest mètode retorna l'atribut nIntents
     *
     * @return un integer que representa el nombre d'intents que es pot realitzar a la Partida
     */
    public int getNIntents(){
        return this.nIntents;
    }

    /**
     * Aquest mètode retorna el temps de la Partida
     *
     * @return un vector d'enters que representen els minuts i segons del temps de la Partida
     */
    public int[] getTemps(){
        return this.temps;
    }

    /**
     * Aquest mètode retorna els intents fets per l'usuari en la Partida
     *
     * @return un conjunt de patrons que representen els intents de la Partida
     */
    public ArrayList<Patro> getVectorIntents(){
        return this.intents;
    }

    /**
     * Aquest mètode retorna les correccions als intents fets pel jugador de la Partida
     *
     * @return un conjunt de correccions que representen les correccions als intents
     */
    public ArrayList<CorreccioLinea> getVectorCorreccions(){
        return this.correccions;
    }

    /**
     * Aquest mètode afegeix la solució passada en la Partida actual
     *
     * @param solucio és un Patró que representa la solució que ha d'endevinar el jugador
     */
    //pre:
    //post: fica valor a l'atribut solució
    public void passarSolucio(Patro solucio){
        this.solucio = solucio;
    }

    /**
     * Aquest mètode retorna la solució actual de la Partida
     *
     * @return un Patró que representa la solució de la Partida actual
     */
    public Patro getSolucio(){ return solucio;}

    /**
     * Aquest mètode retorna si la Partida ha sigut guanyada o no
     *
     * @return un booleà que representa si la Partida ha sigut guanyada o no
     */
    public boolean getGuanyat(){

        return this.guanyat;
    }

    /**
     * Aquest mètode retorna la mida de l'atribut intents
     *
     * @return un integer que representa la mida de l'atribut intents
     */
    public int getMidaIntents(){
        return intents.size();
    }

    /**
     * Aquest mètode retorna la mida de l'atribut correccions
     *
     * @return un integer que representa la mida de l'atribut correccions
     */
    public int getMidaCorreccions(){return correccions.size();}

    /**
     * Aquest mètode afegeix l'intent actual del jugador a la llista d'intents 'intents' de la Partida
     *
     * @param intent representa l'intent actual afegit pel jugador
     */
    public void passarIntent(Patro intent){
        //afegir intent al vector intents
        this.intents.add(intent);
    }

    /**
     * Aquest mètode afegeix la correcció actual a la llista de correccions 'correccions' de la Partida
     * <p>
     * @param correccio representa la correcció actual
     */
    public void passarCorreccio(CorreccioLinea correccio){
        this.correccions.add(correccio);
    }

    /**
     * Aquest mètode fica valor a l'atribut temps de la Partida amb el paràmetre passat
     *
     * @param temps és un vector d'enters que representa el temps que ficarem a la Partida
     */
    public void setTemps(int[] temps){
        this.temps[0] = temps[0];
        this.temps[1] = temps[1];
    }

    /**
     * Aquest mètode fica valor a l'atribut guanyat de la Partida amb el paràmetre passat
     *
     * @param b és un booleà auxiliar que ajuda a canviar el valor de guanyat
     */
    public void setGuanyat(boolean b){
        this.guanyat = b;
    }

    /**
     * Aquest mètode fica valor a l'atribut intents de la Partida amb el paràmetre passat
     *
     * @param a és una ArrayList de Patró auxiliar que ajuda a canviar el valor d'intents
     */
    public void setIntents(ArrayList<Patro> a){
        this.intents = a;
    }

    /**
     * Aquest mètode fica valor a l'atribut correccions de la Partida amb el paràmetre passat
     *
     * @param c és una ArrayList de CorreccioLinea auxiliar que ajuda a canviar el valor de correccions
     */
    public void setCorreccions(ArrayList<CorreccioLinea> c){
        this.correccions = c;
    }

    /**
     * Aquest mètode retorna si la Partida ha sigut acabada o no
     *
     * @return un booleà que representa si la Partida ha acabat o no
     */
    public abstract boolean partidaAcabada();

    /**
     * Aquest mètode s'obté l'intent del torn actual
     *
     * @return un Patró que representa l'intent actual de la Partida
     * @throws NoIntentDisponible en cas de que encara no existeixi cap intent
     */
    public Patro getIntentActual() throws Exception{
        if (intents.isEmpty()) throw new NoIntentDisponible();
        return intents.get(intents.size() - 1);
    }

    /**
     * Aquest mètode s'obté la correcció del torn actual
     *
     * @return una CorreccioLinea que representa la correcció actual de la Partida
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció
     */
    public CorreccioLinea getCorreccioActual() throws Exception{
        if (correccions.isEmpty()) throw new NoCorreccioDisponible();
        return correccions.get(correccions.size() - 1);
    }

    /**
     * Aquest mètode calcula la correcció de l'intent passat com a paràmetre
     *
     * @param intent representa l'intent actual fet pel jugador
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi
     */
    public void checkIntent(Patro intent) throws Exception{
        //en mode codebreaker, quan vol verificar el patró que el jugador ha fet(tick)
        /*
        significat nombres de aux[]:
        - 2: color i posició estan en la solució
        - 1: només color correcte
        - 0: el color no està en la solució*/
        if (this.solucio == null) throw new NoSolucioDisponible();
        Patro solucio = this.solucio;

        boolean algunaErrada = false;  //aquest bool servirà per saber si han endivinat el patró solució
        int[] aux = new int[numBoles];  //fem un vector pero abans de retornar-lo ho passaron a CorreccioLinea

        int[] control = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        for(int i = 0; i < numBoles; ++i){
            control[solucio.getColor(i)]++;
            if (intent.getColor(i) == solucio.getColor(i)){ //coincideixen en color i posició
                aux[i] = 2;
                control[intent.getColor(i)]--;
            }
        }

        for (int i = 0; i < numBoles; ++i){
            if(!(intent.getColor(i) == solucio.getColor(i))) { //no coincideix en posició
                boolean teColor= false;
                for (int j = 0; j < numBoles && !teColor; ++j){
                    //es mira si el color de boles de l'intent están dins la solució
                    if (intent.getColor(i) == solucio.getColor(j) && control[intent.getColor(i)] != 0) {
                        teColor = true;
                        aux[i] = 1; //color es troba en la solució, però no en aquesta posició concreta
                        algunaErrada = true;    //com que algun color no està en la seva posició hi ha errada
                        control[intent.getColor(i)]--;
                    }
                }
                if (!teColor){  //si la bola actual no té ni color ni posició
                    aux[i] = 0;    //no coincideixen en color
                    algunaErrada = true;
                }
            }
        }

        if (!algunaErrada) {
            guanyat = true; //solucio endevinada
        }

        CorreccioLinea auxC = new CorreccioLinea(this.numBoles, this.modeAjuda);
        auxC = auxC.actualitzaCorreccioLinea(aux);
        passarCorreccio(auxC);
    }

    /**
     * Aquest mètode diu si la correcció passada com a paràmetre és correcte, per evitar que l'usuari en codemaker faci trampes.
     *
     * @param correccio representa la correcció feta per l'usuari en mode codemaker
     * @return un boolea que representa si la correcció es correcte o no
     * @throws CorreccioIncorrecte en cas que la correcció no sigui correcte
     * @throws NoIntentDisponible en cas que no existeixi cap intent
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws PosicioPatroNoDisponible en cas de que la posició donada no existeixi
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció
     */
    public boolean checkCorreccio(CorreccioLinea correccio) throws Exception{
        Patro intent = getIntentActual();
        checkIntent(intent);
        CorreccioLinea aux = getCorreccioActual();
        //agafem la correcció "oficial"

        int num1, num2, num0;
        int num1Aux, num2Aux, num0Aux;
        num1 = num2 = num0 = num1Aux = num2Aux = num0Aux = 0;
        for (int i = 0; i < numBoles; ++i){
            //com és la correcció de la màquina i aquesta no té mode ajuda, dona igual si estan en l'ordre equivocat
            if (correccio.getColor(i) == 0) ++num0;
            else if (correccio.getColor(i) == 1) ++num1;
            else if (correccio.getColor(i) == 2) ++num2;

            if (aux.getColor(i) == 0) ++num0Aux;
            else if (aux.getColor(i) == 1) ++num1Aux;
            else if (aux.getColor(i) == 2) ++num2Aux;
        }

        if (num0 != num0Aux | num1 != num1Aux | num2 != num2Aux) {
            throw new CorreccioIncorrecte();
        }
        return true;
    }

    /**
     * Aquest mètode converteix un integer a un char
     *
     * @param i és integer que volem convertir en char
     * @return el int que hem passat com a paràmetre pero en forma de char
     */
    public char intToChar(int i){
        //només poden ser nombres entre el nombre 0 i 7
        return (char)(i+'0');
    }

    /**
     * Aquest mètode converteix un intent en un String
     *
     * @param intent representa l'intent que volem convertir a String
     * @return un String que conté la mateixa informació que l'intent passat com a paràmetre
     * @throws PosicioPatroNoDisponible en cas de que s'accedeixi a una posició del intent no disponible
     */
    public String intentToString(Patro intent) throws Exception{
        //nInt -> nombre d'intents actuals
        StringBuilder aux = new StringBuilder(String.valueOf(intent.getColor(0)));
        for(int i = 1; i < 8; ++i){
            //comencem desde 1, ja que el cas base (nombre en posicio 0, ja la hem fet previament)
            aux.append('-');
            if (i+1 <= this.numBoles) aux.append(intToChar(intent.getColor(i)));
            else aux.append('9');
        }

        return aux.toString();
    }

    /**
     * Aquest mètode converteix una correcció a un String
     *
     * @param correccio representa la correcció que volem convertir a String
     * @return un String amb la mateixa informació que la correcció que passem com a paràmetre
     * @throws PosicioCorreccioLineaNoDisponible en cas de que s'accedeixi a una posició no disponible
     */
    public String correccioToString(CorreccioLinea correccio) throws Exception{
        //nInt -> nombre d'intents actuals
        StringBuilder aux = new StringBuilder(String.valueOf(correccio.getColor(0)));
        for(int i = 1; i < 8; ++i){
            //comencem des d'1, ja que el cas base (nombre en posició 0, ja la hem fet prèviament)
            aux.append('-');
            if (i+1 <= this.numBoles) aux.append(intToChar(correccio.getColor(i)));
            else aux.append('9');
        }

        return aux.toString();
    }

    /**
     * Aquest mètode guarda la Partida en un array de Strings
     *
     * @return un conjunt de Strings que representen la partida guardada
     * @throws PosicioPatroNoDisponible en cas de que s'accedeixi a una posició del intent no disponible
     * @throws PosicioCorreccioLineaNoDisponible en cas de que s'accedeixi a una posició no disponible
     */
    public ArrayList<String> guardarPartida() throws Exception {
        ArrayList<String> s = new ArrayList<>();
        //tindrà 21 de size

        s.add(0, "1");

        int modeAjudaI;
        if (!this.modeAjuda) modeAjudaI = 0;
        else modeAjudaI = 1;

        int colorsRepetitsI;
        if (!this.colorsRepetits) colorsRepetitsI = 0;
        else colorsRepetitsI = 1;

        if (this.intents.size() == 10) throw new PartidaNoGuardable();

        //passem l'atribut temps a string segons si són d'un dígit o 2 perquè sempre la mida de s[0] sigui 70
        String temps0;
        if (this.temps[0] < 10){
            temps0 = "0";
            temps0 = temps0 + intToChar(this.temps[0]);
        }
        else temps0 = String.valueOf(this.temps[0]);

        String temps1;
        if (this.temps[1] < 10){
            temps1 = "0";
            temps1 = temps1 + intToChar(this.temps[1]);
        }
        else temps1 = String.valueOf(this.temps[1]);

        s.add(1,"numBoles: "+this.numBoles+ ", modeAjuda: "+modeAjudaI+ ", colorsRepetits: "+colorsRepetitsI+ ", intents: "+this.intents.size()+ ", temps: "+temps0+":"+temps1);

        //passem la solució
        s.add(2, intentToString(this.solucio));

        //del 3 a 11 -> intents
        for (int i = 0; i < 9; ++i){
            if(i+1 > intents.size()){
                //no queden intents
                s.add(i+3,"9-9-9-9-9-9-9-9");
            }
            else s.add(i+3, intentToString(intents.get(i)));
        }

        //del 12 al 20 -> correccions
        for (int k = 0; k < 9; ++k){
            if (k+1 > correccions.size()){
                //no queden correccions
                s.add(k+12, "9-9-9-9-9-9-9-9");
            }
            else s.add(k+12, correccioToString(correccions.get(k)));

        }
        return s;
    }

    /**
     * Aquest mètode carrega la partida passada en Strings i la converteix en una classe Partida
     *
     * @param s representa la partida en Strings
     */
    public void carregarPartida(ArrayList<String> s){
        this.numBoles = Character.getNumericValue(s.get(1).charAt(10));
        this.modeAjuda = Character.getNumericValue(s.get(1).charAt(24)) == 1;
        this.colorsRepetits = Character.getNumericValue(s.get(1).charAt(43)) == 1;

        int nombreIntents = Character.getNumericValue(s.get(1).charAt(55));

        if (Character.getNumericValue(s.get(1).charAt(65)) != 0){
            int tempsDecimal = Character.getNumericValue(s.get(1).charAt(65));
            int tempsUnitat = Character.getNumericValue(s.get(1).charAt(66));
            this.temps[0] = tempsDecimal*10 + tempsUnitat;
        }
        else this.temps[0] = Character.getNumericValue(s.get(1).charAt(66));

        if (Character.getNumericValue(s.get(1).charAt(68)) != 0){
            int tempsDecimal = Character.getNumericValue(s.get(1).charAt(68));
            int tempsUnitat = Character.getNumericValue(s.get(1).charAt(69));
            this.temps[1] = tempsDecimal*10 + tempsUnitat;
        }
        else this.temps[1] = Character.getNumericValue(s.get(1).charAt(69));

        //solució
        this.solucio = new Patro(llegirVector(s.get(2)));

        //intents i correcions omplint
        this.intents = new ArrayList<>();

        for (int i = 3; i <= 11; ++i){
            Patro auxP = new Patro(llegirVector(s.get(i)));
            if (i-3 < nombreIntents) this.intents.add(auxP);
            //si no és un patró de "relleno" l'afegim al vector d'intents de la partida
        }
        
        this.correccions = new ArrayList<>();
        for (int i = 12; i <= 20; ++i){
            CorreccioLinea auxC = new CorreccioLinea(llegirVector(s.get(i)));
            if (i-12 < nombreIntents) this.correccions.add(auxC);
        }
    }

    /**
     * Aquest mètode llegeix el vector en forma de String i el converteix en un vector integers
     *
     * @param s representa el vector que volem passar a vector integer
     * @return un vector integer que representa el string passat a vector
     */
    private int[] llegirVector(String s){

        int[] aux = new int[this.numBoles];
        for (int i = 0; i < numBoles; ++i){
            aux[i] = Character.getNumericValue(s.charAt(2*i));
        }

        return aux;
    }

    /**
     * Aquest mètode retorna la informació valuable de la Partida
     *
     * @return un vector integer la informació important de la Partida
     */
    public abstract int[] obtenirPuntuacio();

    public abstract void nextPartida();

    public abstract int getPartidesGuanyades();
}