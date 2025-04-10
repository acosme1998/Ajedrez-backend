package Model;

import java.util.ArrayList;
import java.util.List;

import Config.Color;

public class Reina extends Ficha {

    public Reina(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        int dx = Math.abs(nuevoX - this.x);
        int dy = Math.abs(nuevoY - this.y);

        // Movimiento válido: en línea recta o en diagonal
        if (dx != 0 && dy != 0 && dx != dy) return false;

        int dirX = Integer.compare(nuevoX, this.x);
        int dirY = Integer.compare(nuevoY, this.y);

        int xIntermedio = this.x + dirX;
        int yIntermedio = this.y + dirY;

        while (xIntermedio != nuevoX || yIntermedio != nuevoY) {
            if (tablero.estaOcupada(xIntermedio, yIntermedio)) {
                return false; // Hay una ficha bloqueando el camino
            }
            xIntermedio += dirX;
            yIntermedio += dirY;
        }

        return !tablero.estaOcupada(nuevoX, nuevoY)
            || tablero.getPieza(nuevoX, nuevoY).getEquipo() != this.equipo;
    }

    @Override
    public String getRutaImagen() {
        return equipo == Color.BLANCO ? "src/PiezasAjedrez/Reina_blanca.png" : "src/PiezasAjedrez/Reina_negra.png";
    }

    @Override
    public List<int[]> listaMovimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        // 8 direcciones posibles: combinando líneas rectas y diagonales
        int[][] direcciones = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        for (int[] dir : direcciones) {
            int xActual = this.x + dir[0];
            int yActual = this.y + dir[1];

            while (tablero.dentroDelTablero(xActual, yActual)) {
                if (!tablero.estaOcupada(xActual, yActual)) {
                    movimientos.add(new int[] { xActual, yActual, 0 });
                } else {
                    Ficha pieza = tablero.getPieza(xActual, yActual);
                    if (pieza.getEquipo() != this.equipo) {
                        int tipoMovimiento = (pieza instanceof Rey) ? 2 : 1;
                        movimientos.add(new int[] { xActual, yActual, tipoMovimiento });

                        if (tipoMovimiento == 2) {
                            System.out.println("¡El Rey está amenazado en " + xActual + " - " + yActual);
                        }
                    }
                    break;
                }
                xActual += dir[0];
                yActual += dir[1];
            }
        }

        return movimientos;
    }
}
