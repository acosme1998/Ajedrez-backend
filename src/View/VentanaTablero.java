package View;

import javax.swing.*;
import java.awt.*;

public class VentanaTablero {
    private JFrame ventana;
    private JPanel menuDerecha;
    private Tablero tablero;
    public int filaPeones = 6;
    //Rutas imagenes piezas blancas/negras
    ImageIcon peonBlanco = new ImageIcon("src/PiezasAjedrez/Peon_blanco.png");
    ImageIcon torreBlanca= new ImageIcon("src/PiezasAjedrez/Torre_blanca.png");
    ImageIcon caballoBlanco =new ImageIcon( "src/PiezasAjedrez/Caballo_blanco.png");
    ImageIcon alfilBlanco = new ImageIcon("src/PiezasAjedrez/Alfil_blanco.png");
    ImageIcon reinaBlanca = new ImageIcon("src/PiezasAjedrez/Reina_blanca.png");
    ImageIcon reyBlanco = new ImageIcon("src/PiezasAjedrez/Rey_blanco.png");

    ImageIcon peonNegro = new ImageIcon("src/PiezasAjedrez/Peon_negro.png");
    ImageIcon torreNegra = new ImageIcon("src/PiezasAjedrez/Torre_negra.png");
    ImageIcon caballoNegro =new ImageIcon( "src/PiezasAjedrez/Caballo_negro.png");
    ImageIcon alfilNegro = new ImageIcon("src/PiezasAjedrez/Alfil_negro.png");
    ImageIcon reinaNegra = new ImageIcon("src/PiezasAjedrez/Reina_negra.png");
    ImageIcon reyNegro= new ImageIcon("src/PiezasAjedrez/Rey_negro.png");

     


    public VentanaTablero(){
        ventana = new JFrame("Ajedrez");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1911, 982);

        tablero = new Tablero();
        tablero.setPreferredSize(new Dimension(982, 982));

        menuDerecha = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuDerecha.setPreferredSize(new Dimension(200, 400));

        JToolBar opciones = new JToolBar(JToolBar.VERTICAL);
        opciones.setFloatable(true);

        JButton jugar = new JButton("Jugar");
        JButton parar = new JButton("Parar");
        JButton ajustes = new JButton("Ajustes");

        ventana.setLayout(new BorderLayout());

        menuDerecha.add(opciones);
        menuDerecha.add(jugar);
        menuDerecha.add(parar);
        menuDerecha.add(ajustes);
        
        jugar.setPreferredSize(new Dimension(70, 50));
        parar.setPreferredSize(new Dimension(70, 50));
        ajustes.setPreferredSize(new Dimension(70, 50));
        opciones.setPreferredSize(new Dimension(50, 70));

        for(int fil = 0; fil < Tablero.CANTIDAD_CASILLAS; fil++){
            for(int col = 0; col < Tablero.CANTIDAD_CASILLAS; col++){
                //Color del tablero
                if((fil + col) % 2 == 0){
                    tablero.getCasillas()[fil][col].setBackground(new Color(0xD3D3D3));
                }
                else{
                    tablero.getCasillas()[fil][col].setBackground(new Color(0xffffff));
                }
                //Colocar torre blanca/negra
                if((fil == 7 && col == 0) || (fil == 7 && col == 7)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(torreBlanca.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                if((fil == 0 && col == 0) || (fil == 0 && col == 7)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(torreNegra.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                //Colocar caballo blanco/negro
                if((fil == 7 && col == 1) || (fil == 7 && col == 6)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(caballoBlanco.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                if((fil == 0 && col == 1) || (fil == 0 && col == 6)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(caballoNegro.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                //Colocar alfil blanco/negro
                if((fil == 7 && col == 2) || (fil == 7 && col == 5)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(alfilBlanco.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                if((fil == 0 && col == 2) || (fil == 0 && col == 5)){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(alfilNegro.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                //Colocar reina blanca/negra
                if(fil == 7 && col == 3){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(reinaBlanca.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                if(fil == 0 && col == 3){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(reinaNegra.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                //Colocar rey blanco/negro
                if(fil == 7 && col == 4){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(reyBlanco.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                if(fil == 0 && col == 4){
                    tablero.getCasillas()[fil][col].setIcon(new ImageIcon(reyNegro.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                //Colocar peones blancos/negros
                for(int columna = 0; columna < tablero.getCasillas()[filaPeones].length; columna++){
                    tablero.getCasillas()[filaPeones][columna].setIcon(new ImageIcon(peonBlanco.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
                filaPeones = 1;
                for(int columna = 0; columna < tablero.getCasillas()[filaPeones].length; columna++){
                    tablero.getCasillas()[filaPeones][columna].setIcon(new ImageIcon(peonNegro.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
            }
        }

        ventana.add(tablero, BorderLayout.WEST);
        ventana.add(menuDerecha,BorderLayout.EAST);

        ventana.setVisible(true);
    }
}
