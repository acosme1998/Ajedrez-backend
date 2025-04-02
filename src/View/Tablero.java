package View;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel{
    public static final int CANTIDAD_CASILLAS = 8;
    public JToggleButton[][] casillas = new JToggleButton[CANTIDAD_CASILLAS][CANTIDAD_CASILLAS];

    public Tablero(){
        setLayout(new GridLayout(CANTIDAD_CASILLAS, CANTIDAD_CASILLAS));
        inicializarTablero();
    }

    public void inicializarTablero(){
        for(int fil = 0; fil < CANTIDAD_CASILLAS; fil++){
            for(int col = 0; col < CANTIDAD_CASILLAS; col++){
                casillas [fil][col] = new JToggleButton();
                casillas[fil][col].setPreferredSize(new Dimension(15, 15));
                add(casillas[fil][col]);
            }
        }
    }

    public JToggleButton[][] getCasillas() {
        return casillas;
    }
}
