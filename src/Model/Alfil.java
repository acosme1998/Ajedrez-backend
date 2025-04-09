package Model;

import java.util.ArrayList;
import java.util.List;

import Config.Color;

public class Alfil extends Ficha {

    public Alfil(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        int dx = Math.abs(nuevoX - this.x);
        int dy = Math.abs(nuevoY - this.y);

        // Movimiento en diagonal (dx debe ser igual a dy)
        if (dx != dy)
            return false;

        int dirX = (nuevoX > this.x) ? 1 : -1;
        int dirY = (nuevoY > this.y) ? 1 : -1;

        int xIntermedio = this.x + dirX;
        int yIntermedio = this.y + dirY;

        while (xIntermedio != nuevoX && yIntermedio != nuevoY) {
            if (tablero.estaOcupada(xIntermedio, yIntermedio)) {
                return false; // Hay una pieza bloqueando el camino
            }
            xIntermedio += dirX;
            yIntermedio += dirY;
        }

        // Verificar si la casilla destino está vacía o contiene una pieza enemiga
        if (!tablero.estaOcupada(nuevoX, nuevoY) ||
                tablero.getPieza(nuevoX, nuevoY).getEquipo() != this.equipo) {
            return true;
        }

        return false;
    }

    @Override
    public String getRutaImagen() {
        return equipo == Color.BLANCO ? "src/PiezasAjedrez/Alfil_blanco.png" : "src/PiezasAjedrez/Alfil_negro.png";

    }

    //movimientos que puede hacer en todas las posiciones
    @Override
    public List<int[]> listaMovimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        // array de posibles movimientos
        int[][] direcciones = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

        for (int[] dir : direcciones) {
            int xActual = this.x + dir[0];
            int yActual = this.y + dir[1];

            while (tablero.dentroDelTablero(xActual, yActual)) {
                if (!tablero.estaOcupada(xActual, yActual)) {//si al casilla no esta ocuapada puede moverse
                    movimientos.add(new int[] { xActual, yActual, 0});// tercer valor sobre que tipo de movimeinto es(normal,captura,jaque)
                } else {
                    Ficha pieza = tablero.getPieza(xActual, yActual);
                    if (pieza.getEquipo() != this.equipo) {
                        int tipoMovimiento = (pieza instanceof Rey) ? 2 : 1; 
                        //instanceof sirve para verificar que tipo de objeto es( verifica si el objeto ficha es Rey)
                        //si es Rey devuvel tipoMovimiento =2, amenaza jaque, sino 1= captura
                        movimientos.add(new int[]{xActual, yActual, tipoMovimiento}); 

                        if (tipoMovimiento == 2) {
                            System.out.println("¡El Rey está amenazado en " + xActual + " - " + yActual );
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
