module com.example.keltispractice {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.Backup to javafx.fxml;
    exports com.Backup;
    exports com.Backup.controller;
    opens com.Backup.controller to javafx.fxml;
    exports com.KeltisT.Controllers;
    opens com.KeltisT.Controllers to javafx.fxml;
    exports com.KeltisT;
    opens com.KeltisT to javafx.fxml;

}