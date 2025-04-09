package Model;

import Config.Color;

class Casilla {
    private Ficha ficha;
    private final int x, y;

    public Casilla(int x, int y) {
        this.x=x;
        this.y=y;
        this.ficha = null;//para saber si contiene algo
    }

    public boolean estaOcupada() {
        return ficha != null;//cambiar estado de ocupado
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

    public Tablero() {
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }
    

    public boolean dentroDelTablero(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas; //se colocan limites del tablero
    }

    public boolean estaOcupada(int x, int y) {
        return dentroDelTablero(x, y) && casillas[x][y].estaOcupada();
    }//si hay ficha en x,y te de vuelve true

    public Ficha getPieza(int x, int y) {
        return casillas[x][y].getFicha();
    }

    public void colocarPieza(Ficha ficha, int x, int y) {
        if (dentroDelTablero(x, y)) {//si esta dentro del tablero, coloca la ficha en la casilla con las mismas coordenadas
            casillas[x][y].colocarPieza(ficha);
        }
    }

    public void eliminarPieza(int x, int y) {
        casillas[x][y].eliminarPieza();
    }

    //pone ficha en el inicio
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

        // // Alfiles negros
        colocarPieza(new Alfil(0, 2, Color.NEGRO), 0, 2);
        colocarPieza(new Alfil(0, 5, Color.NEGRO), 0, 5);


        //Rey 
        colocarPieza(new Rey(0, 2, Color.BLANCO), 7, 4);
        colocarPieza(new Rey(0, 5, Color.NEGRO), 0, 4);
    }

    public boolean moverPieza(int xActual, int yActual, int xNueva, int yNueva) {
        if (!dentroDelTablero(xActual, yActual) || !dentroDelTablero(xNueva, yNueva)) {
            return false;
        }

        Ficha ficha = getPieza(xActual, yActual);
        if (ficha == null || !ficha.esMovimientoValido(xNueva, yNueva, this)) {
            return false;
        }

        if (estaOcupada(xNueva, yNueva)) {
            Ficha fichaEnemiga = getPieza(xNueva, yNueva);
            if (fichaEnemiga.getEquipo() != ficha.getEquipo()) {
                fichaEnemiga.matarFicha();
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

        return true;
    }
}

