package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.animation.FadeTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminViewController implements Initializable {

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
    //---------------Elementos de la cancion--------------------
    @FXML
    private ImageView imageViewSongPortait;
    @FXML
    private TextField txtNameSong;
    @FXML
    private TextField txtDurationSong;
    @FXML
    private TextField txtNameAlbumnSong;
    @FXML
    private TextField txtYearSong;
    @FXML
    private TableView<?> tableViewSongs;
    @FXML
    private TableColumn<?, ?> ColumnCodeSong;
    @FXML
    private TableColumn<?, ?> ColumnNameSong;
    @FXML
    private TableColumn<?, ?> columnAlbum;
    @FXML
    private TableColumn<?, ?> columnYear;

    //-----------Elementos del artista------
    @FXML
    private TextField txtNameArtist;
    @FXML
    private TextField txtNationalityArtist;
    @FXML
    private CheckBox checkGroupArtist;
    @FXML
    private TableView<Artist> tableViewArtists;
    @FXML
    private TableColumn<Artist, String> tableColumnCode;

    @FXML
    private TableColumn<Artist, Boolean> tableColumnGroup;

    @FXML
    private TableColumn<Artist, String>tableColumnName;

    @FXML
    private TableColumn<Artist, String> tableColumnNationality;

    //------------Variables auxiliares---------------
    private Stage stage;
    private AnchorPane anchorPane;
    private Double x;
    private Double y;
    private LoginViewController loginViewController;
    private AdminController adminController;
    private Artist artistSelection;
    private ObservableList<Artist> artistsList= FXCollections.observableArrayList();


    //-------------------Auxiliars functions-----------------------
    public void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static String generateCode(String word1, String word2) {
        String combinedWords = word1 + word2;
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(combinedWords.length());
            code.append(combinedWords.charAt(index));
        }
        return code.toString();
    }

    public void refreshTableViewArtist(){
        artistsList.clear();
        tableViewArtists.getItems().clear();
        tableViewArtists.setItems(getArtistsList());
    }

    //------------------------------Auxiliars artist's functions-----------------------------
    private ObservableList<Artist> getArtistsList(){
        artistsList.addAll(adminController.mfm.getListArtist());
        return artistsList;
    }
    private boolean verifyArtist(String name, String nationality){
        String notification="";
        if (name.isEmpty()){
            notification+="Type artist's name\n";
        }
        if (nationality.isEmpty()){
            notification+= "Type artist's nationality\n";
        }
        if (notification.equals("")) {
            return true;
        }
        showMessage("Notification", "Invalid data", notification, Alert.AlertType.INFORMATION);
        return false;
    }

    private boolean createArtist(String code,String name, String nationality, boolean isAGroup){
        try {
            if (adminController.mfm.addArtist(code,name,nationality,isAGroup)){
                showMessage("Notification", "Artist registered","Artist was registered successfully", Alert.AlertType.INFORMATION);
                return true;
            }
        } catch (ArtistException Ae) {
            showMessage("Notification","Artist not registered", Ae.getMessage(), Alert.AlertType.INFORMATION);
        }
        return false;
    }


    @FXML
    void addArtist(ActionEvent event) throws IOException {
        String nameArtist= txtNameArtist.getText();
        String nationalityArtist= txtNationalityArtist.getText();
        boolean isAGroup= checkGroupArtist.isSelected();
        if (verifyArtist(nameArtist,nationalityArtist)){
            String code= generateCode(nameArtist,nationalityArtist);
            if (createArtist(code,nameArtist,nationalityArtist,isAGroup)){
                adminController.mfm.userSerialization();
                cleanUpArtist(event);
                refreshTableViewArtist();
            }
        }
    }

    @FXML
    void cleanUpArtist(ActionEvent event) {
        txtNameArtist.clear();
        txtNationalityArtist.clear();
    }


    @FXML
    void addSong(ActionEvent event) {

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
    void showArtistsInfo() {
        txtNameArtist.setText(artistSelection.getName());
        txtNationalityArtist.setText(artistSelection.getNationality());
    }

    @FXML
    void showSongsInfo(ActionEvent event) {
        anchorArtists.setVisible( false );
        anchorSongs.setVisible( true );
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
    @FXML
    void initialize() {
        anchorSongs.setVisible( false );
        eventsControl();
    }


    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminController= new AdminController();

        this.tableColumnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tableColumnNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        this.tableColumnGroup.setCellValueFactory(new PropertyValueFactory<>("isAlone"));

        tableViewArtists.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                artistSelection = newSelection;
                showArtistsInfo();
            }else{
                //btnEliminarProducto.setDisable( true );
            }
        });

    }
}
