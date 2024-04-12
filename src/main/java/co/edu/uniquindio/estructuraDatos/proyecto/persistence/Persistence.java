package co.edu.uniquindio.estructuraDatos.proyecto.persistence;

import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.utilities.FileUtil;
import com.sun.mail.smtp.SMTPOutputStream;

import java.io.*;
import java.util.*;

public class Persistence {

    public static final String ROUTE_USERS_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Users.txt";
    public static final String ROUTE_SONGS_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Songs.txt";

    public static void loadDataFiles(Storify storify) throws UserException,FileNotFoundException, IOException{
        HashMap<String, User> loadedUsers = loadUsers();
        //LinkedList<Song> loadSongs = (LinkedList<Song>) loadSongs();

        if(!loadedUsers.isEmpty()){
            storify.getUsersMap().putAll( loadedUsers );
        }
    }

    public static void saveUsers(HashMap<String,User> userMap) throws IOException {
        String content = "";
        for (Map.Entry<String,User> entry : userMap.entrySet()){
            User user  = entry.getValue();
            content += user.getUserName() + "@@" + user.getPassword() +
                    "@@" + user.getEmail() + "\n";
        }
        FileUtil.guardarArchivo(ROUTE_USERS_FILE,content,false);
    }

    public static HashMap<String, User> loadUsers() throws FileNotFoundException,IOException {
        HashMap<String,User> users = new HashMap<>();
        ArrayList<String> content = FileUtil.leerArchivo(ROUTE_USERS_FILE);
        String line = "";
        for (int i = 0; i < content.size(); i++) {
            line = content.get(i);
            User user = new User();
            user.setUserName(line.split("@@")[0]);
            user.setPassword(line.split("@@")[1]);
            user.setEmail(line.split("@@")[2]);
            users.put(user.getUserName(), user); // Agregar el usuario al mapa
        }
        return users;
    }
    /*
    public static void saveSongs(List<Song> songList){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ROUTE_SONGS_FILE))){
            outputStream.writeObject(songList);
        }catch (IOException e){
            System.err.println("Error con la serializacion de canciones: "+ e.getMessage());
        }
    }

    public static List<Song> loadSongs(){
        List<Song> songList = new LinkedList<>();
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ROUTE_SONGS_FILE))){
            songList = (LinkedList<Song>) inputStream.readObject();
            System.out.println("Lista de canciones serializada correctamente desde el archivo: " + ROUTE_SONGS_FILE);
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Error al serializar la lista de canciones" + e.getMessage());
        }
        return songList;
    }
    */
}
