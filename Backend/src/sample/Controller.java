package sample;

import com.mongodb.MongoClient;
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
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
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
            MongoClient mongoClient = new MongoClient(HOST, PORT);

//          create a database name
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");

//          create a collection
            MongoCollection coll = mongoDatabase.getCollection("FlightList");

//          get the values of the fields
            Document doc = new Document("date", date.getText())
                    .append("starting_time", starting_time.getText())
                    .append("landing_time", landing_time.getText())
                    .append("currently_flying", currently_flying.getValue())
                    .append("max_speed", max_speed.getText())
                    .append("max_height", max_height.getText());


//          save the document
            coll.insertOne(doc);

//          display a success message
            status.setText("nishmar sababaaaaa!");

//          set the fields to null or empty
            date.setText("");
            starting_time.setText("");
            landing_time.setText("");
            currently_flying.setValue(null);
            max_speed.setText("");
        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//          display the error message
            status.setText("lo nishmar cosemec");
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
        primaryStage.setScene(new Scene(root, 747, 400));
        primaryStage.show();

    }


}
