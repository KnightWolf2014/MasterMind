
package presentacio.controladors;

import presentacio.vistes.*;
import domini.controladors.CtrlDomini;

import java.util.ArrayList;


public class CtrlPresentacio {
    /**
     * Atributs de la classe CtrlPresentacio
     */
    private final CtrlDomini ctrlDomini;
    private final VistaInicial vistaInicial;
    private VistaConfiguracio vistaConfiguracio;
    private VistaTauler vistaTauler;
    private VistaRanquing vistaRanquing;
    private VistaRecords vistaRecords;

    private VistaComJugar vistaComJugar;

    /**
     * Creadora de la classe CtrlPresentacio.
     */
    public CtrlPresentacio(){
        ctrlDomini = new CtrlDomini();
        vistaInicial = new VistaInicial(this);
    }

    /**
     * Mètode que fa visible la vista del menú inicial.
     */
    public void inicialitzarPresentacio(){
        vistaInicial.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del menú inicial a la vista de configuració.
     */
    public void sincronitzacioIniciAConfig(){
        vistaInicial.desactivar();
        vistaInicial.ferInvisible();
        vistaConfiguracio = new VistaConfiguracio(this);
        vistaConfiguracio.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista de configuració a la del menú principal.
     */
    public void sincronitzacioConfigAInici(){
        vistaConfiguracio.desactivar();
        vistaConfiguracio.ferInvisible();
        vistaInicial.activar();
        vistaInicial.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista de configuració a la vista d’un tauler de joc de les mateixes característiques que les especificades com a arguments.
     *
     * @param rol           El rol de la partida: 0 equival a codemaker i 1 a codebreaker.
     * @param numbBoles     El nombre de boles de la partida.
     * @param repetits      Si la partida admet colors repetits.
     * @param ajuda         Si el mode ajuda està activat.
     * @param mode          El mode de joc: 0 equival a clàssic i 1 a crono.
     * @param temps         El temps màxim pel mode crono, és 0 pel mode clàssic.
     */
    public void sincronitzacioConfigATauler(int rol, int numbBoles, boolean repetits, boolean ajuda,int mode, int temps) {
        vistaConfiguracio.desactivar();
        vistaConfiguracio.ferInvisible();
        vistaTauler = new VistaTauler(this,rol,numbBoles,repetits,ajuda,mode,temps);
        vistaTauler.activar();
        vistaTauler.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista de tauler de joc a la vista d’un tauler de joc de les mateixes característiques que les especificades com a arguments.
     *
     * @param rol           El rol de la partida: 0 equival a codemaker i 1 a codebreaker.
     * @param numbBoles     El nombre de boles de la partida.
     * @param repetits      Si la partida admet colors repetits.
     * @param ajuda         Si el mode ajuda està activat.
     * @param mode          El mode de joc: 0 equival a clàssic i 1 a crono.
     * @param temps         El temps màxim pel mode crono, és 0 pel mode clàssic.
     */
    public void sincronitzacioTaulerATauler(int rol, int numbBoles, boolean repetits, boolean ajuda,int mode, int temps){
        vistaTauler.desactivar();
        vistaTauler.ferInvisible();
        vistaTauler = new VistaTauler(this,rol,numbBoles,repetits,ajuda,mode,temps);
        vistaTauler.activar();
        vistaTauler.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista de tauler de joc a la del menú principal
     */
    public void sincronitzacioTaulerAInici(){
        vistaTauler.desactivar();
        vistaTauler.ferInvisible();
        vistaInicial.activar();
        vistaInicial.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del menú principal a la del rànquing.
     */
    public void sincronitzacioIniciARanquing(){
        vistaInicial.desactivar();
        vistaInicial.ferInvisible();
        vistaRanquing = new VistaRanquing(this);
        vistaRanquing.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del rànquing a la del menú principal.
     */
    public void sincronitzacioRanquingAInici(){
        vistaRanquing.desactivar();
        vistaRanquing.ferInvisible();
        vistaInicial.activar();
        vistaInicial.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del menú principal a la dels rècords.
     */
    public void sincronitzacioIniciARecords(){
        vistaInicial.desactivar();
        vistaInicial.ferInvisible();
        vistaRecords = new VistaRecords(this);
        vistaRecords.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista dels rècords a la vista del menú principal.
     */
    public void sincronitzacioRecordsAInici(){
        vistaRecords.desactivar();
        vistaRecords.ferInvisible();
        vistaInicial.activar();
        vistaInicial.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del menú principal a la d’un tauler de joc d’una partida guardada.
     */
    public void sincronitzacioIniciACarregar(){
        vistaInicial.desactivar();
        vistaInicial.ferInvisible();
        vistaTauler = new VistaTauler(this);
        vistaTauler.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista del menú principal a la d’un tauler de joc d’una partida guardada.
     */
    public void sincronitzacioIniciAComJugar(){
        vistaInicial.desactivar();
        vistaInicial.ferInvisible();
        vistaComJugar = new VistaComJugar(this);
        vistaComJugar.ferVisible();
    }

    /**
     * Mètode que fa el canvi de la vista de com jugar a la vista del menú inicial.
     */
    public void sincronitzacioComJugarAInici(){
        vistaComJugar.desactivar();
        vistaComJugar.ferInvisible();
        vistaInicial.activar();
        vistaInicial.ferVisible();
    }

    /**
     * Mètode per establir la solució d’una partida on es juga com a codemaker.
     * @param solucio Vector que correspon a la solució establerta pel jugador.
     */
    public void marcarSolucio(int[] solucio){
        try {
            ctrlDomini.marcarSolucio(solucio);
        }
        catch(Exception e){
            System.out.println("error marcar solucio");
        }
    }

    /**
     * Mètode per demanar que l’algoritme faci el seu torn donats els inputs del jugador.
     */
    public void ferTornMaquina(){
        try {
            ctrlDomini.ferTorn();
        }
        catch(Exception e){
            System.out.println("error torn maquina");
        }
    }

    /**
     * Mètode per enviar una correcció feta per l’usuari.
     * @param correccio Vector que correspon a la solució establerta per l'usuari.
     * @return Retorna cert si la correcció és correcta per l'intent i fals en cas contrari
     */
    public boolean ferCorreccio(int[] correccio){
        try {
            return ctrlDomini.ferCorreccio(correccio);
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Mètode per enviar un intent fet per l’usuari.
     * @param intent Vector que correspon a l'intent establer per l'usuari.
     */
    public void ferIntent(int[] intent){
        try {
            ctrlDomini.ferIntent(intent);
        }
        catch (Exception e){
            System.out.println("error fer intent");
        }
    }

    /**
     * Mètode per consultar els intents ja fets de la partida.
     * @return Matriu on cada fila correspon a un intent de la partida.
     */
    public int[][] getIntents(){
       return ctrlDomini.getVectorIntents();
    }

    /**
     * Mètode per consultar les correccions ja fetes de la partida.
     * @return Matriu on cada fila correspon a una correcció de la partida.
     */
    public int[][] getCorreccions(){
        return ctrlDomini.getVectorCorreccions();
    }

    /**
     * Mètode per a que l’algoritme estableixi la solució en una partida de codebreaker.
     */
    public void establirSolucio(){
        ctrlDomini.establirSolucio();
    }

    /**
     * Mètode per inicialitzar una partida del domini amb els paràmetres indicats.
     * @param rol           El rol de la partida: 0 equival a codemaker i 1 a codebreaker.
     * @param numBoles      El nombre de boles de la partida.
     * @param repetits      Si la partida admet colors repetits.
     * @param ajuda         Si el mode ajuda està activat.
     * @param mode          El mode de joc: 0 equival a clàssic i 1 a crono.
     * @param temps         El temps màxim pel mode crono, és 0 pel mode clàssic.
     */
    public void inicialitzarPartida(int rol,int numBoles,boolean repetits,boolean ajuda,int mode,int temps){
        ctrlDomini.inicialitzarJoc(rol,numBoles,repetits,ajuda,mode,temps);
    }

    /**
     * Mètode per consultar si una partida del domini s’ha acabat.
     * @return Retorna cert si la partida està acabada i fals en cas contrari.
     */
    public boolean partidaAcabada(){
        return ctrlDomini.partidaAcabada();
    }

    /**
     * Mètode per consultar si una partida del domini s’ha guanyat.
     * @return Retorna cert si s'ha guanyat la partida i fals en cas contrari
     */
    public boolean partidaGuanyada(){
        return ctrlDomini.partidaGuanyada();
    }

    /**
     * Mètode per guardar el resultat d’una partida al rànquing.
     * @param modeJoc           El mode de joc: 0 equival a clàssic i 1 a crono.
     * @param numBoles          El nombre de boles de la partida.
     * @param modeAjuda         Si el mode ajuda està activat.
     * @param colorsRepetits    Si la partida admet colors repetits.
     * @param minuts            Els minuts que han passat des del principi de la partida si es juga en clàssic i els minuts que queden si es juga en crono.
     * @param segons            Els segons que han passat des del principi de la partida si es juga en clàssic i els minuts que queden si es juga en crono.
     * @param temps             El temps màxim de la partida si es juga en crono, 0 si es juga en clàssic.
     */
    public void guardarPartida(int modeJoc,int numBoles,boolean modeAjuda,boolean colorsRepetits, int minuts, int segons, int temps){
        int[] punts = ctrlDomini.obtenirPuntuacio();
        String player = "";
        if (modeJoc == 0) {
            ctrlDomini.carregaRankingsClassic(numBoles, modeAjuda, colorsRepetits);
            int[] tempsPartida = new int[]{minuts, segons};
            if (ctrlDomini.novaPosRankingClassic(punts[0], tempsPartida)){
                player = vistaTauler.getNomJugador();
            }
            ctrlDomini.carregarRecords();
            if (ctrlDomini.guardarRankingClassic(player, tempsPartida, punts[0])){
                ctrlDomini.guardarRecords();
            }
        }
        else{
            ctrlDomini.carregaRankingsCrono(numBoles, modeAjuda, colorsRepetits, temps);
            if (ctrlDomini.novaPosRankingCrono(punts[0], punts[1])){
                player = vistaTauler.getNomJugador();
            }
            ctrlDomini.carregarRecords();
            if (ctrlDomini.guardarRankingCrono(player, punts[0], punts[1])){
                ctrlDomini.guardarRecords();
            }
        }
    }

    /**
     * Mètode per consultar el rànquing corresponent als arguments donats.
     * @param numBoles          El nombre de boles de la partida.
     * @param modeAjuda         Si el mode ajuda està activat.
     * @param modeRepetits      Si la partida admet colors repetits.
     * @param temps             El temps màxim de la partida si es juga en crono, 0 si es juga en clàssic.
     * @param modeJoc           El mode de joc: 0 si és clàssic, 1 si és crono.
     * @return Retorna les entrades del rànquing per les partides amb els paràmetres indicats.
     */
    public ArrayList<String> carregarRanquing(int numBoles, boolean modeAjuda, boolean modeRepetits, int temps, int modeJoc){
        ctrlDomini.inicialitzarPersistencia();
        if(modeJoc == 0){
            ctrlDomini.carregaRankingsClassic(numBoles,modeAjuda,modeRepetits);
            return ctrlDomini.getRankingClassic();
        }
        else{
            ctrlDomini.carregaRankingsCrono(numBoles,modeAjuda,modeRepetits,temps);
            return ctrlDomini.getRankingCrono();
        }
    }

    /**
     * Mètode per consultar els rècords.
     * @return Retorna cada entrada dels rècords.
     */
    public ArrayList<String> carregarRecords(){
        ctrlDomini.inicialitzarPersistencia();
        ctrlDomini.carregarRecords();
        return ctrlDomini.getRecords();
    }

    /**
     * Mètode per guardar l’estat d’una partida per poder carregar-la en un futur.
     * @param temps Vector on la primera entrada correspon als minuts de la partida i la segona als segons
     */
    public void guardarEstatPartida(int[] temps){
        try {
            ctrlDomini.guardarPartida(temps);
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }

    /**
     * Mètode per carregar una partida guardada.
     */
    public void carregarEstatPartida(){
        ctrlDomini.carregarPartida();
    }

    /**
     * Mètode per consultar el nombre de boles de la partida del domini.
     * @return Retorna el nombre de boles de la partida.
     */
    public int getNumBoles(){
        return ctrlDomini.getNumBoles();
    }

    /**
     * Mètode per consultar si la partida del domini admet colors repetits.
     * @return Retorna cert si la partida admet colors repetits i fals en cas contrari.
     */
    public boolean getRepetits(){
        return ctrlDomini.getColorsRepetits();
    }

    /**
     * Mètode per consultar si la partida del domini té el mode ajuda activat.
     * @return Retorna cert si el mode ajuda està activat i fals en cas contrari.
     */
    public boolean getAjuda(){
        return ctrlDomini.getModeAjuda();
    }

    /**
     * Mètode per consultar el temps de la partida del domini.
     * @return Retorna un vector on la primera entrada correspon als minuts de la partida i la segona als segons.
     */
    public int[] getTemps(){
        return ctrlDomini.getTemps();
    }

    /**
     * Mètode per consultar si hi ha una partida guardada.
     * @return Retorna cert si existeix una partida guardada i fals en cas contrari.
     */
    public boolean existeixGuardada(){
        return ctrlDomini.partidaGuardada();
    }

    /**
     * Mètode per començar la següent partida quan s’està jugant en mode crono.
     */
    public void nextPartida(){
        ctrlDomini.nextPartida();
    }

    /**
     * Mètode per borrar la partida guardada.
     */
    public void borrarPartidaGuardada(){
        ctrlDomini.resetejarPartida();
    }

    /**
     * Mètode main de la classe.
     * @param args Arguments del main.
     */
    public static void main(String[] args){
        CtrlPresentacio c = new CtrlPresentacio();
        c.inicialitzarPresentacio();
    }
}
