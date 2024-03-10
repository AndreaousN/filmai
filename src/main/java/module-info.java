module com.example.filmai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.filmai to javafx.fxml;
    exports com.example.filmai;
    exports com.example.filmai.controller;
    opens com.example.filmai.controller to javafx.fxml;
    opens com.example.filmai.model to javafx.base;
}