package ProyectoII;

import java.util.ArrayList;

/**
 *
 * @author Mari
 */
public class Model {

    public Model() throws Exception {
        factory = new Factory();
    }

    public int random(int min, int max) { //Retorna un número random
        int num = (int) (Math.random() * (max - min)) + min;
        return num;
    }

    public void crearPersonaje(int vida, int x, int y) {
        setPersonaje(new personajePrincipal(vida, x, y, true, (x + "," + y)));
        todo.add(personaje.getPosicion());
    }

    public void actualizarMovimientos(int x, int y) {
        personaje.setX(x);
        personaje.setY(y);
    }

    public void getEnemigo(int x, int y) {
        int cantidad = goombas.size();
        for (int i = 0; i < cantidad; i++) {
            int ejeX = goombas.get(i).getX();
            int ejeY = goombas.get(i).getY();
            if (x == ejeX && y == ejeY) {
                System.out.println("getEnemigo: " + goombas.size());
                goombas.remove(goombas.get(i));
                break;
            }
        }
    }
    
    public void getAliado(int x, int y) {
        int cantidad = hongos.size();
        for (int i = 0; i < cantidad; i++) {
            int ejeX = hongos.get(i).getX();
            int ejeY = hongos.get(i).getY();
            if (x == ejeX && y == ejeY) {
                System.out.println("getAliado: " + hongos.size());
                personaje.recibirVida();
                hongos.remove(hongos.get(i));
                break;
            }
        }
    }

    public void factoryEnemigo(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            do {
                ejeX = random(0, 10);
                ejeY = random(0, 15);
                posicion = ejeX + "," + ejeY;
            } while (todo.contains(posicion));
            enemigos goomba = factory.crearEnemigo(1, ejeX, ejeY);
            goombas.add(goomba);
            todo.add(goomba.getPosicion());
        }
    }

    public void factoryAliado(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            do {
                ejeX = random(0, 10);
                ejeY = random(0, 15);
                posicion = ejeX + "," + ejeY;
            } while (todo.contains(posicion));
            aliados hongo = factory.crearAliado(1, ejeX, ejeY);
            hongos.add(hongo);
            posGoombas.add(hongo.getPosicion());
            todo.add(hongo.getPosicion());
        }
    }

    //Variables globales
    String posicion = ""; //Posicion de un objeto
    Factory factory;
    int ejeX = 0;
    int ejeY = 0;

    //ArrayList <String> Estos arrays son para tener el control de las 
    //coordenadas
    ArrayList<aliados> hongos = new ArrayList<aliados>();
    ArrayList<enemigos> goombas = new ArrayList<enemigos>();
    ArrayList<String> posGoombas = new ArrayList<String>();
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
