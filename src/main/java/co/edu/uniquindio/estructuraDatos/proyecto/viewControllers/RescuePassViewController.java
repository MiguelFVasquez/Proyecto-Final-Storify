package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RescuePassViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorSecond;

    @FXML
    private AnchorPane anchorFirst;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtNameAcc;

    @FXML
    private TextField txtcode1;

    @FXML
    private TextField txtcode2;

    @FXML
    private TextField txtcode3;

    @FXML
    private TextField txtcode4;

    @FXML
    private TextField txtcode5;
    private Stage stage;
    private AnchorPane anchorPane;

    public void init(Stage stage2) {
        this.stage = stage2;
    }
    public void show() {
        FadeTransition fadeIn = new FadeTransition( Duration.seconds(1) , anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        stage.show();
        fadeIn.play();

    }


    @FXML
    void closeWindow(ActionEvent event) {
        anchorSecond.setVisible( false );
        anchorFirst.setVisible( false );
        FadeTransition fadeOut = new FadeTransition( Duration.seconds( 1 ) , anchorPane  );
        fadeOut.setFromValue( 0.5 );
        fadeOut.setToValue( 0 );
        fadeOut.play();
        fadeOut.setOnFinished( evt -> {
            this.stage.close();

        });
    }

    @FXML
    void searchEmail(ActionEvent event) {
        anchorSecond.setVisible( true );
    }

    @FXML
    void initialize() {
        eventsManager();
        anchorSecond.setVisible( false );
        txtNameAcc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnSubmit.setDisable(newValue.isEmpty());
            }
        });

    }
    public void eventsManager(){
        btnSubmit.setDisable( true );
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            if (change.getControlNewText().matches("\\d")) {
                return change;
            } else {
                change.setText(""); // else make no change
                change.setRange(change.getRangeStart(), change.getRangeStart());
                return change;

            }
        };

        txtcode1.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode2.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode3.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode4.setTextFormatter(new TextFormatter<>(numberFilter));
        txtcode5.setTextFormatter(new TextFormatter<>(numberFilter));

        txtcode1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode2.requestFocus();
            }
        });

        txtcode2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode3.requestFocus();
            }
        });

        txtcode3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode4.requestFocus();
            }
        });

        txtcode4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                txtcode5.requestFocus();
            }
        });

        txtcode5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {

            }
        });
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
}
