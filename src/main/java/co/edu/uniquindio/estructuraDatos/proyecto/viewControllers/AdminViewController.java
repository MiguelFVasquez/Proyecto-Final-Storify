package co.edu.uniquindio.estructuraDatos.proyecto.viewControllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.persistence.Persistence;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
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
    //---------------Elementos de la cancion--------------------//
    @FXML
    private ComboBox<String> comboBoxArtist;
    @FXML
    private ImageView imageViewSongPortait;
    @FXML
    private ImageView imageViewArtist;
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
    private Button btnSelectPhotoArtist;
    @FXML
    private Button btnAddSong;
    @FXML
    private Button btnCancelChangesSong;
    @FXML
    private Button btnConfirmChangesSong;
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnRemoveSong;
    //-----------Elementos del artista--------//
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
    private Button btnEditArtist;
    @FXML
    private Button btnCleanUpArtist;
    @FXML
    private Button btnConfirmChangesArtist;
    @FXML
    private Button btnCancelChanges;
    @FXML
    private Button btnRemoveArtist;


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


    //-------------------Auxiliars functions-----------------------//

    /**
     * Metodo que crea mensajes para mostrar en la interfaz
     * @param title
     * @param header
     * @param content
     * @param alertype
     */
    public void showMessage(String title, String header, String content, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Metodo que genera el codigo para una cancion
     * @param word1
     * @param word2
     * @return
     */
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

    /**
     * Metodo que crea el tootltipo en la view de el administrador para mostrar mensajes
     * @param s
     * @param stage
     */
    private void showPopUp(String s , Stage stage) {
        // Crear el contenido del "Tooltip"
        Popup popup = new Popup();

        // Crear el contenido del Popup
        Text text = new Text(s);
        StackPane content = new StackPane(text);
        content.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);" +
                "    -fx-padding: 10px;" +
                "    -fx-border-color: black;" +
                "    -fx-border-radius: 5px;" +
                "    -fx-background-radius: 5px;" +
                "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0.5, 0, 0);" +
                "    -fx-text-fill: white;");
        popup.getContent().add(content);
        text.setFill( Color.WHITE);

        // Obtener el centro del Stage
        double centerX = stage.getX() + stage.getWidth() / 2;
        double centerY = stage.getY() + stage.getHeight() / 2;

        // Mostrar el Popup para calcular sus dimensiones
        popup.show(stage);
        // Ahora que el Popup está mostrado, podemos obtener su ancho y alto
        double popupWidth = popup.getWidth();
        double popupHeight = popup.getHeight();
        // Ocultarlo nuevamente para reposicionarlo correctamente
        popup.hide();

        // Posicionar el Popup en el centro del Stage
        double popupX = centerX - popupWidth / 2;
        double popupY = centerY - popupHeight / 2;

        // Mostrar el Popup en la posición calculada
        popup.show(stage, popupX, popupY);

        // Cerrar automáticamente el Popup después de 3 segundos
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> popup.hide());
        delay.play();
    }


    //------------------------------Auxiliars artist's functions-----------------------------//

    /**
     * Metodo que refresca los items de la tabla de artistas
     * @throws IOException
     */
    private void refreshTableViewArtist() throws IOException {
        artistsList.clear();
        tableViewArtists.getItems().clear();
        tableViewArtists.setItems(getArtistsList());
    }

    /**
     * Metodo que obtiene la lista de los artistas
     * @return
     * @throws IOException
     */
    private ObservableList<Artist> getArtistsList() throws IOException {
        artistsList.addAll(adminController.mfm.getListArtist());
        return artistsList;
    }

    /**
     * Metodo que obtiene la lista el nombre de los artistas
     * @return
     */
    private ObservableList<String> getNamesArtist(){
        namesArtist.addAll(adminController.mfm.getNamesArtistList());
        return namesArtist;
    }

    /**
     * Metodo que verifica los espacios en blanco de los textFields
     * @param name
     * @param nationality
     * @return
     */
    private boolean verifySpacesArtist(String name, String nationality){
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

    /**
     * Metodo que crea el objeto artista
     * @param code
     * @param name
     * @param nationality
     * @param image
     * @param isAGroup
     * @return
     */
    private boolean createArtist(String code, String name, String nationality, String image , boolean isAGroup){
        try {
            if (adminController.mfm.addArtist(code,name,nationality, image, isAGroup)){
                showMessage("Notification", "Artist registered","Artist was registered successfully", Alert.AlertType.INFORMATION);
                return true;
            }
        } catch (ArtistException Ae) {
            showMessage("Notification","Artist not registered", Ae.getMessage(), Alert.AlertType.INFORMATION);
        }
        return false;
    }
    void showArtistsInfo() {

        anchorSongs.setVisible( false );
        anchorArtists.setVisible( true );
    }

//--------------------------Auxiliars song's functios--------------------------------------------//

    /**
     * Metodo que obtiene la lista de canciones
     * @return
     */
    private ObservableList<Song> getSongsList(){
        songsList.clear();
        songsList.addAll(adminController.mfm.getSongList());
        return songsList;
    }

    /**
     * Metodo que refresca los items de la tabla de canciones
     */
    private void refreshTableViewSong(){
        tableViewSongs.getItems().clear();
        tableViewSongs.setItems(getSongsList());
    }

    /**
     * Metodo que muestra la informacion de una cancion
     */
    private void showSongInfo(){
        txtNameSong.setEditable( false );
        comboBoxGender.setDisable( true);
        comboBoxArtist.setDisable( true);
        txtYearSong.setEditable( false );
        txtDurationSong.setEditable( false );
        txtLinkSong.setEditable( false );
        btnSelectCover.setDisable( true );

        txtNameSong.setText(songSelection.getName());
        txtLinkSong.setText(songSelection.getLink().toString());
        txtYearSong.setText(songSelection.getYear());
        txtDurationSong.setText(songSelection.getDuration());
        comboBoxArtist.getSelectionModel().select(songSelection.getArtist().getName());
        comboBoxGender.getSelectionModel().select(songSelection.getGender());
        imageViewSongPortait.setImage(new Image( songSelection.getCover() ));

    }

    /**
     * Metodo que verifica los espacio en blanco de una cancion
     * @param name
     * @param gender
     * @param artist
     * @param year
     * @param duration
     * @param link
     * @param image
     * @return
     */
    private boolean verifySong(String name, Gender gender, String artist, String year, String duration, String link, Image image){
        String notification="";
        if (name.isEmpty()){
            notification+= "Type song's name\n";
        }
        if (gender == null){
            notification+= "Select song's gender\n";
        }
        if (artist == null || artist.isEmpty() ){
            notification+= "Type song's artist\n";
        }
        if (year.isEmpty()){
            notification+= "Type song's year\n";
        }else {
            if (!isNumber(year)) {
                notification += "Type song's year in numbers\n";
            }
        }
        if (duration.isEmpty()){
            notification+="Type song's duration\n";
        }else {
            if (!isNumber(duration)) {
                notification += "Type song's duration in numbers\n";
            }
        }
        if (link == (null) || link.isEmpty()){
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

    /**
     * Metodo que valida que dato ingresado sea un numero
     * @param string
     * @return
     */
    private boolean isNumber(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo que obtiene la url de una cancion
     * @return
     */
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

    /**
     * Metodo que crea un objeto de tipo cancion
     * @param code
     * @param name
     * @param gender
     * @param year
     * @param duration
     * @param link
     * @param image
     * @param artist
     */
    private void createSong(String code, String name, Gender gender, String year, String duration, URL link, String image, Artist artist) {
        try {
            if (adminController.mfm.addSong(code, name, image, year, duration, gender, link, artist)) {
                showMessage("Notification", "Song registered", "Song was registered successfully on artist: '" + artist.getName() + "' list ", Alert.AlertType.INFORMATION);
            }
        } catch (SongException Se) {
            showMessage("Notification", "Song not registered" ,Se.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

//--------------------------EVENTOS DE LOS BOTONES-----------------------------------------------//

    /**
     * Metodo que agrega un artista a el arbol cuando se preciona el boton en la interfaz
     * @param event
     * @throws IOException
     */
    @FXML
    void addArtist(ActionEvent event) throws IOException {
        String nameArtist= txtNameArtist.getText();
        String nationalityArtist= txtNationalityArtist.getText();
        boolean isAGroup= checkGroupArtist.isSelected();
        if(verifySpacesArtist(nameArtist,nationalityArtist)){
            if ( imageViewArtist.getImage()!=null){
                String code= generateCode(nameArtist,nationalityArtist);
                if (createArtist(code,nameArtist,nationalityArtist, imageViewArtist.getImage().getUrl(), isAGroup)){
                    namesArtist.add(nameArtist);
                    //adminController.mfm.saveResourceXML();
                    adminController.mfm.saveDataTest();
                    System.out.printf("Combo box nombre de artistas: " + comboBoxArtist.getItems().toString() );
                    cleanUpArtist(event);
                    refreshTableViewArtist();
                }
            }else{
                showMessage( "Notification" , "Invalid data", "Please select a photo", Alert.AlertType.INFORMATION );
            }
        }

    }

    /**
     * Metodo que limpia los campos de texto para crear y editar un artista
     * @param event
     */
    @FXML
    void cleanUpArtist(ActionEvent event) {
        createArtistForm();
        txtNameArtist.clear();
        txtNationalityArtist.clear();
        checkGroupArtist.setSelected( false );
        imageViewArtist.setImage( null );
    }

    /**
     * Metodo que permite editar los datos de un artista
     * @param event
     */
    @FXML
    void editInfoArtists(ActionEvent event) {
        txtNationalityArtist.setEditable( true );
        checkGroupArtist.setDisable( false );
        btnSelectPhotoArtist.setDisable(false );

        btnAddArtist.setVisible( false );
        btnCleanUpArtist.setVisible( false );
        btnConfirmChangesArtist.setVisible( true );
        btnCancelChanges.setVisible( true );

        showPopUp( "Now you can edit the info of the artist on the fields at the top" , stage);
    }

    /**
     * Metodo que actualiza los datos de el artista
     * @param event
     * @throws ArtistException
     * @throws IOException
     */
    @FXML
    void updateInfoArtist(ActionEvent event) throws ArtistException, IOException {
        String name = txtNameArtist.getText();
        String nacionality = txtNationalityArtist.getText();
        boolean check = checkGroupArtist.isSelected();
        String cover = imageViewArtist.getImage().getUrl();
        if(verifySpacesArtist( name, nacionality )){
            Artist artist = new Artist(generateCode(name, nacionality), name, nacionality, cover, check);
            updateArtist( artist );
            tableViewArtists.getSelectionModel().clearSelection();
            cleanUpArtist( event );
            refreshTableViewArtist();
            adminController.mfm.saveDataTest();


        }

    }

    /**
     * Metodo que permite habilitar y deshabilitar los campos para crear un artista
     */
    void createArtistForm(){
        txtNameArtist.setEditable( true );
        txtNationalityArtist.setEditable( true );
        checkGroupArtist.setDisable( false );
        btnSelectPhotoArtist.setDisable(false );

        tableViewArtists.getSelectionModel().clearSelection();
        btnAddArtist.setVisible( true );
        btnCleanUpArtist.setVisible( true );
        btnConfirmChangesArtist.setVisible( false );
        btnCancelChanges.setVisible( false );
    }

    /**
     * Metodo que permite habilitar y deshabilitar los campos para crear una cancion
     */
    void createSongForm(){
        txtNameSong.setEditable( true );
        comboBoxGender.setDisable( false );
        comboBoxArtist.setDisable( false );
        txtYearSong.setEditable( true );
        txtDurationSong.setEditable( true );
        txtLinkSong.setEditable( true );
        btnSelectCover.setDisable( false );


        tableViewSongs.getSelectionModel().clearSelection();
        btnAddSong.setVisible( true );
        btnCleanUpSong.setVisible( true );
        btnConfirmChangesSong.setVisible( false );
        btnCancelChangesSong.setVisible( false );
    }

    /**
     * Metodo que confirma los datos actualizados de un artista
     * @param artist
     * @throws ArtistException
     */
    private void updateArtist(Artist artist) throws ArtistException {

        boolean flag = adminController.mfm.updateArtist( artist );
        if(flag){
                showMessage( "Notification", "Artist's information changed" ,
                        "The infomation of" + artistSelection.getName()+" has been changed", Alert.AlertType.INFORMATION );
        }else{
            showMessage( "Notification", "Artist's  information not changed" ,
                    "The infomation of" + artistSelection.getName()+" hasn't changed", Alert.AlertType.INFORMATION );
        }

    }

    /**
     * Bonton que cancela la edicion de los datos de el artista
     * @param event
     */
    @FXML
    void cancelInfoUpdate(ActionEvent event) {
        createArtistForm();
    }

    /**
     * Boton que cancela la edicion de los datos de una cancion
     * @param event
     */
    @FXML
    void cancelInfoSongUpdate(ActionEvent event) {
        cleanUpSong( event );
    }

    /**
     * Metodo de boton que permite actualizar los datos de una cancion
     * @param event
     * @throws SongException
     */
    @FXML
    void updateInfoSong(ActionEvent event) throws SongException {
        String nameSong= txtNameSong.getText();
        String year= txtYearSong.getText();
        String duration= txtDurationSong.getText();
        String artistName= comboBoxArtist.getSelectionModel().getSelectedItem();
        Gender gender = comboBoxGender.getSelectionModel().getSelectedItem();
        Image cover= imageViewSongPortait.getImage();
        String links = txtLinkSong.getText();
        if(verifySong( nameSong,gender,artistName,year,duration,links,cover )){
            URL link= obtenerURL();
            if(link!=null){
                Artist artist= adminController.mfm.getArtist(artistName);
                uptadeSong( songSelection.getCode() , nameSong , gender , year , duration , link , cover.getUrl() , artist );

                adminController.mfm.saveDataTest();
                cleanUpSong( event );
                refreshTableViewSong();
            }else{
                showMessage( "Notification", "Invalid link",
                    "Please enter a valid link", Alert.AlertType.INFORMATION );
            }

            }


    }

    /**
     * Metodo que confirma la actualizacion de los datos de una cancion
     * @param code
     * @param nameSong
     * @param gender
     * @param year
     * @param duration
     * @param link
     * @param url
     * @param artist
     * @throws SongException
     */
    private void uptadeSong(String code , String nameSong , Gender gender , String year , String duration , URL link , String url , Artist artist) throws SongException {
        if(adminController.mfm.updateSong(code, nameSong, gender,year,duration,link, url, artist)){
            showMessage( "Notification", "Info changed" , "The info of the song has been changed", Alert.AlertType.INFORMATION );

        }else{
            showMessage( "Notification", "Info no changed" , "The info of the song hasn't changed", Alert.AlertType.INFORMATION );
        }
    }

    /**
     * Metodo de boton que habilita los campos para editar una cancion
     * @param event
     */
    @FXML
    void editInfoSong(ActionEvent event) {
        txtNameSong.setEditable( true );
        comboBoxGender.setDisable( false);
        comboBoxArtist.setDisable( false);
        txtYearSong.setEditable( true );
        txtDurationSong.setEditable( true );
        txtLinkSong.setEditable( true );
        btnSelectCover.setDisable( false );


        btnCleanUpSong.setVisible( false );
        btnAddSong.setVisible( false );
        btnConfirmChangesSong.setVisible( true );
        btnCancelChangesSong.setVisible( true );

        showPopUp( "Now you can edit the info of the song on the fields at the top" , stage);
    }

    /**
     * Metodo de boton que permite remover una cancion de la aplicacion
     * @param event
     */
    @FXML
    void removeSongFromApp(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want delete this song?");
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
                    deleteSong();
                    adminController.mfm.saveDataTest();

                }  catch (SongException e) {
                    throw new RuntimeException( e );
                }
            }
            cleanUpSong( event );
            refreshTableViewSong();
            tableViewSongs.getSelectionModel().clearSelection();
        });
    }

    /**
     * Metodo que confirma la eliminacion de una cancion
     * @throws SongException
     */
    private void deleteSong() throws SongException {
        if(adminController.mfm.deleteSong(songSelection)){
            showMessage( "Notification", "Song eliminated Succesfully" ,
                    "The song has been removed", Alert.AlertType.INFORMATION );
        }else{
            showMessage( "Notification", "Song not eliminated" ,
                    "The song wasn't been removed", Alert.AlertType.INFORMATION );
        }
    }

    /**
     * Metodo de boton que permite eliminar un artista de la aplicacion
     * @param event
     */
    @FXML
    void removeArtistFromApp(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want delete this artist?");
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
                    deleteArtist();
                    adminController.mfm.saveDataTest();

                } catch (ArtistException e) {
                    throw new RuntimeException( e );
                }
            }

            cleanUpArtist( event );
            try {
                refreshTableViewArtist();
            } catch (IOException e) {
                throw new RuntimeException( e );
            }
            refreshTableViewSong();
            tableViewArtists.getSelectionModel().clearSelection();
        });
    }

    /**
     * Metodo confirma la eliminacion de un artista
     * @throws ArtistException
     */
    private void deleteArtist() throws ArtistException {
        if ( adminController.mfm.deleteArtist( artistSelection ) ){
            showMessage( "Notification", "Artist eliminated" ,
                    "Artist has been removed" , Alert.AlertType.INFORMATION );
        }else{
            showMessage( "Notification", "Artist not eliminated" ,
                    "Artist wasn't removed" , Alert.AlertType.INFORMATION );
        }
    }


    //------------------------------------------------SONGS----------------------------------------------//

    /**
     * Metodo de boton que permite seleccionar la imagen de la cancion
     * @param event
     * @throws IOException
     */
    @FXML
    void selectCoverSong(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSelectCover.getScene().getWindow();

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
                displayImage(destinationPath.toString(), imageViewSongPortait);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Metodo de boton que permite seleccionar la imagen de el artista
     * @param event
     * @throws IOException
     */
    @FXML
    void selectPhotoArtist(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSelectPhotoArtist.getScene().getWindow();

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
            String destinationDir = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/photosArtists/";

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
                displayImage(destinationPath.toString(), imageViewArtist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que muestra la imagen seleccionada
     * @param imagePath
     * @param imageView
     */
    private void displayImage(String imagePath, ImageView imageView) {
        try {
            // Crear el Image y el ImageView
            Image image = new Image("file:" + imagePath);
            imageView.setImage( image );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo de boton que permite agregar una canccion a la lista y que se muestra en la tabla de canciones
     * @param event
     * @throws ArtistException
     * @throws SongException
     */
    @FXML
    void addSong(ActionEvent event) throws ArtistException, SongException {
        String nameSong= txtNameSong.getText();
        String year= txtYearSong.getText();
        String duration= txtDurationSong.getText();
        String artistName= comboBoxArtist.getSelectionModel().getSelectedItem();

        Gender gender = comboBoxGender.getValue();

        System.out.println("asdasd");
        Image cover= imageViewSongPortait.getImage();
        String linkS = txtLinkSong.getText();
        if (verifySong(nameSong,gender,artistName,year,duration,linkS,cover)){
            URL link= obtenerURL();
            if(link!=null){
                String codeSong= generateCode(nameSong,artistName);
                Artist artist= adminController.mfm.getArtist(artistName);
                createSong( codeSong , nameSong , gender , year , duration , link , cover.getUrl() , artist );
                adminController.mfm.saveDataTest();
                System.out.printf( "Lista de canciones: " + getSongsList() );
                cleanUpSong( event );
                refreshTableViewSong();
            }else{
                showMessage( "Notification", "Invalid link",
                        "Please enter a valid link", Alert.AlertType.INFORMATION );
            }

        }
    }

    /**
     * Metodo que limpia los campos de view para crear canciones
     * @param event
     */
    @FXML
    void cleanUpSong(ActionEvent event) {
        createSongForm();
        txtNameSong.clear();
        txtLinkSong.clear();
        txtYearSong.clear();
        txtDurationSong.clear();
        comboBoxGender.setValue( null );
        comboBoxArtist.setValue( null );
        imageViewSongPortait.setImage( null );

    }

    /**
     * Metodo que permite a el usuario cerrar sesion
     * @param event
     */
    @FXML
    void logOut(ActionEvent event) {
        loginViewController.show();
        this.stage.close();
    }

    /**
     * Metodo que permite mostras la view con la tabla de los artistas y los campos apra agregar uno nuevo
     * @param event
     */
    @FXML
    void showArtistsInfo(ActionEvent event) {
        anchorSongs.setVisible( false );
        anchorArtists.setVisible( true );
    }

    /**
     * Metodo que permite mostrar la view con la tabla de las canciones y los campos para agregar una nueva
     * @param event
     */
    @FXML
    void showSongsInfo(ActionEvent event) {
        anchorArtists.setVisible( false );
        anchorSongs.setVisible( true );
    }

    /**
     * Metodo que maneja los eventos de el mouse
     */
    void eventsControl(){
        btnLogOut.setOnMouseEntered(event -> btnLogOut.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnLogOut.setOnMouseExited(event -> btnLogOut.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnArtists.setOnMouseEntered(event -> btnArtists.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnArtists.setOnMouseExited(event -> btnArtists.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));
        btnSongs.setOnMouseEntered(event -> btnSongs.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: black;"));
        btnSongs.setOnMouseExited(event -> btnSongs.setStyle("-fx-background-color: transparent; -fx-text-fill: black;"));

    }

    /**
     * Metodo que inicializa el siguiente escenario
     * @param stage2
     */
    public void init(Stage stage2) {
        this.stage = stage2;
    }

    /**
     * Metodo que crea una animacion
     */
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

    /**
     * Metodo que inicializa el controller con la view de canciones y los evnetos de mouse
     */
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

    /**
     * Metodo initialize para la view de administrador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminController= new AdminController();

        comboBoxArtist.setItems(getNamesArtist());
        comboBoxGender.getItems().addAll( Gender.values() );

        cleanUpArtist( new ActionEvent() );
        createSongForm();

        btnEditArtist.setDisable( true );
        btnEditSong.setDisable( true );




        System.out.printf("Lista canciones: "+ adminController.mfm.getSongList());
        this.tableColumnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tableColumnNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        this.tableColumnGroup.setCellValueFactory(new PropertyValueFactory<>("isAlone"));

        btnRemoveArtist.setDisable( true );
        tableViewArtists.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                artistSelection = newSelection;
                showArtistInfo();
                btnEditArtist.setDisable( false );
                btnRemoveArtist.setDisable( false );
            }else{
                createArtistForm();
                btnRemoveArtist.setDisable( true );
                btnEditArtist.setDisable( true );
            }
        });
        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));

        btnRemoveSong.setDisable( true );
        tableViewSongs.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                songSelection = newSelection;
                showSongInfo();
                btnAddSong.setDisable( true );
                btnRemoveSong.setDisable( false );

                btnEditSong.setDisable( false );
            }else{
                btnRemoveSong.setDisable( true );
                songSelection = null;
                btnAddSong.setDisable( false );
                createSongForm();
                btnEditSong.setDisable( true );

            }
        });
        refreshTableViewSong();


        try {
            refreshTableViewArtist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que carga los datos de un artista seleccionado para mostrar sus datos
     */
    void showArtistInfo(){
        txtNameArtist.setEditable( false );
        txtNationalityArtist.setEditable( false );
        checkGroupArtist.setDisable( true );
        btnSelectPhotoArtist.setDisable(true );
        txtNameArtist.setText(artistSelection.getName());
        txtNationalityArtist.setText(artistSelection.getNationality());
        checkGroupArtist.setSelected( artistSelection.getIsAlone() );
        imageViewArtist.setImage( new Image( artistSelection.getPhoto() ) );
    }


}
