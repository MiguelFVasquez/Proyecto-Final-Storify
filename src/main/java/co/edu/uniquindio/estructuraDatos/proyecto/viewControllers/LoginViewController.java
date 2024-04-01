package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAlrHavAcc;

    @FXML
    private Button btnCloseWindow;

    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnHaveAnAcc;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNameRegister;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordConfirRegister;

    @FXML
    private Tab tabLogIn;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabSignUp;

    @FXML
    private TextField txtPasswordRegister;
    private Stage stage;

    @FXML
    void activeLogInTab(ActionEvent event) {

    }

    @FXML
    void activeRegisterTab(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void registerUser(ActionEvent event) {

    }

    @FXML
    void rescuePassword(ActionEvent event) {

    }
    
    //--------------------------------------UTILITARY FUNCTIONS
    @FXML
    void initialize() {
    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        stage.show();
    }

}
