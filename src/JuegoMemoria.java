public class JuegoMemoria {
    private Tablero tablero;
    private Jugador[] jugadores;
    private int turnoActual;
    private boolean primeraSeleccionHecha;
    private int r1;
    private int c1;
    private int r2;
    private int c2;

    public JuegoMemoria(Tablero tablero, Jugador j1, Jugador j2) {
        this.tablero = tablero;
        this.jugadores = new Jugador[]{j1, j2};
        this.turnoActual = 0;
        this.primeraSeleccionHecha = false;
        this.r1 = this.c1 = this.r2 = this.c2 = -1;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getJugadorActual() {
        return jugadores[turnoActual];
    }

    public Jugador getJugador(int i) {
        return jugadores[i];
    }

    public boolean terminado() {
        return tablero.completo();
    }

    public void seleccionarPrimera(int r, int c) throws Exception {
        if (primeraSeleccionHecha) {
            throw new Exception("Ya se realizo la prmera seleccion");
        }
        tablero.revelar(r, c);
        r1 = r;
        c1 = c;
        primeraSeleccionHecha = true;
    }

    public boolean seleccionarSegundaYResolver(int r, int c) throws Exception {
        if (!primeraSeleccionHecha) {
            throw new Exception("Primero debes seleccionar la primera celda.");
        }
        if (r == r1 && c == c1) {
            throw new Exception("No puedes elegir la misma celda dos veces.");
        }
        tablero.revelar(r, c);
        r2 = r;
        c2 = c;
        boolean pareja = tablero.sonPareja(r1, c1, r2, c2);

        if (pareja) {
            tablero.marcarPareja(r1, c1, r2, c2);
            getJugadorActual().anotarPar();
        }
        return pareja;
    }

    public void ocultarSiNoPareja() {
        if (!tablero.sonPareja(r1, c1, r2, c2)) {
            tablero.ocultar(r1, c1);
            tablero.ocultar(r2, c2);
        }
    }
    public void alternarTurnoSiNoPareja(boolean pareja){
        if (!pareja){
            turnoActual = (turnoActual == 0) ? 1 : 0;
        }

    }

    public void limpiarSeleccion() {
        primeraSeleccionHecha = false;
        r1 = c1 = r2 = c2 = -1;
    }
}
