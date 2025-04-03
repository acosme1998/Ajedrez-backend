package Model;
import java.util.ArrayList;
import java.util.List;

import Config.Color;
//------------------------------------------Falta poner la logica para moverse/limites
public class Rey extends Ficha {

    public Rey(int x, int y, Color equipo) {
        super(x, y, true, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {


        return false;
    }

    @Override
    public String getRutaImagen() {
        return equipo == Color.BLANCO ? "src/PiezasAjedrez/Rey_blanco.png" : "src/PiezasAjedrez/Rey_negro.png";

    }

    //movimientos que puede hacer en todas las posiciones
    @Override
    public List<int[]> listaMovimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        // array de posibles movimientos
        int[][] direcciones = { };

        for (int[] dir : direcciones) {
            int xActual = this.x + dir[0];
            int yActual = this.y + dir[1];

            while (tablero.dentroDelTablero(xActual, yActual)) {
                if (!tablero.estaOcupada(xActual, yActual)) {//si al casilla no esta ocuapada puede moverse
                    movimientos.add(new int[] { xActual, yActual, 0});// tercer valor sobre que tipo de movimeinto es(normal,captura,jaque)
                } else {//Rey amenazado--bueno el rey puede amenzar al rey?
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

