package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.DoublyLinkedList;

import java.io.Serializable;

public class Artist implements Serializable, Comparable<Artist> {
    private String code;
    private String name;
    private String nationality;
    private Boolean isAlone;
    private DoublyLinkedList<Song> listaCanciones;

    public Artist() {
    }

    public Artist(String code, String name, String nationality, Boolean isAlone) {
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.isAlone = isAlone;
        this.listaCanciones= new DoublyLinkedList<>();
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

    public Boolean getAlone() {
        return isAlone;
    }

    public void setAlone(Boolean alone) {
        isAlone = alone;
    }

    public DoublyLinkedList<Song> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(DoublyLinkedList<Song> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }


    //--------------------Metodos propios del artista------------------------

    public void addSong(Song newSong){
        this.listaCanciones.addLast(newSong);
    }
    public void delateSong(Song songDelete){
        this.listaCanciones.delete(songDelete);
    }

    @Override
    public int compareTo(Artist another) {
        return this.name.compareTo(another.getName());
    }
}
