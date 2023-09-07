package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import presentacio.utils.CreateRoundButton;
import presentacio.controladors.CtrlPresentacio;

public class VistaTauler {
    /**
     * Atributs de la classe VistaTauler
     */
    private final CtrlPresentacio ctrlPresentacio;

    private final int rol;
    private final int numBoles;
    private final boolean repetits;
    private boolean settingSolucio = false;
    private int currentNum;
    private final int mode;
    private final int temps;
    private final boolean ajuda;
    private int currentAttempt = 0;
    private int minuts = 0;
    private int segons = 0;
    private boolean correccioIncorrecta = false;
    private boolean pausat = false;
    private int[] currentArray;
    private final HashMap<Integer,Color> mapaColors = new HashMap<>();
    private final HashMap<Integer,Color> mapaColorsCorreccions = new HashMap<>();


    private JButton[][] correccions;
    private JButton[][] intents;
    private final JButton pausa = new JButton("Pausa");
    private final JButton envia = new JButton("Envia");
    private final JButton ok = new JButton("OK");
    private JButton[] selectorColor;
    private JButton[] solucio;
    private final JLabel labelTimer = new JLabel();
    private Timer timer;

    private final JFrame frameVista = new JFrame("Vista Tauler");
    private final JPanel panelContinguts = new JPanel();
    private final JPanel header = new JPanel();
    private final JPanel colors = new JPanel();
    private final JPanel panelCorreccions = new JPanel();
    private final JPanel panelIntents = new JPanel();
    private final JPanel[] panelCorreccio = new JPanel[10];
    private final JPanel[] panelIntent = new JPanel[10];
    private final JPanel taulers = new JPanel();
    private final JPanel panelEnvia = new JPanel();
    private final JPanel panelSolucio = new JPanel();

    /**
     * Mètode per crear un tauler de joc corresponent a una partida amb la configuració indicada.
     * @param ctrl El controlador de presentació.
     * @param rol El rol del jugador: 0 si és codemaker, 1 si és codebreaker.
     * @param numBoles El nombre de boles de la partida
     * @param repetits Si la partida admet colors repetits.
     * @param ajuda Si el mode ajuda està activat.
     * @param mode El mode de joc: 0 si és clàssic, 1 si és crono.
     * @param temps El temps màxim de la partida si es juga en crono, 0 en altres casos.
     */
    public VistaTauler(CtrlPresentacio ctrl, int rol, int numBoles, boolean repetits, boolean ajuda, int mode, int temps){
        ctrlPresentacio = ctrl;
        this.rol = rol;
        this.numBoles = numBoles;
        this.repetits = repetits;
        this.mode = mode;
        this.temps = temps;
        this.ajuda = ajuda;

        ctrl.inicialitzarPartida(rol,numBoles,repetits,ajuda,mode,temps);
        solucio = new JButton[numBoles];
        if(rol == 0) settingSolucio = true;
        else{
            if(mode == 1){
                minuts = temps;
            }
            settingSolucio = false;
            ctrlPresentacio.establirSolucio();
        }
        for(int i = 0; i < numBoles; i++){
            solucio[i] = new CreateRoundButton();
            solucio[i].setBackground((Color.GRAY));
        }
        inicialitzacioComuna();
    }

    /**
     * Mètode per crear el tauler de joc a partir d'una partida guardada.
     * @param ctrl El controlador de presentació.
     */
    public VistaTauler(CtrlPresentacio ctrl){
        ctrlPresentacio = ctrl;
        rol = 1;
        mode = 0;
        temps = 0;
        numBoles = ctrlPresentacio.getNumBoles();
        repetits = ctrlPresentacio.getRepetits();
        ajuda = ctrlPresentacio.getAjuda();
        int[] tempsPartida = ctrlPresentacio.getTemps();
        minuts = tempsPartida[0];
        segons = tempsPartida[1];
        inicialitzacioComuna();
        int[][] correccionsPartida = ctrlPresentacio.getCorreccions();
        int[][] intentsPartida = ctrlPresentacio.getIntents();
        while(currentAttempt < intentsPartida.length){
            dibuixaCorreccions(correccionsPartida);
            dibuixaIntents(intentsPartida);
            currentAttempt++;
        }
        for(int i = 0; i < numBoles; i++){
            intents[9][i].setEnabled(false);
            intents[9-currentAttempt][i].setEnabled(true);
            intents[9-currentAttempt][i].setBackground(Color.GRAY);
        }
    }

    /**
     * Mètode que s’encarrega de la part de la creació de la vista que és comuna en totes les creadores.
     */
    private void inicialitzacioComuna(){
        currentArray = new int[numBoles];
        currentNum = -1;
        selectorColor = new JButton[8];
        for(int i = 0; i < numBoles; i++) currentArray[i] = -1;
        mapaColors.put(-1,Color.GRAY);
        mapaColors.put(0,Color.RED);
        mapaColors.put(1,Color.BLUE);
        mapaColors.put(2,Color.GREEN);
        mapaColors.put(3,Color.ORANGE);
        mapaColors.put(4,Color.MAGENTA);
        mapaColors.put(5,Color.YELLOW);
        mapaColors.put(6,Color.PINK);
        mapaColors.put(7,Color.CYAN);
        if(rol == 0) mapaColorsCorreccions.put(0,Color.GRAY);
        else mapaColorsCorreccions.put(0,Color.lightGray);
        mapaColorsCorreccions.put(1,Color.WHITE);
        mapaColorsCorreccions.put(2,Color.BLACK);
        intents = new JButton[10][numBoles];
        correccions = new JButton[10][numBoles];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < numBoles; j++) {
                correccions[i][j] = new CreateRoundButton();
                intents[i][j] = new CreateRoundButton();
                correccions[i][j].setBackground(Color.lightGray);
                intents[i][j].setBackground(Color.lightGray);
            }
        }
        inicialitzarComponents();
    }

    /**
     * Mètode per fer visible la vista.
     */
    public void ferVisible(){
        frameVista.pack();
        frameVista.setLocationRelativeTo(null);
        frameVista.setVisible(true);
    }

    /**
     * Mètode per fer invisible la vista.
     */
    public void ferInvisible(){
        frameVista.setVisible(false);
    }

    /**
     * Mètode per activar la vista.
     */
    public void activar(){
        frameVista.setEnabled(true);
    }

    /**
     * Mètode per desactivar la vista.
     */
    public void desactivar(){
        frameVista.setEnabled(false);
    }

    /**
     * Listener per triar un color.
     * @param i Int que representa el color que s'acaba de seleccionar
     */
    public void actionPerformed_pressColor (int i){
        currentNum = i;
    }

    /**
     * Listener per assignar un color a un element d’un intent o correcció.
     * @param event Event que activa la funció.
     * @param i Int que es refereix a la posició de l'array que s'està definint.
     */
    public void actionPerformed_pressArray (ActionEvent event, int i){
        currentArray[i] = currentNum;
        Color c;
        if(rol == 1 || settingSolucio) c = mapaColors.get(currentNum);
        else c = mapaColorsCorreccions.get(currentNum);
        ((JButton) event.getSource()).setBackground(c);
    }

    /**
     * Listener per enviar una correcció o intent.
     */
    public void actionPerformed_pressEnvia (){
        VistaDialeg vistaDialeg = new VistaDialeg();
        if(rol == 0){
            if(correccioIncorrecta){
                int[][]correccionsMaquina = ctrlPresentacio.getCorreccions();
                correccioIncorrecta= !correcionsIguals(correccionsMaquina[correccionsMaquina.length - 1],currentArray);
            }
            else if(!ctrlPresentacio.ferCorreccio(currentArray)){
                correccioIncorrecta = true;
            }
            if(!correccioIncorrecta){
                ctrlPresentacio.ferTornMaquina();
                if (!ctrlPresentacio.partidaAcabada()) dibuixaIntents(ctrlPresentacio.getIntents());
                currentAttempt = currentAttempt + 1;
                for (int i = 0; i < numBoles; i++) {
                    currentArray[i] = 0;
                    if(currentAttempt <11){
                        correccions[9 - currentAttempt + 1][i].setEnabled(true);
                        if(!ctrlPresentacio.partidaAcabada() && !ctrlPresentacio.partidaGuanyada()) correccions[9 - currentAttempt + 1][i].setBackground(Color.GRAY);
                    }
                    correccions[9 - currentAttempt+2][i].setEnabled(false);
                }
            }
            else{
                String[] strBotons = {"OK"};
                vistaDialeg.setDialeg("Error en la correccio", "La teva correccio no correspon a l'intent", strBotons, 4, true);
            }
        }
        else{
            if(patroValid()) {
                int[] intentArray = currentArray.clone();
                ctrlPresentacio.ferIntent(intentArray);
                dibuixaCorreccions(ctrlPresentacio.getCorreccions());
                currentAttempt = currentAttempt + 1;
                for (int i = 0; i < numBoles; i++){
                    currentArray[i] = -1;
                    if(currentAttempt < 10){
                        intents[9-currentAttempt][i].setEnabled(true);
                        if(!ctrlPresentacio.partidaAcabada() && !ctrlPresentacio.partidaGuanyada())intents[9-currentAttempt][i].setBackground(Color.GRAY);
                    }
                    intents[9-currentAttempt+1][i].setEnabled(false);
                }
            }
            else{
                String[] strBotons = {"OK"};
                vistaDialeg.setDialeg("Error en l'intent", "El teu intent no correspon a la configuracio de la partida", strBotons, 4, true);
            }
        }
        if(ctrlPresentacio.partidaAcabada() && !correccioIncorrecta) {
            timer.stop();
            envia.setEnabled(false);
            pausa.setEnabled(false);
            if (rol == 0) {
                String text;
                if(ctrlPresentacio.partidaGuanyada()) text = "L'algoritme ha trobat la solucio";
                else text = "L'algoritme no ha trobat la solucio";
                String[] strBotons = {"Restart", "Menu"};
                int sel = vistaDialeg.setDialeg("Partida Acabada", text, strBotons, 4, false);
                switch (sel) {
                    case 0:
                        ctrlPresentacio.sincronitzacioTaulerATauler(rol, numBoles, repetits, ajuda, mode, temps);
                        break;
                    case 1:
                        ctrlPresentacio.sincronitzacioTaulerAInici();
                        break;
                }
            }
            else{
                String text;
                if(ctrlPresentacio.partidaGuanyada()){
                    ctrlPresentacio.guardarPartida(mode,numBoles,ajuda,repetits,minuts,segons,temps);
                    text = "Has guanyat la partida!";
                }
                else text = "Has perdut la partida";
                String[] strBotons = {"Restart", "Menu"};
                int sel = vistaDialeg.setDialeg("Partida Acabada", text, strBotons, 4, false);
                switch (sel) {
                    case 0:
                        ctrlPresentacio.sincronitzacioTaulerATauler(rol, numBoles, repetits, ajuda, mode, temps);
                        break;
                    case 1:
                        ctrlPresentacio.sincronitzacioTaulerAInici();
                        break;
                }
            }
        }
        else if(mode == 1 && ctrlPresentacio.partidaGuanyada()){
            ctrlPresentacio.nextPartida();
            ctrlPresentacio.establirSolucio();
            restartTauler();
        }
    }

    /**
     * Listener per pausar la partida.
     */
    public void actionPerformed_pressPausa (){
        pausat = true;
        VistaDialeg vistaDialeg = new VistaDialeg();

        if(rol == 1 && mode == 0) {
            String[] strBotons = {"Restart", "Guardar", "Menu"};
            int sel = vistaDialeg.setDialeg("Pausa", "Menu Pausa", strBotons, 4, true);
            switch (sel) {
                case 0:
                    ctrlPresentacio.sincronitzacioTaulerATauler(rol, numBoles, repetits, ajuda, mode, temps);
                    break;
                case 1:
                    ctrlPresentacio.guardarEstatPartida(new int[]{minuts,segons});
                    ctrlPresentacio.sincronitzacioTaulerAInici();
                    break;
                case 2:
                    ctrlPresentacio.borrarPartidaGuardada();
                    ctrlPresentacio.sincronitzacioTaulerAInici();
                    break;
            }
        }
        else{
            String[] strBotons = {"Restart", "Menu"};
            int sel = vistaDialeg.setDialeg("Pausa", "Menu Pausa", strBotons, 4,true);
            switch (sel) {
                case 0:
                    ctrlPresentacio.sincronitzacioTaulerATauler(rol, numBoles, repetits, ajuda, mode, temps);
                    break;
                case 1:
                    ctrlPresentacio.sincronitzacioTaulerAInici();
                    break;
            }
        }
        pausat = false;
    }

    /**
     * Listener per confirmar la selecció d’una solució.
     */
    public void actionPerformed_pressOk (){
        if(patroValid()) {
            envia.setVisible(true);
            ok.setVisible(false);
            settingSolucio = false;
            colors.removeAll();
            currentNum = 0;
            selectorColor = new JButton[3];
            int[] solucioArray = currentArray.clone();
            ctrlPresentacio.marcarSolucio(solucioArray);
            for (int i = 0; i < numBoles; i++) {
                currentArray[i] = 0;
                solucio[i].setEnabled(false);
                correccions[9][i].setEnabled(true);
                correccions[9][i].setBackground(Color.GRAY);
            }
            selectorColor[2] = new CreateRoundButton();
            selectorColor[2].setBackground(mapaColorsCorreccions.get(2));
            colors.add(new JPanel());
            colors.add(selectorColor[2]);
            colors.add(new JPanel());
            selectorColor[1] = new CreateRoundButton();
            selectorColor[1].setBackground(mapaColorsCorreccions.get(1));
            colors.add(selectorColor[1]);
            colors.add(new JPanel());
            selectorColor[0] = new CreateRoundButton();
            selectorColor[0].setBackground(mapaColorsCorreccions.get(0));
            colors.add(selectorColor[0]);
            colors.add(new JPanel());
            for (int i = 0; i < 3; i++) {
                int num = i;
                selectorColor[i].addActionListener
                        (event -> actionPerformed_pressColor(num));
            }
            colors.revalidate();
            frameVista.setMinimumSize(frameVista.getPreferredSize());
            ctrlPresentacio.ferTornMaquina();
            dibuixaIntents(ctrlPresentacio.getIntents());
            currentAttempt = currentAttempt + 1;
        }
        else{
            VistaDialeg vistaDialeg = new VistaDialeg();
            String[] strBotons = {"OK"};
            vistaDialeg.setDialeg("Error en la solucio", "La teva solucio no correspon a la configuracio de la partida", strBotons, 4, true);
        }
    }

    /**
     * Mètode per assignar els listeners als components respectius.
     */
    private void assignarListeners(){
        int numColors = 8;
        for(int i = 0; i < numColors; i++){
            int num = i;
            selectorColor[i].addActionListener
                    (event -> actionPerformed_pressColor(num));
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < numBoles; j++){
                int num = j;
                intents[i][j].addActionListener
                        (event -> actionPerformed_pressArray(event, num));
                correccions[i][j].addActionListener
                        (event -> actionPerformed_pressArray(event, num));
            }
        }
        if(rol == 0) {
            for (int i = 0; i < numBoles; i++) {
                int num = i;
                solucio[i].addActionListener
                        (event -> actionPerformed_pressArray(event, num));
            }
        }
        ok.addActionListener
                (event -> actionPerformed_pressOk());
        pausa.addActionListener
                (event -> actionPerformed_pressPausa());
        envia.addActionListener
                (event -> actionPerformed_pressEnvia());
    }

    /**
     * Mètode per inicialitzar tots els components de la vista.
     */
    private void inicialitzarComponents(){
        inicialitzarFrameVista();
        inicialitzarContinguts();
        inicialitzarTaulers();
        inicialitzarIntents();
        inicialitzarCorreccions();
        inicialitzarHeader();
        inicialitzarColors();
        inicialitzarEnvia();
        if(rol == 0) inicialitzarSolucio();
        assignarListeners();
    }

    /**
     * Mètode per inicialitzar panell que conté tots els components de la vista.
     */
    private void inicialitzarContinguts(){
        panelContinguts.setLayout(new BoxLayout(panelContinguts, BoxLayout.Y_AXIS));
        panelContinguts.add(new JPanel());
        panelContinguts.add(header);
        if(rol == 0){
            panelContinguts.add(new JPanel());
            panelContinguts.add(panelSolucio);
        }
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(new JPanel());
        panelContinguts.add(separator1);
        panelContinguts.add(taulers);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(separator2);
        panelContinguts.add(new JPanel());
        panelContinguts.add(colors);
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelEnvia);
        panelContinguts.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el frame de la vista.
     */
    private void inicialitzarFrameVista(){
        frameVista.setResizable(false);
        frameVista.setMaximumSize(frameVista.getPreferredSize());
        frameVista.setMinimumSize(frameVista.getPreferredSize());
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContinguts);
        if(rol == 1 && mode == 0){
            frameVista.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    ctrlPresentacio.borrarPartidaGuardada();
                }
            });
        }
    }

    /**
     * Mètode per inicialitzar el panell que conté intents i correccions.
     */
    private void inicialitzarTaulers(){
        taulers.setLayout(new BoxLayout(taulers, BoxLayout.X_AXIS));
        taulers.add(new JPanel());
        taulers.add(panelIntents);
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize( new Dimension(1, Integer.MAX_VALUE) );
        taulers.add(new JPanel());
        taulers.add(separator);
        taulers.add(new JPanel());
        taulers.add(panelCorreccions);
        taulers.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar els panells que contenen els intents.
     */
    private void inicialitzarIntents(){
        panelIntents.setLayout(new BoxLayout(panelIntents, BoxLayout.Y_AXIS));
        for(int i = 0; i < 10; i++){
            panelIntents.add(new JPanel());
            panelIntent[i] = new JPanel();
            panelIntents.add(panelIntent[i]);
            panelIntent[i].setLayout(new BoxLayout(panelIntent[i], BoxLayout.X_AXIS));
            for(int j = 0; j < numBoles; j++){
                panelIntent[i].add(intents[i][j]);
                if(j < numBoles - 1) panelIntent[i].add(new JPanel());
                if(i < 9) intents[i][j].setEnabled(false);
                else if(rol == 0) intents[i][j].setEnabled(false);
            }
        }
        if(rol == 1){
            for(int i = 0; i < numBoles; i++) intents[9][i].setBackground(Color.GRAY);
        }
        panelIntents.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar els panells que contenen les correccions.
     */
    private void inicialitzarCorreccions(){
        panelCorreccions.setLayout(new BoxLayout(panelCorreccions, BoxLayout.Y_AXIS));
        for(int i = 0; i < 10; i++){
            panelCorreccions.add(new JPanel());
            panelCorreccio[i] = new JPanel();
            panelCorreccions.add(panelCorreccio[i]);
            panelCorreccio[i].setLayout(new BoxLayout(panelCorreccio[i], BoxLayout.X_AXIS));
            for(int j = 0; j < numBoles; j++){
                panelCorreccio[i].add(correccions[i][j]);
                if(j < numBoles - 1) panelCorreccio[i].add(new JPanel());
                correccions[i][j].setEnabled(false);
            }
        }
        panelCorreccions.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de pausa i el timer.
     */
    private void inicialitzarHeader(){
        ActionListener taskPerformer = event -> {
            if(!pausat) {
                if (mode == 1) {
                    segons = segons - 1;
                    if (segons == -1) {
                        segons = 59;
                        minuts = minuts - 1;
                    }
                } else {
                    segons = segons + 1;
                    if (segons == 60) {
                        segons = 0;
                        minuts = minuts + 1;
                    }
                }
                String sMin = String.valueOf(minuts);
                String sSeg = String.valueOf(segons);
                if (minuts < 10) sMin = "0" + sMin;
                if (segons < 10) sSeg = "0" + sSeg;
                labelTimer.setText(sMin + ":" + sSeg + "  ");
            }
            if(mode == 1 && minuts == 0 && segons == 0){
                ctrlPresentacio.guardarPartida(mode,numBoles,ajuda,repetits,minuts,segons,temps);
                VistaDialeg vistaDialeg = new VistaDialeg();
                String[] strBotons = {"Restart", "Menu"};
                int sel = vistaDialeg.setDialeg("Partida Acabada", "La partida s'ha acabat", strBotons, 4, false);
                switch (sel) {
                    case 0:
                        ctrlPresentacio.sincronitzacioTaulerATauler(rol, numBoles, repetits, ajuda, mode, temps);
                        break;
                    case 1:
                        ctrlPresentacio.sincronitzacioTaulerAInici();
                        break;
                }
            }
        };
        timer = new Timer(1000,taskPerformer);
        timer.start();
        header.setLayout(new BorderLayout());
        header.add(pausa, BorderLayout.WEST);
        String sMin = String.valueOf(minuts);
        String sSeg = String.valueOf(segons);
        if(minuts < 10) sMin = "0" + sMin;
        if(segons < 10) sSeg = "0" + sSeg;
        labelTimer.setText(sMin+":"+sSeg+"  ");
        header.add(labelTimer, BorderLayout.EAST);
    }

    /**
     * Mètode per inicialitzar el panell que conté els elements relacionats a la selecció de colors.
     */
    private void inicialitzarColors(){
        colors.add(new JPanel());
        colors.setLayout(new BoxLayout(colors, BoxLayout.X_AXIS));
        for(int i = 0; i < 8; i++) {
            selectorColor[i] = new CreateRoundButton();
            selectorColor[i].setBackground(mapaColors.get(i));
            colors.add(selectorColor[i]);
            if (i < 7) colors.add(new JPanel());
        }
        colors.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó per enviar un intent o correcció.
     */
    private void inicialitzarEnvia(){
        panelEnvia.setLayout(new BoxLayout(panelEnvia, BoxLayout.X_AXIS));
        panelEnvia.add(envia);
        if(rol == 0) envia.setVisible(false);
    }

    /**
     * Mètode per inicialitzar el panell que conté els elements relacionats amb la selecció d’una solució.
     */
    private void inicialitzarSolucio(){
        panelSolucio.setLayout(new BoxLayout(panelSolucio, BoxLayout.X_AXIS));
        for(int i = 0; i < numBoles; i++){
            panelSolucio.add(new JPanel());
            panelSolucio.add(solucio[i]);
        }
        panelSolucio.add(new JPanel());
        panelSolucio.add(ok);
    }

    /**
     * Mètode per comprovar si l’intent que està fent el jugador és vàlid per la configuració de la partida.
     * @return Retorna cert si el patró està completament definit i és correcte per la configuració de la partida i retorna fals en cas contrari.
     */
    private boolean patroValid(){
        for(int i = 0; i < numBoles; i++){
            if(currentArray[i] == -1) return false;
        }
        if(!repetits){
            for(int i = 0; i < numBoles; i++){
                for(int j = i + 1; j < numBoles; j++){
                    if(currentArray[i] == currentArray[j]) return false;
                }
            }
        }
        return true;
    }

    /**
     * Mètode per dibuixar l’últim intent.
     * @param intentsMaquina Vector dels intents fets per la màquina.
     */
    private void dibuixaIntents(int[][] intentsMaquina){
        for(int i = 0; i < numBoles; i++) {
            intents[9 - currentAttempt][i].setBackground(mapaColors.get(intentsMaquina[currentAttempt][i]));
        }
    }

    /**
     * Mètode per dibuixar la última correcció.
     * @param correccionsMaquina Vector de les correccions fetes per la màquina.
     */
    private void dibuixaCorreccions(int[][] correccionsMaquina){
        for(int i = 0; i < numBoles; i++) {
            correccions[9 - currentAttempt][i].setBackground(mapaColorsCorreccions.get(correccionsMaquina[currentAttempt][i]));
        }
    }

    /**
     * Mètode per determinar si dues correcions són iguals.
     * @param a Vector que representa una primera correcció.
     * @param b Vector que representa una segona correcció.
     * @return Retorna cert si les correccions són iguals i fals en cas contrari.
     */
    private boolean correcionsIguals(int[] a,int[] b){
        int num2a = 0;
        int num2b = 0;
        int num1a = 0;
        int num1b = 0;
        for(int i = 0; i < numBoles; i++){
            if(a[i] == 2) num2a++;
            if(b[i] == 2) num2b++;
            if(a[i] == 1) num1a++;
            if(b[i] == 1) num1b++;
        }
        return (num2a == num2b && num1a == num1b);
    }

    /**
     * Mètode per crear un diàleg que demana el nom del jugador i retorna el valor inserit.
     * @return Retorna la String inserida per l'usuari, si aquesta excedeix una llargada de 10 caràcters retorna els 10 primers caràcters.
     */
    public String getNomJugador(){
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Insereix el teu nom (maxim 10 caracters, els caracters especials s'ignoraran): ");
        JTextField txt = new JTextField(10);
        panel.add(lbl);
        panel.add(txt);
        JOptionPane.showOptionDialog(null, panel, "La teva puntuacio ha entrat al ranquing!", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options , options[0]);
        String nom = txt.getText().replaceAll("[^a-zA-Z0-9_-]", "");
        int num = Math.min(nom.length(), 10);
        return nom.substring(0,num);
    }

    /**
     * Mètode que deixa el tauler en l’estat inicial d’una partida codebreaker.
     */
    private void restartTauler(){
        currentAttempt = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < numBoles; j++){
                if(i < 9){
                    intents[i][j].setEnabled(false);
                    intents[i][j].setBackground(Color.lightGray);
                }
                else{
                    intents[i][j].setEnabled(true);
                    intents[i][j].setBackground(Color.GRAY);
                }
                correccions[i][j].setBackground(Color.lightGray);
            }
        }
    }
}
