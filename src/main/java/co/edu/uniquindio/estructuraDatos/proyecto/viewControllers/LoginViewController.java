package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.app.AdminViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.app.App;
import co.edu.uniquindio.estructuraDatos.proyecto.app.UserViewController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private double x;
    private double y;

    private AnchorPane anchorPane;

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @FXML
    void activeSignUpTab(ActionEvent event) {
        tabSignUp.setDisable( false );
        tabPane.getSelectionModel().select( tabSignUp );
        tabLogIn.setDisable( true );
    }
    @FXML
    void logIn(ActionEvent event) throws IOException {

        if(txtName.getText().equals(  "admin" ) && txtPassword.getText().equals( "$aDmiN")||txtPassword.getText().equals( "123") ){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( App.class.getResource( "AdminView.fxml" ) );
            AnchorPane anchorPane = loader.load();
            AdminViewController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene( new Scene( anchorPane , 1365 , 715 ) );
            controller.init( stage );


            controller.setAnchorPane( anchorPane );
            controller.setLoginViewController( this );

            stage.initStyle( StageStyle.TRANSPARENT );
            stage.centerOnScreen();
            controller.show();
            this.stage.close();

        }else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( App.class.getResource( "UserView.fxml" ) );
            AnchorPane anchorPane = loader.load();
            UserViewController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene( new Scene( anchorPane , 1365 , 715 ) );
            controller.init( stage );


            controller.setAnchorPane( anchorPane );
            controller.setLoginViewController( this );

            stage.initStyle( StageStyle.TRANSPARENT );
            stage.centerOnScreen();
            controller.show();
            this.stage.close();

        }
        cleanUp();


    }

    void cleanUp(){
        txtName.clear();
        txtPassword.clear();
    }

    @FXML
    void closeWindow(ActionEvent event) {
        FadeTransition fadeOut = new FadeTransition( Duration.seconds( 1 ) , anchorPane  );
        fadeOut.setFromValue( 1 );
        fadeOut.setToValue( 0 );
        fadeOut.play();
        fadeOut.setOnFinished( evt -> {
            this.stage.close();

        });
    }

    @FXML
    void activeLogInTab(ActionEvent event) {
        tabLogIn.setDisable( false );
        tabPane.getSelectionModel().select( tabLogIn );
        tabSignUp.setDisable( true );
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
        tabSignUp.setDisable( true );
    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        FadeTransition fadeIn = new FadeTransition( Duration.seconds(1) , anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        stage.show();
        fadeIn.play();

    }

}
