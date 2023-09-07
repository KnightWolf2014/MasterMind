package domini;

import domini.controladors.CtrlDomini;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DriverJocComplet{
    static int numBoles = 4;
    static boolean modeAjuda = true;
    static boolean colorsRepetits = false;
    static int Rol = 1;
    static int modeJoc = 0;
    static int cronometre = 5;
    static boolean partidaGuardada = false;
    private static final CtrlDomini CD = new CtrlDomini();

    private static void benvinguda(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------  WELCOME TO MASTERMIND  ----------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Start game");
        System.out.println("-- 2) Load saved game");
        System.out.println("-- 3) Show Rankings");
        System.out.println("-- 4) Records");
        System.out.println("-- 5) How to play");
        System.out.println("-- 6) Exit");
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
        String rol;
        if (Rol == 0) rol = "Codemaker";
        else rol = "Codebreaker";
        System.out.println("-- Rol: " + rol);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Choose role");
        System.out.println("-- 2) Configure game");
        System.out.println("-- 3) Start");
        System.out.println("-- 4) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
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
        System.out.println("-- 2) Save");
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
        System.out.println("-- 3) Check help mode (only in codebreaker mode)");
        System.out.println("-- 4) Select game mode  ------ option not implemented yet");
        System.out.println("-- 5) Exit");
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
    private static void menuRol(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------- CHOOSE ROL -----------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) Codemaker");
        System.out.println("-- 2) Codebreaker");
        System.out.println("-- 3) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
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
    private static void partidaPerduda(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------------  GAME OVER -----------------------------");
        System.out.println("---------------------------------------------------------------------");
    }
    private static void partidaGuanyada(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------------------------  GOOD GAME -----------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void menuRankings(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("--------------------------  MENU RANKINGS  --------------------------");
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
        System.out.println("-- Minuts: " + cronometre);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one of the available options:");
        System.out.println("-- 1) 4 boles");
        System.out.println("-- 2) 6 boles");
        System.out.println("-- 3) 8 boles");
        System.out.println("-- 4) ModeAjuda activat");
        System.out.println("-- 5) ModeAjuda desactivat");
        System.out.println("-- 6) ColorsRepetits activat");
        System.out.println("-- 7) ColorsRepetits desactivat");
        System.out.println("-- 8) 5 minuts");
        System.out.println("-- 9) 10 minuts");
        System.out.println("-- 10) Veure Ranking Classic");
        System.out.println("-- 11) Veure Ranking Crono");
        System.out.println("-- 12) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }
    private static void reglesJoc(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------  HOW TO PLAY  ---------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Choose one language:");
        System.out.println("-- 1) English");
        System.out.println("-- 2) Castellano");
        System.out.println("-- 3) Català");
        System.out.println("-- 4) Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }
    private static void reglesAngles(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------- CODEBREAKER RULES -------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- The computer picks a sequence of colors. The number of colors is");
        System.out.println("   the code length. The default code length is 4 but it can be");
        System.out.println("   changed when starting a new game.");
        System.out.println();
        System.out.println("-- The objective of the game is to guess the exact positions of the");
        System.out.println("   colors in the computer's sequence.");
        System.out.println();
        System.out.println("-- By default, a color can be used only once in a code sequence.");
        System.out.println("   If you start a new game with the 'Allow duplicates' checked, then");
        System.out.println("   any color can be used any number of times in the code sequence.");
        System.out.println();
        System.out.println("-- After filling a line with your guesses and clicking on the 'Check'");
        System.out.println("   button, the computer responses with the result of your guess.");
        System.out.println();
        System.out.println("-- For each color in your guess that is in the correct color and");
        System.out.println("   correct position in the code sequence, the computer display a small");
        System.out.println("   red color on the right side of the current guess.");
        System.out.println();
        System.out.println("-- For each color in your guess that is in the correct color but is");
        System.out.println("   NOT in the correct position in the code sequence, the computer");
        System.out.println("   display a small white color on the right side of the current guess.");
        System.out.println();
        System.out.println("-- You win the game when you manage to guess all the colors in the");
        System.out.println("   code sequence and when they all in the right position.");
        System.out.println();
        System.out.println("-- You lose the game if you use all attempts without guessing the");
        System.out.println("   computer code sequence");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");

    }
    private static void reglesCastella(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------ REGLAS CODEBREAKER -------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- El ordenador elige una secuencia de colores. El número de colores");
        System.out.println("   es la longitud del código. Por defecto la longitud es 4, pero");
        System.out.println("   puede ser cambiada cuando se empiece un juego nuevo");
        System.out.println();
        System.out.println("-- El objetivo del juego es adivinar las posiciones exactas de los");
        System.out.println("   colores de la secuencia del ordenador");
        System.out.println();
        System.out.println("-- Por defecto, un color puede ser usado solo una vez por secuencia.");
        System.out.println("   Al empezar un juego se puede seleccionar la opción de colores");
        System.out.println("   repetidos, en este caso, cualquier color puede se usado las veces");
        System.out.println("   que se quiera");
        System.out.println();
        System.out.println("-- Después de llenar una linea con los colores que veas convenientes");
        System.out.println("   y al pulsar en el botón de corrección, el ordenador responderá con");
        System.out.println("   el resultado de la secuencia.");
        System.out.println();
        System.out.println("-- Para cada color de tú secuencia que sea correcto y este en la");
        System.out.println("   posición correcta, el ordenador mostrarà un color rojo al lado");
        System.out.println("   de la secuencia.");
        System.out.println();
        System.out.println("-- Para cada color de tú secuencia que sea correcto pero que no este");
        System.out.println("   en la posición correcta, el ordenador mostrarà un color blanco al");
        System.out.println("   lado de la secuencia.");
        System.out.println();
        System.out.println("-- Ganarás el juego si consigues adivinar la posición de todos los");
        System.out.println("   colores de la secuencia.");
        System.out.println();
        System.out.println("-- Perderás el juego si no consigues adivinarlo");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void reglesCodemakerCastella(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-------------------------  REGLAS CODEMAKER -------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Eliges una secuencia de colores. El número de colores");
        System.out.println("   es la longitud del código. Por defecto la longitud es 4, pero");
        System.out.println("   puede ser cambiada cuando se empiece un juego nuevo");
        System.out.println();
        System.out.println("-- El objetivo del juego es intentar conseguir que el ordenador no");
        System.out.println("   adivine los colores de la secuencia puesta por ti");
        System.out.println();
        System.out.println("-- Por defecto, un color puede ser usado solo una vez por secuencia.");
        System.out.println("   Al empezar un juego se puede seleccionar la opción de colores");
        System.out.println("   repetidos, en este caso, cualquier color puede se usado las veces");
        System.out.println("   que se quiera");
        System.out.println();
        System.out.println("-- El ordenador llenará una linea con los colores que vea convenientes");
        System.out.println("   y luego tendrás que corregirle la secuencia");
        System.out.println();
        System.out.println("-- Para cada color de su secuencia que sea correcto y este en la");
        System.out.println("   posición correcta, tendrás que marcar un color blanco al lado de");
        System.out.println("   la secuencia.");
        System.out.println();
        System.out.println("-- Para cada color de su secuencia que sea correcto pero que no este");
        System.out.println("   en la posición correcta, tendrás que marcar un color negro al");
        System.out.println("   lado de la secuencia.");
        System.out.println();
        System.out.println("-- Ganarás el juego si consigues que el ordenador no adivine la");
        System.out.println("   posición de todos los colores de la secuencia.");
        System.out.println();
        System.out.println("-- Perderás el juego si el ordenador los adivina");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void reglesCatala(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------ REGLES CODEBREAKER -------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- L'ordinador tria una seqüència de colors. El nombre de colors és");
        System.out.println("   la longitud del codi. Per defecte la longitud és 4, però pot ser");
        System.out.println("   canviada quan es comenci un joc nou.");
        System.out.println();
        System.out.println("-- L'objectiu del joc és adivinar las posicions exactes dels colors");
        System.out.println("   de la seqüència de l'ordinador.");
        System.out.println();
        System.out.println("-- Per defecte, un color pot ser usat només una vegada per seqüència.");
        System.out.println("   Al començar un joc es pot seleccionar l'opció de colors repetits,");
        System.out.println("   en aquest cas, qualsevol color pot ser utilitzat las vegades que");
        System.out.println("   es vulgui.");
        System.out.println();
        System.out.println("-- Després d'omplir una línia amb els colors que vegis convenients i");
        System.out.println("   clicar en el botó de correcció, l'ordinador respondrà amb el");
        System.out.println("   resultat de la seqüència.");
        System.out.println();
        System.out.println("-- Per cada color de la teva seqüència que sigui correcte i estigui");
        System.out.println("   en la posició correcte, l'ordinador mostrarà un color negre al");
        System.out.println("   costat de la seqüència.");
        System.out.println();
        System.out.println("-- Per cada color de la teva seqüència que sigui correcte i estigui");
        System.out.println("   en una posició incorrecte, l'ordinador mostrarà un color blanc al");
        System.out.println("   costat de la seqüència.");
        System.out.println();
        System.out.println("-- Guanyaràs el joc si aconsegueixes adivinar la posició de tots els");
        System.out.println("   colors de la seqüència.");
        System.out.println();
        System.out.println("-- Perdràs el joc si no aconsegueixes adivinarlos.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void reglesCodemakerCatala(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-------------------------  REGLAS CODEMAKER -------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Tries una seqüència de colors. El nombre de colors és la longitud");
        System.out.println("   del codi. Por defecte la longitud és 4, pero pot ser canviada quan");
        System.out.println("   es comenci un joc nou.");
        System.out.println();
        System.out.println("-- L'objectiu del joc és intentar aconseguir que l'ordinador no");
        System.out.println("   adivini els colors de la seqüència posada per tu.");
        System.out.println();
        System.out.println("-- Per defecte, un color pot ser usat només una vegada per seqüència.");
        System.out.println("   Al començar un joc es pot seleccionar l'opció de colors repetits,");
        System.out.println("   en aquest cas, qualsevol color pot ser utilitzat las vegades que");
        System.out.println("   es vulgui.");
        System.out.println();
        System.out.println("-- L'ordinador omplirà una línia amb els colors que vegi convenients");
        System.out.println("   i després tindràs que corregir-li la seqüència.");
        System.out.println();
        System.out.println("-- Per cada color de la seva seqüència que sigui correcte i que");
        System.out.println("   estigui en la posició correcte, tindràs que marcar un color negre");
        System.out.println("   al costat de la seqüència.");
        System.out.println();
        System.out.println("-- Per cada color de la seva seqüència que sigui correcte i que");
        System.out.println("   estigui en una posició incorrecte, tindràs que marcar un color");
        System.out.println("   negre al costat de la seqüència.");
        System.out.println();
        System.out.println("-- Guanyaràs el joc si aconsegueixes que l'ordenador no adivini la");
        System.out.println("   posició de tots els colors de la seqüència.");
        System.out.println();
        System.out.println("-- Perdràs el joc si l'ordinador els adivina.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void reglesCodemakerAngles(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-------------------------- CODEMAKER RULES --------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- You choose a sequence of colors. The number of colors is");
        System.out.println("   the code length. The default code length is 4 but it can be");
        System.out.println("   changed when starting a new game.");
        System.out.println();
        System.out.println("-- The objective of the game is to try to make the computer not");
        System.out.println("   guess the exact positions of the colors of yours sequence.");
        System.out.println();
        System.out.println("-- By default, a color can be used only once in a code sequence.");
        System.out.println("   If you start a new game with the 'Allow duplicates' checked, then");
        System.out.println("   any color can be used any number of times in the code sequence.");
        System.out.println();
        System.out.println("-- The computer will fill in a line with the colors it deems");
        System.out.println("   appropriate, and then you will have to correct the sequence.");
        System.out.println();
        System.out.println("-- For each color in its guess that is in the correct color and");
        System.out.println("   correct position in the code sequence, you will display a small");
        System.out.println("   white color on the right side of the current guess.");
        System.out.println();
        System.out.println("-- For each color in its guess that is in the correct color but is");
        System.out.println("   NOT in the correct position in the code sequence, you will display");
        System.out.println("   a small black color on the right side of the current guess.");
        System.out.println();
        System.out.println("-- You will win the game if you manage to prevent the computer from");
        System.out.println("   guessing the positions of the colors in your sequence.");
        System.out.println();
        System.out.println("-- You will loose the game if the computer guess them");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");

    }
    private static void noImplementat(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------------  ERROR  ------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-- Not implemented yet");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
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
    private static void regles() {
        reglesJoc();
        Scanner SeleccioIdioma = new Scanner(System.in);
        int idi = SeleccioIdioma.nextInt();
        if (idi != 4) {
            if (idi == 1) {
                reglesAngles();
                reglesCodemakerAngles();
            }
            else if (idi == 2){
                reglesCastella();
                reglesCodemakerCastella();
            }
            else if (idi == 3){
                reglesCatala();
                reglesCodemakerCatala();
            }
            else error();
        }
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
    private static void configuracio(){
        menuConfiguracio();
        Scanner Configuracio = new Scanner(System.in);
        int conf = Configuracio.nextInt();
        while (conf != 5){
            if (conf == 1) escollirNumBoles();
            else if (conf == 2) escollirColorsRepetits();
            else if (conf == 3 && Rol == 1) escollirHelpMode();
            else if (conf == 4) noImplementat();
            else error();

            menuConfiguracio();
            conf = Configuracio.nextInt();
        }
    }

    //EN PROCESS (PREGUNTAR DIMECRES)
    private static void ferTorn() throws Exception{
        if (Rol == 0) {
            CD.ferTorn();
            dibuixaTauler();
            int[] correccio = llegirSequencia("CORRECTION");
            CD.ferCorreccio(correccio);
        } else {
            dibuixaTauler();
            int[] patro = llegirSequencia("ATTEMPT");
            CD.ferIntent(patro);
        }
    }
    private static void comencarPartida() throws Exception{
        if(Rol == 0) modeAjuda = false;
        if (!partidaGuardada){
            CD.inicialitzarJoc(Rol, numBoles, colorsRepetits, modeAjuda, modeJoc, cronometre);
            if(Rol == 0){
                int[] solucio = llegirSequencia("SOLUTION");
                while(CD.marcarSolucio(solucio)){
                    solucio = llegirSequencia("SOLUTION");
                }
            }
            else CD.establirSolucio();
        }
        else CD.carregarPartida();

        menuPartida();
        Scanner entradaDades = new Scanner(System.in);
        int ent = entradaDades.nextInt();
        boolean guardada = false;
        while (ent != 3 && !guardada && !CD.partidaAcabada()) {
            if (ent == 1) ferTorn();
            else if(ent == 2){
                int[] temps = new int[]{10,10};
                CD.guardarPartida(temps);
                guardada = true;
            }
            else error();
            if(!CD.partidaAcabada()) {
                menuPartida();
                ent = entradaDades.nextInt();
            }
        }
        if(CD.partidaAcabada()){
            boolean guanyada = CD.partidaGuanyada();
            if(CD.partidaGuanyada()) partidaGuanyada();
            else partidaPerduda();
            dibuixaTauler();
            int[] punts = CD.obtenirPuntuacio();
            if(Rol == 1) System.out.println("The solution was: " + Arrays.toString(CD.getSolucio()));
            String player = "";
            if (modeJoc == 0 && guanyada) {
                CD.carregaRankingsClassic(numBoles, modeAjuda, colorsRepetits);
                int[] temps = new int[]{12, 34};
                if (CD.novaPosRankingClassic(punts[0], temps)){
                    System.out.println("NOVA POSICIO AL RANKING!");
                    System.out.println("Introdueix el teu nom: ");
                    Scanner scanner = new Scanner(System.in);
                    player = scanner.nextLine();
                }
                CD.carregarRecords();
                if (CD.guardarRankingClassic(player, temps, punts[0])){
                    CD.guardarRecords();
                }
            }
            else if (guanyada){
                CD.carregaRankingsCrono(numBoles, modeAjuda, colorsRepetits, cronometre);
                if (CD.novaPosRankingCrono(punts[1], punts[0])){
                    System.out.println("NOVA POSICIO AL RANKING!");
                    System.out.println("Introdueix el teu nom: ");
                    Scanner scanner = new Scanner(System.in);
                    player = scanner.nextLine();
                }
                CD.carregarRecords();
                if (CD.guardarRankingCrono(player, punts[1], punts[0])){
                    CD.guardarRecords();
                }
            }
        }
    }
    private static void escollirRol(){
        menuRol();
        Scanner R = new Scanner(System.in);
        int r = R.nextInt();
        if (r != 3){
            if (r == 1) Rol = 0;
            else if (r == 2) Rol = 1;
            else error();
        }
    }
    private static void play() throws Exception{
        menuPlay();
        Scanner Opcions = new Scanner(System.in);
        int op = Opcions.nextInt();
        while (op != 4){
            if (op == 1) escollirRol();
            else if (op == 2) configuracio();
            else if (op == 3){
                if (CD.partidaGuardada()) {
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("-- Tens una partida guardada!");
                    System.out.println("-- Vols començar una nova i borrar l'antiga?");
                    System.out.println("-- 1) COMENÇAR     4) EXIT");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------");
                    Scanner play = new Scanner(System.in);
                    int opPlay = play.nextInt();
                    if (opPlay == 1) comencarPartida();
                    else error();
                }
                else comencarPartida();
            }
            else error();

            menuPlay();
            op = Opcions.nextInt();
        }
    }

    private static void dibuixaTauler(){
        int[][] intents = CD.getVectorIntents();
        int[][] correccions = CD.getVectorCorreccions();
        if(Rol == 0 || (CD.partidaAcabada() && !CD.partidaGuanyada())){
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

    private static void Ranking(){
        menuRankings();
        Scanner Opcions = new Scanner(System.in);
        int op = Opcions.nextInt();
        while (op != 12){
            if (op == 1) numBoles = 4;
            else if (op == 2) numBoles = 6;
            else if (op == 3) numBoles = 8;
            else if (op == 4) modeAjuda = true;
            else if (op == 5) modeAjuda = false;
            else if (op == 6) colorsRepetits = true;
            else if (op == 7) colorsRepetits = false;
            else if (op == 8) cronometre = 5;
            else if (op == 9) cronometre = 10;
            else if (op == 10){
                CD.inicialitzarPersistencia();
                CD.carregaRankingsClassic(numBoles, modeAjuda, colorsRepetits);
                CD.mostrarRankingsClassic(numBoles, modeAjuda, colorsRepetits);
            }
            else if (op == 11){
                CD.inicialitzarPersistencia();
                CD.carregaRankingsCrono(numBoles, modeAjuda, colorsRepetits, cronometre);
                CD.mostrarRankingsCrono(numBoles, modeAjuda, colorsRepetits, cronometre);
            }
            else error();

            menuRankings();
            op = Opcions.nextInt();
        }
    }

    public static void Records(){
        CD.inicialitzarPersistencia();
        CD.carregarRecords();
        CD.mostrarRecords();
    }

    public static void CarregarPartida() throws Exception {
        if (CD.partidaGuardada()){
            partidaGuardada = true;
            comencarPartida();
        }
        else System.out.println("No hi ha cap partida guardada!");
    }

    public static void main(String[] args) throws Exception{
        benvinguda();
        Scanner entradaDades = new Scanner(System.in);
        int ent = entradaDades.nextInt();
        while (ent != 6){
            if (ent == 1) play();
            else if (ent == 2) CarregarPartida();
            else if(ent == 3) Ranking();
            else if (ent == 4) Records();
            else if (ent == 5) regles();
            else error();
            benvinguda();
            ent = entradaDades.nextInt();
        }
    }
}

