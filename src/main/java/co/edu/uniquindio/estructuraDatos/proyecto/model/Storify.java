package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.BinaryTree;
import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.DoublyLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Interfaces.IStorify;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
* La tienda guarda su catálogo de música agrupando las Canciones en sus respectivos autores en forma
de lista, los Artistas se almacenan en forma de Árbol Binario, el orden del árbol está dado por el nombre
de los Artistas. Además tenga en cuenta que los usuarios se guardan en un HashMap, donde la llave de
cada usuario es su username.
*
* */
public class Storify implements IStorify, Serializable {
    private String nombre;
    private HashMap<String, User> usersMap;
    private BinaryTree<Artist> artistTree;
    private List<Song> songList;

    public Storify() {
        this.usersMap = new HashMap<>();
        this.artistTree= new BinaryTree<>();
        this.songList= new ArrayList<>();
    }
    public Storify(String nombre) {
        this.nombre = nombre;
        this.usersMap = new HashMap<>();
        this.artistTree= new BinaryTree<>();
        this.songList= new ArrayList<>();
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(HashMap<String, User> usersMap) {
        this.usersMap = usersMap;
    }

    public BinaryTree<Artist> getArtistTree() {
        return artistTree;
    }

    public void setArtistTree(BinaryTree<Artist> artistTree) {
        this.artistTree = artistTree;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
//------------------METODOS USUARIO------------------------------

    /**
     * Metodo para obtener un usuario en base al nombre de usuario
     * @param username
     * @return
     */
    public User getUser(String username){
        return usersMap.getOrDefault(username,null);
    }

    public boolean verifyUser(String username){
        return usersMap.containsKey(username);
    }
    public boolean verifyPassword(String password){
        return usersMap.containsKey(password);
    }

    public boolean logIn(String userName, String password){
        boolean flag= false;
        if (usersMap.containsKey(userName)){
            User userAux= usersMap.get(userName);
            if (userAux.getPassword().equals(password)){
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public boolean addUser(User newUser) throws UserException {
        boolean added= false;
        String userName= newUser.getUserName();
        if (verifyUser(userName)){
            throw new UserException("El nombre de usuario: '"+ userName+ "' ya se encuentra en uso.");
        }else {
            added=true;
            usersMap.put(userName,newUser);
        }
        return added;
    }

    @Override
    public boolean deleteUser(User userDelete) throws UserException {
        boolean deleted= false;
        String userName= userDelete.getUserName();
        if (getUser(userName)==null){
            throw new UserException("El usuario: '" + userName + "' no ha sido encontrado.");
        }else {
            deleted=true;
            usersMap.remove(userName,userDelete);
        }
        return deleted;
    }

    @Override
    public boolean updateUser(User userUpdate) throws UserException {
        boolean updated=false;
        String userName= userUpdate.getUserName();//El username al ser la identificación no se pude cambiar
        String newPassword= userUpdate.getPassword();
        String newEmail= userUpdate.getEmail();
        User userAux= getUser(userName);
        if (userAux==null){
            throw new UserException("El usuario: '" + userName + "' no ha sido encontrado.");
        }else {
            updated=true;
            userAux.setPassword(newPassword);
            userAux.setEmail(newEmail);
        }
        return updated;
    }

    /**
     * Metodo para añadir una canción a la lista del usuario
     * Se verifica que el usuario exista, si existe se añade la canción a la lista
     * @param username
     * @param newSong
     * @return
     * @throws UserException
     * @throws SongException
     */
    public boolean addSongToUserList(String username, Song newSong) throws UserException, SongException {
        boolean flag=false;
        User userAux= getUser(username);
        if (userAux==null){
            throw new UserException("El usuario no ha sido encontrado");
        }else {
            if (userAux.addSongToList(newSong)){
                flag=true;
            }
        }
        return flag;
    }

    /**
     *Metodo que permite eliminar una cancion de la lista del usuario
     * @param username
     * @param songDelete
     * @return
     * @throws UserException
     * @throws SongException
     */
    public boolean removeSongFromUserList(String username, Song songDelete) throws UserException, SongException{
        boolean flag= false;
        User userAux= getUser(username);
        if (userAux==null){
            throw new UserException("El usuario no ha sido encontrado");
        }else {
            if (userAux.removeSongFromList(songDelete)){
                flag=true;
            }
        }
        return flag;
    }

//---------------METODOS ARTISTA------------------------------------------

    /**
     * Si encuentra un artista con el mismo nombre, devolvera true
     * @param name
     * @return
     */
    private boolean verifyArtist(String name){
        boolean verify=false;
        for (Artist artista: artistTree) {
            if (artista.getName().equals(name)) {
                verify = true;
                break;
            }
        }
        return verify;
    }

    /**
     * Metodo para obtener un artista en base al nombre
     * @param name
     * @return
     */
    public Artist getArtist(String name){
        Artist artistAux= null;
        for (Artist artist:artistTree) {
            if (artist.getName().equals(name)){
                artistAux=artist;
                break;
            }
        }
        return artistAux;
    }

    @Override
    public boolean addArtist(Artist newArtist) throws ArtistException {
        boolean added=false;
        String name= newArtist.getName();
        if (verifyArtist(name)){
            throw new ArtistException("El nombre de artista: "+ name + " ya se encuentra en uso. ");
        }else {
            added=true;
            artistTree.insertar(newArtist);
        }
        return added;
    }

    @Override
    public boolean deleteArtist(Artist artistDelete) throws ArtistException {
        boolean deleted=false;
        String name= artistDelete.getName();
        Artist artistAux=getArtist(name);
        if (artistAux==null){
            throw new ArtistException("El artista "+ name+ " no ha sido encontrado");
        }else {
            deleted=true;
            artistTree.eliminar(artistAux);
        }
        return deleted;
    }
    @Override
    public boolean updateArtist(Artist artistUpdate) throws ArtistException {
        boolean updated= false;
        String name= artistUpdate.getName(); //El nombre es la identificación del artista por lo tanto no se puede cambiar;
        String newCode= artistUpdate.getCode();
        String newNationality= artistUpdate.getNationality();
        boolean newStatus= artistUpdate.getIsAlone();
        Artist artistAux= getArtist(name);
        if (artistAux==null){
            throw new ArtistException("El artista: '" + name+"' no ha sido encontrado");

        }else {
            updated=true;
            artistAux.setCode(newCode);
            artistAux.setNationality(newNationality);
            artistAux.setAlone(newStatus);
        }
        return updated;
    }

    /**
     * Metodo para añadir una cancion al artista, se verifica que el artista exista y si existe se hace el proceso para añadir la cancion a su lista
     * @param name
     * @param newSong
     * @return
     * @throws ArtistException
     * @throws SongException
     */
    public boolean addSongToArtistList(String name, Song newSong) throws ArtistException,SongException{
        boolean flag= false;
        Artist artistAux= getArtist(name);
        if (artistAux==null){
            throw new ArtistException("El artista no ha sido encontrado");
        }else {
            if (artistAux.addSongToList(newSong)){
                flag=true;
            }
        }
        return flag;
    }

    /**
     * Metodo para eliminar una canción de la lista del artista
     * @param name
     * @param songDelete
     * @return
     * @throws ArtistException
     * @throws SongException
     */
    public boolean removeSongFromArtistList(String name, Song songDelete) throws ArtistException,SongException{
        boolean flag= false;
        Artist artistAux= getArtist(name);
        if (artistAux==null){
            throw new ArtistException("El artista no ha sido encontrado");
        }else {
            if (artistAux.removeSongToList(songDelete)){
                flag=true;
            }
        }
        return flag;
    }

//-----------------------METODOS CANCION---------------------------------------
    /**
     *
     * @param code
     * @return true si la canción ya está en la lista
     * @return false si la canción no está en la lista
     */
    private boolean verifySong(String code) {
        return this.songList.stream()
                .anyMatch(s -> s.getCode().equals(code));
    }


    public Song getSong(String code){
        Optional<Song> songOptional= this.songList.stream()
                .filter(s ->s.getCode().equals(code))
                .findFirst();
        return songOptional.orElse(null);
    }

    @Override
    public boolean addSong(Song newSong) throws SongException {
        boolean added= false;
        if (verifySong(newSong.getCode())){
            throw new SongException("La canción: " + newSong.getName()+ " ya se encuentra registrada");
        }else {
            added=true;
            songList.add(newSong);
            newSong.getArtist().addSong(newSong);
        }
        return added;
    }

    @Override
    public boolean delateSong(Song songDelete) throws SongException {
        boolean deleted= false;
        String code= songDelete.getCode();
        if (getSong(code)==null){
            throw new SongException("La canción: " + songDelete.getName()+ " no se encuentre en el sistema");
        }else {
            deleted=true;
            songList.remove(songDelete);
            songDelete.getArtist().delateSong(songDelete);
        }
        return deleted;
    }

    @Override
    public boolean updateSong(Song songUpdate) throws SongException {
        boolean updated = false;
        String code = songUpdate.getCode();//Este no podra ser cambiado ya que es el id de la canción
        String name = songUpdate.getName();
        Image newCover = songUpdate.getCover();
        String newYear = songUpdate.getYear();
        String newDuration = songUpdate.getDuration();
        Gender newGender = songUpdate.getGender();
        URL newLink = songUpdate.getLink();
        Artist newArtist = songUpdate.getArtist();

        Song songAux= getSong(code);
        if (songAux==null){
            throw new SongException("La canción: '" + name+ "' no ha sido encontrada");
        }else {
            updated=true;
            songAux.setName(name);
            songAux.setCover(newCover);
            songAux.setYear(newYear);
            songAux.setDuration(newDuration);
            songAux.setGender(newGender);
            songAux.setLink(newLink);
            songAux.setArtist(newArtist);
        }
        return updated;
    }

    //----------------------------METODOS DE BUSQUEDA------------------------------------

    /**
     * Metodo que retorna la lista de canciones del artista
     * @param artistName
     * @return
     * @throws ArtistException
     */
   public List<Song> searchSongByArtistName(String artistName) throws ArtistException{
        DoublyLinkedList<Song> songList= new DoublyLinkedList<>();
        for (Artist artistAux: artistTree){
            if (artistAux.getName().equals(artistName)){
                songList=artistAux.getSongList();
            }
        }
        return songList.toList();
   }

    public List<Song> searchY(String artistName,String songName, Gender gender, String year){
       List<Song> songList= new ArrayList<>();

       return songList;
    }
}
