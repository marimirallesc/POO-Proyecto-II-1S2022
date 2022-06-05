package ProyectoII;

import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */
public class aliados extends serVivo{

    private ImageIcon icon = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/hongo.png"));
    
    public aliados(int vida, int x, int y, String posicion) {
        super(vida, x, y, posicion);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icono) {
        this.icon = icon;
    }
    
}
