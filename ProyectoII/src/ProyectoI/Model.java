package ProyectoI;

import java.util.ArrayList;
/**
 *
 * @author Mari
 */
public class Model {
    
    public Model() throws Exception {
    }
    
    public int random(int min, int max) { //Retorna un número random
        int num = (int) (Math.random() * (max - min)) + min;
        return num;
    }
    
    public void crearPersonaje(int vida, int x, int y){
        setPersonaje(new personajePrincipal(vida, x, y, true, (x + "," + y)));
    }

    public void actualizarMovimientos(int x, int y) {
        personaje.setX(x);
        personaje.setY(y);
    }
    
//Variables globales
    String posicion = ""; //Posicion de un objeto

    //ArrayList <String> Estos arrays son para tener el control de las 
    //coordenadas
    ArrayList<String> todo = new ArrayList<String>();
    //Contiene todas las coordenadas que no son vacías (contiene un icono)
    
    private personajePrincipal personaje;

    public personajePrincipal getPersonaje() {
        return personaje;
    }

    public void setPersonaje(personajePrincipal personaje) {
        this.personaje = personaje;
    }
}
