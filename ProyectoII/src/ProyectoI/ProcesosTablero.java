package ProyectoI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import static java.lang.constant.ConstantDescs.NULL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProcesosTablero {

    public ProcesosTablero(JPanel pnlTablero, JFrame ventanaP) throws Exception {
        panel = pnlTablero;
        ventana = ventanaP;
        sizeJLabel();
        crearTablero();
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        int id = evt.getID();
        int code = evt.getKeyCode();
        char c;
        if (id == KeyEvent.KEY_PRESSED) {
            if (code == 37) {
                c = (char) code;
            } else if (code == 38) {
                c = (char) code;
            } else if (code == 39) {
                c = (char) code;
            } else if (code == 40) {
                c = (char) code;
            } else {
                c = evt.getKeyChar();
                c = Character.toUpperCase(c);
            }
            procedimientos(c);
        }
    }

    public int random(int min, int max) { //Retorna un número random
        int num = (int) (Math.random() * (max - min)) + min;
        return num;
    }

    private void sizeJLabel() {
        //Asigna el tamaño de los labels según la dimensión del panel, sus columnas
        //y filas
        dimension = panel.getSize(); //Dimensión del panel
        TableroX = dimension.width; //Ancho del panel
        TableroY = dimension.height; //Alto del panel
        TamX = TableroX / DimensionX; //Ancho del label
        TamY = TableroY / DimensionY; //Alto del label
        System.out.println("TamX: " + TamX);
        System.out.println("TamY: " + TamY);
    }

    private void crearTablero() {
        //Crea el tablero de labels
        int contadorX, contadorY;
        MatrizLabels = new JLabel[DimensionX][DimensionY];
        panel.setLayout(new GridLayout(DimensionX, DimensionY));
        for (contadorX = 0; contadorX < DimensionX; contadorX++) {
            for (contadorY = 0; contadorY < DimensionY; contadorY++) {
                JLabel jLNuevo = new JLabel();
                jLNuevo.setSize(TamX, TamY);
                jLNuevo.setToolTipText(Integer.toString(contadorX) + "," + Integer.toString(contadorY));
                jLNuevo.setText("X");
                MatrizLabels[contadorX][contadorY] = jLNuevo;
                panel.add(MatrizLabels[contadorX][contadorY]);
            }
        }
    }

    private void procedimientos(char c) {
        int num = c;
        System.out.println("num: " + num);
        System.out.println("c: " + c);
        switch (c) {
            case 'W':
                break;
            case 'S':
                break;
            case 'A':
                break;
            case 'D':
                break;
            case 37:
                System.out.println("LEFT");
                break;
            case 38:
                System.out.println("UP");
                break;
            case 39:
                System.out.println("RIGHT");
                break;
            case 40:
                System.out.println("DOWN");
                break;
            default:
                break;
        }
    }

//Variables globales
//00 = Lado superior izquierdo
//01 = Lado superior derecho
//10 = Lado inferior izquierdo
//11 = Lado inferior derecho
//Interfaz
    JLabel[][] MatrizLabels; //Contiene todos los labels
    JPanel panel; //Panel en el que se imprimen los objetos
    JFrame ventana; //Ventana del proyecto
    Dimension dimension; //Dimensión del panel principal

    //Variables simples
    int size = 10; //Para simplificar porque ambas dimensiones son 50
    int DimensionX = size; //Cantidad de columnas
    int DimensionY = size; //Cantidad de filas
    int TableroX;// La anchura exacta del panel = 1335; 
    int TableroY;//La altura exacta del panel = 845;
    int TamX; //La anchura de los labels de acuerdo al panel
    int TamY; //La altura de los labels de acuerdo al panel
    int ejeX = 0; //Valor en el ejeX
    int ejeY = 0; //Valor en el ejeY
    int veces = 0; //Veces en las que se han impreso los recursos y amenazas
    String posicion = ""; //Posicion de un objeto

    //ArrayList <String> Estos arrays son para tener el control de las 
    //coordenadas
    ArrayList<String> todo = new ArrayList<String>();
    //Contiene todas las coordenadas que no son vacías (contiene un icono)

}
