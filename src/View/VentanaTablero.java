package View;

import javax.swing.*;

import Config.Color;
import Controller.ControladorAjedrez;
// import View.Tablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.*;

public class VentanaTablero {

    private JButton jugar; // Definir el botón como atributo
    private JFrame ventana;
    private JPanel menuDerecha;
    private View.TableroVista tablero;
    // public int filaPeones = 6;

    private JLabel etiquetaTemporizador;
    private JTextArea areaMensajes;
    private ControladorAjedrez controlador;
    private Timer temporizador;

    private AtomicInteger contadorTiempo = new AtomicInteger(0); // Contador de segundos para usarlo con lambda
    private int totalTiempo = 0;
    private int minutosRestantes = 0;
    private int segundosRestantes = 0;
    private boolean detenerTemporizador = false;

    // public VentanaTablero(Model.Tablero modelo){
    public VentanaTablero(View.TableroVista tablero) {
        this.tablero = tablero;

        ventana = new JFrame("Ajedrez");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1911, 982);

        // tablero = new Tablero(); // Vista del tablero
        // View.Tablero tablero = new View.Tablero();
        // controlador = new ControladorAjedrez(modelo, tablero); // Conectar lógica y
        // vista
        tablero.setPreferredSize(new Dimension(982, 982));

        menuDerecha = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuDerecha.setPreferredSize(new Dimension(200, 400));

        JToolBar opciones = new JToolBar(JToolBar.VERTICAL);
        opciones.setFloatable(true);

        JButton jugar = new JButton("Jugar");
        JButton parar = new JButton("Parar");
        JButton ajustes = new JButton("Rendirse");

        areaMensajes = new JTextArea(2, 15);
        areaMensajes.setEditable(false);
        areaMensajes.setLineWrap(true);
        areaMensajes.setWrapStyleWord(true);
        areaMensajes.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(areaMensajes);
        scroll.setPreferredSize(new Dimension(180, 60));

        ventana.setLayout(new BorderLayout());

        menuDerecha.add(opciones);
        menuDerecha.add(jugar);
        menuDerecha.add(parar);
        menuDerecha.add(scroll);
        ;

        controlador = new ControladorAjedrez(new Model.Tablero(), tablero, this); // Pasar la instancia

        jugar.setPreferredSize(new Dimension(70, 50));
        parar.setPreferredSize(new Dimension(70, 50));
        // ajustes.setPreferredSize(new Dimension(70, 50));
        opciones.setPreferredSize(new Dimension(50, 70));

        // Configurar la acción del botón "Jugar"
        jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.iniciarJuego();
                iniciarTemporizador(0, 0, 0);

                //Deshabilitar el boton despues del primer click
                jugar.setEnabled(false);
            }
        });

        parar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Condiciones ara cambiar nombre de etiqueta y empezar temporizador
                if(detenerTemporizador){
                    temporizador.start();
                    parar.setText("Pausar");
                }
                else{
                    temporizador.stop();
                    parar.setText("Reanudar");
                }
                detenerTemporizador = !detenerTemporizador; //Cambiar el estado
            }
        });

        ventana.add(tablero, BorderLayout.WEST);
        ventana.add(menuDerecha, BorderLayout.EAST);

        ventana.setVisible(true);

        etiquetaTemporizador = new JLabel("Tiempo: 00:00");
        etiquetaTemporizador.setFont(new Font("Times New Roman", Font.BOLD, 16));
        menuDerecha.add(etiquetaTemporizador);
    }

    //MEtodo para inicializar el temporizador segun el metodo seleccionado
    public void iniciarTemporizador(int minutosInfinito, int minutosTiempoLimitado, int minutosTiempoTurnos) {
        // Obtener la respuesta seleccionada en la posición 0
        String respuestaModo = Ventana.configuracion.getRespuestas(0) == null ? "Infinito" : Ventana.configuracion.getRespuestas(0);

        if (respuestaModo.equals("Infinito")) {
            minutosInfinito = 0;
            totalTiempo = 0;

            temporizador = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    totalTiempo++;
                    minutosRestantes = totalTiempo / 60;
                    segundosRestantes = totalTiempo % 60;

                    etiquetaTemporizador.setText(String.format("Tiempo: %02d:%02d", minutosRestantes, segundosRestantes));
                }
            });

            temporizador.start();
        }

        if (respuestaModo.equals("Tiempo Limitado")) {
            //Coger el valor que esta guardado en la posicon del array y pasarlo a numerico
            minutosTiempoLimitado = Integer.parseInt(Ventana.configuracion.getRespuestas(1));
            totalTiempo = minutosTiempoLimitado * 60; //pasar los minutos a segundos

            temporizador = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    totalTiempo--; // Resta segundos
                    minutosRestantes = totalTiempo / 60;
                    segundosRestantes = totalTiempo % 60;

                    etiquetaTemporizador.setText(String.format("Tiempo: %02d:%02d", minutosRestantes, segundosRestantes));

                    //Condicion tiepo acabado
                    if(totalTiempo == 0){
                        temporizador.stop();
                        etiquetaTemporizador.setText("Tiempo agotado!");
                    }
                }
            });

            temporizador.start();
        }

        if (respuestaModo.equals("Tiempo por Turnos")) {
            minutosTiempoTurnos = Integer.parseInt(Ventana.configuracion.getRespuestas(2));
            totalTiempo = minutosTiempoTurnos * 60;

            temporizador = new Timer(1000, new ActionListener() {
                Color turno = controlador.getModelo().getTurno();
                @Override
                public void actionPerformed(ActionEvent e) {

                    totalTiempo--; // Resta segundos
                    minutosRestantes = totalTiempo / 60;
                    segundosRestantes = totalTiempo % 60;

                    etiquetaTemporizador.setText(String.format("Tiempo: %02d:%02d", minutosRestantes, segundosRestantes));

                    //Condicion tiempo acabado
                    if(totalTiempo == 0){
                        temporizador.stop();
                        etiquetaTemporizador.setText("Tiempo agotado!");
                        controlador.cambioTurno();
                    } else if (turno != controlador.getModelo().getTurno()){
                        // cuando mueves la ficha cambia el turno(ref: model/tablero > moverPieza)
                        // si es diferente del turno inicial cambio de turno
                        temporizador.stop();
                        etiquetaTemporizador.setText("Cambio de turno!");
                    }
                }
            });

            temporizador.start();
        }

    }



    public JButton getJugarButton() {
        return jugar; // Devuelve el botón jugar
    }

    public void mostrarMensaje(String mensaje) {
        areaMensajes.setText(mensaje); // Muestra un único mensaje
    }

    public void limpiarMensajes() {
        areaMensajes.setText("");
    }

}