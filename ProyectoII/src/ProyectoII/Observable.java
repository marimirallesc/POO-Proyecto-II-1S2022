package ProyectoII;

import java.util.LinkedList;

/**
 *
 * @author Mari
 */
public class Observable{

    LinkedList<Observer> observers;
    private String state;
    
    public Observable(String state) {
        observers = new LinkedList();
        setState(state);
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
        System.out.println("\nCurrent State: " + state + "\n");
        notifyObservers();
    }
    
    public void registerObserver(Observer...observers){
        for(Observer o: observers)
            this.observers.add(o);
    }
    
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    public void notifyObservers(){
        for(Observer o: observers)
            o.update();
    }
    
}
