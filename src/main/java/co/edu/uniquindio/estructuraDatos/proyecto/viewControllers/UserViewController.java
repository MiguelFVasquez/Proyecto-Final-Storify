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
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Label labelTitle;
    @FXML
    private Label lblPlayer;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnLibrary;
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnDeshacer;

    @FXML
    private Button btnTime;
    @FXML
    private Button btnName;
    @FXML
    private Button btnArtist;
    @FXML
    private Button btnUnlike;
    @FXML
    private Button btnYear;
    @FXML
    private Button btnSearchSong;


    @FXML
    private TableColumn<Song, String> colummImageSearch;

    @FXML
    private TableColumn<?, ?> columnArtistSearch;

    @FXML
    private TableColumn<Song, String> columnImage;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnYear;

    @FXML
    private TableColumn<?, ?> columnNameArtist;

    @FXML
    private TableColumn<?, ?> columnNameSearch;

    @FXML
    private TableColumn<?, ?> columnTime;

    @FXML
    private TableColumn<Song, String> columnTimeSearch;

    @FXML
    private TableView<Song> tableViewLikedSongs;

    @FXML
    private TableView<Song> tableViewSearch;

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
    private boolean isFilteredArtist = true;
    private boolean isFilteredName = true;
    private boolean isFilteredTime = true;
    private boolean isFilteredYear= true;


    private User user;
    private boolean playing= false;
    private WebView webView;
    private Song selectedSong;

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
        showArtist( getArtists() );

    }
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
    private void showArtist(List<Artist> artists) {
        int n= 0;
        if ( !artists.isEmpty() ) {
            for (int i = artists.size()-1; i >= 0; i--) {
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
    private void displayImageSong(Song song, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = new Image( song.getCover());
            imageView.setImage( image );
            imageView.setFitWidth(183 );
            imageView.setFitHeight( 160 );
//
//            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
//            clip.setArcWidth(20); // Cambia este valor según el grado de redondeo que desees
//            clip.setArcHeight(20);
//            imageView.setClip( clip );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayImageArtists(Artist artist, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = new Image( artist.getPhoto());
            imageView.setImage( image );
            imageView.setFitWidth(190 );
            imageView.setFitHeight( 170 );
//
//            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
//            clip.setArcWidth(20); // Cambia este valor según el grado de redondeo que desees
//            clip.setArcHeight(20);
//            imageView.setClip( clip );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Song> getSongs(){
        return userController.mfm.getSongList();
    }
    public List<Song> getFavoritesSongs(){
        return user.getSongToList();
    }
    public List<Artist> getArtists(){
        return userController.mfm.getListArtist();
    }

    @FXML
    void showLibraryInfo(ActionEvent event) {
        labelTitle.setText( "Library" );
        anchorLibrary.setVisible( true );
        anchorSearch.setVisible( false );
        anchorHome.setVisible( false );
        refreshTableViewFavorites();

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
        optionsViewController.close();
        logout();
        this.stage.close();
    }

    private void logout() {
        if (playing) {
            playSongVideo(selectedSong.getName(), false); // Pausar el video antes de cerrar sesión
        }
    }

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

    @FXML
    void showSongsByYear(ActionEvent event){
        if (isFilteredYear) {
            isFilteredName= true;
            isFilteredTime = true;
            isFilteredArtist = true;

            btnYear.setStyle("-fx-background-color: transparent;" +
                    "    -fx-text-fill: black;" +
                    "    -fx-border-color: #a3c0f5;");
            btnYear.setStyle("-fx-background-color: transparent;" +
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

    private void getSongsByName() {
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> song1.getName().compareToIgnoreCase(song2.getName()));
        listSongs.clear();
        listSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( listSongs );
    }

    private void getSongsByYear(){
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getYear()), Integer.parseInt(song1.getYear())));
        listSongs.clear();
        listSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( listSongs );
    }
    private void getSongsByTime(){
        List<Song> aux = getFavoritesSongs();
        aux.sort((song1, song2) -> Integer.compare(Integer.parseInt(song2.getDuration()), Integer.parseInt(song1.getDuration())));
        listSongs.clear();
        listSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( listSongs );
    }

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
        listSongs.clear();
        listSongs.addAll( aux );
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( listSongs );
    }




//----------------------------Funcionalidades del usuario----------------------------------


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

            }
        }
    }
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

    private void playSongVideo(String songName, Boolean play) {
        Song song = userController.mfm.getSongByName(songName); // Obtener la canción por su nombre
        if (song != null) {
            String videoUrl = song.getLink().toString(); // Obtener el enlace de YouTube de la canción
            System.out.println((play ? "Reproduciendo" : "Pausando") + " canción: " + songName + " con URL: " + videoUrl); // Mensaje de depuración
            userController.mfm.playSong(webView,videoUrl,play); // Reproducir el video en una nueva ventana de JavaFX
        } else {
            System.out.println("La canción no fue encontrada.");
        }
    }

    @FXML
    void removeFavoriteSongUser(ActionEvent event){
        if(songSelection!=null){
            removeSong( user.getUserName(), songSelection );
            refreshTableViewFavorites();
            userController.mfm.saveDataTest();

        }else{
            showMessage( "Notification", "Song not selected",
                    "Please select a Song" , Alert.AlertType.INFORMATION);
        }

    }



    //------------------------------------INITIALIZATION-------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginViewController= new LoginViewController();
        this.userController= new UserController();
        anchorHome.setVisible( false );
        anchorPlayer.setVisible( false );
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


        btnUnlike.setDisable( true );
        tableViewLikedSongs.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songSelection = newSelection;
                btnUnlike.setDisable( false );

            }else{
                btnUnlike.setDisable( true );

            }
        });

        this.colummImageSearch.setCellValueFactory(new PropertyValueFactory<>("cover"));
        this.columnArtistSearch.setCellValueFactory(new PropertyValueFactory<>("artist"));
        this.columnNameSearch.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnTimeSearch.setCellValueFactory(new PropertyValueFactory<>("duration"));

        this.columnImage.setCellValueFactory(new PropertyValueFactory<>("cover"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        this.columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        refreshTableViewSearch();

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

                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
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

                displayImageSongPlayer(selectedSong);
                imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
                playing = true;
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



        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Aquí se ejecuta la acción cuando el texto cambia
                if(!newValue.isEmpty()){
                    try {

                        List<Song> aux = searchInfo(newValue);
                        if ( aux != null && !aux.isEmpty() ) {
                            System.out.println( aux.get( 0 ).getName() );

                            listFavoritesSongs2.clear();
                            listFavoritesSongs2.addAll( aux );
                            System.out.println(listFavoritesSongs2);
                            tableViewSearch.getItems().clear();
                            tableViewSearch.setItems( listFavoritesSongs2 );
                        }

                    } catch (ArtistException e) {
                        throw new RuntimeException( e );
                    }
                }else{
                    refreshTableViewSearch();
                }
            }
        });


    }

    @FXML
    void searchInfoSong(ActionEvent event) throws ArtistException {

    }

    private List<Song> searchInfo(String newValue) throws ArtistException {
        List<Song> list= userController.mfm.searchArtist(newValue);
        //List<Song> list2 = userController.mfm.searchO( newValue );

//        if(list2 != null || !list2.isEmpty()){
//            list.addAll( list2 );
//        }

        return list;
    }

    private void displayInfoPlayer(Label label){
        anchorPlayer.setVisible( true );
        selectedSong = userController.mfm.getSongByName(label.getText());
        lblPlayer.setText( selectedSong.getName() );

        displayImageSongPlayer(selectedSong);
        imageBtnPlay.setImage( new Image( "file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/playerItems/pause.png" ) );
        playing = true;
        playSongVideo(selectedSong.getName(),true); // Reproducir el video de la canción seleccionada
    }

    private void displayImageSongPlayer(Song song) {
        try {
            // Crear el Image y el ImageView
            Image image = new Image( song.getCover());
            imagePlayer.setImage( image );
            imagePlayer.setFitWidth(125 );
            imagePlayer.setFitHeight( 106 );
//
//            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
//            clip.setArcWidth(20); // Cambia este valor según el grado de redondeo que desees
//            clip.setArcHeight(20);
//            imageView.setClip( clip );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private ObservableList<Song> getSongsObservable() {
        listSongs2.addAll( getSongs());
        return listSongs2;
    }
    private ObservableList<Song> getSongsFavorites() {
        listFavoritesSongs2.addAll(getFavoritesSongs() );
        return listFavoritesSongs2;
    }

    void refreshTableViewSearch() {
        listSongs2.clear();
        tableViewSearch.getItems().clear();
        tableViewSearch.setItems( getSongsObservable() );

    }
    void refreshTableViewFavorites() {
        listFavoritesSongs2.clear();
        tableViewLikedSongs.getItems().clear();
        tableViewLikedSongs.setItems( getSongsFavorites() );

    }


}
