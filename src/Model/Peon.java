package Model;

import Config.Color;

public class Peon extends Ficha {

    public Peon(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        // Movimiento hacia adelante
        int direccion = (equipo == Color.BLANCO) ? -1 : 1; // Blancos suben (-1), negros bajan (+1)

        // Movimiento normal de un paso
        if (nuevoX == x + direccion && nuevoY == y && !tablero.estaOcupada(nuevoX, nuevoY)) {
            return true;
        }

        // Movimiento inicial de dos pasos
        if (x == ((equipo == Color.BLANCO) ? 6 : 1) && nuevoX == x + 2 * direccion && nuevoY == y
                && !tablero.estaOcupada(nuevoX, nuevoY) && !tablero.estaOcupada(x + direccion, y)) {
            return true;
        }

        // Captura en diagonal
        if (nuevoX == x + direccion && (nuevoY == y + 1 || nuevoY == y - 1)
                && tablero.estaOcupada(nuevoX, nuevoY)
                && tablero.getPieza(nuevoX, nuevoY).getEquipo() != this.equipo) {
            return true;
        }

        return false;
    }
}
