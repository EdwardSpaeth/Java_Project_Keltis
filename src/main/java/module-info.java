module com.example.keltispractice {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.keltis to javafx.fxml;
    exports com.keltis;
    exports com.keltis.controller;
    opens com.keltis.controller to javafx.fxml;

}