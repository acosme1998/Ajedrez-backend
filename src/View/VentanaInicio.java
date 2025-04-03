package View;

// import Model.Tablero;
import View.TableroVista; 

import Controller.ControladorAjedrez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaInicio extends JFrame {
    private JFrame ventanaInicio;
    private Configuracion config;

    public VentanaInicio() {
        ventanaInicio = new JFrame("Menú Principal");
        ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInicio.setSize(1920, 1000);
        ventanaInicio.setLayout(null);

        JPanel panelFondo = new JPanel() {
            private Image fondo = new ImageIcon("Ajedrez/src/FondosPantallas/fondo.jpg").getImage(); 
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); 
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setBounds(0, 0, 1920, 1000);

        JButton iniciar = new JButton("Iniciar");
        JButton conf = new JButton("Configuración");
        JLabel bienvenida = new JLabel("¡BIENVENIDO AL AJEDREZ!");
        JLabel bienvenida2 = new JLabel("PUEDES MODIFICAR LOS AJUSTES DE PARTIDA EN CONFIGURACIÓN");

        conf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        iniciar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
        bienvenida2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        iniciar.setBackground(Color.decode("#E3E3E3"));
        conf.setBackground(Color.decode("#E3E3E3"));

        bienvenida.setBounds(550, 100, 500, 50);
        bienvenida2.setBounds(420, 150, 800, 50);
        iniciar.setBounds(650, 350, 200, 80);
        conf.setBounds(650, 500, 200, 80);

        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaTablero(); // Llama a la nueva función para inciar fichas
            }
        });

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config = new Configuracion();
                
                // Esperar hasta que la ventana de configuración se cierre
                config.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        // Ahora puedes usar los valores de configuración en el tablero
                        String colorSeleccionado = config.getColorSeleccionado(); 
                        System.out.println("Color guardado: " + colorSeleccionado);
                    }
                });
            }
        });
        

        panelFondo.add(bienvenida);
        panelFondo.add(bienvenida2);
        panelFondo.add(iniciar);
        panelFondo.add(conf);

        ventanaInicio.setContentPane(panelFondo);
        ventanaInicio.setVisible(true);
    }

    private void abrirVentanaTablero() {
        ventanaInicio.dispose(); // Cerrar menú principal
        TableroVista tablero = new TableroVista();
    
        // Verificar si config ha sido inicializado
        String colorSeleccionado = (config != null) ? config.getColorSeleccionado() : "default";
    
        // Aplicar color seleccionado o uno por defecto
        tablero.setColorTablero(colorSeleccionado);
    
        VentanaTablero ventanaTablero = new VentanaTablero(tablero);
    }
    
}
