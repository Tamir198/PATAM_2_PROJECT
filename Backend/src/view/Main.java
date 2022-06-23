package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    // show the primary stage
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AddFlight.fxml"));
        primaryStage.setTitle("Add Flight");
        primaryStage.setScene(new Scene(root, 331, 364));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
