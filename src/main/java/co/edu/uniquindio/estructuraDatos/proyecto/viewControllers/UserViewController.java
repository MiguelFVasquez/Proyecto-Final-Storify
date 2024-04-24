package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.UserController;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblFS1;

    @FXML
    private Label lblFS2;

    @FXML
    private Button btnLogOut;

    @FXML
    private AnchorPane anchorHome;

    @FXML
    private StackPane stackFS1;
    @FXML
    private StackPane stackFS2;

    @FXML
    private ImageView imageFS1;
    @FXML
    private ImageView imageFS2;

    @FXML
    private Label labelTitle;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLibrary;

    @FXML
    private Button btnSearch;
    private Stage stage;

    private Double x;
    private Double y;

    private LoginViewController loginViewController;
    private AnchorPane anchorPane;
    private UserController userController;

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }



    //------------------------------FUNCTIONS OF SHOW-------------------------------------------------------------------
    @FXML
    void showHomeInfo(ActionEvent event) {
        labelTitle.setText( "Home" );



    }



    public List<Song> getSongs(){
        return userController.mfm.getSongList();
    }

    @FXML
    void showLibraryInfo(ActionEvent event) {
        labelTitle.setText( "Library" );

    }

    @FXML
    void showSearchInfo(ActionEvent event) {
        labelTitle.setText( "Search" );
    }
    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        this.stage.close();
    }



    @FXML
    void initialize() {

    }

    void eventsControll(){
        btnLogOut.setOnMouseEntered(event -> btnLogOut.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLogOut.setOnMouseExited(event -> btnLogOut.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnHome.setOnMouseEntered(event -> btnHome.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnHome.setOnMouseExited(event -> btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnSearch.setOnMouseEntered(event -> btnSearch.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnSearch.setOnMouseExited(event -> btnSearch.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnLibrary.setOnMouseEntered(event -> btnLibrary.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLibrary.setOnMouseExited(event -> btnLibrary.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginViewController= new LoginViewController();
        this.userController= new UserController();
        eventsControll();
    }
}
