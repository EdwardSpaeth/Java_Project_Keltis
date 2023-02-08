module com.example.keltispractice {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;
    requires java.desktop;

    requires org.json;
    requires java.json;

    exports com.KeltisT.Controllers;
    opens com.KeltisT.Controllers to javafx.fxml;
    exports com.KeltisT;
    opens com.KeltisT to javafx.fxml;

}