package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.AdminViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.UserViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.persistence.Persistence;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.ArtistViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ModelFactoryController {
    private LoginViewController loginViewController;
    private UserViewController userViewController;
    private ArtistViewController artistViewController;
    private AdminViewController adminViewController;
    static Storify storify;

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (UserException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ArtistException e) {
                throw new RuntimeException(e);
            } catch (SongException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() throws UserException, MalformedURLException, ArtistException, SongException {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("Invocacion clase singleton");
        initData();
    }
    private Artist newArtist(){
        String code= "jkdak00";
        String name= "Eladio";
        String nationality= "Puerto Rico";
        boolean isAlone= true;
        return new Artist(code,name,nationality,isAlone);
    }
    private Song newSong() throws MalformedURLException {
        String code = "0003";
        String name = "Todo Lit";
        String rutaCover = "src\\main\\resources\\co\\edu\\uniquindio\\estructuraDatos\\proyecto\\images\\Eladio_Carrión_-_Sol_María.jpg";
        Image cover = new Image(new File(rutaCover).toURI().toString()); // Crear objeto Image con la ruta de la imagen
        String year = "2024";
        String duration = "3:00";
        Gender gender = Gender.Reggaeton;
        URL link = new URL("https://www.youtube.com/watch?v=yTAh5-e2dRY&pp=ygUIdG9kbyBsaXQ%3D");
        Artist artist = newArtist();
        return new Song(code, name, cover, year, duration, gender, link, artist);
    }

    private void initData() throws UserException, MalformedURLException, ArtistException, SongException {
        storify = new Storify("SHUHENFY");
        Song song1= newSong();
        storify.addArtist(song1.getArtist());
        storify.addSong(song1);
        cargarDatosDesdeArchivos(storify);
    }

    public void initLoginViewController(LoginViewController loginViewController){
        this.loginViewController = loginViewController;
    }
    public void initUserViewController(UserViewController userViewController){
        this.userViewController = userViewController;
    }
    public void initArtistViewController(ArtistViewController artistViewController){
        this.artistViewController = artistViewController;
    }
    public void initAdminViewController(AdminViewController adminViewController){
        this.adminViewController = adminViewController;
    }

    //----------------------- USER FUNCTIONS --------------------------------------------//

    public boolean registerUser(String userName, String password, String emial)throws UserException{

        User user = new User(userName,password,emial);
        return storify.addUser(user);
    }

    public static void cargarDatosDesdeArchivos(Storify storify) {
        try {
            Persistence.loadDataFiles(storify);
            storify.getUsersMap().putAll((Persistence.loadUsers()));
            //storify.getSongList().addAll(Persistence.loadSongs());

            System.out.println("Serializado de usuarios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

        public boolean verifyUser(String userName){
        return storify.verifyUser(userName);
    }
    public boolean verifyPassword(String password){
        return storify.verifyPassword(password);
    }

    public boolean logInUser(String userName, String password){
        return storify.logIn(userName,password);
    }
    public User getUser(String userName){
        return storify.getUser(userName);
    }

      /*
      Metodo para cargar archivos serializados de cliente
       */

    //-------------------------Admin functions---------------------------------------------------
        //---------------------Artist functions------------------------------------------------

    public List<Artist> getListArtist(){
        return storify.getArtistTree().toList();
    }
    public Artist getArtist(String name){
        return storify.getArtist(name);
    }
    public boolean addArtist(String code, String name, String nationality, boolean isAGroup) throws ArtistException {
        Artist newArtist= new Artist(code,name,nationality,isAGroup);
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
        //------------------------Song's functions----------------------------------------
    public List<Song> getSongList(){
        return storify.getSongList();
    }
    public Song getSong(String code){
        return storify.getSong(code);
    }

    public boolean addSong(String code, String name, Image cover, String year, String duration, Gender gender, URL link, Artist artist) throws SongException {
        Song newSong= new Song(code, name, cover, year, duration,gender,link,artist);
        return storify.addSong(newSong);
    }

    //------------------------ Serialization functions --------------------------------------//

    public void userSerialization() throws IOException {
        Persistence.saveUsers( storify.getUsersMap() );
    }
}
