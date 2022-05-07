module ptm2.backend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;


    opens ptm2.backend to javafx.fxml;
    exports ptm2.backend;
}