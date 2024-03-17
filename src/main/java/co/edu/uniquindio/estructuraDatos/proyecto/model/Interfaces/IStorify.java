package co.edu.uniquindio.estructuraDatos.proyecto.model.Interfaces;

import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.SongException;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;

public interface IStorify {

    //--------Metodos de usuario-----------------
    public boolean addUser(User newUser) throws UserException;
    public boolean deleteUser(User userDelete) throws UserException;
    public boolean updateUser(User userUpdate) throws UserException;

    //-------Metodos de artista----------------
    public boolean addArtist(Artist newArtist) throws ArtistException;
    public boolean deleteArtist(Artist artistDelete) throws ArtistException;
    public boolean updateArtist(Artist artistUpdate) throws ArtistException;
    //-----Metodos de cancion-------------------

    public boolean addSong(Song newSong) throws SongException;
    public boolean delateSong(Song songDelete) throws SongException;
    public boolean updateSong(Song songUpdate) throws SongException;

}
