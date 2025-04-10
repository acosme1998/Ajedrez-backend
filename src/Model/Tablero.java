package Model;

import Config.Color;

import java.util.ArrayList;
import java.util.List;

class Casilla {
    private Ficha ficha;
    private final int x, y;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.ficha = null;// para saber si contiene algo
    }

    public boolean estaOcupada() {
        return ficha != null;// cambiar estado de ocupado
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void colocarPieza(Ficha ficha) {
        this.ficha = ficha;
    }

    public void eliminarPieza() {
        this.ficha = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

public class Tablero {
    private final int filas = 8, columnas = 8;

    private final Casilla[][] casillas;

    private Color turno;

    protected List<Ficha> muertos = new ArrayList<>(); // lista de muertos

    public Tablero() {
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
        this.turno = Color.BLANCO; // empieza equipo "BLANCO"
    }

    public Color getTurno() {
        return turno;
    } // sirve para controlar el turno

    public void setTurno(Color turno) {
        this.turno = turno;
    }

    public List<Ficha> getMuertos() {
        return muertos;
    }

    public boolean dentroDelTablero(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas; // se colocan limites del tablero
    }

    public boolean estaOcupada(int x, int y) {
        return dentroDelTablero(x, y) && casillas[x][y].estaOcupada();
    }// si hay ficha en x,y te de vuelve true

    public Ficha getPieza(int x, int y) {
        return casillas[x][y].getFicha();
    }

    public void colocarPieza(Ficha ficha, int x, int y) {
        if (dentroDelTablero(x, y)) {// si esta dentro del tablero, coloca la ficha en la casilla con las mismas
            // coordenadas
            casillas[x][y].colocarPieza(ficha);
        }
    }

    public void eliminarPieza(int x, int y) {
        casillas[x][y].eliminarPieza();
    }

    // pone ficha en el inicio
    public void inicializarPiezas() {
        // Colocar peones blancos
        for (int col = 0; col < columnas; col++) {
            Peon peonBlanco = new Peon(6, col, Color.BLANCO);
            colocarPieza(peonBlanco, 6, col);
        }

        // Colocar peones negros
        for (int col = 0; col < columnas; col++) {
            Peon peonNegro = new Peon(1, col, Color.NEGRO);
            colocarPieza(peonNegro, 1, col);
        }

        // Alfiles blancos
        colocarPieza(new Alfil(7, 2, Color.BLANCO), 7, 2);
        colocarPieza(new Alfil(7, 5, Color.BLANCO), 7, 5);

        // Alfiles negros
        colocarPieza(new Alfil(0, 2, Color.NEGRO), 0, 2);
        colocarPieza(new Alfil(0, 5, Color.NEGRO), 0, 5);

        // Torres blancas
        colocarPieza(new Torre(7, 0, Color.BLANCO), 7, 0);
        colocarPieza(new Torre(7, 7, Color.BLANCO), 7, 7);

        // Torres negras
        colocarPieza(new Torre(0, 0, Color.NEGRO), 0, 0);
        colocarPieza(new Torre(0, 7, Color.NEGRO), 0, 7);

        // Reyes
        colocarPieza(new Rey(7, 4, Color.BLANCO), 7, 4);
        colocarPieza(new Rey(0, 4, Color.NEGRO), 0, 4);

        // Reina
        colocarPieza(new Reina(7, 3, Color.BLANCO), 7, 3);
        colocarPieza(new Reina(0, 3, Color.NEGRO), 0, 3);

        // Caballos
        colocarPieza(new Caballo(7, 1, Color.BLANCO), 7, 1);
        colocarPieza(new Caballo(7, 6, Color.BLANCO), 7, 6);
        colocarPieza(new Caballo(0, 1, Color.NEGRO), 0, 1);
        colocarPieza(new Caballo(0, 6, Color.NEGRO), 0, 6);
    }

    public boolean moverPieza(int xActual, int yActual, int xNueva, int yNueva) {
        if (!dentroDelTablero(xActual, yActual) || !dentroDelTablero(xNueva, yNueva)) {
            return false;
        }

        Ficha ficha = getPieza(xActual, yActual);
        if (ficha.equipo != turno){
            return false; // el equipo q no toca no puede jugar(mover)
        }

        if (ficha == null || !ficha.esMovimientoValido(xNueva, yNueva, this)) {
            return false;
        }

        if (estaOcupada(xNueva, yNueva)) {
            Ficha fichaEnemiga = getPieza(xNueva, yNueva);
            if (fichaEnemiga.getEquipo() != ficha.getEquipo()) {
                fichaEnemiga.matarFicha(this);
                eliminarPieza(xNueva, yNueva);
            } else {
                return false; // No se puede mover a una casilla ocupada por una ficha del mismo equipo
            }
            System.out.println();
        }

        eliminarPieza(xActual, yActual);
        colocarPieza(ficha, xNueva, yNueva);
        ficha.setX(xNueva);
        ficha.setY(yNueva);
        // cambiar de turno(equipo)
        this.turno = this.turno == Color.NEGRO ? Color.BLANCO : Color.NEGRO;

        return true;
    }
}