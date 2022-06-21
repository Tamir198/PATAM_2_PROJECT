package sample;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private final static MongoClientURI mongoClientURI = new MongoClientURI("mongodb+srv://Admin:QAVvsM8ag0DVny7R@cluster0.oc8hw.mongodb.net/?authMechanism=SCRAM-SHA-1&authSource=admin");
    @FXML
    private TextField date;
    @FXML
    private TextField starting_time;
    @FXML
    private Label status;
    @FXML
    private TextField landing_time;
    @FXML
    public ComboBox<String> currently_flying;
    @FXML
    private TextField max_height;
    @FXML
    private TextField max_speed;
    @FXML
    private Button att;

//  create an observable list to hold the content of the combobox
    ObservableList<String> list  = FXCollections.observableArrayList("true", "false");

//  create a primary stage object
    Stage primaryStage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//      set the items of the combobox
        currently_flying.setItems(list);
    }

    public void getFieldValues(ActionEvent event){
        try{
//          create a connection to mongodb server
            //MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoClient mongoClient = new MongoClient(mongoClientURI);

//          create a database name
            MongoDatabase mongoDatabase = mongoClient.getDatabase("FlightDB");

//          create a collection
            MongoCollection coll = mongoDatabase.getCollection("FlightList");

//          get the values of the fields
            Document doc = new Document("date", date.getText())
                    .append("Starting_Time", starting_time.getText())
                    .append("Landing_Time", landing_time.getText())
                    .append("Currently_Flying", currently_flying.getValue())
                    .append("max_height", max_height.getText() + "ft")
                    .append("max_speed", max_speed.getText() + "mph");


//          save the document
            coll.insertOne(doc);

//          display a success message
            status.setText("saved successfully!");

//          set the fields to null or empty
            date.setText("");
            starting_time.setText("");
            landing_time.setText("");
            currently_flying.setValue(null);
            max_height.setText("");
            max_speed.setText("");
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//          display the error message
            status.setText("data was not saved");
        }
    }

    public void goToFlightList() throws Exception{
//      get the current window
        Stage stage = (Stage)att.getScene().getWindow();

//      close the current window
        stage.close();

//      load the attendance list window
        Parent root = FXMLLoader.load(getClass().getResource("FlightList.fxml"));
        primaryStage.setTitle("Flight List");
        primaryStage.setScene(new Scene(root, 772, 400));
        primaryStage.show();
    }
}