package Model;

class Casilla {
    private Ficha ficha;
    private int x;
    private int y;

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

// ----------------------------------------------TABLERO---------------------------------------
class Tablero {
    // -------van en casilla
    // private boolean ocupado;
    // private boolean equipo;

    private int filas=8, columnas=8;
    // usamo el objeto Casilla que contiene los parametros que necesita cada casilla
    // esta en foramto array bidimencional es el tablero

    private Casilla[][] casilla;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.casilla = new Casilla[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casilla[i][j] = new Casilla(i,j);// las celulas cojen una posicion de x e y en cada lado
            }
        }
    }

    public boolean estaOcupada(int x, int y) {
        return casilla[x][y].estaOcupada();
    }

    public Ficha getPieza(int x, int y) {
        return casilla[x][y].getFicha();
    }

    public void colocarPieza(Ficha ficha, int x, int y) {
        casilla[x][y].colocarPieza(ficha);
    }

    public void eliminarPieza(int x, int y) {
        casilla[x][y].eliminarPieza();
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(casilla[i][j].estaOcupada() ? "[X]" : "[ ]");
            }
            System.out.println();
        }
    }

}
