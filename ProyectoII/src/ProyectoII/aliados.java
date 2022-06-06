package ProyectoII;

import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */

public class aliados extends serVivo{

    private ImageIcon icon = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/hongo.png"));
    private String posicionPP;
    ObserverAliados observer;
    
    public aliados(int vida, int x, int y, String posicion, String posicionP, Observable observable) {
        super(vida, x, y, posicion);
        this.posicionPP = posicionP;
        observer = new ObserverAliados(observable) {};
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icono) {
        this.icon = icon;
    }

    public String getPosicionPP() {
        return posicionPP;
    }

    public void setPosicionPP() {
        this.posicionPP = observer.update();
    }
    
}
