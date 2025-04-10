package Model;

import java.util.ArrayList;
import java.util.List;

import Config.Color;

public class Caballo extends Ficha {

    public Caballo(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        int dx = Math.abs(nuevoX - this.x);
        int dy = Math.abs(nuevoY - this.y);

        // Movimiento en forma de L: 2 en una dirección, 1 en la otra
        if (!((dx == 2 && dy == 1) || (dx == 1 && dy == 2))) return false;

        if (!tablero.dentroDelTablero(nuevoX, nuevoY)) return false;

        if (!tablero.estaOcupada(nuevoX, nuevoY)) return true;

        Ficha piezaDestino = tablero.getPieza(nuevoX, nuevoY);
        return piezaDestino.getEquipo() != this.equipo;
    }

    @Override
    public String getRutaImagen() {
        return equipo == Color.BLANCO ? "src/PiezasAjedrez/Caballo_blanco.png" : "src/PiezasAjedrez/Caballo_negro.png";
    }

    @Override
    public List<int[]> listaMovimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        int[][] desplazamientos = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] mov : desplazamientos) {
            int nuevoX = this.x + mov[0];
            int nuevoY = this.y + mov[1];

            if (tablero.dentroDelTablero(nuevoX, nuevoY)) {
                if (!tablero.estaOcupada(nuevoX, nuevoY)) {
                    movimientos.add(new int[]{nuevoX, nuevoY, 0});
                } else {
                    Ficha otra = tablero.getPieza(nuevoX, nuevoY);
                    if (otra.getEquipo() != this.equipo) {
                        int tipoMovimiento = (otra instanceof Rey) ? 2 : 1;
                        movimientos.add(new int[]{nuevoX, nuevoY, tipoMovimiento});

                        if (tipoMovimiento == 2) {
                            System.out.println("¡El Rey está amenazado en " + nuevoX + " - " + nuevoY);
                        }
                    }
                }
            }
        }

        return movimientos;
    }
}
