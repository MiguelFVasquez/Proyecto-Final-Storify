package co.edu.uniquindio.estructuraDatos.proyecto.threads;

import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;

public class ThreadO extends Thread {
    private Storify storify;
    private String artistName;
    private String songName;
    private String year;
    private Gender gender;

    public ThreadO(Storify storify, String artistName, String songName, Gender gender, String year) {
        this.storify= storify;
        this.artistName= artistName;
        this.songName= songName;
        this.year= year;
        this.gender= gender;
    }

    @Override
    public void run() {
        storify.searchO(artistName,songName,gender,year);
    }
}
