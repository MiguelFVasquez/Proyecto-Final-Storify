package co.edu.uniquindio.estructuraDatos.proyecto.app;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPaneHome;

    @FXML
    private Button btnLogOut;

    @FXML
    private GridPane gridPaneSearch;
    private Stage stage;

    private Double x;
    private Double y;

    private LoginViewController loginViewController;
    private AnchorPane anchorPane;

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        this.stage.close();
    }



    @FXML
    void initialize() {
        eventsControll();
    }

    void eventsControll(){
        btnLogOut.setOnMouseEntered(event -> btnLogOut.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLogOut.setOnMouseExited(event -> btnLogOut.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));


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
        anchorPane.setOnMousePressed( (MouseEvent event) ->{
            x = event.getSceneX();
            y= event.getSceneY();
        });

        anchorPane.setOnMouseDragged((MouseEvent event) -> {
            stage.setX( event.getScreenX()-x );
            stage.setY( event.getScreenY()-y );
        });
    }

}
