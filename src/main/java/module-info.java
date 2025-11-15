module com.programa.dolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;
    requires java.desktop;
    requires javafx.graphics;


    exports com.programa.dolist;
    exports com.programa.dolist.Controlladores;

    opens com.programa.dolist to javafx.fxml;

}