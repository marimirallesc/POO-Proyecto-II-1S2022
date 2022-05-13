package ProyectoI;

import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */
public class aliados extends serVivo{

    private ImageIcon icono = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/hongo.png"));
    
    public aliados(int vida, int x, int y, String posicion) {
        super(vida, x, y, posicion);
    }

    public ImageIcon getHongo() {
        return icono;
    }

    public void setHongo(ImageIcon icono) {
        this.icono = icono;
    }
    
}
