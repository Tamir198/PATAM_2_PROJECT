package sample;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;


public class FlightList implements Initializable {
    private final static MongoClientURI mongoClientURI = new MongoClientURI("mongodb+srv://Admin:QAVvsM8ag0DVny7R@cluster0.oc8hw.mongodb.net/?authMechanism=SCRAM-SHA-1&authSource=admin");
    private String is_flying;
    private String starting;
    private String landing;
    private String height;
    private String speed;
    private String date_;
    private String id_;
    int pos;

    @FXML
    private Label status;
    @FXML
    private TableView<Flights> table;
    @FXML
    private TableColumn<Flights, String> id;
    @FXML
    private TableColumn<Flights, String> currently_flying;
    @FXML
    private TableColumn<Flights, String> starting_time;
    @FXML
    private TableColumn<Flights, String> landing_time;
    @FXML
    private TableColumn<Flights, String> date;
    @FXML
    private TableColumn<Flights, String> max_height;
    @FXML
    private TableColumn<Flights, String> max_speed;
    @FXML
    private Button addFlight;

    //  create a primary stage object
    Stage primaryStage = new Stage();

    //  create an observable list to hold the Attendees object in the Attendees class
    public ObservableList<Flights> list;

    public List flight = new ArrayList();


    //  create a mongodb connection
    //MongoClient mongoClient = new MongoClient(HOST, PORT);
    MongoClient mongoClient = new MongoClient(mongoClientURI);

    //  create a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("FlightDB");

    //  create a collection
    MongoCollection coll = mongoDatabase.getCollection("FlightList");
    //  call the find all method
    MongoCursor<Document> cursor = coll.find().iterator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);

        try {
            for (int i = 0; i < coll.count(); i++) {
                pos = i + 1;

                Document doc = cursor.next();
                id_ = doc.getObjectId("_id").toString();
                date_ = doc.getString("date");
                starting = doc.getString("Starting_Time");
                landing = doc.getString("Landing_Time");
                is_flying = doc.getString("Currently_Flying");
                height = doc.getString("max_height");
                speed = doc.getString("max_speed");

                flight.add(new Flights(id_,is_flying, starting, landing, date_, height, speed));
            }
            list = FXCollections.observableArrayList(flight);


        } finally {
//          close the connection
            cursor.close();
        }

//      call the setTable method
        setTable();
    }

    public void addFlight() throws Exception {
//      get the current window
        Stage stage = (Stage) addFlight.getScene().getWindow();

//      close the current window
        stage.close();

//      load the main class window
        Main mainClass = new Main();

        mainClass.start(primaryStage);
    }

    public void deleteFlight() {
//      get the selected row
        Flights selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
//          display an error message
            status.setText("Please select a row and perform this action again");
        } else {
//          get the value of the selected email column
            ObjectId flight_id = new ObjectId(selectedItem.getFlight_id());

//          here we are using the id as primary key to find each document to delete from the database
            coll.deleteOne(eq("_id",flight_id));

//          call the rePopulateTable method
            rePopulateTable();

//          call the setTable method
            setTable();

//          hide the error message
            status.setText("");
        }
    }

    public void setTable() {
//      this makes the table editable
        table.setEditable(true);

        /*id.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFlight_id method
        id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {

                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setFlight_id(event.getNewValue());

            }
        });*/

        date.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setDate method
        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {

                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDate(event.getNewValue());

            }
        });

        //      make starting_time column editable with a text-field
        starting_time.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setStarting_Time method
        starting_time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setStarting_Time(event.getNewValue());
            }
        });

//      make landing_time column editable with a text-field
        landing_time.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setLanding_Time method
        landing_time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setLanding_Time(event.getNewValue());
            }
        });

//      make currently_flying column editable with a text-field
        currently_flying.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setCurrently_Flying method
        currently_flying.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {

                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCurrently_Flying(event.getNewValue());

            }
        });

//      make max_height column editable with a text-field
        max_height.setCellFactory(TextFieldTableCell.forTableColumn());
//      gets the new value and calls the setMax_height method
        max_height.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMax_height(event.getNewValue());
            }
        });

//      make max_speed column editable with a text-field
        max_speed.setCellFactory(TextFieldTableCell.forTableColumn());
//      gets the new value and calls the setMax_speed method
        max_speed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMax_speed(event.getNewValue());
            }
        });

//      set the values of each column to display on the table
        id.setCellValueFactory(new PropertyValueFactory<Flights, String>("Flight_id"));
        date.setCellValueFactory(new PropertyValueFactory<Flights, String>("date"));
        starting_time.setCellValueFactory(new PropertyValueFactory<Flights, String>("Starting_Time"));
        landing_time.setCellValueFactory(new PropertyValueFactory<Flights, String>("Landing_Time"));
        currently_flying.setCellValueFactory(new PropertyValueFactory<Flights, String>("Currently_Flying"));
        max_height.setCellValueFactory(new PropertyValueFactory<Flights, String>("max_height"));
        max_speed.setCellValueFactory(new PropertyValueFactory<Flights, String>("max_speed"));
        table.setItems(list);
    }

    private void rePopulateTable() {
//      calls the find all methods from the mongodb database
        MongoCursor<Document> cursor = coll.find().iterator();

//      clears the flight list so that the previous data won't be displayed together with this new ones on the table
        flight.clear();
        try {
//          loop through the database and then populate the list
            for (int i = 0; i < coll.count(); i++) {
                //pos = i + 1;

                Document doc = cursor.next();
                id_ = doc.getObjectId("_id").toString();
                date_ = doc.getString("date");
                starting = doc.getString("Starting_Time");
                landing = doc.getString("Landing_Time");
                is_flying = doc.getString("Currently_Flying");
                height = doc.getString("max_height");
                speed = doc.getString("max_speed");

                flight.add(new Flights(id_,is_flying, starting, landing, date_, height, speed));
            }
            list = FXCollections.observableArrayList(flight);


        } finally {
//          close the connection
            cursor.close();
        }
    }
}