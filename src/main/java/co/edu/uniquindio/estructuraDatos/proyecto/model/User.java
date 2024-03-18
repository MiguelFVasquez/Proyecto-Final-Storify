package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.CircularLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;

import java.io.Serializable;

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

    public CircularLinkedList<Song> getSongList() {
        return songList;
    }

    public void setSongList(CircularLinkedList<Song> songList) {
        this.songList = songList;
    }
    private boolean verifySong(String code){
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



}

