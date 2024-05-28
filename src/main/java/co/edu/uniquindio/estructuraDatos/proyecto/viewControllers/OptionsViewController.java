package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.OptionsController;
import co.edu.uniquindio.estructuraDatos.proyecto.controllers.UserController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OptionsViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button btnLike;
    private Stage stage;

    private Boolean isFavorite;

    private Song songSelected;

    private UserViewController userViewController;

    private User user;
    private OptionsController optionsController;

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Song getSongSelected() {
        return songSelected;
    }

    public void setSongSelected(Song songSelected) {
        this.songSelected = songSelected;
    }

    public void setUserViewController(UserViewController userViewController) {
        this.userViewController = userViewController;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void addSongToLikedSongs(ActionEvent event) throws SongException, UserException {
        if(!isFavorite){
            optionsController.mfm.addSongToUserList(user.getUserName(), this.songSelected);
            showTooltip( "Sond added to your favorites", stage );
            optionsController.mfm.saveDataTest();
            userViewController.getStateStack().push( "add" );
            userViewController.getSongsStack().push(songSelected);
            isFavorite= true;
            song();


        }else{
            optionsController.mfm.removeSongFromUserList( user.getUserName(), this.songSelected);
            showTooltip( "Sond removed from your's favorites", stage );
            userViewController.getStateStack().push( "remove" );
            userViewController.getSongsDeleteStack().push( songSelected );
            isFavorite= false;
            optionsController.mfm.saveDataTest();
            song();

        }

    }
    private void showTooltip(String message, Stage stage) {
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

    private void showAlert(String message, Stage ownerStage) {
        // Create a Popup
        Popup popup = new Popup();

        // Create the content of the Popup
        Text text = new Text(message);
        StackPane content = new StackPane(text);
        content.setStyle("-fx-background-color: lightyellow; -fx-padding: 10px; -fx-border-color: black;");
        popup.getContent().add(content);

        // Position the Popup relative to the owner Stage
        popup.setAutoHide(true);
        popup.show(ownerStage);

        // Automatically close the Popup after 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> popup.hide());
        delay.play();
    }


    @FXML
    void initialize() {

        this.optionsController= new OptionsController();
        btnLike.setOnMouseEntered(event -> btnLike.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black; -fx-background-radius: 4px;"));
        btnLike.setOnMouseExited(event -> btnLike.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));



    }
    public void song(){
        if(isFavorite){
            btnLike.setText( "Unlike" );

        }else{
            btnLike.setText( "Like" );

        }
    }

    public void init(Stage stage2) {

        this.stage = stage2;
    }

    public void show() {
        stage.show();

    }

    public void close() {
        stage.close();
    }
}

