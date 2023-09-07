package domini.model;

import java.util.ArrayList;

public class Records {

    /**
     * Variable de la classe Records.
     */
    ArrayList<PosJugador> records;

    /**
     * Creadora de la classe Records.
     */
    public Records(){
        records = new ArrayList<>();
    }

    /**
     * Aquest mètode s'encarrega de carregar uns records.
     *
     * @return  ranking és un ArrayList de PosJugador que representa els records.
     */
    public ArrayList<PosJugador> getRecords(){
        return records;
    }

    /**
     * Aquest mètode s'encarrega de guardar uns records.
     *
     * @param records és un ArrayList de PosJugador que representa els records.
     */
    public void setRecords(ArrayList<PosJugador> records){
        this.records = records;
    }

    /**
     * Aquest mètode s'encarrega de traduir un ArrayList de strings a una llista de records.
     *
     * @param records és un ArrayList de strings on cada string és un record d'una configuració de joc diferent.
     */
    public void carregarRecords(ArrayList<String> records) {

        if (!records.isEmpty()) this.records.clear();

        for (int i = 0; i < 12; ++i){
            String pos = records.get(i);
            int startIndex = pos.indexOf("Player: ") + "Player: ".length();
            int endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String player = pos.substring(startIndex, endIndex);

            startIndex = pos.indexOf("Intents: ") + "Intents: ".length();
            endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String stringIntents = pos.substring(startIndex, endIndex);
            int intents = Integer.parseInt(stringIntents);

            int indexTemps = pos.indexOf("Temps: ") + "Temps: ".length();
            String stringTemps = pos.substring(indexTemps);

            String[] numeros = stringTemps.split(":");
            int minutos = Integer.parseInt(numeros[0]);
            int segundos = Integer.parseInt(numeros[1]);
            int[] temps = new int[] {minutos, segundos};

            PosJugador PRC = new PosJugador(intents, player, temps);
            this.records.add(i, PRC);
        }

        for (int i = 12; i < records.size(); ++i){
            String pos = records.get(i);
            int startIndex = pos.indexOf("Player: ") + "Player: ".length();
            int endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String player = pos.substring(startIndex, endIndex);

            startIndex = pos.indexOf("Victories: ") + "Victories: ".length();
            endIndex = pos.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = pos.length();
            }
            String stringVictories = pos.substring(startIndex, endIndex);
            int victories = Integer.parseInt(stringVictories);

            int indexIntents = pos.indexOf("Intents: ") + "Intents: ".length();
            String stringIntents = pos.substring(indexIntents);
            int intents = Integer.parseInt(stringIntents);

            PosJugador PRC = new PosJugador(intents, player, victories);
            this.records.add(i, PRC);
        }
    }

    /**
     * Aquest mètode s'encarrega de traduir un llistat de records a un ArrayList de strings.
     *
     * @return un ArrayList de string que és el contingut que després anirà guardat a un arxiu.
     */
    public ArrayList<String> guardarRecords() {
        ArrayList<String> matRankings = new ArrayList<>();

        int boles = 4;
        for (int i = 0; i < 3; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: NO -> " + prc.obtePosicionsClassic();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 3; i < 6; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: NO -> " + prc.obtePosicionsClassic();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 6; i < 9; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: SI -> " + prc.obtePosicionsClassic();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 9; i < 12; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: SI -> " + prc.obtePosicionsClassic();
            matRankings.add(fila);
            boles += 2;
        }

        boles = 4;
        for (int i = 12; i < 15; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 5 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 15; i < 18; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 5 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 18; i < 21; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 5 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 21; i < 24; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 5 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }

        boles = 4;
        for (int i = 24; i < 27; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: NO, Cronometre: 10 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 27; i < 30; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: NO, Cronometre: 10 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 30; i < 33; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: NO, ColorsRepetits: SI, Cronometre: 10 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }
        boles = 4;
        for (int i = 33; i < 36; ++i){
            PosJugador prc = records.get(i);
            String fila = "NumBoles: " + boles + ", ModeAjuda: SI, ColorsRepetits: SI, Cronometre: 10 -> " + prc.obtePosicionsCrono();
            matRankings.add(fila);
            boles += 2;
        }

        return matRankings;
    }

    /**
     * Aquest mètode s'encarrega d'afegir un nou record en el mode classic.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param player és un string que representa el nom del jugador.
     * @param temps és un vector d'enters que representa el temps fet pel jugador.
     * @param numBoles és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param modeAjuda és un booleà que representa si la partida s'està jugant o no amb el mode ajuda activat.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant o no amb el mode de colors repetits activat.
     */
    public void afegirRecordClassic(int intents, String player, int[] temps, int numBoles, boolean modeAjuda, boolean colorsRepetits){
        PosJugador novaPosicio = new PosJugador(intents, player, temps);

        if (!modeAjuda){
            if(!colorsRepetits){
                if (numBoles == 4){
                    records.remove(0);
                    records.add(0, novaPosicio);
                }
                else if (numBoles == 6){
                    records.remove(1);
                    records.add(1, novaPosicio);
                }
                else if (numBoles == 8){
                    records.remove(2);
                    records.add(2, novaPosicio);
                }
            }
            else{
                if (numBoles == 4){
                    records.remove(3);
                    records.add(3, novaPosicio);
                }
                else if (numBoles == 6){
                    records.remove(4);
                    records.add(4, novaPosicio);
                }
                else if (numBoles == 8){
                    records.remove(5);
                    records.add(5, novaPosicio);
                }
            }
        }
        else{
            if(!colorsRepetits) {
                if (numBoles == 4) {
                    records.remove(6);
                    records.add(6, novaPosicio);
                } else if (numBoles == 6) {
                    records.remove(7);
                    records.add(7, novaPosicio);
                } else if (numBoles == 8) {
                    records.remove(8);
                    records.add(8, novaPosicio);
                }
            }
            else{
                if (numBoles == 4){
                    records.remove(9);
                    records.add(9, novaPosicio);
                }
                else if (numBoles == 6){
                    records.remove(10);
                    records.add(10, novaPosicio);
                }
                else if (numBoles == 8){
                    records.remove(11);
                    records.add(11, novaPosicio);
                }
            }
        }
    }

    /**
     * Aquest mètode s'encarrega d'afegir un nou record en el mode crono.
     *
     * @param intents és un enter que representa els intents fets pel jugador.
     * @param player és un string que representa el nom del jugador.
     * @param victories és un vector d'enters que representa les victories fetes pel jugador.
     * @param numBoles és un enter que representa el nombre de boles amb les quals s'està jugant la partida.
     * @param modeAjuda és un booleà que representa si la partida s'està jugant o no amb el mode ajuda activat.
     * @param colorsRepetits és un booleà que representa si la partida s'està jugant o no amb el mode de colors repetits activat.
     * @param cronometre és un enter que representa el temps amb el qual s'ha jugat la partida en modo crono.
     */
    public void afegirRecordCrono(int intents, String player, int victories, int numBoles, boolean modeAjuda, boolean colorsRepetits, int cronometre){
        PosJugador novaPosicio = new PosJugador(intents, player, victories);

        if (cronometre == 5) {
            if (!modeAjuda) {
                if (!colorsRepetits) {
                    if (numBoles == 4) {
                        records.remove(12);
                        records.add(12, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(13);
                        records.add(13, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(14);
                        records.add(14, novaPosicio);
                    }
                } else {
                    if (numBoles == 4) {
                        records.remove(15);
                        records.add(15, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(16);
                        records.add(16, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(17);
                        records.add(17, novaPosicio);
                    }
                }
            } else {
                if (!colorsRepetits) {
                    if (numBoles == 4) {
                        records.remove(18);
                        records.add(18, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(19);
                        records.add(19, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(20);
                        records.add(20, novaPosicio);
                    }
                } else {
                    if (numBoles == 4) {
                        records.remove(21);
                        records.add(21, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(22);
                        records.add(22, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(23);
                        records.add(23, novaPosicio);
                    }
                }
            }
        }
        else if (cronometre == 10){
            if (!modeAjuda) {
                if (!colorsRepetits) {
                    if (numBoles == 4) {
                        records.remove(24);
                        records.add(24, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(25);
                        records.add(25, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(26);
                        records.add(26, novaPosicio);
                    }
                } else {
                    if (numBoles == 4) {
                        records.remove(27);
                        records.add(27, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(28);
                        records.add(28, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(29);
                        records.add(29, novaPosicio);
                    }
                }
            } else {
                if (!colorsRepetits) {
                    if (numBoles == 4) {
                        records.remove(30);
                        records.add(30, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(31);
                        records.add(31, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(32);
                        records.add(32, novaPosicio);
                    }
                } else {
                    if (numBoles == 4) {
                        records.remove(33);
                        records.add(33, novaPosicio);
                    } else if (numBoles == 6) {
                        records.remove(34);
                        records.add(34, novaPosicio);
                    } else if (numBoles == 8) {
                        records.remove(35);
                        records.add(35, novaPosicio);
                    }
                }
            }
        }
    }

    /**
     * Aquest mètode s'encarrega de mostrar els records per pantalla.
     */
    public void mostrarRecords(){
        System.out.println("MODE CLASSIC:");
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: NO:");
        int boles = 4;
        for (int i = 0; i < 3; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 3; i < 6; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 6; i < 9; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 9; i < 12; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            boles += 2;
        }

        System.out.println();
        System.out.println();
        System.out.println("MODE CRONO 5 minuts:");
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 12; i < 15; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 15; i < 18; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 18; i < 21; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 21; i < 24; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }

        System.out.println();
        System.out.println();
        System.out.println("MODE CRONO 10 minuts:");
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 24; i < 27; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 27; i < 30; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 30; i < 33; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
        System.out.println();
        System.out.println("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 33; i < 36; ++i) {
            System.out.println("NumBoles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            boles += 2;
        }
    }


    /**
     * Aquest mètode s'encarrega de preparar l'Array de Strings dels records que es veuran des de la presentació.
     *
     * @return un ArrayList de Strings que representan els rankings.
     */
    public ArrayList<String> getRecordsPresentacio(){
        ArrayList<String> matRankings = new ArrayList<>();

        matRankings.add("MODE CLASSIC:");
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: NO:");
        int boles = 4;
        for (int i = 0; i < 3; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Intents: --- , Temps: --- : --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 3; i < 6; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Intents: --- , Temps: --- : --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 6; i < 9; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Intents: --- , Temps: --- : --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 9; i < 12; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Intents: " + this.records.get(i).getIntents() + ", Temps: " + this.records.get(i).getTemps()[0] + ":" + this.records.get(i).getTemps()[1]);
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Intents: --- , Temps: --- : --- ");
            boles += 2;
        }

        matRankings.add(" ");
        matRankings.add(" ");
        matRankings.add("MODE CRONO 5 minuts:");
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 12; i < 15; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 15; i < 18; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 18; i < 21; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 21; i < 24; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }

        matRankings.add(" ");
        matRankings.add(" ");
        matRankings.add("MODE CRONO 10 minuts:");
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 24; i < 27; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: NO, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 27; i < 30; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: NO:");
        boles = 4;
        for (int i = 30; i < 33; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }
        matRankings.add(" ");
        matRankings.add("ModeAjuda: SI, ColorsRepetits: SI:");
        boles = 4;
        for (int i = 33; i < 36; ++i) {
            if (!this.records.get(i).getPlayer().equals("EMPTY")) matRankings.add("Nombre de boles: " + boles + " -> Player: " + this.records.get(i).getPlayer() + ", Victories: " + this.records.get(i).getVictories() + ", Intents: " + this.records.get(i).getIntents());
            else matRankings.add("Nombre de boles: " + boles + " -> Player: --------- , Victories: --- , Intents: --- ");
            boles += 2;
        }

        return matRankings;
    }
}
