package View;

import javax.swing.*;

import Model.Ficha;

import java.awt.*;

public class TableroVista extends JPanel {
    public static final int CANTIDAD_CASILLAS = 8;
    private JToggleButton[][] casillas = new JToggleButton[CANTIDAD_CASILLAS][CANTIDAD_CASILLAS];

    public TableroVista() {
        setLayout(new GridLayout(CANTIDAD_CASILLAS, CANTIDAD_CASILLAS));
        inicializarTablero();//tiene que ser invisible asta que des a jugar
    }

    public void inicializarTablero() {
        for (int fil = 0; fil < CANTIDAD_CASILLAS; fil++) {
            for (int col = 0; col < CANTIDAD_CASILLAS; col++) {
                casillas[fil][col] = new JToggleButton();
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
            JToggleButton boton = casillas[x][y]; // Casilla en la vista

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

    public void setColorTablero(String colorSeleccionado) {
        // Color color1 = Color.LIGHT_GRAY;
        Color color1=new Color(0xD3D3D3);

        Color color2 = new Color(0xffffff);
        // Color color2 = Color.DARK_GRAY;

        if ("Azul y Naranja".equals(colorSeleccionado)) {
            color1 = Color.BLUE;
            // color1=new Color(0xD3D3D3);
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
    }

    public JToggleButton[][] getCasillas() {
        return casillas;
    }
    
}
