public class Jugador {
    private String nombre;
    private int pares;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.pares = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPares() {
        return pares;
    }

    public void setPares(int pares) {
        this.pares = pares;
    }

    public void anotarPar(){
        pares++;
    }
}
