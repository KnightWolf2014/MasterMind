package presentacio.vistes;

import presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class VistaComJugar {
    /**
     * Atributs de la classe VistaComJugar
     */
    private final CtrlPresentacio ctrlPresentacio;
    private final JFrame frameVista = new JFrame("Vista Com Jugar");
    private final JPanel panelContinguts = new JPanel();
    private final JPanel panelText = new JPanel();
    private final JLabel text = new JLabel();
    private final JButton menu = new JButton("Menu");
    private final JPanel peu = new JPanel();
    private final JPanel panelTitol = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(panelText,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    /**
     * Creadora de la classe VistaComJugar.
     * @param ctrl El controlador de presentació.
     */
    public VistaComJugar(CtrlPresentacio ctrl){
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
     */
    public void actionPerformed_pressMenu(){
        ctrlPresentacio.sincronitzacioComJugarAInici();
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
        JLabel titol = new JLabel("Com Jugar");
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
     * Mètode que inicialitza el panell que conté el text de les instruccions.
     */
    private void inicialitzarText(){
        scrollPane.setPreferredSize(new Dimension(450,450));
        panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
        actualitzarText();
        panelText.add(new JPanel());
        panelText.add(text);
        panelText.add(new JPanel());
    }

    /**
     * Mètode que actualitza el text de les instruccions.
     */
    private void actualitzarText(){
        String missatge = "<html><body>";
        missatge = missatge + "<h1 style=\"font-size:130%;\"> Codebreaker:</h1>" +
                "<h1 style=\"font-size:120%;\"> Classic:</h1>" +
                "-- L'ordinador tria una cadena de colors. El nombre de colors es<br>" +
                "   la longitud del codi. Per defecte la longitud es 4, pero pot ser<br>" +
                "   canviada quan es comenci un joc nou.<br><br>" +
                "-- L'objectiu del joc es endivinar las posicions exactes dels colors<br>" +
                "   de la cadena de l'ordinador.<br><br>" +
                "-- Per defecte, un color pot ser usat nomes una vegada per cadena.<br>" +
                "   Al iniciar un joc es pot seleccionar l'opcio de colors repetits,<br>" +
                "   en aquest cas, qualsevol color pot ser utilitzat las vegades que<br>" +
                "   es vulgui.<br><br>" +
                "-- Despres d'omplir una linia amb els colors que vegis convenients i<br>" +
                "   clicar en el boto de correccio, l'ordinador respondra amb el<br>" +
                "   resultat de la cadena.<br><br>" +
                "-- Per cada color de la teva cadena que sigui correcte i estigui<br>" +
                "   en la posicio correcta, l'ordinador mostrara un color negre al<br>" +
                "   costat de la cadena.<br><br>" +
                "-- Per cada color de la teva cadena que sigui correcte i estigui<br>" +
                "   en una posicio incorrecta, l'ordinador mostrara un color blanc al<br>" +
                "   costat de la cadena.<br><br>" +
                "-- Guanyaras el joc si aconsegueixes endivinar la posicio de tots els<br>" +
                "   colors de la cadena.<br><br>" +
                "-- Perdras el joc si no aconsegueixes endivinarlos.<br><br>" +
                "<h1 style=\"font-size:120%;\"> Crono:</h1>" +
                "-- Es tracta d'un mode de joc on durant 5 o 10 minuts pots jugar tantes<br>" +
                "   partides classiques com puguis. L'objectiu es aconseguir el maxim<br>" +
                "   nombre de victories possible.<br><br>" +
                "<h1 style=\"font-size:130%;\"> Codemaker:</h1><br>" +
                "-- Tries una cadena de colors. El nombre de colors es la longitud<br>" +
                "   del codi. Por defecte la longitud es 4, pero pot ser canviada quan<br>" +
                "   es comenci un joc nou.<br><br>" +
                "-- L'objectiu del joc es intentar aconseguir que l'ordinador no<br>" +
                "   endivini els colors de la cadena posada per tu.<br><br>" +
                "-- Per defecte, un color pot ser usat nomes una vegada per cadena.<br>" +
                "   Al iniciar un joc es pot seleccionar l'opcio de colors repetits,<br>" +
                "   en aquest cas, qualsevol color pot ser utilitzat las vegades que<br>" +
                "   es vulgui.<br><br>" +
                "-- L'ordinador omplira una linia amb els colors que vegi convenients<br>" +
                "   i despres tindras que corregir-li la cadena.<br><br>" +
                "-- Per cada color de la seva cadena que sigui correcte i que<br>" +
                "   estigui en la posicio correcta, tindras que marcar un color negre<br>" +
                "   al costat de la cadena.<br><br>" +
                "-- Per cada color de la seva cadena que sigui correcte i que<br>" +
                "   estigui en una posicio incorrecte, tindras que marcar un color<br>" +
                "   negre al costat de la cadena.<br><br>" +
                "-- Guanyaras el joc si aconsegueixes que l'ordenador no endivini la<br>" +
                "   posicio de tots els colors de la cadena.<br><br>" +
                "-- Perdras el joc si l'ordinador els endivina.<br>";
        missatge = missatge + "</body></html>";
        text.setText(missatge);
    }
}
