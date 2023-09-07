package domini.tests;

import domini.model.CorreccioLinea;
import domini.model.Patro;
import domini.model.Usuari;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuariTest {
    @Test
    public void testSetIntent(){
        Usuari u = new Usuari(0);
        Patro p1 = new Patro();
        u.setIntent(p1);
        Patro p2 = u.getIntent();
        assertEquals(p1, p2);
    }

    @Test
    public void testSetCorreccio(){
        Usuari u = new Usuari(0);
        CorreccioLinea c1 = new CorreccioLinea();
        u.setCorreccio(c1);
        CorreccioLinea c2 = u.corregirMaquina();
        assertEquals(c1, c2);
    }

    @Test
    public void testSetSolucio(){
        Usuari u = new Usuari(0);
        Patro p1 = new Patro();
        u.setIntent(p1);
        Patro p2 = u.getIntent();
        assertEquals(p1, p2);
    }
}
