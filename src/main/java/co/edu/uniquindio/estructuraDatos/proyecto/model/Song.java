package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class Song implements Serializable {
    private String code;
    private String name;
    private String cover; //Caratula
    private String year;
    private String duration;
    private Gender gender;
    private URL link;
    private Artist artist;

    /**
     * Contructor vacio
     */
    public Song() {
    }

    /**
     * Contructor con los atributos de cancion ya asignada a un artista
     * @param code
     * @param name
     * @param cover
     * @param year
     * @param duration
     * @param gender
     * @param link
     * @param artist
     */
    public Song(String code, String name, String cover, String year, String duration, Gender gender, URL link, Artist artist) {
        this.code = code;
        this.name = name;
        this.cover = cover;
        this.year = year;
        this.duration = duration;
        this.gender = gender;
        this.link = link;
        this.artist = artist;
    }

    /**
     * Contructor con los atributos de cancion ya asignada a un artista nuevo
     * @param code
     * @param name
     * @param cover
     * @param year
     * @param duration
     * @param gender
     * @param link
     */
    public Song(String code, String name, String cover, String year, String duration, Gender gender, URL link) {
        this.code = code;
        this.name = name;
        this.cover = cover;
        this.year = year;
        this.duration = duration;
        this.gender = gender;
        this.link = link;
        this.artist= new Artist();
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Metodo que valida si dos canciones son iguales segun su codigo
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(code, song.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    /**
     * Metodo que valida la igualdad de dos canciones segun el codigo
     * @param code
     * @return
     */
    public boolean verifyCode(String code){
        return this.getCode().equals(code);
    }

    /**
     * Metodo que verifica la informacion de el motor de busqueda Y
     * @param artistName
     * @param songName
     * @param gender
     * @param year
     * @return
     */
    public boolean verifyInfoY(String artistName,String songName, Gender gender, String year){
        if (this.artist.getName().equals(artistName) && this.name.equals(songName) && this.gender==gender && this.year.equals(year)){
            return true;
        }
        return false;
    }

    /**
     * Metodo que verifica la informacion de el motor de busqueda O
     * @param artistName
     * @param songName
     * @param gender
     * @param year
     * @return
     */
    public boolean verifyInfoO(String artistName,String songName, Gender gender, String year){
        if(gender==null){
            if (this.artist.getName().equals(artistName) || this.name.equalsIgnoreCase(songName) || this.year.equalsIgnoreCase(year)
                    || this.name.toLowerCase().contains( songName.toLowerCase() ) || this.year.toLowerCase().contains( year.toLowerCase() )){
                return true;
            }
        }else{
            if (this.artist.getName().equals(artistName) || this.name.equalsIgnoreCase(songName) || this.year.equalsIgnoreCase(year)
                    || this.gender==gender || this.name.toLowerCase().contains( songName.toLowerCase() ) ||
                    this.year.toLowerCase().contains( year.toLowerCase() )){
                return true;
            }
        }


        return false;
    }

}
