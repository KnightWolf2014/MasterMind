package presentacio.vistes;

import presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaRecords {
    /**
     * Atributs de la classe VistaRecords
     */
    private final CtrlPresentacio ctrlPresentacio;
    private final JFrame frameVista = new JFrame("Vista Records");
    private final JPanel panelContinguts = new JPanel();
    private final JPanel panelText = new JPanel();
    private final JLabel text = new JLabel();
    private final JButton menu = new JButton("Menu");
    private final JPanel peu = new JPanel();
    private final JPanel panelTitol = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(panelText,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    /**
     * Mètode per crear la classe VistaRecords
     * @param ctrl El controlador de presentació.
     */
    public VistaRecords(CtrlPresentacio ctrl){
        ctrlPresentacio = ctrl;
        inicialitzarComponents();
    }

    /**
     * Mètode que fa visible la vista.
     */
    public void ferVisible(){
        frameVista.pack();
        frameVista.setLocationRelativeTo(null);
        frameVista.setVisible(true);
    }

    /**
     * Mètode que fa invisible la vista.
     */
    public void ferInvisible(){
        frameVista.setVisible(false);
    }

    /**
     * Mètode que desactiva la vista.
     */
    public void desactivar(){
        frameVista.setEnabled(false);
    }

    /**
     * Listener que canvia la vista a la del menú inicial.
     * actualitzarText(): Mètode que actualitza el text dels rècords.
     */
    public void actionPerformed_pressMenu(){
        ctrlPresentacio.sincronitzacioRecordsAInici();
    }

    /**
     * Mètode que assigna els listeners als components respectius.
     */
    private void assignarListeners(){
        menu.addActionListener
                (event -> actionPerformed_pressMenu());
    }

    /**
     * Mètode que inicialitza tots els continguts de la vista.
     */
    private void inicialitzarComponents(){
        inicialitzarFrameVista();
        inicialitzarContinguts();
        inicialitzarTitol();
        inicialitzarPeu();
        inicialitzarText();
        assignarListeners();
    }

    /**
     * Mètode que inicialitza el frame de la vista.
     */
    private void inicialitzarFrameVista(){
        frameVista.setMinimumSize(new Dimension(300,450));
        frameVista.setResizable(false);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContinguts);
    }

    /**
     * Mètode que inicialitza el panell que conté tots els continguts de la vista.
     */
    private void inicialitzarContinguts(){
        panelContinguts.setLayout(new BoxLayout(panelContinguts, BoxLayout.Y_AXIS));
        panelContinguts.add(new JPanel());
        panelContinguts.add(panelTitol);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize( new Dimension(Integer.MAX_VALUE,1));
        panelContinguts.add(separator);
        panelContinguts.add(scrollPane);
        panelContinguts.add(peu);
    }

    /**
     * Mètode que inicialitza el panell que conté el títol.
     */
    private void inicialitzarTitol(){
        JLabel titol = new JLabel("Records");
        titol.setFont(new Font("Arial",Font.PLAIN,24));
        panelTitol.setLayout(new FlowLayout());
        panelTitol.add(titol);
    }

    /**
     * Mètode que inicialitza el panell que conté el el botó per tornar al menú inicial.
     */
    private void inicialitzarPeu(){
        peu.setLayout(new FlowLayout(FlowLayout.RIGHT));
        peu.add(menu);
    }

    /**
     * Mètode que inicialitza el panell que conté el text dels rècords.
     */
    private void inicialitzarText(){
        scrollPane.setPreferredSize(new Dimension(425,450));
        panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
        actualitzarText();
        panelText.add(new JPanel());
        panelText.add(text);
        panelText.add(new JPanel());
    }

    /**
     * Mètode que actualitza el text dels rècords.
     */
    private void actualitzarText(){
        ArrayList<String> records = ctrlPresentacio.carregarRecords();
        StringBuilder missatge = new StringBuilder("<html><body>");
        for (String record : records) {
            missatge.append(record).append("<br>");
        }
        missatge.append("</body></html>");
        text.setText(missatge.toString());
    }
}
