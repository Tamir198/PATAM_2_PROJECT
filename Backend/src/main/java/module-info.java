module patam2.backend {
    requires javafx.controls;
    requires javafx.fxml;


    opens patam2.backend to javafx.fxml;
    exports patam2.backend;
}