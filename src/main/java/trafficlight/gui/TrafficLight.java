package trafficlight.gui;


import trafficlight.Observer;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.State;

import java.awt.*;

public class TrafficLight extends Light implements Observer {
    int count = 0;

    public TrafficLight(Color color) {
        super(color);
    }

    public void turnOn(boolean a) {
        isOn = a;
        repaint();
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void update(Object o) {
        this.turnOn(!isOn);
    }
    //TODO implement a part of the pattern here
}
