// Solo juntas esta parte y no integres
class Caballo extends Ficha{
    public Caballo(int x, int y, Color equipo) {
        super(x, y, equipo);
    }

    @Override
    public boolean esMovimientoValido(int nuevoX, int nuevoY, Tablero tablero) {
        int deltaX = Math.abs(nuevoX - this.x);
        int deltaY = Math.abs(nuevoY - this.y);
        return (deltaX == deltaY) || (this.x == nuevoX) || (this.y == nuevoY);
    }

    // validate if it is alive
    public Caballo movimiento(Caballo caballo ,int opcion){
        if (!caballo.isVivo()){
            System.out.println("Caballo esta muerto");
            return;
        }

        // coger la posicion
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