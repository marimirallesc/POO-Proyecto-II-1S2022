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
            if (!vista.isEmpty((ejeX), (ejeY2)) && vista.isAliado((ejeX), (ejeY2))) {
                procedimientos.getAliado(ejeX, ejeY2);
            } else if (!vista.isEmpty((ejeX), (ejeY2)) && !vista.isAliado((ejeX), (ejeY2))) {
                procedimientos.getEnemigo(ejeX, ejeY2);
            }
            vista.setIconNull(ejeX, ejeY2);
        }
        procedimientos.actualizarMovimientos(ejeX, ejeY);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        moverEnemigos();
        imprimirAliados();
        gameOver();
    }
    
    public void gameOver(){
        if (vida == 0) {
            JOptionPane.showMessageDialog(null, "Perdiste");
        } else if(enemigosCantidad == 0 && aliadosCantidad == 0){
            JOptionPane.showMessageDialog(null, "Ganaste");
        }
    }
    
//    public void anuncios(int num) {
//        this.setEnabled(false);
//        if (num == 2) {
//            if (gano) {
//                JOptionPane.showMessageDialog(this, "Ganaste");
//            } else {
//                JOptionPane.showMessageDialog(this, "Perdiste");
//            }
//            this.dispose();
//        }
//    }

    public void moverEnemigos() {
        int cantidad = procedimientos.goombas.size();
        int ejeXP = procedimientos.getPersonaje().getX();
        int ejeYP = procedimientos.getPersonaje().getY();
        for (int i = 0; i < cantidad; i++) {
            int ejeX = procedimientos.goombas.get(i).getX();
            int ejeY = procedimientos.goombas.get(i).getY();
            String posicion = "";
            vista.setIconNull(ejeX, ejeY);
            //do {
            if (ejeX != ejeXP) {
                if (ejeX > ejeXP) {
                    ejeX--;
                } else if (ejeX < ejeXP) {
                    ejeX++;
                }
            } else if (ejeY != ejeYP) {
                if (ejeY > ejeYP) {
                    ejeY--;
                } else if (ejeY < ejeYP) {
                    ejeY++;
                }
            }
            //posicion = ejeX + "," + ejeY;
            //System.out.println(i + ": " + posicion);
            //} while (procedimientos.posGoombas.contains(posicion));
            procedimientos.goombas.get(i).setX(ejeX);
            procedimientos.goombas.get(i).setY(ejeY);
            if (ejeX == ejeXP && ejeY == ejeYP) {
                procedimientos.getPersonaje().recibirAtaque();
                procedimientos.getEnemigo(ejeX, ejeY);
            }
            imprimirEnemigos();
        }
    }

    public void imprimirEnemigos() {
        int cantidad = procedimientos.goombas.size();
        vista.actualizarEnemigos(String.valueOf(cantidad));
        for (int i = 0; i < cantidad; i++) {
            vista.setEnemigo(procedimientos.goombas.get(i));
        }
    }

    public void imprimirAliados() {
        int cantidad = procedimientos.hongos.size();
        vista.actualizarAliados(String.valueOf(cantidad));
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
