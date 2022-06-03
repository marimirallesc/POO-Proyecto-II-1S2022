package ProyectoI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

    public Controller(View vistaP, Model procedimientosP) throws Exception {
        vista = vistaP;
        procedimientos = procedimientosP;
        vista.addViewKeyListener(new listener());
        inicio();
        Enemigos();
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
        int vida = 5;
        int x = 5;
        int y = 7;
        procedimientos.crearPersonaje(vida, x, y);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        procedimientos.factoryEnemigo(5);
        procedimientos.factoryAliado(3);
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
            if (vista.isAliado((ejeX), (ejeY2))) {

            }
            if (!vista.isEmpty((ejeX), (ejeY2)) && !vista.isAliado((ejeX), (ejeY2))) {

            }
        }
        procedimientos.actualizarMovimientos(ejeX, ejeY);
        Enemigos();
        Aliados();
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
    }

    public void Enemigos() {
        int cantidad = procedimientos.goombas.size();
        for (int i = 0; i < cantidad; i++) {
            vista.setEnemigo(procedimientos.goombas.get(i));
        }
    }

    public void Aliados() {
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
    //Model
    View vista;
    Model procedimientos;

}
