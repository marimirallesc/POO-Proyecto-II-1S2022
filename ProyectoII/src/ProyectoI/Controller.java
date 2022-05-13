package ProyectoI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
//        tablero = vista.pnlTablero();
//        corazones = vista.jLCorazones();
//        ventana = vista.ventanaP();
        vista.sizeJLabel();
        vista.crearTablero();
        int vida = 5;
        int x = 5;
        int y = 0;
        procedimientos.crearPersonaje(vida, x, y);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
    }

    public void movimientos(char c) {
        personajePrincipal personaje = procedimientos.getPersonaje();
        int ejeX = personaje.getX();
        int ejeY = personaje.getY();
        String posicion = (ejeX) + "," + (ejeY);
        vista.setIconNull(ejeX, ejeY);
        if (c == 'A' || c == 37) {
            if (ejeY > 0) {
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
            System.out.println("ENTER");
            System.out.println("SPACE");
        }
        procedimientos.actualizarMovimientos(ejeX, ejeY);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
    }

//Variables globales
    //Model
    View vista;
    Model procedimientos;

}
