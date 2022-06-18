module patam2.agent {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens patam2.agent to javafx.fxml;
    exports patam2.agent;
}