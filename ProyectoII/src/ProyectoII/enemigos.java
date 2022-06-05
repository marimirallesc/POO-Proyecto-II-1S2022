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
    //true = hacia la derecha
    //false = hacia la izquierda
    
    public enemigos(int vida, int x, int y, boolean direccionP, 
            String posicion) {
        super(vida, x, y, posicion);
        setDireccion(direccionP);
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

}
