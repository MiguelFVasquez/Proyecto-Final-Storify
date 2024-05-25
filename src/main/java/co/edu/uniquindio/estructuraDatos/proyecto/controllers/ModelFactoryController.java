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
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {
    static Storify storify;
    private LoginViewController loginViewController;
    private RescuePassController rescuePassController;




    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static Storify getStorify() {
        return storify;
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

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
    private void initData() throws InterruptedException{
//        ThreadLoadXML loadXML= new ThreadLoadXML();
//        loadXML.start();
//        loadXML.join();
        loadDataFromFiles();
        //loadResourceStorifyXML();


    }
    //-----------------------Initialization de los controllers---------------
    public void initLoginViewController(LoginViewController loginViewController){
        this.loginViewController= loginViewController;
    }
    public void initRescuePassControler(RescuePassController rescuePassController){
        this.rescuePassController = rescuePassController;
    }
    //----------------------- USER FUNCTIONS --------------------------------------------//

    public boolean registerUser(String userName, String password, String emial)throws UserException{
        User user = new User(userName,password,emial);
        user.setSongList( new CircularLinkedList<>() );
        return storify.addUser(user);
    }

    public User getUser(String name){
        return storify.getUser( name );
    }

    public boolean logInUser(String userName, String password){
        return storify.logIn(userName,password);
    }
    public String getUserName(){
        return loginViewController.getLogInName();
    }
    public boolean addSongToUserList(String username, Song songToAdd) throws UserException, SongException {
        return storify.addSongToUserList(username,songToAdd);
    }
    public boolean removeSongFromUserList(String username, Song songToRemove) throws UserException, SongException {
        return storify.removeSongFromUserList(username,songToRemove);
    }

    public void validateDataRecoverPassword(String userName) throws UserException {
        storify.validateDataRecoverPassword(userName);
    }
    public User rescuePassword(String userName) throws UserException{
        return storify.rescuePassword(userName);
    }

    public String generateCode(int longitud){
        return storify.generateCode(longitud);
    }

    public void sendRescueEmail(User user, String code){
        storify.sendRescueEmail(user,code);
    }

    public void changePassword(User user, String newPassword) throws UserException {
        user.setPassword(newPassword);
        saveDataTest();

    }

//    public void addFavSong(User user, Song songSelected) throws SongException {
//        user.addSongToList( songSelected );
//    }

    //-------------------------Admin functions---------------------------------------------------
        //---------------------Artist functions------------------------------------------------

    public List<Artist> getListArtist(){
        return storify.getArtistTree().toList();
    }

    public List<String> getNamesArtistList(){
        List<String> names= new ArrayList<>();
        for (Artist a : getListArtist()){
            names.add(a.getName());
        }
        return names;
    }

    public Artist getArtist(String name){
        return storify.getArtist(name);
    }
    public boolean addArtist(String code, String name, String nationality, String image , boolean isAGroup) throws ArtistException {
        Artist newArtist= new Artist(code,name,nationality,image,isAGroup);
        return storify.addArtist(newArtist);
    }
    public boolean deleteArtist(Artist deleteArtist) throws ArtistException {
        return storify.deleteArtist(deleteArtist);
    }
    public boolean updateArtist(Artist artistUpdate) throws ArtistException {
        return storify.updateArtist(artistUpdate);
    }
    public boolean addSongToArtistList(String name, Song songAdd) throws ArtistException, SongException {
        return storify.addSongToArtistList(name,songAdd);
    }

    public boolean updateArtistSong(String artistName , Song songAux) throws SongException {
        return storify.updateSongArtist( songAux, getArtist( artistName ) );
    }


        //------------------------Song's functions----------------------------------------
    public List<Song> getSongList(){
        return storify.getSongList();
    }
    public Song getSong(String code){
        return storify.getSong(code);
    }
    public Song getSongByName(String code){
        return storify.getSongByName(code);
    }

    public boolean addSong(String code, String name, String cover, String year, String duration, Gender gender, URL link, Artist artist) throws SongException {
        Song newSong= new Song(code, name, cover, year, duration,gender,link,artist);
        return storify.addSong(newSong);
    }

    public boolean updateSong(String code , String nameSong , Gender gender , String year , String duration , URL link , String url , Artist artist) throws SongException {
        Song updateSong = new Song(code, nameSong,url,year,duration,gender,link,artist);
        return storify.updateSong( updateSong );
    }

    //------------------------ Serialization functions --------------------------------------//

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

    public void saveUsers(){
        try {
            Persistence.saveUsers2( getStorify().getUsersMap() );
            Persistence.saveUsers(getStorify().getUsersMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadDataFromFiles(){
        storify= new Storify("Shuhenfy");
        try{
            Persistence.cargarDatosArchivos(storify);
            System.out.print("Serializado");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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
     * @
     */
    public static void loadResourceStorifyXML()  {
        storify= Persistence.loadResourceStorifyXML();
    }

}
