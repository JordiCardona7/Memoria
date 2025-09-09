import java.util.*;

public class Tablero {
    private int filas;
    private int cols;
    private Celda[][] celdas;
    private int paresTotales;
    private int paresEncontrados;

    public Tablero(int filas, int cols, List<String> simbolosDisponibles, Random rnd) {
        if (filas <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Dimensiones invalidas");
        }

        if ((filas * cols) % 2 != 0) {
            throw new IllegalArgumentException("El numero de celdas debe ser par");
        }
        this.filas = filas;
        this.cols = cols;
        this.celdas = new Celda[filas][cols];
        this.paresTotales = (filas * cols) / 2;
        this.paresEncontrados = 0;

        inicializar(simbolosDisponibles, rnd);

    }

    private void inicializar(List<String> simbolosDisponibles, Random rnd) {
        List<String> bolsa = new ArrayList<>();
        for (int i = 0; i < paresTotales; i++) {
            String simbolo = simbolosDisponibles.get(i);
            bolsa.add(simbolo);
            bolsa.add(simbolo);
        }
        Collections.shuffle(bolsa, rnd);

        int k = 0;
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                celdas[r][c] = new Celda(bolsa.get(k++));
            }
        }
    }

    private void validarRango(int r, int c) {
        if (r < 0 || r >= filas || c < 0 || c >= cols) {
            throw new IllegalArgumentException("Coordenadas fuera de rango");
        }
    }

    public Celda getCelda(int r, int c) {
        validarRango(r, c);
        return celdas[r][c];
    }

    public void revelar(int r, int c) throws Exception {
        validarRango(r, c);
        if (getCelda(r, c).isVisible()) {
            throw new Exception("Esa celda ya esta revelada");
        }
        getCelda(r, c).revelar();
    }
    public void ocultar(int r, int c){
        validarRango(r,c);
        getCelda(r,c).ocultar();
    }
    public boolean sonPareja(int r1, int c1, int r2, int c2) {
        validarRango(r1, c1);
        validarRango(r2, c2);
        return getCelda(r1, c1).getSimbolo().equals(getCelda(r2, c2).getSimbolo());
    }
    public void marcarPareja(int r1, int c1, int r2, int c2) {
        paresEncontrados++;
    }
    public boolean completo(){
        return paresEncontrados == paresTotales;
    }
    public String[][] snapshotVisible(){
        String[][] rep = new String[filas][cols];
        for (int r = 0; r < filas; r++){
            for (int c = 0; c < cols; c++){
                Celda f = celdas[r][c];
                rep[r][c] = f.isVisible() ? f.getSimbolo() : ".";
            }
        }
        return rep;
    }


    public int getFilas() {
        return filas;
    }

    public int getCols() {
        return cols;
    }

    public int getParesTotales() {
        return paresTotales;
    }

    public int getParesEncontrados() {
        return paresEncontrados;
    }
}
