package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import presentacio.controladors.CtrlPresentacio;

public class VistaConfiguracio {
    /**
     * Atributs de la classe VistaConfiguracio
     */
    private final CtrlPresentacio ctrlPresentacio;

    private int rol = 0;
    private int numBoles = 4;
    private boolean modeRepetits = false;
    private boolean modeAjuda = false;
    private int modeJoc = 0;
    private int temps = 0;

    private final JFrame frameVista = new JFrame("Vista Configuracio");
    private final JPanel panelContinguts = new JPanel();

    private final JRadioButton codeMaker = new JRadioButton("Codemaker");
    private final JRadioButton codeBreaker = new JRadioButton("Codebreaker");
    private final ButtonGroup grupRol = new ButtonGroup();
    private final JPanel radioRol = new JPanel();

    private final JRadioButton boles4 = new JRadioButton("4");
    private final JRadioButton boles6 = new JRadioButton("6");
    private final JRadioButton boles8 = new JRadioButton("8");
    private final ButtonGroup grupBoles = new ButtonGroup();
    private final JPanel radioBoles = new JPanel();

    private final JCheckBox repetits = new JCheckBox("Colors repetits");
    private final JCheckBox ajuda = new JCheckBox("Mode ajuda");

    private final JRadioButton classic = new JRadioButton("Classic");
    private final JRadioButton crono = new JRadioButton("Crono");
    private final ButtonGroup grupMode = new ButtonGroup();
    private final JButton menu = new JButton("Menu");
    private final JPanel peu = new JPanel();
    private final JButton start = new JButton("Iniciar");
    private final JPanel panelTitol = new JPanel();
    private final JPanel panelRepetits = new JPanel();
    private final JPanel panelAjuda = new JPanel();
    private final JPanel panelStart = new JPanel();
    private final JPanel panelMode = new JPanel();
    private final JPanel panelTemps = new JPanel();
    private final JRadioButton minuts5 = new JRadioButton("5 minuts");
    private final JRadioButton minuts10 = new JRadioButton("10 minuts");
    private final ButtonGroup grupTemps = new ButtonGroup();

    /**
     * Mètode per crear la vista configuració.
     * @param ctrl El controlador de presentació.
     */
    public VistaConfiguracio(CtrlPresentacio ctrl){
        ctrlPresentacio = ctrl;
        inicialitzarComponents();
    }

    /**
     * Mètode per fer visible la vista.
     */
    public void ferVisible(){
        frameVista.pack();
        frameVista.setVisible(true);
    }

    /**
     * Mètode per fer invisible la vista.
     */
    public void ferInvisible(){
        frameVista.setVisible(false);
    }

    /**
     * Mètode per desactivar la vista.
     */
    public void desactivar(){
        frameVista.setEnabled(false);
    }

    /**
     * Listener per establir el nombre de boles a quatre.
     */
    public void actionPerformed_press4boles(){
        numBoles = 4;
    }

    /**
     * Listener per establir el nombre de boles a sis.
     */
    public void actionPerformed_press6boles(){
        numBoles = 6;
    }

    /**
     * Listener per establir el nombre de boles a vuit.
     */
    public void actionPerformed_press8boles(){
        numBoles = 8;
    }

    /**
     * Listener per canviar el rol del joc a codemaker.
     */
    public void actionPerformed_pressCodemaker(){
        rol = 0;
        panelAjuda.setVisible(false);
        panelMode.setVisible(false);
        panelTemps.setVisible(false);
        modeAjuda = false;
        modeJoc = 0;
        temps = 0;
        ajuda.setSelected(false);
        classic.setSelected(true);
    }

    /**
     * Listener per canviar el rol del joc a codebreaker.
     */
    public void actionPerformed_pressCodebreaker(){
        rol = 1;
        temps = 0;
        panelAjuda.setVisible(true);
        panelMode.setVisible(true);
    }

    /**
     * Listener per canviar la selecció del temps a cinc minuts.
     */
    public void actionPerformed_pressMinuts5(){
        temps = 5;
    }

    /**
     * Listener per canviar la selecció del temps a deu minuts.
     */
    public void actionPerformed_pressMinuts10(){
        temps = 10;
    }

    /**
     * Listener per canviar la selecció de colors repetits.
     */
    public void actionPerformed_pressRepetit(){
        modeRepetits = !modeRepetits;
    }

    /**
     * Listener per canviar la selecció del mode ajuda.
     */
    public void actionPerformed_pressAjuda(){
        modeAjuda = !modeAjuda;
    }

    /**
     * Listener per canviar el mode de joc a clàssic.
     */
    public void actionPerformed_pressClassic(){
        modeJoc = 0;
        temps = 0;
        panelTemps.setVisible(false);
    }

    /**
     * Listener per canviar el mode de joc a crono.
     */
    public void actionPerformed_pressCrono(){
        modeJoc = 1;
        temps = 5;
        minuts5.setSelected(true);
        panelTemps.setVisible(true);
    }

    /**
     * Listener per canviar la vista al menú inicial.
     */
    public void actionPerformed_pressMenu(){
        ctrlPresentacio.sincronitzacioConfigAInici();
    }

    /**
     * Listener per canviar la selecció de colors repetits.
     */
    public void actionPerformed_pressStart() {
        if(ctrlPresentacio.existeixGuardada() && rol == 1 && modeJoc == 0){
            VistaDialeg vistaDialeg = new VistaDialeg();
            String[] strBotons = {"OK", "Cancel·lar"};
            int sel = vistaDialeg.setDialeg("Partida Guardada", "Existeix una partida guardada, iniciar-ne una nova l'eliminarà", strBotons, 4, false);
            switch (sel) {
                case 0:
                    ctrlPresentacio.sincronitzacioConfigATauler(rol, numBoles, modeRepetits, modeAjuda, modeJoc, temps);
                    break;
                case 1: break;
            }
        }
        else {
            ctrlPresentacio.sincronitzacioConfigATauler(rol, numBoles, modeRepetits, modeAjuda, modeJoc, temps);
        }
    }

    /**
     * Mètode per assignar els listeners als components respectius.
     */
    private void assignarListeners(){
        boles4.addActionListener
                (event -> actionPerformed_press4boles());
        boles6.addActionListener
                (event -> actionPerformed_press6boles());
        boles8.addActionListener
                (event -> actionPerformed_press8boles());
        codeMaker.addActionListener
                (event -> actionPerformed_pressCodemaker());
        codeBreaker.addActionListener
                (event -> actionPerformed_pressCodebreaker());
        repetits.addActionListener
                (event -> actionPerformed_pressRepetit());
        ajuda.addActionListener
                (event -> actionPerformed_pressAjuda());
        classic.addActionListener
                (event -> actionPerformed_pressClassic());
        crono.addActionListener
                (event -> actionPerformed_pressCrono());
        menu.addActionListener
                (event -> actionPerformed_pressMenu());
        start.addActionListener
                (event -> actionPerformed_pressStart());
        minuts5.addActionListener
                (event -> actionPerformed_pressMinuts5());
        minuts10.addActionListener
                (event -> actionPerformed_pressMinuts10());
    }

    /**
     * Mètode per inicialitzar tots els components de la vista.
     */
    private void inicialitzarComponents(){
        inicialitzarFrameVista();
        inicialitzarContinguts();
        inicialitzarRadioBoles();
        inicialitzarRadioRol();
        inicialitzarRadioMode();
        inicialitzarPeu();
        inicialitzarTitol();
        inicialitzarAjuda();
        inicialitzarRepetits();
        inicialitzarStart();
        inicialitzarRadioTemps();
        assignarListeners();
    }

    /**
     * Mètode per inicialitzar el frame de la vista.
     */
    private void inicialitzarFrameVista(){
        frameVista.setMinimumSize(new Dimension(300,450));
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContinguts);
    }

    /**
     * Mètode per inicialitzar el panell que conté els continguts de la vista.
     */
    private void inicialitzarContinguts(){
        panelContinguts.setLayout(new BoxLayout(panelContinguts, BoxLayout.Y_AXIS));
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelTitol);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(separator);


        panelContinguts.add(radioRol);
        panelContinguts.add(radioBoles);
        panelContinguts.add(panelRepetits);
        panelContinguts.add(panelAjuda);
        panelContinguts.add(panelMode);
        panelContinguts.add(panelTemps);
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelStart);
        panelContinguts.add(peu);
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons relacionats amb la selecció del rol del joc.
     */
    private void inicialitzarRadioRol(){
        grupRol.add(codeMaker);
        grupRol.add(codeBreaker);
        codeMaker.setSelected(true);
        radioRol.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioRol.add(new JPanel());
        radioRol.add(new JLabel("Rol:"));
        radioRol.add(new JPanel());
        radioRol.add(codeMaker);
        radioRol.add(codeBreaker);
        radioRol.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons relacionats amb la selecció del nombre de boles.
     */
    private void inicialitzarRadioBoles(){
        grupBoles.add(boles4);
        grupBoles.add(boles6);
        grupBoles.add(boles8);
        boles4.setSelected(true);
        radioBoles.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioBoles.add(new JPanel());
        radioBoles.add(new JLabel("Nombre de boles: "));
        radioBoles.add(boles4);
        radioBoles.add(new JPanel());
        radioBoles.add(boles6);
        radioBoles.add(new JPanel());
        radioBoles.add(boles8);
        radioBoles.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó per tornar al menú inicial.
     */
    private void inicialitzarPeu(){
        peu.setLayout(new FlowLayout(FlowLayout.RIGHT));
        peu.add(menu);
    }

    /**
     * Mètode per inicialitzar el panell que conté el títol.
     */
    private void inicialitzarTitol(){
        JLabel titol = new JLabel("Configuracio");
        titol.setFont(new Font("Arial",Font.PLAIN,24));
        panelTitol.setLayout(new FlowLayout());
        panelTitol.add(titol);
    }

    /**
     * Mètode per inicialitzar el panell que conté la checkbox del mode colors repetits.
     */
    private void inicialitzarRepetits(){
        panelRepetits.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelRepetits.add(new JPanel());
        panelRepetits.add(repetits);
    }

    /**
     * Mètode per inicialitzar el panell que conté la checkbox del mode ajuda.
     */
    private void inicialitzarAjuda(){
        panelAjuda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelAjuda.add(new JPanel());
        panelAjuda.add(ajuda);
        panelAjuda.setVisible(false);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó per començar la partida.
     */
    private void inicialitzarStart(){
        panelStart.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelStart.add(start);
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons relacionats amb la selecció del mode de joc.
     */
    private void inicialitzarRadioMode(){
        grupMode.add(classic);
        grupMode.add(crono);
        classic.setSelected(true);
        panelMode.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelMode.add(new JPanel());
        panelMode.add(new JLabel("Mode de joc:"));
        panelMode.add(new JPanel());
        panelMode.add(classic);
        panelMode.add(new JPanel());
        panelMode.add(crono);
        panelMode.add(new JPanel());
        panelMode.setVisible(false);
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons relacionats amb la selecció del temps pel mode crono.
     */
    private void inicialitzarRadioTemps(){
        grupTemps.add(minuts5);
        grupTemps.add(minuts10);
        minuts5.setSelected(true);
        panelTemps.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTemps.add(new JPanel());
        panelTemps.add(new JLabel("Temps:"));
        panelTemps.add(new JPanel());
        panelTemps.add(minuts5);
        panelTemps.add(new JPanel());
        panelTemps.add(minuts10);
        panelTemps.add(new JPanel());
        panelTemps.setVisible(false);
    }
}
