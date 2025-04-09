package Controller;

import Model.Tablero;
import Model.Ficha;
import View.VentanaTablero;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorAjedrez {
    private Tablero modelo;
    private View.TableroVista vista;
    private VentanaTablero ventana;
    private int[] seleccion; // Guarda la casilla seleccionada (x, y)

    public ControladorAjedrez(Tablero modelo, View.TableroVista vista,VentanaTablero ventana) {
        this.modelo = modelo;
        this.vista = vista;
        this.ventana = ventana;
        this.seleccion = null;

        // Inicializa la vista sin piezas al principio
        vista.actualizarVista(modelo);

        if (ventana.getJugarButton() != null) {
            ventana.getJugarButton().addActionListener(e -> iniciarJuego());
        } else {
            System.out.println("El botón Jugar es null.");
        }

        //actualizarVista();
        agregarEventos();
    }

    public void iniciarJuego() {
        // Inicializar las piezas en el modelo
        modelo.inicializarPiezas();

        // Actualizar la vista para mostrar las piezas
        vista.actualizarVista(modelo);

        //falta juntar
        
    }

    private void agregarEventos() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int finalX = x, finalY = y;
                vista.getCasillas()[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manejarClick(finalX, finalY);
                    }
                });
            }
        }
    }

    private void manejarClick(int x, int y) {
        if (seleccion == null) {
            // Primera selección (origen del movimiento)
            if (modelo.estaOcupada(x, y)) {
                seleccion = new int[] { x, y };
                vista.resetearColoresTablero(); 
                //añadir en view en un futuro
                resaltarMovimientos(x, y);
            }
        } else {
            // Segunda selección (destino del movimiento)
            int xOrigen = seleccion[0], yOrigen = seleccion[1];
            if (modelo.moverPieza(xOrigen, yOrigen, x, y)) {
                actualizarVista();
                ventana.limpiarMensajes();
            }
            seleccion = null; // Reiniciar selección
        }
    }

    private void actualizarVista() {
        vista.resetearColoresTablero(); // <- Agregado aquí
    
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Ficha ficha = modelo.getPieza(x, y);
                JButton boton = vista.getCasillas()[x][y];
    
                if (ficha == null) {
                    boton.setIcon(null);
                } else {
                    String imagenPath = ficha.getRutaImagen();
                    if (imagenPath != null && !imagenPath.isEmpty()) {
                        boton.setIcon(new ImageIcon(imagenPath));
                    } else {
                        boton.setIcon(null);
                    }
                }
            }
        }
    }
    



    private void resaltarMovimientos(int x, int y) {
        Ficha ficha = modelo.getPieza(x, y);
        if (ficha == null) return;
    
        List<int[]> movimientos = ficha.listaMovimientosValidos(modelo);
    
        List<int[]> casillasVacias = new ArrayList<>();
        List<int[]> casillasConEnemigos = new ArrayList<>();
        List<int[]> jaques = new ArrayList<>();
    
        for (int[] movimiento : movimientos) {
            int dx = movimiento[0];
            int dy = movimiento[1];
            int tipo = movimiento[2];
    
            if (tipo == 0) {
                casillasVacias.add(new int[]{dx, dy});
            } else if (tipo == 1) {
                casillasConEnemigos.add(new int[]{dx, dy});
            } else if (tipo == 2) {
                jaques.add(new int[]{dx, dy});
                ventana.mostrarMensaje("¡Jaque! El Rey está amenazado");
            }
        }
    
        vista.resaltarCasillas(casillasVacias, casillasConEnemigos, jaques, x, y);
    }
    

}
