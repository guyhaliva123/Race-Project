package utilities;

import java.util.Vector;

public class Observable{
    Vector<Observer> list = new Vector<Observer>();
    public void registerObserver(Observer ob){
        list.add(ob);
    }
    public void unregisterObserver(Observer ob){
        list.remove(ob);
    }
    public void notifyObservers(){
        for (Observer ob : list)
            ob.update(this);
    }
}
