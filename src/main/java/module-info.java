module com.project.ooaid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;
    requires lombok;


    opens com.project.ooaid to javafx.fxml;
    exports com.project.ooaid;
    exports com.project.ooaid.authorization;
    opens com.project.ooaid.authorization to javafx.fxml;
    exports com.project.ooaid.registration;
    opens com.project.ooaid.registration to javafx.fxml;
}