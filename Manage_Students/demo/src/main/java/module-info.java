module com.example {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive java.sql;

    opens com.example.controller to javafx.fxml;
    opens com.example to javafx.fxml;
    opens com.example.entity to javafx.fxml;

    exports com.example.controller;
    exports com.example;
    exports com.example.entity;
}
