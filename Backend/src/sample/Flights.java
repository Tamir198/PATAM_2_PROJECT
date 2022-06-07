package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flights {
    private final SimpleIntegerProperty Flight_id;
    private final SimpleStringProperty Currently_Flying;
    private final SimpleStringProperty Starting_Time;
    private final SimpleStringProperty Landing_Time;
    private final SimpleStringProperty date;
    private final SimpleStringProperty max_height;
    private final SimpleStringProperty max_speed;

    public Flights(Integer Flight_id, String Currently_Flying, String Starting_Time, String Landing_Time, String date, String max_height,String max_speed) {
        this.Flight_id = new SimpleIntegerProperty(Flight_id);
        this.Currently_Flying = new SimpleStringProperty(Currently_Flying);
        this.Starting_Time = new SimpleStringProperty(Starting_Time);
        this.Landing_Time = new SimpleStringProperty(Landing_Time);
        this.date = new SimpleStringProperty(date);
        this.max_height = new SimpleStringProperty(max_height);
        this.max_speed=new SimpleStringProperty(max_speed);
    }


    public Integer getFlightID() {
        return Flight_id.get();
    }

    public String getCurrently_Flying() {
        return Currently_Flying.get();
    }

    public String getStarting_Time() {
        return Starting_Time.get();
    }

    public String getLanding_Time() {
        return Landing_Time.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getMax_height() {
        return max_height.get();
    }
    public String getMax_Speed(){return max_speed.get();}

    public void setCurrently_Flying(String fname) {
        Currently_Flying.set(fname);
    }

    public void setStarting_Time(String lname) {
        Starting_Time.set(lname);
    }

    public void setMax_height(String phoneNumber) {
        max_height.set(phoneNumber);
    }
    public void setMax_speed(String phoneNumber) {
        max_speed.set(phoneNumber);
    }

}
