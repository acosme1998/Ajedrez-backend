package Model;

public class Ficha {
    int x;
    int y;
    boolean vivo;
    boolean equipo;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        caballo.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        caballo.y = y;
    }
    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        caballo.vivo = vivo;
    }

    public void setCambioVivo(boolean vivo){
        caballo.vivo=!vivo;
    }

    public boolean isEquipo() {
        return equipo;
    }
    public void setEquipo(boolean equipo) {
        caballo.equipo = equipo;
    }

    // constructor
    public Ficha(int x, int y, boolean vivo, boolean equipo){
        caballo.x = x;
        caballo.y = y;
        caballo.vivo = vivo;
        caballo.equipo = equipo;
    }
}

class Caballo extends Ficha{
        // validate if it is alive
    public Caballo movimiento(Caballo caballo ,int opcion){
        if (!caballo.isVivo()){
            System.out.println("Caballo esta muerto");
            return;
        }

        // get the current position
        int x = caballo.getX();
        int y = caballo.getY();

        // movimiento
        switch (opcion){
            // x: -2 & y: +1
            case 1:
                x = caballo.setX(x - 2);
                y = caballo.setY(y - 1);
                break;
            // x: -2 & y: +1
            case 2:
                x = caballo.setX(x - 2);
                y = caballo.setY(y + 1);
                break;
            // x: +2 & y: -1
            case 3:
                x = caballo.setX(x + 2);
                y = caballo.setY(y - 1);
                break;
            // x: +2 & y: +1
            case 4:
                x = caballo.setX(x + 2);
                y = caballo.setY(y + 1);
            // x: -1 & y: -2
            case 5:
                x = caballo.setX(x - 1);
                y = caballo.setY(y - 2);
                break;
            // x: +1 & y: -2
            case 6:
                x = caballo.setX(x + 1);
                y = caballo.setY(y - 2);
                break;
            // x: +1 & y: -2
            case 7:
                x = caballo.setX(x + 1);
                y = caballo.setY(y - 2);
                break;
            // x: -1 & y: +2
            case 8:
                x = caballo.setX(x - 1);
                y = caballo.setY(y + 2);
                break;
            // x: +1 & y: -2
            case 9:
                x = caballo.setX(x + 1);
                y = caballo.setY(y - 2);
                break;
        }

        return caballo;
    }
}