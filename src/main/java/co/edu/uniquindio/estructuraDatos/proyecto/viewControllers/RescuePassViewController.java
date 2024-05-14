package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.RescuePassController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private AnchorPane anchorNewPass;

    @FXML
    private Button btnClose;

    @FXML
    private Button btBack;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnSubmitCode;

    @FXML
    private Button btnSubmitPass;

    @FXML
    private TextField txtNameAcc;

    @FXML
    private TextField txtcode1;
    @FXML
    private TextField txtNewPass;

    @FXML
    private TextField txtNewPassConfirm;

    @FXML
    private TextField txtcode2;

    @FXML
    private TextField txtcode3;

    @FXML
    private TextField txtcode4;

    @FXML
    private TextField txtcode5;

    private RescuePassController rescuePassController;
    private Storify storify;
    private Stage stage;

    private String clientName;
    private String code;
    private AnchorPane anchorPane;

    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        stage.show();
        fadeIn.play();

    }

    @FXML
    void changePassword(ActionEvent event) throws UserException {

        String pass1 = txtNewPass.getText();
        String pass2 = txtNewPassConfirm.getText();

        if(!pass1.isEmpty() && !pass2.isEmpty() ){
            if(pass1.equals( pass2 )){
                User user = getUserFromUsername( clientName );
                changePasswordClient( user,pass1 );
                txtNewPass.clear();txtNewPassConfirm.clear();
                showMessage( "Notification" ,"Password changed",
                        "Password has been changed successfully" , Alert.AlertType.INFORMATION );
                stage.close();
            }else{
                showMessage( "Notification", "Passwords doesn't match" ,
                        "Try again", Alert.AlertType.INFORMATION);
            }
        }else{
            showMessage( "Notification", "Blank Spaces" ,"Enter a password", Alert.AlertType.INFORMATION);
        }

    }

    public void changePasswordClient(User clientName , String pass1) throws UserException {
        rescuePassController.mfm.changePassword(clientName  , pass1);
    }

    @FXML
    void verifyCodeRescue(ActionEvent event){
        String aux = "";
        aux += txtcode1.getText();
        aux += txtcode2.getText();
        aux += txtcode3.getText();
        aux += txtcode4.getText();
        aux += txtcode5.getText();

        if(aux.equals( code )){
            showMessage( "Notification" , "Code successfully entered",
                    "Now enter your new password", Alert.AlertType.INFORMATION);
            anchorNewPass.setVisible( true );
        }else{
            showMessage( "Notification" , "Incorrect code",
                    "Try again your code", Alert.AlertType.INFORMATION);
            clearCodeSpaces();
        }
    }

    void clearCodeSpaces(){
        txtcode1.clear();
        txtcode2.clear();
        txtcode3.clear();
        txtcode4.clear();
        txtcode5.clear();
    }

    @FXML
    void closeWindow(ActionEvent event) {
        anchorSecond.setVisible(false);
        anchorFirst.setVisible(false);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), anchorPane);
        fadeOut.setFromValue(0.5);
        fadeOut.setToValue(0);
        fadeOut.play();
        fadeOut.setOnFinished(evt -> {
            this.stage.close();

        });
    }

    @FXML
    void showAnchorFirst(ActionEvent event) {
        anchorSecond.setVisible(false);
        txtcode1.clear();
        txtcode2.clear();
        txtcode3.clear();
        txtcode4.clear();
        txtcode5.clear();
    }

    @FXML
    void initialize() {
        btnSubmitCode.setDisable( true );
        this.rescuePassController = new RescuePassController();
        eventsManager();
        anchorSecond.setVisible(false);
        anchorNewPass.setVisible( false );
        txtNameAcc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnSubmit.setDisable(newValue.isEmpty());
            }
        });

    }

    private void verifySpaces(TextField txt1, TextField txt2,TextField txt3, TextField txt4,TextField txt5,  Button btn) {
        if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty() && !txt3.getText().isEmpty() && !txt4.getText().isEmpty()&& !txt5.getText().isEmpty()) {
            btn.setDisable(false); // Habilitar el botón si ambos campos están llenos
        } else {
            btn.setDisable(true); // Deshabilitar el botón si algún campo está vacío
        }
    }

    public void eventsManager() {
        btnSubmit.setDisable(true);

        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            if (change.getControlNewText().matches("\\d")) {
                return change;
            } else {
                change.setText(""); // else make no change
                return change;
            }
        };

        txtcode1.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode2.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode3.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode4.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode5.setTextFormatter(new TextFormatter<>(numberFilter));

        txtcode1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode2.requestFocus();
            }
            verifySpaces( txtcode1, txtcode2,txtcode3,txtcode4,txtcode5, btnSubmitCode );
        });

        txtcode2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode3.requestFocus();
            } else {
                txtcode1.requestFocus();
                txtcode1.selectEnd();
            }
            verifySpaces( txtcode1, txtcode2,txtcode3,txtcode4,txtcode5, btnSubmitCode );

        });

        txtcode3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode4.requestFocus();
            } else {
                txtcode2.requestFocus();
                txtcode2.selectEnd();
            }
            verifySpaces( txtcode1, txtcode2,txtcode3,txtcode4,txtcode5, btnSubmitCode );

        });

        txtcode4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode5.requestFocus();
            } else {
                txtcode3.requestFocus();
                txtcode3.selectEnd();
            }
            verifySpaces( txtcode1, txtcode2,txtcode3,txtcode4,txtcode5, btnSubmitCode );

        });

        txtcode5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
            } else {
                txtcode4.requestFocus();
                txtcode4.selectEnd();
            }
            verifySpaces( txtcode1, txtcode2,txtcode3,txtcode4,txtcode5, btnSubmitCode );

        });
        txtNameAcc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnSubmit.fire(); // Accionar el botón
                }
            }
        });
        txtNewPass.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    txtNewPassConfirm.requestFocus();
                }
            }
        });
        txtNewPassConfirm.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnSubmitPass.fire();
                }
            }
        });


    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @FXML
    void searchEmail(ActionEvent event) {
        String userName = txtNameAcc.getText();
        clientName = userName;
        if (verifyBlankSpaces(userName)) {
            if (verifyCredentials(userName)) {
                String userEmail = getEmailFromUsername(userName);
                if(userEmail != null){
                    // Si se encontró el correo electrónico, generar y enviar el código de recuperación
                    String code = rescuePassController.mfm.generateCode(5); // Generar código de recuperación de 5 dígitos
                    this.code = code;
                    User user = getUserFromUsername(userName);
                    rescuePassController.mfm.sendRescueEmail(user, code); // Enviar correo electrónico con el código
                    showMessage("Notification", "Code sent", "Code has been sent to your email", Alert.AlertType.INFORMATION);
                }
                txtNameAcc.clear();
                anchorSecond.setVisible(true);
            }else {
                showMessage("Notification", "Invalid credentials",
                        "Credentials are invalid", Alert.AlertType.INFORMATION);
            }
        }
    }

    private User getUserFromUsername(String userName) {
        // Obtener el mapa de usuarios cargados desde el archivo
        HashMap<String, User> users = rescuePassController.mfm.getStorify().getUsersMap();
        // Verificar si el usuario está en el mapa
        if (users.containsKey(userName)) {
            // Si el usuario está en el mapa, devolver el objeto usuario
            return users.get(userName);
        }
        // Si el usuario no está en el mapa, devolver null
        return null;
    }

    private String getEmailFromUsername(String userName) {
        // Obtener el mapa de usuarios cargados desde el archivo
        HashMap<String, User> users = rescuePassController.mfm.getStorify().getUsersMap();
        // Verificar si el usuario está en el mapa
        if (users.containsKey(userName)) {
            // Si el usuario está en el mapa, obtener el objeto usuario
            User user = users.get(userName);
            // Devolver el correo electrónico del usuario
            return user.getEmail();
        }
        // Si el usuario no está en el mapa, devolver null
        return null;
    }


    private boolean verifyCredentials(String userName) {
        // Obtener el mapa de usuarios cargados desde el archivo
        HashMap<String, User> users = rescuePassController.mfm.getStorify().getUsersMap();
        // Verificar si el usuario está en el mapa
        if (users.containsKey(userName)) {
            // Obtener el objeto usuario
            User user = users.get(userName);
            return true;// Las credenciales son válidas
        }
        return false;// Las credenciales son inválidas
    }


    private boolean verifyBlankSpaces(String userName){
        String notification = "";
        if(userName.isEmpty()){
            txtNameAcc.clear();
            notification = "Type your user name";
        }
        if (notification.equals("")) {
            return true;
        }
        showMessage("Notification", "Blank space",
                    notification, Alert.AlertType.INFORMATION);
        return false;
    }

    public void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
