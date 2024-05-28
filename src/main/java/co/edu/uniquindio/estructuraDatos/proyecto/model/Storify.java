package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.BinaryTree;
import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.DoublyLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Interfaces.IStorify;
import co.edu.uniquindio.estructuraDatos.proyecto.utilities.EmailUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

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

//--------------- METODOS RECUPERAR CONTRASENIA USUARIO-------------------

    /**
     * Metodo que valida los datos para recuperar contrasñea
     *
     * @throws UserException
     */
    public void validateDataRecoverPassword(String userName) throws UserException {
        if(userName == null || userName.isBlank()){
            throw new UserException("Por favor ingrese su usuario");
        }
    }

    /**
     * Metodo que busca el usuario con su nombre para recuperar la contraseña
     * @param userName
     * @return
     * @throws UserException
     */
    public User rescuePassword(String userName) throws UserException {
        for(Map.Entry<String,User> entry : usersMap.entrySet()){
            String users = entry.getKey();
            User user = entry.getValue();
            if(users.equals(userName)){
                return user;
            }
        }
        throw new UserException("El usuerio ingresado no corresponde a ningun cliente");
    }

    /**
     * Genera el codigo que se le envia al correo al usuario para que este recupere su contrasenia
     * @param longitud
     * @return
     */
    public String generateCode(int longitud) {
        if (longitud == 0) {
            return "";
        } else {
            Random random = new Random();
            int num = random.nextInt(10);
            return num + generateCode(longitud - 1);
        }
    }

    /**
     * Envia el correo al usuario mediante un hilo aparte para que no se interrumpa
     * @param user El usuario al que se enviará el correo electrónico
     * @param code El código de recuperación de contraseña
     * //@param customMessage El mensaje personalizado que se incluirá en el correo electrónico
     * //@param imagePath La ruta de la imagen que se adjuntará como logo
     */
    public void sendRescueEmail(User user, String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EmailUtil.sendEmail(user.getEmail(), "Cambio de contraseña", "Querido "+ user.getUserName() + ",\n"
                                                                               + "\nPor favor, use este codigo para restablecer su contraseña:\n" +
                                                                                 code);
            }
        }).start();
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
        String cover = artistUpdate.getPhoto();
        Artist artistAux= getArtist(name);
        if (artistAux==null){
            throw new ArtistException("El artista: '" + name+"' no ha sido encontrado");

        }else {
            updated=true;
            artistAux.setCode(newCode);
            artistAux.setNationality(newNationality);
            artistAux.setAlone(newStatus);
            artistAux.setPhoto( cover );
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
    private boolean verifySongByName(String name) {
        return this.songList.stream()
                .anyMatch(s -> s.getName().equals(name));
    }


    public Song getSong(String code){
        Optional<Song> songOptional= this.songList.stream()
                .filter(s ->s.getCode().equals(code))
                .findFirst();
        return songOptional.orElse(null);
    }
    public Song getSongByName(String name){
        Optional<Song> songOptional= this.songList.stream()
                .filter(s ->s.getName().equals(name))
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
        String newCover = songUpdate.getCover();
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
            songAux.getArtist().delateSong( songAux );
            songAux.setName(name);
            songAux.setCover(newCover);
            songAux.setYear(newYear);
            songAux.setDuration(newDuration);
            songAux.setGender(newGender);
            songAux.setLink(newLink);
            songAux.setArtist(newArtist);
            newArtist.addSong( songAux );

        }
        return updated;
    }

    public boolean updateSongArtist(Song songUpdate, Artist artist) throws SongException {
        boolean updated = false;
        String code = songUpdate.getCode();//Este no podra ser cambiado ya que es el id de la canción
        String name = songUpdate.getName();
        String newCover = songUpdate.getCover();
        String newYear = songUpdate.getYear();
        String newDuration = songUpdate.getDuration();
        Gender newGender = songUpdate.getGender();
        URL newLink = songUpdate.getLink();
        Artist newArtist = songUpdate.getArtist();

        Song song;
        for (int i = 0; i < artist.getSongList().getSize(); i++) {
            Song songAux =artist.getSongList().get( i );
            if(code.equals( songAux.getCode())){
                updated=true;
                songAux.setName(name);
                songAux.setCover(newCover);
                songAux.setYear(newYear);
                songAux.setDuration(newDuration);
                songAux.setGender(newGender);
                songAux.setLink(newLink);
                songAux.setArtist(newArtist);
            }
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
            if ( artistAux.getName().equalsIgnoreCase( artistName )){
                System.out.println("Debería mostrar el artista");
                songList=artistAux.getSongList();
            }
            else{
                if(artistAux.getName().toLowerCase().contains( artistName.toLowerCase() )){
                    System.out.println("Debería mostrar artistas relacionados");
                    songList=artistAux.getSongList();
                }
            }
        }
        return songList.toList();
   }

    /**
     * Metodo de busqueda para encontrar una lsta de canciones que cumplen con TODOS los atributos de busqueda
     * @param artistName
     * @param songName
     * @param gender
     * @param year
     * @return
     */
    public List<Song> searchY(String artistName,String songName, Gender gender, String year){
       List<Song> songList= new ArrayList<>();
       for(Artist artistAux: artistTree){
            if (!artistAux.searchY(artistName,songName,gender,year).isEmpty()){
                songList=artistAux.searchY(artistName,songName,gender,year);
            }
       }
       return songList;
    }

    /**
     * Metodo que obtiene la lista de canciones que cumplan con minimo un atributo de busqueda
     * @param artistName
     * @param songName
     * @param gender
     * @param year
     * @return
     */
    public List<Song> searchO(String artistName,String songName, Gender gender, String year){
        List<Song> songList= new ArrayList<>();
        for(Artist artistAux: artistTree){
            if (!artistAux.searchO(artistName,songName,gender,year).isEmpty()){
                songList.addAll( artistAux.searchO(artistName,songName,gender,year));
            }
        }
        return songList;
    }

    /**
     * Metodo para obtener el genero más escuchado en general, de toda la tienda
     *
     * @return
     */
    public Gender getMostListenedGender(){
        Map<Gender, Integer> genderCount = new HashMap<>();

        // Recopilar los géneros más escuchados de todos los usuarios
        for (User user : usersMap.values()) {
            Gender mostListenedGender = user.getMostListenedGender(); //Se obtiene el mas escuchado de cada usuario
            genderCount.put(mostListenedGender, genderCount.getOrDefault(mostListenedGender, 0) + 1);
        }

        // Encontrar el género con el recuento más alto
        Gender mostListenedGender = null;
        int maxCount = 0;

        for (Map.Entry<Gender, Integer> entry : genderCount.entrySet()) {
            Gender genre = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                mostListenedGender = genre;
            }
        }
        return mostListenedGender;
    }

    /**
     * Metodo para obtener el artista mas escuchado de todos los usuarios
     * @return
     */
    public Artist getMostListenedArtist(){
        Map<Artist, Integer> artistCount= new HashMap<>();
        for (User userAux: usersMap.values() ){
            Artist mostListenedArtist= userAux.getMostListenedArtist(); //Obtener el artista mas escuchado de cada usuario
            artistCount.put(mostListenedArtist, artistCount.getOrDefault(mostListenedArtist,0)+1);
        }
        //Encontrar el artista mas escuchado
        Artist mostListenedArtisit= null;
        int maxCount= 0;
        for (Map.Entry<Artist,Integer> entry : artistCount.entrySet()){
            Artist artistAux= entry.getKey();
            int count = entry.getValue();

            if (count>maxCount){
                maxCount=count;
                mostListenedArtisit = artistAux;
            }
        }
        return mostListenedArtisit;
    }

    //===================== METODOS PARA REPRODUCIR MUSICA ================================ //

    // Método para reproducir una canción en YouTube usando WebView
    public void playSong(WebView webView, String videoUrl, boolean play) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    String url = play ? videoUrl.replace("watch?v=", "embed/") + "?autoplay=1" : "";
                    webView.getEngine().load(url);
                });
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true); // Para que el hilo se detenga cuando se cierre la aplicación
        thread.start();
    }

}
