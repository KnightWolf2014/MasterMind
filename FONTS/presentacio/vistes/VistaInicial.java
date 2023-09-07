package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import presentacio.controladors.CtrlPresentacio;

public class VistaInicial {
    /**
     * Atributs de la classe VistaInicial
     */
    private final CtrlPresentacio ctrlPresentacio;
    private final JFrame frameVista = new JFrame("Vista Inicial");
    private final JPanel panelContinguts = new JPanel();
    private final JButton novaPartida = new JButton("Nova partida");
    private final JButton carregarPartida = new JButton("Carregar partida");
    private final JButton ranquing = new JButton("Ranquing");
    private final JButton record = new JButton("Records");
    private final JButton comJugar = new JButton("Com jugar");
    private final JButton sortir = new JButton("Sortir");
    private final JPanel panelTitol = new JPanel();
    private final JPanel panelNovaPartida = new JPanel();
    private final JPanel panelCarregarPartida = new JPanel();
    private final JPanel panelRanquing = new JPanel();
    private final JPanel panelRecords = new JPanel();
    private final JPanel panelComJugar = new JPanel();
    private final JPanel panelSortir = new JPanel();

    /**
     * Creadora de la classe VistaInicial.
     * @param ctrl El controlador de presentació.
     */
    public  VistaInicial(CtrlPresentacio ctrl){
        ctrlPresentacio = ctrl;
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
     * Listener per canviar a la vista de configuració.
     */
    public void actionPerformed_PressNovaPartida(){
        ctrlPresentacio.sincronitzacioIniciAConfig();
    }

    /**
     * Listener per canviar a la vista de rànquing.
     */
    public void actionPerformed_PressRanquing(){ ctrlPresentacio.sincronitzacioIniciARanquing();}

    /**
     * Listener per tancar l’aplicació.
     */
    public void actionPerformed_PressSortir(){System.exit(0);}

    /**
     * Listener per canviar a la vista de rècords.
     */
    public void actionPerformed_PressRecords(){ctrlPresentacio.sincronitzacioIniciARecords();}

    /**
     * Listener per carregar una partida i nar a la vista de tauler de joc.
     */
    public void actionPerformed_PressCarregar(){
        if(ctrlPresentacio.existeixGuardada()) {
            ctrlPresentacio.carregarEstatPartida();
            ctrlPresentacio.sincronitzacioIniciACarregar();
        }
        else{
            VistaDialeg vistaDialeg = new VistaDialeg();
            String[] strBotons = {"OK"};
            vistaDialeg.setDialeg("No es pot carregar", "No hi ha cap partida guardada", strBotons, 4, true);
        }
    }

    /**
     * Listener per canviar a la vista de com jugar.
     */
    public void actionPerformed_PressComJugar(){ctrlPresentacio.sincronitzacioIniciAComJugar();}

    /**
     * Mètode per assignar els listeners als botons respectius.
     */
    public void assignarListeners(){
        novaPartida.addActionListener
                (event -> actionPerformed_PressNovaPartida());
        ranquing.addActionListener
                (event -> actionPerformed_PressRanquing());
        sortir.addActionListener
                (event -> actionPerformed_PressSortir());
        record.addActionListener
                (event -> actionPerformed_PressRecords());
        carregarPartida.addActionListener
                (event -> actionPerformed_PressCarregar());
        comJugar.addActionListener
                (event -> actionPerformed_PressComJugar());
    }

    /**
     * Mètode per inicialitzar tots els components de la vista.
     */
    private void inicialitzarComponents(){
        inicialitzarFrameVista();
        inicialitzarContinguts();
        inicialitzarTitol();
        inicialitzarNovaPartida();
        inicialitzarCarregarPartida();
        inicialitzarRanquing();
        inicialitzarRecord();
        inicialitzarComJugar();
        inicialitzarSortir();
        assignarListeners();
    }

    /**
     * Mètode per inicialitzar el panell continguts.
     */
    private void inicialitzarContinguts(){
        panelContinguts.setLayout(new BoxLayout(panelContinguts, BoxLayout.Y_AXIS));
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelTitol);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(new JPanel());
        panelContinguts.add(separator);
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelNovaPartida);
        panelContinguts.add(panelCarregarPartida);
        panelContinguts.add(panelRanquing);
        panelContinguts.add(panelRecords);
        panelContinguts.add(panelComJugar);
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelSortir);
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
     * Mètode per inicialitzar el panell del títol.
     */
    private void inicialitzarTitol(){
        panelTitol.setLayout(new BoxLayout(panelTitol, BoxLayout.X_AXIS));
        JLabel titol = new JLabel("MASTERMIND");
        titol.setFont(new Font("Arial",Font.PLAIN,24));
        panelTitol.add(titol);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de nova partida.
     */
    private void inicialitzarNovaPartida(){
        novaPartida.setPreferredSize(new Dimension(200, 30));
        panelNovaPartida.setLayout(new FlowLayout());
        panelNovaPartida.add(novaPartida);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de carregar partida.
     */
    private void inicialitzarCarregarPartida(){
        carregarPartida.setPreferredSize(new Dimension(200,30));
        panelCarregarPartida.setLayout(new FlowLayout());
        panelCarregarPartida.add(carregarPartida);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de rànquing.
     */
    private void inicialitzarRanquing(){
        ranquing.setPreferredSize(new Dimension(200,30));
        panelRanquing.setLayout(new FlowLayout());
        panelRanquing.add(ranquing);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de rècords.
     */
    private void  inicialitzarRecord(){
        record.setPreferredSize(new Dimension(200,30));
        panelRecords.setLayout(new FlowLayout());
        panelRecords.add(record);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de com jugar.
     */
    private void inicialitzarComJugar(){
        comJugar.setPreferredSize(new Dimension(200,30));
        panelComJugar.setLayout(new FlowLayout());
        panelComJugar.add(comJugar);
    }

    /**
     * Mètode per inicialitzar el panell que conté el botó de sortir.
     */
    private void inicialitzarSortir(){
        sortir.setPreferredSize(new Dimension(200,30));
        panelSortir.setLayout(new FlowLayout());
        panelSortir.add(sortir);
    }
}
