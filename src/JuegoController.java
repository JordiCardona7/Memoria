import java.util.List;
import java.util.Random;

public class JuegoController {
    private JuegoMemoria juego;

    public JuegoController() {
        this.juego = null;
    }

    public JuegoMemoria getJuego() {
        return juego;
    }

    public void nuevaPartida(int filas, int cols, List<String> simbolos, Random rnd, String nombre1, String nombre2) throws Exception {
         Tablero tablero = new Tablero(filas, cols, simbolos, rnd);

         Jugador j1 = new Jugador(nombre1);
        Jugador j2 = new Jugador(nombre2);

        this.juego = new JuegoMemoria(tablero, j1, j2);
    }
    public void seleccionarPrimera(int r, int c) throws Exception {
        juego.seleccionarPrimera(r, c);
    }

    public boolean seleccionarSegundaYResolver(int r, int c) throws Exception {
        return juego.seleccionarSegundaYResolver(r, c);
    }

    public void ocultarSiNoPareja() {
        juego.ocultarSiNoPareja();
    }

    public void alternarTurnoSiNoPareja(boolean pareja) {
        juego.alternarTurnoSiNoPareja(pareja);
    }

    public void limpiarSeleccion() {
        juego.limpiarSeleccion();
    }

}
