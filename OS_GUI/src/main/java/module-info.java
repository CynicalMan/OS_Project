module com.example.os_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens proj to javafx.fxml;
    exports proj;
}