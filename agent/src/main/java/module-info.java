module patam2.agent {
    requires javafx.controls;
    requires javafx.fxml;


    opens patam2.agent to javafx.fxml;
    exports patam2.agent;
    exports patam2.agent.view;
    opens patam2.agent.view to javafx.fxml;
}