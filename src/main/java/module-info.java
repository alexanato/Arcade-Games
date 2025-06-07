module com.example.arcadeposproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;
    requires java.logging;
    requires com.fasterxml.jackson.databind;

    opens com.example.arcadeposproject.controllers to javafx.fxml;
    opens com.example.arcadeposproject to javafx.fxml;
    exports com.example.arcadeposproject;
}