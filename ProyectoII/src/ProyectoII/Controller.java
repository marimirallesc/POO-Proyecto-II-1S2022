package ProyectoII;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Controller {

    public Controller(View vistaP, Model procedimientosP) throws Exception {
        vista = vistaP;
        procedimientos = procedimientosP;
        procedimientos.recibirController(this);
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
                procedimientos.movimientos(c);
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
        vista.actualizarEnemigos(String.valueOf(procedimientos.enemigosCantidadInicial));
        vista.actualizarAliados(String.valueOf(procedimientos.aliadosCantidadInicial));
        procedimientos.crearPersonaje(procedimientos.vida, procedimientos.posInicialX, procedimientos.posInicialY);
        vista.setPersonajePrinicpal(procedimientos.getPersonaje());
        procedimientos.factoryEnemigo(procedimientos.enemigosCantidadInicial);
        procedimientos.factoryAliado(procedimientos.aliadosCantidadInicial);
        procedimientos.imprimirEnemigos();
    }

    //Variables globales
    //MVC
    View vista;
    Model procedimientos;

}
