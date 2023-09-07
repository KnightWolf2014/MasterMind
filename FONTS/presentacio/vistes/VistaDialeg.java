package presentacio.vistes;

import javax.swing.*;


public class VistaDialeg {

    public VistaDialeg() {
    }

    /**
     * Mètode per crear un diàleg d’opcions amb el títol, text, botons, tipus i si es pot tancar com està indicat als arguments.
     * @param strTitulo El títol de la vista.
     * @param strTexto El text de la vista.
     * @param strBotones Els diferents botons d'opcions.
     * @param iTipo El tipus de diàleg.
     * @param tancable Indica si la finestra es podrà tancar amb la creu o no.
     * @return Retorna un número corresponent a la opció seleccionada
     */
    public int setDialeg
            (String strTitulo, String strTexto, String[] strBotones, int iTipo, boolean tancable) {

        int oTipo = 1;
        switch (iTipo) {
            case 0: oTipo = JOptionPane.ERROR_MESSAGE; break;
            case 1: oTipo = JOptionPane.INFORMATION_MESSAGE; break;
            case 2: oTipo = JOptionPane.WARNING_MESSAGE; break;
            case 3: oTipo = JOptionPane.QUESTION_MESSAGE; break;
            case 4: oTipo = JOptionPane.PLAIN_MESSAGE; break;
        }

        JOptionPane optionPane = new JOptionPane(strTexto,oTipo);
        optionPane.setOptions(strBotones);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(),strTitulo);
        if(tancable) dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        else dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);

        String vsel = (String) optionPane.getValue();
        int isel;
        for (isel = 0;
             isel < strBotones.length && !strBotones[isel].equals(vsel); isel++);
        return isel;
    }

}

