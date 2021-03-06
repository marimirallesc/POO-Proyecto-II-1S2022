package ProyectoI;

/**
 *
 * @author Mari
 */
public class serVivo {

    private int vida = 0;
    private int x = 0;
    private int y = 0;
    private String posicion = "";

    public serVivo(int vida, int x, int y, String posicion) {
        this.vida = vida;
        this.x = x;
        this.y = y;
        this.posicion = posicion;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.posicion = (this.x) + "," + (this.y);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.posicion = (this.x) + "," + (this.y);
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void recibirAtaque() {
        this.vida--;
        if (vida <= 0) {
            this.morir();
        }
    }

    public void morir() {
        setVida(0);
    }
}
