package ProyectoII;

import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */
public class enemigos extends serVivo{

    private ImageIcon goombaLR = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/goombaLR.png"));
    private ImageIcon goombaRL = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/goombaRL.png"));
    private ImageIcon icon;
    private boolean direccion;
    private String posicionPP;
    //true = hacia la derecha
    //false = hacia la izquierda
    ObserverEnemigos observer;
    
    public enemigos(int vida, int x, int y, boolean direccionP, 
            String posicion, String posicionP, Observable observable) {
        super(vida, x, y, posicion);
        setDireccion(direccionP);
        this.posicionPP = posicionP;
        observer = new ObserverEnemigos(observable) {};
    }

    public ImageIcon getGoombaLR() {
        return goombaLR;
    }

    public ImageIcon getGoombaRL() {
        return goombaRL;
    }
    
    public boolean isDireccion() {
        return direccion;
    }
    
    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
        if (isDireccion()) {
            setIcon(getGoombaLR());
        } else {
            setIcon(getGoombaRL());
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getPosicionPP() {
        return posicionPP;
    }

    public void setPosicionPP(String posicionPP) {
        this.posicionPP = observer.update();
    }

}
