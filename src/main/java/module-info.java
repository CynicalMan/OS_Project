module com.example.guios {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.guios to javafx.fxml;
    exports com.example.guios;


}