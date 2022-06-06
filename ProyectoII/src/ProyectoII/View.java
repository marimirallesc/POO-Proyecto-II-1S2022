package ProyectoII;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends javax.swing.JFrame {

    public View() throws Exception {
        initComponents();
        setTitle("Proyecto II");
        setLocationRelativeTo(null);
        tablero = pnlTablero();
        jLCorazones();
    }

    public void anuncios(boolean gano) {
        this.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Perdiste");
        this.dispose();
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
                corazones[0].setIcon(cRojo);
                corazones[1].setIcon(cRojo);
                corazones[2].setIcon(cRojo);
                corazones[3].setIcon(cRojo);
                corazones[4].setIcon(cRojo);
                break;
        }
    }

    public void actualizarEnemigos(String texto) {
        jTFEnemigos.setEditable(false);
        jTFEnemigos.setEnabled(false);
        jTFEnemigos.setText(texto);
    }

    public void actualizarAliados(String texto) {
        jTFAliados.setEditable(false);
        jTFAliados.setEnabled(false);
        jTFAliados.setText(texto);
    }

    public void setIconNull(int x, int y) {
        MatrizLabels[x][y].setIcon(null);
    }

    public void setPersonajePrinicpal(personajePrincipal personaje) {
        int ejeX = personaje.getX();
        int ejeY = personaje.getY();
        int vida = personaje.getVida();
        ImageIcon icon = personaje.getIcon();
        vidaCorazones(vida);
        if (vida > 0) {
            MatrizLabels[ejeX][ejeY].setIcon(icon);
        }
    }

    public void setEnemigo(enemigos goomba) {
        int ejeX = goomba.getX();
        int ejeY = goomba.getY();
        ImageIcon icon = goomba.getIcon();
        MatrizLabels[ejeX][ejeY].setIcon(icon);
    }

    public void setAliado(aliados hongo) {
        int ejeX = hongo.getX();
        int ejeY = hongo.getY();
        ImageIcon icon = hongo.getIcon();
        MatrizLabels[ejeX][ejeY].setIcon(icon);
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

    public boolean isAliado(int xP, int yP) {
        ImageIcon hongo = new javax.swing.ImageIcon(getClass()
                .getResource("/Imagenes/hongo.png"));
        if (MatrizLabels[xP][yP].getIcon().toString().equals(hongo.toString())) {
            return true;
        }
        return false;
    }

    public boolean isPersonaje(int xP, int yP, ImageIcon icono) {
        //Pregunta si un label tiene un aliado
        if (MatrizLabels[xP][yP].getIcon().toString().equals(icono.toString())) {
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
        Instrucciones = new javax.swing.JLabel();
        fArriba = new javax.swing.JLabel();
        Arriba = new javax.swing.JLabel();
        W = new javax.swing.JLabel();
        Abajo = new javax.swing.JLabel();
        S = new javax.swing.JLabel();
        fAbajo = new javax.swing.JLabel();
        Izquierda = new javax.swing.JLabel();
        A = new javax.swing.JLabel();
        fIzquierdo = new javax.swing.JLabel();
        Derecha = new javax.swing.JLabel();
        D = new javax.swing.JLabel();
        fDerecho = new javax.swing.JLabel();
        Atacar = new javax.swing.JLabel();
        Enter = new javax.swing.JLabel();
        Rescatar = new javax.swing.JLabel();
        Espacio = new javax.swing.JLabel();
        Rescatar1 = new javax.swing.JLabel();
        Rescatar2 = new javax.swing.JLabel();
        jTFEnemigos = new javax.swing.JTextField();
        jTFAliados = new javax.swing.JTextField();

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
        jLabel1.setText("Vida:");

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
        instrucciones.setPreferredSize(new java.awt.Dimension(183, 381));

        Instrucciones.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Instrucciones.setText("Instrucciones:");

        fArriba.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        fArriba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fArriba.png"))); // NOI18N

        Arriba.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Arriba.setText("Arriba:");

        W.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        W.setText("W");

        Abajo.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Abajo.setText("Abajo:");

        S.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        S.setText("S ");

        fAbajo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        fAbajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fAbajo.png"))); // NOI18N

        Izquierda.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Izquierda.setText("Izquierda:");

        A.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        A.setText("A ");

        fIzquierdo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        fIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fIzquierda.png"))); // NOI18N

        Derecha.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Derecha.setText("Derecha:");

        D.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        D.setText("D ");

        fDerecho.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        fDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fDerecha.png"))); // NOI18N

        Atacar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Atacar.setText("Atacar o");

        Enter.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        Enter.setText("Enter");

        Rescatar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Rescatar.setText("Rescatar:");

        Espacio.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        Espacio.setText("Espacio");

        Rescatar1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Rescatar1.setText("Enemigos:");

        Rescatar2.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        Rescatar2.setText("Aliados:");

        jTFEnemigos.setEditable(false);
        jTFEnemigos.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jTFEnemigos.setText("0");

        jTFAliados.setEditable(false);
        jTFAliados.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jTFAliados.setText("0");

        javax.swing.GroupLayout instruccionesLayout = new javax.swing.GroupLayout(instrucciones);
        instrucciones.setLayout(instruccionesLayout);
        instruccionesLayout.setHorizontalGroup(
            instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instruccionesLayout.createSequentialGroup()
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instruccionesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Abajo)
                                .addGroup(instruccionesLayout.createSequentialGroup()
                                    .addComponent(S)
                                    .addGap(20, 20, 20)
                                    .addComponent(fAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Arriba)
                                .addGroup(instruccionesLayout.createSequentialGroup()
                                    .addComponent(W)
                                    .addGap(20, 20, 20)
                                    .addComponent(fArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Izquierda)
                                .addGroup(instruccionesLayout.createSequentialGroup()
                                    .addComponent(A)
                                    .addGap(20, 20, 20)
                                    .addComponent(fIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(instruccionesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Derecha)
                            .addGroup(instruccionesLayout.createSequentialGroup()
                                .addComponent(D)
                                .addGap(16, 16, 16)
                                .addComponent(fDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Atacar)
                            .addComponent(Rescatar)
                            .addComponent(Enter)
                            .addComponent(Espacio)))
                    .addGroup(instruccionesLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(Instrucciones))
                    .addGroup(instruccionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Rescatar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFEnemigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(instruccionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Rescatar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFAliados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        instruccionesLayout.setVerticalGroup(
            instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instruccionesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Instrucciones)
                .addGap(14, 14, 14)
                .addComponent(Arriba)
                .addGap(0, 0, 0)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(W))
                .addGap(14, 14, 14)
                .addComponent(Abajo)
                .addGap(0, 0, 0)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S))
                .addGap(14, 14, 14)
                .addComponent(Izquierda)
                .addGap(0, 0, 0)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A))
                .addGap(14, 14, 14)
                .addComponent(Derecha)
                .addGap(0, 0, 0)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D))
                .addGap(14, 14, 14)
                .addComponent(Atacar)
                .addGap(0, 0, 0)
                .addComponent(Rescatar)
                .addGap(0, 0, 0)
                .addComponent(Enter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Espacio)
                .addGap(20, 20, 20)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Rescatar1)
                    .addComponent(jTFEnemigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(instruccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Rescatar2)
                    .addComponent(jTFAliados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(instrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlVidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(instrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel A;
    private javax.swing.JLabel Abajo;
    private javax.swing.JLabel Arriba;
    private javax.swing.JLabel Atacar;
    private javax.swing.JLabel D;
    private javax.swing.JLabel Derecha;
    private javax.swing.JLabel Enter;
    private javax.swing.JLabel Espacio;
    private javax.swing.JLabel Instrucciones;
    private javax.swing.JLabel Izquierda;
    private javax.swing.JLabel Rescatar;
    private javax.swing.JLabel Rescatar1;
    private javax.swing.JLabel Rescatar2;
    private javax.swing.JLabel S;
    private javax.swing.JLabel W;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel fAbajo;
    private javax.swing.JLabel fArriba;
    private javax.swing.JLabel fDerecho;
    private javax.swing.JLabel fIzquierdo;
    private javax.swing.JPanel instrucciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTFAliados;
    private javax.swing.JTextField jTFEnemigos;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnlVidas;
    // End of variables declaration//GEN-END:variables

}
