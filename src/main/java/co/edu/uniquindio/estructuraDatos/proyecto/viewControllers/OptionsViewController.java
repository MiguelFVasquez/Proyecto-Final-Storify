package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OptionsViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddQueue;

    @FXML
    private Button btnLike;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void addSongToLikedSongs(ActionEvent event) {

    }

    @FXML
    void addSongToQueue(ActionEvent event) {

    }

    @FXML
    void initialize() {
        btnLike.setOnMouseEntered(event -> btnLike.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black; -fx-background-radius: 4px;"));
        btnLike.setOnMouseExited(event -> btnLike.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));

        btnAddQueue.setOnMouseEntered(event -> btnAddQueue.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnAddQueue.setOnMouseExited(event -> btnAddQueue.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));

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

