package ProyectoI;

/**
 *
 * @author Mari
 */
public class mainMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        View vista = new View();        
        Model procedimientos = new Model();        
        Controller tablero = new Controller(vista, procedimientos);
        
        vista.setVisible(true);
    }
    
}
