package co.edu.uniquindio.estructuraDatos.proyecto.app;

import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.FirstViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        AnchorPane root = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "First.fxml" ) ) );

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();


        primaryStage.setScene(new Scene(root, 554, 578));
        primaryStage.initStyle( StageStyle.UNDECORATED );
        primaryStage.centerOnScreen();
        primaryStage.show();

        // Al cerrar la ventana, cargar y mostrar el segundo archivo FXML con FadeOut

        // Cargar y mostrar el segundo archivo FXML
        FadeTransition fadeOut = new FadeTransition( Duration.seconds( 2 ) , root );
        fadeOut.setFromValue( 1 );
        fadeOut.setToValue( 0 );
        fadeOut.setOnFinished( evt -> {
                    // Cargar y mostrar el segundo archivo FXML
                    try {
                        //AnchorPane root2 = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "LoginView.fxml" ) ) );
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( App.class.getResource( "LoginView.fxml" ) );
                        AnchorPane anchorPane = loader.load();
                        LoginViewController controller = loader.getController();
                        Stage stage = new Stage();
                        stage.setScene( new Scene( anchorPane , 1365 , 715 ) );
                        controller.init( stage );
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Cerrar el stage actual
                    primaryStage.close();
        });
        fadeOut.play();
    }
        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( App.class.getResource( "First.fxml" ) );
        AnchorPane anchorPane = loader.load();
        FirstViewController controller = loader.getController();
        Scene scene = new Scene( anchorPane );
        Stage stage = new Stage();
        stage.setScene( scene );
        stage.initStyle( StageStyle.UNDECORATED );
        controller.init( stage );
        FadeTransition fadeIn = new FadeTransition( Duration.seconds(1), stage.getScene().getRoot());
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Crear una transición de fade out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), stage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Configurar un timeline para coordinar el fade in y el fade out
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event2 -> {
                    // Mostrar la ventana y comenzar el fade in
                    controller.show();
                    fadeIn.play();
                }),
                new KeyFrame( Duration.seconds(1), event2 -> {
                    // Comenzar el fade out después de que termine el fade in
                    fadeIn.stop();
                    fadeOut.play();
                }),
                new KeyFrame(Duration.seconds(3), event2 -> {
                    // Cerrar la ventana después de que termine el fade out

                    stage.close();
                })
        );

        // Iniciar el timeline
        timeline.play();

        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation( App.class.getResource( "LoginView.fxml" ) );
        AnchorPane anchorPane1 = loader1.load();
        LoginViewController loginViewController = loader1.getController();
        Scene scene2 = new Scene( anchorPane1 );
        Stage stage2 = new Stage();
        stage2.setScene( scene2 );
        stage2.initStyle( StageStyle.UNDECORATED );
        loginViewController.init( stage2 );
        FadeTransition fadeIn2 = new FadeTransition( Duration.seconds(1), stage2.getScene().getRoot());
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);*/


}
