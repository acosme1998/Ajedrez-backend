package Model;

import Config.Color;

public class Reina extends Ficha {
    public Reina(int x, int y, Color equipo) {
        super(x, y, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        int deltaX = Math.abs(nuevoX - this.x);
        int deltaY = Math.abs(nuevoY - this.y);
        return (deltaX == deltaY) || (this.x == nuevoX) || (this.y == nuevoY);
    }
}