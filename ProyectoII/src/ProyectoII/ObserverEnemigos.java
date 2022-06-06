package ProyectoII;

/**
 *
 * @author Mari
 */
public abstract class ObserverEnemigos extends Observer{

    protected Observable subject;
    
    public ObserverEnemigos(Observable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    public String update() {
        return subject.getState();
    }

}
