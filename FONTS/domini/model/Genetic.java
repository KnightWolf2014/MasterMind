package domini.model;

import domini.exceptions.*;

import java.util.*;

public class Genetic extends Algoritme{
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
    private final boolean colorsRepetits;

    /**
     * Array amb les correccions negres.
     */
    private final ArrayList<Integer> correccioNegres = new ArrayList<>();

    /**
     * Array amb les correccions blanques.
     */
    private final ArrayList<Integer> correccioBlanques = new ArrayList<>();

    /**
     * Mida població.
     */
    private static final int TAMANY_POBLACIO = 10000;

    /**
     * Array amb els patrons corresponents als intents provats.
     */
    private final ArrayList<Patro> intentsProvats = new ArrayList<>();

    /**
     * Array amb l'aptitud de cada població.
     */
    private final ArrayList<Integer> aptitud = new ArrayList<>(TAMANY_POBLACIO);

    /**
     * Llista amb totes les possibles combinacions.
     */
    private final ArrayList<Patro> possiblesCombinacions = new ArrayList<>();

    /**
     * Llista amb totes les possibles poblacions.
     */
    private  ArrayList<Patro> poblacio = new ArrayList<>(TAMANY_POBLACIO);

    /**
     * Integer que serveix per escollir que 2 patrons s'utilitzaran per a les funcions crossover1 i crossover2
     */
    private Integer posicioPare = 0;

    /* CREADORES */

    /**
     * Creadora de la classe Genetic.
     *
     * @param rol és el rol de l'Algoritme
     */
    public Genetic(int rol) {
        super(rol);
        Patro p = new Patro();
        this.numBoles = p.getNumBoles();
        this.numColors = 8;
        this.colorsRepetits = p.getColorsRepetits();
    }

    /**
     * Creadora de la classe Genetic.
     *
     * @param n és un enter que representa el nombre de boles del patró.
     * @param color és un enter que representa el nombre de colors del patró.
     * @param repetits és un booleà que representa si els colors es poden repetir.
     */
    public Genetic(int n, int color, boolean repetits) {
        this.numBoles = n;
        this.numColors = color;
        this.colorsRepetits = repetits;
    }


    /**
     * Aquest mètode guarda la correcció feta per l'usuari en dues llistes de boles negres i blanques.
     *
     * @param solucio és la combinació donada per l'usuari que conté el nombre de pius blancs i negres.
     */
    public void setCorreccio(CorreccioLinea solucio) {
        correccioNegres.add(solucio.getNegres());
        correccioBlanques.add(solucio.getBlanques());
    }

    /**
     * Aquest mètode escull la primera combinació a intentar.
     *
     * @return primera combinació que es provara
     */
    public Patro primerIntent() {
        Patro primerIntent;
        primerIntent = new Patro();
        if (numBoles == 6) {
            int[] p = {0, 0, 1, 1, 2, 3};
            primerIntent.setPatro(p);
        } else if (numBoles == 8 && colorsRepetits) {
            int[] p = {0, 0, 1, 1, 2, 2, 3, 4};
            primerIntent.setPatro(p);
        }
        else {
            int[] p = {0, 1, 2, 3, 4, 5, 6, 7};
            primerIntent.setPatro(p);
        }
        return primerIntent;
    }

    /**
     * Aquest mètode crea una població aleatòria i s'encarrega de buidar les llistes.
     */
    public void iniciarPoblacio() {
        possiblesCombinacions.clear();
        poblacio.clear();
        aptitud.clear();
        for (int i = 0; i < TAMANY_POBLACIO; ++i) {
            int[] r = new int[numBoles];
            Random rand = new Random();
            for (int j = 0; j < numBoles; ++j) r[j] = rand.nextInt(8);
            Patro intent = new Patro(r);
            poblacio.add(i, intent);
            aptitud.add(i, 0);
        }
    }

    /**
     * Aquest mètode calcula l'aptitud. Compara tots els patrons de la població amb els intents provats.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void calcularAptitud() throws PosicioPatroNoDisponible {
        for (int i = 0; i < TAMANY_POBLACIO; ++i) {
            int negres = 0;
            int blanques = 0;
            for (int j = 0; j < intentsProvats.size(); ++j) {
                CorreccioLinea comp = comparaIntents(poblacio.get(i), intentsProvats.get(j));
                negres += Math.abs(comp.getNegres() - correccioNegres.get(j));
                blanques += Math.abs(comp.getBlanques() - correccioBlanques.get(j));
            }
            aptitud.set(i, negres + blanques);
        }
    }

    /**
     * Aquest mètode ordena segons l'aptitud.
     *
     * @param apt llista amb totes les aptituds.
     * @param intents llista amb tots els intents.
     */
    public void ordenaSegonsAptitud(ArrayList<Integer> apt, ArrayList<Patro> intents) {
        ordena(apt, intents, 0, aptitud.size() - 1);
    }

    /**
     * Aquest mètode ordena segons l'aptitud, divideix per la meitat i va ordenant.
     *
     * @param apt llista amb totes les aptituds.
     * @param intents llista amb tots els intents.
     * @param petit enter que representa la posició més petita.
     * @param gran enter que representa la posició més gran.
     */
    public void ordena(ArrayList<Integer> apt, ArrayList<Patro> intents, int petit, int gran) {
        int pn = (petit+gran)/2;
        if (petit <= gran) {
            int pivot = divide(apt, intents, petit, gran, pn);
            ordena(apt, intents, petit, pivot - 1);
            ordena(apt, intents, pivot + 1, gran);
        }
    }

    /**
     * Aquest mètode decideix la posició en la qual es tallarà el patró i mou tant les aptituds com els patrons.
     *
     * @param apt llista amb totes les aptituds.
     * @param intents llista amb tots els intents.
     * @param petit enter que representa la posició més petita.
     * @param gran enter que representa la posició més gran.
     * @param pivot enter que representa la posició que de referencia per ordenar la llista.
     * @return un enter que representa la divisió la posició en la qual hem decidit tallar el vector.
     */
    public int divide(ArrayList<Integer> apt, ArrayList<Patro> intents, int petit, int gran, int pivot) {
        int pn = apt.get(pivot);
        int i = petit;
        for(int j = petit; j < gran; ++j) {
            if (apt.get(j) <= pn) {
                swapInteger(apt, i, j);
                swapPatro(intents, i++, j);
            }
        }
        swapInteger(apt, gran, i);
        swapPatro(intents, gran, i);
        return i;
    }

    /**
     * Aquest mètode intercanvia integers de la llista d'aptituds passada per paràmetre.
     *
     * @param apt llista amb totes les aptituds.
     * @param pos1 enter que representa la posició d'una aptitud.
     * @param pos2 enter que representa la posició d'una aptitud.
     */
    public void swapInteger(ArrayList<Integer> apt, int pos1, int pos2) {
        int tmp = apt.get(pos1);
        apt.set(pos1, apt.get(pos2));
        apt.set(pos2, tmp);
    }

    /**
     * Aquest mètode intercanvia de posicions patrons de la llista passada per paràmetre.
     *
     * @param patrons llista amb tots els intents.
     * @param pos1 enter que representa la posició d'un patró.
     * @param pos2 enter que representa la posició d'un patró
     */
    public void swapPatro(ArrayList<Patro> patrons, int pos1, int pos2) {
        Patro tmp = patrons.get(pos1);
        patrons.set(pos1, patrons.get(pos2));
        patrons.set(pos2, tmp);
    }

    /**
     * Aquest mètode evoluciona la població. Modifica, aleatòriament, la població de diferents formes.
     *
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void evolucionaPoblacio() throws PosicioPatroNoDisponible {
        ArrayList<Patro> novaPoblacio = new ArrayList<>(TAMANY_POBLACIO);

        if (numBoles == 6) {
            for (int i = 0; i < TAMANY_POBLACIO; ++i) {
                int[] k = {8, 8, 8, 8, 8, 8};
                novaPoblacio.add(i, new Patro(k));
            }
        } else {
            for (int i = 0; i < TAMANY_POBLACIO; ++i) {
                int[] k = {8, 8, 8, 8, 8, 8, 8, 8};
                novaPoblacio.add(i, new Patro(k));
            }
        }

        //evoluciona la població, de manera random
        for (int i = 0; i < TAMANY_POBLACIO; i += 2) {
            if ((int) (Math.random()*2) == 0 ) crossover1(novaPoblacio, i , i+1);
            else crossover2(novaPoblacio, i, i+1);
        }

        for (int i = 0; i < TAMANY_POBLACIO; i++) {
            if ((int) (Math.random() * 100) < 3) mutar(novaPoblacio, i);
            else if ((int) (Math.random() * 100) < 3) permutar(novaPoblacio, i);
            else if ((int) (Math.random() * 100) < 2) invertir(novaPoblacio, i);
        }
        repeticionsRandom(novaPoblacio);
        poblacio = novaPoblacio;
    }

    /**
     * Aquest mètode agafa dos patrons aleatoris d'una llista i fa un crossover de les dues parts de patrons.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param fill1 enter que representa
     * @param fill2 enter que representa
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void crossover1(ArrayList<Patro> novaPoblacio, int fill1, int fill2) throws PosicioPatroNoDisponible {
        int mare = getPosicioPares();
        int pare = getPosicioPares();
        //genera una posició random per a dividir la població
        int separacio = ((int) (Math.random() * numBoles)) + 1;

        for (int j = 0; j < numBoles; j++) {
            Patro p = novaPoblacio.get(fill1);
            Patro p2 = novaPoblacio.get(fill2);
            if (j <= separacio) {
                p.setColor(j, poblacio.get(mare).getColor(j));
                novaPoblacio.set(fill1, p);

                p2.setColor(j, poblacio.get(pare).getColor(j));
                novaPoblacio.set(fill2, p2);
            } else {
                p.setColor(j, poblacio.get(pare).getColor(j));
                novaPoblacio.set(fill1, p);

                p2.setColor(j, poblacio.get(mare).getColor(j));
                novaPoblacio.set(fill2, p2);
            }
        }
    }

    /**
     * Aquest mètode agafa dos patrons aleatoris d'una llista i fa un crossover de les tres parts dels patrons.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param fill1 enter que representa
     * @param fill2 enter que representa
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void crossover2(ArrayList<Patro> novaPoblacio, int fill1, int fill2) throws PosicioPatroNoDisponible {
        int mare = getPosicioPares();
        int pare = getPosicioPares();
        int separacio1 = ((int) (Math.random() * numBoles)) + 1;
        int separacio2 = ((int) (Math.random() * numBoles)) + 1;

        if (separacio1 > separacio2) {
            int temp = separacio1;
            separacio1 = separacio2;
            separacio2 = temp;
        }

        for (int j = 0; j < numBoles; j++) {
            Patro p = novaPoblacio.get(fill1);
            Patro p2 = novaPoblacio.get(fill2);
            if (j <= separacio1 || j > separacio2) {
                p.setColor(j, poblacio.get(mare).getColor(j));
                novaPoblacio.set(fill1, p);

                p2.setColor(j, poblacio.get(pare).getColor(j));
                novaPoblacio.set(fill2, p2);
            } else {
                p.setColor(j, poblacio.get(pare).getColor(j));
                novaPoblacio.set(fill1, p);

                p2.setColor(j, poblacio.get(mare).getColor(j));
                novaPoblacio.set(fill2, p2);
            }
        }
    }

    /**
     * Aquest mètode modifica el color d'una posició per un color aleatori.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param posicio enter que indica el patró de la població que es modificara.
     */
    public void mutar(ArrayList<Patro> novaPoblacio, int posicio) {
        int pos = (int) (Math.random() * numBoles);
        Patro p = novaPoblacio.get(posicio);
        p.setColor(pos, (int) (Math.random() * numColors));
        novaPoblacio.set(posicio, p);
    }

    /**
     * Aquest mètode permuta els colors d'un patró entre dues posicions random.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param posicio enter que indica el patró de la població que es modificara.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void permutar(ArrayList<Patro> novaPoblacio, int posicio) throws PosicioPatroNoDisponible {
        int pos1 = (int) (Math.random() * numBoles);
        int pos2 = (int) (Math.random() * numBoles);
        while(pos1 >= pos2) {
            if (pos1 > pos2) pos1 = (int) (Math.random() * numBoles);
            pos2 = (int) (Math.random() * numBoles);
        }

        Patro aux = novaPoblacio.get(posicio);
        for (int i = pos1; i < pos2; ++i) {
            int random1 = (int) (Math.floor(Math.random()*(pos2-pos1+1)+pos1));
            int random2 = (int) (Math.floor(Math.random()*(pos2-pos1+1)+pos1));
            int color = aux.getColor(random1);
            aux.setColor(random1, aux.getColor(random2));
            aux.setColor(random2, color);
        }
    }

    /**
     * Aquest mètode inverteix els colors entre dues posicions aleatòries.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param posicio enter que indica el patró de la població que es modificara.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public void invertir(ArrayList<Patro> novaPoblacio, int posicio) throws PosicioPatroNoDisponible {
        int pos1 = (int) (Math.random() * numBoles);
        int pos2 = (int) (Math.random() * numBoles);

        if (pos2 < pos1) {
            int tmp = pos2;
            pos2 = pos1;
            pos1 = tmp;
        }

        for (int i = 0; i < (pos2 - pos1) / 2; i++) {
            int tmp = novaPoblacio.get(posicio).getColor(pos1+ i);
            Patro p = novaPoblacio.get(posicio);
            p.setColor(pos1+ i, p.getColor(pos2 - i));
            p.setColor(pos2-i, tmp);
        }
    }

    /**
     * Aquest mètode genera posicions random per escollir patrons de la població.
     *
     * @return un enter random que s'utilitzarà com a posició.
     */
    public int getPosicioPares() {
        posicioPare += (int) (Math.random() * 10);
        if (posicioPare < TAMANY_POBLACIO / 5) return posicioPare;
        else posicioPare = 0;
        return posicioPare;
    }

    /**
     * Aquest mètode modifica els patrons que es repeteixen entre la nova població i la guardada a la variable població.
     * Si es repeteixen genera una nova combinació de forma random.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     */
    public void repeticionsRandom(ArrayList<Patro> novaPoblacio) {
        for (int i = 0; i < TAMANY_POBLACIO; i++) {
            if (esRepeticio(novaPoblacio, i)) {
                int[] r = new int[numBoles];
                Random rand = new Random();
                for (int j = 0; j < numBoles; ++j) r[j] = rand.nextInt(8);
                Patro p = new Patro(r);
                novaPoblacio.set(i, p);
            }
        }
    }

    /**
     * Aquest mètode compara un patró de la població passada amb la població guardada a la variable poblacio.
     *
     * @param novaPoblacio arraylist amb les combinacions possibles.
     * @param posicio enter que indica el patró de la població que es modificara.
     * @return boolea que indica si es una repetició o no.
     */
    public boolean esRepeticio(ArrayList<Patro> novaPoblacio, int posicio) {
        for (int i = 0; i < TAMANY_POBLACIO; i++) {
            if (poblacio.get(i).equals(novaPoblacio.get(posicio))) return true;
        }
        return false;
    }

    /**
     * Aquest mètode compara els intents provats i els patrons de la població per saber si s'han d'afegir a les possibles solucions.
     *
     * @return boolea per saber si es podrà afegir a la llista de possibles combinacions.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public boolean afegirAPossibles() throws PosicioPatroNoDisponible {
        LabelContinua:
        for (int i = 0; i < TAMANY_POBLACIO; i++) {
            for (int j = 0; j < correccioNegres.size() && j < correccioBlanques.size(); j++) {
                CorreccioLinea comp = comparaIntents(poblacio.get(i), intentsProvats.get(j));
                if (comp.getNegres() != correccioNegres.get(j) || comp.getBlanques() != correccioBlanques.get(j)) {
                    continue LabelContinua;
                }
            }
            if (possiblesCombinacions.size() < 1) possiblesCombinacions.add(poblacio.get(i));
            else return false;
        }
        return true;
    }

    /**
     * Aquest mètode decideix el pròxim intent que es provarà.
     *
     * @return el següent patró que es provarà.
     * @throws PosicioPatroNoDisponible en cas que la posició donada no existeixi.
     */
    public Patro getIntent() throws PosicioPatroNoDisponible {
        if (correccioNegres.size() == 0 && correccioBlanques.size() == 0) {
            Patro intent = primerIntent();
            intentsProvats.add(intent);
            return intent;
        }
        int generacio;
        boolean afegir = true;
        iniciarPoblacio();
        calcularAptitud();
        ordenaSegonsAptitud(aptitud, poblacio);
        while(possiblesCombinacions.isEmpty()) {
            generacio = 0;
            while (afegir && generacio <= 5000) {
                evolucionaPoblacio();
                calcularAptitud();
                ordenaSegonsAptitud(aptitud, poblacio);
                afegir = afegirAPossibles();
                ++generacio;
            }
        }
        Patro intent = possiblesCombinacions.get((int) (Math.random() * possiblesCombinacions.size()));
        intentsProvats.add(intent);
        return intent;
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
