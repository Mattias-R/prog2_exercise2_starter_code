package TrafficLight;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.gui.TrafficLight;
import trafficlight.states.State;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class checkTrafficlight {

    @Test
    @DisplayName("Test if Singleton returns the same object")
    public void checkSingleton(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        assertEquals(ctrl,TrafficLightCtrl.getInstance());
    }
    @Test
    @DisplayName("Test if Singleton returns the same object")
    public void checkGreenLight(){
        TrafficLight green = new TrafficLight(Color.green);
        assertTrue(!green.isOn());
    }
    @Test
    @DisplayName("check if green light is on after notifyObserver")
    public void checkGreenLight2(){
        TrafficLight green = new TrafficLight(Color.green);
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getGreenState();
        state.addObserver(green);
        state.notifyObserver();
        assertTrue(green.isOn());
    }
    @Test
    @DisplayName("Test if Singleton returns the same object")
    public void checkYellowLight(){
        TrafficLight yellow = new TrafficLight(Color.yellow);
        assertTrue(!yellow.isOn());
    }

    @Test
    @DisplayName("check if green light is on after notifyObserver")
    public void checkYellowLight2(){
        TrafficLight yellow = new TrafficLight(Color.yellow);
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getYellowState();
        state.addObserver(yellow);
        state.notifyObserver();
        assertTrue(yellow.isOn());
    }
    @Test
    @DisplayName("check if green light is on after notifyObserver")
    public void checkRedLight(){
        TrafficLight red = new TrafficLight(Color.red);
        assertTrue(!red.isOn());
    }
    @Test
    @DisplayName("check if green light is on after notifyObserver")
    public void checkRedLight2(){
        TrafficLight red = new TrafficLight(Color.red);
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getYellowState();
        state.addObserver(red);
        state.notifyObserver();
        assertTrue(red.isOn());
    }

}
