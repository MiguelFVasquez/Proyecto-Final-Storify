package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.CircularLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.persistence.Persistence;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {

    //Se instancia la clase principal (Storify) y los controladores necesarios
    static Storify storify;
    private LoginViewController loginViewController;
    private RescuePassController rescuePassController;



    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            //Crea la intancia del singleton
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Metodo para hacer llamada de storify en el singleton
     * @return storify
     */
    public static Storify getStorify() {
        return storify;
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    /**
     * Invoacion del singleton
     * @throws InterruptedException
     */
    public ModelFactoryController() throws InterruptedException {
        System.out.print("Invocación singleton");
        try {
            initData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        saveDataTest();
        saveResourceXML();

        if (storify==null) {
            loadDataBase();
            saveResourceXML();
        }
    }

    /**
     * Se inician los datos al invocar el singleton
     * @throws InterruptedException
     */
    private void initData() throws InterruptedException{
//        ThreadLoadXML loadXML= new ThreadLoadXML();
//        loadXML.start();
//        loadXML.join();
        loadDataFromFiles();
        //loadResourceStorifyXML();


    }
    //-----------------------Initializacion de los controllers---------------

    /**
     * Inicializacion del controlador
     * @param loginViewController
     */
    public void initLoginViewController(LoginViewController loginViewController){
        this.loginViewController= loginViewController;
    }

    /**
     * Inicializacion del controlador
     * @param rescuePassController
     */
    public void initRescuePassControler(RescuePassController rescuePassController){
        this.rescuePassController = rescuePassController;
    }
    //----------------------- USER FUNCTIONS --------------------------------------------//

    /**
     * Instancia del metodo que crea un usuario, le asigna su lista de canciones, y lo agrega a el mapa de usuarios
     * @param userName
     * @param password
     * @param emial
     * @return usuario nuevo
     * @throws UserException
     */
    public boolean registerUser(String userName, String password, String emial)throws UserException{
        User user = new User(userName,password,emial);
        user.setSongList( new CircularLinkedList<>() );
        return storify.addUser(user);
    }

    /**
     * Instancia del metodo que obtiene el usuario segun su nombre
     * @param name
     * @return usuario
     */
    public User getUser(String name){
        return storify.getUser( name );
    }

    /**
     * Instancia del metodo que permite inicio de sesion de los usuarios
     * @param userName
     * @param password
     * @return credenciales del usuario
     */
    public boolean logInUser(String userName, String password){
        return storify.logIn(userName,password);
    }

    /**
     * Instancia del metodo que obtiene el nombre de usuario para el login
     * @return
     */
    public String getUserName(){
        return loginViewController.getLogInName();
    }

    /**
     * Instancia de metodo que agrega una cancion a la lista de canciones de un usuario
     * @param username
     * @param songToAdd
     * @return
     * @throws UserException
     * @throws SongException
     */
    public boolean addSongToUserList(String username, Song songToAdd) throws UserException, SongException {
        return storify.addSongToUserList(username,songToAdd);
    }

    /**
     * Instancia de metodo que remueve una canciond el lista del cacniones de un usuario
     * @param username
     * @param songToRemove
     * @return
     * @throws UserException
     * @throws SongException
     */
    public boolean removeSongFromUserList(String username, Song songToRemove) throws UserException, SongException {
        return storify.removeSongFromUserList(username,songToRemove);
    }

    /**
     * Instancia de metodo que verifica la cancion segun el codigo asignado
     * @param user
     * @param songSelection
     * @return
     */
    public boolean verifySong(User user, Song songSelection) {
        return user.verifySong( songSelection.getCode() );
    }

    /**
     * Instancia de metodo que reproduce una cancion mediante JavaFx WebView capturando el link de yotube de la cancion
     * @param webView
     * @param url
     * @param play
     */
    public void playSong(WebView webView, String url, Boolean play){
        storify.playSong(webView, url,play);
    }

    /**
     * Instancia de metodo que valida los datos para recuperar contrasenia
     * @param userName
     * @throws UserException
     */
    public void validateDataRecoverPassword(String userName) throws UserException {
        storify.validateDataRecoverPassword(userName);
    }

    /**
     * Instancia de metodo que busca el usuario con su nombre para recuperar la contrasenia
     * @param userName
     * @return
     * @throws UserException
     */
    public User rescuePassword(String userName) throws UserException{
        return storify.rescuePassword(userName);
    }

    /**
     * Instancia del metodo que genera el codigo que se le envia al correo al usuario para que este recupere su contrasenia
     * @param longitud
     * @return
     */
    public String generateCode(int longitud){
        return storify.generateCode(longitud);
    }

    /**
     * Instancia de metodo que envia el correo al usuario mediante un hilo aparte para que no se interrumpa
     * @param user
     * @param code
     */
    public void sendRescueEmail(User user, String code){
        storify.sendRescueEmail(user,code);
    }

    /**
     * Metodo que setea la nueva contraseña y la serializa
     * @param user
     * @param newPassword
     * @throws UserException
     */
    public void changePassword(User user, String newPassword) throws UserException {
        user.setPassword(newPassword);
        saveDataTest();

    }

    /**
     * Instacia de metodo que retorna la lista de canciones del artista
     * @param newValue
     * @return
     * @throws ArtistException
     */
    public List<Song> searchArtist(String newValue) throws ArtistException {
        return storify.searchSongByArtistName( newValue );
    }

    /**
     * Metodo que realiza busqueda O
     * @param newValue
     * @return
     */
    public List<Song> searchO(String newValue) {
        try {
            String newValue2 = newValue.substring(0, 1).toUpperCase() + newValue.substring(1);
            Gender gender1 = Gender.valueOf(newValue2);  // Verifica si gender es un valor válido en el Enum Gender
            return storify.searchO( newValue, newValue, gender1 , newValue );

        } catch (IllegalArgumentException e) {
            System.err.println("Valor no válido en el Enum Gender: " + newValue);
        }
        return storify.searchO( newValue, newValue, null , newValue );
    }

    /**
     * Instancia de metodo que toma el usuario y le actualiza los datos
     * @param name
     * @param password
     * @param email
     * @return
     * @throws UserException
     */
    public boolean updateUser(String name , String password , String email) throws UserException {
        User user = new User(name,password,email);
        return storify.updateUser(user  );
    }

    /**
     * Instancia de metodo que elimina un usuario de el mapa
     * @param user
     * @throws UserException
     */
    public void deleteUser(User user) throws UserException {
        storify.deleteUser(  user);
    }

    /**
     * Instancia de metodo que obtiene el artista mas escucho
     * @return
     */
    public Artist getMostListenedArtist() {
        return storify.getMostListenedArtist();
    }

    /**
     * Instancia de metodo que obtiene el genero mas escuchado
     * @return
     */
    public Gender getMostListenedGender() {
        return storify.getGenderMostSongs();
    }

    /**
     * Instancia de metodo que obtiene el artista mas escuchado por un usuario
     * @param user
     * @return
     */
    public Artist getMostListenedArtistByUser(User user) {
        return user.getMostListenedArtist();
    }

    /**
     * Instancia de metodo que obtiene el genero mas escuchado por un usuario
     * @param user
     * @return
     */
    public Gender getMostListenedGenderByUser(User user) {
        return user.getMostListenedGender();
    }


    //-------------------------Admin functions---------------------------------------------------

    /**
     * Instancia de metodo que permite a el administrador eliminar una cancion
     * @param songSelection
     * @return
     * @throws SongException
     */
    public boolean deleteSong(Song songSelection) throws SongException {
       return storify.deleteSong( songSelection );
    }
        //---------------------Artist functions------------------------------------------------

    /**
     * Instancia de metodo que obtiene el arbol de artistas
     * @return
     */
    public List<Artist> getListArtist(){
        return storify.getArtistTree().toList();
    }

    /**
     * Metodo que obtiene el nombre de los artistas
     * @return
     */
    public List<String> getNamesArtistList(){
        List<String> names= new ArrayList<>();
        for (Artist a : getListArtist()){
            names.add(a.getName());
        }
        return names;
    }

    /**
     * Metodo que obtiene los artistas por el nombre
     * @param name
     * @return
     */
    public Artist getArtist(String name){
        return storify.getArtist(name);
    }

    /**
     * Instancia de metodo que agrega un artista nuevo a el arbol
     * @param code
     * @param name
     * @param nationality
     * @param image
     * @param isAGroup
     * @return
     * @throws ArtistException
     */
    public boolean addArtist(String code, String name, String nationality, String image , boolean isAGroup) throws ArtistException {
        Artist newArtist= new Artist(code,name,nationality,image,isAGroup);
        return storify.addArtist(newArtist);
    }

    /**
     * Instancia de metodo que elimina un artista de el arbol
     * @param deleteArtist
     * @return
     * @throws ArtistException
     */
    public boolean deleteArtist(Artist deleteArtist) throws ArtistException {
        return storify.deleteArtist(deleteArtist);
    }

    /**
     * Instancia de metodo que permite actualziar los datos el artista
     * @param artistUpdate
     * @return
     * @throws ArtistException
     */
    public boolean updateArtist(Artist artistUpdate) throws ArtistException {
        return storify.updateArtist(artistUpdate);
    }

    /**
     * Instancia de metodo que agrega una cancion a la lista de canciones de el artista
     * @param name
     * @param songAdd
     * @return
     * @throws ArtistException
     * @throws SongException
     */
    public boolean addSongToArtistList(String name, Song songAdd) throws ArtistException, SongException {
        return storify.addSongToArtistList(name,songAdd);
    }

    /**
     * Instancia de metodo que actualiza la cancion de un artista
     * @param artistName
     * @param songAux
     * @return
     * @throws SongException
     */
    public boolean updateArtistSong(String artistName , Song songAux) throws SongException {
        return storify.updateSongArtist( songAux, getArtist( artistName ) );
    }


        //------------------------Song's functions----------------------------------------

    /**
     * Instancia de metodo que obtiene la lista de canciones
     * @return
     */
    public List<Song> getSongList(){
        return storify.getSongList();
    }

    /**
     * Instancia de metodo que obtiene una cancion segun el codigo
     * @param code
     * @return
     */
    public Song getSong(String code){
        return storify.getSong(code);
    }

    /**
     * Instancia de metodo que obtiene una cancion por su nombre
     * @param nombre
     * @return
     */
    public Song getSongByName(String nombre){
        return storify.getSongByName(nombre);
    }

    /**
     * Instancia de metodo que crea una cancion nueva y lo añade a la lista de canciones
     * @param code
     * @param name
     * @param cover
     * @param year
     * @param duration
     * @param gender
     * @param link
     * @param artist
     * @return
     * @throws SongException
     */
    public boolean addSong(String code, String name, String cover, String year, String duration, Gender gender, URL link, Artist artist) throws SongException {
        Song newSong= new Song(code, name, cover, year, duration,gender,link,artist);
        return storify.addSong(newSong);
    }

    /**
     * Instancia de metodo que crea una instancia de cancion y le actualiza los datos
     * @param code
     * @param nameSong
     * @param gender
     * @param year
     * @param duration
     * @param link
     * @param url
     * @param artist
     * @return
     * @throws SongException
     */
    public boolean updateSong(String code , String nameSong , Gender gender , String year , String duration , URL link , String url , Artist artist) throws SongException {
        Song updateSong = new Song(code, nameSong,url,year,duration,gender,link,artist);
        return storify.updateSong( updateSong );
    }

    //------------------------ Serialization functions --------------------------------------//

    /**
     * Instancia de los metodos de la clase persistencia para la serializacion de los datos
     */
    public void saveDataTest(){
        try{
            Persistence.saveUsers2( getStorify().getUsersMap() );
            Persistence.saveUsers(getStorify().getUsersMap());
            Persistence.saveArtist(getStorify().getArtistTree());
            Persistence.saveArtist2( getStorify().getArtistTree() );
            Persistence.saveSongs(getStorify().getSongList());
            Persistence.saveSongs2( getStorify().getSongList() );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Instancia de metodos de la clase persistencia que guarda la serializacion de los usuarios
     */
    public void saveUsers(){
        try {
            Persistence.saveUsers2( getStorify().getUsersMap() );
            Persistence.saveUsers(getStorify().getUsersMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que instancia de la clase persistencia el metodo que carga los datos serializados a el singleton
     */
    public static void loadDataFromFiles(){
        storify= new Storify("Shuhenfy");
        try{
            Persistence.cargarDatosArchivos(storify);
            System.out.print("Serializado");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo que carga los datos default de el singleton
     */
    private void loadDataBase() {
        storify= new Storify();
    }

    /**
     * Guarda el archivo serializado
     */
    public static void saveResourceXML(){
        Persistence.saveResourceStorifyXML(storify);
    }

    /**
     * Carga el archivo serializado
     */
    public static void loadResourceStorifyXML()  {
        storify= Persistence.loadResourceStorifyXML();
    }

}
