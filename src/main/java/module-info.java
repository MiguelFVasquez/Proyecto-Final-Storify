module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.logging;
    requires java.desktop;
    requires junit;
    requires java.mail;
    requires jdk.xml.dom;

    opens co.edu.uniquindio.estructuraDatos.proyecto.app to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.estructuraDatos.proyecto.model to javafx.base;
    opens co.edu.uniquindio.estructuraDatos.proyecto.viewControllers to javafx.fxml, javafx.graphics;

    exports co.edu.uniquindio.estructuraDatos.proyecto.model;
    exports co.edu.uniquindio.estructuraDatos.proyecto.test;
    exports co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;
}