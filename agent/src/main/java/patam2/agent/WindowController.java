package patam2.agent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import java.util.Observable;

public class WindowController extends Observable {

    @FXML
    Canvas joystick;
    @FXML
    Slider slideThrottle;
    @FXML
    Slider slideRudder;

    // mouse clicked or released
    boolean mousePushed;
    // to show mouse move on joystick
    double jx,jy;
    // middle of canvas x,y
    double mx, my;

    // aileron = , elevator = going up and down (adjusting height) , rudder = steering left and right, throttle = speed
    double aileron, elevator,rudder,throttle;

    public WindowController(){
        mousePushed = false;
        jx = 0;
        jy = 0;
        aileron = 0;
        elevator = 0;
        rudder = 0;
        throttle = 0;
    }

    public double getAileron() {
        return aileron;
    }

    public double getElevator() {
        return elevator;
    }

    public double getRudder() {
        return rudder;
    }

    public double getThrottle() {
        return throttle;
    }

    public String getRoot() {
        return "src/main/resources/Protocol";
    }

    void paint(){
        // in order to paint the joystick, we first need to extract from it a GraphicsContext
        GraphicsContext gc = joystick.getGraphicsContext2D();

        // get the middle of the canvas (x,y values of the canvas' center)
        mx = joystick.getWidth()/2;
        my = joystick.getHeight()/2;

        // clear the last drawing everytime "paint()" is called, and then re-paint
        gc.clearRect(0,0, joystick.getWidth(), joystick.getHeight());

        // paint an oval
        gc.strokeOval(jx-50, jy-50, 100,100);

        aileron = Math.min(Math.max((jx-mx)/mx,-1),1);
        elevator = Math.min(Math.max((jy-my)/my,-1),1);

        // notify changes to the controller (the observer)
        setChanged();
        notifyObservers();
        System.out.println(aileron+","+elevator);

    }

    public void mouseDown(MouseEvent me){
        if(!mousePushed){
            mousePushed = true;
            System.out.println("mouse is down");
        }
    }

    // release the mouse click
    public void mouseUp(MouseEvent me){
        if(mousePushed){
            mousePushed = false;
            System.out.println("mouse is up");
            // return the joystick to the middle on mouse up
            jx = mx;
            jy = my;
            paint();
        }
    }

    public void mouseMove(MouseEvent me){
        // check if there was a mousePush, beacuse we want to relate only to the mouse drag
        if(mousePushed){
            // paint the joystick according to where to mouse is moving
            jx = me.getX();
            jy = me.getY();
            paint();
        }
    }

    public void throttleMove(MouseEvent mouseEvent){
        throttle = slideThrottle.getValue() / 100;
        setChanged();
        notifyObservers();
        System.out.println("throttle: " + throttle);
    }

    public void rudderMove(MouseEvent mouseEvent){

        double rudderValue = (slideRudder.getValue() - 50 ) / 50;
        rudder = (slideRudder.getValue() - 50) / 50;
        setChanged();
        notifyObservers();
        System.out.println("rudder: " + rudder);
    }

    public void throttleDown(MouseEvent me){
        if(!mousePushed){
            mousePushed = true;
            System.out.println("throttle is down");
        }
    }

    // release the mouse click
    public void throttleUp(MouseEvent me){
        if(mousePushed){
            mousePushed = false;
            System.out.println("throttle is up");
            // Change the throttle value to where the slider was pressed
            throttle = slideThrottle.getValue() / 100;
            setChanged();
            notifyObservers();
            System.out.println("throttle: " + throttle);
        }
    }

    public void rudderDown(MouseEvent me){
        if(!mousePushed){
            mousePushed = true;
            System.out.println("rudder is down");
            rudder = (slideRudder.getValue() - 50 ) / 50;
            setChanged();
            notifyObservers();
            System.out.println("rudder: " + rudder);
        }
    }

    // release the mouse click
    public void rudderUp(MouseEvent me){
        if(mousePushed){
            mousePushed = false;
            rudder = 0.05;
            slideRudder.setValue(50);
            setChanged();
            notifyObservers();
            System.out.println("rudder: " + rudder);
        }
    }
}