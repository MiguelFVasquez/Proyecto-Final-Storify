package co.edu.uniquindio.estructuraDatos.proyecto.test;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import javafx.scene.image.Image;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
public class StorifyTest {
    private Storify storify= new Storify();

    //-------Intanciamos clases para probarlas
    private Song newSong() throws MalformedURLException {
        String code = "0003";
        String name = "Todo Lit";
        String rutaCover = "C:\\Users.txt\\Juan Miguel\\OneDrive - uqvirtual.edu.co\\5to semestre\\Programación\\Cover1.jpeg";
        Image cover = new Image(new File(rutaCover).toURI().toString()); // Crear objeto Image con la ruta de la imagen
        String year = "2024";
        String duration = "3:00";
        Gender gender = Gender.Reggaeton;
        URL link = new URL("https://www.youtube.com/watch?v=yTAh5-e2dRY&pp=ygUIdG9kbyBsaXQ%3D");
        Artist artist = newArtist();
        return new Song(code, name, cover, year, duration, gender, link, artist);
    }

    private Artist newArtist(){
        String code= "jkdak00";
        String name= "Eladio";
        String nationality= "Puerto Rico";
        Image image = new Image( "file:src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/images/photosArtists/eladio.jpg" );
        boolean isAlone= true;
        return new Artist(code,name,nationality,image,isAlone);
    }
    //-------------Metodos de prueba(test)------------------------

    //--------Metodos de usuarios----------------

    /**
     * Testeamos de que un usuario sea añadido con exito a la lista
     * @throws UserException
     */
    @Test
    public void testAddUser() throws UserException {
        User newUser= new User("Miguel","10001","juan23@gmail");
        boolean result= storify.addUser(newUser);
        assertTrue(result);//Verifica que el resultado sea positivo, es decir que se añade el usurio a la lista
    }

    /**
     * Testeamos de que un usuario sea eliminado, el deberia retornar false ya que el usuario no está en la lista
     * @throws UserException
     */
    @Test
    public void testDeleteUser() throws UserException {
        User user= new User("Miguel","10001","juan23@gmail");
        storify.addUser(user);
        boolean result= storify.deleteUser(user);
        assertTrue(result);
    }


    @Test
    public void testAddSongToUserList() throws UserException, MalformedURLException, SongException {
        User newUser= new User("juanmi","0190191","juanmi@gmail.com");
        Song newSong= newSong();
        storify.addUser(newUser);
        storify.addSong(newSong);
        boolean result= storify.addSongToUserList(newUser.getUserName(), newSong);
        assertTrue(result);
    }
    @Test
    public void testRemoveSongFromUserList() throws MalformedURLException, UserException, SongException {
        User newUser= new User("juanmi","0190191","juanmi@gmail.com");
        Song song= newSong();
        storify.addUser(newUser);
        storify.addSong(song);
        storify.addSongToUserList(newUser.getUserName(), song);
        boolean result= storify.removeSongFromUserList(newUser.getUserName(),song);
        assertTrue(result);
    }
    //------------------------Metodos de las canciones--------------------------
    /**
     *
     * @throws UserException
     * @throws MalformedURLException
     * @throws SongException
     */
    @Test
    public void testAddSong() throws UserException, MalformedURLException, SongException {
        Song newSong= newSong();
        boolean result= storify.addSong(newSong);
        assert(result);
    }
    /**
     * Testeamos de que una canción sea eliminada, el deberia retornar false ya que la canción no está en la lista
     * @throws MalformedURLException
     * @throws SongException
     */
    @Test
    public void testDeleteSong() throws MalformedURLException, SongException {
        Song song= newSong();
        storify.addSong(song);
        boolean result= storify.delateSong(song);
        assertTrue(result);
    }
    //-------------------Metodos de los artistas---------------------------
    @Test
    public void testAddArtist() throws ArtistException {
        Artist newArtist= newArtist();
        boolean result= storify.addArtist(newArtist);
        assertTrue(result);
    }
    @Test
    public void testDeleteArtist() throws ArtistException {
        Artist artist= newArtist();
        storify.addArtist(artist);
        boolean result= storify.deleteArtist(artist);
        assertTrue(result);
    }

    @Test
    public void testAddSongToArtistList() throws MalformedURLException, ArtistException, SongException {
        Artist artist= newArtist();
        Song song= newSong();
        storify.addArtist(artist);
        storify.addSong(song);
        boolean result= storify.addSongToArtistList(newArtist().getName(), song);
        assertTrue(result);
    }
    @Test
    public void testRemoveSongFromArtistList() throws MalformedURLException, ArtistException, SongException {
        Artist artist= newArtist();
        Song song= newSong();
        storify.addArtist(artist);
        storify.addSong(song);
        storify.addSongToArtistList(artist.getName(), song);
        boolean result= storify.removeSongFromArtistList(artist.getName(),song);
        assertTrue(result);
    }

}
