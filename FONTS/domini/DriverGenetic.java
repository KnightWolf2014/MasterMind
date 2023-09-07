package domini;

import domini.model.Genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverGenetic {
    static int numBoles = 6;
    static boolean repetit = true;

    private static void llegirNumBoles(){
        System.out.println("Decideixi el nombre de boles:");
        System.out.println("1 - 6 boles");
        System.out.println("2 - 8 boles");
        Scanner entrada = new Scanner(System.in);
        int num = entrada.nextInt();
        if(num == 1) numBoles = 6;
        else numBoles = 8;
    }

    private static void llegirRepetit(){
        System.out.println("Decideixi si es permeten boles repetides:");
        System.out.println("1 - Activades");
        System.out.println("2 - Desactivades");
        Scanner entrada = new Scanner(System.in);
        int num = entrada.nextInt();
        repetit = num == 1;
    }

    private static ArrayList<Integer> llegirSolucio(){
        System.out.println("Introdueixi la solucio:");
        Scanner entrada = new Scanner(System.in);
        ArrayList<Integer> solucio = new ArrayList<>();
        for(int i = 0; i < numBoles; i++){
            solucio.add(entrada.nextInt());
        }
        return solucio;
    }

    public static void menu(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("------------------------ MENU GENETIC  ------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.print("-- Configuracio actual: "+ numBoles + " i ");
        if(!repetit && numBoles != 6) System.out.print("no ");
        System.out.println("repetits");
        System.out.println("-- 1) Establir numero de boles");
        System.out.println("-- 2) Establir repetits");
        System.out.println("-- 3) Establir solucio");
        System.out.println("-- 4) Sortir");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void main(String[] args) throws Exception{
        menu();
        Scanner entrada = new Scanner(System.in);
        int option = entrada.nextInt();
        while(option != 4){
            if(option == 1) llegirNumBoles();
            if(option == 2) {
                if (numBoles == 6) repetit = true;
                else llegirRepetit();
            }
            if(option == 3) {
                Genetic algoritme = new Genetic(numBoles, 8, repetit);
                ArrayList<Integer> sol = llegirSolucio();
                List<List<Integer>> intents;
                intents = algoritme.solve(sol);
                for (List<Integer> intent : intents) {
                    for (int j = 0; j < numBoles; j++) {
                        System.out.print(intent.get(j));
                    }
                    System.out.println();
                }
            }
            menu();
            option = entrada.nextInt();
        }
    }
}
