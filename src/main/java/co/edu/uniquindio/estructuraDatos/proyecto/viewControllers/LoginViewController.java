package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.AdminViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.app.App;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.UserViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.controllers.LoginController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
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

    private LoginController loginController;
    private Storify storify;
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
        String userName = txtName.getText();
        String password = txtPassword.getText();
        if(txtName.getText().equals(  "admin" ) /*&& txtPassword.getText().equals( "$aDmiN" )*/&& txtPassword.getText().equals( "123" ) ){
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
            if (verifyBlankSpaces(userName,password)) {
                if (verifyUser(userName)) {
                    if (loginController.mfm.logInUser(userName, password)) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(App.class.getResource("UserView.fxml"));
                        AnchorPane anchorPane = loader.load();
                        UserViewController controller = loader.getController();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(anchorPane, 1365, 715));
                        controller.init(stage);

                        controller.setAnchorPane(anchorPane);
                        controller.setLoginViewController(this);

                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.centerOnScreen();
                        controller.show();
                        this.stage.close();
                    }
                }else {
                    showMessage("Notification", "Invalid credentials",
                            "credentials are invalid", Alert.AlertType.INFORMATION);
                }
            }
            cleanUp();
        }
    }

    private boolean verifyPassword(String password) {
        return loginController.mfm.verifyPassword(password);
    }

    public boolean verifyUser(String userName){
        return loginController.mfm.verifyUser(userName);
    }



    private boolean verifyBlankSpaces(String userName, String password) {
        String notification = "";
        if(userName.isEmpty()){
            txtName.clear();
            notification += "Type your user name \n";
        }
        if (password.isEmpty()){
            txtPassword.clear();
            notification += "Type your password";
        }
        if (notification.equals("")) {
            return true;
        }
        showMessage("Notification", "Blank space",
                notification, Alert.AlertType.INFORMATION);
        return false;
    }


    void cleanUp(){
        txtName.clear();
        txtPassword.clear();
        txtNameRegister.clear();
        txtPasswordRegister.clear();
        txtPasswordConfirRegister.clear();
        txtEmail.clear();
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
    void registerUser(ActionEvent event) throws UserException, IOException {
        String userName = txtNameRegister.getText();
        String password = txtPasswordRegister.getText();
        String email = txtEmail.getText();
        if(verifyRegisterBlankSpaces(userName,password,email) && verifyPasswordRegister()){
            if(userRegist(userName,password,email)){
                loginController.mfm.userSerialization();
                activeLogInTab( event );

            }
            cleanUp();
        }
    }

    public boolean userRegist(String userName, String password, String email) throws UserException {
        try {
            if(loginController.mfm.registerUser(userName,password,email)){
                showMessage("Notification", "User created","You can logIn", Alert.AlertType.INFORMATION);
                return true;
            }
        }catch (UserException ue){
            showMessage("Notification", "User not created", ue.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }



    public boolean verifyPasswordRegister(){
        boolean flag = false;
        if (txtPasswordRegister.getText().equals(txtPasswordConfirRegister.getText())){
            return true;
        }else{
            showMessage("Notification", "Password does not match", "Type your password again",
                    Alert.AlertType.INFORMATION);
        }
        return false;
    }

    private boolean verifyRegisterBlankSpaces(String userName, String password, String email) {
        String notification = "";
        if(userName.isEmpty()){
            txtNameRegister.clear();
            notification += "Type your user name \n";
        }
        if (password.isEmpty()){
            txtPasswordRegister.clear();
            notification += "Type your password\n";
        }
        if(password.isEmpty()){
            txtPasswordConfirRegister.clear();
            notification += "Confirm your password\n";
        }
        if(email.isEmpty()){
            txtEmail.clear();
            notification += "Type your email";
        }
        if (notification.equals("")) {
            return true;
        }
        showMessage("Notification", "Blank space",
                notification, Alert.AlertType.INFORMATION);
        return false;
    }



    @FXML
    void rescuePassword(ActionEvent event) {

    }
    
    //--------------------------------------UTILITARY FUNCTIONS
    @FXML
    void initialize() {
        tabSignUp.setDisable( true );
        this.loginController= new LoginController();
        this.

        eventsControl();

    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    void eventsControl(){
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.2), btnLogin);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), btnLogin);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        btnLogin.setOnMouseEntered(event -> {
            btnLogin.setStyle("-fx-background-color:  #a3c0f5;  " +
                    "-fx-cursor: hand ; " +
                    "-fx-background-radius: 18px ; " +
                    "-fx-border-radius: 18px " );
            scaleIn.play();
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnLogin.setOnMouseExited(event -> {
            btnLogin.setStyle("-fx-background-color:   transparent; " +
                    "-fx-cursor: default; " +
                    "-fx-border-color:    #a3c0f5; " +
                    "-fx-border-radius: 18px ");
            scaleOut.play();});
    }

    public void show() {
        FadeTransition fadeIn = new FadeTransition( Duration.seconds(1) , anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        stage.show();
        fadeIn.play();

    }

}
