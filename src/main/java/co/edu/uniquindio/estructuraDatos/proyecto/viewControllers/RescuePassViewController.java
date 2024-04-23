package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RescuePassViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorSecond;

    @FXML
    private AnchorPane anchorFirst;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtNameAcc;

    @FXML
    private TextField txtcode1;

    @FXML
    private TextField txtcode2;

    @FXML
    private TextField txtcode3;

    @FXML
    private TextField txtcode4;

    @FXML
    private TextField txtcode5;
    private Stage stage;

    public void init(Stage stage2) {
        this.stage = stage2;
    }
    public void show() {
        stage.show();
    }


    @FXML
    void closeWindow(ActionEvent event) {
        stage.close();

    }

    @FXML
    void searchEmail(ActionEvent event) {
        anchorSecond.setVisible( true );
    }

    @FXML
    void initialize() {
        anchorSecond.setVisible( false );

    }


}
