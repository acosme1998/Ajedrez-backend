package Model;

public class Ficha {
    int x;
    int y;
    boolean vivo;
    boolean equipo;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setCambioVivo(boolean vivo){
        this.vivo=!vivo;
    }

    public boolean isEquipo() {
        return equipo;
    }
    public void setEquipo(boolean equipo) {
        this.equipo = equipo;
    }
    
}
