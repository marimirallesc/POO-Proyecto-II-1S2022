package ProyectoII;

/**
 *
 * @author Mari
 */
public abstract class Observer {

    protected Observable subject;
    
    public String update() {
        return subject.getState();
    }

}
