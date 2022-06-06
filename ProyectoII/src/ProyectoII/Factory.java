package ProyectoII;

/**
 *
 * @author Mari
 */
public class Factory {
    
    public enemigos crearEnemigo(int vida, int x, int y, String posicionPP, Observable observable){
        return new enemigos(vida, x, y, true, (x + "," + y), posicionPP, observable);
    }
    
    public aliados crearAliado(int vida, int x, int y, String posicionPP, Observable observable){
        return new aliados(vida, x, y, (x + "," + y), posicionPP, observable);
    }
    
}
