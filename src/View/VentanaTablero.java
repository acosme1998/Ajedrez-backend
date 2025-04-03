package View;

import javax.swing.*;

import Controller.ControladorAjedrez;
// import View.Tablero;

import java.awt.*;

public class VentanaTablero {

    private JButton jugar; // Definir el botón como atributo
    private JFrame ventana;
    private JPanel menuDerecha;
    private View.TableroVista tablero;
    // public int filaPeones = 6;
    private ControladorAjedrez controlador;

    // Rutas imagenes piezas blancas/negras
    // private ImageIcon cargarImagen(String ruta) {
    // return new ImageIcon(getClass().getResource(ruta));
    // }

    // ImageIcon peonBlanco = cargarImagen("/PiezasAjedrez/Peon_blanco.png");
    // ImageIcon torreBlanca = cargarImagen("/PiezasAjedrez/Torre_blanca.png");
    // ImageIcon caballoBlanco = cargarImagen("/PiezasAjedrez/Caballo_blanco.png");
    // ImageIcon alfilBlanco = cargarImagen("/PiezasAjedrez/Alfil_blanco.png");
    // ImageIcon reinaBlanca = cargarImagen("/PiezasAjedrez/Reina_blanca.png");
    // ImageIcon reyBlanco = cargarImagen("/PiezasAjedrez/Rey_blanco.png");

    // ImageIcon peonNegro = cargarImagen("/PiezasAjedrez/Peon_negro.png");
    // ImageIcon torreNegra = cargarImagen("/PiezasAjedrez/Torre_negra.png");
    // ImageIcon caballoNegro = cargarImagen("/PiezasAjedrez/Caballo_negro.png");
    // ImageIcon alfilNegro = cargarImagen("/PiezasAjedrez/Alfil_negro.png");
    // ImageIcon reinaNegra = cargarImagen("/PiezasAjedrez/Reina_negra.png");
    // ImageIcon reyNegro = cargarImagen("/PiezasAjedrez/Rey_negro.png");

    // public VentanaTablero(Model.Tablero modelo){
    public VentanaTablero(View.TableroVista tablero) {
        this.tablero = tablero;

        ventana = new JFrame("Ajedrez");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1911, 982);

        // tablero = new Tablero(); // Vista del tablero
        // View.Tablero tablero = new View.Tablero();
       //controlador = new ControladorAjedrez(modelo, tablero); // Conectar lógica y vista
        tablero.setPreferredSize(new Dimension(982, 982));

        menuDerecha = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuDerecha.setPreferredSize(new Dimension(200, 400));

        JToolBar opciones = new JToolBar(JToolBar.VERTICAL);
        opciones.setFloatable(true);

        jugar = new JButton("Jugar");
        JButton parar = new JButton("Parar");
        JButton ajustes = new JButton("Ajustes");

        ventana.setLayout(new BorderLayout());

        menuDerecha.add(opciones);
        menuDerecha.add(jugar);
        menuDerecha.add(parar);
        menuDerecha.add(ajustes);

        controlador = new ControladorAjedrez(new Model.Tablero(), tablero, this); // Pasar la instancia

        jugar.setPreferredSize(new Dimension(70, 50));
        parar.setPreferredSize(new Dimension(70, 50));
        ajustes.setPreferredSize(new Dimension(70, 50));
        opciones.setPreferredSize(new Dimension(50, 70));

       

        // Configurar la acción del botón "Jugar"
        jugar.addActionListener(e -> controlador.iniciarJuego()); // Llamamos al método de inicio en el controlador

        ventana.add(tablero, BorderLayout.WEST);
        ventana.add(menuDerecha, BorderLayout.EAST);

        ventana.setVisible(true);
    }
    

    public JButton getJugarButton() {
        return jugar; // Devuelve el botón jugar
    }
}
