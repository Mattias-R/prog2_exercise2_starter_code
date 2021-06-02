package trafficlight.ctrl;
import trafficlight.Subject;
import trafficlight.gui.TrafficLight;
import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

public class TrafficLightCtrl{

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;
    // static objekt. Das wird bei getInstance übergeben (Singleton pattern)
    private static TrafficLightCtrl ctrl = null;

    //war ursprünglich public, hab ich auf private umgestellt damit das Singleton pattern funktioniert
    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        //TODO useful to update the current state
        greenState.notifyObserver();
    }
    //Dadurch das der Construktor private ist kann nur noch über diese Methode ein TrafficLightCtrl erstellt werden
    public static TrafficLightCtrl getInstance() {
        if (ctrl == null) {
            ctrl = new TrafficLightCtrl();
        }
        return ctrl;
    }

    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                yellowState.notifyObserver();
                greenState.notifyObserver();
                System.out.println("grün");
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                //redState.notifyObserver();
                yellowState.notifyObserver();
                System.out.println("rot");
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    redState.notifyObserver();
                    yellowState.notifyObserver();
                    System.out.println("gelb1");
                    return redState;
                }else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    yellowState.notifyObserver();
                    redState.notifyObserver();
                    greenState.notifyObserver();
                    System.out.println("gelb2");
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public void stop() {
        doRun = false;
    }
}