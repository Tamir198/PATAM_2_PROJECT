package sample;


import com.mongodb.MongoClient;
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


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;


public class FlightList implements Initializable {
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    private String is_flying;
    private String starting;
    private String landing;
    private String height;
    private String speed;
    private String date_;
//    private String _email;
//    private String _gender;
//    private String pnumber;
    private int pos;

    @FXML
    private Label status;
    @FXML
    private TableView<Flights> table;
    @FXML
    private TableColumn<Flights, Integer> id;
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
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    //  create a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");

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
                date_ = doc.getString("date");
                starting = doc.getString("Starting_Time");
                landing = doc.getString("Landing_Time");
                is_flying = doc.getString("Currently_Flying");
                height = doc.getString("max_height");
                speed = doc.getString("max_speed");

                flight.add(new Flights(pos, is_flying, starting, landing, date_, height, speed));
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

//    public void editAttendanceList() {
//        Flights selectedItem = table.getSelectionModel().getSelectedItem();
//        if (selectedItem == null){
////          display an error message if no row was selected
//            status.setText("Please select a row and perform this action again");
//        }
//        else{
//            is_flying = selectedItem.getCurrently_Flying();
//            starting = selectedItem.getStarting_Time();
//            landing = selectedItem.getDate();
//            height = selectedItem.getLanding_Time();
//            speed = selectedItem.getMax_height();
//
////          here i am using the email as my primary key to find each document to update it in the database
//            coll.updateOne(eq("email", height), new Document("$set",
//                    new Document("firstname", is_flying)
//                            .append("lastname", starting)
//                            .append("gender", landing)
//                            .append("email", height)
//                            .append("phone_number", speed)));
//
////          call the rePopulateTable method
//            rePopulateTable();
//
////          call the setTable method
//            setTable();
//
////          hide the error message
//            status.setText("");
//        }
//
//    }

    public void deleteFlight() {
//      get the selected row
        Flights selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
//          display an error message
            status.setText("Please select a row and perform this action again");
        } else {
//          get the value of the selected email column
            String landing_time_ = selectedItem.getLanding_Time();

//          here i am using the email as my primary key to find each document to delete from the database
            coll.deleteOne(eq("date", date_));

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

        date.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {

                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDate(event.getNewValue());

            }
        });

        //      make lastname column editable with a text-field
        starting_time.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        starting_time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setStarting_Time(event.getNewValue());
            }
        });

//      make phone number column editable with a text-field
        landing_time.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        landing_time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setLanding_Time(event.getNewValue());
            }
        });

//      make firstname column editable with a text-field
        currently_flying.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        currently_flying.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {

                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCurrently_Flying(event.getNewValue());

            }
        });

//      make phone number column editable with a text-field
        max_height.setCellFactory(TextFieldTableCell.forTableColumn());
//      gets the new value and calls the setFirstname method
        max_height.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMax_height(event.getNewValue());
            }
        });
        max_speed.setCellFactory(TextFieldTableCell.forTableColumn());
//      gets the new value and calls the setFirstname method
        max_speed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flights, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flights, String> event) {
                ((Flights) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMax_speed(event.getNewValue());
            }
        });
//      set the values of each column to display on the table
        id.setCellValueFactory(new PropertyValueFactory<Flights, Integer>("id"));
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
                pos = i + 1;

                Document doc = cursor.next();
                date_ = doc.getString("date");
                starting = doc.getString("Starting_Time");
                landing = doc.getString("Landing_Time");
                is_flying = doc.getString("Currently_Flying");
                height = doc.getString("max_height");
                speed = doc.getString("max_speed");

                flight.add(new Flights(pos, is_flying, starting, landing, date_, height, speed));
            }
            list = FXCollections.observableArrayList(flight);


        } finally {
//          close the connection
            cursor.close();
        }
    }
}