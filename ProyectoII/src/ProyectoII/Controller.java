package ProyectoII;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Controller {

    public Controller(View vistaP, Model procedimientosP) throws Exception {
        vista = vistaP;
        procedimientos = procedimientosP;
        vista.addViewKeyListener(new listener());
        inicio();
    }

    class listener implements KeyListener {

        @Override
        public void keyPressed(java.awt.event.KeyEvent evt) {
            int id = evt.getID();
            int code = evt.getKeyCode();
            char c;
            if (id == KeyEvent.KEY_PRESSED) {
                switch (code) {
                    case 37:
                        c = (char) code;
                        break;
                    case 38:
                        c = (char) code;
                        break;
                    case 39:
                        c = (char) code;
                        break;
                    case 40:
                        c = (char) code;
                        break;
                    default:
                        c = evt.getKeyChar();
                        c = Character.toUpperCase(c);
                        break;
                }
                movimientos(c);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public void inicio() {
        vista.sizeJLabel();
        vista.crearTablero();
        vista.actualizarEnemigos(String.valueOf(enemigosCantidad));
        vista.actualizarAliados(String.valueOf(aliadosCantidad));
        procedimientos.crearPersonaje(vida, posInicialX, posInicialY);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        procedimientos.factoryEnemigo(enemigosCantidad);
        procedimientos.factoryAliado(aliadosCantidad);
        imprimirEnemigos();
        System.out.println(procedimientos.posGoombas);
    }

    public void movimientos(char c) {
        personajePrincipal personaje = procedimientos.getPersonaje();
        int ejeX = personaje.getX();
        int ejeY = personaje.getY();
        String posicion = (ejeX) + "," + (ejeY);
        vista.setIconNull(ejeX, ejeY);
        if (c == 'A' || c == 37) {
            if (ejeY > 0) {
                personaje.setDireccion(false);
                if (vista.isEmpty((ejeX), (ejeY - 1))) {
                    ejeY--;
                }
            }
        } else if (c == 'W' || c == 38) {
            if (ejeX > 0) {
                if (vista.isEmpty((ejeX - 1), (ejeY))) {
                    ejeX--;
                }
            }
        } else if (c == 'D' || c == 39) {
            if (ejeY < (vista.DimensionY - 1)) {
                personaje.setDireccion(true);
                if (vista.isEmpty((ejeX), (ejeY + 1))) {
                    ejeY++;
                }
            }
        } else if (c == 'S' || c == 40) {
            if (ejeX < (vista.DimensionX - 1)) {
                if (vista.isEmpty((ejeX + 1), (ejeY))) {
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
            if (!vista.isEmpty((ejeX), (ejeY2))) {
                if (vista.isAliado((ejeX), (ejeY2))) {
                    System.out.println("Rescate Aliado");
                    vista.setIconNull(ejeX, ejeY2);
                    procedimientos.getAliado(ejeX, ejeY2);
                } else if (!vista.isAliado((ejeX), (ejeY2))) {
                    System.out.println("Ataque Enemigo");
                    vista.setIconNull(ejeX, ejeY2);
                    procedimientos.getEnemigo(ejeX, ejeY2);
                }  
            }
        }
        procedimientos.actualizarMovimientos(ejeX, ejeY);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        //moverEnemigos();
        imprimirAliados();
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        gameOver();
    }

    public void gameOver() {
        //System.out.println(procedimientos.goombas.size());
        vista.actualizarEnemigos(String.valueOf(procedimientos.goombas.size()));
        vista.actualizarAliados(String.valueOf(procedimientos.hongos.size()));
        if (procedimientos.getPersonaje().getVida() == 0) {
            vista.anuncios(false);
        } else if (procedimientos.goombas.size() == 0
                && procedimientos.hongos.size() == 0) {
            vista.anuncios(true);
        }
    }

    public void moverEnemigos() {
        int cantidad = procedimientos.goombas.size();
        int ejeXP = procedimientos.getPersonaje().getX();
        int ejeYP = procedimientos.getPersonaje().getY();
        int ejeX = 0;
        int ejeY = 0;
        String posicion = "";
        System.out.println("****** " + cantidad + " ******");
        for (int i = 0; i < cantidad; i++) {
            try {
                ejeX = procedimientos.goombas.get(i).getX();
                ejeY = procedimientos.goombas.get(i).getY();
                vista.setIconNull(ejeX, ejeY);
                if (ejeX != ejeXP) {
                    if (ejeX > ejeXP) {
                        if (vista.isEmpty((ejeX - 1), (ejeY)) || vista.isPersonaje((ejeX - 1), (ejeY), procedimientos.getPersonaje().getIcon())) {
                            ejeX--;
                        }
                    } else if (ejeX < ejeXP) {
                        if (vista.isEmpty((ejeX + 1), (ejeY)) || vista.isPersonaje((ejeX + 1), (ejeY), procedimientos.getPersonaje().getIcon())) {
                            ejeX++;
                        }
                    }
                } else if (ejeY != ejeYP) {
                    if (ejeY > ejeYP) {
                        if (vista.isEmpty((ejeX), (ejeY - 1)) || vista.isPersonaje((ejeX), (ejeY - 1), procedimientos.getPersonaje().getIcon())) {
                            ejeY--;
                        }
                    } else if (ejeY < ejeYP) {
                        if (vista.isEmpty((ejeX), (ejeY + 1)) || vista.isPersonaje((ejeX), (ejeY + 1), procedimientos.getPersonaje().getIcon())) {
                            ejeY++;
                        }
                    }
                }
                posicion = ejeX + "," + ejeY;
                System.out.println(i + ": " + posicion);
                if (ejeX == ejeXP && ejeY == ejeYP) {
                    procedimientos.getEnemigo(ejeX, ejeY);
                    procedimientos.getPersonaje().recibirAtaque();
                    System.out.println("Ataque: " + procedimientos.goombas.size());
                }
                procedimientos.goombas.get(i).setX(ejeX);
                procedimientos.goombas.get(i).setY(ejeY);
                imprimirEnemigos();
            } catch (Exception e) {
                //break;
            }
        }
    }

    public void imprimirEnemigos() {
        int cantidad = procedimientos.goombas.size();
        for (int i = 0; i < cantidad; i++) {
            vista.setEnemigo(procedimientos.goombas.get(i));
        }
    }

    public void imprimirAliados() {
        int cantidad = procedimientos.hongos.size();
        int ejeXP = procedimientos.getPersonaje().getX();
        int ejeYP = procedimientos.getPersonaje().getY();
        for (int i = 0; i < cantidad; i++) {
            int ejeX = procedimientos.hongos.get(i).getX();
            int ejeY = procedimientos.hongos.get(i).getY();
            vista.setIconNull(ejeX, ejeY);
            if (((ejeX - 1) == ejeXP) || (ejeX == ejeXP) || ((ejeX + 1) == ejeXP)) {
                if (((ejeY - 1) == ejeYP) || (ejeY == ejeYP) || ((ejeY + 1) == ejeYP)) {
                    vista.setAliado(procedimientos.hongos.get(i));
                }
            }
        }
    }

    //Variables globales
    int vida = 5;
    int enemigosCantidad = 5;
    int aliadosCantidad = 3;
    int posInicialX = 5;
    int posInicialY = 7;
    //MVC
    View vista;
    Model procedimientos;

}
