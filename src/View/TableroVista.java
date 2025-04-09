package View;

import javax.swing.*;

import Model.Ficha;

import Controller.ControladorAjedrez;

import java.awt.*;

import java.util.List;


public class TableroVista extends JPanel {
    public static final int CANTIDAD_CASILLAS = 8;
    private JButton[][] casillas = new JButton[CANTIDAD_CASILLAS][CANTIDAD_CASILLAS];
    public Color[]colores = new Color[2];
    

    public TableroVista() {
        setLayout(new GridLayout(CANTIDAD_CASILLAS, CANTIDAD_CASILLAS));
        inicializarTablero();//tiene que ser invisible asta que des a jugar
    }

    public void inicializarTablero() {
        for (int fil = 0; fil < CANTIDAD_CASILLAS; fil++) {
            for (int col = 0; col < CANTIDAD_CASILLAS; col++) {
                casillas[fil][col] = new JButton();
                casillas[fil][col].setPreferredSize(new Dimension(60, 60));
                add(casillas[fil][col]);
            }
        }
    
    }
    //mirar si cambiar a nombre ya que pondremos turnos
    public void actualizarVista(Model.Tablero modelo) {
    for (int x = 0; x < CANTIDAD_CASILLAS; x++) {
        for (int y = 0; y < CANTIDAD_CASILLAS; y++) {
            Ficha ficha = modelo.getPieza(x, y); // Obtener pieza del modelo
            JButton boton = casillas[x][y]; // Casilla en la vista

            //jugar fichas visible
            if (ficha == null) {
                boton.setIcon(null); // Si no hay pieza, quitar el icono
            } else {
                String imagenPath = ficha.getRutaImagen(); // Obtener la ruta de la imagen de la pieza
                if (imagenPath != null && !imagenPath.isEmpty()) {
                    boton.setIcon(new ImageIcon(imagenPath)); // Establecer el icono de la pieza
                }
            }
        }
    }
}
public void resaltarCasillas(List<int[]> movimientos, List<int[]> enemigos, List<int[]> jaques, int xSel, int ySel) {
    resetearColoresTablero(); // Limpiamos colores anteriores

    // Ficha seleccionada
    casillas[xSel][ySel].setBackground(Color.YELLOW);

    // Casillas vacías (movimientos válidos)
    for (int[] m : movimientos) {
        casillas[m[0]][m[1]].setBackground(Color.GREEN);
    }

    // Capturas
    for (int[] e : enemigos) {
        casillas[e[0]][e[1]].setBackground(Color.ORANGE);
    }

    // Amenazas al rey
    for (int[] j : jaques) {
        casillas[j[0]][j[1]].setBackground(Color.RED);
        
    }
}

public void resetearColoresTablero() {
    for (int fil = 0; fil < CANTIDAD_CASILLAS; fil++) {
        for (int col = 0; col < CANTIDAD_CASILLAS; col++) {
            if ((fil + col) % 2 == 0) {
                //casillas[fil][col].setBackground(new Color(240, 217, 181)); // claro
                casillas[fil][col].setBackground(colores[0]); // claro

            } else {
                //casillas[fil][col].setBackground(new Color(181, 136, 99)); // oscuro
                casillas[fil][col].setBackground(colores[1]); // oscuro

            }
        }
    }
}

    public Color[] setColorTablero(String colorSeleccionado) {
        
        Color color1=new Color(0xD3D3D3);
        Color color2 = new Color(0xffffff);

        if ("Azul y Naranja".equals(colorSeleccionado)) {
            color1 = Color.BLUE;
            color2 = Color.ORANGE;
        } else if ("Verde y Rojo".equals(colorSeleccionado)) {
            color1 = Color.GREEN;
            color2 = Color.RED;
        }

        // Aplica los colores al tablero
        for (int fil = 0; fil < CANTIDAD_CASILLAS; fil++) {
            for (int col = 0; col < CANTIDAD_CASILLAS; col++) {
                if ((fil + col) % 2 == 0) {
                    casillas[fil][col].setBackground(color1);
                } else {
                    casillas[fil][col].setBackground(color2);
                }
            }
        }
        colores[0]=color1;
        colores[1]=color2;
        
        return colores;
    }

    public JButton[][] getCasillas() {
        return casillas;
    }
    
}
