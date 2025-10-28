module com.example.projectstartingcoding {
    requires javafx.controls;
    requires javafx.fxml;
requires java.sql;
    requires jasperreports;

    opens com.example.projectstartingcoding to javafx.fxml;
    exports com.example.projectstartingcoding;
}