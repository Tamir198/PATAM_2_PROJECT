package patam2.agent.controller;

import patam2.agent.model.Model;
import patam2.agent.WindowController;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    public float startTime;
    public float endTime;
    public float from;
    public float to;
    public float maxAltitude;
    public float maxSpeed;
    public float aileron;
    public float elevator;
    public float rudder;
    public float throttle;

    Model model;
    WindowController windowController;
    public Controller(Model model,WindowController windowController){
        this.model = model;
        model.addObserver(this);
        this.windowController = windowController;
        windowController.addObserver(this);
    }

    public void getInstructions(String propertiesPath){

    }

    public void smooth(String propertiesPath){

    }

    // Sends a Json file to the backend
    public void sendRealTimeAnalytics(){

    }

    // Sends a Json file to the backend when flight ends
    public void sendFinalAnalytics(){

    }

    @Override
    public void update(Observable o, Object arg) {
        model.setFg_root(windowController.getRoot());
        model.setAileron(windowController.getAileron());
        model.setElevators(windowController.getElevator());
        model.setThrottle(windowController.getThrottle());
        model.setRudder(windowController.getRudder());
    }
}
