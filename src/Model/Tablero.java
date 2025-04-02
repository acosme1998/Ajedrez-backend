package Model;

import Config.Color;

class Casilla {
    private Ficha ficha;
    private final int x;
    private final int y;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.ficha = null; // Casilla vacÃ­a al inicio
    }

    public boolean estaOcupada() {
        return ficha != null;
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

class Tablero {
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

    public boolean estaOcupada(int x, int y) {
        return dentroDelTablero(x, y) && casillas[x][y].estaOcupada();
    }

    public Ficha getPieza(int x, int y) {
        return dentroDelTablero(x, y) ? casillas[x][y].getFicha() : null;
    }

    public void colocarPieza(Ficha ficha, int x, int y) {
        if (dentroDelTablero(x, y)) {
            casillas[x][y].colocarPieza(ficha);
        }
    }

    public void eliminarPieza(int x, int y) {
        if (dentroDelTablero(x, y)) {
            casillas[x][y].eliminarPieza();
        }
    }

    private boolean dentroDelTablero(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas;
    }

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
        }
        
        eliminarPieza(xActual, yActual);
        colocarPieza(ficha, xNueva, yNueva);
        ficha.setX(xNueva);
        ficha.setY(yNueva);

        return true;
    }

}