package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.UserController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private AnchorPane anchorSearch;
    @FXML
    private AnchorPane anchorLibrary;

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
    @FXML
    private Button btnLike;
    @FXML
    private Button btnDeshacer;

    private Stage stage;

    private Double x;
    private Double y;

    private LoginViewController loginViewController;
    private AnchorPane anchorPane;
    private UserController userController;
    private Song songSelection;

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    private Stack<String> stateStack;
    private Stack<Song> songsStack;
    private Stack<Song> songsDeleteStack;

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    //------------------------------UTILITIES-----------------------------------
    private void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean addSong(String userName, Song songToAdd){
        try {
            if (userController.mfm.addSongToUserList(userName,songToAdd)){
                showMessage("Notification", "Song add to list", "The song: '" + songSelection.getName() +"' was add to list successfully", Alert.AlertType.INFORMATION);
                return true;
            }
        } catch (UserException uE) {
            showMessage("Error", "Error adding song to user list", uE.getMessage(), Alert.AlertType.ERROR);
        } catch (SongException sE) {
            showMessage("Error", "Error adding song to user list", sE.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }
    //Se pasa la cancion que se desea eliminar
    private void removeSong(String userName, Song songToRemove){
        try{
            if (userController.mfm.removeSongFromUserList(userName,songToRemove)){
                showMessage("Notification", "Song removed from list", "The song: '" + songToRemove.getName() +" ' was removed from list successfully", Alert.AlertType.INFORMATION );
                stateStack.push("Elimino");
            }
        } catch (UserException e) {
            showMessage("Error", "Error deleting song from list", e.getMessage(), Alert.AlertType.ERROR);
        } catch (SongException e) {
            showMessage("Error", "Error deleting song from list", e.getMessage(), Alert.AlertType.ERROR);
        }


    }


    //------------------------------FUNCTIONS OF SHOW-------------------------------------------------------------------
    @FXML
    void showHomeInfo(ActionEvent event) {
        labelTitle.setText( "Home" );
        anchorHome.setVisible( true );
        anchorLibrary.setVisible( false );
        anchorSearch.setVisible( false );
        showReleasesSongs( getSongs() );

    }
    private void showReleasesSongs(List<Song> songs){
        if(songs.size() != 0){
            for (int i = 0; i < songs.size(); i++) {
                if(i==2 && songs.get( i ) != null){
                    Song songAux = songs.get( i );
                    lblFS1.setText( songAux.getName() );

                    if (songAux.getCover() != null) {
                        System.out.println(songAux.getCover().getUrl());
                        displayImage( songAux, imageFS1 );

                    } else {
                        System.out.println("No se pudo cargar la imagen desde la URL: " );
                    }
                }

            }
        }

    }
    private void displayImage(Song song, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = song.getCover();
            imageView.setImage( image );
            imageView.setFitWidth( 180 );
            imageView.setFitHeight( 150 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Song> getSongs(){
        return userController.mfm.getSongList();
    }

    @FXML
    void showLibraryInfo(ActionEvent event) {
        labelTitle.setText( "Library" );
        anchorLibrary.setVisible( true );
        anchorSearch.setVisible( false );
        anchorHome.setVisible( false );

    }

    @FXML
    void showSearchInfo(ActionEvent event) {
        labelTitle.setText( "Search" );
        anchorSearch.setVisible( true );
        anchorLibrary.setVisible( false );
        anchorHome.setVisible( false );
    }
    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        this.stage.close();
    }
//----------------------------Funcionalidades del usuario----------------------------------
    @FXML
    void addSongToUserList(ActionEvent event) {
        String userName= userController.mfm.getUserName();
        if(songSelection!=null){
            Song songToAdd = songSelection; //Se debe de seleccionar una canción para poderla añadir
            if(addSong(userName,songToAdd)){
                stateStack.push("Agrego");
                songsStack.push(songSelection);
            }
        }else {
            showMessage("Song selection", "Song don't selected", "Please, to add a song, selecet one", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void regresarAccion(ActionEvent event) {
        String userName= userController.mfm.getUserName();
        if(stateStack.isEmpty()){
            showMessage("Notification", "Nothing to undo", "There isn't nothing to undo", Alert.AlertType.INFORMATION);
        }else {
            if (stateStack.pop().equals("Agrego")){ //Si agrego una cancion, la función deshacer lo que hace es eliminarla
                Song songToRemove= songsStack.pop();
                removeSong(userName,songToRemove);
                songsDeleteStack.push(songToRemove); //Añade la cancion a una nueva pila, donde se guardan las canciones eliminadas para poder acceder a ellas depues cuando se haga el rehacer
            }else { //SI elimino la canción , lo que hara la funcón es agregarla
                songSelection= songsStack.pop(); // La canción seleccionada pasa a ser la ultima adicipon de la cola y procede a agregarse
                addSongToUserList(event);
            }
        }
    }


    //------------------------------------INITIALIZATION-------------------------
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
        anchorHome.setVisible( false );
        btnHome.fire();
        songsStack= new Stack<>();
        stateStack= new Stack<>();
        songsDeleteStack= new Stack<>();

        eventsControll();
    }
}
