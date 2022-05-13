package ProyectoI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends javax.swing.JFrame {

    public View() throws Exception {
        initComponents();
        setTitle("Proyecto II");
        setLocationRelativeTo(null);
        tablero = pnlTablero();
        jLCorazones();
    }

    public JPanel pnlTablero() {
        return panel;
    }

    public JFrame ventanaP() {
        return this;
    }

    public JLabel[] jLCorazones() {
        corazones = new JLabel[5];
        corazones[0] = c1;
        corazones[1] = c2;
        corazones[2] = c3;
        corazones[3] = c4;
        corazones[4] = c5;
        return corazones;
    }

    void addViewKeyListener(KeyListener evt) {
        this.addKeyListener(evt);
    }

    public void sizeJLabel() {
        //Asigna el tamaño de los labels según la dimensión del panel, sus columnas
        //y filas
        dimension = tablero.getSize(); //Dimensión del panel
        TableroX = dimension.width; //Ancho del panel
        TableroY = dimension.height; //Alto del panel
        TamX = TableroX / DimensionX; //Ancho del label
        TamY = TableroY / DimensionY; //Alto del label
        System.out.println("TamX: " + TamX);
        System.out.println("TamY: " + TamY);
    }

    public void crearTablero() {
        //Crea el tablero de labels
        int contadorX, contadorY;
        MatrizLabels = new JLabel[DimensionX][DimensionY];
        tablero.setLayout(new GridLayout(DimensionX, DimensionY));
        for (contadorX = 0; contadorX < DimensionX; contadorX++) {
            for (contadorY = 0; contadorY < DimensionY; contadorY++) {
                JLabel jLNuevo = new JLabel();
                jLNuevo.setSize(TamX, TamY);
                jLNuevo.setToolTipText(Integer.toString(contadorX) + "," + Integer.toString(contadorY));
                MatrizLabels[contadorX][contadorY] = jLNuevo;
                tablero.add(MatrizLabels[contadorX][contadorY]);
            }
        }
        setMatrizLabels(MatrizLabels);
    }

    public void vidaCorazones(int vida) {
        switch (vida) {
            case 0:
                corazones[0].setIcon(cGris);
                corazones[1].setIcon(cGris);
                corazones[2].setIcon(cGris);
                corazones[3].setIcon(cGris);
                corazones[4].setIcon(cGris);
                break;
            case 1:
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cGris);
                corazones[2].setIcon(cGris);
                corazones[3].setIcon(cGris);
                corazones[4].setIcon(cGris);
                break;
            case 2:
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cRojo);
                corazones[2].setIcon(cGris);
                corazones[3].setIcon(cGris);
                corazones[4].setIcon(cGris);
                break;
            case 3:
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cRojo);
                corazones[2].setIcon(cRojo);
                corazones[3].setIcon(cGris);
                corazones[4].setIcon(cGris);
                break;
            case 4:
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cRojo);
                corazones[2].setIcon(cRojo);
                corazones[3].setIcon(cRojo);
                corazones[4].setIcon(cGris);
                break;
            case 5:
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cRojo);
                corazones[2].setIcon(cRojo);
                corazones[3].setIcon(cRojo);
                corazones[4].setIcon(cRojo);
                break;
            default:
                break;
        }
    }

    public void setIconNull(int x, int y){
        MatrizLabels[x][y].setIcon(null);
    }
    
    public void setPersonajePrinicpal(personajePrincipal personaje) {
        int ejeX = personaje.getX();
        int ejeY = personaje.getY();
        int vida = personaje.getVida();
        ImageIcon icon = personaje.getIcon();
        MatrizLabels[ejeX][ejeY].setIcon(icon);
        vidaCorazones(vida);
    }

    public JLabel[][] getMatrizLabels() {
        return MatrizLabels;
    }

    public void setMatrizLabels(JLabel[][] MatrizLabels) {
        this.MatrizLabels = MatrizLabels;
    }

    public boolean isEmpty(int xP, int yP) {
        //Pregunta si un label está vacio
        if (MatrizLabels[xP][yP].getIcon() == null) {
            return true;
        }
        return false;
    }
    
//Variables globales
    //Interfaz
    JLabel[][] MatrizLabels; //Contiene todos los labels
    JLabel[] corazones; //Contiene todos los labels de los corazones
    JPanel tablero; //Panel en el que se imprimen los objetos
    JPanel vidas; //Panel en el que se imprimen las vidas
    Dimension dimension; //Dimensión del panel principal
    JFrame ventana; //Ventana del proyecto

    //Variables simples
    int DimensionX = 10; //Cantidad de columnas
    int DimensionY = 15; //Cantidad de filas
    int TableroX;// La anchura exacta del panel
    int TableroY;//La altura exacta del panel
    int TamX; //La anchura de los labels de acuerdo al panel
    int TamY; //La altura de los labels de acuerdo al panel
    int ejeX = 0; //Valor en el ejeX
    int ejeY = 0; //Valor en el ejeY

    //Iconos de corazones
    ImageIcon cRojo = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/cRojo.png"));
    ImageIcon cGris = new javax.swing.ImageIcon(getClass()
            .getResource("/Imagenes/cGris.png"));

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        pnlVidas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        instrucciones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setSize(new java.awt.Dimension(800, 800));

        panel.setBackground(new java.awt.Color(255, 204, 153));
        panel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1154, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlVidas.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Vida");

        c1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        c2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        c3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        c4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        c5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout pnlVidasLayout = new javax.swing.GroupLayout(pnlVidas);
        pnlVidas.setLayout(pnlVidasLayout);
        pnlVidasLayout.setHorizontalGroup(
            pnlVidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVidasLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlVidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnlVidasLayout.setVerticalGroup(
            pnlVidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVidasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        instrucciones.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout instruccionesLayout = new javax.swing.GroupLayout(instrucciones);
        instrucciones.setLayout(instruccionesLayout);
        instruccionesLayout.setHorizontalGroup(
            instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        instruccionesLayout.setVerticalGroup(
            instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(instrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlVidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(instrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JPanel instrucciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnlVidas;
    // End of variables declaration//GEN-END:variables

}
