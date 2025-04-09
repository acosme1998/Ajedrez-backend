package Model;
/*
 * Una clase abstracta te permite:
 * -Reutilizar código común (posición, equipo, estado de vida).
 * -Forzar a que cada pieza implemente su propio método de movimiento.
 * -Evitar instanciar fichas genéricas (no tiene sentido crear una "Ficha" sin definir qué tipo es).
 */

import java.util.List;

import Config.Color;

public abstract class Ficha {
    //private: Solo la misma clase puede acceder a ese atributo o método.
    //protected: La misma clase y sus subclases pueden acceder a ese atributo o método.
    protected int x, y;
    protected boolean vivo;
    protected Color equipo;//color blanco-negro

    public Ficha(int x, int y, boolean vivo, Color equipo) {
        this.x = x;
        this.y = y;
        this.vivo = vivo; // estado inicial muerta para poder hacer mas adelante que se vea , parece vacio
        this.equipo = equipo; // el tablero
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    //color de ficha =blanco/negro
    // public boolean isEquipo() {
    //     return equipo;
    // }

    public Color getEquipo() {
        return equipo;
    }

    // verificar k este vivo
    public boolean isVivo() {
        return vivo;
    }

    
    public void matarFicha() {
        this.vivo = false;
    }

    // public void setCambioVivo(boolean vivo) {
    //     this.vivo = !vivo;
    // }

     // Método abstracto para que cada pieza implemente su movimiento
     public abstract boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero);
    

    //colocaremos todos los posibles movimeintos 
    public abstract List<int[]> listaMovimientosValidos(Tablero tablero);

    // Método abstracto para obtener la ruta de la imagen de la pieza
    public abstract String getRutaImagen();

}
