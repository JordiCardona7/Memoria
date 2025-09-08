public class Celda {
    private String simbolo;
    private boolean visible;

    public Celda(String simbolo){
        this.simbolo = simbolo;
        this.visible = false;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean revelar(){
        return visible = true;
    }
    public boolean ocultar(){
        return visible = false;
    }

}
