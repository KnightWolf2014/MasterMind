package domini;

import domini.controladors.CtrlDomini;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DriverModoCrono {
    //com que no implementat la presentació, s'acaba la partida una vegada guanyat 3 partides seguides
    static int numBoles = 4;
    static boolean modeAjuda = true;
    static boolean colorsRepetits = false;
    private static final CtrlDomini CD = new CtrlDomini();

    private static void benvinguda(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------  WELCOME TO MASTERMIND  ----------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Start game");
        System.out.println("-- 2) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void menuPlay(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------  START GAME  ----------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Configure game");
        System.out.println("-- 2) Start");
        System.out.println("-- 3) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void menuConfiguracio(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-------------------------  CONFIGURE GAME  --------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- NumBoles: " + numBoles);
        String colorsrepe;
        if (colorsRepetits) colorsrepe = "YES";
        else colorsrepe = "NO";
        System.out.println("-- Duplicate colors: " + colorsrepe);
        String ayuda;
        if (modeAjuda) ayuda = "YES";
        else ayuda = "NO";
        System.out.println("-- Help mode: " + ayuda);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Change number of balls");
        System.out.println("-- 2) Check duplicate colors");
        System.out.println("-- 3) Check help mode");
        System.out.println("-- 4) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void menuNumBoles(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("--------------------  CHANGE NUMBER OF BALLS  -----------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) 4 balls");
        System.out.println("-- 2) 6 balls");
        System.out.println("-- 3) 8 balls");
        System.out.println("-- 4) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void escollirNumBoles(){
        menuNumBoles();
        Scanner NumBoles = new Scanner(System.in);
        int num = NumBoles.nextInt();
        if (num != 4){
            if (num == 1) numBoles = 4;
            else if (num == 2) numBoles = 6;
            else if (num == 3) numBoles = 8;
            else error();
        }
    }

    private static void menuColorsRepetits(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------ DUPLICATE COLORS ---------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Activate");
        System.out.println("-- 2) Deactivate");
        System.out.println("-- 3) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void escollirColorsRepetits(){
        menuColorsRepetits();
        Scanner ColorsDupe = new Scanner(System.in);
        int cd = ColorsDupe.nextInt();
        if (cd != 3){
            if (cd == 1) colorsRepetits = true;
            else if (cd == 2) colorsRepetits = false;
            else error();
        }
    }

    private static void menuHelpMode(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------- HELP MODE ------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Activate");
        System.out.println("-- 2) Deactivate");
        System.out.println("-- 3) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void escollirHelpMode(){
        menuHelpMode();
        Scanner Help = new Scanner(System.in);
        int h = Help.nextInt();
        if (h != 3){
            if (h == 1) modeAjuda = true;
            else if (h == 2) modeAjuda = false;
            else error();
        }
    }

    private static void configuracio(){
        menuConfiguracio();
        Scanner Configuracio = new Scanner(System.in);
        int conf = Configuracio.nextInt();
        while (conf != 4){
            if (conf == 1) escollirNumBoles();
            else if (conf == 2) escollirColorsRepetits();
            else if (conf == 3) escollirHelpMode();
            else error();

            menuConfiguracio();
            conf = Configuracio.nextInt();
        }
    }

    private static void menuPartida(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------------  GAME  -------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Create Secuence");
        System.out.println("-- 2) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void dibuixaTauler(){

        int[][] intents = CD.getVectorIntents();
        int[][] correccions = CD.getVectorCorreccions();
        if((CD.partidaAcabada() && !CD.partidaGuanyada())){
            int[] solucio = CD.getSolucio();
            System.out.println("THE SOLUTION IS: " + Arrays.toString(solucio));
        }
        if(intents.length != 0) {
            for (int i = 0; i < numBoles * 4; i++) System.out.print("-");
            System.out.println("------");
        }
        for(int i = 0; i < correccions.length; i++){
            System.out.print("| ");
            for(int j = 0; j < numBoles; j++){
                System.out.print(intents[i][j]);
                System.out.print(" ");
            }
            System.out.print("|| ");
            for(int j = 0; j < numBoles; j++){
                System.out.print(correccions[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        if(correccions.length != intents.length){
            System.out.print("| ");
            for(int j = 0; j < numBoles; j++){
                System.out.print(intents[intents.length - 1][j]);
                System.out.print(" ");
            }
            System.out.print("|| ");
            for(int i = 0; i < 2*numBoles; i++) System.out.print(" ");
            System.out.println("|");
        }
        if(intents.length != 0) {
            for (int i = 0; i < numBoles * 4; i++) System.out.print("-");
            System.out.println("------");
        }
        System.out.println();
    }

    private static int[] llegirSequencia(String text){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------- INSERT YOUR " + text + " FOR " + numBoles + " BALLS ----------------");
        System.out.println("---------------------------------------------------------------------");
        Scanner entrada = new Scanner(System.in);
        int[] sequencia = new int[numBoles];
        try {
            for (int i = 0; i < numBoles; i++) {
                int num = entrada.nextInt();
                sequencia[i] = num;
            }
        }
        catch(InputMismatchException e){
            System.out.println("INPUT ERROR: Input type for your sequence is not correct");
            sequencia = llegirSequencia(text);
        }
        return sequencia;
    }

    private static void ferTorn() throws Exception{
        dibuixaTauler();
        int[] patro = llegirSequencia("ATTEMPT");
        CD.ferIntent(patro);

    }

    private static void partidaGuanyadaIAcabada(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------------  GOOD GAME -----------------------------");
        System.out.println("---------------------------------------------------------------------");

        dibuixaTauler();
    }

    private static void seguentPartida() throws Exception{
        CD.establirSolucio();

        menuPartida();
        Scanner entradaDades = new Scanner(System.in);
        int ent = entradaDades.nextInt();
        while (ent != 2 && CD.getPartidesGuanyades() < 3 && !CD.partidaAcabada()) {
            if (ent == 1) ferTorn();
            else error();
            if (CD.partidaGuanyada()) partidaGuanyada();
            if(!CD.partidaAcabada()) {
                menuPartida();
                ent = entradaDades.nextInt();

            }
        }
        if(CD.partidaAcabada()){

            dibuixaTauler();
            System.out.println("The solution was: " + Arrays.toString(CD.getSolucio()));
        }
    }

    private static void partidaGuanyada() throws Exception{
        if (CD.getPartidesGuanyades() == 2) partidaGuanyadaIAcabada();
        else {
            CD.nextPartida();
            int partidesRestants = 3-CD.getPartidesGuanyades();


            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("----------------------------  GOOD GAME -----------------------------");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Queda "+partidesRestants+" partides per guanyar");

            //cridar a ctrl domini perquè cridi a la funció de next partida, i que comenci tot again

            //CtrlDomini crida a nextPartida de crono

            seguentPartida();
        }
    }

    private static void partidaPerduda(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------------  GAME OVER -----------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void comencarPartida() throws Exception{
        CD.inicialitzarJoc(1, numBoles, colorsRepetits, modeAjuda, 1, 5);
        CD.establirSolucio();

        menuPartida();
        Scanner entradaDades = new Scanner(System.in);
        int ent = entradaDades.nextInt();
        while (ent != 2 && CD.getPartidesGuanyades() < 3 && !CD.partidaAcabada()) {
            if (ent == 1) ferTorn();
            else error();
            if(!CD.partidaAcabada()) {
                //mode crono acabar la partida es acabar mode crono, guanyar partida es guanyar la partida actual
                if(CD.partidaGuanyada()){
                    partidaGuanyada();
                    ent = 2;
                }
                else{
                    menuPartida();
                    ent = entradaDades.nextInt();}

            }
        }
        if(CD.partidaAcabada() || CD.partidaGuanyada() && CD.getPartidesGuanyades() == 3){

            if(CD.partidaGuanyada() && CD.getPartidesGuanyades() == 3) partidaGuanyadaIAcabada();
            else partidaPerduda();

            dibuixaTauler();
            System.out.println("The solution was: " + Arrays.toString(CD.getSolucio()));
        }
    }

    private static void play() throws Exception{
        menuPlay();
        Scanner Opcions = new Scanner(System.in);
        int op = Opcions.nextInt();
        while (op != 3){
            if (op == 1) configuracio();
            else if (op == 2){
                comencarPartida();
            }
            else error();

            menuPlay();
            op = Opcions.nextInt();
        }
    }

    private static void error(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------------  ERROR  ------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void main(String[] args) throws Exception{
        benvinguda();
        Scanner entradaDades = new Scanner(System.in);
        int ent = entradaDades.nextInt();
        while (ent != 2){
            if (ent == 1) play();
            else error();
            benvinguda();
            ent = entradaDades.nextInt();
        }
    }
}
