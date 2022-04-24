package patam2.agent.controller;

import patam2.agent.model.Model;
import patam2.agent.view.WindowController;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    Model model;
    WindowController windowController;
    public Controller(Model model,WindowController windowController){
        this.model = model;
        model.addObserver(this);
        this.windowController = windowController;
        windowController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: smoothing using a radius
        //model.setAileron(windowController.getAileron);
    }
}
