package Model;

import java.util.ArrayList;
import java.util.List;

import Config.Color;

public class Peon extends Ficha {

    public Peon(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        // Movimiento hacia adelante
        int direccion = (equipo == Color.BLANCO) ? -1 : 1; // Si color blanco suben (-1), else bajan (+1)

        // Movimiento normal de un paso
        if (nuevoX == x + direccion && nuevoY == y && !tablero.estaOcupada(nuevoX, nuevoY)) {
            return true;
        }

        // Movimiento inicial de dos pasos
        if (x == ((equipo == Color.BLANCO) ? 6 : 1) // x esta en la fila inical( si es blanco=6, else =1)
                && nuevoX == x + 2 * direccion
                && nuevoY == y // se mueve solo en x de forma horizontal sera el mismo
                && !tablero.estaOcupada(nuevoX, nuevoY)
                && !tablero.estaOcupada(x + direccion, y)) // Verifica que la casilla intermedia tampoco este ocupada
        {
            return true;
        }

        // Captura en diagonal
        if (nuevoX == x + direccion // se mueve en linea direccion(Si color blanco suben (-1), else bajan (+1))
                && (nuevoY == y + 1 || nuevoY == y - 1) // se mueve a derecha o izq
                && tablero.estaOcupada(nuevoX, nuevoY)
                && tablero.getPieza(nuevoX, nuevoY).getEquipo() != this.equipo) {
            return true;
        }

        return false;
    }

    @Override
    public String getRutaImagen() {
        return equipo == Color.BLANCO ? "src/PiezasAjedrez/peon_blanco.png" : "src/PiezasAjedrez/peon_negro.png";

    }

    @Override
    public List<int[]> listaMovimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        int direccion = (equipo == Color.BLANCO) ? -1 : 1; // Si color blanco suben (-1), else bajan (+1)

        if (!tablero.estaOcupada(x + direccion, y)) {
            movimientos.add(new int[] { x + direccion, y, 0 }); // 1 paso
        }

        
        if ((x == 6 && equipo == Color.BLANCO) || (x == 1 && equipo == Color.NEGRO)) { // inicial dos pasos
            if (!tablero.estaOcupada(x + direccion, y) && !tablero.estaOcupada(x + 2 * direccion, y)) {
                movimientos.add(new int[] { x + 2 * direccion, y, 0 });
            }
        }

        int[] izqDerDiagonal = { -1, 1 };
        for (int i = 0; i < 2; i++) {
            // la diagonal vanza 1 y muve uno izq o der
            int xActual = x + direccion;
            int yActual = y + izqDerDiagonal[i];

            if (tablero.dentroDelTablero(xActual, yActual) && tablero.estaOcupada(xActual, yActual)) {
                Ficha pieza = tablero.getPieza(xActual, yActual);
                if (pieza.getEquipo() != this.equipo) {// si no es del mismo equipo
                    int tipoMovimiento = (pieza instanceof Rey) ? 2 : 1;
                    // instanceof sirve para verificar que tipo de objeto es( verifica si el objeto
                    // ficha es Rey)
                    // si es Rey devuvel tipoMovimiento =2, amenaza jaque, sino 1= captura
                    movimientos.add(new int[] { xActual, yActual, tipoMovimiento });

                    if (tipoMovimiento == 2) {
                        System.out.println("¡El Rey está amenazado en " + xActual + " - " + yActual);
                    }
                }
            }
        }
        return movimientos;
    }


}
