package co.edu.uniquindio.estructuraDatos.proyecto.app;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorArtists;

    @FXML
    private AnchorPane anchorSongs;

    @FXML
    private Button btnAddArtist;

    @FXML
    private Button btnAddSong;

    @FXML
    private Button btnArtists;

    @FXML
    private Button btnCleanUpArtist;

    @FXML
    private Button btnCleanUpSong;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSongs;

    @FXML
    private CheckBox checkGroupArtist;

    @FXML
    private ImageView imageViewSongPortait;

    @FXML
    private TableColumn<?, ?> tableColumnCode1;

    @FXML
    private TableColumn<?, ?> tableColumnGroup1;

    @FXML
    private TableColumn<?, ?> tableColumnName1;

    @FXML
    private TableColumn<?, ?> tableColumnNationality1;

    @FXML
    private TableView<?> tableViewArtists1;

    @FXML
    private TableColumn<?, ?> tableColumnCode;

    @FXML
    private TableColumn<?, ?> tableColumnGroup;

    @FXML
    private TableColumn<?, ?> tableColumnName;

    @FXML
    private TableColumn<?, ?> tableColumnNationality;

    @FXML
    private TableView<?> tableViewArtists;

    @FXML
    private TextField txtDurationSong;

    @FXML
    private TextField txtNameAlbumnSong;

    @FXML
    private TextField txtNameArtist;

    @FXML
    private TextField txtNameSong;

    @FXML
    private TextField txtNationalityArtist;

    @FXML
    private TextField txtYearSong;

    private Stage stage;
    private AnchorPane anchorPane;
    private Double x;
    private Double y;
    private LoginViewController loginViewController;


    //------------------------------------

    @FXML
    void addArtist(ActionEvent event) {

    }

    @FXML
    void addSong(ActionEvent event) {

    }

    @FXML
    void cleanUpArtist(ActionEvent event) {

    }

    @FXML
    void cleanUpSong(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        this.stage.close();
    }

    @FXML
    void showArtistsInfo(ActionEvent event) {
        anchorSongs.setVisible( false );
        anchorArtists.setVisible( true );
    }

    @FXML
    void showSongsInfo(ActionEvent event) {
        anchorArtists.setVisible( false );
        anchorSongs.setVisible( true );
    }

    @FXML
    void initialize() {
        anchorSongs.setVisible( false );
        eventsControl();
    }

    void eventsControl(){
        btnLogOut.setOnMouseEntered(event -> btnLogOut.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLogOut.setOnMouseExited(event -> btnLogOut.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnArtists.setOnMouseEntered(event -> btnArtists.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnArtists.setOnMouseExited(event -> btnArtists.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnSongs.setOnMouseEntered(event -> btnSongs.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnSongs.setOnMouseExited(event -> btnSongs.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));

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

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }


}
