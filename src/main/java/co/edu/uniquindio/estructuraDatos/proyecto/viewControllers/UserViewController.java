package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import co.edu.uniquindio.estructuraDatos.proyecto.app.App;
import co.edu.uniquindio.estructuraDatos.proyecto.controllers.UserController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
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
    private OptionsViewController optionsViewController;

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
                Song songAux = songs.get( i );

                switch (i){

                    case 0:
                        assert songAux != null;

                        lblFS1.setText( songAux.getName() );
                        System.out.println( songAux.getCover().getUrl() );
                        displayImage( songAux , imageFS1 );
                        break;
                    case 1:
                        lblFS2.setText( songAux.getName() );
                        System.out.println( songAux.getCover().getUrl() );
                        displayImage( songAux , imageFS2 );
                        break;

                    case 2:
                        lblFS3.setText( songAux.getName() );
                        System.out.println( songAux.getCover().getUrl() );
                        displayImage( songAux , imageFS3 );
                        break;

                    case 3:
                        lblFS4.setText( songAux.getName() );
                        System.out.println( songAux.getCover().getUrl() );
                        displayImage( songAux , imageFS4 );
                        break;

                    case 4:
                        lblFS5.setText( songAux.getName() );
                        System.out.println( songAux.getCover().getUrl() );
                        displayImage( songAux , imageFS5 );
                        break;

                }


            }
        }

    }

    private
    private void displayImage(Song song, ImageView imageView) {

        try {
            // Crear el Image y el ImageView
            Image image = song.getCover();
            imageView.setImage( image );
            imageView.setFitWidth(200 );
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
        stage.setScene(  scene);

        stage.initStyle( StageStyle.UNDECORATED );

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
            animationScaleIn(stackFS1);
        });

        stackFS1.setOnMouseExited((MouseEvent e) -> {
            stackFS1.setCursor( Cursor.DEFAULT );
            animationScaleOut(stackFS1);

        });
        stackFS2.setOnMouseEntered((MouseEvent e) -> {
            stackFS2.setCursor( Cursor.HAND );
            animationScaleIn( stackFS2 );
        });

        stackFS2.setOnMouseExited((MouseEvent e) -> {
            stackFS2.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackFS2 );
        });
        stackFS3.setOnMouseEntered((MouseEvent e) -> {
            stackFS3.setCursor( Cursor.HAND );
            animationScaleIn( stackFS3 );
        });

        stackFS3.setOnMouseExited((MouseEvent e) -> {
            stackFS3.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackFS3 );
        });
        stackFS4.setOnMouseEntered((MouseEvent e) -> {
            stackFS4.setCursor( Cursor.HAND );
            animationScaleIn( stackFS4 );
        });

        stackFS4.setOnMouseExited((MouseEvent e) -> {
            stackFS4.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackFS4 );
        });
        stackFS5.setOnMouseEntered((MouseEvent e) -> {
            stackFS5.setCursor( Cursor.HAND );
            animationScaleIn( stackFS5 );
        });

        stackFS5.setOnMouseExited((MouseEvent e) -> {
            stackFS5.setCursor( Cursor.DEFAULT );
            animationScaleOut( stackFS5 );
        });

        stackFS1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                optionsViewController.show();
            }
        });
        stackFS2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                optionsViewController.show();
            }
        });
        stackFS3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                optionsViewController.show();
            }
        });
        stackFS4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                optionsViewController.show();
            }
        });
        stackFS5.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verificar si el clic es con el botón derecho
                // Obtener las coordenadas del clic y posicionar la ventana
                optionsViewController.getStage().setX( event.getScreenX() );
                optionsViewController.getStage().setY( event.getScreenY() );
                optionsViewController.show();
            }
        });


    }
    void loadOptionsView(){
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
        stage.setScene(  scene);

        stage.initStyle( StageStyle.UNDECORATED );
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


}
