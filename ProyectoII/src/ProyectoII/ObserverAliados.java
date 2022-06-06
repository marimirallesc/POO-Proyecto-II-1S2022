package ProyectoII;

/**
 *
 * @author Mari
 */
public abstract class ObserverAliados extends Observer{

    protected Observable subject;

    public ObserverAliados(Observable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    public String update() {
        return subject.getState();
    }

}
