package domini.stubs;

import domini.model.CorreccioLinea;
import domini.model.Jugador;
import domini.model.Patro;

public class StubJugador extends Jugador {
    public StubJugador(int rol){
        super(rol);
    }

    public Patro getIntent() {
        return new Patro();
    }

    public Patro getSolucio() {
        return new Patro();
    }

    public void setCorreccio(CorreccioLinea solucio){}
}
