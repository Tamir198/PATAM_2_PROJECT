package view;

import javafx.beans.property.SimpleStringProperty;

public class Flights {
    private final SimpleStringProperty Flight_id;
    private final SimpleStringProperty Currently_Flying;
    private final SimpleStringProperty Starting_Time;
    private final SimpleStringProperty Landing_Time;
    private final SimpleStringProperty date;
    private final SimpleStringProperty max_height;
    private final SimpleStringProperty max_speed;

    public Flights(String Flight_id, String Currently_Flying, String Starting_Time, String Landing_Time, String date, String max_height, String max_speed) {
        this.Flight_id = new SimpleStringProperty(Flight_id);
        this.Currently_Flying = new SimpleStringProperty(Currently_Flying);
        this.Starting_Time = new SimpleStringProperty(Starting_Time);
        this.Landing_Time = new SimpleStringProperty(Landing_Time);
        this.date = new SimpleStringProperty(date);
        this.max_height = new SimpleStringProperty(max_height);
        this.max_speed=new SimpleStringProperty(max_speed);
    }

    public String getFlight_id() {
        return Flight_id.get();
    }

    public void setFlight_id(String flight_id) {
        this.Flight_id.set(flight_id);
    }

    public String getCurrently_Flying() {
        return Currently_Flying.get();
    }

    public SimpleStringProperty currently_FlyingProperty() {
        return Currently_Flying;
    }

    public void setCurrently_Flying(String currently_Flying) {
        this.Currently_Flying.set(currently_Flying);
    }

    public String getStarting_Time() {
        return Starting_Time.get();
    }

    public SimpleStringProperty starting_TimeProperty() {
        return Starting_Time;
    }

    public void setStarting_Time(String starting_Time) {
        this.Starting_Time.set(starting_Time);
    }

    public String getLanding_Time() {
        return Landing_Time.get();
    }

    public SimpleStringProperty landing_TimeProperty() {
        return Landing_Time;
    }

    public void setLanding_Time(String landing_Time) {
        this.Landing_Time.set(landing_Time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getMax_height() {
        return max_height.get();
    }

    public SimpleStringProperty max_heightProperty() {
        return max_height;
    }

    public void setMax_height(String max_height) {
        this.max_height.set(max_height);
    }

    public String getMax_speed() {
        return max_speed.get();
    }

    public SimpleStringProperty max_speedProperty() {
        return max_speed;
    }

    public void setMax_speed(String max_speed) {
        this.max_speed.set(max_speed);
    }
}
