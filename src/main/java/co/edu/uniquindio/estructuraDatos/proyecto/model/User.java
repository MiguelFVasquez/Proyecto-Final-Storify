package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.CircularLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Usuario se conoce su username (único), contraseña, email, además el usuario tiene una
lista propia de Canciones, dicha lista es una Lista circular.
* */
public class User implements Serializable {

    private String userName;
    private String password;
    private String email;
    private CircularLinkedList<Song> songList;



    public User() {
    }
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.songList = new CircularLinkedList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Song> getSongToList() {
        return songList.toList();
    }

    public CircularLinkedList<Song> getSongList() {
        return songList;
    }

    public void setSongList(CircularLinkedList<Song> songList) {
        this.songList = songList;
    }
    public boolean verifySong(String code){
        boolean flag=false;
            for (Song songAux: songList) {
                if (songAux.verifyCode(code)){
                    flag=true;


                    break;
                }
            }

        return flag;
    }

    /**
     *
     * @param newSong
     * @return
     * @throws SongException
     */
    public boolean addSongToList(Song newSong) throws SongException {
        boolean flag= false;
        if (verifySong(newSong.getCode())){
            throw new SongException("La canción ya se encuentra en la lista");
        }else {
            flag=true;
            songList.add(newSong);
        }
        return flag;
    }

    /**
     *
     * @param songDelete
     * @return
     * @throws SongException
     */
    public boolean removeSongFromList(Song songDelete) throws SongException{
        boolean flag= false;
        if (!verifySong(songDelete.getCode())){
            throw new SongException("La canción no se encuentra en la lista");
        }else {
            flag=true;
            songList.delete(songDelete);

        }
        return flag;
    }

    public Gender getMostListenedGender() {
        Map<Gender, Integer> genderCount = new HashMap<>();

        // Iterar sobre la lista de canciones y contar cada género
        for (Song songAux : songList) {
            Gender gender = songAux.getGender();
            genderCount.put(gender, genderCount.getOrDefault(gender, 0) + 1);
        }

        // Encontrar el género con el recuento más alto
        Gender mostLikedGender = null;
        int maxCount = 0;

        for (Map.Entry<Gender, Integer> entry : genderCount.entrySet()) {
            Gender gender = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                mostLikedGender = gender;
            }
        }
        return mostLikedGender;
    }

    public Artist getMostListenedArtist() {
        Map<Artist, Integer> artistCount = new HashMap<>();

        for (Song songAux : songList){
            Artist artist= songAux.getArtist();
            artistCount.put(artist,artistCount.getOrDefault(artist,0) +1);
        }
        //Artista con mas apariciones
        Artist mostLikedArtist= null;
        int maxCount=0;
        for (Map.Entry<Artist,Integer> entry : artistCount.entrySet()){
            Artist artist= entry.getKey();
            int count= entry.getValue();
            if (count>maxCount){
                maxCount=count;
                mostLikedArtist= artist;
            }
        }
        return mostLikedArtist;
    }

}

