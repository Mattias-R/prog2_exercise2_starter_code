package trafficlight;

import trafficlight.gui.TrafficLight;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(TrafficLight observer){
        observers.remove(observer);
    }
    public void notifyObserver(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }

}
