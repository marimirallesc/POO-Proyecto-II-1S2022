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

    public void recibirController(Controller controllerP){
        this.controller = controllerP;
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
        personaje.observable.setState(personaje.getPosicion());
        personaje.observable.notifyObservers();
        for (int i = 0; i < goombas.size(); i++) {
            goombas.get(i).setPosicionPP();
        }
        for (int i = 0; i < hongos.size(); i++) {
            hongos.get(i).setPosicionPP();
        }
    }

    public void getEnemigo(int x, int y) {
        int cantidad = goombas.size();
        for (int i = 0; i < cantidad; i++) {
            int ejeX = goombas.get(i).getX();
            int ejeY = goombas.get(i).getY();
            if (x == ejeX && y == ejeY) {
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
            enemigos goomba = factory.crearEnemigo(1, ejeX, ejeY, personaje.getPosicion(), personaje.observable);
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
            aliados hongo = factory.crearAliado(1, ejeX, ejeY, personaje.getPosicion(), personaje.observable);
            hongos.add(hongo);
            posGoombas.add(hongo.getPosicion());
            todo.add(hongo.getPosicion());
        }
    }
    
    public void movimientos(char c) {
        personajePrincipal personaje = getPersonaje();
        int ejeX = personaje.getX();
        int ejeY = personaje.getY();
        String posicion = (ejeX) + "," + (ejeY);
        controller.vista.setIconNull(ejeX, ejeY);
        if (c == 'A' || c == 37) {
            if (ejeY > 0) {
                personaje.setDireccion(false);
                if (controller.vista.isEmpty((ejeX), (ejeY - 1))) {
                    ejeY--;
                }
            }
        } else if (c == 'W' || c == 38) {
            if (ejeX > 0) {
                if (controller.vista.isEmpty((ejeX - 1), (ejeY))) {
                    ejeX--;
                }
            }
        } else if (c == 'D' || c == 39) {
            if (ejeY < (controller.vista.DimensionY - 1)) {
                personaje.setDireccion(true);
                if (controller.vista.isEmpty((ejeX), (ejeY + 1))) {
                    ejeY++;
                }
            }
        } else if (c == 'S' || c == 40) {
            if (ejeX < (controller.vista.DimensionX - 1)) {
                if (controller.vista.isEmpty((ejeX + 1), (ejeY))) {
                    ejeX++;
                }
            }
        } else if (c == 10 || c == 32) {
            int ejeY2 = ejeY;
            if (!personaje.isDireccion()) {
                ejeY2--;
            } else {
                ejeY2++;

            }
            if (!controller.vista.isEmpty((ejeX), (ejeY2))) {
                if (controller.vista.isAliado((ejeX), (ejeY2))) {
                    controller.vista.setIconNull(ejeX, ejeY2);
                    getAliado(ejeX, ejeY2);
                } else if (!controller.vista.isAliado((ejeX), (ejeY2))) {
                    controller.vista.setIconNull(ejeX, ejeY2);
                    getEnemigo(ejeX, ejeY2);
                }
            }
        }
        actualizarMovimientos(ejeX, ejeY);
        controller.vista.setPersonajePrinicpal(getPersonaje());
        moverEnemigos();
        imprimirAliados();
        controller.vista.setPersonajePrinicpal(getPersonaje());
        turno++;
        agregarEA();
        gameOver();
    }

    public void agregarEA() {
        if (turno % 4 == 0) {
            factoryEnemigo(1);
            imprimirEnemigos();
            if (aliadosCantidad > aliadosCantidadInicial) {
                factoryAliado(1);
                aliadosCantidad--;
                imprimirAliados();
            }
        }
    }

    public void gameOver() {
        controller.vista.actualizarEnemigos(String.valueOf(goombas.size()));
        controller.vista.actualizarAliados(String.valueOf(hongos.size()));
        if (getPersonaje().getVida() == 0) {
            controller.vista.anuncios(false);
        }
    }

    public void moverEnemigos() {
        int cantidad = goombas.size();
        int ejeXP = 0;
        int ejeYP = 0;
        int ejeX = 0;
        int ejeY = 0;
        String posicion = "";
        for (int i = 0; i < cantidad; i++) {
            try {
                String string = goombas.get(i).getPosicionPP();
                String[] partes = string.split(",");
                String parte1 = partes[0];
                String parte2 = partes[1];
                ejeXP = Integer.parseInt(parte1);
                ejeYP = Integer.parseInt(parte2);
                ejeX = goombas.get(i).getX();
                ejeY = goombas.get(i).getY();
                controller.vista.setIconNull(ejeX, ejeY);
                if (ejeX != ejeXP) {
                    if (ejeX > ejeXP) {
                        if (controller.vista.isEmpty((ejeX - 1), (ejeY)) || controller.vista.isPersonaje((ejeX - 1), (ejeY), getPersonaje().getIcon())) {
                            ejeX--;
                        }
                    } else if (ejeX < ejeXP) {
                        if (controller.vista.isEmpty((ejeX + 1), (ejeY)) || controller.vista.isPersonaje((ejeX + 1), (ejeY), getPersonaje().getIcon())) {
                            ejeX++;
                        }
                    }
                } else if (ejeY != ejeYP) {
                    if (ejeY > ejeYP) {
                        if (controller.vista.isEmpty((ejeX), (ejeY - 1)) || controller.vista.isPersonaje((ejeX), (ejeY - 1), getPersonaje().getIcon())) {
                            ejeY--;
                        }
                    } else if (ejeY < ejeYP) {
                        if (controller.vista.isEmpty((ejeX), (ejeY + 1)) || controller.vista.isPersonaje((ejeX), (ejeY + 1), getPersonaje().getIcon())) {
                            ejeY++;
                        }
                    }
                }
                goombas.get(i).setX(ejeX);
                goombas.get(i).setY(ejeY);
                if (ejeX == ejeXP && ejeY == ejeYP) {
                    getEnemigo(ejeX, ejeY);
                    getPersonaje().recibirAtaque();
                }
                imprimirEnemigos();
            } catch (Exception e) {
            }
        }
    }

    public void imprimirEnemigos() {
        int cantidad = goombas.size();
        for (int i = 0; i < cantidad; i++) {
            controller.vista.setEnemigo(goombas.get(i));
        }
    }
    
    public void imprimirAliados() {
        int cantidad = hongos.size();
        int ejeXP = 0;
        int ejeYP = 0;
        int ejeX = 0;
        int ejeY = 0;
        for (int i = 0; i < cantidad; i++) {
            String string = hongos.get(i).getPosicionPP();
            String[] partes = string.split(",");
            String parte1 = partes[0];
            String parte2 = partes[1];
            ejeXP = Integer.parseInt(parte1);
            ejeYP = Integer.parseInt(parte2);
            ejeX = hongos.get(i).getX();
            ejeY = hongos.get(i).getY();
            controller.vista.setIconNull(ejeX, ejeY);
            if (((ejeX - 1) == ejeXP) || (ejeX == ejeXP) || ((ejeX + 1) == ejeXP)) {
                if (((ejeY - 1) == ejeYP) || (ejeY == ejeYP) || ((ejeY + 1) == ejeYP)) {
                    controller.vista.setAliado(hongos.get(i));
                }
            }
        }
    }

    //Variables globales
    String posicion = ""; //Posicion de un objeto
    Factory factory;
    Controller controller;
    int ejeX = 0;
    int ejeY = 0;
    int vida = 5;
    int aliadosCantidad = 6;
    int enemigosCantidadInicial = 5;
    int aliadosCantidadInicial = 3;
    int posInicialX = 5;
    int posInicialY = 7;
    int turno = 0;

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
