package Model;
/*
 * Una clase abstracta te permite:
 * -Reutilizar código común (posición, equipo, estado de vida).
 * -Forzar a que cada pieza implemente su propio método de movimiento.
 * -Evitar instanciar fichas genéricas (no tiene sentido crear una "Ficha" sin definir qué tipo es).
 */

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
        this.vivo = false; // estado inicial muerta para poder hacer mas adelante que se vea , parece vacio
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

    // vivo o muerto
    public boolean isVivo() {
        return vivo;
    }

    // cambio de true a false o visceversa(false=muerto,true=vivo)
    // public void setVivo(boolean vivo) {
    //     this.vivo = vivo;
    // }

    public void matarFicha() {//si se peude falso despues para poner en muerto
        this.vivo = false;
    }

    // public void setCambioVivo(boolean vivo) {
    //     this.vivo = !vivo;
    // }

    // Método abstracto para que cada pieza implemente su movimiento
    public abstract boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero);


}