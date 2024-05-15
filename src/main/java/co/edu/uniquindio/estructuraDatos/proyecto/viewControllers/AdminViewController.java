package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.persistence.Persistence;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;

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
    private Button btnArtists;

    @FXML
    private Button btnCleanUpSong;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSongs;
    //---------------Elementos de la cancion--------------------
    @FXML
    private ComboBox<String> comboBoxArtist;
    @FXML
    private ImageView imageViewSongPortait;
    @FXML
    private TextField txtNameSong;
    @FXML
    private TextField txtDurationSong;
    @FXML
    private ComboBox<Gender> comboBoxGender;
    @FXML
    private TextField txtYearSong;
    @FXML
    private TextField txtLinkSong;
    @FXML
    private TableView<Song> tableViewSongs;
    @FXML
    private TableColumn<Song, String> columnCodeSong;
    @FXML
    private TableColumn<Song,String > columnNameSong;
    @FXML
    private TableColumn<Song, Artist> columnArtist;
    @FXML
    private TableColumn<Song, String> columnYear;

    @FXML
    private Button btnSelectCover;
    @FXML
    private Button btnAddSong;

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

    @FXML
    private Button btnAddArtist;
    @FXML
    private Button btnCleanUpArtist;

    //------------Variables auxiliares---------------
    private Stage stage;
    private AnchorPane anchorPane;
    private Double x;
    private Double y;
    private LoginViewController loginViewController;
    private AdminController adminController;
    private Artist artistSelection;
    private Song songSelection;
    private ObservableList<Artist> artistsList= FXCollections.observableArrayList();
    private ObservableList<Song> songsList= FXCollections.observableArrayList();
    private ObservableList<String> namesArtist = FXCollections.observableArrayList();


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


    //------------------------------Auxiliars artist's functions-----------------------------
    private void refreshTableViewArtist() throws IOException {
        artistsList.clear();
        tableViewArtists.getItems().clear();
        tableViewArtists.setItems(getArtistsList());
    }
    private ObservableList<Artist> getArtistsList() throws IOException {
        artistsList.addAll(Persistence.loadArtist().toList());
        return artistsList;
    }
    private ObservableList<String> getNamesArtist(){
        namesArtist.addAll(adminController.mfm.getNamesArtistList());
        return namesArtist;
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
    void showArtistsInfo() {
        txtNameArtist.setText(artistSelection.getName());
        txtNationalityArtist.setText(artistSelection.getNationality());
        anchorSongs.setVisible( false );
        anchorArtists.setVisible( true );
    }

//--------------------------Auxiliars song's functios--------------------------------------------

    private ObservableList<Song> getSongsList(){
        songsList.clear();
        songsList.addAll(adminController.mfm.getSongList());
        return songsList;
    }

    private void refreshTableViewSong(){
        tableViewSongs.getItems().clear();
        tableViewSongs.setItems(getSongsList());
    }

    private void showSongInfo(){
        txtNameSong.setText(songSelection.getName());
        txtLinkSong.setText(songSelection.getLink().toString());
        txtYearSong.setText(songSelection.getYear());
        txtDurationSong.setText(songSelection.getDuration());
        comboBoxArtist.getSelectionModel().select(songSelection.getArtist().getName());
        comboBoxGender.getSelectionModel().select(songSelection.getGender());
        imageViewSongPortait.setImage(songSelection.getCover());

    }
    private boolean verifySong(String name, Gender gender, String artist, String year, String duration, URL link, Image image){
        String notification="";
        if (name.isEmpty()){
            notification+= "Type song's name\n";
        }
        if (gender.equals(null)){
            notification+= "Select song's gender\n";
        }
        if (artist.equals(null)){
            notification+= "Type song's artist\n";
        }
        if (year.isEmpty()){
            notification+= "Type song's year\n";
        }
        if (duration.isEmpty()){
            notification+="Type song's duration\n";
        }
        if (link.equals(null)){
            notification+= "Type song's link\n";
        }
        if (image == null){
            notification+= "Select song's image\n";
        }
        if (notification.equals("")) {
            return true;
        }
        showMessage("Notification", "Invalid data", notification, Alert.AlertType.INFORMATION);
        return false;
    }

    private URL obtenerURL() {
        try {
            // Intenta crear un objeto URL con el valor del TextField
            return new URL(txtLinkSong.getText());
        } catch (MalformedURLException e) {
            // Manejo de la excepción en caso de que la URL sea inválida
            e.printStackTrace();
            return null; // Devuelve null si la URL no es válida
        }
    }

    private boolean createSong(String code, String name, Gender gender, String year, String duration, URL link, Image image, Artist artist) {
        try {
            if (adminController.mfm.addSong(code, name, image, year, duration, gender, link, artist)) {
                showMessage("Notification", "Song registered", "Song was registered successfully on artist: '" + artist.getName() + "' list ", Alert.AlertType.INFORMATION);
            }
        } catch (SongException Se) {
            showMessage("Notification", "Song not registered" ,Se.getMessage(), Alert.AlertType.INFORMATION);
        }
        return false;
    }

//--------------------------EVENTOS DE LOS BOTONES-----------------------------------------------

    @FXML
    void addArtist(ActionEvent event) throws IOException {
        String nameArtist= txtNameArtist.getText();
        String nationalityArtist= txtNationalityArtist.getText();
        boolean isAGroup= checkGroupArtist.isSelected();
        if (verifyArtist(nameArtist,nationalityArtist)){
            String code= generateCode(nameArtist,nationalityArtist);
            if (createArtist(code,nameArtist,nationalityArtist,isAGroup)){
                namesArtist.add(nameArtist);
                //adminController.mfm.saveResourceXML();
                adminController.mfm.saveDataTest();
                System.out.printf("Combo box nombre de artistas: " + comboBoxArtist.getItems().toString() );
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

//------------------------------------------------SONGS----------------------------------------------
    @FXML
    void selectCoverSong(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSelectCover.getScene().getWindow();
//        // Cerrar la ventana
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select Cover");
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif");
//        fileChooser.getExtensionFilters().add(extFilter);
//        java.io.File file = fileChooser.showOpenDialog(stage);
//        if (file != null) {
//
//            String absolutePath = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/covers/";
//            String nameFile = file.getName();
//
//            try {
//                Path destiny = Path.of(absolutePath + nameFile);
//                Files.copy(file.toPath(), destiny, StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Archivo copiado correctamente a: " + destiny);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        // Configurar el FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );

        // Mostrar el diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Definir el directorio de destino dentro del proyecto
            String destinationDir = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/covers/";

            // Crear el directorio si no existe
            Path destinationDirPath = Path.of(destinationDir);
            if (!Files.exists(destinationDirPath)) {
                try {
                    Files.createDirectories(destinationDirPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Crear el destino como un Path
            Path destinationPath = destinationDirPath.resolve(selectedFile.getName());

            try {
                // Copiar el archivo seleccionado al destino
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Imagen copiada a: " + destinationPath.toString());

                // Cargar la imagen y mostrarla
                displayImage(destinationPath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void displayImage(String imagePath) {
        try {
            // Crear el Image y el ImageView
            Image image = new Image("file:" + imagePath);
            imageViewSongPortait.setImage( image );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addSong(ActionEvent event) throws ArtistException, SongException {
        String nameSong= txtNameSong.getText();
        String year= txtYearSong.getText();
        String duration= txtDurationSong.getText();
        String artistName= comboBoxArtist.getSelectionModel().getSelectedItem();
        Gender gender= comboBoxGender.getSelectionModel().getSelectedItem();
        Image cover= imageViewSongPortait.getImage();
        URL link= obtenerURL();

        if (verifySong(nameSong,gender,artistName,year,duration,link,cover)){
            String codeSong= generateCode(nameSong,artistName);
            Artist artist= adminController.mfm.getArtist(artistName);
            if (!createSong(codeSong,nameSong,gender,year,duration,link,cover,artist)){
                Song songAux= adminController.mfm.getSong(codeSong);
                adminController.mfm.addSongToArtistList(artistName,songAux);
                //adminController.mfm.saveResourceXML();
                adminController.mfm.saveDataTest();
                System.out.printf("Lista de canciones: " + getSongsList());
                cleanUpSong( event );
                refreshTableViewSong();
            }
        }

    }

    @FXML
    void cleanUpSong(ActionEvent event) {
        txtNameSong.clear();
        txtLinkSong.clear();
        txtYearSong.clear();
        txtDurationSong.clear();
        comboBoxArtist.getSelectionModel().select(null);
        comboBoxGender.getSelectionModel().select(null);
        Image image = new Image("file:" + "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/musica.png");
        imageViewSongPortait.setImage(image);
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
        adminController= new AdminController();
        anchorSongs.setVisible( true );
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
        System.out.printf("Lista canciones: "+ adminController.mfm.getSongList());
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
        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tableViewSongs.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songSelection = newSelection;
                showSongInfo();
            }else{
                //btnEliminarProducto.setDisable( true );
            }
        });
        refreshTableViewSong();
        comboBoxArtist.setItems(getNamesArtist());
        comboBoxGender.getItems().setAll(Gender.values());
        try {
            refreshTableViewArtist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
