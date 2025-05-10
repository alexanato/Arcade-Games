module com.example.arcadeposproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.arcadeposproject.controllers to javafx.fxml;
    opens com.example.arcadeposproject to javafx.fxml;
    exports com.example.arcadeposproject;
}