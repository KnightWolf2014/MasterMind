package domini.model;

import domini.exceptions.NoCorreccioDisponible;
import domini.exceptions.NoSolucioDisponible;
import domini.exceptions.PosicioPatroNoDisponible;

import java.util.*;

public class FiveGuess extends Algoritme {
    /* VARIABLES */

    /**
     * Nombre de boles del patró.
     */
    private final int numBoles;

    /**
     * Número de colors.
     */
    private final int numColors;

    /**
     * Booleà que diu si els colors es poden repetir.
     */
    private final boolean colorRepetit;

    /**
     * Patró que conté la última combinació provada.
     */
    private Patro ultimIntent;

    /**
     * Patró corresponent a la solució.
     */
    private CorreccioLinea correccio;

    /**
     * Llista amb totes les possibles combinacions.
     */
    private final ArrayList<Patro> possiblesCombinacions = new ArrayList<>();

    /**
     * Llista amb les opcions que falten per provar.
     */
    private ArrayList<Patro> intentsPerProvar;


    /* CREADORES */

    /**
     * Creadora de la classe FiveGuess.
     *
     * @param rol és el rol de l'Algoritme.
     */
    public FiveGuess(int rol) {
        super(rol);
        Patro p = new Patro();
        this.numBoles = p.getNumBoles();
        this.colorRepetit = p.getColorsRepetits();
        this.numColors = 8;
    }

    /**
     * Creadora de la classe FiveGuess.
     *
     * @param n és un enter que representa el nombre de boles del patró.
     * @param color és un enter que representa el nombre de colors del patró.
     * @param repetits és un booleà que representa si els colors es poden repetir.
     */
    public FiveGuess(int n, int color, boolean repetits) {
        this.numBoles = n;
        this.numColors = color;
        this.colorRepetit = repetits;
    }

    /**
     * Aquest mètode s'encarrega de guardar la correcció feta per l'usuari.
     *
     * @param solucio és la combinació donada per l'usuari que conté el nombre de pius blancs i negres.
     */
    public void setCorreccio(CorreccioLinea solucio) {
        correccio = new CorreccioLinea(solucio.getNegres(), solucio.getBlanques());
    }

    /**
     * Aquest mètode genera totes les possibles combinacions amb els colors i el nombre de boles donat,
     * amb la possibilitat que es repeteixin els colors.
     *
     * @param posicio és un enter que representa l'índex de la posició que s'està afegint.
     * @param combinacio és una combinació completa que s'afegirà a la llista de possibles combinacions.
     */
    public void trobarCombinacions(int posicio, Patro combinacio) {
        if (posicio == numBoles) possiblesCombinacions.add(combinacio);
        else {
            for (int i = 0; i < numColors; ++i) {
                int[] comb = combinacio.getPatro();
                int[] a = Arrays.copyOf(comb, comb.length);
                Patro novaCombinacio = new Patro(a);
                novaCombinacio.setColor(posicio, i);
                trobarCombinacions(posicio + 1, novaCombinacio);
            }
        }
    }

    /**
     * Aquest mètode genera totes les possibles combinacions amb els colors i el nombre de boles donat,
     * sense que es repeteixi cap color.
     *
     * @param posicio és un enter que representa l'índex de la posició que s'està afegint.
     * @param combinacio és una combinació completa que s'afegirà a la llista de possibles combinacions.
     * @param usat vector de booleans amb les posicions ja utilitzades per evitar repeticions de números.
     */
    public void trobarCombinacionsSenseRepeticions(int posicio, Patro combinacio, boolean[] usat) {
        if (posicio == numBoles) possiblesCombinacions.add(combinacio);
        else {
            for (int i = 0; i < numColors; ++i) {
                if (!usat[i]){
                    int[] comb = combinacio.getPatro();
                    int[] a = Arrays.copyOf(comb, comb.length);
                    Patro novaCombinacio = new Patro(a);
                    novaCombinacio.setColor(posicio, i);
                    usat[i] = true;
                    trobarCombinacionsSenseRepeticions(posicio+1, novaCombinacio, usat);
                    usat[i] = false;
                }
            }
        }
    }

    /**
     * Aquest mètode escull la primera combinació a intentar.
     *
     * @return primera combinació que es provara.
     */
    public Patro primerIntent() {
        Patro primerIntent;
        primerIntent = new Patro();
        if (primerIntent.getColorsRepetits()) {
            int[] p = {0, 0, 1, 1};
            primerIntent.setPatro(p);
        }
        else {
            if (numBoles == 4) {
                int[] p = {0, 1, 2, 3};
                primerIntent.setPatro(p);
            }
            else if (numBoles == 6) {
                int[] p = {0, 1, 2, 3, 4, 5};
                primerIntent.setPatro(p);
            }
        }
        return primerIntent;
    }

    /**
     * Aquest mètode elimina de la llista possiblesCombinacions els patrons que tenen una correcció pitjor.
     *
     * @throws PosicioPatroNoDisponible cas que la posició donada no existeixi.
     */
    public void actualitzaCombinacions() throws PosicioPatroNoDisponible {
        ArrayList<Patro> auxCombinations = new ArrayList<>(possiblesCombinacions);
        for (Patro combinacio : auxCombinations) {
            CorreccioLinea aux = comparaIntents(combinacio, ultimIntent);
            if (!aux.equals(correccio)) possiblesCombinacions.remove(combinacio);
        }
    }

    /**
     * Aquest mètode calcula el màxim nombre d'encerts de combinacions avaluades.
     *
     * @param intent combinació de colors a avaluar.
     * @return el màxim nombre d'encerts.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public int maxim(Patro intent) throws PosicioPatroNoDisponible {
        Map<CorreccioLinea, Integer> puntuacions = new HashMap<>();
        int max = 0;
        for (Patro combinacio : possiblesCombinacions) {
            CorreccioLinea corr = comparaIntents(combinacio, intent);
            if (puntuacions.isEmpty()) puntuacions.put(corr, 1);
            else {
                boolean exists = false;
                for (Map.Entry<CorreccioLinea, Integer> aux : puntuacions.entrySet()) {
                    if (aux.getKey().equals(corr)) {
                        aux.setValue(aux.getValue() + 1);
                        exists = true;
                    }
                    if (aux.getValue() > max) max = aux.getValue();
                }
                if (!exists) puntuacions.put(corr, 1);
            }
        }
        return max;
    }

    /**
     * Aquest mètode decideix el pròxim intent que es provarà.
     *
     * @return el següent patró que es provarà.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public Patro getIntent() throws PosicioPatroNoDisponible {
        //en el cas que sigui el primer intent
        if (possiblesCombinacions.isEmpty()) {
            int [] p = new int[numBoles];
            Patro combinacio = new Patro(p);
            if (colorRepetit) trobarCombinacions(0, combinacio);
            else {
                boolean[] usat = new boolean[8];
                trobarCombinacionsSenseRepeticions(0, combinacio, usat);
            }
            intentsPerProvar = new ArrayList<>(possiblesCombinacions);
            ultimIntent = primerIntent();
        } else {
            actualitzaCombinacions();
            //Comprova quina és la combinació que eliminarà més combinacions possibles.
            //Busca per cada combinació el mínim de patrons eliminats, i escull el màxim d'aquests mínims.
            int cont = 0;
            for (Patro combinacio : intentsPerProvar) {
                int max = maxim(combinacio);
                int minEliminades = intentsPerProvar.size() - max;
                if (cont < minEliminades) {
                    cont = minEliminades;
                    ultimIntent = new Patro(combinacio.getPatro());
                }
                else if ((cont == minEliminades)&& (!possiblesCombinacions.contains(ultimIntent))
                        && possiblesCombinacions.contains(combinacio)) ultimIntent = new Patro(combinacio.getPatro());
            }
        }
        intentsPerProvar.remove(ultimIntent);
        return ultimIntent;
    }

    /**
     * Aquest mètode genera una llista amb tots els intents que s'han provat.
     *
     * @param solucio llista amb les solucions.
     * @return una llista amb tots els patrons que s'han provat.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     * @throws NoSolucioDisponible en cas que encara no s'hagi passat la solució
     * @throws NoCorreccioDisponible en cas de que encara no existeixi cap correcció.
     */
    public List<List<Integer>>  solve (List<Integer> solucio) throws Exception {
        List<List<Integer>> intents = new ArrayList<>();
        Patro pat = new Patro();
        int[] sol = new int[numBoles];
        pat.setPatro(sol);
        for(int i = 0; i < numBoles; i++) sol[i] = solucio.get(i);
        Partida p = new Classic(numBoles, false, pat.getColorsRepetits());
        p.passarSolucio(pat);
        while (!p.partidaAcabada()){
            Patro intent = getIntent();
            p.passarIntent(intent);
            p.checkIntent(intent);
            setCorreccio(p.getCorreccioActual());
            ArrayList<Integer> intentA = new ArrayList<>();
            for(int i = 0; i < numBoles; i++) intentA.add(intent.getColor(i));
            intents.add(intentA);
        }
        return intents;
    }
}
