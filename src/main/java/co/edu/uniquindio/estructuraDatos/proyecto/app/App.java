package co.edu.uniquindio.estructuraDatos.proyecto.app;

import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.FirstViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;


public class App extends Application {
    private double y;
    private double x;

    /**
     * Metodo que da inicio a la aplicacion
     * @param primaryStage
     * @throws IOException
     */
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
                        anchorPane.setOnMousePressed( (MouseEvent event) ->{
                            x = event.getSceneX();
                            y= event.getSceneY();
                        });
                        
                        anchorPane.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX( event.getScreenX()-x );
                            stage.setY( event.getScreenY()-y );
                        });
                        FadeTransition fadeIn2 = new FadeTransition( Duration.seconds(1), anchorPane);
                        fadeIn2.setFromValue(0);
                        fadeIn2.setToValue(1);
                        controller.setAnchorPane( anchorPane );
                        stage.initStyle( StageStyle.TRANSPARENT );
                        stage.centerOnScreen();
                        stage.show();
                        fadeIn2.play();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Cerrar el stage actual
                    primaryStage.close();
        });
        fadeOut.play();
    }
}
