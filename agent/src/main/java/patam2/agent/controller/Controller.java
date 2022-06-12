package patam2.agent.controller;

import patam2.agent.model.Model;
import patam2.agent.view.WindowController;

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
        // TODO: smoothing using a radius
    }

    // Sends a Json file to the backend
    public void sendRealTimeAnalytics(){

    }

    // Sends a Json file to the backend when flight ends
    public void sendFinalAnalytics(){

    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: update
        //model.setAileron(windowController.getAileron);
    }
}
