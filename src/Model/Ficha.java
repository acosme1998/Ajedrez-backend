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
        caballo.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        caballo.y = y;
    }
    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        caballo.vivo = vivo;
    }

    public void setCambioVivo(boolean vivo){
        caballo.vivo=!vivo;
    }

    public boolean isEquipo() {
        return equipo;
    }
    public void setEquipo(boolean equipo) {
        caballo.equipo = equipo;
    }

    // constructor
    public Ficha(int x, int y, boolean vivo, boolean equipo){
        caballo.x = x;
        caballo.y = y;
        caballo.vivo = vivo;
        caballo.equipo = equipo;
    }
}

