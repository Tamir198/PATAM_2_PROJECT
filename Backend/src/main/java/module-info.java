module ptm2.backend {
    requires javafx.controls;
    requires javafx.fxml;


    opens ptm2.backend to javafx.fxml;
    exports ptm2.backend;
}