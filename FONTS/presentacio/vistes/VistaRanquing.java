package presentacio.vistes;

import presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaRanquing {
    /**
     * Atributs de la classe VistaRanquing
     */
    private final CtrlPresentacio ctrlPresentacio;
    private int numBoles = 4;
    private boolean modeRepetits = false;
    private boolean modeAjuda = false;
    private int modeJoc = 0;
    private int temps = 0;

    private final JFrame frameVista = new JFrame("Vista Ranquing");
    private final JPanel panelContinguts = new JPanel();
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
    private final JPanel panelMode = new JPanel();

    private final JRadioButton minuts5 = new JRadioButton("5 minuts");
    private final JRadioButton minuts10 = new JRadioButton("10 minuts");
    private final ButtonGroup grupTemps = new ButtonGroup();
    private final JPanel panelTemps = new JPanel();
    private final JPanel panelTitol = new JPanel();
    private final JPanel panelSettings = new JPanel();

    private final JButton menu = new JButton("Menu");
    private final JPanel peu = new JPanel();

    private final JPanel panelText = new JPanel();
    private final JLabel text = new JLabel();

    /**
     * Mètode per crear la vista rànquing.
     * @param ctrl El controlador de presentació.
     */
    public VistaRanquing(CtrlPresentacio ctrl){
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
        actualitzarText();
    }

    /**
     * Listener per establir el nombre de boles a sis.
     */
    public void actionPerformed_press6boles(){
        numBoles = 6;
        actualitzarText();
    }

    /**
     * Listener per establir el nombre de boles a vuit.
     */
    public void actionPerformed_press8boles(){
        numBoles = 8;
        actualitzarText();
    }

    /**
     * Listener per canviar la quantitat de temps a cinc minuts.
     */
    public void actionPerformed_pressMinuts5(){
        temps = 5;
        actualitzarText();
    }

    /**
     * Listener per canviar la quantitat de temps a deu minuts.
     */
    public void actionPerformed_pressMinuts10(){
        temps = 10;
        actualitzarText();
    }

    /**
     * Listener per establir el mode de colors repetits.
     */
    public void actionPerformed_pressRepetit(){
        modeRepetits = !modeRepetits;
        actualitzarText();
    }

    /**
     * Listener per establir el mode ajuda.
     */
    public void actionPerformed_pressAjuda(){
        modeAjuda = !modeAjuda;
        actualitzarText();
    }

    /**
     * Listener per establir el mode de joc a clàssic.
     */
    public void actionPerformed_pressClassic(){
        modeJoc = 0;
        temps = 0;
        panelTemps.setVisible(false);
        actualitzarText();
    }

    /**
     * Listener per establir el mode de joc a crono.
     */
    public void actionPerformed_pressCrono(){
        modeJoc = 1;
        temps = 5;
        minuts5.setSelected(true);
        panelTemps.setVisible(true);
        actualitzarText();
    }

    /**
     * Listener per canviar a la vista del menú inicial.
     */
    public void actionPerformed_pressMenu(){
        ctrlPresentacio.sincronitzacioRanquingAInici();
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
        inicialitzarRadioMode();
        inicialitzarRadioTemps();
        inicialitzarTitol();
        inicialitzarSettings();
        inicialitzarPeu();
        inicialitzarText();
        assignarListeners();
    }

    /**
     * Mètode per inicialitzar el frame de la vista.
     */
    private void inicialitzarFrameVista(){
        frameVista.setMinimumSize(new Dimension(350,500));
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContinguts);
    }

    /**
     * Mètode per inicialitzar el panell continguts i els seus components.
     */
    private void inicialitzarContinguts(){
        panelContinguts.setLayout(new BoxLayout(panelContinguts, BoxLayout.Y_AXIS));
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelTitol);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(separator);
        panelContinguts.add(radioBoles);
        panelContinguts.add(panelSettings);
        panelContinguts.add(panelMode);
        panelContinguts.add(panelTemps);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(separator2);
        panelContinguts.add(panelText);
        panelContinguts.add(peu);
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons per la selecció del nombre de boles.
     */
    private void inicialitzarRadioBoles(){
        grupBoles.add(boles4);
        grupBoles.add(boles6);
        grupBoles.add(boles8);
        boles4.setSelected(true);
        radioBoles.setLayout(new FlowLayout(FlowLayout.CENTER));
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
     * Mètode per inicialitzar el panell que conté els botons per la selecció del mode de joc.
     */
    private void inicialitzarRadioMode(){
        grupMode.add(classic);
        grupMode.add(crono);
        classic.setSelected(true);
        panelMode.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelMode.add(new JPanel());
        panelMode.add(new JLabel("Mode de joc:"));
        panelMode.add(new JPanel());
        panelMode.add(classic);
        panelMode.add(new JPanel());
        panelMode.add(crono);
        panelMode.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons per la selecció de temps.
     */
    private void inicialitzarRadioTemps(){
        grupTemps.add(minuts5);
        grupTemps.add(minuts10);
        minuts5.setSelected(true);
        panelTemps.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTemps.add(new JPanel());
        panelTemps.add(new JLabel("Temps:"));
        panelTemps.add(new JPanel());
        panelTemps.add(minuts5);
        panelTemps.add(new JPanel());
        panelTemps.add(minuts10);
        panelTemps.add(new JPanel());
        panelTemps.setVisible(false);
    }

    /**
     * Mètode per inicialitzar el panell que conté el títol.
     */
    private void inicialitzarTitol(){
        JLabel titol = new JLabel("Ranquing");
        titol.setFont(new Font("Arial",Font.PLAIN,24));
        panelTitol.setLayout(new FlowLayout());
        panelTitol.add(titol);
    }

    /**
     * Mètode per inicialitzar el panell que conté els botons per la seleccio del mode ajuda i colors repetits.
     */
    private void inicialitzarSettings(){
        panelSettings.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSettings.add(new JPanel());
        panelSettings.add(repetits);
        panelSettings.add(new JPanel());
        panelSettings.add(ajuda);
        panelSettings.add(new JPanel());
    }

    /**
     * Mètode per inicialitzar el panell peu i els seus components.
     */
    private void inicialitzarPeu(){
        peu.setLayout(new FlowLayout(FlowLayout.RIGHT));
        peu.add(menu);
    }

    /**
     * Mètode per inicialitzar el panell que conté el text.
     */
    private void inicialitzarText(){
        panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
        actualitzarText();
        panelText.add(new JPanel());
        panelText.add(text);
        panelText.add(new JPanel());
    }

    /**
     * Mètode per actualitzar el text del rànquing a la configuració establerta.
     */
    private void actualitzarText(){
        ArrayList<String> ranquing = ctrlPresentacio.carregarRanquing(numBoles,modeAjuda,modeRepetits,temps,modeJoc);
        StringBuilder missatge = new StringBuilder("<html><body>");
        for (String s : ranquing) {
            missatge.append(s).append("<br>");
        }
        missatge.append("</body></html>");
        text.setText(missatge.toString());
    }
}
