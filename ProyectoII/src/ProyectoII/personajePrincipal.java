package ProyectoII;

import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */
public class personajePrincipal extends serVivo{

    private ImageIcon daisyLR = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/daisyLR.png"));
    private ImageIcon daisyRL = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/daisyRL.png"));
    private ImageIcon icon;
    private boolean direccion; 
    //true = hacia la derecha
    //false = hacia la izquierda
    Observable observable;
    
    public personajePrincipal(int vida, int x, int y, boolean direccionP, 
            String posicion) {
        super(vida, x, y, posicion);
        setDireccion(direccionP);
        observable = new Observable(getPosicion());
        observable.notifyObservers();
    }

    public ImageIcon getDaisyLR() {
        return daisyLR;
    }

    public ImageIcon getDaisyRL() {
        return daisyRL;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
        if (isDireccion()) {
            setIcon(getDaisyLR());
        } else
            setIcon(getDaisyRL());
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
}
