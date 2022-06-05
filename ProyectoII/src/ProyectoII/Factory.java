package ProyectoII;

/**
 *
 * @author Mari
 */
public class Factory {
    
    public enemigos crearEnemigo(int vida, int x, int y){
        return new enemigos(vida, x, y, true, (x + "," + y));
    }
    
    public aliados crearAliado(int vida, int x, int y){
        return new aliados(vida, x, y, (x + "," + y));
    }
    
}
