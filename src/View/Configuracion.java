package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configuracion extends JFrame {
    // Variables para guardar configuraciones
    private String colorSeleccionado;
    private String respuestaPregunta1 = "";
    private String respuestaPregunta2 = "";
    private String respuestaPregunta3 = "";

    public Configuracion() {
        setTitle("Configuración de Ajedrez");
        
        // Ajustar al tamaño de la ventana principal
        setSize(1920, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Usar GridBag Layout para un mejor control de espaciado
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Añadir márgenes más grandes
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Título de configuración
        JLabel tituloConfiguracion = new JLabel("Configuración de Ajedrez", SwingConstants.CENTER);
        tituloConfiguracion.setFont(new Font("Times New Roman", Font.BOLD, 40)); // Fuente similar a tu menú
        gbc.ipady = 40; // Añadir padding vertical
        add(tituloConfiguracion, gbc);

        // 2. Panel desplegable para colores
        gbc.ipady = 0;
        JLabel labelColor = new JLabel("Selecciona un color:", SwingConstants.CENTER);
        labelColor.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(labelColor, gbc);

        String[] colores = {"Blanco y Negro", "Azul y Naranja", "Verde y Rojo"};
        JComboBox<String> comboColores = new JComboBox<>(colores);
        comboColores.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        comboColores.setPreferredSize(new Dimension(400, 50)); // Hacer más grande
        comboColores.addActionListener(e -> {
            colorSeleccionado = (String) comboColores.getSelectedItem();
        });
        add(comboColores, gbc);

        // 3. Modo de juego
        JLabel labelModoJuego = new JLabel("Modo de Juego:", SwingConstants.CENTER);
        labelModoJuego.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(labelModoJuego, gbc);

        // 4. Botones horizontales
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10)); // Más espacio entre botones
        
        JButton boton1 = new JButton("Modo tiempos turno ");
        JButton boton2 = new JButton("Pregunta 2");
        JButton boton3 = new JButton("Pregunta 3");

        // Estilo de botones similar al menú principal
        boton1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        boton2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        boton3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        boton1.setBackground(Color.decode("#E3E3E3"));
        boton2.setBackground(Color.decode("#E3E3E3"));
        boton3.setBackground(Color.decode("#E3E3E3"));
        boton1.setPreferredSize(new Dimension(200, 80));
        boton2.setPreferredSize(new Dimension(200, 80));
        boton3.setPreferredSize(new Dimension(200, 80));

        // Paneles para mostrar preguntas y respuestas
        JPanel panelPregunta1 = new JPanel();
        JPanel panelPregunta2 = new JPanel();
        JPanel panelPregunta3 = new JPanel();
        panelPregunta1.setVisible(false);
        panelPregunta2.setVisible(false);
        panelPregunta3.setVisible(false);

        // Manejadores de eventos para los botones (similar a código anterior)
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPregunta1.removeAll();
                panelPregunta1.setLayout(new BoxLayout(panelPregunta1, BoxLayout.Y_AXIS));
                JLabel pregunta1 = new JLabel("Tiempo de turno");
                pregunta1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                JTextField respuesta1 = new JTextField(20);
                respuesta1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                respuesta1.setMaximumSize(new Dimension(600, 50)); // Tamaño máximo
                respuesta1.addActionListener(evt -> {
                    respuestaPregunta1 = respuesta1.getText();
                });
                panelPregunta1.add(pregunta1);
                panelPregunta1.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado
                panelPregunta1.add(respuesta1);
                panelPregunta1.setVisible(true);
                panelPregunta2.setVisible(false);
                panelPregunta3.setVisible(false);
                revalidate();
                repaint();
            }
        });

        // Los otros dos botones siguen un patrón similar
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPregunta2.removeAll();
                panelPregunta2.setLayout(new BoxLayout(panelPregunta2, BoxLayout.Y_AXIS));
                JLabel pregunta2 = new JLabel("¿Cuál es tu segunda configuración?");
                pregunta2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                JTextField respuesta2 = new JTextField(20);
                respuesta2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                respuesta2.setMaximumSize(new Dimension(600, 50)); // Tamaño máximo
                respuesta2.addActionListener(evt -> {
                    respuestaPregunta2 = respuesta2.getText();
                });
                panelPregunta2.add(pregunta2);
                panelPregunta2.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado
                panelPregunta2.add(respuesta2);
                panelPregunta1.setVisible(false);
                panelPregunta2.setVisible(true);
                panelPregunta3.setVisible(false);
                revalidate();
                repaint();
            }
        });

        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPregunta3.removeAll();
                panelPregunta3.setLayout(new BoxLayout(panelPregunta3, BoxLayout.Y_AXIS));
                JLabel pregunta3 = new JLabel("¿Cuál es tu tercera configuración?");
                pregunta3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                JTextField respuesta3 = new JTextField(20);
                respuesta3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                respuesta3.setMaximumSize(new Dimension(600, 50)); // Tamaño máximo
                respuesta3.addActionListener(evt -> {
                    respuestaPregunta3 = respuesta3.getText();
                });
                panelPregunta3.add(pregunta3);
                panelPregunta3.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado
                panelPregunta3.add(respuesta3);
                panelPregunta1.setVisible(false);
                panelPregunta2.setVisible(false);
                panelPregunta3.setVisible(true);
                revalidate();
                repaint();
            }
        });

        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        add(panelBotones, gbc);

        // Añadir paneles de preguntas
        add(panelPregunta1, gbc);
        add(panelPregunta2, gbc);
        add(panelPregunta3, gbc);

        // 5. Botón de guardar y volver
        JButton botonGuardar = new JButton("Guardar y Volver al Menú");
        botonGuardar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        botonGuardar.setBackground(Color.decode("#E3E3E3"));
        botonGuardar.setPreferredSize(new Dimension(300, 80));
        gbc.ipady = 20; // Añadir padding vertical
        add(botonGuardar, gbc);

        botonGuardar.addActionListener(e -> {
            // Lógica para guardar configuración
            System.out.println("Color seleccionado: " + colorSeleccionado);
            System.out.println("Respuesta 1: " + respuestaPregunta1);
            System.out.println("Respuesta 2: " + respuestaPregunta2);
            System.out.println("Respuesta 3: " + respuestaPregunta3);
            
            // Volver al menú principal
            // new VentanaInicio();  // Comentado por si quieres volver a abrir
            dispose(); // Cierra la ventana de configuración
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        
        // Hacer visible la ventana
        setVisible(true);
    }

    public String getColorSeleccionado() {
        return colorSeleccionado;
    }


}