package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import co.edu.uniquindio.estructuraDatos.proyecto.app.App;
import co.edu.uniquindio.estructuraDatos.proyecto.controllers.UserController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UserViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogOut;

    @FXML
    private AnchorPane anchorHome;
    @FXML
    private AnchorPane anchorSearch;
    @FXML
    private AnchorPane anchorLibrary;
    @FXML
    private AnchorPane anchorPlayer;
    @FXML
    private AnchorPane anchorUserSettings;
    @FXML
    private AnchorPane anchorAnalytics;
    @FXML
    private AnchorPane anchorSongsArtist;

    @FXML
    private StackPane stackFS1;
    @FXML
    private StackPane stackFS2;
    @FXML
    private StackPane stackFS3;
    @FXML
    private StackPane stackFS4;
    @FXML
    private StackPane stackFS5;
    @FXML
    private StackPane stackA1;
    @FXML
    private StackPane stackA2;
    @FXML
    private StackPane stackA3;
    @FXML
    private StackPane stackA4;
    @FXML
    private StackPane stackPA;
    @FXML
    private StackPane stackPAU;

    @FXML
    private ImageView imageFS1;

    @FXML
    private ImageView imageFS2;

    @FXML
    private ImageView imageFS3;

    @FXML
    private ImageView imageFS4;

    @FXML
    private ImageView imageFS5;
    @FXML
    private ImageView imageSA1;
    @FXML
    private ImageView imageSA2;
    @FXML
    private ImageView imageSA3;
    @FXML
    private ImageView imageSA4;
    @FXML
    private ImageView imagePlayer;
    @FXML
    private ImageView imageBtnPlay;
    @FXML
    private ImageView imagePA;
    @FXML
    private ImageView imagePAU;

    @FXML
    private Button btnUserSettings;

    @FXML
    private Label lblFS1;

    @FXML
    private Label lblFS2;

    @FXML
    private Label lblFS3;

    @FXML
    private Label lblFS4;

    @FXML
    private Label lblFS5;
    @FXML
    private Label lblSA1;
    @FXML
    private Label lblSA2;
    @FXML
    private Label lblSA3;
    @FXML
    private Label lblSA4;
    @FXML
    private Label lblPA;
    @FXML
    private Label lblPAU;
    @FXML
    private Label lblmostListenedGender;

    @FXML
    private Label labelTitle;
    @FXML
    private Label lblPlayer;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtEmailSettings;

    @FXML
    private TextField txtNameSettings;

    @FXML
    private PasswordField txtPasswordSettings;

    @FXML
    private Button btnHome;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnCancelChangesSettings;
    @FXML
    private Button btnCloseArtistSongs;

    @FXML
    private Button btnPlaySongArtist;
    @FXML
    private Button btnPlaySongLibrary;
    @FXML
    private Button btnLikeSongArtist;
    @FXML
    private Button btnPlaySongSearch;
    @FXML
    private Button btnLikeSongSearch;

    @FXML
    private Button btnConfirmChangesSettings;

    @FXML
    private Button btnDeleteAcc;

    @FXML
    private Button btnEditInfoSettings;

    @FXML
    private Button btnLibrary;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUndo;
    @FXML
    private Button btnUndoLikedSongs;
    @FXML
    private Button btnUndoSearch;
    @FXML
    private Button btnUndoArtist;
    @FXML
    private Button btnTime;
    @FXML
    private Button btnName;
    @FXML
    private Button btnNameArtist;
    @FXML
    private Button btnTimeArtist;
    @FXML
    private Button btnYearArtist;
    @FXML
    private Button btnArtist;
    @FXML
    private Button btnUnlike;
    @FXML
    private Button btnYear;
    @FXML
    private Button btnAnalytics;


    @FXML
    private TableColumn<Song, String> colummImageSearch;

    @FXML
    private TableColumn<Song, Artist> columnArtistSearch;

    @FXML
    private TableColumn<Song, String> columnImage;
    @FXML
    private TableColumn<Song, String> columnImageArtist;

    @FXML
    private TableColumn<Song, String> columnName;

    @FXML
    private TableColumn<Song, String> columnYear;

    @FXML
    private TableColumn<Song, Artist> columnNameArtist;

    @FXML
    private TableColumn<Song, String> columnNameSearch;
    @FXML
    private TableColumn<Song, String> columnNameSongArtist;

    @FXML
    private TableColumn<Song, String> columnTime;

    @FXML
    private TableColumn<Song, String> columnTimeSearch;
    @FXML
    private TableColumn<Song, String> columnTimeArtist;

    @FXML
    private TableColumn<Song, String> columnYearSearch;
    @FXML
    private TableColumn<Song, String> columnYearArtist;


    @FXML
    private TableView<Song> tableViewLikedSongs = new TableView<>();

    @FXML
    private TableView<Song> tableViewSearch = new TableView<>();
    @FXML
    private TableView<Song> tableViewArtist = new TableView<>();

    private Stage stage;

    private Double x;
    private Double y;

    private LoginViewController loginViewController;
    private AnchorPane anchorPane;
    private UserController userController;
    private Song songSelection;
    private OptionsViewController optionsViewController;
    private ObservableList<Song> listSongs = FXCollections.observableArrayList();


    private ObservableList<Song> listSongs2 = FXCollections.observableArrayList();
    private ObservableList<Song> listFavoritesSongs = FXCollections.observableArrayList();
    private ObservableList<Song> listFavoritesSongs2 = FXCollections.observableArrayList();
    private ObservableList<Song> listSongsArtist = FXCollections.observableArrayList();


    private boolean isFilteredArtist = true;
    private boolean isFilteredName = true;
    private boolean isFilteredTime = true;
    private boolean isFilteredYear= true;

    private boolean isFilteredArtist2 = true;
    private boolean isFilteredName2 = true;
    private boolean isFilteredTime2 = true;
    private boolean isFilteredYear2 = true;


    private User user;
    private boolean playing= false;
    private WebView webView;
    private Song selectedSong;
    private boolean showingToolTip = false;
    private Song songArtistSelection;
    private Song songSearch;
    private Artist artistSelected;

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    private Stack<String> stateStack;
    private Stack<Song> songsStack;
    private Stack<Song> songsDeleteStack;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    //------------------------------UTILITIES-----------------------------------


    public Stack<String> getStateStack() {
        return stateStack;
    }

    public void setStateStack(Stack<String> stateStack) {
        this.stateStack = stateStack;
    }

    public Stack<Song> getSongsStack() {
        return songsStack;
    }

    public void setSongsStack(Stack<Song> songsStack) {
        this.songsStack = songsStack;
    }

    public Stack<Song> getSongsDeleteStack() {
        return songsDeleteStack;
    }

    public void setSongsDeleteStack(Stack<Song> songsDeleteStack) {
        this.songsDeleteStack = songsDeleteStack;
    }

    /**
     * Metodo para mostrar mensajes en la interfaz
     * @param title
     * @param header
     * @param content
     * @param alertype
     */
    private void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //============================== Song functions =====================================//

    /**
     * Metodo que agrega una cancion a la lista de canciones de un usuario
     * @param userName
     * @param songToAdd
     * @return
     */
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

    /**
     * Metodo que elimina una cancione de la lista de canciones de un usuario
     * @param userName
     * @param songToRemove
     */
    private void removeSong(String userName, Song songToRemove){
        try{
            if (userController.mfm.removeSongFromUserList(user.getUserName(),songToRemove)){
                showMessage("Notification", "Song removed from list", "The song: '" + songToRemove.getName() +" ' was removed from list successfully", Alert.AlertType.INFORMATION );
            }
        } catch (UserException e) {
            showMessage("Error", "Error deleting song from list", e.getMessage(), Alert.AlertType.ERROR);
        } catch (SongException e) {
            showMessage("Error", "Error deleting song from list", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que muestras mensajes en la interfaz
     * @param message
     * @param stage
     */
    private void showTooltip2(String message, Stage stage) {
        // Create a Tooltip
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);

        // Customize the size of the Tooltip
        tooltip.setStyle("-fx-font-size: 20px; -fx-padding: 5px;");

        // Calculate the center of the window
        Bounds stageBounds = stage.getScene().getRoot().getLayoutBounds();
        double x = (stageBounds.getWidth() - tooltip.getWidth()) ;
        double y = (stageBounds.getHeight() - tooltip.getHeight()) ;

        Point2D p = stage.getScene().getRoot().localToScreen(x, y);

        // Show the Tooltip at the center of the window
        tooltip.show(stage.getScene().getRoot(), p.getX(), p.getY());
        tooltip.centerOnScreen();
        // Automatically hide the Tooltip after 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
            tooltip.hide();
        });
        delay.play();
    }


    //------------------------------FUNCTIONS OF SHOW---------------------------------------------------------//

    /**
     * Metodo de boton que muestra la informacion de la home view
     * @param event
     */
    @FXML
    void showHomeInfo(ActionEvent event) {
        labelTitle.setText( "Home" );
        anchorHome.setVisible( true );
        anchorLibrary.setVisible( false );
        anchorSearch.setVisible( false );
        anchorUserSettings.setVisible( false );
        anchorAnalytics.setVisible( false );
        anchorSongsArtist.setVisible( false );
        showReleasesSongs( getSongs() );
        showArtist( getArtists() );

    }

    /**
     * Metodo que muestra las ultimas canciones agragadas a la lista en la home view
     * @param songs
     */
    private void showReleasesSongs(List<Song> songs){
        int n= 0;
        if(songs.size() != 0){
            for (int i = songs.size()-1; i>=0; i--) {
                Song songAux = songs.get( i );

                switch (n){

                    case 0:
                        assert songAux != null;

                        lblFS1.setText( songAux.getName() );
                        displayImageSong( songAux , imageFS1 );
                        break;
                    case 1:
                        lblFS2.setText( songAux.getName() );
                        displayImageSong( songAux , imageFS2 );
                        break;

                    case 2:
                        lblFS3.setText( songAux.getName() );
                        displayImageSong( songAux , imageFS3 );
                        break;

                    case 3:
                        lblFS4.setText( songAux.getName() );
                        displayImageSong( songAux , imageFS4 );
                        break;

                    case 4:
                        lblFS5.setText( songAux.getName() );
                        displayImageSong( songAux , imageFS5 );
                        break;

                }
                if(n==4){
                    break;
                }
                n++;

            }
        }
    }

    /**
     * Metodo que muestras los ultimos artistas agregados a el arbol en la home view
     * @param artists
     */
    private void showArtist(List<Artist> artists) {
        int n= 0;
        if ( !artists.isEmpty() ) {
            for (int i = 0; i < artists.size(); i++) {
                Artist artistAux = artists.get( i );

                switch (n) {
                    case 0:
                        assert artistAux != null;

                        lblSA1.setText( artistAux.getName() );
                        displayImageArtists( artistAux , imageSA1 );
                        break;
                    case 1:
                        lblSA2.setText( artistAux.getName() );
                        displayImageArtists( artistAux , imageSA2 );
                        break;

                    case 2:
                        lblSA3.setText( artistAux.getName() );
                        displayImageArtists( artistAux , imageSA3 );
                        break;

                    case 3:
                        lblSA4.setText( artistAux.getName() );
                        displayImageArtists( artistAux , imageSA4 );
                        break;

                }
                n++;
            }
        }
    }

    /**
     * Metodo que muestra la imagen de una canciones en la home view
     * @param song
     * @param imageView
     */
    private void displayImageSong(Song song, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = new Image( song.getCover());
            imageView.setImage( image );
            imageView.setFitWidth(183 );
            imageView.setFitHeight( 160 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que muestra la imagen de un artista en la home view
     * @param artist
     * @param imageView
     */
    private void displayImageArtists(Artist artist, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = new Image( artist.getPhoto());
            imageView.setImage( image );
            imageView.setFitWidth(190 );
            imageView.setFitHeight( 170 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Instancia de el metodo que obtiene las canciones de la lista de canciones
     * @return
     */
    public List<Song> getSongs(){
        return userController.mfm.getSongList();
    }

    /**
     * Instancia de metodo que obtiene las cancionesde la lista de canciones de un usuario
     * @return
     */
    public List<Song> getFavoritesSongs(){
        return user.getSongToList();
    }

    /**
     * Instancia de metodo que obtiene la lista de artistas
     * @return
     */
    public List<Artist> getArtists(){
        return userController.mfm.getListArtist();
    }

    /**
     * Metodo de boton que muestra la view para buscar canciones
     * @param event
     */
    @FXML
    void showSearchInfo(ActionEvent event) {
        labelTitle.setText( "Search" );
        anchorHome.setVisible( false );
        anchorLibrary.setVisible( false );
        anchorSearch.setVisible( true );
        anchorUserSettings.setVisible( false );
        anchorAnalytics.setVisible( false );
        anchorSongsArtist.setVisible( false );

    }

    /**
     * Metodo de boton que muestra la view de la libreria de un usuario
     * @param event
     */
    @FXML
    void showLibraryInfo(ActionEvent event) {
        labelTitle.setText( "Library" );
        anchorHome.setVisible( false );
        anchorLibrary.setVisible( true );
        anchorSearch.setVisible( false );
        anchorUserSettings.setVisible( false );
        anchorAnalytics.setVisible( false );
        anchorSongsArtist.setVisible( false );

        refreshTableViewFavorites();
    }

    /**
     * Metodo de boton que muestra la view de las configuraciones de un usuario
     * @param event
     */
    @FXML
    void showUserSettings(ActionEvent event){
        anchorHome.setVisible( false );
        anchorLibrary.setVisible( false );
        anchorSearch.setVisible( false );
        anchorUserSettings.setVisible( true );
        anchorAnalytics.setVisible( false );
        anchorSongsArtist.setVisible( false );

        labelTitle.setText( "Settings" );
        txtNameSettings.setText( user.getUserName() );
        txtPasswordSettings.setText( user.getPassword() );
        txtEmailSettings.setText( user.getEmail() );

    }

    /**
     * Metodo de boton que muestra la view de las estadisticas
     * @param event
     */
    @FXML
    void showAnalytics(ActionEvent event) {
        labelTitle.setText( "Analytics" );
        anchorHome.setVisible( false );
        anchorLibrary.setVisible( false );
        anchorSearch.setVisible( false );
        anchorUserSettings.setVisible( false );
        anchorAnalytics.setVisible( true );
        anchorSongsArtist.setVisible( false );



        Artist artistMostListened = userController.mfm.getMostListenedArtist();
            if(artistMostListened!=null){
                displayInfoArtistMostListened( artistMostListened, imagePA, lblPA);
            }else{
                lblPA.setText( "Mysterious??" );
            }

        Gender genderMostListened = userController.mfm.getMostListenedGender();
            if(genderMostListened!=null){
                lblmostListenedGender.setText( genderMostListened.toString() );

            }else{
                lblmostListenedGender.setText( "All genders has the same numbers of songs " );

            }

        Artist artistMostListenedByUser = userController.mfm.getMostListenedArtistByUser(user);
        if ( artistMostListenedByUser != null ) {
            displayInfoArtistMostListened( artistMostListenedByUser , imagePAU , lblPAU );
        } else {
            lblPAU.setText( "Mysterious??" );
        }

    }

    /**
     * Metodo que permite cerrar sesion a el usuario
     * @param event
     */
    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        optionsViewController.close();
        pauseAllVideos();
        this.stage.close();
    }

    /**
     * Metodo que pausa la reproduccion de la canciones cuando se cierra sesion
     */
    private void pauseAllVideos() {
        if (playing) {
            if (selectedSong != null) {
                playSongVideo(selectedSong.getName(), false); // Pausar la canción principal
            }
            songArtistSelection = getSelectedSong(tableViewArtist);
            if (songArtistSelection != null) {
                playSongVideo(songArtistSelection.getName(), false); // Pausar la canción de Artist
            }
            songSearch = getSelectedSong(tableViewSearch);
            if (songSearch != null) {
                playSongVideo(songSearch.getName(), false); // Pausar la canción de Search
            }
            songSelection = getSelectedSong(tableViewLikedSongs);
            if (songSelection != null) {
                playSongVideo(songSelection.getName(), false); // Pausar la canción de Library
            }
            playing = false; // Asegurarse de actualizar el estado de reproducción
        }
    }

    /**
     * Metodo de boton que filtra las canciones por su artista en la view de busqueda
     * @param event
     */
    @FXML
    void showSongsByArtist(ActionEvent event) {
        if (isFilteredArtist) {
            isFilteredName= true;
            isFilteredTime = true;
            isFilteredYear = true;

            btnName.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnTime.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnArtist.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                    "-fx-text-fill: black;" +
                    "-fx-border-color: black;");
            getSongsByArtist();
        } else {
            btnArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewFavorites();
        }
        isFilteredArtist = !isFilteredArtist;
    }

    /**
     * Metodo de boton que filtra las canciones por año en la view de busqueda
     * @param event
     */
    @FXML
    void showSongsByYear(ActionEvent event){
        if (isFilteredYear) {
            isFilteredName= true;
            isFilteredTime = true;
            isFilteredArtist = true;

            btnArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnTime.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnName.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");

            btnYear.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByYear();

        } else {
            btnYear.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewFavorites();
        }
        isFilteredYear = !isFilteredYear;
    }

    /**
     * Metodo de boton que filtra las canciones por su nombre en la view de busqueda
     * @param event
     */
    @FXML
    void showSongsByName(ActionEvent event) {
        if (isFilteredName) {
            isFilteredTime= true;
            isFilteredArtist = true;
            isFilteredYear = true;
            btnTime.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnYear.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnName.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByName();

        } else {
            btnName.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewFavorites();
        }
        isFilteredName = !isFilteredName;
    }

    /**
     * Metodo de boton que filtra las canciones por la duracion de la cancion en el view de busqueda
     * @param event
     */
    @FXML
    void showSongsByTime(ActionEvent event) {
        if (isFilteredTime) {
            isFilteredName= true;
            isFilteredArtist = true;
            isFilteredYear = true;

            btnName.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnYear.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnTime.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByTime();
        } else {
            btnTime.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewFavorites();
        }
        isFilteredTime = !isFilteredTime;
    }

    /**
     * Metodo que obitne la lista de canciones segun su nombre
     */
    private void getSongsByName() {
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> song1.getName().compareToIgnoreCase(song2.getName()));
        listFavoritesSongs.clear();
        listFavoritesSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.getItems().addAll( listFavoritesSongs );
    }

    /**
     * Metodo que obitne la lista de canciones segun su año
     */
    private void getSongsByYear(){
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getYear()), Integer.parseInt(song1.getYear())));
        listFavoritesSongs.clear();
        listFavoritesSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.getItems().addAll( listFavoritesSongs );
    }

    /**
     * Metodo que obitne la lista de canciones segun su duracion
     */
    private void getSongsByTime(){
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getDuration()), Integer.parseInt(song1.getDuration())));
        listFavoritesSongs.clear();
        listFavoritesSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.getItems().addAll( listFavoritesSongs );
    }

    /**
     * Metodo que obitne la lista de canciones segun su artista
     */
    private void getSongsByArtist(){
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> {
            int artistComparison = song1.getArtist().getName().compareToIgnoreCase( song2.getArtist().getName() );
            if ( artistComparison != 0 ) {
                return artistComparison; // Ordenar por nombre de artista
            }else{
                return song1.getName().compareToIgnoreCase(song2.getName()); // Si tienen el mismo artista, ordenar por nombre de canción
            }
        });
        listFavoritesSongs.clear();
        listFavoritesSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.getItems().addAll( listFavoritesSongs );
    }

    //------------------------FUNTIONS OF ARTISTVIEWSONG--------------------------------------------------------//

    /**
     * Metodo que filtra las canciones de un artista segun el año en la vie de artista
     * @param event
     */
    @FXML
    void showSongsByYearArtist(ActionEvent event){
        if (isFilteredYear2) {
            isFilteredName2= true;
            isFilteredTime2 = true;
            isFilteredArtist2 = true;

            btnTimeArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnNameArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");

            btnYearArtist.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByYearArtist();

        } else {
            btnYearArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewArtistSongs(artistSelected);
        }
        isFilteredYear2 = !isFilteredYear2;
    }

    /**
     * Metodo que filtra las canciones de un artista segun el nombre en la vie de artista
     * @param event
     */
    @FXML
    void showSongsByNameArtist(ActionEvent event) {
        if (isFilteredName2) {
            isFilteredTime2 = true;
            isFilteredYear2 = true;
            btnTimeArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnYearArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnNameArtist.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByNameArtist();

        } else {
            btnNameArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewArtistSongs(artistSelected);

        }
        isFilteredName2 = !isFilteredName2;
    }

    /**
     * Metodo que filtra las canciones de un artista segun la duracion de la cancion en la vie de artista
     * @param event
     */
    @FXML
    void showSongsByTimeArtist(ActionEvent event) {
        if (isFilteredTime2) {
            isFilteredName2= true;
            isFilteredYear2 = true;

            btnNameArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnYearArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnTimeArtist.setStyle(
                    " -fx-background-color: #a3c0f5; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black;");
            getSongsByTimeArtist();
        } else {
            btnTimeArtist.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            refreshTableViewArtistSongs(artistSelected);
        }
        isFilteredTime2 = !isFilteredTime2;
    }

    /**
     * Metodo que obtine la lista de canciones de un artista segun su año
     */
    private void getSongsByYearArtist() {
        List<Song> aux = artistSelected.getSongList().toList();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getYear()), Integer.parseInt(song1.getYear())));
        listSongsArtist.clear();
        listSongsArtist.addAll( aux );
        tableViewArtist.getItems().clear();
        tableViewArtist.getItems().addAll( listSongsArtist );
    }

    /**
     * Metodo que obtine la lista de canciones de un artista segun su nombre
     */
    private void getSongsByNameArtist() {
        List<Song> aux = artistSelected.getSongList().toList();
        aux.sort((song1, song2) -> {
            int artistComparison = song1.getArtist().getName().compareToIgnoreCase( song2.getArtist().getName() );
            if ( artistComparison != 0 ) {
                return artistComparison; // Ordenar por nombre de artista
            }else{
                return song1.getName().compareToIgnoreCase(song2.getName()); // Si tienen el mismo artista, ordenar por nombre de canción
            }
        });
        listSongsArtist.clear();
        listSongsArtist.addAll( aux );
        tableViewArtist.getItems().clear();
        tableViewArtist.getItems().addAll( listSongsArtist );
    }

    /**
     * Metodo que obtine la lista de canciones de un artista segun su duracion
     */
    private void getSongsByTimeArtist() {
        List<Song> aux = artistSelected.getSongList().toList();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getDuration()), Integer.parseInt(song1.getDuration())));
        listSongsArtist.clear();
        listSongsArtist.addAll( aux );
        tableViewArtist.getItems().clear();
        tableViewArtist.getItems().addAll( listSongsArtist );
    }

//----------------------------Funcionalidades del usuario----------------------------------//

    /**
     * Metodo de boton que deshace una accion
     * @param event
     */
    @FXML
    void revertAction(ActionEvent event) {
        revertActionManager();
    }

    /**
     * Metodo de boton que deshace el like de un usuario a un cancion en la libreria
     * @param event
     */
    @FXML
    void revertActionLikeSongs(ActionEvent event){
        revertActionManager();
        refreshTableViewFavorites();

    }

    /**
     * Metodo de boton que deshace el like de un usuario a un cancion en la view de busqueda
     * @param event
     */
    @FXML
    void revertActionSearch(ActionEvent event){
        revertActionManager();
    }

    /**
     * Metodo de boton que deshace el like de un usuario a un cancion en la view de artista
     * @param event
     */
    @FXML
    void revertActionArtist(ActionEvent event){
        revertActionManager();
    }

    /**
     * Metodo que maneja la logica para deshacer la accion de el usuario
     */
    private void revertActionManager() {
        String userName = userController.mfm.getUserName();
        if ( stateStack.isEmpty() ) {
            showMessage( "Notification" , "Nothing to undo" , "There isn't nothing to undo" , Alert.AlertType.INFORMATION );
        } else {
            if ( stateStack.pop().equals( "add" ) ) { //Si agrego una cancion, la función deshacer lo que hace es eliminarla
                Song songToRemove = songsStack.pop();
                songsDeleteStack.push( songToRemove );
                removeSong( userName , songToRemove );
                stateStack.push( "remove" );
                btnUndo.setText( "Undo" );
                btnUndoArtist.setText( "Undo" );
                btnUndoLikedSongs.setText( "Undo" );
                btnUndoSearch.setText( "Undo" );

                //Añade la cancion a una nueva pila, donde se guardan las canciones eliminadas para poder acceder a ellas depues cuando se haga el rehacer
            } else { //SI elimino la canción , lo que hara la funcón es agregarla
                songSelection = songsDeleteStack.pop(); // La canción seleccionada pasa a ser la ultima adicipon de la cola y procede a agregarse
                songsStack.push( songSelection );
                addSong( user.getUserName() , songSelection );
                stateStack.push( "add" );
                btnUndo.setText( "Redo" );
                btnUndoArtist.setText( "Redo" );
                btnUndoLikedSongs.setText( "Redo" );
                btnUndoSearch.setText( "Redo" );

            }
        }
    }

    /**
     * Metodo de boton que elimina una cancion de la lista de canciones favoritas de el usuario
     * @param event
     */
    @FXML
    void removeFavoriteSongUser(ActionEvent event){
        if(songSelection!=null){
            stateStack.push("remove");
            songsDeleteStack.push(songSelection);
            removeSong( user.getUserName(), songSelection );
            refreshTableViewFavorites();
            userController.mfm.saveDataTest();

        }else{
            showMessage( "Notification", "Song not selected",
                    "Please select a Song" , Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Metodo que muestras la informacion de el artista mas escuchado en la view de estadisticas
     * @param artist
     * @param imageView
     * @param label
     */
    private void displayInfoArtistMostListened(Artist artist, ImageView imageView, Label label) {

        try {
            // Crear el Image y el ImageView
            Image image = new Image( artist.getPhoto());
            label.setText( artist.getName() );
            imageView.setImage( image );
            imageView.setFitWidth(262 );
            imageView.setFitHeight( 209 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //=================================== User utility =======================================//

    /**
     * Metodo de boton que cancela los cambios realizados en la view de configuracion de un usuario
     * @param event
     */
    @FXML
    void cancelChangesSettings(ActionEvent event) {
        txtPasswordSettings.setEditable( false );
        txtEmailSettings.setEditable( false );
        btnCancelChangesSettings.setVisible( false );
        btnConfirmChangesSettings.setVisible( false );
        btnEditInfoSettings.setVisible( true );

        txtPasswordSettings.setText( user.getPassword() );
        txtEmailSettings.setText( user.getEmail() );

    }

    /**
     * Metodo de boton que cambia la informacion de un usuario en la view de configuracion de ususario
     * @param event
     * @throws UserException
     */
    @FXML
    void changeInfoUser(ActionEvent event) throws UserException {
        String name = txtNameSettings.getText();
        String password = txtPasswordSettings.getText();
        String email = txtEmailSettings.getText();

        if(verifyBlankSpaces( email, password )){
            updateInfoUser( name, password,email );
            userController.mfm.saveDataTest();
            txtPasswordSettings.setEditable( false );
            txtEmailSettings.setEditable( false );
            btnCancelChangesSettings.setVisible( false );
            btnConfirmChangesSettings.setVisible( false );
            btnEditInfoSettings.setVisible( true );
        }

    }

    /**
     * Metodo que confirma la actualizacion de los datos de el usuario en la view de configuracion de usuario
     * @param name
     * @param password
     * @param email
     * @throws UserException
     */
    private void updateInfoUser(String name , String password , String email) throws UserException {

        if(userController.mfm.updateUser(name,password,email)){
            showMessage( "Notification", "User updated",
                    "The user info was changed", Alert.AlertType.INFORMATION );
        }else{
            showMessage( "Notification", "User not updated",
                    "The user wasn't changed", Alert.AlertType.INFORMATION );
        }

    }

    /**
     * Metodo que valida los espacios en blanco de la view de condifuracion de usuario
     * @param email
     * @param password
     * @return
     */
    private boolean verifyBlankSpaces(String email, String password) {
        String notification = "";
        if(email.isEmpty()){
            notification += "Type your new email\n";
        }
        if (password.isEmpty()){
            notification += "Type your new password";
        }
        if( notification.isEmpty() )
            return true;
        showMessage("Notification", "Blank space",
                notification, Alert.AlertType.INFORMATION);
        return false;
    }

    /**
     * Metodo de boton que elimian la cuenta de eun usuario en la view de configuracion de usuario
     * @param event
     */
    @FXML
    void deleteAcc(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want delete your account");
        alert.setContentText("This accion can't be reversed");

        // Agregar botones al diálogo
        ButtonType confirmButtonType = new ButtonType("Confirm");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

        // Mostrar el diálogo y esperar la respuesta del usuario
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == confirmButtonType) {
                // El usuario confirmó la acción, realiza la acción deseada
                try {
                    logOut(event);
                    deleleUser();

                } catch (UserException e) {
                    throw new RuntimeException( e );
                }
            }
        });

    }

    /**
     * Instancia de metodo que elimina y serliaza el usuario
     * @throws UserException
     */
    private void deleleUser() throws UserException {
        userController.mfm.deleteUser(user);
        userController.mfm.saveUsers();
    }

    /**
     * Metodo de boton que habilita los campos para editar la configuracion de el usuario
     * @param event
     */
    @FXML
    void editInfoUserSettings(ActionEvent event) {
        txtPasswordSettings.setEditable( true );
        txtEmailSettings.setEditable( true );
        btnCancelChangesSettings.setVisible( true );
        btnConfirmChangesSettings.setVisible( true );
        btnEditInfoSettings.setVisible( false );
    }

    /**
     * Metodo de boton que te permite regresar a el menu princiapl
     * @param event
     */
    @FXML
    void backToHome(ActionEvent event){
        anchorSongsArtist.setVisible( false );
        anchorHome.setVisible( true );
        labelTitle.setText( "Home" );
    }

    /**
     * Metodo de boton que permite agregar una cancion a favoritos desde la view de artista
     * @param event
     * @throws UserException
     * @throws SongException
     */
    @FXML
    void addSongArtistToFavoriteSongs(ActionEvent event) throws UserException, SongException {
        if(!verifySong(songArtistSelection)){
            userController.mfm.addSongToUserList(user.getUserName(), songArtistSelection);
            showTooltip2( "Sond added to your favorites", stage );
            stateStack.push("add");
            songsStack.push(songSearch);
            tableViewArtist.getSelectionModel().clearSelection();




        }else{
            userController.mfm.removeSongFromUserList( user.getUserName(), songArtistSelection);
            showTooltip2( "Sond removed from your's favorites", stage );
            stateStack.push("remove");
            songsDeleteStack.push(songSearch);
            tableViewArtist.getSelectionModel().clearSelection();
        }
        userController.mfm.saveDataTest();

    }

    private boolean verifySong(Song songSelection) {
        return userController.mfm.verifySong(user, songSelection);
    }

    /**
     * Metodo de boton que permite agregar una cancion a favoritos desde la view de busqueda
     * @param event
     * @throws UserException
     * @throws SongException
     */
    @FXML
    void addSongSearchFavorite(ActionEvent event) throws UserException, SongException {
        if(!verifySong(songSearch)){
            userController.mfm.addSongToUserList(user.getUserName(), songSearch);
            tableViewSearch.getSelectionModel().clearSelection();
            stateStack.push("add");
            songsStack.push(songSearch);
            showTooltip2( "Sond added to your favorites", stage );
        }else{
            userController.mfm.removeSongFromUserList( user.getUserName(), songSearch);
            tableViewSearch.getSelectionModel().clearSelection();
            showTooltip2( "Sond removed from your's favorites", stage );
            stateStack.push("remove");
            songsDeleteStack.push(songSearch);
        }
        userController.mfm.saveDataTest();
    }


    //------------------------------------INITIALIZATION---------------------------//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginViewController= new LoginViewController();
        this.userController= new UserController();
        anchorHome.setVisible( false );
        anchorPlayer.setVisible( false );
        anchorLibrary.setVisible( false );
        anchorPlayer.setVisible( false );
        anchorUserSettings.setVisible( false );
        anchorAnalytics.setVisible( false );
        anchorSongsArtist.setVisible( false );
        webView = new WebView();
        webView.setPrefSize(125, 106); // Ajustar el tamaño del WebView
        anchorPlayer.setTopAnchor(webView, 0.0);
        anchorPlayer.setBottomAnchor(webView, 0.0);
        anchorPlayer.setLeftAnchor(webView, 0.0);
        anchorPlayer.setRightAnchor(webView, 0.0);

        btnHome.fire();
        songsStack= new Stack<>();
        stateStack= new Stack<>();
        songsDeleteStack= new Stack<>();

        eventsControll();
        imageFS1.smoothProperty();
        imageFS2.smoothProperty();
        imageFS3.smoothProperty();
        imageFS4.smoothProperty();
        imageFS5.smoothProperty();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( App.class.getResource( "OptionsView.fxml" ) );
        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        OptionsViewController controller = loader.getController();
        this.optionsViewController = controller;
        Stage stage = new Stage();
        Scene scene = new Scene(  anchorPane);
        controller.init( stage );
        controller.setUserViewController( this );

        stage.setScene(  scene);

        stage.initStyle( StageStyle.UNDECORATED );

        btnPlaySongLibrary.setDisable( true);
        btnUnlike.setDisable( true );
        tableViewLikedSongs.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songSelection = newSelection;
                btnUnlike.setDisable( false );
                btnPlaySongLibrary.setDisable( false);

            }else{
                btnUnlike.setDisable( true );
                btnPlaySongLibrary.setDisable( true);


            }
        });
        btnLikeSongArtist.setDisable( true );
        btnPlaySongArtist.setDisable( true );
        tableViewArtist.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songArtistSelection = newSelection;
                if(!verifySong( newSelection )){
                    btnLikeSongArtist.setText( "Like" );
                }else{
                    btnLikeSongArtist.setText( "Unlike" );

                }
                btnPlaySongArtist.setDisable( false );
                btnLikeSongArtist.setDisable( false );

            }else{
                btnLikeSongArtist.setDisable( true );
                btnPlaySongArtist.setDisable( true );


            }
        });
        btnLikeSongSearch.setDisable( true );
        btnPlaySongSearch.setDisable( true );
        tableViewSearch.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songSearch = newSelection;
                if(!verifySong( newSelection )){
                    btnLikeSongSearch.setText( "Like" );
                }else{
                    btnLikeSongSearch.setText( "Unlike" );

                }
                btnPlaySongSearch.setDisable( false );
                btnLikeSongSearch.setDisable( false );
            }else{
                btnLikeSongSearch.setDisable( true );
                btnPlaySongSearch.setDisable( true );
                tableViewSearch.getSelectionModel().clearSelection();


            }
        });

        this.colummImageSearch.setCellValueFactory(new PropertyValueFactory<>("cover"));
        this.columnArtistSearch.setCellValueFactory(new PropertyValueFactory<>("artist"));
        this.columnNameSearch.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnTimeSearch.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnYearSearch.setCellValueFactory(new PropertyValueFactory<>("year"));

        this.columnImage.setCellValueFactory(new PropertyValueFactory<>("cover"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        this.columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        this.columnImageArtist.setCellValueFactory(new PropertyValueFactory<>("cover"));
        this.columnNameSongArtist.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnTimeArtist.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnYearArtist.setCellValueFactory(new PropertyValueFactory<>("year"));
        refreshTableViewSearch();

    }


    @FXML
    void initialize() {

    }

    /**
     * Metodo que controla los eventos de mouse y animaciones
     */
    void eventsControll(){

        btnLogOut.setOnMouseEntered(event -> btnLogOut.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLogOut.setOnMouseExited(event -> btnLogOut.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnHome.setOnMouseEntered(event -> btnHome.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnHome.setOnMouseExited(event -> btnHome.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnSearch.setOnMouseEntered(event -> btnSearch.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnSearch.setOnMouseExited(event -> btnSearch.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnLibrary.setOnMouseEntered(event -> btnLibrary.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLibrary.setOnMouseExited(event -> btnLibrary.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnUserSettings.setOnMouseEntered(event -> btnUserSettings.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnUserSettings.setOnMouseExited(event -> btnUserSettings.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnAnalytics.setOnMouseEntered(event -> btnAnalytics.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnAnalytics.setOnMouseExited(event -> btnAnalytics.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));


        //----------------------------------------------ANIMATIONS------------------------------------------------------

        // Manejar eventos de mouse
        stackFS1.setOnMouseEntered((MouseEvent e) -> {
            stackFS1.setCursor( Cursor.HAND );
            stackFS1.setStyle("-fx-background-color: rgb(237, 179, 161, 0.2); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn(stackFS1);
        });

        stackFS1.setOnMouseExited((MouseEvent e) -> {
            stackFS1.setCursor( Cursor.DEFAULT );
            stackFS1.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut(stackFS1);

        });
        stackFS2.setOnMouseEntered((MouseEvent e) -> {
            stackFS2.setCursor( Cursor.HAND );
            stackFS2.setStyle("-fx-background-color: rgb(237, 179, 161, 0.2); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");

            animationScaleIn( stackFS2 );
        });

        stackFS2.setOnMouseExited((MouseEvent e) -> {
            stackFS2.setCursor( Cursor.DEFAULT );
            stackFS2.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackFS2 );
        });
        stackFS3.setOnMouseEntered((MouseEvent e) -> {
            stackFS3.setCursor( Cursor.HAND );
            stackFS3.setStyle("-fx-background-color: rgb(237, 179, 161, 0.2); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackFS3 );
        });

        stackFS3.setOnMouseExited((MouseEvent e) -> {
            stackFS3.setCursor( Cursor.DEFAULT );
            stackFS3.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackFS3 );
        });
        stackFS4.setOnMouseEntered((MouseEvent e) -> {
            stackFS4.setCursor( Cursor.HAND );
            stackFS4.setStyle("-fx-background-color: rgb(237, 179, 161, 0.2); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackFS4 );
        });

        stackFS4.setOnMouseExited((MouseEvent e) -> {
            stackFS4.setCursor( Cursor.DEFAULT );
            stackFS4.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackFS4 );
        });
        stackFS5.setOnMouseEntered((MouseEvent e) -> {
            stackFS5.setCursor( Cursor.HAND );
            stackFS5.setStyle("-fx-background-color: rgb(237, 179, 161, 0.2); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackFS5 );
        });

        stackFS5.setOnMouseExited((MouseEvent e) -> {
            stackFS5.setCursor( Cursor.DEFAULT );
            stackFS5.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackFS5 );
        });

        stackA1.setOnMouseEntered((MouseEvent e) -> {
            stackA1.setCursor( Cursor.HAND );
            stackA1.setStyle("-fx-background-color: rgb(163, 192, 245, 0.3); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");

            animationScaleIn( stackA1 );
        });

        stackA1.setOnMouseExited((MouseEvent e) -> {
            stackA1.setCursor( Cursor.DEFAULT );
            stackA1.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");

            animationScaleOut( stackA1 );
        });
        stackA2.setOnMouseEntered((MouseEvent e) -> {
            stackA2.setCursor( Cursor.HAND );
            stackA2.setStyle("-fx-background-color: rgb(163, 192, 245, 0.3); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackA2 );
        });

        stackA2.setOnMouseExited((MouseEvent e) -> {
            stackA2.setCursor( Cursor.DEFAULT );
            stackA2.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackA2 );
        });

        stackA3.setOnMouseEntered((MouseEvent e) -> {
            stackA3.setCursor( Cursor.HAND );
            stackA3.setStyle("-fx-background-color: rgb(163, 192, 245, 0.3); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackA3 );
        });

        stackA3.setOnMouseExited((MouseEvent e) -> {
            stackA3.setCursor( Cursor.DEFAULT );
            stackA3.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackA3 );
        });
        stackA4.setOnMouseEntered((MouseEvent e) -> {
            stackA4.setCursor( Cursor.HAND );
            stackA4.setStyle("-fx-background-color: rgb(163, 192, 245, 0.3); " +
                    "-fx-text-fill: black; " +
                    "-fx-background-radius: 5px;");
            animationScaleIn( stackA4 );
        });

        stackA4.setOnMouseExited((MouseEvent e) -> {
            stackA4.setCursor( Cursor.DEFAULT );
            stackA4.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: black;");
            animationScaleOut( stackA4 );
        });

        stackPA.setOnMouseEntered((MouseEvent e) -> {
            stackPA.setCursor( Cursor.NONE );
            animationScaleIn( stackPA );
        });

        stackPA.setOnMouseExited((MouseEvent e) -> {
            stackPA.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackPA );
        });

        stackPAU.setOnMouseEntered((MouseEvent e) -> {
            stackPAU.setCursor( Cursor.NONE );
            animationScaleIn( stackPAU );
        });

        stackPAU.setOnMouseExited((MouseEvent e) -> {
            stackPAU.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackPAU );
        });



        stackFS1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );

                Song song = userController.mfm.getSongByName(lblFS1.getText());
                optionsViewController.setUser( this.user );
                optionsViewController.setSongSelected( song );
                if(user.verifySong( song.getCode() )){
                    optionsViewController.setFavorite( true );
                }else{
                    optionsViewController.setFavorite( false );

                }
                optionsViewController.show();
                optionsViewController.song();
            }
            if(event.getButton()== MouseButton.PRIMARY){
                anchorPlayer.setVisible( true );
                selectedSong = userController.mfm.getSongByName(lblFS1.getText());
                lblPlayer.setText( selectedSong.getName() );
                playSongVideo(selectedSong.getName(),true);//Reanudar video
                playing = true;
                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
            }

        });
        stackFS2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                Song song = userController.mfm.getSongByName(lblFS2.getText());
                optionsViewController.setUser( this.user );
                optionsViewController.setSongSelected( song );
                if(user.verifySong( song.getCode() )){
                    optionsViewController.setFavorite( true );
                }else{
                    optionsViewController.setFavorite( false );

                }
                optionsViewController.show();
                optionsViewController.song();
            }
            if(event.getButton()== MouseButton.PRIMARY){
                anchorPlayer.setVisible( true );
                selectedSong = userController.mfm.getSongByName(lblFS2.getText());
                lblPlayer.setText( selectedSong.getName() );
                playSongVideo(selectedSong.getName(),true);//Reanudar video
                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
            }
        });
        stackFS3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                Song song = userController.mfm.getSongByName(lblFS3.getText());
                optionsViewController.setUser( this.user );
                optionsViewController.setSongSelected( song );
                if(user.verifySong( song.getCode() )){
                    optionsViewController.setFavorite( true );
                }else{
                    optionsViewController.setFavorite( false );

                }
                optionsViewController.show();
                optionsViewController.song();
            }
            if(event.getButton()== MouseButton.PRIMARY){
                anchorPlayer.setVisible( true );
                selectedSong = userController.mfm.getSongByName(lblFS3.getText());
                lblPlayer.setText( selectedSong.getName() );
                playSongVideo(selectedSong.getName(),true);//Reanudar video
                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
            }
        });
        stackFS4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                Song song = userController.mfm.getSongByName(lblFS4.getText());
                optionsViewController.setUser( this.user );
                optionsViewController.setSongSelected( song );
                if(user.verifySong( song.getCode() )){
                    optionsViewController.setFavorite( true );
                }else{
                    optionsViewController.setFavorite( false );

                }
                optionsViewController.show();
                optionsViewController.song();
            }if(event.getButton()== MouseButton.PRIMARY){
                anchorPlayer.setVisible( true );
                selectedSong = userController.mfm.getSongByName(lblFS4.getText());
                lblPlayer.setText( selectedSong.getName() );
                playSongVideo(selectedSong.getName(),true);//Reanudar video
                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
            }
        });
        stackFS5.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                Song song = userController.mfm.getSongByName(lblFS5.getText());
                optionsViewController.setUser( this.user );
                optionsViewController.setSongSelected( song );
                if(user.verifySong( song.getCode() )){
                    optionsViewController.setFavorite( true );
                }else{
                    optionsViewController.setFavorite( false );

                }
                optionsViewController.show();
                optionsViewController.song();
            }
            if(event.getButton()== MouseButton.PRIMARY){
                anchorPlayer.setVisible( true );
                selectedSong = userController.mfm.getSongByName(lblFS5.getText());
                lblPlayer.setText( selectedSong.getName() );
                playSongVideo(selectedSong.getName(),true);//Reanudar video
                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
            }
        });

        stackA1.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                anchorSongsArtist.setVisible( true );
                labelTitle.setText(lblSA1.getText()+"'s songs" );
                Artist artist = userController.mfm.getArtist(lblSA1.getText());
                this.artistSelected = artist;
                refreshTableViewArtistSongs( artist );
            }
        });
        stackA2.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                anchorSongsArtist.setVisible( true );
                labelTitle.setText(lblSA2.getText()+"'s songs" );
                Artist artist = userController.mfm.getArtist(lblSA2.getText());
                this.artistSelected = artist;

                refreshTableViewArtistSongs( artist );
            }
        });

        stackA3.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                anchorSongsArtist.setVisible( true );
                labelTitle.setText(lblSA3.getText()+"'s songs" );
                Artist artist = userController.mfm.getArtist(lblSA3.getText());
                this.artistSelected = artist;

                refreshTableViewArtistSongs( artist );
            }
        });
        stackA4.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                anchorSongsArtist.setVisible( true );
                labelTitle.setText(lblSA4.getText()+"'s songs" );
                Artist artist = userController.mfm.getArtist(lblSA4.getText());
                this.artistSelected = artist;

                refreshTableViewArtistSongs( artist );
            }
        });


        colummImageSearch.setCellFactory(column -> new TableCell<Song, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                if (empty || url == null) {
                    setGraphic(null);
                } else {

                    imageView.setImage(new Image( url ));
                    imageView.setFitHeight(50); // Ajusta el tamaño de la imagen según sea necesario
                    imageView.setFitWidth(50);  // Ajusta el tamaño de la imagen según sea necesario
                    setGraphic(imageView);
                }
            }
        });
        columnImage.setCellFactory( column -> new TableCell<Song, String>() {

            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String url , boolean empty) {
                super.updateItem( url , empty );
                if ( empty || url == null ) {
                    setGraphic( null );
                } else {

                    imageView.setImage( new Image( url ) );
                    imageView.setFitHeight( 50 ); // Ajusta el tamaño de la imagen según sea necesario
                    imageView.setFitWidth( 50 );  // Ajusta el tamaño de la imagen según sea necesario
                    setGraphic( imageView );
                }
            }

        } );

        columnImageArtist.setCellFactory( column -> new TableCell<Song, String>() {

            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String url , boolean empty) {
                super.updateItem( url , empty );
                if ( empty || url == null ) {
                    setGraphic( null );
                } else {

                    imageView.setImage( new Image( url ) );
                    imageView.setFitHeight( 50 ); // Ajusta el tamaño de la imagen según sea necesario
                    imageView.setFitWidth( 50 );  // Ajusta el tamaño de la imagen según sea necesario
                    setGraphic( imageView );
                }
            }

        } );


        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Aquí se ejecuta la acción cuando el texto cambia
                if (!newValue.isEmpty()) {
                    try {
                        List<Song> aux = searchInfo(newValue);
                        System.out.println("Nuevo valor de búsqueda: " + newValue);
                        if (aux != null && !aux.isEmpty()) {
                            System.out.println("Primera canción en aux: " + aux.get(0).getName());

                            Platform.runLater(() -> {
                                listSongs.clear();
                                listSongs.addAll(aux);
                                System.out.println("Contenido de listSongs después de añadir aux: " + listSongs);

                                // Actualizar la TableView
                                tableViewSearch.getItems().clear();
                                tableViewSearch.getItems().addAll(listSongs);
                                System.out.println("TableView actualizada con listSongs.");
                            });
                        }else{
                            tableViewSearch.getItems().clear();

                        }
                    } catch (ArtistException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Platform.runLater(() -> {
                        refreshTableViewSearch();
                    });
                }
            }
        });

        Tooltip tooltip = new Tooltip();

        // Actualizar el texto del Tooltip cuando el usuario interactúa con el PasswordField
        txtPasswordSettings.setOnMouseEntered(event -> showTooltip( txtPasswordSettings.getText(), stage) );

    }

    /**
     * Metodo que muestra mensaje en la interfaz
     * @param message
     * @param stage
     */
    private void showTooltip(String message, Stage stage) {
        // Create a Tooltip
       if(!showingToolTip){
           Tooltip tooltip = new Tooltip(message);
           tooltip.setAutoHide(true);

           // Customize the style of the Tooltip
           tooltip.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f0f0; -fx-text-fill: #333333;");

           // Calculate the position relative to the TextField
           Point2D point = txtPasswordSettings.localToScreen(txtPasswordSettings.getWidth() / 2, txtPasswordSettings.getHeight() / 2);

           // Show the Tooltip near the mouse position
           tooltip.show(stage, point.getX() + 10, point.getY() + 10); // Offset the position slightly

           // Automatically hide the Tooltip after a certain duration
           PauseTransition delay = new PauseTransition(Duration.seconds(0.8)); // Adjust the duration as needed
           delay.setOnFinished(e -> {
               tooltip.hide();
           });
           delay.play();
       }
    }

    /**
     * Metodo que busca la informacion para la view de busqueda
     * @param newValue
     * @return
     * @throws ArtistException
     */
    private List<Song> searchInfo(String newValue) throws ArtistException {
        List<Song> list= userController.mfm.searchArtist(newValue);
        List<Song> list2 = userController.mfm.searchO( newValue );

        if(list2 != null || !list2.isEmpty()){
            list.addAll( list2 );
        }

        return list;
    }

    //================================= Media utilities =======================================//

    /**
     * Metodo de boton que reproduce una cancione seleccionada desde la view principal
     * @param event
     */
    @FXML
    void playSong(ActionEvent event){
        if (playing) {
            imageBtnPlay.setImage(new Image("file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/1.png"));
            playSongVideo(selectedSong.getName(),false); //Pause video
            playing = false;
        } else {
            imageBtnPlay.setImage(new Image("file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png"));
            playSongVideo(selectedSong.getName(),true);//Reanudar video
            playing = true;
        }
    }

    /**
     * Metodo de boton que reproduce una cancione seleccionada desde la view de artista
     * @param event
     */
    @FXML
    void playSongArtist(ActionEvent event){
        songArtistSelection = getSelectedSong(tableViewArtist);
        if (songArtistSelection != null) {
            displayInfoPlayerArtist(songArtistSelection);
        } else {
            System.out.println("No se ha seleccionado ninguna canción.");
        }
    }

    /**
     * Metodo de boton que reproduce una cancione seleccionada desde la view de busqueda
     * @param event
     */
    @FXML
    void playSongSearch(ActionEvent event){
        songSearch = getSelectedSong(tableViewSearch);
        if(songSearch != null){
            displayInfoPlayerSearch(songSearch);
        }else{
            System.out.println("No se ha seleccionado ninguna canción.");
        }
    }

    /**
     * Metodo de boton que reproduce una cancione seleccionada desde la view de libreria
     * @param event
     */
    @FXML
    void playSongLibrary(ActionEvent event){
        songSelection = getSelectedSong(tableViewLikedSongs);
        if(songSelection != null){
            displayInfoPlayerLibrary(songSelection);
        }
    }

    /**
     * Metodo que obtiene la seleccion de el usuario ya sea en la view de busqueda, de libreria o de artista
     * @param tableView
     * @return
     */
    private Song getSelectedSong(TableView<Song> tableView) {
        return tableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Metodo que reproduce el video segun el link de la cancion obtenida por su nombre
     * @param songName
     * @param play
     */
    private void playSongVideo(String songName, Boolean play) {
        Song song = userController.mfm.getSongByName(songName); // Obtener la canción por su nombre
        if (song != null) {
            String videoUrl = song.getLink().toString(); // Obtener el enlace de YouTube de la canción
            //System.out.println((play ? "Reproduciendo" : "Pausando") + " canción: " + songName + " con URL: " + videoUrl); // Mensaje de depuración
            userController.mfm.playSong(webView,videoUrl,play); // Reproducir el video en una nueva ventana de JavaFX
        } else {
            System.out.println("La canción no fue encontrada.");
        }
    }

    /**
     * Metodo que habilita y pone el reprodcutor de la musica desde la view de artista
     * @param artistSelection
     */
    private void displayInfoPlayerArtist(Song artistSelection){
        anchorPlayer.setVisible( true );
        songArtistSelection = userController.mfm.getSongByName(artistSelection.getName());
        lblPlayer.setText( songArtistSelection.getName() );

        displayImageSongPlayer(songArtistSelection);
        imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
        playing = true;
        playSongVideo(songArtistSelection.getName(),true); // Reproducir el video de la canción seleccionada
    }

    /**
     * Metodo que habilita y pone el reprodcutor de la musica desde la view de busqueda
     * @param artistSelection
     */
    private void displayInfoPlayerSearch(Song searchSelection){
        anchorPlayer.setVisible( true );
        songSearch = userController.mfm.getSongByName(searchSelection.getName());
        lblPlayer.setText( songSearch.getName() );

        displayImageSongPlayer(songSearch);
        imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
        playing = true;
        playSongVideo(songSearch.getName(),true); // Reproducir el video de la canción seleccionada
    }

    /**
     * Metodo que habilita y pone el reprodcutor de la musica desde la view de libreria
     * @param artistSelection
     */
    private void displayInfoPlayerLibrary(Song librarySelection){
        anchorPlayer.setVisible( true );
        songSelection = userController.mfm.getSongByName(librarySelection.getName());
        lblPlayer.setText( songSelection.getName() );

        displayImageSongPlayer(songSelection);
        imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
        playing = true;
        playSongVideo(songSelection.getName(),true); // Reproducir el video de la canción seleccionada
    }

    /**
     * Metodo que habilita y pone el reprodcutor de la musica desde la view de principal
     * @param artistSelection
     */
    private void displayInfoPlayer(Label label){
        anchorPlayer.setVisible( true );
        selectedSong = userController.mfm.getSongByName(label.getText());
        lblPlayer.setText( selectedSong.getName() );

        displayImageSongPlayer(selectedSong);
        imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
        playing = true;
        playSongVideo(selectedSong.getName(),true); // Reproducir el video de la canción seleccionada
    }

    /**
     * Metodo que pone la imagen en el reproductor dependiendo de la seleccioanda
     * @param song
     */
    private void displayImageSongPlayer(Song song) {
        try {
            // Crear el Image y el ImageView
            Image image = new Image( song.getCover());
            imagePlayer.setImage( image );
            imagePlayer.setFitWidth(125 );
            imagePlayer.setFitHeight( 106 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==================================== Animations =======================================//

    void animationScaleIn(StackPane stack){
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), stack);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);
        scaleIn.play();


    }

    void animationScaleOut(StackPane stack){
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), stack);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);
        scaleOut.play();
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

            if(optionsViewController!=null){
                optionsViewController.close();
            }

        });

        anchorPane.setOnMouseDragged((MouseEvent event) -> {
            stage.setX( event.getScreenX()-x );
            stage.setY( event.getScreenY()-y );
        });
    }

    //====================================== Functions utilities =========================================//

    /**
     * Metodo que obtiene la lista de canciones
     * @return
     */
    private ObservableList<Song> getSongsObservable() {
        listSongs2.addAll( getSongs());
        return listSongs2;
    }

    /**
     * Metodo que obtiene la lista de canciones favoritas
     * @return
     */
    private ObservableList<Song> getSongsFavorites() {
        listFavoritesSongs2.addAll(getFavoritesSongs() );
        return listFavoritesSongs2;
    }

    /**
     * Metodo que refresca la tabla de busqueda
     */
    void refreshTableViewSearch() {
        listSongs2.clear();
        tableViewSearch.getItems().clear();
        tableViewSearch.setItems( getSongsObservable() );

    }

    /**
     * Metodo que refresaca la tabla de favoritos
     */
    void refreshTableViewFavorites() {
        listFavoritesSongs2.clear();
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( getSongsFavorites() );

    }

    /**
     * Metodo que refresaca la tabla de artistas
     * @param artist
     */
    void refreshTableViewArtistSongs(Artist artist){
        List<Song> aux = artist.getSongList().toList();
        listSongsArtist.clear();
        listSongsArtist.addAll( aux );
        tableViewArtist.getItems().clear();
        tableViewArtist.getItems().addAll( listSongsArtist );
    }
}
