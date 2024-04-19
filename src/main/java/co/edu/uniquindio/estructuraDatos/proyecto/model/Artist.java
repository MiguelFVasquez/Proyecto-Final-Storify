package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.DoublyLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;

import java.io.Serializable;

public class Artist implements Serializable, Comparable<Artist> {
    private String code;
    private String name;
    private String nationality;
    private Boolean isAlone;
    private DoublyLinkedList<Song> songList;

    public Artist() {
    }

    public Artist(String code, String name, String nationality, Boolean isAlone) {
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.isAlone = isAlone;
        this.songList = new DoublyLinkedList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getIsAlone() {
        return isAlone;
    }

    public void setAlone(Boolean alone) {
        isAlone = alone;
    }

    public DoublyLinkedList<Song> getSongList() {
        return songList;
    }

    public void setSongList(DoublyLinkedList<Song> songList) {
        this.songList = songList;
    }


    //--------------------Metodos propios del artista------------------------

    public void addSong(Song newSong){
        this.songList.addLast(newSong);
    }
    public void delateSong(Song songDelete){
        this.songList.delete(songDelete);
    }

    @Override
    public int compareTo(Artist another) {
        return this.name.compareTo(another.getName());
    }


    private boolean verifySong(String code){
        boolean flag=false;
        for (Song songAux: songList){
            if (songAux.verifyCode(code)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    public boolean addSongToList(Song newSong) throws SongException {
        boolean flag= false;
        if (verifySong(newSong.getCode())){
            throw new SongException("La cación ya se encuentra en la lista");
        }else {
            songList.addLast(newSong);
            flag=true;
        }
        return flag;
    }

    public boolean removeSongToList(Song songDelete) throws SongException{
        boolean flag= false;
        if (!verifySong(songDelete.getCode())){
            throw new SongException("La canción no se encuentra en la lista");
        }else {
            songList.delete(songDelete);
            flag=true;
        }
        return flag;
    }




    @Override
    public String toString() {
        return  name;
    }
}
